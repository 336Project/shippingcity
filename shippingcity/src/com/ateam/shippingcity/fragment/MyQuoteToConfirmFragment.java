package com.ateam.shippingcity.fragment;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.MyQuoteAccess;
import com.ateam.shippingcity.access.PalletTransportAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.activity.HBaseActivity;
import com.ateam.shippingcity.activity.MyQuoteConfirmActivity;
import com.ateam.shippingcity.activity.MainActivity;
import com.ateam.shippingcity.activity.PersonalMyIntegralActivity;
import com.ateam.shippingcity.adapter.MyQuoteToConfirmAdapter;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.IntegralRule;
import com.ateam.shippingcity.model.MyQuoteToConfirm;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;
import com.ateam.shippingcity.widget.TextViewPair;

@SuppressLint("ValidFragment")
public class MyQuoteToConfirmFragment extends HBaseXListViewFragment<MyQuoteToConfirm> implements
		OnXListItemClickListener {

	private MyQuoteToConfirmAdapter mAdapter;// 海运list适配器
	private ArrayList<MyQuoteToConfirm> allList= new ArrayList<MyQuoteToConfirm>();
	private ArrayList<MyQuoteToConfirm> seaList= new ArrayList<MyQuoteToConfirm>();
	private ArrayList<MyQuoteToConfirm> airList= new ArrayList<MyQuoteToConfirm>();
	private ArrayList<MyQuoteToConfirm> landList= new ArrayList<MyQuoteToConfirm>();
	private ArrayList<MyQuoteToConfirm> dataList = new ArrayList<MyQuoteToConfirm>();// 要显示的数据
	protected int page_size=10;
	protected int current_page=1;
	private int mode=0;
	private MyQuoteAccess<List<MyQuoteToConfirm>> access;
	public MyQuoteToConfirmFragment() {

	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
			Intent intent = new Intent();
			intent.setClass(getActivity(), MyQuoteConfirmActivity.class);
			String offerid="";
			switch (mode) {
			case 0:
				offerid = allList.get(position).getId();
				break;
			case 1:
				offerid = seaList.get(position).getId();
				break;
			case 2:
				offerid = airList.get(position).getId();
				break;
			case 3:
				offerid = dataList.get(position).getId();
				break;
			default:
				break;
			}
			intent.putExtra("offerid", offerid);
			getActivity().startActivity(intent);
	}
	
	@Override
	public void request() {
		access.getMyQuoteList(mBaseApp.getUserssid(), "0", current_page,
				page_size);
	}
	@Override
	public BaseAdapter getAdapter() {
		return mAdapter;
	}

	@Override
	public List<MyQuoteToConfirm> getDataSource() {
		return dataList;
	}
	@Override
	public void initData() {
		mAdapter = new MyQuoteToConfirmAdapter(getActivity(), dataList);
		setOnXListItemClickListener(this);
		initRequest();
	}

	private void initRequest() {
//		 for (int i = 0; i < 11; i++) {
//		 MyQuoteToConfirm myQuoteToConfirm=new MyQuoteToConfirm();
//		 myQuoteToConfirm.setInitiation("HONGKONG"+i);
//		 dataList.add(myQuoteToConfirm);
//		 }
		HRequestCallback<Respond<List<MyQuoteToConfirm>>> requestCallback = new HRequestCallback<Respond<List<MyQuoteToConfirm>>>() {
			@SuppressWarnings("unchecked")
			@Override
			public Respond<List<MyQuoteToConfirm>> parseJson(String jsonStr) {
				Type type = new com.google.gson.reflect.TypeToken<Respond<List<MyQuoteToConfirm>>>() {
				}.getType();
				return (Respond<List<MyQuoteToConfirm>>) JSONParse.jsonToObject(jsonStr, type);
			}
			@Override
			public void onSuccess(Respond<List<MyQuoteToConfirm>> result) {
				if(result.isSuccess()){
					if(current_page>=1){
						allList.clear();
						seaList.clear();
						airList.clear();
						landList.clear();
					}
					allList.addAll((ArrayList<MyQuoteToConfirm>) result.getDatas());
					for (int i = 0; i < result.getDatas().size(); i++) {
						MyQuoteToConfirm myQuoteToConfirm = ((ArrayList<MyQuoteToConfirm>) result.getDatas()).get(i);
						String shipping_type = myQuoteToConfirm.getShipping_type();
						if(shipping_type.equals("1")){
							seaList.add(myQuoteToConfirm);
						}else if(shipping_type.equals("2")){
							airList.add(myQuoteToConfirm);
						}
						else{
							landList.add(myQuoteToConfirm);
						}
					}
					if(mode==0){
						onLoadComplete(1, allList);
					}
					else if(mode==1){
						onLoadComplete(1, seaList);
					}
					else if(mode==2){
						onLoadComplete(1, airList);
					}
					else{
						onLoadComplete(1, landList);
					}
//					dataList = (ArrayList<MyQuoteToConfirm>) result.getDatas();
				}else{
					
				}
			}
			@Override
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
			}
		};
		access = new MyQuoteAccess<List<MyQuoteToConfirm>>(getActivity(),
				requestCallback);
		access.getMyQuoteList(mBaseApp.getUserssid(), "0", current_page,
				page_size);
	}
	public void select(int mode){
		switch (mode) {
		case 0:
			onLoadComplete(1, allList);
			break;
		case 1:
			onLoadComplete(1, seaList);
			break;
		case 2:
			onLoadComplete(1, airList);
			break;
		case 3:
			onLoadComplete(1, landList);
			break;

		default:
			break;
		}
	}
	@Override
	public void onRefresh() {
		current_page=1;
		request();
	}
	@Override
	public void onLoadMore() {
		current_page++;
		request();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public boolean isLazyLoad() {
		return false;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
}
