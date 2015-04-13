package com.ateam.shippingcity.model;

import java.io.Serializable;
import java.util.List;

public class MyData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id; //
	private String userid;
	private String itemid;
	private String remarks; //备注
	private String createtime; //报价日期
	private String shipcompany; //船公司
	private String goods_type; //1拼箱重货 2拼箱轻货
	private String content; 
	private String textpath; 
	private String total_price; //合计
	
	
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

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getShipcompany() {
		return shipcompany;
	}

	public void setShipcompany(String shipcompany) {
		this.shipcompany = shipcompany;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTextpath() {
		return textpath;
	}

	public void setTextpath(String textpath) {
		this.textpath = textpath;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
}
