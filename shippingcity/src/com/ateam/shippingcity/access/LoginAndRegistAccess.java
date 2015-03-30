package com.ateam.shippingcity.access;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;

import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.model.User;
import com.ateam.shippingcity.utils.SysUtil;
/**
 * 
 * @author 李晓伟
 * 2015-3-30 下午4:49:28
 * @TODO 登录及注册
 */
public class LoginAndRegistAccess extends HBaseAccess<Respond<User>> {
	private Context context;
	public LoginAndRegistAccess(Context c,
			HRequestCallback<Respond<User>> requestCallback) {
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
		execute(URL_LOGIN_AND_REGIST, nvps);
	}
}
