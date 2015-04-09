package com.ateam.shippingcity.utils;

import com.ateam.shippingcity.R;
import com.ateam.shippingcity.access.PersonalAccess;
import com.ateam.shippingcity.access.I.HRequestCallback;
import com.ateam.shippingcity.model.Respond;
import com.ateam.shippingcity.model.UpdateInfo;
import com.ateam.shippingcity.service.APKDownloadService;
import com.google.gson.reflect.TypeToken;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
/**
 * 
 * @author 李晓伟
 * @Create_date 2015-2-3 上午11:58:14
 * @Version  
 * @TODO 检测更新工具
 */
public class CheckUpdateUtil {
	public static final String UPDATE_SETTING="update_setting";
	private static CheckUpdateUtil instance;
	private UpdateCallback updateCallback;
	private static Context mContext;
	private boolean isNewVersion=false;//是否有新版本
	
	public static CheckUpdateUtil getInstance(Context context){
		mContext=context;
		if(instance==null){
			instance= new CheckUpdateUtil();
		}
		return instance;
	}
	private CheckUpdateUtil(){
	}
	/**
	 * 
	 * @author 李晓伟
	 * 2015-3-10 下午4:40:08
	 * @param isShow 是否显示提示(false:静默检测)
	 * @TODO 
	 */
	public void check(final boolean isShow) {
		HRequestCallback<Respond<UpdateInfo>> requestCallback=new HRequestCallback<Respond<UpdateInfo>>() {
			@Override
			public void onFail(Context c, String errorMsg) {
				if(isShow){
					super.onFail(c, errorMsg);
				}
			}
			@SuppressWarnings("unchecked")
			@Override
			public Respond<UpdateInfo> parseJson(String jsonStr) {
				java.lang.reflect.Type type = new TypeToken<Respond<UpdateInfo>>() {
				}.getType();
				return (Respond<UpdateInfo>) JSONParse.jsonToObject(jsonStr, type);
			}
			
			@Override
			public void onSuccess(Respond<UpdateInfo> result) {
				setNewVersion(false);
				if(isShow){
					if(result.isSuccess()&&result.getDatas()!=null){
						showDownloadDialog(0,result.getDatas());
					}else{
						MyToast.showShort(mContext, "抱歉，更新发生异常！");
					}
					
				}else{//静默检测
					/*if(!result.isSuccess()){
					}else if(result.getDatas()!=null){
						SharedPreferences sp=mContext.getSharedPreferences(CheckUpdateUtil.UPDATE_SETTING, 0);
						setNewVersion(true);
						if(sp.getBoolean("is_alert", true)||result.getDatas().getType().equals("2")){
							showDownloadDialog(1,result.getDatas());
						}
					}else{
					}*/
				}
				if(updateCallback!=null){
					updateCallback.onResult(isNewVersion());
				}
			}
		};
		PersonalAccess<UpdateInfo> access=new PersonalAccess<UpdateInfo>(mContext, requestCallback);
		access.setIsShow(isShow);
		access.updateCheck(getVersionName());
	}
	/**
	 * 
	 * @author 李晓伟
	 * 2015-2-2 下午6:06:40
	 * @param c
	 * @return
	 * @TODO 获取版本号
	 */
	public String getVersionName(){
		try {
			PackageManager packageManager = mContext.getPackageManager();
	        PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(),0);
			String version = packInfo.versionName;
			return version;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
        return "";
	}
	/**
	 * 
	 * @author 李晓伟
	 * 2015-2-3 上午9:13:06
	 * @param context
	 * @return
	 * @TODO 检测网络
	 */
	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	/**
	 * 
	 * @author 李晓伟
	 * 2014-7-7 上午9:27:08
	 * @TODO 提示更新窗口
	 */
	public void showDownloadDialog(int type, final UpdateInfo updateInfo){
		if(type==1)return;
		final AlertDialog dialog=new AlertDialog.Builder(mContext).create();
		View view;
		if(updateInfo!=null&&(updateInfo.getType().equals("1")||updateInfo.getType().equals("2"))){
			setNewVersion(true);
			view=LayoutInflater.from(mContext).inflate(R.layout.item_update_dialog, null);
			((TextView)view.findViewById(R.id.txt_new_version)).setText("新版本V"+updateInfo.getVersion_code()+"新功能");
			String[] contents=updateInfo.getUpgrade_point().split("\\|");
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < contents.length; i++) {
				sb.append((i+1)+"、"+contents[i]);
				if(i<contents.length-1){
					sb.append("\n");
				}
			}
			((TextView)view.findViewById(R.id.txt_update_content)).setText(sb.toString());
			view.findViewById(R.id.txt_cancel).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			view.findViewById(R.id.txt_download).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					//开始下载
					Intent downloadIntent = new Intent(mContext,APKDownloadService.class);
					downloadIntent.putExtra(APKDownloadService.KEY_DOWNLOAD_URL, updateInfo.getApk_url());
					downloadIntent.putExtra(APKDownloadService.KEY_VERSION, updateInfo.getVersion_code());
					mContext.startService(downloadIntent);
					if(updateInfo.getType().equals("2")){
						AppManager.getInstance().ExitApp();
						System.exit(0);
					}
				}
			});
		}else{
			setNewVersion(false);
			view=LayoutInflater.from(mContext).inflate(R.layout.item_no_update_dialog, null);
			view.findViewById(R.id.txt_sure).setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
		}
		dialog.setView(view);
		dialog.show();
		dialog.getWindow().setGravity(Gravity.CENTER);
	}
	
	public interface UpdateCallback{
		void onResult(boolean isNewVersion);
	}

	public UpdateCallback getUpdateCallback() {
		return updateCallback;
	}

	public void setUpdateCallback(UpdateCallback updateCallback) {
		this.updateCallback = updateCallback;
	}
	public boolean isNewVersion() {
		return isNewVersion;
	}
	public void setNewVersion(boolean isNewVersion) {
		this.isNewVersion = isNewVersion;
	}
}
