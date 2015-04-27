package com.ateam.shippingcity.activity;

import java.lang.reflect.Type;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PalletTransportAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.access.I.HURL;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.PalletTransportDetail;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;
import com.ateam.shippingcity.utils.SysUtil;
import com.ateam.shippingcity.widget.weinxinImageShow.MyGridAdapter;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 货盘详情界面
 * @version 
 * @create_date 2015-3-28下午3:09:09
 */
public class PalletDetailActivity extends HBaseActivity implements OnClickListener{

	private TextView mTvTransportType;//运输方式
	private TextView mTvBeginPlace;//运输开始地
	private TextView mTvEndPlace;//运输终点
	private TextView mTvGoBeginTime;//走货开始时间
	private TextView mTvGoEndTime;//走货结束时间
	private TextView mTvBoxType;//装箱类型
	private TextView mTvNotice;//备注
	private TextView mTvOfferEndTime;//报价结束时间
	private LinearLayout mLinearAddPhoto;//添加显示图片布局
	private Button mBtnFocus;//关注按钮
	private Button mBtnMyOffer;//我的报价按钮
	private GridView mGvAddPhoto;//添加显示图片布局
	private ImageView mIvType;//
	private PalletTransport mPallet;
	private PalletTransportAccess<PalletTransportDetail> access;
	private PalletTransportAccess<List<PalletTransport>> mFocusAccess;
	private View mViewAddPhoto;
	private TextView mTvShowPhoto;
	private LinearLayout mLayoutPalletDetail;
	
	private boolean isFocus=false;//标记是否被关注  默认没有  （false未关注  true关注）
	private boolean isFocusChange=false;//标记关注是否改变
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("货盘详情");
		setBaseContentView(R.layout.activity_pallet_detail);
		getLeftIcon().setOnClickListener(this);
		initView();
		initData();
	}
	
	/**
	 * 控件实例化
	 */
	@SuppressWarnings("deprecation")
	private void initView(){
		mPallet=(PalletTransport) getIntent().getSerializableExtra("palletTransport");
		Log.e("", "mPallet.picture"+mPallet.picture_path);
		mTvTransportType=(TextView)findViewById(R.id.tv_transportType);
		mTvBeginPlace=(TextView)findViewById(R.id.tv_beginPlace);
		mTvEndPlace=(TextView)findViewById(R.id.tv_endPlace);
		mTvGoBeginTime=(TextView)findViewById(R.id.tv_goBeginTime);
		mTvGoEndTime=(TextView)findViewById(R.id.tv_goEndTime);
		mTvBoxType=(TextView)findViewById(R.id.tv_boxType);
		mLinearAddPhoto=(LinearLayout)findViewById(R.id.layout_addPhoto);
		mTvNotice=(TextView)findViewById(R.id.tv_notice);
		mTvOfferEndTime=(TextView)findViewById(R.id.tv_offerEndTime);
		mBtnFocus=(Button)findViewById(R.id.btn_focus);
		mBtnMyOffer=(Button)findViewById(R.id.btn_myOffer);
		mGvAddPhoto=(GridView)findViewById(R.id.gv_addPhoto);
		mViewAddPhoto=(View)findViewById(R.id.view_addPhoto);
		mIvType=(ImageView)findViewById(R.id.iv_type);
		mTvShowPhoto=(TextView)findViewById(R.id.tv_showPhoto);
		mLayoutPalletDetail=(LinearLayout)findViewById(R.id.layout_palletDetail);
		mBtnFocus.setOnClickListener(this);
		mBtnMyOffer.setOnClickListener(this);
		if(mPallet.ifbid!=null&&mPallet.ifbid.equals("1")){
			findViewById(R.id.tv_view).setVisibility(View.GONE);
			mBtnMyOffer.setText("我的报价");
		}else if(SysUtil.getRemainTime(mPallet.deadlinetime).equals("0小时")){
			mBtnFocus.setBackgroundDrawable(getResources().getDrawable(R.drawable.closing_quotes_submit_icon));
			mBtnFocus.setTextColor(getResources().getColor(R.color.white));
			mBtnFocus.setClickable(false);
			mBtnMyOffer.setBackgroundDrawable(getResources().getDrawable(R.drawable.closing_quotes_submit_icon));
			mBtnMyOffer.setTextColor(getResources().getColor(R.color.white));
			mBtnMyOffer.setClickable(false);
		}
		//设置箱型图标
		if(mPallet.shipment_type.equals("1")){
			mIvType.setBackgroundDrawable(getResources().getDrawable(R.drawable.pallet_details_zhengxiang_small_icon));
		}else if(mPallet.shipment_type.equals("2")){
			if(mPallet.shipping_type.equals("3")){
				mIvType.setBackgroundDrawable(getResources().getDrawable(R.drawable.pallet_details_sanhuo_groceries_small_icon));
			}else{
				mIvType.setBackgroundDrawable(getResources().getDrawable(R.drawable.pallet_details_san_groceries_small_icon));
			}
		}else{
			mIvType.setBackgroundDrawable(getResources().getDrawable(R.drawable.pallet_details_of_pinxiang_small_icon));
		}
		if(mPallet.shipping_type.toString().equals("2")){
			mIvType.setBackgroundDrawable(getResources().getDrawable(R.drawable.pallet_details_air_transport_small_icon));
		}
	}
	
	/**
	 * 配置横向滑动的gridview，并传值显示
	 */
	private void initGridView(final String[] urls){
		int size=0;
		if(urls.length>5){
			size=5;
		}else{
			size=urls.length;
		}
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  
        		(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42*size, getResources().getDisplayMetrics()), 
                LinearLayout.LayoutParams.MATCH_PARENT);  
        mGvAddPhoto.setLayoutParams(params);  
        mGvAddPhoto.setColumnWidth((int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42, getResources().getDisplayMetrics()));  
        mGvAddPhoto.setHorizontalSpacing(1);  
        mGvAddPhoto.setStretchMode(GridView.NO_STRETCH);  
        mGvAddPhoto.setNumColumns(size); 
        String[] url=new String[5];
		if(urls.length>5){
			for (int i = 0; i < url.length; i++) {
				url[i]=urls[i];
			}
			mGvAddPhoto.setAdapter(new MyGridAdapter(url, this));
		}else{
			mGvAddPhoto.setAdapter(new MyGridAdapter(urls, this));
		}
		mGvAddPhoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				SysUtil.showImage(PalletDetailActivity.this,position,urls);
			}
		});
		mLinearAddPhoto.setClickable(true);
		mLinearAddPhoto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(urls.length!=0){
					SysUtil.showImage(PalletDetailActivity.this,0,urls);
				}
			}
		});
	}
	
	/**
	 * 提交数据
	 */
	private void initData(){
		HRequestCallback<Respond<PalletTransportDetail>> requestCallback = new HRequestCallback<Respond<PalletTransportDetail>>() {

			@SuppressWarnings("unchecked")
			@Override
			public Respond<PalletTransportDetail> parseJson(String jsonStr) {
				Log.e("", "jsonStr"+jsonStr);
				Type type = new com.google.gson.reflect.TypeToken<Respond<PalletTransportDetail>>() {
				}.getType();
				return (Respond<PalletTransportDetail>) JSONParse.jsonToObject(
						jsonStr, type);
			}

			@Override
			public void onSuccess(Respond<PalletTransportDetail> result) {
				if(result.isSuccess()){
					getLayoutError().setVisibility(View.GONE);
					getLayoutContent().setVisibility(View.VISIBLE);
					PalletTransportDetail datas = result.getDatas();
					String picture_path = datas.picture_path;
					if(!SysUtil.getRemainTime(mPallet.deadlinetime).equals("0小时")){
						if(datas.collected!=null){
							if(datas.collected.equals("0")){
								isFocus=false;
								mBtnFocus.setText("关注");
							}else if(datas.collected.equals("1")){
								isFocus=true;
								mBtnFocus.setText("取消关注");
							}
						}
					}
					if(!picture_path.equals("")){
						String[] split = picture_path.split("\\|");
						for (int i = 0; i < split.length; i++) {
							String string = split[i];
							string="http://"+HURL.IP+"/"+string;
							split[i]=string;
						}
						initGridView(split);
					}
					else{
						mLinearAddPhoto.setVisibility(View.GONE);
						mViewAddPhoto.setVisibility(View.GONE);
					}
					String shipping_type = datas.shipping_type;
					String shipment_type = datas.shipment_type;
					mTvBeginPlace.setText(datas.initiation);
					mTvEndPlace.setText(datas.destination);
					mTvGoBeginTime.setText(datas.startime);
					mTvGoEndTime.setText(datas.endtime);
					StringBuffer description=new StringBuffer();
					if(shipment_type.equals("1")){
						description.append("箱型：");
						if(mPallet.boxtype.size()==mPallet.number.size()){
							for (int i = 0; i < mPallet.boxtype.size(); i++) {
								if(!mPallet.boxtype.get(i).equals("")){
									description.append(mPallet.boxtype.get(i)+"*"+mPallet.number.get(i));
								}
								if((mPallet.number.size()-i)>=2){
									description.append("、");
								}
							}
						}
					}else{
						description.append("件数："+datas.packages+";");
						if(shipping_type.equals("1")&&shipment_type.equals("2")){
							description.append("毛重："+datas.weight+"TON;");
						}else{
							description.append("毛重："+datas.weight+"KG;");
						}
						description.append("体积："+datas.volume+"CBM;");
						if(datas.size.size()==3){
							description.append("单件尺寸:"+datas.size.get(0)+"CM*"+datas.size.get(1)+"CM*"+datas.size.get(2)+"CM;");
						}
					}
					mTvBoxType.setText(description);
					if(shipping_type.equals("1")){
						mTvTransportType.setText("海运");
					}else if(shipping_type.equals("2")){
						mTvTransportType.setText("空运");
						description=new StringBuffer();
						description.append("件数："+datas.packages+";");
						if(shipping_type.equals("1")&&shipment_type.equals("2")){
							description.append("毛重："+datas.weight+"TON;");
						}else{
							description.append("毛重："+datas.weight+"KG;");
						}
						description.append("体积："+datas.volume+"CBM;");
						if(datas.size.size()==3){
							description.append("单件尺寸:"+datas.size.get(0)+"CM*"+datas.size.get(1)+"CM*"+datas.size.get(2)+"CM;");
						}
						mTvBoxType.setText(description);
					}else{
						mTvTransportType.setText("陆运");
					}
					mTvNotice.setText(datas.remarks);
					mTvOfferEndTime.setText(datas.deadlinetime);
					mLayoutPalletDetail.setVisibility(View.VISIBLE);
				}
				if(result.getStatusCode().equals("500")){
					MyToast.showShort(PalletDetailActivity.this, result.getMessage());
				}
			}
			@Override
			public void onFail(Context c, String errorMsg) {
				// TODO Auto-generated method stub
				super.onFail(c, errorMsg);
				onLoadFail();
			}
		};
		access = new PalletTransportAccess<PalletTransportDetail>(
				PalletDetailActivity.this, requestCallback);
		access.getPalletDetail(mBaseApp.getUserssid(), mPallet.id);
	}
	
	@Override
	public void onReload() {
		super.onReload();
		initData();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_focus:
			if(TextUtils.isEmpty(mBaseApp.getUserssid())){
				Intent intent=new Intent(PalletDetailActivity.this, PersonalLoginActivity.class);
				startActivity(intent);
			}else{
				if(mBtnFocus.getText().equals("关注")){
					toFocus("add");
				}else{
					toFocus("delete");
				}
			}
			break;
		case R.id.btn_myOffer:
			if(TextUtils.isEmpty(mBaseApp.getUserssid())){
				Intent intent=new Intent(PalletDetailActivity.this, PersonalLoginActivity.class);
				startActivity(intent);
			}else{
				toOfferPage(mPallet);
			}
			break;
		case R.id.iv_left_icon:
			onBackPressed();
			break;
		default:
			break;
		}
	}
	
	/**
	 * 关注
	 */
	private void toFocus(final String action){
		HRequestCallback<Respond<List<PalletTransport>>> requestCallback = new HRequestCallback<Respond<List<PalletTransport>>>() {

			@SuppressWarnings("unchecked")
			@Override
			public Respond<List<PalletTransport>> parseJson(String jsonStr) {
				Log.e("", "" + jsonStr.toString());
				Type type = new com.google.gson.reflect.TypeToken<Respond<List<PalletTransport>>>() {
				}.getType();
				return (Respond<List<PalletTransport>>) JSONParse.jsonToObject(
						jsonStr, type);
			}

			@Override
			public void onSuccess(Respond<List<PalletTransport>> result) {
				if(result.getStatusCode().equals("200")){
					if(action.equals("add")){
						MyToast.showShort(PalletDetailActivity.this, "关注成功!");
						if(!isFocus){
							isFocusChange=true;
						}else{
							isFocusChange=false;
						}
						mBtnFocus.setText("取消关注");
					}else{
						MyToast.showShort(PalletDetailActivity.this, "取消关注成功!");
						if(isFocus){
							isFocusChange=true;
						}else{
							isFocusChange=false;
						}
						mBtnFocus.setText("关注");
					}
				}
				if(result.getStatusCode().equals("500")){
					MyToast.showShort(PalletDetailActivity.this, result.getMessage());
				}
			}
			@Override
			public void onFail(Context c, String errorMsg) {
				super.onFail(c, errorMsg);
			}
		};
		mFocusAccess = new PalletTransportAccess<List<PalletTransport>>(
				PalletDetailActivity.this, requestCallback);
		mFocusAccess.toFocus(mBaseApp.getUserssid(),mPallet.id,action);
	}
	
	/**
	 * 点击报价后，跳转到相应的界面
	 * @param bean
	 */
	private void toOfferPage(PalletTransport bean){
		Intent intent =new Intent();
		if(bean.shipping_type.toString().equals("1")){
			if(bean.shipment_type.equals("1")){
				intent.setClass(this, PalletSeaWholeOfferActivity.class);
			}else if(bean.shipment_type.equals("3")){
				intent.setClass(this, PalletSeaSpellOfferActivity.class);
			}else{
				intent.setClass(this, PalletSeaDiffOfferActivity.class);
			}
		}else if(bean.shipping_type.toString().equals("2")){
			intent.setClass(this, PalletAirOfferActivity.class);
		}else{
			if(bean.shipment_type.equals("1")){
				intent.setClass(this, PalletLordOfferActivity.class);
			}else if(bean.shipment_type.equals("2")){
				intent.setClass(this, PalletAirOfferActivity.class);
				intent.putExtra("palletType", "陆运");
			}
		}
		intent.putExtra("palletTransport", bean);
		startActivity(intent);
	}
	
	@Override
	public void onBackPressed() {
		if(isFocusChange){
			setResult(RESULT_OK);
		}
		finish();
	}
}
