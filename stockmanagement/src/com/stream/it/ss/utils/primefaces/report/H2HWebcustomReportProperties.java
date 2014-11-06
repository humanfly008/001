package com.stream.it.ss.utils.primefaces.report;

public abstract class H2HWebcustomReportProperties {
	protected String[][] reportDescriptionHeader;
	protected String[][] reportSummaryFooter;
	
	public String[][] getReportDescriptionHeader() {
		return reportDescriptionHeader;
	}
	public void setReportDescriptionHeader(String[][] reportDescriptionHeader) {
		this.reportDescriptionHeader = reportDescriptionHeader;
	}
	public String[][] getReportSummaryFooter() {
		return reportSummaryFooter;
	}
	public void setReportSummaryFooter(String[][] reportSummaryFooter) {
		this.reportSummaryFooter = reportSummaryFooter;
	}
}
