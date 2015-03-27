package com.ateam.shippingcity;


import com.ateam.shippingcity.activity.HBaseActivity;
import com.ateam.shippingcity.fragment.HomeFragment;
import com.ateam.shippingcity.fragment.MyQuoteFragment;
import com.ateam.shippingcity.fragment.PalletDistrictFragment;
import com.ateam.shippingcity.utils.AppManager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
/**
 * 
 * @author 李晓伟
 * @Create_date 2015-3-27 上午10:01:15
 * @Version 
 * @TODO
 */
public class MainActivity extends HBaseActivity implements OnClickListener{
	private long currTime=0;
	private HomeFragment mHomeFragment;
	private MyQuoteFragment mQuoteFragment;
	private PalletDistrictFragment mDistrictFragment;
	private Fragment mCurrFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("航运城");
		getRightIcon().setVisibility(View.INVISIBLE);
		getLeftIcon().setOnClickListener(this);
		setBaseContentView(R.layout.activity_main);
		init();
	}
	
	
	private void init() {
		findViewById(R.id.txt_home).setOnClickListener(this);
		findViewById(R.id.txt_my_quote).setOnClickListener(this);
		findViewById(R.id.txt_pallet_district).setOnClickListener(this);
		mHomeFragment=new HomeFragment();
		FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.layout_main_content, mHomeFragment);
		transaction.commit();
		mCurrFragment=mHomeFragment;
	}

	private void switchContent(Fragment to){
		if(mCurrFragment!=to){
			FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
			if(!to.isAdded()){
				transaction.hide(mCurrFragment).add(R.id.layout_main_content, to).commit();
			}else{
				transaction.hide(mCurrFragment).show(to).commit();
			}
			mCurrFragment=to;
		}
	}

	@Override
	public void onBackPressed() {
		if(System.currentTimeMillis()-currTime>2000){
			currTime=System.currentTimeMillis();
			showMsg(MainActivity.this, "再按一次退出程序");
		}else{
			AppManager.getInstance().ExitApp();
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_home://首页
			if(mHomeFragment==null){
				mHomeFragment=new HomeFragment();
			}
			setActionBarTitle("航运城");
			switchContent(mHomeFragment);
			break;
		case R.id.txt_my_quote://我的报价
			if(mQuoteFragment==null){
				mQuoteFragment=new MyQuoteFragment();
			}
			setActionBarTitle("我的报价");
			switchContent(mQuoteFragment);
			break;
		case R.id.txt_pallet_district://货盘区
			if(mDistrictFragment==null){
				mDistrictFragment=new PalletDistrictFragment();
			}
			setActionBarTitle("货盘区");
			switchContent(mDistrictFragment);
			break;
		case R.id.iv_left_icon:
			PopupWindow pop=new PopupWindow(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
			pop.setContentView(LayoutInflater.from(this).inflate(R.layout.item_pop_personl_center, null));
			pop.setBackgroundDrawable(new ColorDrawable());
			pop.setOutsideTouchable(true);
			pop.setTouchable(true);
			pop.setFocusable(true);
			pop.showAtLocation(getWindow().getDecorView(), Gravity.NO_GRAVITY, 0, 0);
			break;
		default:
			break;
		}
	}
	
}
