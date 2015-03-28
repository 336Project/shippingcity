package com.ateam.shippingcity.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.adapter.PersonalMyIntegralAdapter;
import com.ateam.shippingcity.widget.xlist.XListView;
import com.ateam.shippingcity.widget.xlist.XListView.IXListViewListener;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 
 * @author 李晓伟
 * 2015-3-28 下午3:39:15
 * @TODO 我的积分
 */
public class PersonalMyIntegralActivity extends HBaseActivity implements IXListViewListener,OnClickListener{
	private XListView mListView;
	protected int page_size=10;
	protected int current_page=1;
	private List<Map<String, String>> mDataSource;
	private PersonalMyIntegralAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("我的积分");
		getRightTxt().setVisibility(View.VISIBLE);
		getRightTxt().setText("积分规则");
		getRightTxt().setOnClickListener(this);
		setBaseContentView(R.layout.activity_personal_integral);
		initListView();
	}
	
	/**
	 * 
	 * @author 李晓伟
	 * 2015-3-13 上午10:33:56
	 * @TODO 初始化ListView
	 */
	private void initListView(){
		mDataSource=new ArrayList<Map<String,String>>();
		for (int i = 0; i < 10; i++) {
			Map<String,String> map=new HashMap<String, String>();
			map.put("title", "推荐奖励");
			map.put("integral", ""+i);
			mDataSource.add(map);
		}
		mAdapter=new PersonalMyIntegralAdapter(this, mDataSource);
		
		mListView=(XListView) findViewById(R.id.xListView);
		mListView.setPullLoadEnable(false);
		mListView.setPullRefreshEnable(true);
		mListView.setXListViewListener(this);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			}
			
		});
		mListView.setAdapter(mAdapter);
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
	
	private void request() {
		
	}
	/**
	 * 
	 * @author 李晓伟
	 * 2015-3-4 下午4:56:00
	 * @TODO 加载完成
	 */
	public void  onLoadComplete(long totalSize,List<Map<String, String>> newDatas) {
		if(mDataSource==null) 
			throw new NullPointerException("DataSource must be not null");
		stopRefreshOrLoad();
		if(current_page==1){
			mDataSource.clear();
		}
		if(newDatas!=null&&!newDatas.isEmpty()){
			mDataSource.addAll(newDatas);
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		mListView.setRefreshTime(df.format(new Date()));
		if(mDataSource!=null&&mDataSource.size()<totalSize){
			mListView.setPullLoadEnable(true);
		}else{
			mListView.setPullLoadEnable(false);
		}
		mAdapter.notifyDataSetChanged();
		if(totalSize==0){
			showMsg(this, R.string.empty_data);
		}
	}
	public void stopRefreshOrLoad(){
		mListView.stopRefresh();
		mListView.stopLoadMore();
	}

	@Override
	public void onClick(View v) {
		jump(this, PersonalIntegralRuleActivity.class);
	}
}
