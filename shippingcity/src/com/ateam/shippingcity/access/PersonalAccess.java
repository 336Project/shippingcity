package com.ateam.shippingcity.access;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.SysUtil;
/**
 * 
 * @author 李晓伟
 * 2015-3-30 下午4:49:28
 * @TODO 个人中心接口
 */
public class PersonalAccess<T> extends HBaseAccess<Respond<T>> {
	private Context context;
	public PersonalAccess(Context c,
			HRequestCallback<Respond<T>> requestCallback) {
		super(c, requestCallback);
		this.context=c;
	}
	/**
	 * 
	 * 2015-3-30 下午5:06:48
	 * @param username
	 * @param password
	 * @TODO 登录
	 */
	public void login(String username,String password){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "login"));
		nvps.add(new BasicNameValuePair("imei", SysUtil.getIMEI(context)));
		nvps.add(new BasicNameValuePair("Msubmit", "提交"));
		execute(URL_PERSONAL_CENTER, nvps);
	}
	/**
	 * 
	 * 2015-3-31 下午4:18:48
	 * @param ssid
	 * @TODO 获取用户信息
	 */
	public void getUserInfo(String userssid){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "userinfo"));
		execute(URL_PERSONAL_CENTER, nvps);
	}
	/**
	 * 
	 * 2015-3-31 下午5:28:27
	 * @param mobile
	 * @TODO 获取手机验证码
	 */
	public void getMobileCode(String mobile){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("step", "getMobileCode"));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "register"));
		execute(URL_PERSONAL_CENTER, nvps);
	}
}
