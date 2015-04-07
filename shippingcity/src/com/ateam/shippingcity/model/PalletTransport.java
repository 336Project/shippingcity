package com.ateam.shippingcity.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 海运对象
 * @version 
 * @create_date 2015-3-28上午9:52:53
 */
public class PalletTransport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public String id;//货盘id
	public String order_id;// 订单号
	public String shipping_type;//1海运 2空运 3陆运
	public String shipment_type;//1整箱 2散杂货 3拼箱
	public String userid;//发布货盘的用户id
	public String truename;//实名
	public String company;//公司名
	public String mobile;//电话
	public String initiation;//起始港
	public String destination;//目的港
	public List<String> boxtype;//箱型
	public List<String> number;//对应箱型的数量
	public String createtime;//添加时间
	public String deadlinetime;//报价截止时间
	public String startime;//走货开始时间
	public String endtime;//走货结束时间
	public String dealtime;//--------
	public String status;//货盘状态（{1}报价中 {2}已确认货代 {3}中止交易{4}已截止）
	public String packages;//件数
	public String weight;//毛重
	public String volume;//体积
	public List<String> size;//大小 分别为长宽高
	public String picture_path;//图片路径 多张图用竖线 ‘|’分割 加上前缀root_url才算完整的图片请求路径
	public String remarks;//备注
	public String buyer;//选择的货代userid 0为还未选择报价的货代
	public String note;//---------------
	public String count;//报价人数
	public String d_createtime;//------------
	public String username;	//用户名
	//登录状态下：
	public String isowner;//是否为当前登录用户发布的 0否1是
	public String ifbid;//是否已经报价过 0否1是
	
}
