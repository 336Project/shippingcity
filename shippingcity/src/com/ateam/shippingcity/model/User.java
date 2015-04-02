package com.ateam.shippingcity.model;

import java.io.Serializable;

/**
 * 
 * @author 李晓伟
 * 2015-3-30 下午4:46:23
 * @TODO 用户
 */
public class User implements Serializable{
	private static final long serialVersionUID = -8877047153853837439L;
	
	private String userssid; //登陆令牌
	private String token; //快速登陆使用
	
	private String truename;
	private String email;
	private String department;
	private String career;
	private String qq;
	private String company;
	private String mobile;
	private String credit;
	private String avatar;//头像
	private String com_address;
	private String vtruename;
	private String vcompany;
	
	public String getUserssid() {
		return userssid;
	}
	public void setUserssid(String userssid) {
		this.userssid = userssid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTruename() {
		if(truename!=null){
			truename=new String(truename.getBytes());
		}
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getCompany() {
		if(company!=null){
			company=new String(company.getBytes());
		}
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
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getAvatar() {
		/*if(!TextUtils.isEmpty(avatar)){
			avatar=avatar.split("&")[0];
		}*/
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getCom_address() {
		if(com_address!=null){
			com_address=new String(com_address.getBytes());
		}
		return com_address;
	}
	public void setCom_address(String com_address) {
		this.com_address = com_address;
	}
	public String getVtruename() {
		return vtruename;
	}
	public void setVtruename(String vtruename) {
		this.vtruename = vtruename;
	}
	public String getVcompany() {
		return vcompany;
	}
	public void setVcompany(String vcompany) {
		this.vcompany = vcompany;
	}
}
