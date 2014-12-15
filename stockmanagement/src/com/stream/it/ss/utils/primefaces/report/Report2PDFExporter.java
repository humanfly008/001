package com.stream.it.ss.utils.primefaces.report;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import com.stream.it.ss.utils.DataListTrackingHelper;
import com.stream.it.ss.utils.format.StringType;

public class Report2PDFExporter extends Report2PDF{
	private static final Log logger = LogFactory.getLog(Report2PDFExporter.class);
	private static final String font = "com/stream/it/ss/utils/primefaces/report/TF_Pimai.ttf";
	
	@SuppressWarnings("rawtypes")
	public StreamedContent genReportData(String reportTitel, List dataList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooter, String user) throws IOException {
		logger.info("genReportData begin............");
		StreamedContent content = null;
		
		try {			
			String reportFileName = this.reportName+".pdf";
			
			//******* get result datalist for export report ******//
	    	DataListTrackingHelper dataListTrackingHelper = new DataListTrackingHelper(dataList, filedHeaderName);
			Map<Integer, Object> filedData = dataListTrackingHelper.getfiledData();

	    	
	    	//******* setting report layout ************//
	    	FileOutputStream fileout = new FileOutputStream(reportFileName);
			Document document = new Document(PageSize.A4.rotate(), 20, 20, 10, 20);		
			PdfWriter.getInstance(document, fileout);
			
			BaseFont bf = BaseFont.createFont(font, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font2 = new Font(bf, 13);
			
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm aa");
            String userAndTime = "User Name : " + user + "     Date : " + formatDate.format(new Date()) + "     Time : " + formatTime.format(new Date());    
            HeaderFooter footer = new HeaderFooter(new Phrase(userAndTime, new Font(bf, 15)), false);
            footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer); 
			document.open();	
					
            Paragraph paragraph;
            PdfPCell cell;
            int headerRow = 2;
                        
            PdfPTable headerLabeltable = null;
            
            PdfPTable table = new PdfPTable(filedHeaderName.length);
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);    
            
            
            //********* create report header ***********//
            paragraph = new Paragraph(new Phrase(reportTitel, new Font(bf, 18)));
			cell = new PdfPCell(paragraph);
    		cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
    		cell.setPaddingBottom(10);
    		cell.disableBorderSide(Cell.LEFT);
    		cell.disableBorderSide(Cell.TOP);
    		cell.disableBorderSide(Cell.RIGHT);
    		cell.disableBorderSide(Cell.BOTTOM);
    		cell.setColspan(dataListTrackingHelper.getFiledHeaderName().length);
    		
    		table.addCell(cell);
    		
    		//********* Set table header all page ****//
    		//********* crate Description Header *****//
    		if(reportDescriptionHeader!=null && reportDescriptionHeader.length >0 ){
    			int maxColumnTable = this.findMaxColumnTableHeaderLabel(reportDescriptionHeader);
    			headerLabeltable = new PdfPTable(maxColumnTable);
    			headerLabeltable.setWidthPercentage(100);    
    			
    			for (int i = 0; i < reportDescriptionHeader.length; i++) {
    				String tempValueHeaderLabel = "";
		    		String[]reportDescriptionHeaderValues = reportDescriptionHeader[i];

		    		for(int j=0; j<reportDescriptionHeaderValues.length; j++){		    			
		    			tempValueHeaderLabel = valueWithWidthSuffix(new StringBuffer(reportDescriptionHeaderValues[j]), 30);
		    		
		    			paragraph = new Paragraph(new Phrase(tempValueHeaderLabel, new Font(bf, 14)));
						cell = new PdfPCell(paragraph);
			    		cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    		cell.setPaddingBottom(2);
			    		cell.disableBorderSide(Cell.LEFT);
			    		cell.disableBorderSide(Cell.TOP);
			    		cell.disableBorderSide(Cell.RIGHT);
			    		cell.disableBorderSide(Cell.BOTTOM);
			    		cell.setColspan(1);
			    		headerLabeltable.addCell(cell);
		    		}
		    		
		    	}
		    	
    			cell = new PdfPCell(headerLabeltable);
	    		cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
	    		cell.setPaddingBottom(2);
	    		cell.disableBorderSide(Cell.LEFT);
	    		cell.disableBorderSide(Cell.TOP);
	    		cell.disableBorderSide(Cell.RIGHT);
	    		cell.disableBorderSide(Cell.BOTTOM);
	    		cell.setColspan(filedHeaderName.length);
    			table.addCell(cell);
    			headerRow++;
    		}
    		
    		//********* crate Header Detail *****//
			for(int i=0; i<filedHeaderName.length; i++){
				paragraph = new Paragraph(headerName[i], font2);
				cell = new PdfPCell(paragraph);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				table.addCell(cell);				
			}
			
			//********* crate Data Detail *****//
			for(int i=0; i<filedData.size(); i++){				
				List valueByRow = (List)filedData.get(i);	            
				//--- create cell value by row --//
	            for (int k = 0; k < valueByRow.size(); k++) {
	            	paragraph = new Paragraph((String)StringType.getString(valueByRow.get(k)), font2);
	            	cell = new PdfPCell(paragraph);
	            	cell.setHorizontalAlignment(cellValueAlign[k]);
	            	table.addCell(cell);
	            }	            
			}
			
			//********* crate Summary Footer *****//
			if(reportSummaryFooter!=null && reportSummaryFooter.length >0){
				for (int i = 0; i < reportSummaryFooter.length; i++) {
	            	paragraph = new Paragraph((String)StringType.getString(reportSummaryFooter[i]), font2);
	            	cell = new PdfPCell(paragraph);
	            	cell.setHorizontalAlignment(cellValueAlign[i]);
	            	table.addCell(cell);
	            }	
			}
			
			table.setHeaderRows(headerRow);
			document.add(table);			

			document.close();
			fileout.flush();
			fileout.close();
			
	        InputStream stream = new BufferedInputStream(new FileInputStream(reportFileName));
	        content = new DefaultStreamedContent(stream, "application/pdf", reportFileName);
			
		}catch (Exception e) {
	    	logger.error(e.getMessage());
	        e.printStackTrace();
	    }finally{
	    	logger.info("genReportData end............");
	    }
		
		return content;
	}
	
	@SuppressWarnings("rawtypes")
	public StreamedContent genReportData(String reportTitel, List dataList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooterRow1,String[] reportSummaryFooterRow2, String user) throws IOException {
		logger.info("genReportData begin............");
		StreamedContent content = null;
		
		try {			
			String reportFileName = this.reportName+".pdf";
			
			//******* get result datalist for export report ******//
	    	DataListTrackingHelper dataListTrackingHelper = new DataListTrackingHelper(dataList, filedHeaderName);
			Map<Integer, Object> filedData = dataListTrackingHelper.getfiledData();

	    	
	    	//******* setting report layout ************//
	    	FileOutputStream fileout = new FileOutputStream(reportFileName);
			Document document = new Document(PageSize.A4.rotate(), 20, 20, 10, 20);		
			PdfWriter.getInstance(document, fileout);
			
			BaseFont bf = BaseFont.createFont(font, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font2 = new Font(bf, 12);
			
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm aa");
            String userAndTime = "User Name : " + user + "     Date : " + formatDate.format(new Date()) + "     Time : " + formatTime.format(new Date());    
            HeaderFooter footer = new HeaderFooter(new Phrase(userAndTime, new Font(bf, 12)), false);
            footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer); 
            
			document.open();	
					
            Paragraph paragraph;
            PdfPCell cell;
            int headerRow = 2;
                        
            PdfPTable headerLabeltable = null;
            
            PdfPTable table = new PdfPTable(filedHeaderName.length);
            table.setWidths(columnWidths);
            table.setWidthPercentage(100);    
            
            
            //********* create report header ***********//
            paragraph = new Paragraph(new Phrase(reportTitel, new Font(bf, 18)));
			cell = new PdfPCell(paragraph);
    		cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
    		cell.setPaddingBottom(10);
    		cell.disableBorderSide(Cell.LEFT);
    		cell.disableBorderSide(Cell.TOP);
    		cell.disableBorderSide(Cell.RIGHT);
    		cell.disableBorderSide(Cell.BOTTOM);
    		cell.setColspan(dataListTrackingHelper.getFiledHeaderName().length);
    		
    		table.addCell(cell);
    		
    		//********* Set table header all page ****//
    		//********* crate Description Header *****//
    		if(reportDescriptionHeader!=null && reportDescriptionHeader.length >0 ){
    			int maxColumnTable = this.findMaxColumnTableHeaderLabel(reportDescriptionHeader);
    			headerLabeltable = new PdfPTable(maxColumnTable);
    			headerLabeltable.setWidthPercentage(100);    
    			
    			for (int i = 0; i < reportDescriptionHeader.length; i++) {
    				String tempValueHeaderLabel = "";
		    		String[]reportDescriptionHeaderValues = reportDescriptionHeader[i];

		    		for(int j=0; j<reportDescriptionHeaderValues.length; j++){		    			
		    			tempValueHeaderLabel = valueWithWidthSuffix(new StringBuffer(reportDescriptionHeaderValues[j]), 30);
		    		
		    			paragraph = new Paragraph(new Phrase(tempValueHeaderLabel, new Font(bf, 14)));
						cell = new PdfPCell(paragraph);
			    		cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
			    		cell.setPaddingBottom(2);
			    		cell.disableBorderSide(Cell.LEFT);
			    		cell.disableBorderSide(Cell.TOP);
			    		cell.disableBorderSide(Cell.RIGHT);
			    		cell.disableBorderSide(Cell.BOTTOM);
			    		cell.setColspan(1);
			    		headerLabeltable.addCell(cell);
		    		}
		    		
		    	}
		    	
    			cell = new PdfPCell(headerLabeltable);
	    		cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
	    		cell.setPaddingBottom(2);
	    		cell.disableBorderSide(Cell.LEFT);
	    		cell.disableBorderSide(Cell.TOP);
	    		cell.disableBorderSide(Cell.RIGHT);
	    		cell.disableBorderSide(Cell.BOTTOM);
	    		cell.setColspan(filedHeaderName.length);
    			table.addCell(cell);
    			headerRow++;
    		}
    		
    		//********* crate Header Detail *****//
			for(int i=0; i<filedHeaderName.length; i++){
				paragraph = new Paragraph(headerName[i], font2);
				cell = new PdfPCell(paragraph);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBackgroundColor(Color.LIGHT_GRAY);
				table.addCell(cell);				
			}
			
			//********* crate Data Detail *****//
			for(int i=0; i<filedData.size(); i++){				
				List valueByRow = (List)filedData.get(i);	            
				//--- create cell value by row --//
	            for (int k = 0; k < valueByRow.size(); k++) {
	            	paragraph = new Paragraph((String)StringType.getString(valueByRow.get(k)), font2);
	            	cell = new PdfPCell(paragraph);
	            	cell.setHorizontalAlignment(cellValueAlign[k]);
	            	table.addCell(cell);
	            }	            
			}
			
			//********* crate Summary Footer Row1 *****//
			if(reportSummaryFooterRow1!=null && reportSummaryFooterRow1.length >0){
				for (int i = 0; i < reportSummaryFooterRow1.length; i++) {
	            	paragraph = new Paragraph((String)StringType.getString(reportSummaryFooterRow1[i]), font2);
	            	cell = new PdfPCell(paragraph);
	            	cell.setHorizontalAlignment(cellValueAlign[i]);
	            	table.addCell(cell);
	            }	
			}
			
			//********* crate Summary Footer Row1 *****//
			if(reportSummaryFooterRow2!=null && reportSummaryFooterRow2.length >0){
				for (int i = 0; i < reportSummaryFooterRow2.length; i++) {
	            	paragraph = new Paragraph((String)StringType.getString(reportSummaryFooterRow2[i]), font2);
	            	cell = new PdfPCell(paragraph);
	            	cell.setHorizontalAlignment(cellValueAlign[i]);
	            	table.addCell(cell);
	            }	
			}
			
			table.setHeaderRows(headerRow);
			document.add(table);			

			document.close();
			fileout.flush();
			fileout.close();
			
	        InputStream stream = new BufferedInputStream(new FileInputStream(reportFileName));
	        content = new DefaultStreamedContent(stream, "application/pdf", reportFileName);
			
		}catch (Exception e) {
	    	logger.error(e.getMessage());
	        e.printStackTrace();
	    }finally{
	    	logger.info("genReportData end............");
	    }
		
		return content;
	}
	
	
	private String valueWithWidthSuffix(StringBuffer valueParam, int columnWidth){
		if(valueParam.length()<columnWidth){
			valueParam.append(" ");
			valueWithWidthSuffix(valueParam, columnWidth);
		}
		return valueParam.toString();
	}
	
	private int findMaxColumnTableHeaderLabel(String[][] headerLabelRow){
		int maxColumn = 0;
		for(int i=0; i<headerLabelRow.length; i++){

			if(headerLabelRow[i].length>maxColumn){
				maxColumn = headerLabelRow[i].length;
			}
		}
		
		return maxColumn;
	}
	
	//*************************************
	public float[] getColumnWidths() {
		return columnWidths;
	}
	public void setColumnWidths(float[] columnWidths) {
		this.columnWidths = columnWidths;
	}
	public int[] getCellValueAlign() {
		return cellValueAlign;
	}
	public void setCellValueAlign(int[] cellValueAlign) {
		this.cellValueAlign = cellValueAlign;
	}
}
