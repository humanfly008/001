package com.stream.it.ss.spring.log;

import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class LoggingInterceptor implements MethodInterceptor{
	private final Logger log = Logger.getLogger(getClass());
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        if (log.isInfoEnabled()) {
            log.info(new Date());
            log.info("begin: "+ methodInvocation.getMethod().getDeclaringClass() + ":"+ methodInvocation.getMethod().getName());
        }

        long startTime = System.currentTimeMillis();

        try {
            Object returnValue = methodInvocation.proceed();

            return returnValue;
            
        } finally {
            if (log.isInfoEnabled()) {
                log.info("ending: "+ methodInvocation.getMethod().getDeclaringClass() + ":"+ methodInvocation.getMethod().getName());            
                log.info("invocation time: "+(System.currentTimeMillis() - startTime) + " msecs.");
            }
        }
    }
}
