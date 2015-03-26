package com.ateam.shippingcity;

import com.ateam.shippingcity.activity.HBaseActivity;
import com.ateam.shippingcity.utils.AppManager;

import android.os.Bundle;

public class MainActivity extends HBaseActivity {
	private long currTime=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActionBarTitle("测试");
		setBaseContentView(R.layout.activity_main);
	}
	
	@Override
	public void onBackPressed() {
		if(System.currentTimeMillis()-currTime>2000){
			currTime=System.currentTimeMillis();
			showMsg(MainActivity.this, "再按一次退出程序");
		}else{
			AppManager.getInstance().ExitApp();
		}
	}
	
}
