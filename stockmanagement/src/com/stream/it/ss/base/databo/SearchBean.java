package com.stream.it.ss.base.databo;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class SearchBean extends DataBo{
    private String orderBy = "";
    private String orderType = "";
    private String groupBy = "";
    private SearchConditionValuesBean[] conditionValuesBean;
    private PaggingBean paggingBean = new PaggingBean();
    
    @SuppressWarnings("rawtypes")
	private List sqlParameter = new ArrayList();    
    
    private String sqlCustomCondition;
    
	public String getGroupBy() {
        return groupBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List getSqlParameter() {
    	List sqlParameterTemp = new ArrayList(sqlParameter);
    	
    	sqlParameter = new ArrayList();
    	if(conditionValuesBean!=null && conditionValuesBean.length>0){
    		for(int i=0; i<conditionValuesBean.length; i++){
    			SearchConditionValuesBean valuesBean = conditionValuesBean[i];
    			if(valuesBean!=null){
	    			Object[] value = valuesBean.getValues();
	    			if(value!=null){
		    			for(int j=0;j<value.length; j++){		    				
		    				if(value[j]!=null && value[j]!="" && !(value[j] instanceof Boolean)){
		    					sqlParameter.add(value[j].toString().toUpperCase());
		    				}
		    			}    
	    			}
    			}
    		}
    	}
    	sqlParameterTemp.addAll(sqlParameter);
    	sqlParameter = new ArrayList();
    	
    	return sqlParameterTemp;
    }

    public void setSqlParameter(@SuppressWarnings("rawtypes") List parameterSQL) {
        this.sqlParameter = parameterSQL;
    }

    public PaggingBean getPaggingBean() {
        return paggingBean;
    }

    public void setPaggingBean(PaggingBean paggingBean) {
        this.paggingBean = paggingBean;
    }

	public SearchConditionValuesBean[] getConditionValuesBean() {
		return conditionValuesBean;
	}

	public void setConditionValuesBean(
			SearchConditionValuesBean[] conditionValuesBean) {
		this.conditionValuesBean = conditionValuesBean;
	}

	public String getSqlCustomCondition() {
		return sqlCustomCondition;
	}

	public void setSqlCustomCondition(String sqlCustomCondition) {
		this.sqlCustomCondition = sqlCustomCondition;
	}
}
