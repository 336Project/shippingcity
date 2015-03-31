package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.R.menu;
import com.ateam.shippingcity.constant.MyConstant;
import com.ateam.shippingcity.widget.weinxinImageShow.ImagePagerActivity;
import com.ateam.shippingcity.widget.weinxinImageShow.MyGridAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyQuoteHistoryActivity extends HBaseActivity {

	private TextView tv_mode_of_transportation; //运输方式
	private TextView tv_placeBegin; //起点
	private TextView tv_placeEnd; //终点
	private TextView tv_transportTimeBegin;//运输开始时间
	private TextView tv_transportTimeEnd;//运输结束时间
	private TextView tv_palletDescribe; //货盘描述
	private TextView tv_remark;//备注
	private TextView tv_quotation_deadline;//报价截止日期
	private ImageView iv_winType;//中标状态
	String[] urls = {
            "http://img0.bdstatic.com/img/image/shouye/leimu/mingxing2.jpg",
            "http://c.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=cdab1512d000baa1be2c44b3772bc82f/91529822720e0cf3855c96050b46f21fbf09aaa1.jpg",
            "http://g.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=ccd33b46d53f8794d7ff4b26e2207fc9/0d338744ebf81a4c0f993437d62a6059242da6a1.jpg",
            "http://f.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=6b62f61bac6eddc422e7b7f309e0c7c0/6159252dd42a2834510deef55ab5c9ea14cebfa1.jpg",
            "http://c.hiphotos.bdimg.com/album/s%3D900%3Bq%3D90/sign=b8658f17f3d3572c62e290dcba28121a/5fdf8db1cb134954bb97309a574e9258d0094a47.jpg",
            "http://g.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=e58fb67bc8ea15ce45eee301863b4bce/a5c27d1ed21b0ef4fd6140a0dcc451da80cb3e47.jpg"
    };
	private GridView mGvAddPhoto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_my_quote_history);
		init();
		initView();
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
	}
	private void initView() {
		tv_mode_of_transportation = (TextView) findViewById(R.id.tv_mode_of_transportation);
		tv_placeBegin = (TextView) findViewById(R.id.tv_placeBegin);
		tv_placeEnd = (TextView) findViewById(R.id.tv_placeEnd);
		tv_transportTimeBegin = (TextView) findViewById(R.id.tv_transportTimeBegin);
		tv_transportTimeEnd = (TextView) findViewById(R.id.tv_transportTimeEnd);
		tv_palletDescribe = (TextView) findViewById(R.id.tv_palletDescribe);
		tv_remark = (TextView) findViewById(R.id.tv_remark);
		tv_quotation_deadline = (TextView) findViewById(R.id.tv_quotation_deadline);
		iv_winType = (ImageView) findViewById(R.id.iv_winType);
		mGvAddPhoto=(GridView)findViewById(R.id.gv_addPhoto);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.my_quote_history, menu);
		return true;
	}

}
