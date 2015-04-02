package com.ateam.shippingcity.model;

import java.io.Serializable;

/**
 * 海运对象
 * @version 
 * @create_date 2015-3-28上午9:52:53
 */
public class PalletTransport extends HBaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String remainTime;//剩余时间
	private String boxType;//装箱状态
	private String placeBegin;//开始地
	private String placeEnd;//终点
	private String transportType;//运输类型
	private String transportTimeBegin;//运输开始时间
	private String transportTimeEnd;//运输结束时间
	private String palletDescribe;//货盘描述
	public String getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(String remainTime) {
		this.remainTime = remainTime;
	}
	public String getBoxType() {
		return boxType;
	}
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
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
	
}
