package com.stream.it.ss.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SuppressWarnings("rawtypes")
public class DataListTrackingHelper {
	public String[] filedName;
	
	public List dataList;

	public DataListTrackingHelper(List dataList, String[]filedHeaderName){
		this.filedName = filedHeaderName;
		this.dataList = dataList;
	}
	
	public String[]getFiledHeaderName()throws SecurityException, NoSuchMethodException{
		String tempFiledHeader = "";
		
		if(this.filedName==null || filedName.length==0){
			if(this.dataList!=null){
				Object obj = this.dataList.get(0);
				Class<? extends Object> classObj = obj.getClass();
				Method[] methodClass = classObj.getMethods();
				
				System.out.println("class of obj list.."+classObj);				
				
				for(int j=0; j<methodClass.length; j++){
	            	if(methodClass[j].getName().indexOf("get")>=0 && !methodClass[j].getName().equals("getClass")){
	            		String methodName = methodClass[j].getName().replaceAll("get", "");
	            		System.out.println("filed name.."+methodName);
	            		tempFiledHeader+=methodName+",";
	            	}		            	
	            }
			}			
			filedName = tempFiledHeader.split(",");			
		}else{
			StringBuilder result = new StringBuilder();
			for(int i=0; i<filedName.length; i++){				
				result.append(Character.toUpperCase(filedName[i].charAt(0)));
				result.append(filedName[i].substring(1)+",");
				//System.out.println(result.toString());
			}
			filedName = result.toString().split(",");
		}
		
		return filedName;
	}
	
	public Map<Integer,Object>getfiledData() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, Exception{
		Map<Integer, Object> result = new HashMap<Integer,Object>();

		this.getFiledHeaderName();
		
		for(int i=0; i<dataList.size(); i++){
			Object obj = dataList.get(i);
			Class<? extends Object> classObj = obj.getClass();
			
			List<Object> value = new ArrayList<Object>();
			for(int j=0; j<this.filedName.length; j++){
				String invokeMethod = "get"+filedName[j];
				Method methodObj = classObj.getMethod(invokeMethod, null);
					
				Object filedValue = methodObj.invoke(obj,null);
        		//System.out.println("filed value.."+StringType.getString(filedValue));		    
				
				if(filedValue!=null){
					value.add(filedValue);
				}else{
					value.add("");
				}
				
			}
			
			result.put(i, value);			
		}			
		
		return result;
	}	
}
