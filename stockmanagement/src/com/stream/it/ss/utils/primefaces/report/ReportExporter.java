package com.stream.it.ss.utils.primefaces.report;

import java.io.IOException;
import java.util.List;

import org.primefaces.model.StreamedContent;

import com.stream.it.ss.base.constant.ExportDataConstantMSG;


public class ReportExporter {
	private String reportName;
	private Report2PDF report2pdfExporter;
	private Report2Excel report2ExcelExporter;
	
	public ReportExporter(){
		report2pdfExporter = new Report2PDFExporter();
		report2ExcelExporter = new Report2ExcelExporter();
	}
	
	@SuppressWarnings("rawtypes")
	public StreamedContent genReportData(String reportType, String reportTitel, List reportDateList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooter, String user) throws IOException,Exception{
		StreamedContent streamedContent = null;
		
		report2ExcelExporter.setReportName(reportName);
		report2pdfExporter.setReportName(reportName);
		
		if(reportType.equalsIgnoreCase(ExportDataConstantMSG.XLS))
			streamedContent = report2ExcelExporter.genReportData(reportTitel, reportDateList, filedHeaderName, headerName, reportDescriptionHeader, reportSummaryFooter, user);
				
		if(reportType.equalsIgnoreCase(ExportDataConstantMSG.PDF))		
			streamedContent = report2pdfExporter.genReportData(reportTitel, reportDateList, filedHeaderName, headerName, reportDescriptionHeader, reportSummaryFooter, user);			
		
		
		return streamedContent;
	}
	
	@SuppressWarnings("rawtypes")
	public StreamedContent genReportData(String reportType, String reportTitel, List reportDateList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooterRow1, String[] reportSummaryFooterRow2, String user) throws IOException,Exception{
		StreamedContent streamedContent = null;
		
		report2ExcelExporter.setReportName(reportName);
		report2pdfExporter.setReportName(reportName);
		
		if(reportType.equalsIgnoreCase(ExportDataConstantMSG.XLS))
			streamedContent = report2ExcelExporter.genReportData(reportTitel, reportDateList, filedHeaderName, headerName, reportDescriptionHeader, reportSummaryFooterRow1, reportSummaryFooterRow2, user);
				
		if(reportType.equalsIgnoreCase(ExportDataConstantMSG.PDF))		
			streamedContent = report2pdfExporter.genReportData(reportTitel, reportDateList, filedHeaderName, headerName, reportDescriptionHeader, reportSummaryFooterRow1, reportSummaryFooterRow2, user);			
		
		
		return streamedContent;
	}

	//***** setter,getter *********//
	public void setReport2pdfExporter(Report2PDF report2pdfExporter) {
		this.report2pdfExporter = report2pdfExporter;
	}
	public void setReport2ExcelExporter(Report2Excel report2ExcelExporter) {
		this.report2ExcelExporter = report2ExcelExporter;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}		
	
}
