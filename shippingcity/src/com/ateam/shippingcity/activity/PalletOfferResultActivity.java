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
	
	private PalletTransportAccess<List<PalletTransport>> access;
	private PalletTransport mPallet;

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
		mPallet=(PalletTransport) intent.getSerializableExtra("palletTransport");
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
						if(result.getStatusCode().equals("200")){
							jumpToResult("success");
							finish();
						}
						if(result.getStatusCode().equals("500")){
							MyToast.showShort(PalletOfferResultActivity.this, result.getMessage());
						}
					}
				};
				access = new PalletTransportAccess<List<PalletTransport>>(
						PalletOfferResultActivity.this, requestCallback);
				//判断当前的跳转来的页面为哪个页面，进行选择相应的提交方法
				if(mPallet.shipping_type.toString().equals("1")){
					if(mPallet.shipment_type.equals("1")){//海运整箱
						access.seaWholeOfferCommit(
								mBaseApp.getUserssid(), 
								intent.getStringExtra("TGP"),
								mPallet.id,
								intent.getStringExtra("shipCompany"),
								intent.getStringExtra("addInform"));
					}else if(mPallet.shipment_type.equals("3")){//海运拼箱
						access.seaSpellOfferCommit(
						mBaseApp.getUserssid(), 
						intent.getStringExtra("money"), 
						mPallet.id, 
						intent.getStringExtra("addInform"), 
						intent.getStringExtra("type"));
					}else{//海运杂货箱
						access.seaDiffOfferCommit(
						mBaseApp.getUserssid(), 
						mPallet.id,
						intent.getStringExtra("offerContent"));
					}
				}else if(mPallet.shipping_type.toString().equals("2")){//空运
					access.airAndlordOfferCommit(
					mBaseApp.getUserssid(), 
					intent.getStringExtra("money"), 
					mPallet.id, 
					intent.getStringExtra("addInform"));
				}else{
					if(mPallet.shipment_type.equals("1")){//陆运整箱
						access.lordWholeOfferCommit(
						mBaseApp.getUserssid(), 
						intent.getStringExtra("TGP"),
						mPallet.id,
						intent.getStringExtra("addInform"));
					}else if(mPallet.shipment_type.equals("2")){//陆运杂货箱
						access.airAndlordOfferCommit(
								mBaseApp.getUserssid(), 
								intent.getStringExtra("money"), 
								mPallet.id, 
								intent.getStringExtra("addInform"));
					}
				}
			}
		});
	}
	
	private void jumpToResult(String result){
		Intent intent=new Intent(this,PalletOfferResultActivity.class);
		intent.putExtra("result", result);
		startActivity(intent);
	}

}
