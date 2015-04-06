package com.ateam.shippingcity.activity;

import java.lang.reflect.Type;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.R.layout;
import com.ateam.shippingcity.access.PalletTransportAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
	
	private PalletTransportAccess<List<PalletTransport>> access;
	private PalletTransport mPallet;

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
		mPallet=(PalletTransport) getIntent().getSerializableExtra("palletTransport");
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
				HRequestCallback<Respond<List<PalletTransport>>> requestCallback = new HRequestCallback<Respond<List<PalletTransport>>>() {

					@SuppressWarnings("unchecked")
					@Override
					public Respond<List<PalletTransport>> parseJson(String jsonStr) {
						Type type = new com.google.gson.reflect.TypeToken<Respond<List<PalletTransport>>>() {
						}.getType();
						return (Respond<List<PalletTransport>>) JSONParse.jsonToObject(
								jsonStr, type);
					}

					@Override
					public void onSuccess(Respond<List<PalletTransport>> result) {
//						Log.e("", "" + result.toString());
						if(result.getStatusCode().equals("200")){
							jumpToResult("success");
							finish();
						}
						if(result.getStatusCode().equals("500")){
							jumpToResult("fial");
						}
					}
				};
				access = new PalletTransportAccess<List<PalletTransport>>(
						PalletAirOfferActivity.this, requestCallback);
				access.airAndlordOfferCommit(
						mBaseApp.getUserssid(), 
						mEtMoney.getText().toString(), 
						mPallet.id, 
						mEtAddInform.getText().toString());
			}
		});
	}
	
	private void jumpToResult(String result){
		Intent intent=new Intent(this,PalletOfferResultActivity.class);
		intent.putExtra("result", result);
		startActivity(intent);
	}

}
