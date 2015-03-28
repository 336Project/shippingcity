package com.ateam.shippingcity.activity;


import com.ateam.shippingcity.HomeActivity;
import com.ateam.shippingcity.R;
import com.ateam.shippingcity.fragment.MyQuoteFragment;
import com.ateam.shippingcity.fragment.PalletFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
/**
 * 
 * @author 李晓伟
 * @Create_date 2015-3-27 上午10:01:15
 * @Version 
 * @TODO
 */
public class PalletAndQuoteCommonActivity extends HBaseActivity implements OnClickListener{
	public static final String KEY_TYPE="type";
	public static final int TYPE_PALLET=1;//货盘区
	public static final int TYPE_QUOTE=2;//我的报价
	private MyQuoteFragment mQuoteFragment;
	private PalletFragment mDistrictFragment;
	private Fragment mCurrFragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getRightIcon().setVisibility(View.INVISIBLE);
		getLeftIcon().setOnClickListener(this);
		setBaseContentView(R.layout.activity_main);
		init();
	}
	
	
	private void init() {
		findViewById(R.id.txt_home).setOnClickListener(this);
		findViewById(R.id.txt_my_quote).setOnClickListener(this);
		findViewById(R.id.txt_pallet_district).setOnClickListener(this);
		int type=getIntent().getIntExtra(KEY_TYPE, TYPE_PALLET);
		FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
		if(type==TYPE_PALLET){
			setActionBarTitle("货盘区");
			mDistrictFragment=new PalletFragment();
			transaction.add(R.id.layout_main_content, mDistrictFragment);
			transaction.commit();
			mCurrFragment=mDistrictFragment;
		}else{
			setActionBarTitle("我的报价");
			mQuoteFragment=new MyQuoteFragment();
			transaction.add(R.id.layout_main_content, mQuoteFragment);
			transaction.commit();
			mCurrFragment=mQuoteFragment;
		}
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
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_home://首页
			startActivity(new Intent(this, HomeActivity.class));
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
				mDistrictFragment=new PalletFragment();
			}
			setActionBarTitle("货盘区");
			switchContent(mDistrictFragment);
			break;
		case R.id.iv_left_icon:
			startActivity(new Intent(this, PersonalCenterActivity.class));
			break;
		default:
			break;
		}
	}
	
}
