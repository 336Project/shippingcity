package com.ateam.shippingcity.model;

import java.io.Serializable;
import java.util.List;
/**
 * 待确认报价对象
 * @author Administrator
 * 
 */
public class MyQuoteToConfirm implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id; //货盘id
	private String shipping_type;//1海运 2空运 3陆运
	private	String shipment_type;//1整箱 2拼箱 3散杂货
	private String userid; //发布货盘的用户id
	private String truename;// 实名
	private String company;//公司名
	private String mobile; //电话
	private String initiation;//起始港
	private String destination; //目的港
	private String startime;//走货开始时间
	private String endtime;//走货结束时间
	private List<String> boxtype;
	private List<String> number;
	private String packages; //件数
	private String weight; //毛重
	private String volume; //体积
//	private String size; //单件尺寸
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
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
	public List<String> getBoxtype() {
		return boxtype;
	}
	public void setBoxtype(List<String> boxtype) {
		this.boxtype = boxtype;
	}
	public List<String> getNumber() {
		return number;
	}
	public void setNumber(List<String> number) {
		this.number = number;
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
//	public String getSize() {
//		return size;
//	}
//	public void setSize(String size) {
//		this.size = size;
//	}
}

