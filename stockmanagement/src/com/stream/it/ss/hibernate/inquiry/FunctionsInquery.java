package com.stream.it.ss.hibernate.inquiry;

public class FunctionsInquery {
	private String no;
	private String funcId;
	private String funcName;
	private String funcUrl;
	private boolean checkDelete;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public boolean isCheckDelete() {
		return checkDelete;
	}
	public void setCheckDelete(boolean checkDelete) {
		this.checkDelete = checkDelete;
	}
	public String getFuncId() {
		return funcId;
	}
	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}
	public String getFuncName() {
		return funcName;
	}
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
	public String getFuncUrl() {
		return funcUrl;
	}
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}	
	
}
