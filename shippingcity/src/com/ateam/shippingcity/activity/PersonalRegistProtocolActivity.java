package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.widget.ElasticScrollView;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

/**
 * 
 * @author 李晓伟
 * 2015-3-28 下午2:16:10
 * @TODO 注册协议
 */
public class PersonalRegistProtocolActivity extends HBaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("航运城《软件许可及服务协议》");
		setBaseContentView(R.layout.activity_personal_regist_protocol);
		final ElasticScrollView scrollView=(ElasticScrollView) findViewById(R.id.scroll_view);
		findViewById(R.id.img_back_to_top).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				scrollView.fullScroll(ScrollView.FOCUS_UP);
			}
		});
	}
}
