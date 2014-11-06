package com.stream.it.ss.hibernate.inquiry;

import java.io.Serializable;


public class Dropdown implements Serializable{
	private String label;
    private String value;

    public Dropdown(){}
    
    public Dropdown(String label, String value){
    	this.label = label;
    	this.value = value;
    }
    
    public String getLabel() {
        return label;
    }

    public String getValue() {
        return value;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
