package com.ateam.shippingcity.model;

import java.io.Serializable;

public class Respond<T> extends HBaseObject implements Serializable{
	private static final long serialVersionUID = 3321056538834150075L;
	private T datas;

	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}
}
