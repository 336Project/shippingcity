package com.ateam.shippingcity.model;

import java.io.Serializable;

public class MyQuoteToConfirmDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private static mobile' => string '13799005682' //电话
	private String initiation;//起始港
	public String getInitiation() {
		return initiation;
	}
	public void setInitiation(String initiation) {
		this.initiation = initiation;
	}
}
