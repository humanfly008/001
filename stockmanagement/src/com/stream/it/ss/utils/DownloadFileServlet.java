package com.stream.it.ss.utils;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import java.util.ResourceBundle;


public class DownloadFileServlet extends HttpServlet {
	private static Logger logger = Logger.getLogger(DownloadFileServlet.class);
    
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("system");
    private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE_JPEG 		= "image/jpeg";
    private static final String CONTENT_TYPE_GIF 		= "image/gif";
    private static final String CONTENT_TYPE_BMP 		= "image/bmp";
    private static final String CONTENT_TYPE_TIFF		= "image/tiff";
    private static final String CONTENT_TYPE_PNG 		= "image/png";
    private static final String CONTENT_TYPE_SWF 		= "application/x-shockwave-flash";
    private static final String CONTENT_TYPE_PDF 		= "application/pdf";
    private static final String ITEM_FILE_PATH;
    
    static{
        ITEM_FILE_PATH = resourceBundle.getString("pic.store.file");                       
    }

    public DownloadFileServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();	
    }
		
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {        
        try {
        	String fileName = request.getParameter("fileName");                        	
				
        	if(fileName!=null && !fileName.equals("")){
	            String filePath = ITEM_FILE_PATH+fileName;
	            logger.info("strFilePath = " + filePath);
	            
	            if(filePath != null) {
	                int dotIndex = filePath.lastIndexOf('.');
	                String contentType = null;
	                String fileExt = filePath.substring(dotIndex+1).toUpperCase();
			              		
	                if (fileExt.equals("GIF")) {
			        	contentType = CONTENT_TYPE_GIF;
					} else if (fileExt.equals("BMP")) {
					    contentType = CONTENT_TYPE_BMP;
					} else if (fileExt.equals("JPG") || fileExt.equals("JPEG")) {
					    contentType = CONTENT_TYPE_JPEG;
					} else  if (fileExt.equalsIgnoreCase("PNG")) {
					    contentType = CONTENT_TYPE_PNG;
					} else  if (fileExt.equalsIgnoreCase("PDF")) {
					    contentType = CONTENT_TYPE_PDF;
					} else  if (fileExt.equalsIgnoreCase("TIFF")) {
					    contentType = CONTENT_TYPE_TIFF;
					} else  if (fileExt.equalsIgnoreCase("SWF")) {
					    contentType = CONTENT_TYPE_SWF;
					}
							
	                response.setContentType(contentType);		
	                ServletOutputStream out = response.getOutputStream();		
	                FileInputStream in = null;
	                
	                try {		
	                    in = new FileInputStream(filePath);
	                    int buff = in.read();
	                    while (buff != -1) { // -1 as EOF
	                        out.write(buff);
	                        buff = in.read();
	                    }
	                    out.flush();
	                    
	                } catch (Exception e) {
	                    logger.error("Load Image Fail", e);
	                } finally {
	                    if(out != null) out.close();
	                    out = null;
	                    if(in != null) in.close();
	                    in = null; 		
	                }
	            }
        	}
	
        } catch(Exception exception) {	
            logger.debug("load image fail", exception);
        }
    }
	
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {    
        doGet(request, response);	
    }

}
