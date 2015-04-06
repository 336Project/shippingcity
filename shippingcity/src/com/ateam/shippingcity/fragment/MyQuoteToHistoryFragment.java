package com.ateam.shippingcity.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.ateam.shippingcity.access.MyQuoteAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.activity.MyQuoteHistoryActivity;
import com.ateam.shippingcity.adapter.MyQuoteToHistoryAdapter;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.MyQuoteToHistory;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;

@SuppressLint("ValidFragment")
public class MyQuoteToHistoryFragment extends HBaseXListViewFragment implements OnXListItemClickListener{
	
	private MyQuoteToHistoryAdapter mAdapter;//历史报价list适配器
	private ArrayList<MyQuoteToHistory> allList= new ArrayList<MyQuoteToHistory>();
	private ArrayList<MyQuoteToHistory> seaList= new ArrayList<MyQuoteToHistory>();
	private ArrayList<MyQuoteToHistory> airList= new ArrayList<MyQuoteToHistory>();
	private ArrayList<MyQuoteToHistory> landList= new ArrayList<MyQuoteToHistory>();
	private ArrayList<MyQuoteToHistory> dataList=new ArrayList<MyQuoteToHistory>();//要显示的数据
	
	protected int page_size=10;
	protected int current_page=1;
	private int mode=0;
	private MyQuoteAccess<List<MyQuoteToHistory>> access;
	public  MyQuoteToHistoryFragment(){
		
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if(position>=1){
			Intent intent = new Intent();
			intent.setClass(getActivity(), MyQuoteHistoryActivity.class);
			String offerid = dataList.get(position-1).getId();
			intent.putExtra("offerid", offerid);
			getActivity().startActivity(intent);
		}
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
	public List getDataSource() {
		return dataList;
	}

	@Override
	public void initData() {
		mAdapter=new MyQuoteToHistoryAdapter(getActivity(), dataList);
		setOnXListItemClickListener(this);
		initRequest();
	}
	private void initRequest() {
//		for (int i = 0; i < 11; i++) {	
//			MyQuoteToHistory myQuoteToHistory=new MyQuoteToHistory();
//			myQuoteToHistory.setBoxType("整箱");
//			myQuoteToHistory.setPalletDescribe("真是一群不容易的人啊 。，宅搜的金发来看；萨卡发发；了");
//			myQuoteToHistory.setPlaceBegin("HONGKONG"+i);
//			myQuoteToHistory.setPlaceEnd("ALEXANDRIA"+i);
//			myQuoteToHistory.setTransportTimeBegin(""+i);
//			myQuoteToHistory.setTransportTimeEnd(""+i+i);
//			myQuoteToHistory.setTransportType("海运");
//			dataList.add(myQuoteToHistory);
//		}
		HRequestCallback<Respond<List<MyQuoteToHistory>>> requestCallback = new HRequestCallback<Respond<List<MyQuoteToHistory>>>() {
			@SuppressWarnings("unchecked")
			@Override
			public Respond<List<MyQuoteToHistory>> parseJson(String jsonStr) {
				Type type = new com.google.gson.reflect.TypeToken<Respond<List<MyQuoteToHistory>>>() {
				}.getType();
				return (Respond<List<MyQuoteToHistory>>) JSONParse.jsonToObject(jsonStr, type);
			}

			@Override
			public void onSuccess(Respond<List<MyQuoteToHistory>> result) {
				if(result.isSuccess()){
					mAdapter.setMyuid("782");
					dataList = (ArrayList<MyQuoteToHistory>) result.getDatas();
					if(result.isSuccess()){
						if(current_page>=1){
							allList.clear();
							seaList.clear();
							airList.clear();
							landList.clear();
						}
						allList.addAll((ArrayList<MyQuoteToHistory>) result.getDatas());
						for (int i = 0; i < result.getDatas().size(); i++) {
							MyQuoteToHistory MyQuoteToHistory = ((ArrayList<MyQuoteToHistory>) result.getDatas()).get(i);
							String shipping_type = MyQuoteToHistory.getShipping_type();
							if(shipping_type.equals("1")){
								seaList.add(MyQuoteToHistory);
							}else if(shipping_type.equals("2")){
								airList.add(MyQuoteToHistory);
							}
							else{
								landList.add(MyQuoteToHistory);
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
					}else{
						
					}
				}
			}
			@Override
			public void onFail(Context c, String errorMsg) {
				onLoadComplete(1, null);
			}
		};
		access = new MyQuoteAccess<List<MyQuoteToHistory>>(getActivity(),
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
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater,container, savedInstanceState);
	}
	@Override
	public boolean isLazyLoad() {
		return true;
	}
}
