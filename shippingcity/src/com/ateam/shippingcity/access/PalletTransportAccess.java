package com.ateam.shippingcity.access;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

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
		nvps.add(new BasicNameValuePair("shipping", shipping));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("page", page+""));
		nvps.add(new BasicNameValuePair("pagesize", pagesize+""));
		execute(URL_PALLET_LIST, nvps);
	};
	
}
