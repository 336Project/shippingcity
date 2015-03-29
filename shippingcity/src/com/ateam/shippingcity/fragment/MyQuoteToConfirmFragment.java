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

import com.ateam.shippingcity.activity.MyQuoteSeaTransportFCLActivity;
import com.ateam.shippingcity.adapter.MyQuoteToConfirmAdapter;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.MyQuoteToConfirm;
import com.ateam.shippingcity.utils.MyToast;

@SuppressLint("ValidFragment")
public class MyQuoteToConfirmFragment extends HBaseXListViewFragment implements OnXListItemClickListener{
	
	private MyQuoteToConfirmAdapter mAdapter;//海运list适配器
	private ArrayList<MyQuoteToConfirm> dataList=new ArrayList<MyQuoteToConfirm>();//要显示的数据
	private String type;
	
	public  MyQuoteToConfirmFragment(){
		
	}
	public MyQuoteToConfirmFragment(String type){
		this.type=type;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		getActivity().startActivity(new Intent(getActivity(), MyQuoteSeaTransportFCLActivity.class));
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
		mAdapter=new MyQuoteToConfirmAdapter(getActivity(), dataList);
		setOnXListItemClickListener(this);
		initRequest();
	}
	private void initRequest() {
		for (int i = 0; i < 11; i++) {	
			MyQuoteToConfirm myQuoteToConfirm=new MyQuoteToConfirm();
			myQuoteToConfirm.setBoxType("整箱");
			myQuoteToConfirm.setPalletDescribe("真是一群不容易的人啊 。，宅搜的金发来看；萨卡发发；了");
			myQuoteToConfirm.setPlaceBegin("HONGKONG"+i);
			myQuoteToConfirm.setPlaceEnd("ALEXANDRIA"+i);
			myQuoteToConfirm.setTransportTimeBegin(""+i);
			myQuoteToConfirm.setTransportTimeEnd(""+i+i);
			if(type==null){
				myQuoteToConfirm.setTransportType("全部");
			}
			else{
				myQuoteToConfirm.setTransportType(type);
			}
			dataList.add(myQuoteToConfirm);
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
