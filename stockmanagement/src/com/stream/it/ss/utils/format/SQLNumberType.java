package com.stream.it.ss.utils.format;

public class SQLNumberType {
	
	public static Long numberValue(Long parameter){
		try {
           if(parameter != null)
        	   if(parameter!=0)
               	return parameter;
            
		} catch (Throwable e) {
            e.printStackTrace();
		}
		
		return null;		
	}
	
	public static Long numberValue(String parameter){
		try {
           if(parameter != null && !parameter.equals(""))
        	   return Long.parseLong(parameter);
            
		} catch (Throwable e) {
            e.printStackTrace();
		}
		
		return null;		
	}
}
