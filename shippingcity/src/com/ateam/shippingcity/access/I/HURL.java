package com.ateam.shippingcity.access.I;
/**
 * 
 * @author 李晓伟
 * @Create_date 2015-3-25 上午10:07:58
 * @Version 
 * @TODO 接口访问url
 */
public interface HURL {
	public static final String IP="118.193.146.184";
	/**
	 * 个人信息
	 */
	public static final String URL_PERSONAL_MEMBER="http://"+IP+"/mobile/index.php?route=member";
	/**
	 * 积分
	 */
	public static final String URL_PERSONAL_CREDIT="http://"+IP+"/mobile/index.php?route=credit";
}
