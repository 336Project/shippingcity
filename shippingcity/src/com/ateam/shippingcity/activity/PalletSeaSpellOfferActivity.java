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
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * 海运杂箱报价
 * @version 
 * @create_date 2015-3-30上午9:30:56
 */
public class PalletSeaSpellOfferActivity extends HBaseActivity implements OnClickListener{

	private EditText mEtMoney;
	private EditText mEtAddInform;
	private Button mBtnHeavyCargo;
	private Button mBtnLightCargo;
	private Button mBtnCommit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("报价");
		setBaseContentView(R.layout.activity_pallet_spell_offer);
		initView();
		initData();
	}
	
	/**
	 * 控件实例化
	 */
	private void initView(){
		mEtMoney=(EditText)findViewById(R.id.et_money);
		mEtAddInform=(EditText)findViewById(R.id.et_addInform);
		mBtnHeavyCargo=(Button)findViewById(R.id.btn_heavyCargo);
		mBtnLightCargo=(Button)findViewById(R.id.btn_lightCargo);
		mBtnCommit=(Button)findViewById(R.id.btn_commit);
	}
	
	/**
	 * 重新提交数据
	 */
	private void initData(){
		mBtnHeavyCargo.setOnClickListener(this);
		mBtnLightCargo.setOnClickListener(this);
		mBtnCommit.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_heavyCargo:
			
			break;
		case R.id.btn_lightCargo:
			
			break;
		case R.id.btn_commit:
	
			break;

		default:
			break;
		}
	}

}
