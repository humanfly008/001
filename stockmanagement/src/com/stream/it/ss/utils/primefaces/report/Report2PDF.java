package com.stream.it.ss.utils.primefaces.report;

import java.io.IOException;
import java.util.List;

import org.primefaces.model.StreamedContent;

public abstract class Report2PDF extends H2HWebcustomReportProperties{
	protected String reportName;
	protected float[] columnWidths;	
	protected int[] cellValueAlign;
	
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
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	@SuppressWarnings("rawtypes")
	public abstract StreamedContent genReportData(String reportFileName, List dataList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooter, String user) throws IOException;
	@SuppressWarnings("rawtypes")
	public abstract StreamedContent genReportData(String reportName, List dataList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooterRow1,String[] reportSummaryFooterRow2, String user) throws IOException;
	
}
