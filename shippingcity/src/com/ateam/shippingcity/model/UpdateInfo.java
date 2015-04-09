package com.ateam.shippingcity.model;


/**
 * 
 * @author 李晓伟
 * 2015-4-9 上午11:58:04
 * @TODO 更新
 */
public class UpdateInfo {
	private String type;//是否升级：1升级0不升级2强制升级
	private String upgrade_point;//更新内容
	private String apk_url;//下载地址
	private String version_code;//版本号
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUpgrade_point() {
		if(upgrade_point!=null){
			return new String(upgrade_point.getBytes());
		}
		return upgrade_point;
	}
	public void setUpgrade_point(String upgrade_point) {
		this.upgrade_point = upgrade_point;
	}
	public String getApk_url() {
		return apk_url;
	}
	public void setApk_url(String apk_url) {
		this.apk_url = apk_url;
	}
	public String getVersion_code() {
		return version_code;
	}
	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}
	
	
}
