package com.ateam.shippingcity.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.R;

import com.ateam.shippingcity.access.PalletTransportAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;
import com.ateam.shippingcity.widget.HAutoCompleteTextView;
import com.ateam.shippingcity.widget.PalletWholeOfferItem;
import com.ateam.shippingcity.R.layout;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * 海运整箱报价
 * @version 
 * @create_date 2015-3-30上午9:30:38
 */
public class PalletSeaWholeOfferActivity extends HBaseActivity {

	private HAutoCompleteTextView mEtShipCompany;//公司名称
	private EditText mEtAddInform;//附加信息
	private Button mBtnCommit;//提交按钮
	
	private ArrayList<EditText> mBoxList=new ArrayList<EditText>();
	private PalletTransportAccess<List<PalletTransport>> access;
	private PalletTransportAccess<ArrayList<String>> mGetCompanyAccess;
	private PalletTransport mPallet;
	private LinearLayout mLayoutAddBox;
	private View mLine=null;//上一个box item的线
	private StringBuffer offerList=new StringBuffer();//报价字符串
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
		mPallet=(PalletTransport) getIntent().getSerializableExtra("palletTransport");
		mEtShipCompany=(HAutoCompleteTextView)findViewById(R.id.et_shipCompany);
		mEtAddInform=(EditText)findViewById(R.id.et_addInform);
		mBtnCommit=(Button)findViewById(R.id.btn_commit);
		mLayoutAddBox=(LinearLayout)findViewById(R.id.layout_addBox);
		//是否显示 货箱
		getCompanyInform();
		addBox("20GP","20'GP");
		addBox("40GP","40'GP");
		addBox("40HQ","40'HQ");
		addBox("45HQ","45'HQ");
		addBox("20RF","20'RF");
		addBox("40RF","40'RF");
		addBox("20OT","20'OT");
		addBox("40OT","40'OT");
		addBox("20FR","20'FR");
		addBox("20TK","20'TK");
		addBox("20SP","20'SP");
		addBox("40SP","40'SP");
		addBox("40RH","40'RH");
	}
	
	private void addBox(String boxType,String boxTitle){
		if(mPallet.boxtype.contains(boxType)){
			if(mLine!=null){
				mLine.setVisibility(View.VISIBLE);
			}
			PalletWholeOfferItem item=new PalletWholeOfferItem(this);
			item.setTvBox(boxTitle);
			mLine=item.getLine();
			mBoxList.add(item.getBox());
			mLayoutAddBox.addView(item);
		}
	}
	
	/**
	 * 重新提交数据
	 */
	private void initData(){
		mBtnCommit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(mEtShipCompany.getText().equals("")){
					MyToast.showShort(PalletSeaWholeOfferActivity.this, "船公司名称不能为空！");
					return;
				}
				offerList=new StringBuffer();
				for (int i = 0; i < mBoxList.size(); i++) {
					if(i<(mBoxList.size()-1)){
						offerList.append(mBoxList.get(i).getText().toString()+"|");
					}else{
						offerList.append(mBoxList.get(i).getText().toString());
					}
					if(mBoxList.get(i).getText().toString().equals("")){
						MyToast.showShort(PalletSeaWholeOfferActivity.this, "报价不能为空！");
						return;
					}
				}
				commitData();
			}
		});
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
				if(result.getStatusCode().equals("200")){
					jumpToResult("success");
					finish();
				}
				if(result.getStatusCode().equals("500")){
					jumpToResult("fial");
					MyToast.showShort(PalletSeaWholeOfferActivity.this, result.getMessage());
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
		access.seaWholeOfferCommit(
				mBaseApp.getUserssid(), 
				offerList.toString(),
				mPallet.id,
				mEtShipCompany.getText().toString(),
				mEtAddInform.getText().toString());
	}
	
	/** 
     * 初始化AutoCompleteTextView，最多显示5项提示，使 
     * AutoCompleteTextView在一开始获得焦点时自动提示 
     * @param field 保存在sharedPreference中的字段名 
     * @param auto 要操作的AutoCompleteTextView 
     */  
    private void initAutoComplete(HAutoCompleteTextView auto,ArrayList<String> dataList) {  
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  
                android.R.layout.simple_dropdown_item_1line, dataList);  
        //只保留最近的50条的记录  
        auto.setAdapter(adapter);  
        auto.setDropDownHeight(400);  
        auto.setThreshold(1);  
        auto.setOnFocusChangeListener(new OnFocusChangeListener() {  
            @Override  
            public void onFocusChange(View v, boolean hasFocus) {  
            	HAutoCompleteTextView view = (HAutoCompleteTextView) v;  
                if (hasFocus) {  
                        view.showDropDown();  
                }  
            }  
        });  
    }  
    
    /**
     * 获取船公司信息
     */
    private void getCompanyInform(){
    	HRequestCallback<Respond<ArrayList<String>>> requestCallback = new HRequestCallback<Respond<ArrayList<String>>>() {

			@SuppressWarnings("unchecked")
			@Override
			public Respond<ArrayList<String>> parseJson(String jsonStr) {
				Type type = new com.google.gson.reflect.TypeToken<Respond<ArrayList<String>>>() {
				}.getType();
				return (Respond<ArrayList<String>>) JSONParse.jsonToObject(
						jsonStr, type);
			}

			@Override
			public void onSuccess(Respond<ArrayList<String>> result) {
				initAutoComplete(mEtShipCompany,result.getDatas());
			}
		};
		mGetCompanyAccess = new PalletTransportAccess<ArrayList<String>>(
				this, requestCallback);
		mGetCompanyAccess.setIsShow(false);
		mGetCompanyAccess.getBootCompany(mBaseApp.getUserssid());
    }
    
    private void jumpToResult(String result){
		Intent intent=new Intent(this,PalletOfferResultActivity.class);
		intent.putExtra("result", result);
		intent.putExtra("TGP", offerList.toString());
		intent.putExtra("shipCompany", mEtShipCompany.getText().toString());
		intent.putExtra("addInform", mEtAddInform.getText().toString());
		intent.putExtra("palletTransport", mPallet);
		startActivity(intent);
	}
    
}
