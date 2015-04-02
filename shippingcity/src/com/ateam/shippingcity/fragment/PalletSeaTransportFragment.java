package com.ateam.shippingcity.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.access.PalletTransportAccess;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.activity.PalletDetailActivity;
import com.ateam.shippingcity.adapter.PalletSeaTransportAdapter;
import com.ateam.shippingcity.application.HBaseApp;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.model.User;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

/**
 * 海运列表片段
 */
public class PalletSeaTransportFragment extends HBaseXListViewFragment<PalletTransport> implements OnXListItemClickListener {
	
	private PalletSeaTransportAdapter mAdapter;//海运list适配器
	private ArrayList<PalletTransport> dataList=new ArrayList<PalletTransport>();//要显示的数据
	private PalletTransportAccess<PalletTransport> access;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return super.onCreateView(inflater,container, savedInstanceState);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		MyToast.showShort(getActivity(), "你点击了该item！");
		Intent intent=new Intent(getActivity(),PalletDetailActivity.class);
		getActivity().startActivity(intent);
	}

	@Override
	public void request() {
		// TODO Auto-generated method stub
		access.getPalletRansportList(mBaseApp.getUserssid(), getArguments().getString("type"), current_page, page_size);
	}

	@Override
	public BaseAdapter getAdapter() {
		// TODO Auto-generated method stub
		return mAdapter;
	}

	@Override
	public List getDataSource() {
		// TODO Auto-generated method stub
		return dataList;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		mAdapter=new PalletSeaTransportAdapter(getActivity(), dataList);
		setOnXListItemClickListener(this);
		initRequest();
	}
	
	/**
	 * 
	 * 2015-3-11 下午5:08:44
	 * @TODO 初始化请求
	 */
	private void initRequest() {
		HRequestCallback<Respond<PalletTransport>> requestCallback=new HRequestCallback<Respond<PalletTransport>>() {
		
		@SuppressWarnings("unchecked")
		@Override
		public Respond<PalletTransport> parseJson(String jsonStr) {
			Type type=new com.google.gson.reflect.TypeToken<Respond<PalletTransport>>(){}.getType();
			return (Respond<PalletTransport>) JSONParse.jsonToObject(jsonStr, type);
		}
		
		@Override
		public void onSuccess(Respond<PalletTransport> result) {
			Log.e("", ""+result.toString());
			if(result.getDatas()!=null){
				
			}
		}
	};
	access=new PalletTransportAccess<PalletTransport>(getActivity(), requestCallback);
	access.setIsShow(false);
	access.getPalletRansportList(mBaseApp.getUserssid(), getArguments().getString("type"), current_page, page_size);
//		for (int i = 0; i < 11; i++) {	
//			PalletTransport seaTransport=new PalletTransport();
//			seaTransport.setBoxType("整箱");
//			seaTransport.setPalletDescribe("真是一群不容易的人啊 。，宅搜的金发来看；萨卡发发；了");
//			seaTransport.setPlaceBegin("XIAMEN"+i);
//			seaTransport.setPlaceEnd("fuzhou"+i);
//			seaTransport.setRemainTime("一个月");
//			seaTransport.setTransportTimeBegin(""+i);
//			seaTransport.setTransportTimeEnd(""+i+i);
//			seaTransport.setTransportType(type);
//			dataList.add(seaTransport);
//		}
	}
	@Override
	public boolean isLazyLoad() {
		return true;
	}

}
