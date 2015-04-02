package com.ateam.shippingcity.model;

import java.io.Serializable;

/**
 * 
 * @author 李晓伟
 * 2015-4-2 下午1:39:03
 * @TODO 积分规则
 */
public class IntegralRule implements Serializable{
	private static final long serialVersionUID = 1465998800033704359L;
	
	private String credit_edit;//完善个人资料奖励
	private String credit_companyedit;//完善公司资料奖励
	private String credit_login;//24小时登录奖励
	private String credit_user;//引导一位会员注册奖励
	private String credit_ip;//引导一个IP访问奖励
	private String credit_maxip;//24小时引导 上限
	private String credit_add_credit;//上传资质证书奖励
	private String credit_del_credit;//资质证书被删除扣除
	private String credit_add_news;//发布企业新闻奖励
	private String credit_del_news;//企业新闻被删除扣除
	private String credit_add_page;//发布企业单页奖励
	private String credit_del_page;//企业单页被删除扣除
	private String credit_updateprices;//运价更新
	private String credit_excellprices;//运价批量（excell）更新
	private String credit_maxprices;//每周运价更新上限
	private String credit_sucess;//交易成功
	private String credit_publish;//采购需求发布
	private String credit_maxpublish;//采购需求发布上限
	private String credit_validate;//公司认证
	private String credit_mobilephone;//手机认证
	
	private String credit_invitenums;//邀请名额
	private String credit_invitescore;//邀请名额对应的积分
	
	private String credit_company_cat;//公司类型
	private String credit_company_cat_score;//公司类型对应积分
	
	private String credit_buy;//购买额度
	private String credit_price;//额度对应价格
	
	private String credit_exchange;//会员积分兑换 0、1

	public String getCredit_edit() {
		return credit_edit;
	}

	public void setCredit_edit(String credit_edit) {
		this.credit_edit = credit_edit;
	}

	public String getCredit_companyedit() {
		return credit_companyedit;
	}

	public void setCredit_companyedit(String credit_companyedit) {
		this.credit_companyedit = credit_companyedit;
	}

	public String getCredit_login() {
		return credit_login;
	}

	public void setCredit_login(String credit_login) {
		this.credit_login = credit_login;
	}

	public String getCredit_user() {
		return credit_user;
	}

	public void setCredit_user(String credit_user) {
		this.credit_user = credit_user;
	}

	public String getCredit_ip() {
		return credit_ip;
	}

	public void setCredit_ip(String credit_ip) {
		this.credit_ip = credit_ip;
	}

	public String getCredit_maxip() {
		return credit_maxip;
	}

	public void setCredit_maxip(String credit_maxip) {
		this.credit_maxip = credit_maxip;
	}

	public String getCredit_add_credit() {
		return credit_add_credit;
	}

	public void setCredit_add_credit(String credit_add_credit) {
		this.credit_add_credit = credit_add_credit;
	}

	public String getCredit_del_credit() {
		return credit_del_credit;
	}

	public void setCredit_del_credit(String credit_del_credit) {
		this.credit_del_credit = credit_del_credit;
	}

	public String getCredit_add_news() {
		return credit_add_news;
	}

	public void setCredit_add_news(String credit_add_news) {
		this.credit_add_news = credit_add_news;
	}

	public String getCredit_del_news() {
		return credit_del_news;
	}

	public void setCredit_del_news(String credit_del_news) {
		this.credit_del_news = credit_del_news;
	}

	public String getCredit_add_page() {
		return credit_add_page;
	}

	public void setCredit_add_page(String credit_add_page) {
		this.credit_add_page = credit_add_page;
	}

	public String getCredit_del_page() {
		return credit_del_page;
	}

	public void setCredit_del_page(String credit_del_page) {
		this.credit_del_page = credit_del_page;
	}

	public String getCredit_updateprices() {
		return credit_updateprices;
	}

	public void setCredit_updateprices(String credit_updateprices) {
		this.credit_updateprices = credit_updateprices;
	}

	public String getCredit_excellprices() {
		return credit_excellprices;
	}

	public void setCredit_excellprices(String credit_excellprices) {
		this.credit_excellprices = credit_excellprices;
	}

	public String getCredit_maxprices() {
		return credit_maxprices;
	}

	public void setCredit_maxprices(String credit_maxprices) {
		this.credit_maxprices = credit_maxprices;
	}

	public String getCredit_sucess() {
		return credit_sucess;
	}

	public void setCredit_sucess(String credit_sucess) {
		this.credit_sucess = credit_sucess;
	}

	public String getCredit_publish() {
		return credit_publish;
	}

	public void setCredit_publish(String credit_publish) {
		this.credit_publish = credit_publish;
	}

	public String getCredit_maxpublish() {
		return credit_maxpublish;
	}

	public void setCredit_maxpublish(String credit_maxpublish) {
		this.credit_maxpublish = credit_maxpublish;
	}

	public String getCredit_validate() {
		return credit_validate;
	}

	public void setCredit_validate(String credit_validate) {
		this.credit_validate = credit_validate;
	}

	public String getCredit_mobilephone() {
		return credit_mobilephone;
	}

	public void setCredit_mobilephone(String credit_mobilephone) {
		this.credit_mobilephone = credit_mobilephone;
	}

	public String getCredit_invitenums() {
		return credit_invitenums;
	}

	public void setCredit_invitenums(String credit_invitenums) {
		this.credit_invitenums = credit_invitenums;
	}

	public String getCredit_invitescore() {
		return credit_invitescore;
	}

	public void setCredit_invitescore(String credit_invitescore) {
		this.credit_invitescore = credit_invitescore;
	}

	public String getCredit_company_cat() {
		return credit_company_cat;
	}

	public void setCredit_company_cat(String credit_company_cat) {
		this.credit_company_cat = credit_company_cat;
	}

	public String getCredit_company_cat_score() {
		return credit_company_cat_score;
	}

	public void setCredit_company_cat_score(String credit_company_cat_score) {
		this.credit_company_cat_score = credit_company_cat_score;
	}

	public String getCredit_buy() {
		return credit_buy;
	}

	public void setCredit_buy(String credit_buy) {
		this.credit_buy = credit_buy;
	}

	public String getCredit_price() {
		return credit_price;
	}

	public void setCredit_price(String credit_price) {
		this.credit_price = credit_price;
	}

	public String getCredit_exchange() {
		return credit_exchange;
	}

	public void setCredit_exchange(String credit_exchange) {
		this.credit_exchange = credit_exchange;
	}
}
