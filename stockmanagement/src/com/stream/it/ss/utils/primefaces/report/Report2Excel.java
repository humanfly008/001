package com.stream.it.ss.utils.primefaces.report;

import java.io.IOException;
import java.util.List;

import org.primefaces.model.StreamedContent;

public abstract class Report2Excel extends H2HWebcustomReportProperties{
	protected String reportName;
	
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	@SuppressWarnings("rawtypes")
	public abstract StreamedContent genReportData(String reportName, List dataList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooter, String user) throws IOException;
	@SuppressWarnings("rawtypes")
	public abstract StreamedContent genReportData(String reportName, List dataList, String[]filedHeaderName, String[]headerName, String[][] reportDescriptionHeader, String[] reportSummaryFooterRow1, String[] reportSummaryFooterRow2, String user) throws IOException;
}
