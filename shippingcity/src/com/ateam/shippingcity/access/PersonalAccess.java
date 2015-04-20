package com.ateam.shippingcity.access;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		execute(URL_PERSONAL_MEMBER, nvps);
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
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-3-31 下午9:45:32
	 * @param mobile
	 * @TODO 获取手机验证码
	 */
	public void getMobileCodeRegister(String mobile){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("step", "getMobileCode"));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "register"));
		execute(URL_PERSONAL_MEMBER, nvps);
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
		execute(URL_PERSONAL_MEMBER, nvps);
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
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-4-4 上午11:46:15
	 * @param mobile
	 * @TODO 忘记密码->获取验证码
	 */
	public void getMobileCodeFindpw(String mobile){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("step", "getMobileCode"));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "findpw"));
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-4-4 下午12:01:37
	 * @param mobile
	 * @param mobile_code
	 * @param password
	 * @TODO 忘记密码->重置密码
	 */
	public void resetPassword(String mobile,String mobile_code,String password){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("step", "checkMobileCode"));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "findpw"));
		nvps.add(new BasicNameValuePair("mobile_code", mobile_code));
		nvps.add(new BasicNameValuePair("password", password));
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-3-31 下午9:46:00
	 * @param userssid
	 * @TODO 获取积分记录
	 */
	public void getIntegralRecords(String userssid,int page,int pagesize){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("action", "get"));
		nvps.add(new BasicNameValuePair("page", page+""));
		nvps.add(new BasicNameValuePair("pagesize", pagesize+""));
		execute(URL_PERSONAL_CREDIT, nvps);
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
		execute(URL_PERSONAL_CREDIT, nvps);
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
		execute(URL_PERSONAL_FEEDBACK, nvps);
	}
	/**
	 * 
	 * 2015-4-9 上午11:51:56
	 * @param versioncode
	 * @TODO 检测更新
	 */
	public void updateCheck(String versioncode){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("appid", "2"));
		nvps.add(new BasicNameValuePair("versioncode", versioncode));
		nvps.add(new BasicNameValuePair("apptype", "货代端"));
		execute(URL_PERSONAL_UPDATE, nvps);
	}
	/**
	 * 
	 * 2015-4-2 下午3:38:10
	 * @param userssid
	 * @param mobile
	 * @TODO 手机修改->获取手机验证码
	 */
	public void getGeneralMobileMode(String userssid,String mobile){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "generalmobilecode"));
		nvps.add(new BasicNameValuePair("using", "changemobile"));
		nvps.add(new BasicNameValuePair("type", "get"));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-4-2 下午3:41:00
	 * @param userssid
	 * @param mobile_code
	 * @TODO 手机修改->验证手机验证码
	 */
	public void checkGeneralMobileMode(String userssid,String mobile,String mobile_code){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "generalmobilecode"));
		nvps.add(new BasicNameValuePair("using", "changemobile"));
		nvps.add(new BasicNameValuePair("type", "validate"));
		nvps.add(new BasicNameValuePair("mobile_code", mobile_code));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-4-2 下午3:44:19
	 * @param userssid
	 * @param mobile
	 * @TODO 修改手机号
	 */
	public void modifyMobile(String userssid,String mobile){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "changemobile"));
		nvps.add(new BasicNameValuePair("mobile", mobile));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-4-2 下午4:16:33
	 * @param userssid
	 * @param truename
	 * @TODO 修改名称
	 */
	public void modifyUsername(String userssid,String truename){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "userinfo"));
		nvps.add(new BasicNameValuePair("Msubmit", "提交"));
		nvps.add(new BasicNameValuePair("truename", truename));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-4-3 下午1:42:36
	 * @param userssid
	 * @param address
	 * @TODO 修改公司地址
	 */
	public void modifyAddress(String userssid,String address){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "userinfo"));
		nvps.add(new BasicNameValuePair("Msubmit", "提交"));
		nvps.add(new BasicNameValuePair("com_address", address));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-4-3 下午1:51:08
	 * @param userssid
	 * @param password
	 * @param newpassword
	 * @TODO 登录后修改密码
	 */
	public void modifyPassword(String userssid,String password,String newpassword){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("action", "loginchangepw"));
		nvps.add(new BasicNameValuePair("password", password));
		nvps.add(new BasicNameValuePair("newpassword", newpassword));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		execute(URL_PERSONAL_MEMBER, nvps);
	}
	/**
	 * 
	 * 2015-4-3 下午2:01:28
	 * @param userssid
	 * @param img
	 * @TODO 修改头像
	 */
	public void modifyAvatar(String userssid,File img){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		Map<String, File> file=new HashMap<String, File>();
		file.put("img", img);
		execute(URL_PERSONAL_UPLOAD, nvps,file);
	}
	/**
	 * 
	 * 2015-4-8 下午2:56:59
	 * @param userssid
	 * @param upthumb
	 * @param old
	 * @param type
	 * @TODO 修改证件
	 */
	public void modifyPhoto(String userssid,File upthumb,String old,String type){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("old", old));
		if(type.equals("身份证")){
			nvps.add(new BasicNameValuePair("fid", "thumb"));
		}else if(type.equals("工牌")){
			nvps.add(new BasicNameValuePair("fid", "thumb1"));
		}else if(type.equals("营业执照")){
			nvps.add(new BasicNameValuePair("fid", "thumb"));
		}
		Map<String, File> file=new HashMap<String, File>();
		file.put("upthumb", upthumb);
		execute(URL_PERSONAL_UPLOADS, nvps,file);
	}
	/**
	 * 
	 * 2015-4-8 下午3:46:45
	 * @param userssid
	 * @param imgUrl
	 * @param type
	 * @TODO 认证
	 */
	public void authen(String userssid,String imgUrl,String type){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("Msubmit", "提交"));
		nvps.add(new BasicNameValuePair("thumb", imgUrl));
		if(type.equals("身份证")){
			//nvps.add(new BasicNameValuePair("thumb", imgUrl));
			nvps.add(new BasicNameValuePair("action", "truename"));
		}else if(type.equals("工牌")){
			//nvps.add(new BasicNameValuePair("thumb1", imgUrl));
			//nvps.add(new BasicNameValuePair("action", "truename"));
			nvps.add(new BasicNameValuePair("action", "company"));
		}else if(type.equals("营业执照")){
			//nvps.add(new BasicNameValuePair("thumb", imgUrl));
			nvps.add(new BasicNameValuePair("action", "vcompany"));
		}
		execute(URL_PERSONAL_AUTHEN, nvps);
	}
	/**
	 * 
	 * 2015-4-8 上午10:38:22
	 * @param userssid
	 * @param shipping
	 * @param page
	 * @param pagesize
	 * @TODO 获取关注的货盘列表
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
		nvps.add(new BasicNameValuePair("collect", "1"));
		execute(URL_PALLET_LIST, nvps);
	}
	/**
	 * 
	 * 2015-4-16 上午11:18:39
	 * @param userssid
	 * @param mobile
	 * @TODO 好友邀请
	 */
	public void invite(String userssid,String mobile){
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("userssid", userssid));
		nvps.add(new BasicNameValuePair("mobile_access_token", "thekeyvalue"));
		nvps.add(new BasicNameValuePair("apptype", "货代端"));
		nvps.add(new BasicNameValuePair("mobilelist", mobile));
		execute(URL_PERSONAL_INVITE, nvps);
	}
}
