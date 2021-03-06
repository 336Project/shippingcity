package com.ateam.shippingcity.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.activity.MyQuoteConfirmActivity;
import com.ateam.shippingcity.activity.PalletDetailActivity;
import com.ateam.shippingcity.adapter.PalletTransportAdapter;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

/**
 * 关注的海运列表片段
 */
public class PersoanlAttentionPalletTransportFragment extends HBaseXListViewFragment<PalletTransport> implements
		OnXListItemClickListener {
	private String type="";
	private PalletTransportAdapter mAdapter;// 海运list适配器
	private ArrayList<PalletTransport> dataList = new ArrayList<PalletTransport>();// 要显示的数据
	private PersonalAccess<List<PalletTransport>> access;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//判断是否已经报价，进行跳转
		if(dataList.get(position).ifbid!=null&&dataList.get(position).ifbid.equals("1")){
			Intent intent = new Intent(getActivity(), MyQuoteConfirmActivity.class);
			intent.putExtra("offerid", dataList.get(position).id);
			getActivity().startActivity(intent);
		}else{
			Intent intent = new Intent(getActivity(), PalletDetailActivity.class);
			intent.putExtra("palletTransport", dataList.get(position));
//			getActivity().startActivityForResult(intent, 1000);
//			startActivityForResult(intent, 1000);
			getParentFragment().startActivityForResult(intent, 1000);
		}
	}
	
	@Override
	public void request() {
		// TODO Auto-generated method stub
		access.getPalletRansportList(mBaseApp.getUserssid(), type, current_page, page_size);
	}

	@Override
	public BaseAdapter getAdapter() {
		// TODO Auto-generated method stub
		return mAdapter;
	}

	@Override
	public List<PalletTransport> getDataSource() {
		// TODO Auto-generated method stub
		return dataList;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		type=getArguments().getString("type");
		mAdapter = new PalletTransportAdapter(getActivity(), dataList);
		setOnXListItemClickListener(this);
		initRequest();
	}

	/**
	 * 
	 * 2015-3-11 下午5:08:44
	 * 
	 * @TODO 初始化请求
	 */
	private void initRequest() {
		HRequestCallback<Respond<List<PalletTransport>>> requestCallback = new HRequestCallback<Respond<List<PalletTransport>>>() {
			
			@Override
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
				stopRefreshOrLoad();
			}
			@SuppressWarnings("unchecked")
			@Override
			public Respond<List<PalletTransport>> parseJson(String jsonStr) {
				Type type = new com.google.gson.reflect.TypeToken<Respond<List<PalletTransport>>>() {
				}.getType();
				return (Respond<List<PalletTransport>>) JSONParse.jsonToObject(jsonStr, type);
			}

			@Override
			public void onSuccess(Respond<List<PalletTransport>> result) {
					onLoadComplete(result.getTotalPages(), result.getDatas());
			}
		};
		access = new PersonalAccess<List<PalletTransport>>(
				getActivity(), requestCallback);
		if(type.equals("全部")){
			access.getPalletRansportList(mBaseApp.getUserssid(), type, current_page, page_size);
		}
	}

	@Override
	public boolean isLazyLoad() {
		if(type.equals("全部")){
			return false;
		}
		return true;
	}

}
