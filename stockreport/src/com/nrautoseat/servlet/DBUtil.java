package com.nrautoseat.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBUtil {

    private static Context CTX = null;

    private static ResourceBundle config = ResourceBundle.getBundle("jdbc");

    @SuppressWarnings("unused")
	private static Context getInitialContext() throws NamingException {
    	if (CTX == null) {
    		CTX = new InitialContext();
    	} 
    	return CTX;
    }
	   
	public static Connection getConnectionJDBC() throws Exception{
	    // ON DEVELOP     
	    Connection conn = null;
		String userName = config.getString("jdbc.username");
	    String password = config.getString("jdbc.password");
	    
		String url = config.getString("jdbc.url");
	    Class.forName(config.getString("jdbc.driverClassName")).newInstance();
	    conn = DriverManager.getConnection(url, userName, password);
	    
		return conn;   
	}
}
