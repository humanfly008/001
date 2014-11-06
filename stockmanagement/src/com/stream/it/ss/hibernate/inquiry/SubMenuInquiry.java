package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;

public class SubMenuInquiry  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String funcId;
	private String funcName;
	private String funcUrl;
	
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
