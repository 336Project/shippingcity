package com.ateam.shippingcity.model;

import java.io.Serializable;
import java.util.List;

public class MyData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String remarks; //备注
	private String createtime; //报价时间
	private List<String> price; //价格
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public List<String> getPrice() {
		return price;
	}

	public void setPrice(List<String> price) {
		this.price = price;
	}
}
