package com.ateam.shippingcity.adapter;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.activity.PalletAirOfferActivity;
import com.ateam.shippingcity.activity.PalletLordOfferActivity;
import com.ateam.shippingcity.activity.PalletSeaDiffOfferActivity;
import com.ateam.shippingcity.activity.PalletSeaSpellOfferActivity;
import com.ateam.shippingcity.activity.PalletSeaWholeOfferActivity;
import com.ateam.shippingcity.activity.PersonalLoginActivity;
import com.ateam.shippingcity.application.HBaseApp;
import com.ateam.shippingcity.model.PalletTransport;
import com.ateam.shippingcity.utils.SysUtil;

/**
 * 海运列表适配器
 * @version 
 * @create_date 2015-3-28上午9:52:09
 */
public class PalletTransportAdapter extends HBaseAdapter<PalletTransport>{

	private Context c;
	public PalletTransportAdapter(Context c, List<PalletTransport> datas) {
		super(c, datas);
		this.c=c;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void convert(ViewHolder holder, final PalletTransport bean) {
		// TODO Auto-generated method stub
		((TextView)holder.getView(R.id.tv_remainTime)).setText(SysUtil.getRemainTime(bean.deadlinetime));
		TextView mTvBoxType = ((TextView)holder.getView(R.id.tv_boxType));
		//根据详情的不同，设置不同的明细
		if(bean.shipment_type.equals("1")){
			mTvBoxType.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.list_zhengxiang_great_icon));
			StringBuffer Describe=new StringBuffer();
			if(bean.boxtype.size()==bean.number.size()){
				for (int i = 0; i < bean.boxtype.size(); i++) {
					if(!bean.boxtype.get(i).equals("")){
						Describe.append(bean.boxtype.get(i)+"*"+bean.number.get(i));
					}
					if((bean.number.size()-i)>=2){
						Describe.append("、");
					}
				}
			}
			((TextView)holder.getView(R.id.tv_palletDescribe)).setText(Describe.toString());
		}else if(bean.shipment_type.equals("2")){
			if(bean.shipping_type.equals("3")){
				mTvBoxType.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.list_sanhuo_groceries_great_icon));
			}else{
				mTvBoxType.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.list_san_groceries_great_icon));
			}
			StringBuffer Describe=new StringBuffer();
			Describe.append("件数:"+bean.packages+";"+"毛重:"+bean.weight+";"+"体积:"+bean.volume+";");
			if(bean.size.size()==3){
				Describe.append("长:"+bean.size.get(0)+";"+"宽:"+bean.size.get(1)+";"+"高:"+bean.size.get(2)+";");
			}
			((TextView)holder.getView(R.id.tv_palletDescribe)).setText(Describe);
		}else{
			mTvBoxType.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.list_of_pinxiang_great_icon));
			StringBuffer Describe=new StringBuffer();
			Describe.append("件数:"+bean.packages+";"+"毛重:"+bean.weight+";"+"体积:"+bean.volume+";");
			if(bean.size.size()==3){
				Describe.append("长:"+bean.size.get(0)+";"+"宽:"+bean.size.get(1)+";"+"高:"+bean.size.get(2)+";");
			}
			((TextView)holder.getView(R.id.tv_palletDescribe)).setText(Describe);
		}
		((TextView)holder.getView(R.id.tv_placeBegin)).setText(bean.initiation);
		((TextView)holder.getView(R.id.tv_placeEnd)).setText(bean.destination);
		//设置当前运输的状态
		if(bean.shipping_type.toString().equals("1")){
			((TextView)holder.getView(R.id.tv_transportType)).setText("海运");
		}else if(bean.shipping_type.toString().equals("2")){
			((TextView)holder.getView(R.id.tv_transportType)).setText("空运");
			mTvBoxType.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.list_air_transport_great_icon));
		}else{
			((TextView)holder.getView(R.id.tv_transportType)).setText("陆运");
		}
		((TextView)holder.getView(R.id.tv_transportTimeBegin)).setText(bean.startime);
		((TextView)holder.getView(R.id.tv_transportTimeEnd)).setText(bean.endtime);
		TextView mOffer = ((TextView)holder.getView(R.id.tv_offer));
		//判断是否是已经报价，或是已经截止
		if(bean.ifbid!=null&&bean.ifbid.equals("1")){
			((TextView)holder.getView(R.id.tv_lableRemainTime)).setVisibility(View.VISIBLE);
			TextView lableTitle = ((TextView)holder.getView(R.id.tv_remainTime));
			lableTitle.setText(SysUtil.getRemainTime(bean.deadlinetime));
			lableTitle.setTextColor(c.getResources().getColor(R.color.coralColor));
			lableTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			mOffer.setVisibility(View.VISIBLE);
			mOffer.setClickable(false);
			mOffer.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.quoted_bg_gray));
			if(SysUtil.getRemainTime(bean.deadlinetime).equals("0小时")){
				((TextView)holder.getView(R.id.tv_lableRemainTime)).setVisibility(View.GONE);
				lableTitle.setText("本单报价已截止");
				lableTitle.setTextColor(c.getResources().getColor(R.color.pallet_item_grey_title));
				lableTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			}
		}else if(SysUtil.getRemainTime(bean.deadlinetime).equals("0小时")){
			((TextView)holder.getView(R.id.tv_lableRemainTime)).setVisibility(View.GONE);
			TextView lableTitle = ((TextView)holder.getView(R.id.tv_remainTime));
			lableTitle.setText("本单报价已截止");
			lableTitle.setTextColor(c.getResources().getColor(R.color.pallet_item_grey_title));
			lableTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			mOffer.setVisibility(View.GONE);
//			mOffer.setClickable(false);
//			mOffer.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.round_rect_bg_gray));
		}else{
			((TextView)holder.getView(R.id.tv_lableRemainTime)).setVisibility(View.VISIBLE);
			TextView lableTitle = ((TextView)holder.getView(R.id.tv_remainTime));
			lableTitle.setText(SysUtil.getRemainTime(bean.deadlinetime));
			lableTitle.setTextColor(c.getResources().getColor(R.color.coralColor));
			lableTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
			mOffer.setVisibility(View.VISIBLE);
			mOffer.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.btn_offer_selector));
			mOffer.setClickable(true);
			mOffer.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					if(TextUtils.isEmpty(((HBaseApp)c.getApplicationContext()).getUserssid())){
						Intent intent=new Intent(c, PersonalLoginActivity.class);
						c.startActivity(intent);
					}else{
						toOfferPage(bean);
					}
				}
			});
		}
	}
	
	@Override
	public int getResId() {
		// TODO Auto-generated method stub
		return R.layout.item_pallet_sea_transport;
	}
	
	/**
	 * 点击报价后，跳转到相应的界面
	 * @param bean
	 */
	private void toOfferPage(PalletTransport bean){
		Intent intent =new Intent();
		if(bean.shipping_type.toString().equals("1")){
			if(bean.shipment_type.equals("1")){
				intent.setClass(c, PalletSeaWholeOfferActivity.class);
			}else if(bean.shipment_type.equals("3")){
				intent.setClass(c, PalletSeaSpellOfferActivity.class);
			}else{
				intent.setClass(c, PalletSeaDiffOfferActivity.class);
			}
		}else if(bean.shipping_type.toString().equals("2")){
			intent.setClass(c, PalletAirOfferActivity.class);
		}else{
			if(bean.shipment_type.equals("1")){
				intent.setClass(c, PalletLordOfferActivity.class);
			}else if(bean.shipment_type.equals("2")){
				intent.setClass(c, PalletAirOfferActivity.class);
				intent.putExtra("palletType", "陆运");
			}
		}
		intent.putExtra("palletTransport", bean);
		c.startActivity(intent);
	}

}
