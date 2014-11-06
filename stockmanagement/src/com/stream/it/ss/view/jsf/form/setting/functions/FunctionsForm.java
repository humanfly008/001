package com.stream.it.ss.view.jsf.form.setting.functions;

import com.stream.it.ss.base.databo.DataBo;

public class FunctionsForm extends DataBo{	
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
