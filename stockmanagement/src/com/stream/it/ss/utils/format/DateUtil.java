package com.stream.it.ss.utils.format;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	public static final String HOUR_SECOND_FORMAT = "HH:mm:ss";
	public static final String HOUR_SECOND_FORMAT2 = "HH:mm";
    public static final String LONG_DATE_FORMAT = "dd/MM/yyyy";
    public static final String LONG_DATE_FORMAT_LOCALTHAI = "dd-MM-yyyy";
    public static final String LONG_DATE_FORMAT_REPORT = "yyyy/MM/dd";
    public static final String LONG_DATE_FORMAT_REPORT_2 = "yyyy-MM-dd";
    public static final String SHORT_DATE_FORMAT_REPORT = "dd/MM/yyyy";
    public static final String YEAR_MONTH_FORMAT = "yyyyMM";
    public static final String YEAR_MONTH_DAY_FORMAT = "yyyyMMdd";
    public static final String YEAR_MONTH_WEEK_FORMAT = "yyyyMMWW";
    public static final String YEAR_FORMAT = "yyyy";
    public static final String MONTH_FORMAT = "MM";
    public static final String DATE_FORMAT = "dd";
    public static final Locale SYS_LOCALE = Locale.US;
    public static final Locale SYS_LOCALE_THAI = new Locale("th","TH");
    public static final String LONG_DATE_FORMAT_REPORT_HOUR_SECOND_FORMAT = "yyyyMMddhhmmss";
    public static final String LONG_DATE_HOUR_SECOND_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String LONG_DATE_HOUR_SECOND_FORMAT2 = "dd/MM/yyyy HH:mm";
    public static final String LONG_DATE_HOUR_SECOND_FORMAT3 = "dd/MM/yyyy HH:mm aa";
    public static final String FORMAT_YEAR = "yyyy";
    public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd HH:mm:ss.ms";


    public static String getSysYear() {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_YEAR);
        Date date = new Date();
        String year = dateFormat.format(date);
	
        return year;
    }

    public static String getStrDate(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT,SYS_LOCALE);
        String dd = "";
		try {
			if(d!=null)
	            dd = dateFormat.format(d);
		} catch (Exception e) {
	            dd = "";
		}
		
		return dd;
    }

    public static String getStrDateThai(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT,SYS_LOCALE_THAI);
		String dd = "";
		try {
			if(d!=null)
	            dd = dateFormat.format(d);
		} catch (Exception e) {
	            dd = "";
		}
		
		return dd;
    }
       
    public static String getStrDateThaiHourSecond(Date d){
    
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_HOUR_SECOND_FORMAT,SYS_LOCALE_THAI);
        String dd = "";
		try {
			if(d!=null)
	            dd = dateFormat.format(d);
		} catch (Exception e) {
	            dd = "";
		}
		
		return dd;    
    }
    
    public static String getCurrentTimeStr(Date date){
    	String currentTimeText = "";
    	if(date!=null)
    		currentTimeText = new SimpleDateFormat(HOUR_SECOND_FORMAT2).format(date);
        
    	return currentTimeText;
    }
    
    public static String getStrDateHourSecond(Date d){
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_HOUR_SECOND_FORMAT2,SYS_LOCALE);
        String dd = "";
		try {
			if(d!=null)
	            dd = dateFormat.format(d);
		} catch (Exception e) {
	            dd = "";
		}
		
		return dd;    
    }
    
    public static String getStrDateHour(Date d){
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_HOUR_SECOND_FORMAT3,SYS_LOCALE);
        String dd = "";
		try {
			if(d!=null)
	            dd = dateFormat.format(d);
		} catch (Exception e) {
	            dd = "";
		}
		
		return dd;    
    }
	
    public static String getStrLongDateHourSecond(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_HOUR_SECOND_FORMAT,SYS_LOCALE);
		String dd = "";
		try {
			if(d!=null)
	            dd = dateFormat.format(d);
		} catch (Exception e) {
            dd = "";	
        }
		
        return dd;	
    }
	
    public static Date getStrLongDateHourSecondToDate(String d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_HOUR_SECOND_FORMAT,SYS_LOCALE);
        String dd = "";
		try {
			if(d!=null)
	            dd = dateFormat.format(d);
		} catch (Exception e) {
	            dd = "";
		}
		
		return new Date(dd);
    }

    public static String getStrLongDateHourSecondThai(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_HOUR_SECOND_FORMAT,SYS_LOCALE_THAI);
        String dd = "";
        try {
        	if(d!=null)
        		dd = dateFormat.format(d);
        } catch (Exception e) {
            dd = "";
        }
        
        return dd;	
    }

    public static Date getFormatDate(String dateStr) {
		Date date = null;
	        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT);
		try {
			if(dateStr!=null)
	            date = dateFormat.parse(dateStr);
		} catch (Exception e) {
	            date = null;
		}
		
		return date;
    }
	
    public static Date getFormatDateYYYYMMDD(String dateStr) {	
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT_REPORT,SYS_LOCALE);
        try {
        	if(dateStr!=null)
        		date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            date = null;
        }
        
        return date;
    }
	
    public static Date getFormatLongDate(String dateStr) {
    	Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_HOUR_SECOND_FORMAT,SYS_LOCALE);
		try {
			if(dateStr!=null)
				date = dateFormat.parse(dateStr);
		} catch (Exception e) {
			date = null;
		}
		
		return date;
    }
	
    public static Date getFormatDate(String date, String format) {
		
        Date rdate = null;
        try {
        	if(date!=null){
        		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            	rdate = dateFormat.parse(date);
        	}
        } catch (ParseException e) {
        }
        
        return rdate;
    }

    /**
     * Get current year as String with default locale.
     *
     * @return Current year.
     */
	
    public static String getCurrentYear() {
        return getCurrentYear(SYS_LOCALE);	
    }

    
    public static Date getCurrentYearTimes() {
        return getFormatDateTime_yyyyMMdd(getCurrentYearTime(SYS_LOCALE));
    }
        
    /**
     * Get current year as String with given locale.
     *
     * @param locale
     *            Locale used to format year.
     * @return Current year.
     */

        
    public static String getCurrentYear(Locale locale) {
        DateFormat format = new SimpleDateFormat(YEAR_FORMAT, locale);
        return format.format(new Date());	
    }
        
    public static String getCurrentYearTime(Locale locale) {
    	DateFormat format = new SimpleDateFormat(FORMAT_YYYYMMDD, locale);
        return format.format(new Date());	
    }

    public static String getMonthByDate(Date dd) {
    	DateFormat format = new SimpleDateFormat(MONTH_FORMAT, SYS_LOCALE);
    	return format.format(dd);
    }

    public static String getYearMonth() {
        DateFormat format = new SimpleDateFormat(YEAR_MONTH_FORMAT, SYS_LOCALE);
        return format.format(new Date());	
    }


    public static String getMonth() {
        DateFormat format = new SimpleDateFormat(MONTH_FORMAT, SYS_LOCALE);
        return format.format(new Date());	
    }

    public static Date getDateByYearMonth(String YearMonth) {
        Date d = new Date();
        DateFormat format = new SimpleDateFormat(YEAR_MONTH_FORMAT, SYS_LOCALE);
        try {
            d = format.parse(YearMonth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;	
    }

    public static String getYearByDate(Date d) {		
        return getYearByDate(d, SYS_LOCALE);
    }

    public static String getYearByDate(Date d, Locale locale) {
    	DateFormat format = new SimpleDateFormat(YEAR_FORMAT, locale);
		return format.format(d);
    }

    public static Date getSystemLocaleDate(Date d) {
    	return Calendar.getInstance(SYS_LOCALE).getTime();
    }

    public static SimpleDateFormat getLocaleSimpleDateFormat() {
    	return new SimpleDateFormat(LONG_DATE_FORMAT, SYS_LOCALE);
    }

    public static int diffDate(Date startDate, Date endDate) {
    	int difInDays = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60L * 24));

		return difInDays;
    }

    public static String plusByDay(Date startDate, int day) {
    	Date d = addDate( startDate, day);
		return getStrDate(d);
    }

    public static Date addDate(Date startDate, int day) {
        Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.DATE, day);
	
		return c.getTime();
    }
    
    public static Date plusByMinute(Date startDate, int minute) {
        Calendar c = Calendar.getInstance();
		c.setTime(startDate);
		c.add(Calendar.MINUTE, minute);
	
		return c.getTime();
    }

    public static int diffDateNotTime(Date startDate, Date endDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT);
		Date dst = getFormatDate(dateFormat.format(startDate));
		Date dsp = getFormatDate(dateFormat.format(endDate));
		int difInDays = (int) ((dsp.getTime() - dst.getTime()) / (1000 * 60L * 60 * 24));
		
        return difInDays;
    }

    public static int getDayInMonth(String mm, String yy) {
    	int d = 30;
    	int m = Integer.parseInt(mm);
    	int y = Integer.parseInt(yy);
    	switch (m) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:    d = 31;     break;
            case 4:
            case 6:
            case 9:
            case 11:    d = 30;     break;
            case 2:	
                if(y % 4 == 0)d = 29;
                else d = 28;	            
                break;
            default:	d = 30;     break;	
        }
        
        return d;	
    }
    
    public static Date dateToDate(Date dd) {
    	String d1 = getStrDate(dd);
    	return getFormatDate(d1);
    }

    public static String getDateStrByDate(Date dd) {
    	DateFormat format = new SimpleDateFormat(DATE_FORMAT, SYS_LOCALE);
		String dateStr = "";
		try {
			if(dd!=null)
				dateStr = format.format(dd);
        } catch (RuntimeException e) {
            e.printStackTrace();
            dateStr = "NA";
        }
		
		return dateStr;
    }
	
    public static boolean checkBetweenDate(Date startDate, Date endDate, Date varDate) {		
        boolean flag = false;
        if (startDate.compareTo(varDate) == 0 || endDate.compareTo(varDate) == 0) {
            flag = true;
        }
        if (flag == false) {
            if (startDate.compareTo(varDate) == -1 && endDate.compareTo(varDate) == 1) {
            	flag = true;
            }
        }
        
        return flag;
    }

    public static Integer getYearInteger(String yearCK) {
        Integer year = null;
        if (yearCK != null && !yearCK.equalsIgnoreCase("")) {
            year = new Integer(yearCK);
        } else {
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat(YEAR_FORMAT, SYS_LOCALE);
            year = new Integer(dateFormat.format(calendar.getTime()));
        }
        
        return year;
    }

    public static Integer getIntegerYearByDate(Date date) {
        Integer year = null;
        if (date == null ) {
            year = new Integer(0);
        } else {
            DateFormat dateFormat = new SimpleDateFormat(YEAR_FORMAT, SYS_LOCALE);
            year = new Integer(dateFormat.format(date));
        }

        return year;
    }

    public static Integer getThisYearInteger() {
        return getYearInteger(null);
    }

    public static String converse2YearLeadDateText(String dateLeadStr) {
        // Supot edit 2005-10-21
    	String returnDate = null;
    	DateFormat formatter1 = new SimpleDateFormat(LONG_DATE_FORMAT, SYS_LOCALE);
    	DateFormat formatter2 = new SimpleDateFormat(LONG_DATE_FORMAT_REPORT, SYS_LOCALE);
        
    	if(dateLeadStr != null && !dateLeadStr.equals("")){
		    try{
		        Date dateParse = formatter1.parse(dateLeadStr);
		        returnDate = formatter2.format(dateParse);
		    }catch(ParseException e){
		        e.printStackTrace();
		    }
    	}

        return returnDate;	
    }

    //FUNCTION CONVERSE FROM FORMAT "yyyy-MM-dd" TO FORMAT "dd-MM-yyyy"
    public static String converse2DateLeadDateText(String dateLeadStr) {
        String returnDate = null;
        DateFormat formatter1 = new SimpleDateFormat(LONG_DATE_FORMAT_REPORT, SYS_LOCALE);
        DateFormat formatter2 = new SimpleDateFormat(LONG_DATE_FORMAT, SYS_LOCALE);        
        if(dateLeadStr != null && !dateLeadStr.equals("")){
            try{
                Date dateParse = formatter1.parse(dateLeadStr);
                returnDate = formatter2.format(dateParse);
            }catch(ParseException e){
                e.printStackTrace();
            }
        }
        
        return returnDate;
    }

    //FUNCTION CHANGE FORMAT TO FORMAT "yyyy-MM-dd"
    public static Date getFormatDateForSearch(String date) {
        Date rdate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT_REPORT,SYS_LOCALE);
        try {
        	if(date!=null)
        		rdate = dateFormat.parse(date);
        } catch (ParseException e) {
            rdate = null;
        }
		
        return rdate;
    }

    //FUNCTION CHANGE FORMAT TO FORMAT "dd-MMM-yyyy"	
    public static String getFormatShortDateReport(Date date) {
        DateFormat format = new SimpleDateFormat(SHORT_DATE_FORMAT_REPORT, SYS_LOCALE);
        String dateStr = "";
        try {
        	if(date!=null)
        		dateStr = format.format(date);
        } catch (RuntimeException e) {
            e.printStackTrace();
            dateStr = "";
        }
		
        return dateStr;
    }

    //FUNCTION CHANGE FORMAT TO FORMAT "yyyyMMddhhmmss"
    public static String getFormatRunningDateTime() {
    	Date date = new Date();
    	DateFormat format = new SimpleDateFormat(LONG_DATE_FORMAT_REPORT_HOUR_SECOND_FORMAT, SYS_LOCALE);
    	String dateStr = "";
    	try {
            dateStr = format.format(date);
    	} catch (RuntimeException e) {
            e.printStackTrace();
            dateStr = "";
        }
    	return dateStr;
    }

    //FUNCTION CREATE CURRENT DATE STRING
    public static String getCurrentTimeStr(){
        String currentTimeText = new SimpleDateFormat(HOUR_SECOND_FORMAT).format(new Date());
        return currentTimeText;
    }
    
    public static String getDateTimeStr_HHMMSS(Date date){
    	String currentTimeText = "00:00:00";
    	
    	if(date != null){
    		currentTimeText = new SimpleDateFormat(HOUR_SECOND_FORMAT).format(date);	
    	}
    	
        return currentTimeText;
    }
    
    public static int getIntegerMonthByDate(Date date) {
    	Integer month = null;
    	if (date == null ) {
            month = new Integer(0);
        } else {
            DateFormat dateFormat = new SimpleDateFormat(MONTH_FORMAT, SYS_LOCALE);
            month = new Integer(dateFormat.format(date));
        }
	
        return month.intValue();
    }

    public static String getMonthShortNameThaiByMounthNo(int monthNo) {	
        int mountNumber = monthNo - 1;
        String monthShortNameThai = "";
        
        if(Calendar.JANUARY == mountNumber){
            monthShortNameThai = "\u0E21.\u0E04.";

        }else if(Calendar.FEBRUARY == mountNumber){	
            monthShortNameThai = "\u0E01.\u0E1E.";
		
        }else if(Calendar.MARCH == mountNumber){		
            monthShortNameThai = "\u0E21\u0E35.\u0E04.";

        }else if(Calendar.APRIL == mountNumber){	
            monthShortNameThai = "\u0E40\u0E21.\u0E22.";

        }else if(Calendar.MAY == mountNumber){	
            monthShortNameThai = "\u0E1E.\u0E04.";

        }else if(Calendar.JUNE == mountNumber){	
            monthShortNameThai = "\u0E21\u0E34.\u0E22.";

        }else if(Calendar.JULY == mountNumber){	
            monthShortNameThai = "\u0E01.\u0E04.";

        }else if(Calendar.AUGUST == mountNumber){	
            monthShortNameThai = "\u0E2A.\u0E04.";
            
        }else if(Calendar.SEPTEMBER == mountNumber){
            monthShortNameThai = "\u0E01.\u0E22.";
	
        }else if(Calendar.OCTOBER == mountNumber){
            monthShortNameThai = "\u0E15.\u0E04.";

        }else if(Calendar.NOVEMBER == mountNumber){
            monthShortNameThai = "\u0E1E.\u0E22.";
            
        }else if(Calendar.DECEMBER == mountNumber){
            monthShortNameThai = "\u0E18.\u0E04.";
        }

        return monthShortNameThai;	
    }
	
    public static String getMonthFullNameThaiByMounthNo(int monthNo) {
        int mountNumber = monthNo - 1;
        String monthShortNameThai = "";

        if(Calendar.JANUARY == mountNumber){
            monthShortNameThai = "\u0E21\u0E01\u0E23\u0E32\u0E04\u0E21";

        }else if(Calendar.FEBRUARY == mountNumber){	
            monthShortNameThai = "\u0E01\u0E38\u0E21\u0E20\u0E32\u0E1E\u0E31\u0E19\u0E18\u0E4C";
	
        }else if(Calendar.MARCH == mountNumber){
            monthShortNameThai = "\u0E21\u0E35\u0E19\u0E32\u0E04\u0E21";
	
        }else if(Calendar.APRIL == mountNumber){
            monthShortNameThai = "\u0E40\u0E21\u0E29\u0E32\u0E22\u0E19";
	
        }else if(Calendar.MAY == mountNumber){
            monthShortNameThai = "\u0E1E\u0E24\u0E29\u0E20\u0E32\u0E04\u0E21";

        }else if(Calendar.JUNE == mountNumber){
            monthShortNameThai = "\u0E21\u0E34\u0E16\u0E38\u0E19\u0E32\u0E22\u0E19";

        }else if(Calendar.JULY == mountNumber){	
            monthShortNameThai = "\u0E01\u0E23\u0E01\u0E0E\u0E32\u0E04\u0E21";

        }else if(Calendar.AUGUST == mountNumber){
            monthShortNameThai = "\u0E2A\u0E34\u0E07\u0E2B\u0E32\u0E04\u0E21";

        }else if(Calendar.SEPTEMBER == mountNumber){
            monthShortNameThai = "\u0E01\u0E31\u0E19\u0E22\u0E32\u0E22\u0E19";

        }else if(Calendar.OCTOBER == mountNumber){
            monthShortNameThai = "\u0E15\u0E38\u0E25\u0E32\u0E04\u0E21";

        }else if(Calendar.NOVEMBER == mountNumber){
            monthShortNameThai = "\u0E1E\u0E24\u0E28\u0E08\u0E34\u0E01\u0E32\u0E22\u0E19";

        }else if(Calendar.DECEMBER == mountNumber){	
            monthShortNameThai = "\u0E18\u0E31\u0E19\u0E27\u0E32\u0E04\u0E21";
        }

        return monthShortNameThai;
    }
    
    public static String getMonthFullNameByMounthNo(int monthNo) {
        int mountNumber = monthNo - 1;
        String monthShortNameThai = "";

        if(Calendar.JANUARY == mountNumber){
            monthShortNameThai = "January";

        }else if(Calendar.FEBRUARY == mountNumber){	
            monthShortNameThai = "February";
	
        }else if(Calendar.MARCH == mountNumber){
        	monthShortNameThai = "March";
	
        }else if(Calendar.APRIL == mountNumber){
        	monthShortNameThai = "April";
	
        }else if(Calendar.MAY == mountNumber){
        	monthShortNameThai = "May";

        }else if(Calendar.JUNE == mountNumber){
        	monthShortNameThai = "June";

        }else if(Calendar.JULY == mountNumber){	
        	monthShortNameThai = "July";

        }else if(Calendar.AUGUST == mountNumber){
        	monthShortNameThai = "August";

        }else if(Calendar.SEPTEMBER == mountNumber){
        	monthShortNameThai = "September";

        }else if(Calendar.OCTOBER == mountNumber){
        	monthShortNameThai = "October";

        }else if(Calendar.NOVEMBER == mountNumber){
        	monthShortNameThai = "November";

        }else if(Calendar.DECEMBER == mountNumber){	
        	monthShortNameThai = "December";
        }

        return monthShortNameThai;
    }

    // yyyyMMdd
    public static String getFormatDate_yyyyMMdd(Date date) {
        return getFormatDateBy(date, YEAR_MONTH_DAY_FORMAT);
    }


    // yyyyMM
    public static String getFormatDate_yyyyMM(Date date) {
        return getFormatDateBy(date, YEAR_MONTH_FORMAT);
    }

    // yyyyMMWW
    public static String getFormatDate_yyyyMMWW(Date date) {
        return getFormatDateBy(date, YEAR_MONTH_WEEK_FORMAT);
	
    }
	
    // yyyy
    public static String getFormatDate_yyyy(Date date) {
        return getFormatDateBy(date, YEAR_FORMAT);
    }


    // dd-mm-yyyy
    public static String getFormatDate_dd_mm_yyyy(Date date) {
        return getFormatDateBy(date, LONG_DATE_FORMAT);
    }

    public static String formatDateddmmyyyy(Date date) {
        return getFormatDateBy(date, LONG_DATE_FORMAT_REPORT_2);
    }
    
    public static String formatDateNewddmmyyyy(Date date) {
        return getFormatDateBy(date, SHORT_DATE_FORMAT_REPORT);
    }

    public static String getFormatDateBy(Date date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format,SYS_LOCALE);
        String d = null;
	
        try {
        	if(date!=null)
        		d = dateFormat.format(date);
	
        } catch (Exception e) {
            e.printStackTrace();
        }
	
        return d;
    }

    public static String getStrDateTime(java.util.Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss",Locale.US);
        String dd = "";
	
        try {
            dd = dateFormat.format(date);
	
        } catch (Exception e) {
            dd = "";
        }
	
        return dd;
    }

    //CONVERT STRING FORMAT dd-mm-yyyy hh:mm:ss(US_LOCAL) TO DATE(US_LOCAL)
    public static Date getFormatDateTime(String dateStr) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss",SYS_LOCALE);

        try {
            date = dateFormat.parse(dateStr);
	
        } catch (Exception e) {
            //e.printStackTrace();
            date = null;
        }
	
        return date;
	
    }

    //CONVERT STRING FORMAT dd-mm-yyyy(US_LOCAL) TO DATE(US_LOCAL)
    public static Date getFormatDate_yyyyMMdd(String dateStr) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(YEAR_MONTH_DAY_FORMAT,SYS_LOCALE);

        try {
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            //e.printStackTrace();
            date = null;
        }
        return date;
    }

    public static Date getFormatDateTime_yyyyMMdd(String dateStr) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",SYS_LOCALE);
	
        try {
            date = dateFormat.parse(dateStr);
	
        } catch (Exception e) {
            //e.printStackTrace();
            date = null;
        }
	
        return date;
    }
	
    public static String getFormatTime_HH_mm(Date date) {
        String str = "";
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
       
        if(null != date){
            str = dateFormat.format(date);
        }
        
        return str;
    }

    public static Date getFormatDateTEST(String dateStr) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-dd-mm",SYS_LOCALE);
        try {
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            //e.printStackTrace();
            date = null;
        }
	
        return date;
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static  String getStrDateTimeStamp(Date d) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss.SSSSSS",SYS_LOCALE);
        String dd = dateFormat.format(new Date());
        try {
            dd = dateFormat.format(d);
        } catch (Exception e) {
            dd = "";
        }
        return dd;
    }
    
    public static String getCurrentDateLocalThai(){
        String currentDateThai = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",SYS_LOCALE_THAI);
        Date date = new Date();
        currentDateThai = dateFormat.format(date);
	
        return currentDateThai;
    }

    public static String getDateLocalThai(){
        String dayMonthYear = "";
        DateFormat dateFormat2 = new SimpleDateFormat("MM/dd",SYS_LOCALE_THAI);
        Date date2 = new Date();
        dayMonthYear = dateFormat2.format(date2);
	
        return dayMonthYear;
    }

    public static String getYearLocalThai(){
        String year = "";
        int yearCur = 0;
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy",SYS_LOCALE_THAI);
        Date date2 = new Date();
        year = dateFormat2.format(date2);
        yearCur = (Integer.parseInt(year)-543);
        year = StringType.getString(yearCur);

        return year;
    }
	
    public static Date getFormatDateLocalThai(String dateStr) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT_LOCALTHAI);
        try {
            if(!"".equals(dateStr))
                date = dateFormat.parse(dateStr);
		
        } catch (Exception e) {
            //e.printStackTrace();
            date = null;
        }
        
        return date;
    }

    public static Date getFormatLongDateYYYYMMDD(String dateStr) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(LONG_DATE_FORMAT_REPORT_2);
        try {
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            //e.printStackTrace();
            date = null;
        }
        
        return date;
    }

    public static Date getDateBeforeByMonth(int month){
    	Date current = new Date();  
    	try{
    		Calendar cal = Calendar.getInstance();  
    		cal.setTime(current);  
    		cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)-month));  
    		current = cal.getTime(); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return current;
    }

    public static Date getDateBeforeByDate(int date){
    	Date current = new Date();  
    	try{
    		Calendar cal = Calendar.getInstance();  
    		cal.setTime(current);  
    		cal.set(Calendar.DATE, (cal.get(Calendar.DATE)-date));  
    		current = cal.getTime(); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return current;
    }
    
    public static Date getDateAfterByMonth(int month){
    	Date current = new Date();  
    	try{
    		Calendar cal = Calendar.getInstance();  
    		cal.setTime(current);  
    		cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH)+month));  
    		current = cal.getTime(); 
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return current;
    }
     
    public static Date getFormatDate_ddMMyyyy(String dateStr) {
        Date d = null;
        DateFormat format = new SimpleDateFormat(LONG_DATE_FORMAT, SYS_LOCALE);
        try{
            if(!"".equals(dateStr))
                d = format.parse(dateStr);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        
        return d;
    }
        
    public static String convertFormatDateToThai(String dateStr){ 
        String result = "";
        if(dateStr!=null && !dateStr.equals("")){
            String[] ddMMyyyy = dateStr.split("/");
            String dd = ddMMyyyy[0];
            String MM = ddMMyyyy[1];
            int yyyy = Integer.parseInt(ddMMyyyy[2])+543;
            result = dd+'/'+MM+'/'+yyyy;            
        }
        
        return result;        
    }

    public static Date convertFormatDateToDDMMYYYY(String dateStr){  
        Date d = null; 
        try{
            if(dateStr!=null && !dateStr.equals("")){
                String[] ddMMyyyy = dateStr.split("/");
                String dd = ddMMyyyy[0];
                String MM = ddMMyyyy[1];
                int yyyy = Integer.parseInt(ddMMyyyy[2])-543;                    
                String convertDate = dd+'/'+MM+'/'+yyyy;
                DateFormat format = new SimpleDateFormat(LONG_DATE_FORMAT, SYS_LOCALE);
                if(!"".equals(dateStr))
                    d = format.parse(convertDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return d;
    }
        
    public static String convertDateYYYYMMDD(String dateStr){
        if(!dateStr.equals("")){
            String[] orderDateDDMMYYYY = dateStr.split("/");
            dateStr = orderDateDDMMYYYY[2]+'-'+orderDateDDMMYYYY[1]+'-'+orderDateDDMMYYYY[0];
            
        }
        
        return dateStr;
    }
       
    public static String convertDateYYYYMMDD_Th(String dateStr){
        if(!dateStr.equals("")){
            String[] orderDateDDMMYYYY = dateStr.split("/");
            int orderDateYYYY = Integer.parseInt(orderDateDDMMYYYY[2]);
            if(orderDateYYYY-543<2000){
                dateStr = orderDateDDMMYYYY[0]+'/'+orderDateDDMMYYYY[1]+'/'+orderDateYYYY;
            }else{
                orderDateYYYY = orderDateYYYY-543;
                dateStr = orderDateDDMMYYYY[0]+'/'+orderDateDDMMYYYY[1]+'/'+orderDateYYYY;
            }
        }
        
        return dateStr;        
    }    
        
    public static String getDateFrom(String dateStr){
        if(!dateStr.equals("")){            
            String[] orderDateDDMMYYYY = dateStr.split("/");
            int orderDateYYYY = Integer.parseInt(orderDateDDMMYYYY[2]);            
            if(orderDateYYYY<2500){
                orderDateYYYY = orderDateYYYY+543;
                dateStr = orderDateDDMMYYYY[0]+'/'+orderDateDDMMYYYY[1]+'/'+String.valueOf(orderDateYYYY);
            }else{              
                dateStr = orderDateDDMMYYYY[0]+'/'+orderDateDDMMYYYY[1]+'/'+String.valueOf(orderDateYYYY);          
            }            
        }
            
        return dateStr;    
    }
    
    public static Date convertHHmmToDate(String hhmmStr){
    	Date date = null;
    	if(!hhmmStr.equals("")){   
    	    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm",SYS_LOCALE);
    	
            try {
                date = dateFormat.parse(hhmmStr);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                date = simpleDateFormat.parse(hhmmStr);
        		
            } catch (Exception e) {
                date = null;
            }
    	}
    	
        return date;
    }
    
    public static Date convertHHmmssToDate(String hhmmStr){
    	Date date = null;
    	if(!hhmmStr.equals("")){   
    	    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss",SYS_LOCALE);
    	
            try {
                date = dateFormat.parse(hhmmStr);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                date = simpleDateFormat.parse(hhmmStr);
        		
            } catch (Exception e) {
                date = null;
            }
    	}
    	
        return date;
    }
    
}
