package com.ateam.shippingcity.activity;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 空运报价
 * @version 
 * @create_date 2015-3-30上午10:35:31
 */
public class PalletAirOfferActivity extends HBaseActivity {

	private EditText mEtMoney;//报价金额
	private EditText mEtAddInform;//附加信息
	private Button mBtnCommit;//提交按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("报价");
		setBaseContentView(R.layout.activity_pallet_air_offer);
		initView();
		initData();
	}
	
	/**
	 * 控件实例化
	 */
	private void initView(){
		mEtMoney=(EditText)findViewById(R.id.et_money);
		mEtAddInform=(EditText)findViewById(R.id.et_addInform);
		mBtnCommit=(Button)findViewById(R.id.btn_commit);
	}
	
	/**
	 * 重新提交数据
	 */
	private void initData(){
		mBtnCommit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
