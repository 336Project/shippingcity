package com.ateam.shippingcity.access;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.util.Log;

import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;

public class PalletTransportAccess <T> extends HBaseAccess<Respond<T>>{

	public PalletTransportAccess(Context c,
			HRequestCallback<Respond<T>> requestCallback) {
		super(c, requestCallback);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取货运列表
	 * @param userssid
	 * @param shipping 货运类型
	 * @param page 第几页
	 * @param pagesize 每页显示数量
	 */
	public void getPalletRansportList(String userssid,String shipping,int page,int pagesize){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userssid", userssid));
		if(shipping.equals("全部")){
			nvps.add(new BasicNameValuePair("shipping", "0"));
		}else if(shipping.equals("海运")){
			nvps.add(new BasicNameValuePair("shipping", "1"));
		}else if(shipping.equals("空运")){
			nvps.add(new BasicNameValuePair("shipping", "2"));
		}else{
			nvps.add(new BasicNameValuePair("shipping", "3"));
		}
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("page", page+""));
		nvps.add(new BasicNameValuePair("pagesize", pagesize+""));
		Log.e("", ""+nvps.toString());
		execute(URL_PALLET_LIST, nvps);
	};
	
	/**
	 * 获取船公司名称
	 * @param userssid
	 * @param optionName
	 */
	public void getBootCompany(String userssid){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("optionName", "船公司"));
		execute(URL_ALL_PORTS, nvps);
	}
	
	/**
	 * 海运整箱  报价请求
	 * @param userssid
	 * @param price
	 * @param itemid
	 * @param shipcompany
	 * @param remarks
	 */
	public void seaWholeOfferCommit(String userssid,
			String price,String itemid,String shipcompany,String remarks){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("post[price]", price));
		nvps.add(new BasicNameValuePair("post[itemid]", itemid));
		nvps.add(new BasicNameValuePair("post[shipcompany]", shipcompany));
		nvps.add(new BasicNameValuePair("post[remarks]", remarks));
		nvps.add(new BasicNameValuePair("action", "add"));
		Log.e("", ""+nvps.toString());
		execute(URL_ENTRUST_SHOW, nvps);
	}
	
	/**
	 * 海运拼箱  报价请求
	 * @param userssid
	 * @param price
	 * @param itemid
	 * @param shipcompany
	 * @param remarks
	 */
	public void seaSpellOfferCommit(String userssid,
			String price,String itemid,String remarks,String goods_type){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("post[price]", price));
		nvps.add(new BasicNameValuePair("post[itemid]", itemid));
		nvps.add(new BasicNameValuePair("post[remarks]", remarks));
		nvps.add(new BasicNameValuePair("action", "add"));
		nvps.add(new BasicNameValuePair("goods_type", goods_type));
		execute(URL_ENTRUST_SHOW, nvps);
	}
	
	/**
	 * 陆运整箱  报价请求
	 * @param userssid
	 * @param price
	 * @param itemid
	 * @param shipcompany
	 * @param remarks
	 */
	public void lordWholeOfferCommit(String userssid,
			String price,String itemid,String remarks){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("post[price]", price));
		nvps.add(new BasicNameValuePair("post[itemid]", itemid));
		nvps.add(new BasicNameValuePair("post[remarks]", remarks));
		nvps.add(new BasicNameValuePair("action", "add"));
		Log.e("", ""+nvps.toString());
		execute(URL_ENTRUST_SHOW, nvps);
	}
	
	/**
	 * 陆运整箱  报价请求
	 * @param userssid
	 * @param itemid
	 * @param remarks
	 */
	public void seaDiffOfferCommit(String userssid,String itemid,String remarks){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("post[itemid]", itemid));
		nvps.add(new BasicNameValuePair("post[remarks]", remarks));
		nvps.add(new BasicNameValuePair("action", "add"));
		execute(URL_ENTRUST_SHOW, nvps);
	}
	
	/**
	 * 空运，陆运散杂货  报价请求
	 * @param userssid
	 * @param price
	 * @param itemid
	 * @param shipcompany
	 * @param remarks
	 */
	public void airAndlordOfferCommit(String userssid,
			String price,String itemid,String remarks){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("post[price]", price));
		nvps.add(new BasicNameValuePair("post[itemid]", itemid));
		nvps.add(new BasicNameValuePair("post[remarks]", remarks));
		nvps.add(new BasicNameValuePair("action", "add"));
		execute(URL_ENTRUST_SHOW, nvps);
	}
	
	/**
	 * 详情接口
	 * @param userssid
	 * @param price
	 * @param itemid
	 * @param shipcompany
	 * @param remarks
	 */
	public void getPalletDetail(String userssid,String itemid){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("itemid", itemid));
		execute(URL_ENTRUST_SHOW, nvps);
	}
	
	/**
	 * 关注
	 * @param userssid
	 * @param price
	 * @param itemid
	 * @param shipcompany
	 * @param remarks
	 */
	public void toFocus(String userssid,String itemid,String action){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("id", itemid));
		nvps.add(new BasicNameValuePair("type", "货盘"));
		nvps.add(new BasicNameValuePair("action", action));
		execute(URL_FOCUS, nvps);
	}
	
}
