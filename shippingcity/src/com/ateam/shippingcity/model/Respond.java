package com.ateam.shippingcity.model;

import java.io.Serializable;

public class Respond<T> extends HBaseObject implements Serializable{
	private static final long serialVersionUID = 3321056538834150075L;
	private T datas;
	private boolean isSuccess;

	private int totalPages;
	private int totalCredit;//积分总额
	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}

	public boolean isSuccess() {
		if(getStatusCode().equals("200")){
			isSuccess=true;
		}else{
			isSuccess=false;
		}
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}
}
