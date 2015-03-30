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

/**
 * 海运整箱报价
 * @version 
 * @create_date 2015-3-30上午9:30:38
 */
public class PalletSeaWholeOfferActivity extends HBaseActivity {

	private EditText mEtShipCompany;//公司名称
	private EditText mEtTGP;//20gp报价
	private EditText mEtFGP;//40gp报价
	private EditText mEtFHP;//40hp报价
	private EditText mEtAddInform;//附加信息
	private Button mBtnCommit;//提交按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("报价");
		setBaseContentView(R.layout.activity_pallet_offer);
		initView();
		initData();
	}

	/**
	 * 控件实例化
	 */
	private void initView(){
		mEtShipCompany=(EditText)findViewById(R.id.et_shipCompany);
		mEtTGP=(EditText)findViewById(R.id.et_TGP);
		mEtFGP=(EditText)findViewById(R.id.et_FGP);
		mEtFHP=(EditText)findViewById(R.id.et_FHP);
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
