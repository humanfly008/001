package com.stream.it.ss.spring.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.stream.it.ss.base.databo.DataBo;
import com.stream.it.ss.base.databo.ResultBO;
import com.stream.it.ss.base.databo.SearchBean;

public class ResultBOInterceptor implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		if(methodInvocation.getArguments()!=null && methodInvocation.getArguments().length>0){
			if(methodInvocation.getArguments()[0] instanceof SearchBean){
				SearchBean searchBean = (SearchBean)methodInvocation.getArguments()[0];
				searchBean.setResultBO(new ResultBO());
			}else if(methodInvocation.getArguments()[0] instanceof DataBo){
				DataBo dataBo = (DataBo)methodInvocation.getArguments()[0];
				dataBo.setResultBO(new ResultBO());
			}
		}
        return methodInvocation.proceed();
	}

}
