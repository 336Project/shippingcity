package com.ateam.shippingcity.model;

import java.io.Serializable;
import java.util.List;

public class MyQuoteToConfirmDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private static mobile' => string '13799005682' //电话
	private String shipping_type;
	private String shipment_type;
	private String initiation;//起始港
	private String destination;//目的港
	private String startime;  //走货开始日期
	private String endtime; //走货结束日期
	private String deadlinetime; //报价截止日期
	private List<String> type; //箱型
	private List<String> num; //数量
	private String packages; //件数
	private String weight; //毛重
	private String volume; //体积
	private String size; //单件尺寸
	private String remarks; //备注
	private String mobile; //电话
	private String status;
	private String buyer;
	private String picture_path;
	private MyData mydata;
	
	public String getInitiation() {
		return initiation;
	}
	public void setInitiation(String initiation) {
		this.initiation = initiation;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getShipping_type() {
		return shipping_type;
	}
	public void setShipping_type(String shipping_type) {
		this.shipping_type = shipping_type;
	}
	public String getShipment_type() {
		return shipment_type;
	}
	public void setShipment_type(String shipment_type) {
		this.shipment_type = shipment_type;
	}
	public String getStartime() {
		return startime;
	}
	public void setStartime(String startime) {
		this.startime = startime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getDeadlinetime() {
		return deadlinetime;
	}
	public void setDeadlinetime(String deadlinetime) {
		this.deadlinetime = deadlinetime;
	}
	public List<String> getType() {
		return type;
	}
	public void setType(List<String> type) {
		this.type = type;
	}
	public List<String> getNum() {
		return num;
	}
	public void setNum(List<String> num) {
		this.num = num;
	}
	public String getPackages() {
		return packages;
	}
	public void setPackages(String packages) {
		this.packages = packages;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public MyData getMydata() {
		return mydata;
	}
	public void setMydata(MyData mydata) {
		this.mydata = mydata;
	}
	public String getPicture_path() {
		return picture_path;
	}
	public void setPicture_path(String picture_path) {
		this.picture_path = picture_path;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
