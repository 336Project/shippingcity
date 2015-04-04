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
import android.widget.Toast;

import com.ateam.shippingcity.access.MyQuoteAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.activity.MyQuoteHistoryActivity;
import com.ateam.shippingcity.activity.MyQuoteToConfirmActivity;
import com.ateam.shippingcity.adapter.MyQuoteToHistoryAdapter;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.MyQuoteToConfirm;
import com.ateam.shippingcity.model.MyQuoteToHistory;
import com.ateam.shippingcity.model.MyQuoteToHistory;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;

@SuppressLint("ValidFragment")
public class MyQuoteToHistoryFragment extends HBaseXListViewFragment implements OnXListItemClickListener{
	
	private MyQuoteToHistoryAdapter mAdapter;//历史报价list适配器
	private ArrayList<MyQuoteToHistory> dataList=new ArrayList<MyQuoteToHistory>();//要显示的数据
	private String type;
	
	public  MyQuoteToHistoryFragment(){
		
	}
	public MyQuoteToHistoryFragment(String type){
		this.type=type;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		MyToast.showShort(getActivity(), "你点击了该item！");
		getActivity().startActivity(new Intent(getActivity(), MyQuoteHistoryActivity.class));
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
					Log.e("result.getDatas()", "result.getDatas():"+result.getDatas());
					mAdapter.setMyuid("782");
					dataList = (ArrayList<MyQuoteToHistory>) result.getDatas();
					onLoadComplete(3, dataList);
//					setupView(result.getDatas());
					/*for (IntegralRule rule : result.getDatas()) {
						System.out.println(rule.toString());
					}*/
				}
			}
			@Override
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
			}
		};
		MyQuoteAccess<List<MyQuoteToHistory>> access = new MyQuoteAccess<List<MyQuoteToHistory>>(getActivity(),
				requestCallback);
		access.getMyQuoteList(mBaseApp.getUserssid(), "0", current_page,
				page_size);
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
