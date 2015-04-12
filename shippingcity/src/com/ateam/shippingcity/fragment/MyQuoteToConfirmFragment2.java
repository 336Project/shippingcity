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
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.MyQuoteAccess;
import com.ateam.shippingcity.access.PalletTransportAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.activity.HBaseActivity;
import com.ateam.shippingcity.activity.MyQuoteConfirmActivity;
import com.ateam.shippingcity.activity.MainActivity;
import com.ateam.shippingcity.activity.MyQuoteHistoryActivity;
import com.ateam.shippingcity.activity.PersonalMyIntegralActivity;
import com.ateam.shippingcity.adapter.MyQuoteToConfirmAdapter;
import com.ateam.shippingcity.application.HBaseApp;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.IntegralRule;
import com.ateam.shippingcity.model.MyQuoteToConfirm;
import com.ateam.shippingcity.model.MyQuoteToHistory;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;
import com.ateam.shippingcity.widget.TextViewPair;
import com.ateam.shippingcity.widget.xlist.XListView;
import com.ateam.shippingcity.widget.xlist.XListView.IXListViewListener;

@SuppressLint("ValidFragment")
public class MyQuoteToConfirmFragment2 extends Fragment implements IXListViewListener {

	private MyQuoteToConfirmAdapter mAdapter;// 海运list适配器
	private ArrayList<MyQuoteToConfirm> allList= new ArrayList<MyQuoteToConfirm>();
	private ArrayList<MyQuoteToConfirm> seaList= new ArrayList<MyQuoteToConfirm>();
	private ArrayList<MyQuoteToConfirm> airList= new ArrayList<MyQuoteToConfirm>();
	private ArrayList<MyQuoteToConfirm> landList= new ArrayList<MyQuoteToConfirm>();
	private ArrayList<MyQuoteToConfirm> dataList = new ArrayList<MyQuoteToConfirm>();// 要显示的数据
	protected int page_size=10;
	protected int current_page=1;
	private int totalPages=1;
	private int mode=0;
	private MyQuoteAccess<List<MyQuoteToConfirm>> access;
	private List<MyQuoteToConfirm> mDataSource;
	private XListView mListView;
	private HBaseApp mBaseApp;
	private boolean isCurrentPage=true;
	public MyQuoteToConfirmFragment2() {

	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_base_x_list, null);
		mBaseApp=(HBaseApp) getActivity().getApplication();
		initListView(view);
		initData();
		mListView.setAdapter(getAdapter());
		mDataSource=getDataSource();
		return view;
	}
	private void initListView(View view) {
		mListView=(XListView) view.findViewById(R.id.xListView);
		mListView.setPullLoadEnable(false);
		mListView.setPullRefreshEnable(true);
		mListView.setXListViewListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), MyQuoteConfirmActivity.class);
				String offerid="";
				if(mDataSource!=null){
					offerid = mDataSource.get(position-1).getId();
					intent.putExtra("offerid", offerid);
					getActivity().startActivity(intent);
				}
			}
		});
	}
	public void request() {
		access.getMyQuoteList(mBaseApp.getUserssid(), "0", current_page,
				page_size);
	}
	public BaseAdapter getAdapter() {
		return mAdapter;
	}

	public List<MyQuoteToConfirm> getDataSource() {
		return dataList;
	}
	
	public void initData() {
		mAdapter = new MyQuoteToConfirmAdapter(getActivity(), dataList);
		initRequest();
	}

	private void initRequest() {
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
					totalPages = result.getTotalPages();
					if(current_page==1){
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
					mDataSource.clear();
					if(mode==0){
						onLoadComplete(totalPages, allList);
					}
					else if(mode==1){
						onLoadComplete(totalPages, seaList);
					}
					else if(mode==2){
						onLoadComplete(totalPages, airList);
					}
					else{
						onLoadComplete(totalPages, landList);
					}
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
		mDataSource.clear();
		switch (mode) {
		case 0:
			onLoadComplete(totalPages, allList);
			break;
		case 1:
			onLoadComplete(totalPages, seaList);
			break;
		case 2:
			onLoadComplete(totalPages, airList);
			break;
		case 3:
			onLoadComplete(totalPages, landList);
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
	public void onLoadComplete(long totalPage,List<MyQuoteToConfirm> newDatas) {
		if(mDataSource==null) 
			throw new NullPointerException("DataSource must be not null");
		stopRefreshOrLoad();
		if(current_page==1){
			mDataSource.clear();
		}
		if(newDatas!=null&&!newDatas.isEmpty()){
			mDataSource.addAll(newDatas);
			Log.e("mDataSource", "mDataSource:"+mDataSource.size());
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		mListView.setRefreshTime(df.format(new Date()));
		if(mDataSource!=null&&current_page<totalPage){
			mListView.setPullLoadEnable(true);
		}else{
			mListView.setPullLoadEnable(false);
		}
		getAdapter().notifyDataSetChanged();
		if(mDataSource.size()==0){
			if(isCurrentPage()){
				MyToast.showShort(getActivity(), "暂无相关数据");
			}
		}
	}
	public void stopRefreshOrLoad(){
		mListView.stopRefresh();
		mListView.stopLoadMore();
	}
	public boolean isCurrentPage() {
		return isCurrentPage;
	}
	public void setCurrentPage(boolean isCurrentPage) {
		this.isCurrentPage = isCurrentPage;
	}
}
