package com.ateam.shippingcity.model;

import java.io.Serializable;
import java.util.List;

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
	
	private String truename;//姓名
	private String email;//邮箱
	private String department;
	private String career;
	private String qq;
	private String company;//公司
	private String mobile;
	private String credit;
	private String avatar;//头像
	private String com_address;
	private String vtruename;//实名认证（身份证 工牌）
	private String vcompany;//公司认证
	private List<String> vpicture;//对应身份证、工牌、营业执照
	
	private String status_company;//公司认证状态
	private String status_truename;//实名认证状态
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
	public String getStatus_company() {
		if(getVcompany().equals("2")){
			status_company="审核中";
		}else if(getVcompany().equals("3")){
			status_company="已认证";
		}else{
			status_company="未上传";
		}
		return status_company;
	}
	public void setStatus_company(String status_company) {
		this.status_company = status_company;
	}
	public String getStatus_truename() {
		if(getVtruename().equals("2")){
			status_truename="审核中";
		}else if(getVtruename().equals("3")){
			status_truename="已认证";
		}else{
			status_truename="未上传";
		}
		return status_truename;
	}
	public void setStatus_truename(String status_truename) {
		this.status_truename = status_truename;
	}
	public List<String> getVpicture() {
		return vpicture;
	}
	public void setVpicture(List<String> vpicture) {
		this.vpicture = vpicture;
	}
}
