package com.ateam.shippingcity.activity;

import java.lang.reflect.Type;
import java.util.List;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.MyQuoteAccess;
import com.ateam.shippingcity.access.PalletTransportAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.MyQuoteToConfirmDetail;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.utils.JSONParse;
import com.ateam.shippingcity.utils.MyToast;
import com.ateam.shippingcity.utils.SysUtil;
import com.ateam.shippingcity.widget.weinxinImageShow.MyGridAdapter;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
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
	private View mLineAddPhoto;//线
	private GridView mGvAddPhoto;//添加显示图片布局
	private ImageView mIvType;//
	
	private PalletTransport mPallet;
	
	private MyQuoteAccess<MyQuoteToConfirmDetail> access;
	private PalletTransportAccess<List<PalletTransport>> mFocusAccess;
	
	private String[] urls = {
            "http://img0.bdstatic.com/img/image/shouye/leimu/mingxing2.jpg",
            "http://c.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=cdab1512d000baa1be2c44b3772bc82f/91529822720e0cf3855c96050b46f21fbf09aaa1.jpg",
            "http://g.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=ccd33b46d53f8794d7ff4b26e2207fc9/0d338744ebf81a4c0f993437d62a6059242da6a1.jpg",
            "http://f.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=6b62f61bac6eddc422e7b7f309e0c7c0/6159252dd42a2834510deef55ab5c9ea14cebfa1.jpg",
            "http://c.hiphotos.bdimg.com/album/s%3D900%3Bq%3D90/sign=b8658f17f3d3572c62e290dcba28121a/5fdf8db1cb134954bb97309a574e9258d0094a47.jpg",
            "http://g.hiphotos.bdimg.com/album/s%3D680%3Bq%3D90/sign=e58fb67bc8ea15ce45eee301863b4bce/a5c27d1ed21b0ef4fd6140a0dcc451da80cb3e47.jpg"
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("货盘详情");
		setBaseContentView(R.layout.activity_pallet_detail);
		initView();
		initGridView();
		initData();
	}
	
	/**
	 * 控件实例化
	 */
	@SuppressWarnings("deprecation")
	private void initView(){
		mPallet=(PalletTransport) getIntent().getSerializableExtra("palletTransport");
		mTvTransportType=(TextView)findViewById(R.id.tv_transportType);
		mTvBeginPlace=(TextView)findViewById(R.id.tv_beginPlace);
		mTvEndPlace=(TextView)findViewById(R.id.tv_endPlace);
		mTvGoBeginTime=(TextView)findViewById(R.id.tv_goBeginTime);
		mTvGoEndTime=(TextView)findViewById(R.id.tv_goEndTime);
		mTvBoxType=(TextView)findViewById(R.id.tv_boxType);
		mLineAddPhoto=(View)findViewById(R.id.view_addPhoto);
		mLinearAddPhoto=(LinearLayout)findViewById(R.id.layout_addPhoto);
		mTvNotice=(TextView)findViewById(R.id.tv_notice);
		mTvOfferEndTime=(TextView)findViewById(R.id.tv_offerEndTime);
		mBtnFocus=(Button)findViewById(R.id.btn_focus);
		mBtnMyOffer=(Button)findViewById(R.id.btn_myOffer);
		mGvAddPhoto=(GridView)findViewById(R.id.gv_addPhoto);
		mIvType=(ImageView)findViewById(R.id.iv_type);
		mBtnFocus.setOnClickListener(this);
		mBtnMyOffer.setOnClickListener(this);
		if(mPallet.ifbid!=null&&mPallet.ifbid.equals("1")){
			findViewById(R.id.tv_view).setVisibility(View.GONE);
			mBtnMyOffer.setText("我的报价");
		}else if(!SysUtil.getRemainTime(mPallet.deadlinetime).equals("0小时")){
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
			mIvType.setBackgroundDrawable(getResources().getDrawable(R.drawable.pallet_details_san_groceries_small_icon));
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
	private void initGridView(){
		int size=0;
		if(urls.length>5){
			size=5;
		}else{
			size=urls.length;
		}
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(  
        		(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 42*size, getResources().getDisplayMetrics()), 
                LinearLayout.LayoutParams.FILL_PARENT);  
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
	}
	
	/**
	 * 提交数据
	 */
	private void initData(){
		HRequestCallback<Respond<MyQuoteToConfirmDetail>> requestCallback = new HRequestCallback<Respond<MyQuoteToConfirmDetail>>() {

			@SuppressWarnings("unchecked")
			@Override
			public Respond<MyQuoteToConfirmDetail> parseJson(String jsonStr) {
				Log.e("", "jsonStr"+jsonStr);
				Type type = new com.google.gson.reflect.TypeToken<Respond<MyQuoteToConfirmDetail>>() {
				}.getType();
				return (Respond<MyQuoteToConfirmDetail>) JSONParse.jsonToObject(
						jsonStr, type);
			}

			@Override
			public void onSuccess(Respond<MyQuoteToConfirmDetail> result) {
				if(result.isSuccess()){
					MyQuoteToConfirmDetail datas = result.getDatas();
					String shipping_type = datas.getShipping_type();
					String shipment_type = datas.getShipment_type();
					if(shipping_type.equals("1")){
						mTvTransportType.setText("海运");
					}else if(datas.getShipping_type().equals("2")){
						mTvTransportType.setText("空运");
					}else{
						mTvTransportType.setText("陆运");
					}
					mTvBeginPlace.setText(datas.getInitiation());
					mTvEndPlace.setText(datas.getDestination());
					mTvGoBeginTime.setText(datas.getStartime());
					mTvGoEndTime.setText(datas.getEndtime());
					StringBuffer description=new StringBuffer();
					if(shipment_type.equals("1")){
						List<String> type=datas.getType();
						List<String> num=datas.getNum();
						if(type.size()>0){
							description.append("箱型：");
							for (int i = 0; i < type.size(); i++) {
								description.append(type.get(i)+",");
								description.append("数量"+num.get(i)+"箱");
								if(i<type.size()-1){
									description.append(";");
								}
								else description.append("。");
							}
						}
					}else{
						description.append("件数："+datas.getPackages()+";");
						description.append("毛重："+datas.getWeight()+"kg;");
						description.append("体积："+datas.getVolume()+"立方;");
						description.append("单件尺寸："+datas.getSize()+"。");
					}
					mTvBoxType.setText(description);
					mTvNotice.setText(datas.getRemarks());
					mTvOfferEndTime.setText(datas.getDeadlinetime());
				}
				if(result.getStatusCode().equals("500")){
					MyToast.showShort(PalletDetailActivity.this, result.getMessage());
				}
			}
		};
		access = new MyQuoteAccess<MyQuoteToConfirmDetail>(
				PalletDetailActivity.this, requestCallback);
		access.getMyQuoteDetail(mBaseApp.getUserssid(), mPallet.id);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.btn_focus:
			if(mBtnFocus.getText().equals("关注")){
				toFocus("add");
			}else{
				toFocus("delete");
			}
			break;
		case R.id.btn_myOffer:
			toOfferPage(mPallet);
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
						mBtnFocus.setText("取消关注");
					}else{
						MyToast.showShort(PalletDetailActivity.this, "取消关注成功!");
						mBtnFocus.setText("关注");
					}
				}
				if(result.getStatusCode().equals("500")){
					MyToast.showShort(PalletDetailActivity.this, result.getMessage());
				}
			}
			@Override
			public void onFail(Context c, String errorMsg) {
				// TODO Auto-generated method stub
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
	
}
