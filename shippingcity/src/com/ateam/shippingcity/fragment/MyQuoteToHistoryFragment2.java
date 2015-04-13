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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.MyQuoteAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.activity.MyQuoteHistoryActivity;
import com.ateam.shippingcity.adapter.MyQuoteToHistoryAdapter;
import com.ateam.shippingcity.application.HBaseApp;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.MyQuoteToHistory;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;
import com.ateam.shippingcity.widget.xlist.XListView;
import com.ateam.shippingcity.widget.xlist.XListView.IXListViewListener;

@SuppressLint("ValidFragment")
public class MyQuoteToHistoryFragment2 extends Fragment implements
		IXListViewListener {

	private MyQuoteToHistoryAdapter mAdapter;// 历史报价list适配器
	private ArrayList<MyQuoteToHistory> allList = new ArrayList<MyQuoteToHistory>();
	private ArrayList<MyQuoteToHistory> seaList = new ArrayList<MyQuoteToHistory>();
	private ArrayList<MyQuoteToHistory> airList = new ArrayList<MyQuoteToHistory>();
	private ArrayList<MyQuoteToHistory> landList = new ArrayList<MyQuoteToHistory>();
	private ArrayList<MyQuoteToHistory> dataList = new ArrayList<MyQuoteToHistory>();// 要显示的数据

	protected int page_size = 10;
	protected int current_page = 1;
	private int totalPages = 1;
	private int mode = 0;
	private String myuid;
	private MyQuoteAccess<List<MyQuoteToHistory>> access;
	private HBaseApp mBaseApp;
	private XListView mListView;
	private List<MyQuoteToHistory> mDataSource;
	private boolean isCurrentPage=false;

	public MyQuoteToHistoryFragment2() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_base_x_list, null);
		mBaseApp = (HBaseApp) getActivity().getApplication();
		initListView(view);
		initData();
		mListView.setAdapter(getAdapter());
		mDataSource = getDataSource();
		return view;
	}

	private void initListView(View view) {
		mListView = (XListView) view.findViewById(R.id.xListView);
		mListView.setPullLoadEnable(false);
		mListView.setPullRefreshEnable(true);
		mListView.setXListViewListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), MyQuoteHistoryActivity.class);
				String offerid = "";
				if (mDataSource != null) {
					offerid = mDataSource.get(position - 1).getId();
					intent.putExtra("offerid", offerid);
					intent.putExtra("myuid", myuid);
					getActivity().startActivity(intent);
				}
			}
		});
	}

	public void request() {
		access.getMyQuoteList(mBaseApp.getUserssid(), "1", current_page,
				page_size);
	}

	public BaseAdapter getAdapter() {
		return mAdapter;
	}

	public List<MyQuoteToHistory> getDataSource() {
		return dataList;
	}

	public void initData() {
		mAdapter = new MyQuoteToHistoryAdapter(getActivity(), dataList);
		initRequest();
	}

	private void initRequest() {
		HRequestCallback<Respond<List<MyQuoteToHistory>>> requestCallback = new HRequestCallback<Respond<List<MyQuoteToHistory>>>() {

			@SuppressWarnings("unchecked")
			@Override
			public Respond<List<MyQuoteToHistory>> parseJson(String jsonStr) {
				Type type = new com.google.gson.reflect.TypeToken<Respond<List<MyQuoteToHistory>>>() {
				}.getType();
				return (Respond<List<MyQuoteToHistory>>) JSONParse
						.jsonToObject(jsonStr, type);
			}

			@Override
			public void onSuccess(Respond<List<MyQuoteToHistory>> result) {
				if (result.isSuccess()) {
					totalPages = result.getTotalPages();
					myuid = result.getMyuid();
					mAdapter.setMyuid(myuid);
					dataList = (ArrayList<MyQuoteToHistory>) result.getDatas();
					if (current_page == 1) {
						allList.clear();
						seaList.clear();
						airList.clear();
						landList.clear();
					}
					allList.addAll((ArrayList<MyQuoteToHistory>) result
							.getDatas());
					for (int i = 0; i < result.getDatas().size(); i++) {
						MyQuoteToHistory MyQuoteToHistory = ((ArrayList<MyQuoteToHistory>) result
								.getDatas()).get(i);
						String shipping_type = MyQuoteToHistory
								.getShipping_type();
						if (shipping_type.equals("1")) {
							seaList.add(MyQuoteToHistory);
						} else if (shipping_type.equals("2")) {
							airList.add(MyQuoteToHistory);
						} else {
							landList.add(MyQuoteToHistory);
						}
					}
					mDataSource.clear();
					if (mode == 0) {
						onLoadComplete(result.getTotalPages(), allList);
					} else if (mode == 1) {
						onLoadComplete(result.getTotalPages(), seaList);
					} else if (mode == 2) {
						onLoadComplete(result.getTotalPages(), airList);
					} else {
						onLoadComplete(result.getTotalPages(), landList);
					}
				} else {
				}
			}

			@Override
			public void onFail(Context c, String errorMsg) {
				onLoadComplete(1, null);
			}
		};
		access = new MyQuoteAccess<List<MyQuoteToHistory>>(getActivity(),
				requestCallback);
		access.getMyQuoteList(mBaseApp.getUserssid(), "1", current_page,
				page_size);
	}

	public void select(int mode) {
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
		current_page = 1;
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
	public void onLoadComplete(long totalPage, List<MyQuoteToHistory> newDatas) {
		if (mDataSource == null)
			throw new NullPointerException("DataSource must be not null");
		stopRefreshOrLoad();
		if (current_page == 1) {
			mDataSource.clear();
		}
		if (newDatas != null && !newDatas.isEmpty()) {
			mDataSource.addAll(newDatas);
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		mListView.setRefreshTime(df.format(new Date()));
		if (mDataSource != null && current_page < totalPage) {
			mListView.setPullLoadEnable(true);
		} else {
			mListView.setPullLoadEnable(false);
		}
		getAdapter().notifyDataSetChanged();
		if (mDataSource.size() == 0) {
			if(isCurrentPage()){
				MyToast.showShort(getActivity(), "暂无相关数据");
			}
		}
	}

	public void stopRefreshOrLoad() {
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
