package com.ateam.shippingcity.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.adapter.PersonalMyIntegralAdapter;
import com.ateam.shippingcity.model.MyIntegral;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.widget.TextViewPair;
import com.ateam.shippingcity.widget.xlist.XListView;
import com.ateam.shippingcity.widget.xlist.XListView.IXListViewListener;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
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
	private List<MyIntegral> mDataSource;
	private PersonalMyIntegralAdapter mAdapter;
	private PersonalAccess<List<MyIntegral>> access;
	private TextViewPair mTxtTotalCredit;
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
		mDataSource=new ArrayList<MyIntegral>();
		mAdapter=new PersonalMyIntegralAdapter(this, mDataSource);
		mTxtTotalCredit=(TextViewPair) findViewById(R.id.txt_total_credit);
		mTxtTotalCredit.setValueText("0");
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
		HRequestCallback<Respond<List<MyIntegral>>> requestCallback=new HRequestCallback<Respond<List<MyIntegral>>>() {
			@Override
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
				onLoadComplete(mDataSource.size(), null);
			}
			@SuppressWarnings("unchecked")
			@Override
			public Respond<List<MyIntegral>> parseJson(String jsonStr) {
				java.lang.reflect.Type type = new TypeToken<Respond<List<MyIntegral>>>() {
				}.getType();
				return (Respond<List<MyIntegral>>) JSONParse.jsonToObject(jsonStr, type);
			}
			
			@Override
			public void onSuccess(Respond<List<MyIntegral>> result) {
				if(result.isSuccess()){
					mTxtTotalCredit.setValueText(result.getTotalCredit()+"");
					onLoadComplete(result.getTotalPages(), result.getDatas());
				}else{
					showMsg(PersonalMyIntegralActivity.this, result.getMessage());
				}
			}
		};
		access=new PersonalAccess<List<MyIntegral>>(this, requestCallback);
		access.getIntegralRecords(mBaseApp.getUserssid(),current_page,page_size);
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
		access.getIntegralRecords(mBaseApp.getUserssid(),current_page,page_size);
	}
	/**
	 * 
	 * @author 李晓伟
	 * 2015-3-4 下午4:56:00
	 * @TODO 加载完成
	 */
	public void  onLoadComplete(long totalPage,List<MyIntegral> newDatas) {
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
		if(mDataSource!=null&&current_page<totalPage){
			mListView.setPullLoadEnable(true);
		}else{
			mListView.setPullLoadEnable(false);
		}
		mAdapter.notifyDataSetChanged();
		if(mDataSource.size()==0){
			showMsg(this, R.string.empty_data);
		}
	}
	public void stopRefreshOrLoad(){
		mListView.stopRefresh();
		mListView.stopLoadMore();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txt_right:
			jump(this, PersonalIntegralRuleActivity.class);
			/*Intent intent=new Intent(this, HWebViewActivity.class);
			intent.putExtra(HWebViewActivity.KEY_TITLE, "积分规则");
			intent.putExtra(HWebViewActivity.KEY_URL, HURL.URL_PERSONAL_CREDIT_RULE);
			startActivity(intent);*/
			break;

		default:
			break;
		}
	}
}
