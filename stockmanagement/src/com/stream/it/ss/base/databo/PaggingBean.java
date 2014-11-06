package com.stream.it.ss.base.databo;

import java.io.Serializable;

import com.stream.it.ss.utils.format.*;


@SuppressWarnings("serial")
public class PaggingBean implements Serializable{
    private int pageNo = 1;
    private int rowsPerPage;
    private int[] rowsPerPageOption = {5,10,15,20,50,100};
    
    private int totalPage;
    private int totalRow;
    
    public PaggingBean(){
    }
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}
	public int getRowsPerPage() {
		if(rowsPerPage==0)
			return 10;
		return rowsPerPage;
	}
	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public int[] getRowsPerPageOption() {
		return rowsPerPageOption;
	}
	public void setRowsPerPageOption(int[] rowsPerPageOption) {
		this.rowsPerPageOption = rowsPerPageOption;
	}
	public String getPageNoStr(){
		return StringType.getIntegerNumberPercentFormatted((double) pageNo);
	}
	public String getRowsPerPageStr(){
		return StringType.getIntegerNumberPercentFormatted((double) rowsPerPage);
	}
	public String getTotalPageStr(){
		return StringType.getIntegerNumberPercentFormatted((double) totalPage);
	}
	public String getTotalRowStr(){
		return StringType.getIntegerNumberPercentFormatted((double) totalRow);
	}
}
