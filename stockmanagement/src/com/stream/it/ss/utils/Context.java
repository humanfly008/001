package com.stream.it.ss.utils;


import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.springframework.*;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class Context {
	private static Context instance = null;
    private static ServletContext servletContext = null;
    public static ApplicationContext applicationContext = null;
    
    private Context(){}

    public static Context getInstance() {
        if (instance == null) {
            instance = new Context();
            servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        }
        
        return instance;
    }    
}
