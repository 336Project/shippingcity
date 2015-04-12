package com.ateam.shippingcity.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.R.menu;
import com.ateam.shippingcity.access.MyQuoteAccess;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.access.I.HURL;
import com.ateam.shippingcity.constant.ConstantUtil;
import com.ateam.shippingcity.model.MyData;
import com.ateam.shippingcity.model.MyQuoteToConfirmDetail;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;
import com.ateam.shippingcity.utils.PopupWindowUtil;
import com.ateam.shippingcity.widget.weinxinImageShow.ImagePagerActivity;
import com.ateam.shippingcity.widget.weinxinImageShow.MyGridAdapter;
import com.google.gson.reflect.TypeToken;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyQuoteConfirmActivity extends HBaseActivity implements
		OnClickListener {
	private GridView mGvAddPhoto;
	private String id;
	private TextView tv_initiation;
	private TextView tv_destination;
	private TextView tv_shipping;
	private ImageView iv_shipment;
	private TextView tv_startime;
	private TextView tv_endtime;
	private TextView tv_deadlinetime;
	private TextView tv_description;
	private TextView tv_remarks;
	private LinearLayout ll_photo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_my_quote_sea_transport_fcl);
		init();
		intIntent();
		initView();
		request();
	}

	private void request() {
		HRequestCallback<Respond<MyQuoteToConfirmDetail>> requestCallback = new HRequestCallback<Respond<MyQuoteToConfirmDetail>>() {

			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
				onLoadFail();
			}

			@SuppressWarnings("unchecked")
			@Override
			public Respond<MyQuoteToConfirmDetail> parseJson(String jsonStr) {
				java.lang.reflect.Type type = new TypeToken<Respond<MyQuoteToConfirmDetail>>() {
				}.getType();
				return (Respond<MyQuoteToConfirmDetail>) JSONParse
						.jsonToObject(jsonStr, type);
			}

			@Override
			public void onSuccess(Respond<MyQuoteToConfirmDetail> result) {
				if (result.isSuccess()) {
					MyQuoteToConfirmDetail datas = result.getDatas();
					MyData mydata = datas.getMydata();
					String picture_path = datas.getPicture_path();
					if (!picture_path.equals("")) {
						String[] split = picture_path.split("\\|");
						for (int i = 0; i < split.length; i++) {
							String string = split[i];
							string = "http://" + HURL.IP + "/" + string;
							split[i] = string;
						}
						initGridView(split);
					} else {
						ll_photo.setVisibility(View.GONE);
					}

					String shipping_type = datas.getShipping_type();
					String shipment_type = datas.getShipment_type();
					if (shipping_type.equals("1")) {
						tv_shipping.setText("海运");
						if (shipment_type.equals("1")) {
							iv_shipment
									.setImageResource(R.drawable.pallet_details_zhengxiang_small_icon);
							ArrayList<Integer> tvId_List = new ArrayList<Integer>();
							ArrayList<String> content_List = new ArrayList<String>();
							tvId_List.add(R.id.tv_shipcompany);
							tvId_List.add(R.id.tv_totalprices);
							tvId_List.add(R.id.tv_remarks);
							tvId_List.add(R.id.tv_createtime);
							tvId_List.add(R.id.ll_quotation);
							content_List.add(mydata.getShipcompany());
							List<String> price = mydata.getPrice();
							double sum = 0;
							for (int i = 0; i < price.size(); i++) {
								String price_item = price.get(i);
								if (!price_item.equals("")) {
									double parseDouble = Double
											.parseDouble(price_item);
									sum += parseDouble;
								}
							}
							content_List.add("$"+sum + "");
							content_List.add(mydata.getRemarks());
							String createtime = mydata.getCreatetime();
							if (createtime.equals("")) {
								content_List.add("");
							} else {
								content_List.add(formatDate(createtime));
							}
							PopupWindowUtil.initPopup(
									MyQuoteConfirmActivity.this,
									R.layout.pop_my_quote_1, tvId_List,
									content_List, datas.getType(),
									datas.getNum(), mydata.getPrice());
						} else if (shipment_type.equals("2")) {
							iv_shipment
									.setImageResource(R.drawable.pallet_details_san_groceries_small_icon);
							ArrayList<Integer> tvId_List = new ArrayList<Integer>();
							ArrayList<String> content_List = new ArrayList<String>();
							tvId_List.add(R.id.tv_remarks);
							tvId_List.add(R.id.tv_createtime);
							content_List.add(mydata.getRemarks());
							String createtime = mydata.getCreatetime();
							if (createtime.equals("")) {
								content_List.add("");
							} else {
								content_List.add(formatDate(createtime));
							}
							PopupWindowUtil.initPopup(
									MyQuoteConfirmActivity.this,
									R.layout.pop_my_quote_3, tvId_List,
									content_List);
						} else {
							iv_shipment
									.setImageResource(R.drawable.pallet_details_of_pinxiang_small_icon);
							ArrayList<Integer> tvId_List = new ArrayList<Integer>();
							ArrayList<String> content_List = new ArrayList<String>();
							tvId_List.add(R.id.tv_totalprices);
							tvId_List.add(R.id.tv_remarks);
							tvId_List.add(R.id.tv_createtime);
							tvId_List.add(R.id.tv_unit);
							List<String> price = datas.getMydata().getPrice();
							double sum = 0;
							for (int i = 0; i < price.size(); i++) {
								String price_item = price.get(i);
								if (!price_item.equals("")) {
									double parseDouble = Double
											.parseDouble(price_item);
									sum += parseDouble;
								}
							}
							content_List.add(sum + "");
							content_List.add(mydata.getRemarks());
							String createtime = mydata.getCreatetime();
							if (createtime.equals("")) {
								content_List.add("");
							} else {
								content_List.add(formatDate(createtime));
							}
							String goods_type = mydata.getGoods_type();
							if (goods_type.equals("0")) {
								content_List.add("$/RT");
							} else {
								content_List.add("$/CBM");
							}
							PopupWindowUtil.initPopup(
									MyQuoteConfirmActivity.this,
									R.layout.pop_my_quote_2, tvId_List,
									content_List);
						}
					} else if (shipping_type.equals("2")) {
						tv_shipping.setText("空运");
						iv_shipment
								.setImageResource(R.drawable.pallet_details_air_transport_small_icon);
						ArrayList<Integer> tvId_List = new ArrayList<Integer>();
						ArrayList<String> content_List = new ArrayList<String>();
						tvId_List.add(R.id.tv_totalprices);
						tvId_List.add(R.id.tv_remarks);
						tvId_List.add(R.id.tv_createtime);
						tvId_List.add(R.id.tv_unit);
						List<String> price = datas.getMydata().getPrice();
						double sum = 0;
						for (int i = 0; i < price.size(); i++) {
							String price_item = price.get(i);
							if (!price_item.equals("")) {
								double parseDouble = Double
										.parseDouble(price_item);
								sum += parseDouble;
							}
						}
						content_List.add(sum + "");
						content_List.add(datas.getMydata().getRemarks());
						String createtime = datas.getMydata().getCreatetime();
						if (createtime.equals("")) {
							content_List.add("");
						} else {
							content_List.add(formatDate(createtime));
						}
						content_List.add("¥/KGS");

						PopupWindowUtil.initPopup(MyQuoteConfirmActivity.this,
								R.layout.pop_my_quote_2, tvId_List,
								content_List);
					} else {
						tv_shipping.setText("陆运");
						if (shipment_type.equals("1")) {
							iv_shipment
									.setImageResource(R.drawable.pallet_details_zhengxiang_small_icon);
							ArrayList<Integer> tvId_List = new ArrayList<Integer>();
							ArrayList<String> content_List = new ArrayList<String>();
							tvId_List.add(R.id.tv_totalprices);
							tvId_List.add(R.id.tv_remarks);
							tvId_List.add(R.id.tv_createtime);
							tvId_List.add(R.id.ll_quotation);
							List<String> price = mydata.getPrice();
							double sum = 0;
							for (int i = 0; i < price.size(); i++) {
								String price_item = price.get(i);
								if (!price_item.equals("")) {
									double parseDouble = Double
											.parseDouble(price_item);
									sum += parseDouble;
								}
							}
							content_List.add("¥"+sum + "");
							content_List.add(mydata.getRemarks());
							String createtime = mydata.getCreatetime();
							if (createtime.equals("")) {
								content_List.add("");
							} else {
								content_List.add(formatDate(createtime));
							}
							PopupWindowUtil.initPopup(
									MyQuoteConfirmActivity.this,
									R.layout.pop_my_quote_4, tvId_List,
									content_List, datas.getType(),
									datas.getNum(), mydata.getPrice());
						} else if (shipment_type.equals("2")) {
							iv_shipment
									.setImageResource(R.drawable.pallet_details_san_groceries_small_icon);
							ArrayList<Integer> tvId_List = new ArrayList<Integer>();
							ArrayList<String> content_List = new ArrayList<String>();
							tvId_List.add(R.id.tv_totalprices);
							tvId_List.add(R.id.tv_remarks);
							tvId_List.add(R.id.tv_createtime);

							List<String> price = datas.getMydata().getPrice();
							double sum = 0;
							for (int i = 0; i < price.size(); i++) {
								String price_item = price.get(i);
								if (!price_item.equals("")) {
									double parseDouble = Double
											.parseDouble(price_item);
									sum += parseDouble;
								}
							}
							content_List.add("¥" + sum + "");
							content_List.add(datas.getMydata().getRemarks());
							String createtime = datas.getMydata()
									.getCreatetime();
							if (createtime.equals("")) {
								content_List.add("");
							} else {
								content_List.add(formatDate(createtime));
							}
							PopupWindowUtil.initPopup(
									MyQuoteConfirmActivity.this,
									R.layout.pop_my_quote_2, tvId_List,
									content_List);
						}
					}
					tv_initiation.setText(datas.getInitiation());
					tv_destination.setText(datas.getDestination());
					tv_startime.setText(datas.getStartime());
					tv_endtime.setText(datas.getEndtime());
					tv_deadlinetime.setText(datas.getDeadlinetime());
					StringBuffer description = new StringBuffer();
					if (shipping_type.equals("1") && shipment_type.equals("1")) {
						List<String> type = datas.getType();
						List<String> num = datas.getNum();
						if (type.size() > 0) {
							description.append("箱型：");
							for (int i = 0; i < type.size(); i++) {
								description.append(type.get(i) + ",");
								description.append("数量" + num.get(i) + "箱");
								if (i < type.size() - 1) {
									description.append(";");
								} else
									description.append("。");
							}
						}
					} else {
						description.append("件数：" + datas.getPackages() + ";");
						description.append("毛重：" + datas.getWeight() + "kg;");
						description.append("体积：" + datas.getVolume() + "立方;");
						description.append("单件尺寸：" + datas.getSize() + "。");
					}
					tv_description.setText(description.toString());
					tv_remarks.setText(datas.getRemarks());
				}
			}
		};
		MyQuoteAccess<MyQuoteToConfirmDetail> access = new MyQuoteAccess<MyQuoteToConfirmDetail>(
				this, requestCallback);
		access.getMyQuoteDetail(mBaseApp.getUserssid(), id);
	}

	private void intIntent() {
		Intent intent = getIntent();
		id = intent.getStringExtra("offerid");
	}

	private void initView() {
		mGvAddPhoto = (GridView) findViewById(R.id.gv_addPhoto);
		tv_shipping = (TextView) findViewById(R.id.tv_shipping);
		iv_shipment = (ImageView) findViewById(R.id.iv_shipment);
		tv_initiation = (TextView) findViewById(R.id.tv_initiation);
		tv_destination = (TextView) findViewById(R.id.tv_destination);
		tv_startime = (TextView) findViewById(R.id.tv_startime);
		tv_endtime = (TextView) findViewById(R.id.tv_endtime);
		tv_deadlinetime = (TextView) findViewById(R.id.tv_deadlinetime);
		tv_description = (TextView) findViewById(R.id.tv_description);
		tv_remarks = (TextView) findViewById(R.id.tv_remarks);
		ll_photo = (LinearLayout) findViewById(R.id.ll_photo);
	}

	private void initGridView(final String[] urls) {
		int size = 0;
		if (urls.length > 5) {
			size = 5;
		} else {
			size = urls.length;
		}
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		float density = dm.density;
		int allWidth = (int) (55 * size * density);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
						42 * size, getResources().getDisplayMetrics()),
				LinearLayout.LayoutParams.FILL_PARENT);
		mGvAddPhoto.setLayoutParams(params);
		mGvAddPhoto.setColumnWidth((int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 42, getResources()
						.getDisplayMetrics()));
		mGvAddPhoto.setHorizontalSpacing(1);
		mGvAddPhoto.setStretchMode(GridView.NO_STRETCH);
		mGvAddPhoto.setNumColumns(size);
		String[] url = new String[5];
		if (urls.length > 5) {
			for (int i = 0; i < url.length; i++) {
				url[i] = urls[i];
			}
			mGvAddPhoto.setAdapter(new MyGridAdapter(url, this));
		} else {
			mGvAddPhoto.setAdapter(new MyGridAdapter(urls, this));
		}
		mGvAddPhoto
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						imageBrower(position, urls);
					}
				});
	}

	private void imageBrower(int position, String[] urls) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
		intent.putExtra(ConstantUtil.EXTRA_IMAGE_URLS, urls);
		intent.putExtra(ConstantUtil.EXTRA_IMAGE_INDEX, position);
		startActivity(intent);
	}

	private void init() {
		setActionBarTitle("货盘详情");
		findViewById(R.id.tv_show_my_quote_pop).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_quote_to_confirm, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_show_my_quote_pop:
			PopupWindowUtil.showPopup(this,
					R.layout.activity_my_quote_sea_transport_fcl);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (PopupWindowUtil.getPopupIsShowing()) {
				PopupWindowUtil.dismissPopup();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

	private String formatDate(String createtime) {
		Date date = new Date(Long.parseLong(createtime)*1000);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"MM-dd HH:mm");
		return simpleDateFormat.format(date);
	}
}
