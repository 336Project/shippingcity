package com.ateam.shippingcity.model;

import java.io.Serializable;
/**
 * 待确认报价对象
 * @author Administrator
 * 
 */
public class MyQuoteToConfirm implements Serializable{

	private static final long serialVersionUID = 1L;
	private String placeBegin;//开始地
	private String placeEnd;//终点
	private String transportType;//运输类型
	private String transportTimeBegin;//运输开始时间
	private String transportTimeEnd;//运输结束时间
	private String palletDescribe;//货盘描述
	private String boxType;//装箱状态
	private long myQuoteToConfirm; //待报价对象的Id，用于获取详细信息
	
	public String getPlaceBegin() {
		return placeBegin;
	}
	public void setPlaceBegin(String placeBegin) {
		this.placeBegin = placeBegin;
	}
	public String getPlaceEnd() {
		return placeEnd;
	}
	public void setPlaceEnd(String placeEnd) {
		this.placeEnd = placeEnd;
	}
	public String getTransportType() {
		return transportType;
	}
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}
	public String getTransportTimeBegin() {
		return transportTimeBegin;
	}
	public void setTransportTimeBegin(String transportTimeBegin) {
		this.transportTimeBegin = transportTimeBegin;
	}
	public String getTransportTimeEnd() {
		return transportTimeEnd;
	}
	public void setTransportTimeEnd(String transportTimeEnd) {
		this.transportTimeEnd = transportTimeEnd;
	}
	public String getPalletDescribe() {
		return palletDescribe;
	}
	public void setPalletDescribe(String palletDescribe) {
		this.palletDescribe = palletDescribe;
	}
	public String getBoxType() {
		return boxType;
	}
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	public long getMyQuoteToConfirm() {
		return myQuoteToConfirm;
	}
	public void setMyQuoteToConfirm(long myQuoteToConfirm) {
		this.myQuoteToConfirm = myQuoteToConfirm;
	}
}
