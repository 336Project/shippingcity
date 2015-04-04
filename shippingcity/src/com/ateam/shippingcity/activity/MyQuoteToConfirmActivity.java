package com.ateam.shippingcity.activity;

import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.R.menu;
import com.ateam.shippingcity.access.MyQuoteAccess;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.constant.MyConstant;
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
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

public class MyQuoteToConfirmActivity extends HBaseActivity implements OnClickListener{
	String[] urls = {
            "http://img0.bdstatic.com/img/image/shouye/leimu/mingxing2.jpg",
            "http://c.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=cdab1512d000baa1be2c44b3772bc82f/91529822720e0cf3855c96050b46f21fbf09aaa1.jpg",
            "http://g.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=ccd33b46d53f8794d7ff4b26e2207fc9/0d338744ebf81a4c0f993437d62a6059242da6a1.jpg",
            "http://f.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=6b62f61bac6eddc422e7b7f309e0c7c0/6159252dd42a2834510deef55ab5c9ea14cebfa1.jpg",
            "http://c.hiphotos.bdimg.com/album/s%3D900%3Bq%3D90/sign=b8658f17f3d3572c62e290dcba28121a/5fdf8db1cb134954bb97309a574e9258d0094a47.jpg",
            "http://g.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=e58fb67bc8ea15ce45eee301863b4bce/a5c27d1ed21b0ef4fd6140a0dcc451da80cb3e47.jpg"
    };
	private GridView mGvAddPhoto;
	private String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_my_quote_sea_transport_fcl);
		init();
		intIntent();
		initView();
//		request();
	}
	
	private void request() {
		HRequestCallback<Respond<List<MyQuoteToConfirmDetail>>> requestCallback=new HRequestCallback<Respond<List<MyQuoteToConfirmDetail>>>() {
			
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
				onLoadFail();
			}
			@SuppressWarnings("unchecked")
			@Override
			public Respond<List<MyQuoteToConfirmDetail>> parseJson(String jsonStr) {
				java.lang.reflect.Type type = new TypeToken<Respond<List<MyQuoteToConfirmDetail>>>() {
				}.getType();
				return (Respond<List<MyQuoteToConfirmDetail>>) JSONParse.jsonToObject(jsonStr, type);
			}
			@Override
			public void onSuccess(Respond<List<MyQuoteToConfirmDetail>> result) {
				if(result.isSuccess()){
					
				}
			}
		};
		MyQuoteAccess<List<MyQuoteToConfirmDetail>> access=new MyQuoteAccess<List<MyQuoteToConfirmDetail>>(this, requestCallback);
		access.getMyQuoteDetail(mBaseApp.getUserssid(), id);
	}
	
	private void intIntent() {
		Intent intent = getIntent();
		id = intent.getStringExtra("id");
	}
	private void initView() {
		mGvAddPhoto=(GridView)findViewById(R.id.gv_addPhoto);
		initGridView();
	}
	private void initGridView() {
		int size=0;
		if(urls.length>5){
			size=5;
		}else{
			size=urls.length;
		}
		DisplayMetrics dm = new DisplayMetrics();  
        getWindowManager().getDefaultDisplay().getMetrics(dm);  
        float density = dm.density;  
        int allWidth = (int) (55 * size * density);  
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  
        		(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42*size, getResources().getDisplayMetrics()), 
                LinearLayout.LayoutParams.FILL_PARENT);  
        mGvAddPhoto.setLayoutParams(params);  
        mGvAddPhoto.setColumnWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42, getResources().getDisplayMetrics()));  
        mGvAddPhoto.setHorizontalSpacing(1);  
        mGvAddPhoto.setStretchMode(GridView.NO_STRETCH);  
        mGvAddPhoto.setNumColumns(size); 
        String[] url=new String[5];
		if(urls.length>5){
			for (int i = 0; i < url.length; i++) {
				url[i]=urls[i];
			}
			mGvAddPhoto.setAdapter(new MyGridAdapter(url, this));
		}else{
			mGvAddPhoto.setAdapter(new MyGridAdapter(urls, this));
		}
		mGvAddPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				imageBrower(position,urls);
			}
		});
	}
	private void imageBrower(int position, String[] urls) {
		Intent intent = new Intent(this, ImagePagerActivity.class);
		// 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
		intent.putExtra(MyConstant.EXTRA_IMAGE_URLS, urls);
		intent.putExtra(MyConstant.EXTRA_IMAGE_INDEX, position);
		startActivity(intent);
	}
	private void init() {
		setActionBarTitle("货盘详情");
		findViewById(R.id.tv_show_my_quote_pop).setOnClickListener(this);
		PopupWindowUtil.initPopup(this, R.layout.pop_my_quote_1);
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
			PopupWindowUtil.showPopup(this, R.layout.activity_my_quote_sea_transport_fcl);
			break;

		default:
			break;
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			if(PopupWindowUtil.getPopupIsShowing()){
				PopupWindowUtil.dismissPopup();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}

}
