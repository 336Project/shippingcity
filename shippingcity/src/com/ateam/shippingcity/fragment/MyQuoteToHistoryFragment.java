package com.ateam.shippingcity.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.ateam.shippingcity.activity.MyQuoteHistoryActivity;
import com.ateam.shippingcity.activity.MyQuoteSeaTransportFCLActivity;
import com.ateam.shippingcity.adapter.MyQuoteToHistoryAdapter;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.MyQuoteToHistory;
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
		// TODO Auto-generated method stub
		return mAdapter;
	}

	@Override
	public List getDataSource() {
		return null;
	}

	@Override
	public void initData() {
		mAdapter=new MyQuoteToHistoryAdapter(getActivity(), dataList);
		setOnXListItemClickListener(this);
		initRequest();
	}
	private void initRequest() {
		for (int i = 0; i < 11; i++) {	
			MyQuoteToHistory myQuoteToHistory=new MyQuoteToHistory();
			myQuoteToHistory.setBoxType("整箱");
			myQuoteToHistory.setPalletDescribe("真是一群不容易的人啊 。，宅搜的金发来看；萨卡发发；了");
			myQuoteToHistory.setPlaceBegin("HONGKONG"+i);
			myQuoteToHistory.setPlaceEnd("ALEXANDRIA"+i);
			myQuoteToHistory.setTransportTimeBegin(""+i);
			myQuoteToHistory.setTransportTimeEnd(""+i+i);
			myQuoteToHistory.setTransportType("海运");
			dataList.add(myQuoteToHistory);
		}
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
