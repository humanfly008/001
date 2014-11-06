package com.stream.it.ss.utils.primefaces.report;

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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.stream.it.ss.utils.DataListTrackingHelper;
import com.stream.it.ss.utils.format.StringType;

public class Report2ExcelExporter extends Report2Excel{
	private static final Log logger = LogFactory.getLog(Report2ExcelExporter.class);
	
	@SuppressWarnings("rawtypes")
	public StreamedContent genReportData(String reportTitle, List dataList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooter, String user) throws IOException {
		logger.info("genReportData begin............");

		StreamedContent content = null;
		Row row 	= null;
	    Cell cell 	= null;

	    try {
	    	
	    	String reportFileName = this.reportName+".xls";
	    	
			//******* get result datalist for export report ******//
	    	DataListTrackingHelper dataListTrackingHelper = new DataListTrackingHelper(dataList, filedHeaderName);
			Map<Integer, Object> filedData = dataListTrackingHelper.getfiledData();
	    	
	    	//********* prepare poi excel exporter *****//
	    	Workbook wb = new HSSFWorkbook();
	        HSSFCellStyle styleHeader = (HSSFCellStyle) wb.createCellStyle();
	        
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm aa");
            String userAndTime = "User Name : " + user + "     Date : " + formatDate.format(new Date()) + "     Time : " + formatTime.format(new Date());    

	        HSSFFont fontHeader = (HSSFFont) wb.createFont();
	        fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        styleHeader.setFont(fontHeader);
	        Sheet sheet = wb.createSheet("sheet");
	        
	        Footer footer = sheet.getFooter();
	        footer.setLeft(userAndTime);
	     

	        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
	        wb.write(fileOut);
	        fileOut.close();
	        
	        short currentRowReport = 0;
	        
	        row = sheet.createRow(currentRowReport);
	        cell = row.createCell(0);
	        cell.setCellStyle(styleHeader);
	        cell.setCellValue(reportTitle);	        

	        //********* crate Description Header *****//
	        if(reportDescriptionHeader!=null && reportDescriptionHeader.length >0){
	        	row = sheet.createRow(++currentRowReport);
	        	
	        	for (int i = 0; i < reportDescriptionHeader.length; i++) {
		        	String[]reportDescriptionHeaderValues = reportDescriptionHeader[i];
		        	++currentRowReport;
		        	row = sheet.createRow(currentRowReport);
		        	
		    		for(int j=0; j<reportDescriptionHeaderValues.length; j++){
		    			cell = row.createCell(j);
		 	           	cell.setCellValue((String)StringType.getString(reportDescriptionHeaderValues[j]));	                
		    		}
	        	}
	        	
	        	row = sheet.createRow(++currentRowReport);
	        }
	    		        
	        
	        //********* create header detail ***********//
	        row = sheet.createRow(++currentRowReport);
	        for (int i = 0; i < headerName.length; i++) {
	            cell = row.createCell(i);
	            cell.setCellStyle(styleHeader);
	            cell.setCellValue(headerName[i]);
	        }

	        //******** create list detail *************//
	        for (int i=0;i<filedData.size();i++) {
	            row = sheet.createRow(++currentRowReport);
	            
	            List valueByRow = (List)filedData.get(i);	            

	            //--- create cell value by row --//
	            for (int k = 0; k < valueByRow.size(); k++) {
	                cell = row.createCell(k);
	                cell.setCellValue((String)StringType.getString(valueByRow.get(k)));	                
	            }
	        }
	        
	        //********* crate Summary Footer *****//
	        if(reportSummaryFooter!=null && reportSummaryFooter.length >0){
	        	row = sheet.createRow(++currentRowReport);
	        		        	
		    	for(int j=0; j<reportSummaryFooter.length; j++){
		    		cell = row.createCell(j);
		 	      	cell.setCellValue((String)StringType.getString(reportSummaryFooter[j]));	                
		   		}	     
	        }
	        
	        
	        //** output stream ***//
	        FileOutputStream fos = new FileOutputStream(reportFileName);
	        wb.write(fos);
	        fos.flush();
	        fos.close();

	        InputStream stream = new BufferedInputStream(new FileInputStream(reportFileName));
	        content = new DefaultStreamedContent(stream, "application/xls", reportFileName);
	        
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	        e.printStackTrace();
	    }finally{
	    	logger.info("genReportData end............");
	    }
	    
	    
	    return content;
	}
	
	@SuppressWarnings("rawtypes")
	public StreamedContent genReportData(String reportTitle, List dataList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooterRow1, String[] reportSummaryFooterRow2,String user) throws IOException {
		logger.info("genReportData begin............");

		StreamedContent content = null;
		Row row 	= null;
	    Cell cell 	= null;

	    try {
	    	
	    	String reportFileName = this.reportName+".xls";
	    	
			//******* get result datalist for export report ******//
	    	DataListTrackingHelper dataListTrackingHelper = new DataListTrackingHelper(dataList, filedHeaderName);
			Map<Integer, Object> filedData = dataListTrackingHelper.getfiledData();
	    	
	    	//********* prepare poi excel exporter *****//
	    	Workbook wb = new HSSFWorkbook();
	        HSSFCellStyle styleHeader = (HSSFCellStyle) wb.createCellStyle();
	        
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm aa");
            String userAndTime = "User Name : " + user + "     Date : " + formatDate.format(new Date()) + "     Time : " + formatTime.format(new Date());    

	        HSSFFont fontHeader = (HSSFFont) wb.createFont();
	        fontHeader.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        styleHeader.setFont(fontHeader);
	        Sheet sheet = wb.createSheet("sheet");
	        
	        Footer footer = sheet.getFooter();
	        footer.setLeft(userAndTime);
	     

	        FileOutputStream fileOut = new FileOutputStream("workbook.xls");
	        wb.write(fileOut);
	        fileOut.close();
	        
	        short currentRowReport = 0;
	        
	        row = sheet.createRow(currentRowReport);
	        cell = row.createCell(0);
	        cell.setCellStyle(styleHeader);
	        cell.setCellValue(reportTitle);	        

	        //********* crate Description Header *****//
	        if(reportDescriptionHeader!=null && reportDescriptionHeader.length >0){
	        	row = sheet.createRow(++currentRowReport);
	        	
	        	for (int i = 0; i < reportDescriptionHeader.length; i++) {
		        	String[]reportDescriptionHeaderValues = reportDescriptionHeader[i];
		        	++currentRowReport;
		        	row = sheet.createRow(currentRowReport);
		        	
		    		for(int j=0; j<reportDescriptionHeaderValues.length; j++){
		    			cell = row.createCell(j);
		 	           	cell.setCellValue((String)StringType.getString(reportDescriptionHeaderValues[j]));	                
		    		}
	        	}
	        	
	        	row = sheet.createRow(++currentRowReport);
	        }
	    		        
	        
	        //********* create header detail ***********//
	        row = sheet.createRow(++currentRowReport);
	        for (int i = 0; i < headerName.length; i++) {
	            cell = row.createCell(i);
	            cell.setCellStyle(styleHeader);
	            cell.setCellValue(headerName[i]);
	        }

	        //******** create list detail *************//
	        for (int i=0;i<filedData.size();i++) {
	            row = sheet.createRow(++currentRowReport);
	            
	            List valueByRow = (List)filedData.get(i);	            

	            //--- create cell value by row --//
	            for (int k = 0; k < valueByRow.size(); k++) {
	                cell = row.createCell(k);
	                cell.setCellValue((String)StringType.getString(valueByRow.get(k)));	                
	            }
	        }
	        
	        //********* crate Summary Footer *****//
	        if(reportSummaryFooterRow1!=null && reportSummaryFooterRow1.length >0){
	        	row = sheet.createRow(++currentRowReport);
	        		        	
		    	for(int j=0; j<reportSummaryFooterRow1.length; j++){
		    		cell = row.createCell(j);
		 	      	cell.setCellValue((String)StringType.getString(reportSummaryFooterRow1[j]));	                
		   		}	     
	        }
	        
	        if(reportSummaryFooterRow2!=null && reportSummaryFooterRow2.length >0){
	        	row = sheet.createRow(++currentRowReport);
	        		        	
		    	for(int j=0; j<reportSummaryFooterRow2.length; j++){
		    		cell = row.createCell(j);
		 	      	cell.setCellValue((String)StringType.getString(reportSummaryFooterRow2[j]));	                
		   		}	     
	        }
	        
	        
	        //** output stream ***//
	        FileOutputStream fos = new FileOutputStream(reportFileName);
	        wb.write(fos);
	        fos.flush();
	        fos.close();

	        InputStream stream = new BufferedInputStream(new FileInputStream(reportFileName));
	        content = new DefaultStreamedContent(stream, "application/xls", reportFileName);
	        
	    } catch (Exception e) {
	    	logger.error(e.getMessage());
	        e.printStackTrace();
	    }finally{
	    	logger.info("genReportData end............");
	    }
	    
	    
	    return content;
	}
}
