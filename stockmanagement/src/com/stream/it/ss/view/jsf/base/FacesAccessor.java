/*
 * Copyright 2011 PrimeFaces Extensions.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Id$
 */

package com.stream.it.ss.view.jsf.base;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.MethodExpressionActionListener;

/**
 * Accessor for objects stored in several scopes via faces context {@link javax.faces.context.FacesContext}.
 *
 * @author  ova / last modified by $Author$
 * @version $Revision$
 */
public class FacesAccessor {

	public static Object getManagedBean(final String beanName) {
		FacesContext fc = FacesContext.getCurrentInstance();

		Object bean;
		try {
			ELContext elContext = fc.getELContext();
			bean = elContext.getELResolver().getValue(elContext, null, beanName);
		} catch (RuntimeException e) {
			throw new FacesException(e.getMessage(), e);
		}

		if (bean == null) {
			throw new FacesException("Managed bean with name '" + beanName
			                         + "' was not found. Check your faces-config.xml or @ManagedBean annotation.");
		}

		return bean;
	}

	public static String getRequestParameter(String name) {
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}
	
		public static MethodExpression createMethodExpression(String valueExpression,
	            Class<?> expectedReturnType,
	            Class<?>[] expectedParamTypes) {
	MethodExpression methodExpression = null;
	try {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExpressionFactory factory = fc.getApplication().getExpressionFactory();
	methodExpression = factory.
	createMethodExpression(fc.getELContext(), valueExpression, expectedReturnType, expectedParamTypes);
	} catch (Exception e) {
	throw new FacesException("Method expression '" + valueExpression + "' could not be created.");
	}
	
	return methodExpression;
	}

		public static MethodExpressionActionListener createMethodActionListener(String valueExpression,
		                              Class<?> expectedReturnType,
		                              Class<?>[] expectedParamTypes) {
		MethodExpressionActionListener actionListener = null;
		try {
		actionListener = new MethodExpressionActionListener(createMethodExpression(
		valueExpression, expectedReturnType, expectedParamTypes));
		} catch (Exception e) {
		throw new FacesException("Method expression for ActionListener '" + valueExpression
		+ "' could not be created.");
		}
		
		return actionListener;
		}

}
