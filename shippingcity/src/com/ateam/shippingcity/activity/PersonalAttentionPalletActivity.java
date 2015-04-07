package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.fragment.PalletFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 
 * @author 李晓伟
 * 2015-4-7 下午8:00:12
 * @TODO 我关注的货盘
 */
public class PersonalAttentionPalletActivity extends HBaseActivity implements OnClickListener{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getLeftIcon().setOnClickListener(this);
		getLeftIcon().setImageResource(R.drawable.home_member_center_icon);
		setActionBarTitle("我关注的货盘");
		init();
	}

	private void init() {
		FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_content, new PalletFragment());
		transaction.commit();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_left_icon://个人中心
			if(TextUtils.isEmpty(mBaseApp.getUserssid())){
				jump(this, PersonalLoginActivity.class);
			}else{
				jump(this, PersonalCenterActivity.class);
			}
			break;
		default:
			break;
			
		}
	}
}
