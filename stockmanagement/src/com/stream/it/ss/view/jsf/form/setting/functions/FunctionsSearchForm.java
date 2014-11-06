package com.stream.it.ss.view.jsf.form.setting.functions;

import com.stream.it.ss.base.databo.SearchBean;

public class FunctionsSearchForm extends SearchBean{
    private String funcId;
    private String funcName;
    private boolean searchMsg ;

	public String getfuncId() {
		return funcId;
	}

	public void setfuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getfuncName() {
		return funcName;
	}

	public void setfuncName(String funcName) {
		this.funcName = funcName;
	}

	public boolean isSearchMsg() {
		return searchMsg;
	}

	public void setSearchMsg(boolean searchMsg) {
		this.searchMsg = searchMsg;
	} 

}
