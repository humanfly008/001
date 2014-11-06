package com.nrautoseat.servlet;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExportReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExportReportServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();	
    }
    
    private String[] monthThDesc = {"","มกราคม","กุมภาพันธ์","มีนาคม","เมษายน","พฤษภาคม","มิถุนายน","กฏกฎาคม","สิงหาคม","กันยายน","ตุลาคม","พฤจิยายน","ธันวาคม"};
    
	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {        
		try {
			System.out.println("PRINT REPORT SERVLET BEGIN....");
			String param = request.getParameter("param");
			
			String userId 	= param.split(",")[0];
			String id 		= param.split(",")[1];
			String month 	= param.split(",")[2];
			String year 	= param.split(",")[3];

//			System.out.println("USER ID : "+userId);
//			System.out.println("MONTH   : "+month);
//			System.out.println("YEAR    : "+year);

			ReportExporter reportExporter = new ReportExporter(request, response);
			Hashtable<String, String> reportParam = new Hashtable<String, String>();
	        String reportName = "/report/stock_salary_user_report";
            
	        reportParam.put("sql", 		"AND USER_ID = '"+userId+"' AND ID="+id);
	        reportParam.put("month", 		month);
	        reportParam.put("year", 		year);
	        reportParam.put("month_th", 	monthThDesc[Integer.parseInt(month)]);

	        int yearTH = Integer.parseInt(year)+543;
	        
	        reportParam.put("year_th", 	yearTH+"");
        
			reportExporter.exportPDF(reportName, DBUtil.getConnectionJDBC(), reportParam);

			System.out.println("PRINT REPORT SERVLET END....");

		} catch (Exception e) {
			e.printStackTrace();
		}				
	}
	
	@Override
	 
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {    
	    doGet(request, response);	
	}
	
}
