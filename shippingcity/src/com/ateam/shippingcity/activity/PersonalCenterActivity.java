package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
/**
 * 
 * @author 李晓伟
 * 2015-3-27 下午1:52:57
 * @TODO 个人中心
 */
public class PersonalCenterActivity extends Activity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.item_pop_personl_center);
		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
		getWindow().setGravity(Gravity.TOP);
		
		init();
	}

	private void init() {
		findViewById(R.id.layout_personal_info).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_personal_info:
			
			break;

		default:
			break;
		}
	}
}
