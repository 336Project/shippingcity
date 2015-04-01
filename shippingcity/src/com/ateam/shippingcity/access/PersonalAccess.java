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
	 * 2015-3-31 下午9:45:32
	 * @param mobile
	 * @TODO 获取手机验证码
	 */
	public void getMobileCode(String mobile){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("step", "getMobileCode"));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "register"));
		execute(URL_PERSONAL_CENTER, nvps);
	}
	/**
	 * 
	 * 2015-3-31 下午9:45:40
	 * @param mobile
	 * @param code
	 * @TODO 验证手机验证码
	 */
	public void checkMobileCode(String mobile,String mobile_code){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("mobile_code", mobile_code));
		nvps.add(new BasicNameValuePair("step", "checkMobileCode"));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "register"));
		execute(URL_PERSONAL_CENTER, nvps);
	}
	/**
	 * 
	 * 2015-3-31 下午9:45:47
	 * @param mobile
	 * @param password
	 * @param cpassword
	 * @param truename
	 * @param company
	 * @param invitermobile
	 * @TODO 注册
	 */
	public void register(String mobile,String password,String cpassword,String truename,String company,String invitermobile){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("step", "submitReginfo"));
		nvps.add(new BasicNameValuePair("action", "register"));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("post[password]", password));
		nvps.add(new BasicNameValuePair("post[cpassword]", cpassword));
		nvps.add(new BasicNameValuePair("post[truename]", truename));
		nvps.add(new BasicNameValuePair("post[company]", company));
		nvps.add(new BasicNameValuePair("post[invitermobile]", invitermobile));
		execute(URL_PERSONAL_CENTER, nvps);
	}
	/**
	 * 
	 * 2015-3-31 下午9:46:00
	 * @param userssid
	 * @TODO 获取积分记录
	 */
	public void getIntegralRecords(String userssid){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("action", "get"));
		execute(URL_PERSONAL_CENTER, nvps);
	}
	/**
	 * 
	 * 2015-3-31 下午11:11:18
	 * @param userssid
	 * @TODO 获取积分规则
	 */
	public void getCreditRule(String userssid){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("action", "getcreditrule"));
		execute(URL_PERSONAL_CENTER, nvps);
	}
	/**
	 * 
	 * 2015-4-1 下午5:21:20
	 * @param userssid
	 * @param content
	 * @TODO 意见反馈
	 */
	public void feedback(String userssid,String content){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("my", "0"));
		nvps.add(new BasicNameValuePair("post[hidden]", "1"));
		nvps.add(new BasicNameValuePair("post[content]", content));
		nvps.add(new BasicNameValuePair("Msubmit", "提交"));
		execute(URL_PERSONAL_CENTER, nvps);
	}
}
