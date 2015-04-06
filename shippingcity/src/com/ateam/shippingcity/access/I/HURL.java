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
	/**
	 * 头像上传
	 */
	public static final String URL_PERSONAL_UPLOAD="http://"+IP+"/mobile/index.php?route=upload";
	/**
	 * 货盘区列表
	 */
	public static final String URL_PALLET_LIST="http://"+IP+"/mobile/index.php?route=entrust_index";
	/**
	 * 货盘区报价地址
	 */
	public static final String URL_ENTRUST_SHOW="http://"+IP+"/mobile/index.php?route=entrust_show";
	/**
	 * 获取船公司名称
	 */
	public static final String URL_ALL_PORTS="http://"+IP+"/mobile/index.php?route=all_ports";
	/**
	 * 我的报价列表
	 */
	public static final String URL_MYQUOTE_LIST="http://"+IP+"/mobile/index.php?route=my";
}
