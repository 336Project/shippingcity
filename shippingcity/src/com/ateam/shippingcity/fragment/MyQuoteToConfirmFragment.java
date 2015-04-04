package com.ateam.shippingcity.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.MyQuoteAccess;
import com.ateam.shippingcity.access.PalletTransportAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.activity.HBaseActivity;
import com.ateam.shippingcity.activity.MyQuoteToConfirmActivity;
import com.ateam.shippingcity.activity.PalletAndQuoteCommonActivity;
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
public class MyQuoteToConfirmFragment extends HBaseXListViewFragment implements
		OnXListItemClickListener {

	private MyQuoteToConfirmAdapter mAdapter;// 海运list适配器
	private ArrayList<MyQuoteToConfirm> dataList = new ArrayList<MyQuoteToConfirm>();// 要显示的数据
	private String type;

	public MyQuoteToConfirmFragment() {

	}

	public MyQuoteToConfirmFragment(String type) {
		this.type = type;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(position>=1){
			Intent intent = new Intent();
			intent.setClass(getActivity(), MyQuoteToConfirmActivity.class);
			String offerid = dataList.get(position-1).getId();
			intent.putExtra("id", offerid);
			Log.e("position", "position="+position);
			getActivity().startActivity(new Intent(getActivity(),MyQuoteToConfirmActivity.class));
		}
	}

	@Override
	public void request() {

	}
	@Override
	public BaseAdapter getAdapter() {
		return mAdapter;
	}

	@Override
	public List getDataSource() {
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
					dataList = (ArrayList<MyQuoteToConfirm>) result.getDatas();
					onLoadComplete(3, dataList);
				}
			}
			@Override
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
			}
		};
		MyQuoteAccess<List<MyQuoteToConfirm>> access = new MyQuoteAccess<List<MyQuoteToConfirm>>(getActivity(),
				requestCallback);
		access.getMyQuoteList(mBaseApp.getUserssid(), "0", current_page,
				page_size);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public boolean isLazyLoad() {
		return true;
	}
}
