package com.ateam.shippingcity.activity;

import java.lang.reflect.Type;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PalletTransportAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 海运散杂货报价
 * @version 
 * @create_date 2015-3-30上午10:15:48
 */
public class PalletSeaDiffOfferActivity extends HBaseActivity {

	private EditText mEtOfferContent;//报价内容
	private Button mBtnCommit;//提交按钮
	
	private PalletTransportAccess<List<PalletTransport>> access;
	private PalletTransport mPallet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("报价");
		setBaseContentView(R.layout.activity_pallet_diff_offer);
		initView();
		initData();
	}
	
	/**
	 * 控件实例化
	 */
	private void initView(){
		mPallet=(PalletTransport) getIntent().getSerializableExtra("palletTransport");
		mEtOfferContent=(EditText)findViewById(R.id.et_offerContent);
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
				if(mEtOfferContent.getText().toString().equals("")){
					MyToast.showShort(PalletSeaDiffOfferActivity.this, "报价内容不能为空!");
					return;
				}
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
							MyToast.showShort(PalletSeaDiffOfferActivity.this, result.getMessage());
							finish();
						}
					}
					@Override
					public void onFail(Context c, String errorMsg) {
						// TODO Auto-generated method stub
						super.onFail(c, errorMsg);
						jumpToResult("fial");
						finish();
					}
				};
				access = new PalletTransportAccess<List<PalletTransport>>(
						PalletSeaDiffOfferActivity.this, requestCallback);
				access.seaDiffOfferCommit(
						mBaseApp.getUserssid(), 
						mPallet.id,
						mEtOfferContent.getText().toString());
			}
		});
	}
	
	private void jumpToResult(String result){
		Intent intent=new Intent(this,PalletOfferResultActivity.class);
		intent.putExtra("result", result);
		intent.putExtra("offerContent", mEtOfferContent.getText().toString());
		intent.putExtra("palletTransport", mPallet);
		startActivity(intent);
	}

}
