package com.ateam.shippingcity.fragment;

import java.util.ArrayList;
import java.util.List;

import com.ateam.shippingcity.adapter.PalletSeaTransportAdapter;
import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import com.ateam.shippingcity.model.PalletSeaTransport;
import com.ateam.shippingcity.utils.MyToast;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

/**
 * 海运列表片段
 */
@SuppressLint("ValidFragment")
public class PalletSeaTransportFragment extends HBaseXListViewFragment implements OnXListItemClickListener {
	
	private PalletSeaTransportAdapter mAdapter;//海运list适配器
	private ArrayList<PalletSeaTransport> dataList=new ArrayList<PalletSeaTransport>();//要显示的数据
	
	private String type;

	public PalletSeaTransportFragment(){
		
	}
	public PalletSeaTransportFragment(String type) {
		// Required empty public constructor
		this.type=type;
	}

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
	}

	@Override
	public void request() {
		// TODO Auto-generated method stub
		
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
//		ELOAN_RequestCallback<ELOAN_ResultList<ELOAN_Project>> requestCallback=new ELOAN_RequestCallback<ELOAN_ResultList<ELOAN_Project>>() {
//			
//			@SuppressWarnings("unchecked")
//			@Override
//			public ELOAN_ResultList<ELOAN_Project> parseJson(String jsonStr) {
//				java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<ELOAN_ResultList<ELOAN_Project>>() {
//				}.getType();
//				return (ELOAN_ResultList<ELOAN_Project>) ELOAN_JsonParse.jsonToObject(jsonStr,type);
//			}
//			@Override
//			public void onFail(Context c, String errorMsg) {
//				super.onFail(c, errorMsg);
//				stopRefreshOrLoad();
//			}
//			@Override
//			public void onSuccess(ELOAN_ResultList<ELOAN_Project> result) {
//				onLoadComplete(result.page.totalRecords,result.page.records);
//			}
//		};
//		mAccess=new ELOAN_ProjectsAccess(getActivity(), requestCallback);
		for (int i = 0; i < 11; i++) {	
			PalletSeaTransport seaTransport=new PalletSeaTransport();
			seaTransport.setBoxType("整箱");
			seaTransport.setPalletDescribe("真是一群不容易的人啊 。，宅搜的金发来看；萨卡发发；了");
			seaTransport.setPlaceBegin("XIAMEN"+i);
			seaTransport.setPlaceEnd("fuzhou"+i);
			seaTransport.setRemainTime("一个月");
			seaTransport.setTransportTimeBegin(""+i);
			seaTransport.setTransportTimeEnd(""+i+i);
			seaTransport.setTransportType(type);
			dataList.add(seaTransport);
		}
	}
	@Override
	public boolean isLazyLoad() {
		return true;
	}

}
