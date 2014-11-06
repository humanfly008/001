package com.stream.it.ss.view.jsf.base;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;

public class DisplayMessages {
	
	public static void showMessage(String msg, ResultBO resultBO){        
        if(resultBO.getException()==null){
            showInfoMessage(msg+" "+resultBO.getActionMessage());
        
        }else{
	    	if(resultBO.getErrorMessages() != null &&resultBO.getErrorMessages().size()>0){
		    	for(int i=0 ;i < resultBO.getErrorMessages().size();i++){
		    		showErrorMessage(msg+" "+resultBO.getErrorMessages().get(i));
		    	}	    	
	    	}else if(!resultBO.getErrorMessage().equals("")){
	    		showErrorMessage(msg+" "+resultBO.getErrorMessage());
	        
	    	}else if(resultBO.getInfoMessage() !=null &&resultBO.getInfoMessage().size()>0){
		    	for(int i=0 ;i < resultBO.getInfoMessage().size();i++){
		    		showInfoMessage(resultBO.getInfoMessage().get(i));
		    	}
	    	}else{
	    		showErrorMessage(msg+" "+resultBO.getErrorMessage());
	    	}
	    }
    }
    
    public static void showMessage(String msg, DataBo dataBO){        
        ResultBO resultBO = dataBO.getResultBO();
        if(resultBO.getException()==null){       	
            showInfoMessage(msg+" "+resultBO.getActionMessage());          
	    }else{
	    	if(resultBO.getErrorMessages() != null &&resultBO.getErrorMessages().size()>0){
		    	for(int i=0 ;i < resultBO.getErrorMessages().size();i++){
		    		showErrorMessage(msg+" "+resultBO.getErrorMessages().get(i));
		    	}	    	
	    	}else if(!resultBO.getErrorMessage().equals("")){
	    		showErrorMessage(msg+" "+resultBO.getErrorMessage());
	        
	    	}else if(resultBO.getInfoMessage() !=null &&resultBO.getInfoMessage().size()>0){
		    	for(int i=0 ;i < resultBO.getInfoMessage().size();i++){
		    		showInfoMessage(resultBO.getInfoMessage().get(i));
		    	}
	    	}else{
	    		showErrorMessage(msg+" "+resultBO.getErrorMessage());
	    	}
	    }
    }
    
    
    public static void showErrorMessage(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public static void showInfoMessage(String msg){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public static void addErrorMessage(String errorMsg, String errorMsgDetail){
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMsg , errorMsgDetail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
