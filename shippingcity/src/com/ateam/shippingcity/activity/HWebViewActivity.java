package com.ateam.shippingcity.activity;


import com.ateam.shippingcity.R;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ProgressBar;
/**
 * 
 * @author 李晓伟
 * @Create_date 2015-3-10 上午10:39:46
 * @Version 
 * @TODO 网页查看器
 */
public class HWebViewActivity extends HBaseActivity{
	public static final String KEY_URL="url";
	public static final String KEY_TITLE="title";
	private WebView webView;
	private ProgressBar progressBar;
	private String url;
	private String title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setBaseContentView(R.layout.activity_web_view);
		init();
	}
	private void init() {
		title=getIntent().getStringExtra(KEY_TITLE);
		if(title!=null){
			setActionBarTitle(title);
		}
		url=getIntent().getStringExtra(KEY_URL);
		if(url==null){
			onLoadFail("连接地址有误");
			return;
		}
		Log.e("load url:", url);
		initWebView();
	}
	private void initWebView() {
		progressBar = (ProgressBar) findViewById(R.id.pb);
		progressBar.setMax(100);
		webView = (WebView) findViewById(R.id.webView_detail);
		//webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);// 让网页自适应屏幕宽度
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				webView.loadUrl(url);
				return true;
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}

			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				super.onReceivedError(view, errorCode, description, failingUrl);
			}

		});
		webView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress == 100) {
					progressBar.setVisibility(View.GONE);
				} else {
					if (progressBar.getVisibility() == View.GONE)
						progressBar.setVisibility(View.VISIBLE);
					progressBar.setProgress(newProgress);
				}
				super.onProgressChanged(view, newProgress);

			}

		});
		webView.loadUrl(url);
	}
}
