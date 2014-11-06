package com.stream.it.ss.utils.format;

public class NumberType {
	public static Byte getByte(String theString) {	
        Byte byteReturn = null;	
        if (theString == null || theString.equals("")) {	
            byteReturn = new Byte("0");	
        } else {	
            byteReturn = new Byte(Byte.parseByte(theString));	
        }	
        return byteReturn;	
    }
	
    public static Double getDouble(Object theObject) {		
        Double doubleReturn = null;	
        String theString = null;
	
        if (theObject != null && !"".equals(theObject.toString().trim())) {	
            theString = theObject.toString();	
            if(theString.indexOf("(") != -1 && theString.indexOf(")") != -1){	
                theString = StringType.getStringWithoutCharector(theString,'(');		
                theString = StringType.getStringWithoutCharector(theString,')');		
                theString = "-" + theString;		
            }
			
            doubleReturn = new Double(StringType.getStringWithoutCharector(theString.toString().trim(),','));
	
        }else{	
            doubleReturn =	new Double(0);	
        }

        return doubleReturn;
    }

	
    public static Double getDouble(Double theDouble) {
        Double doubleReturn = null;
        if (theDouble != null && !"".equals(theDouble.toString().trim())) {
            doubleReturn = new Double(theDouble.toString().trim());
        } else {
            doubleReturn = new Double(0);
        }
        return doubleReturn;
    }
	
    public static Double getDouble(double theDouble) {
        Double doubleReturn = null;
        try{
            doubleReturn = new Double(theDouble);
        }catch (Exception e) {
            return (new Double(0.00));
        }
        return doubleReturn;	
    }

	
    public static double getDoubleValue(Double theDouble) {
        double doubleReturn;	
        if (theDouble != null) {
            doubleReturn = (theDouble.doubleValue()*100)/100;
        } else {
            doubleReturn = 0.00;
        }

        return doubleReturn;	
    }
	
    public static Double getDouble(String theString) {
        Double doubleReturn = null;
        if (theString == null || theString.trim().equals("")) {
            doubleReturn = new Double(0.00);
        }else {		
            if(theString.indexOf("(") != -1 && theString.indexOf(")") != -1){
                theString = StringType.getStringWithoutCharector(theString,'(');
                theString = StringType.getStringWithoutCharector(theString,')');
                theString = "-" + theString;
            }
            doubleReturn = new Double(StringType.getStringWithoutCharector(theString,','));
        }
        return doubleReturn;
    }

    public static Double getDouble(String theString,int theFractionDigits) {
        Double doubleReturn = null;
        if(theFractionDigits > 0 && theString.indexOf(".") == -1){			
            String temp = theString.substring(theString.length()-theFractionDigits);	
            theString = theString.substring(0,theString.length()-theFractionDigits);	
            theString += ".";	
            theString += temp;	
        }
        doubleReturn = getDouble(theString);
		
        return doubleReturn;
    }
	
    public static float getFloatValue(Float theFloat) {		
        float floatReturn;

        if (theFloat != null) {	
            floatReturn = theFloat.floatValue();
        } else {
            floatReturn = 0.00f;
        }

        return floatReturn;	
    }
	
    public static Float getFloat(Object theObject) {
        Float floatReturn = null;
        if(theObject==null){
            floatReturn = null;
        }else{
            floatReturn = new Float(StringType.getStringWithoutCharector(theObject.toString(),','));
        }
	
        return floatReturn;
    }
	
    public static Float getFloat(String theString) {
        Float floatReturn = null;
        if(theString == null||theString.trim().length()==0){
            floatReturn = new Float(0);
        }else{
            floatReturn = new Float(Float.parseFloat(theString));
        }
	
        return floatReturn;
    }
	
    public static Float getFloat(float theFloat) {
        Float floatReturn = null;
        floatReturn = new Float(theFloat);
	
        return floatReturn;	
    }
	
    public static Long getLong(Object theObject) {
    	Long shortReturn = null;
		
        if (theObject != null && !"".equals(theObject.toString().trim())) {
            shortReturn = Long.valueOf(theObject.toString().trim());	
        } else {		
            shortReturn = new Long(0);
        }	
       
        return shortReturn;
    }

    public static Long getLong(String theString) {			
        Long longReturn = null;	
        if(theString == null||theString.equals("")){	
            longReturn = new Long(0L);	
        }else{	
            longReturn = new Long(Long.parseLong(StringType.getStringWithoutCharector(theString.toString().trim(),',')));	
        }
	
        return longReturn;	
    }

    public static Long getLong(int theInt) {
        Long longReturn = null;	
        longReturn = new Long(Integer.toString(theInt));

        return longReturn;	
    }

	
    public static long getLongValue(Long theLong) {
        long longReturn = 0;;
        if(theLong != null){
            longReturn = theLong.longValue();
        }

        return longReturn;	
    }

	
    public static Integer getInteger(String theString) {
        Integer integerReturn = null;	
        if (theString == null || theString.trim().equals("")) {	
            integerReturn = new Integer(0);	
        } else {	
            integerReturn = new Integer(Integer.parseInt(StringType.getStringWithoutCharector(theString.trim(),',')));	
        }
	
        return integerReturn;	
    }

    public static int getIntegerValue(Integer theInteger) {
        int intValue = 0;
        if(theInteger != null){			
            intValue = theInteger.intValue();
        }

        return intValue;
    }
	
    public static Short getShort(Object theObject) {
    	Short shortReturn = null;

    	if (theObject != null && !"".equals(theObject.toString().trim())) {
            shortReturn = Short.valueOf(StringType.getStringWithoutCharector(theObject.toString().trim(),','));
    	}

    	return shortReturn;
    }

    public static Short getShort(int theInt) {
        Short shortReturn = null;	
        shortReturn = new Short(Integer.toString(theInt));

        return shortReturn;	
    }

    public static Short getShort(String theString) {
        Short shortReturn = null;
        if (theString == null || theString.trim().equals("")) {
            shortReturn = new Short("0");
        } else {
            shortReturn = new Short(Short.parseShort(theString));
        }
        
        return shortReturn;	
    }
	
    public static Double getDoubleValid(String input){
        try{
            String temp = StringType.replaceAll(input, ",", "");
            if(temp.indexOf("(") != -1){ // Nagative Number.
                temp = StringType.replaceAll(temp, "(", "");
                temp = StringType.replaceAll(temp, ")", "");

                return new Double(-1 * Double.parseDouble(temp));
		
            }else{
                return new Double(Double.parseDouble(temp));	
            }
		
        }catch(Exception ex){
            return null;
        }
    }
}
