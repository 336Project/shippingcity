package com.ateam.shippingcity.model;

import java.io.Serializable;

/**
 * 
 * @author 李晓伟 2015-4-2 上午10:32:47
 * @TODO 我的积分
 */
public class MyIntegral implements Serializable {
	private static final long serialVersionUID = -8244274889565824630L;
	private String itemid;
	private String username;
	private String amount;// 积分
	private String balance;
	private String addtime;
	private String reason;// 获得来源
	private String note;
	private String editor;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
}
