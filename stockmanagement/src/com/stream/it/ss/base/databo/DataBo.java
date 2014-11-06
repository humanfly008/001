package com.stream.it.ss.base.databo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DataBo implements Serializable{    
    private ResultBO resultBO = new ResultBO();
    private SecuriyBO securiyBO = new SecuriyBO();
    
    public ResultBO getResultBO() {
        return resultBO;
    }

    public void setResultBO(ResultBO resultBO) {
        this.resultBO = resultBO;
    }

	public SecuriyBO getSecuriyBO() {
		return securiyBO;
	}

	public void setSecuriyBO(SecuriyBO securiyBO) {
		this.securiyBO = securiyBO;
	}
}
