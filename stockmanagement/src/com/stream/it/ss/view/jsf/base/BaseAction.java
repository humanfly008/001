package com.stream.it.ss.view.jsf.base;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;

import com.stream.it.ss.utils.Context;


public class BaseAction implements Serializable{
	private static final long serialVersionUID = 1L;
	public static ApplicationContext applicationContext = Context.applicationContext;
	
	public static HttpSession getHttpSession() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facescontext.getExternalContext().getSession(true);
        return session;
    }
    
    public static Map<String, Object> getSessionMap(){
        FacesContext facescontext = FacesContext.getCurrentInstance();
        Map<String, Object> sessionMap = facescontext.getExternalContext().getSessionMap();
        return sessionMap;
    }

    public static HttpServletResponse getHttpServletResponse() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        HttpServletResponse response=(HttpServletResponse)facescontext.getExternalContext().getResponse();
        return response;
    }

    public static HttpServletRequest getHttpServletRequest() {
        FacesContext facescontext = FacesContext.getCurrentInstance();
        HttpServletRequest request=(HttpServletRequest)facescontext.getExternalContext().getRequest();
        return request;
    }
    
    public static ServletContext getServletContext() {
        ServletContext context = getHttpSession().getServletContext();
        return context;
    }
	
}
