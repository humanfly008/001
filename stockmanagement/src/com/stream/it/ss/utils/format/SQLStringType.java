package com.stream.it.ss.utils.format;

public class SQLStringType {
	
	public static String likeValue(String parameter){
		String result = null;
		try {
            if(parameter!=null && !parameter.equals(""))
            	result = "%"+parameter+"%";
            
		} catch (Throwable e) {
            e.printStackTrace();
		}
		
		return result;
	}
	
	public static String likePrefixValue(String parameter){
		String result = null;
		try {
			if(parameter!=null && !parameter.equals(""))
            	result = parameter+"%";
            
		} catch (Throwable e) {
            e.printStackTrace();
		}
		
		return result;
	}
	
	public static String likeSuffixValue(String parameter){
		String result = null;
		try {
			if(parameter!=null && !parameter.equals(""))
            	result = "%"+parameter;
            
		} catch (Throwable e) {
            e.printStackTrace();
		}
		
		return result;
	}
}
