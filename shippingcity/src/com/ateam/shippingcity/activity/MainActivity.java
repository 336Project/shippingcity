package com.ateam.shippingcity.activity;


import com.ateam.shippingcity.HomeActivity;
import com.ateam.shippingcity.R;
import com.ateam.shippingcity.fragment.MyQuoteFragment;
import com.ateam.shippingcity.fragment.PalletFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
/**
 * 
 * @author 李晓伟
 * @Create_date 2015-3-27 上午10:01:15
 * @Version 
 * @TODO
 */
public class MainActivity extends HBaseActivity implements OnClickListener{
	public static final String KEY_TYPE="type";
	public static final int TYPE_PALLET=1;//货盘区
	public static final int TYPE_QUOTE=2;//我的报价
	private MyQuoteFragment mQuoteFragment;
	private PalletFragment mDistrictFragment;
	private Fragment mCurrFragment;
	
	private TextView mTxtHome;
	private TextView mTxtQuote;
	private TextView mTxtPallet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getRightIcon().setVisibility(View.INVISIBLE);
		getLeftIcon().setOnClickListener(this);
		getLeftIcon().setImageResource(R.drawable.home_member_center_icon);
		setBaseContentView(R.layout.activity_main);
		init();
	}
	
	
	private void init() {
		mTxtHome=(TextView) findViewById(R.id.txt_home);
		mTxtHome.setOnClickListener(this);
		mTxtQuote=(TextView) findViewById(R.id.txt_my_quote);
		mTxtQuote.setOnClickListener(this);
		mTxtPallet=(TextView) findViewById(R.id.txt_pallet_district);
		mTxtPallet.setOnClickListener(this);
		int type=getIntent().getIntExtra(KEY_TYPE, TYPE_PALLET);
		FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
		if(type==TYPE_PALLET){
			setSelect(mTxtPallet);
			setActionBarTitle("货盘区");
			mDistrictFragment=new PalletFragment();
			transaction.add(R.id.layout_main_content, mDistrictFragment);
			transaction.commit();
			mCurrFragment=mDistrictFragment;
		}else{
			setSelect(mTxtQuote);
			setActionBarTitle("我的报价");
			getRightTxt().setVisibility(View.VISIBLE);
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
			setSelect(mTxtHome);
			startActivity(new Intent(this, HomeActivity.class));
			break;
		case R.id.txt_my_quote://我的报价
			if(TextUtils.isEmpty(mBaseApp.getUserssid())){
				jump(this, PersonalLoginActivity.class);
			}else{
				setSelect(mTxtQuote);
				if(mQuoteFragment==null){
					mQuoteFragment=new MyQuoteFragment();
				}
				getRightIcon().setVisibility(View.VISIBLE);
				getRightTxt().setVisibility(View.VISIBLE);
				setActionBarTitle("我的报价");
				switchContent(mQuoteFragment);
			}
			break;
		case R.id.txt_pallet_district://货盘区
			setSelect(mTxtPallet);
			if(mDistrictFragment==null){
				mDistrictFragment=new PalletFragment();
			}
			getRightIcon().setVisibility(View.INVISIBLE);
			getRightTxt().setVisibility(View.GONE);
			setActionBarTitle("货盘区");
			switchContent(mDistrictFragment);
			break;
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
	/**
	 * 
	 * 2015-4-4 下午1:11:07
	 * @param v
	 * @TODO 设置选中状态
	 */
	public void setSelect(TextView currView){
		if(currView==mTxtHome){
			mTxtHome.setSelected(true);
			mTxtHome.setTextColor(Color.rgb(253,173,52));
			mTxtPallet.setSelected(false);
			mTxtPallet.setTextColor(Color.rgb(150,150,150));
			mTxtQuote.setSelected(false);
			mTxtQuote.setTextColor(Color.rgb(150,150,150));
		}else if(currView==mTxtPallet){
			mTxtHome.setSelected(false);
			mTxtHome.setTextColor(Color.rgb(150,150,150));
			mTxtPallet.setSelected(true);
			mTxtPallet.setTextColor(Color.rgb(253,173,52));
			mTxtQuote.setSelected(false);
			mTxtQuote.setTextColor(Color.rgb(150,150,150));
		}else if(currView==mTxtQuote){
			mTxtHome.setSelected(false);
			mTxtHome.setTextColor(Color.rgb(150,150,150));
			mTxtPallet.setSelected(false);
			mTxtPallet.setTextColor(Color.rgb(150,150,150));
			mTxtQuote.setSelected(true);
			mTxtQuote.setTextColor(Color.rgb(253,173,52));
		}
	}
}
