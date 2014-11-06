package com.nrautoseat.servlet;

import java.io.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRTextExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;


public class ReportExporter {
	private HttpServletRequest request;
    private HttpServletResponse response;

    public ReportExporter(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    private String getFullPath(String reportName, String extension)throws Exception{
        String path = request.getSession().getServletContext().getRealPath(reportName+extension);
        if(path==null){
            java.net.URL url = request.getSession().getServletContext().getResource(reportName+extension);
            path = url.getPath();
        }
        return path;
    }

    public void exportPDF(String reportName, Connection conn, Map jasperparameters)throws IOException,Exception{

        JRExporter export = new JRPdfExporter();
        String filepath = getFullPath(reportName, ".jasper");        
        JasperPrint jasperPrint;
        try{
        	jasperPrint = JasperFillManager.fillReport(filepath, jasperparameters, conn);
            ServletOutputStream sosStream = null;

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            export.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        
            response.setHeader("Content-type","application/pdf");
            response.setHeader("Content-disposition","attachment; filename="+reportName+"_"+sdf.format(c.getTime())+".pdf");

            sosStream = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            export.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            export.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
            export.exportReport();
            response.setContentLength(baos.size());
            response.setCharacterEncoding("UTF-8");
            baos.writeTo(sosStream);
            sosStream.flush();
            sosStream.close();
            response.setStatus(200);
            response.flushBuffer();

        } catch (JRException ex) {
            Logger.getLogger(ReportExporter.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception exx){
            response.sendError(500, exx.getMessage());
            exx.printStackTrace();
            return;
        }finally{
        	conn.close();
        }
    }
    
    public void exportExcel(String reportName, Connection conn, Map jasperparameters) throws IOException,Exception{
		
		JasperPrint  jasperPrint = null;
		try {
			
			String filepath = getFullPath(reportName, ".jasper");
			jasperPrint  = JasperFillManager.fillReport(filepath, jasperparameters, conn);
			
			byte[] bytes = null;
			JRXlsExporter exporter = new JRXlsExporter();
			ByteArrayOutputStream outputReport = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint );
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputReport );
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "sample.xls" );
			exporter.exportReport();
			
			Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			bytes = outputReport.toByteArray();
			response.setHeader("Content-disposition","attachment; filename="+reportName+"_"+sdf.format(c.getTime())+".xls");
			response.setContentType( "application/vnd.ms-excel" );
			response.setContentLength( bytes.length );
			
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write( bytes, 0, bytes.length );
			ouputStream.flush();
			ouputStream.close();
			
        }catch (JRException ex) {
            Logger.getLogger(ReportExporter.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception exx){
        	response.sendError(500, exx.getMessage());
            exx.printStackTrace();
            return;
        }finally{
        	conn.close();
        }
        
    }
    
    public void exportPDF(String reportName, List beanList, Map jasperparameters)throws IOException,Exception{

        JRExporter export = new JRPdfExporter();
        String filepath = getFullPath(reportName, ".jasper");        
        JasperPrint jasperPrint;
        try{
        	jasperPrint = JasperFillManager.fillReport(filepath, jasperparameters, new JRBeanCollectionDataSource(beanList));
            ServletOutputStream sosStream = null;

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            export.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        
            response.setHeader("Content-type","application/pdf");
            response.setHeader("Content-disposition","attachment; filename="+reportName+"_"+sdf.format(c.getTime())+".pdf");

            sosStream = response.getOutputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            export.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
            export.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
            export.exportReport();
            response.setContentLength(baos.size());
            response.setCharacterEncoding("UTF-8");
            baos.writeTo(sosStream);
            sosStream.flush();
            sosStream.close();
            response.setStatus(200);
            response.flushBuffer();

        } catch (JRException ex) {
            Logger.getLogger(ReportExporter.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception exx){
            response.sendError(500, exx.getMessage());
            exx.printStackTrace();
            return;
        }
    }
    
    public void exportExcel(String reportName, List beanList, Map jasperparameters) throws IOException,Exception{
		
		JasperPrint  jasperPrint = null;
		try {
			
			String filepath = getFullPath(reportName, ".jasper");
			jasperPrint  = JasperFillManager.fillReport(filepath, jasperparameters, new JRBeanCollectionDataSource(beanList));
			
			byte[] bytes = null;
			JExcelApiExporter exporter = new JExcelApiExporter();
			ByteArrayOutputStream outputReport = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint );
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputReport );
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "sample.xls" );
			exporter.exportReport();
			
			Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			bytes = outputReport.toByteArray();
			response.setHeader("Content-disposition","attachment; filename="+reportName+"_"+sdf.format(c.getTime())+".xls");
			response.setContentType( "application/vnd.ms-excel" );
			response.setContentLength( bytes.length );
			
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write( bytes, 0, bytes.length );
			ouputStream.flush();
			ouputStream.close();
			
        }catch (JRException ex) {
            Logger.getLogger(ReportExporter.class.getName()).log(Level.SEVERE, null, ex);
        }catch(Exception exx){
        	response.sendError(500, exx.getMessage());
            exx.printStackTrace();
            return;
        }
                            
    }	
    
    public void exportWord(String reportName, Connection conn, Map jasperparameters) throws IOException,Exception{    
    	try {
	    	JasperPrint  jasperPrint = null;
			String filepath = getFullPath(reportName, ".jasper");
			jasperPrint  = JasperFillManager.fillReport(filepath, jasperparameters, conn);
				
			Integer pageHeight= new Integer(jasperPrint.getPageHeight());
			Integer pageWidth= new Integer(jasperPrint.getPageWidth());
			List jasperPrintList = new ArrayList();
			jasperPrintList.add(jasperPrint);
			
	    	ByteArrayOutputStream pdfReport = new ByteArrayOutputStream();
			JRRtfExporter exporter = new JRRtfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfReport);
			exporter.setParameter(JRTextExporterParameter.CHARACTER_WIDTH, Integer.valueOf("5"));
			exporter.setParameter(JRTextExporterParameter.CHARACTER_HEIGHT, Integer.valueOf("15"));
			exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, pageWidth);
			exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, pageHeight);
			
			exporter.setParameter(JRTextExporterParameter.CHARACTER_ENCODING, "UTF-8");
			exporter.exportReport();
			byte[] bytes = pdfReport.toByteArray();
			
			response.setContentType("application/msword");
			response.setContentLength( bytes.length );
			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write( bytes, 0, bytes.length );
			ouputStream.flush();
			ouputStream.close(); 
	
    	}catch (Exception e) {
			e.printStackTrace();
		}   
    }
}
