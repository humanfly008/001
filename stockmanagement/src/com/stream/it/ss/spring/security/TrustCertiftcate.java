package com.stream.it.ss.spring.security;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;


public class TrustCertiftcate {
	private static final Logger log = Logger.getLogger(TrustCertiftcate.class);

	

//    public static void trustCertificate() {
//    	log.info("trustCertificate...");
//    	
//        try{
//        	System.setProperty("javax.net.ssl.trustStore", 			systemProperties.getKeystorePath()+systemProperties.getKeystoreTrustStore());
//    		System.setProperty("javax.net.ssl.trustStorePassword", 	systemProperties.getKeystoreTrustStorePassword());
//
//        } catch (Exception ex) {
//        	log.error(ex);
//        }        
//    }
	
    public void trustCertificate(String serviceURL) throws Exception {
    	log.info("trustCertificate.............");
        
		System.setProperty("javax.net.ssl.trustStore", 			"/H2H_CONFIG/keystore/h2h_webcustom");
		System.setProperty("javax.net.ssl.trustStorePassword", 	"h2h_webcustom");
		System.setProperty("javax.net.ssl.keyStore",			"/H2H_CONFIG/keystore/h2h_webcustom");
		System.setProperty("javax.net.ssl.keyStorePassword",	"h2h_webcustom");
		System.setProperty("javax.net.ssl.keyStoreType", 		"JKS");
        
        try {
        	URL url = new URL(serviceURL);            
        	HttpURLConnection httpConnection = (HttpsURLConnection) url.openConnection();

        	// Normally, instanceof would also be used to check the type.
        	
        	setAcceptAllVerifier((HttpsURLConnection)httpConnection);
        	

        } catch (IOException ex) {
        	System.out.println(ex);
        }        
    }
    
    private static SSLSocketFactory sslSocketFactory = null;
    
    protected static void setAcceptAllVerifier(HttpsURLConnection connection) throws NoSuchAlgorithmException, KeyManagementException {

        // Create the socket factory.
        // Reusing the same socket factory allows sockets to be
        // reused, supporting persistent connections.
        if( null == sslSocketFactory) {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, ALL_TRUSTING_TRUST_MANAGER, new java.security.SecureRandom());
            sslSocketFactory = sc.getSocketFactory();
        }

        connection.setSSLSocketFactory(sslSocketFactory);

        // Since we may be using a cert with a different name, we need to ignore
        // the hostname as well.
        connection.setHostnameVerifier(ALL_TRUSTING_HOSTNAME_VERIFIER);
    }
    
    private static final TrustManager[] ALL_TRUSTING_TRUST_MANAGER = new TrustManager[] {
        new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        }
    };
    
    private static final HostnameVerifier ALL_TRUSTING_HOSTNAME_VERIFIER  = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}
