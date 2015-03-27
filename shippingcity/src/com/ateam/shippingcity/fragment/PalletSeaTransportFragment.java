package com.ateam.shippingcity.fragment;

import java.util.List;

import com.ateam.shippingcity.fragment.HBaseXListViewFragment.OnXListItemClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

/**
 * 海运列表片段
 */
public class PalletSeaTransportFragment extends HBaseXListViewFragment implements OnXListItemClickListener {
	

	public PalletSeaTransportFragment() {
		// Required empty public constructor
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
		
	}

	@Override
	public void request() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BaseAdapter getAdapter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getDataSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 * @author 李晓伟
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
//			ELOAN_Project mproject=new ELOAN_Project();
//			mproject.setTitle("测试"+i);
//			mproject.setMortgageProfit(20+""+i);
//			mproject.setMortgageDuring(i+"月");
//			mproject.setMortgageTotal("200"+i);
//			mproject.setRepayment_modal(mType);
//			projects.add(mproject);
		}
	}
	@Override
	public boolean isLazyLoad() {
		return true;
	}

}
