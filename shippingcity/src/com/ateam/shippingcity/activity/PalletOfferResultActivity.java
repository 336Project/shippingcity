package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 报价结果界面
 * @version 
 * @create_date 2015-3-30上午10:53:20
 */
public class PalletOfferResultActivity extends HBaseActivity {

	private ImageView mIvSucOrFail;//成功或是失败图片
	private TextView mTvSucOrFailOne;//成功失败提示一
	private TextView mTvSucOrFailTwo;//成功失败提示二
	private Button mBtnReCommit;//重新提交按钮
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("报价成功");
		setBaseContentView(R.layout.activity_pallet_offer_result);
		initView();
		initData();
	}
	
	private void initView(){
		intent=getIntent();
		mIvSucOrFail=(ImageView)findViewById(R.id.iv_sucOrFail);
		mTvSucOrFailOne=(TextView)findViewById(R.id.tv_sucOrFailOne);
		mTvSucOrFailTwo=(TextView)findViewById(R.id.tv_sucOrFailTwo);
		mBtnReCommit=(Button)findViewById(R.id.btn_reCommit);
		if(!intent.getStringExtra("result").equals("success")){
			setActionBarTitle("报价失败");
			mIvSucOrFail.setBackgroundDrawable(getResources().getDrawable(R.drawable.failure_to_submit));
		}else{
			mTvSucOrFailOne.setText(getResources().getString(R.string.success_one));
			mTvSucOrFailTwo.setText(getResources().getString(R.string.success_two));
			mBtnReCommit.setVisibility(View.GONE);
		}
	}
	
	/**
	 * 重新提交数据
	 */
	private void initData(){
		mBtnReCommit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
