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
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * 海运拼箱报价
 * @version 
 * @create_date 2015-3-30上午9:30:56
 */
public class PalletSeaSpellOfferActivity extends HBaseActivity implements OnClickListener{

	private EditText mEtMoney;
	private EditText mEtAddInform;
	private Button mBtnHeavyCargo;
	private Button mBtnLightCargo;
	private Button mBtnCommit;
	
	private boolean isHeavy=true;
	private boolean isLight=false;

	private PalletTransportAccess<List<PalletTransport>> access;
	private PalletTransport mPallet;
	private int type;
	
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
		mPallet=(PalletTransport) getIntent().getSerializableExtra("palletTransport");
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
			mBtnHeavyCargo.setBackgroundDrawable(getResources().getDrawable(R.drawable.quotes_select_weight_icon));
			mBtnLightCargo.setBackgroundDrawable(getResources().getDrawable(R.drawable.quotes_unselected_weight_icon));
			isHeavy=true;
			isLight=false;
			break;
		case R.id.btn_lightCargo:
			mBtnLightCargo.setBackgroundDrawable(getResources().getDrawable(R.drawable.quotes_select_weight_icon));
			mBtnHeavyCargo.setBackgroundDrawable(getResources().getDrawable(R.drawable.quotes_unselected_weight_icon));
			isHeavy=false;
			isLight=true;
			break;
		case R.id.btn_commit:
			commitData();
			break;

		default:
			break;
		}
	}
	
	/**
	 * 提交数据
	 */
	private void commitData(){
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
//				Log.e("", "" + result.toString());
				if(result.getStatusCode().equals("200")){
					jumpToResult("success");
					finish();
				}
				if(result.getStatusCode().equals("500")){
					jumpToResult("fial");
					MyToast.showShort(PalletSeaSpellOfferActivity.this, result.getMessage());
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
				this, requestCallback);
		type=isHeavy==true?1:2;
		access.seaSpellOfferCommit(
				mBaseApp.getUserssid(), 
				mEtMoney.getText().toString(), 
				mPallet.id, 
				mEtAddInform.getText().toString(), 
				type+"");
	}
	
	private void jumpToResult(String result){
		Intent intent=new Intent(this,PalletOfferResultActivity.class);
		intent.putExtra("result", result);
		intent.putExtra("money", mEtMoney.getText().toString());
		intent.putExtra("type", type+"");
		intent.putExtra("addInform", mEtAddInform.getText().toString());
		intent.putExtra("palletTransport", mPallet);
		startActivity(intent);
	}

}
