package com.ateam.shippingcity.widget;

import com.ateam.shippingcity.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PalletWholeOfferItem extends LinearLayout{

	private View view;
	private TextView mTvBox;
	private View mViewLine;
	private EditText mEtBox;

	public PalletWholeOfferItem(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.item_pallet_whole_offer, this);
        initview();
	}
	
	public PalletWholeOfferItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.item_pallet_whole_offer, this);
        initview();
    }
	
	private void initview(){
		mTvBox=(TextView)view.findViewById(R.id.tv_box);
		mEtBox=(EditText)view.findViewById(R.id.et_box);
		mViewLine=(View)view.findViewById(R.id.view_line);
	}
	
	public void setTvBox(String boxType){
		mTvBox.setText(boxType);
	}
	
	public EditText getBox(){
		return mEtBox;
	}
	
	/**
	 * 隐藏线
	 */
	public View getLine(){
		return mViewLine;
	}

}
