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
	@Override
	public String toString() {
		return "User [userssid=" + userssid + ", token=" + token + "]";
	}
}
