package com.ateam.shippingcity.access;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;

public class MyQuoteAccess <T> extends HBaseAccess<Respond<T>>{

	public MyQuoteAccess(Context c,
			HRequestCallback<Respond<T>> requestCallback) {
		super(c, requestCallback);
	}
	
	/**
	 * 获取待确认报价和历史报价
	 * @param userssid
	 * @param history 待确认0;历史报价1
	 * @param page 第几页
	 * @param pagesize 每页显示数量
	 */
	public void getMyQuoteList(String userssid,String history,int page,int pagesize){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "list"));
		nvps.add(new BasicNameValuePair("role", "forwarder"));
		nvps.add(new BasicNameValuePair("mid", "24"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("history", history));
		nvps.add(new BasicNameValuePair("page", page+""));
		nvps.add(new BasicNameValuePair("pagesize", pagesize+""));
		execute(URL_MYQUOTE_LIST, nvps);
	};
	/**
	 * 获取待确认报价和历史报价详情页
	 * @param userssid 用户Id
	 * @param id LIST中item的Id
	 */
	public void getMyQuoteDetail(String userssid,String id){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "detail"));
		nvps.add(new BasicNameValuePair("role", "forwarder"));
		nvps.add(new BasicNameValuePair("mid", "24"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("id", id));
		execute(URL_MYQUOTE_LIST, nvps);
	};
}
