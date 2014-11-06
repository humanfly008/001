package com.stream.it.ss.view.jsf.base;

import javax.faces.FacesException;
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@SuppressWarnings("serial")
public class SessionPhaseListener implements PhaseListener{
	private static final String homepage = "view-expire.xhtml";

//	public AuthenticationServiceStub  stub;
//	String token;
//	String sessionId;
//	String ip;
//	String appId = "H2H";
//	
    @Override
    public void afterPhase(PhaseEvent event) {
        //Do anything
    }

    @Override
    public void beforePhase(PhaseEvent event) {
     
    	//============================        UCA      =================================// 
//    	try {
//			stub = new AuthenticationServiceStub();
//			System.out.println("UCAAAAAAAAAAAAAAAAAAAAAA");
//			
//			FacesContext facesContext = FacesContext.getCurrentInstance(); 
//			HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
//		
//			
//			HttpSession session = request.getSession();
//		    if(session.getAttribute("token") == null){
//		    	token = request.getParameter("token");
//		    	session.setAttribute("token",token);
//			}
//		    else{
//		    	
//		    	token = (String) session.getAttribute("token");
//		    }
//		    
//		    sessionId = request.getSession().getId();
//			
//			token = request.getParameter("token");
//			System.out.println("token ============:" + token );
//			
//			System.out.println("=="+session.getAttribute("ActiveSession"));
//			
//			try {
//				if(CheckLoggedOn())
//				{
//				
//						if(session.getAttribute("ActiveSession") == null){
//							session.setAttribute("ActiveSession","true");
//							System.out.println("xx"+session.getAttribute("ActiveSession"));
//						}
//				}
//
//				else{
//					try {
//						System.out.println("a");
//						FacesContext.getCurrentInstance().getExternalContext().redirect("https://10.10.119.109/UCA_UAT/login.aspx");
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					return;
//					
//					}
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//					e.printStackTrace();
//			}
//			
//		} catch (AxisFault e) {
//						
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//
//		}
    	
    	
    	
    	
    	//============================        UCA      =================================// 
    	
    	
//        FacesContext context = event.getFacesContext();
//        ExternalContext ext = context.getExternalContext();
//        HttpSession session = (HttpSession) ext.getSession(false);
//        boolean newSession = (session == null) || (session.isNew());
//        boolean postback = !ext.getRequestParameterMap().isEmpty();
//        boolean timedout = postback && newSession;
//        if (timedout) {
//            Application app = context.getApplication();
//            ViewHandler viewHandler = app.getViewHandler();
//            UIViewRoot view = viewHandler.createView(context, "/" + homepage);
//            context.setViewRoot(view);
//            context.renderResponse();
//            try {
//            	FacesContext facesContext = event.getFacesContext();
//            	HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
//            	response.addHeader("X-UA-Compatible", "IE=8");
//
//            	
//                viewHandler.renderView(context, view);
//                context.responseComplete();
//            } catch (Throwable t) {
//                throw new FacesException("Session timed out", t);
//            }
//        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
    
//public boolean  CheckLoggedOn() throws RemoteException {
//		
//		IsUserLogon  isUserLogon =  new IsUserLogon(); 
//		isUserLogon.setIpAddress(ip);
//		isUserLogon.setAppId(appId);
//		isUserLogon.setSessionId(sessionId);
//		isUserLogon.setToken(token);
//	
//		IsUserLogonResponse isUserLogonResponse = new IsUserLogonResponse();
//		
//		isUserLogonResponse = stub.isUserLogon(isUserLogon);	
//		return isUserLogonResponse.getIsUserLogonResult();
//		
//	}

}
