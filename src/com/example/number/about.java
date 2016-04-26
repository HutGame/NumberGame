package com.example.number;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class about extends Activity{
	private Button exit;
	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);
		super.onCreate(savedInstanceState);
		exit=(Button)findViewById(R.id.button2);
		init();
		exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(about.this,start.class);
				startActivity(intent);
			}

		}

				);


	}
	private void init(){
		webView = (WebView) findViewById(R.id.webView1);
		//WebView����web��Դ
		webView.loadUrl("http://wxz.name/tmp/Rank/about.html");    //����
		//����WebViewĬ��ʹ�õ�������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ��WebView��
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				//����ֵ��true��ʱ�����ȥWebView�򿪣�Ϊfalse����ϵͳ�����������������
				view.loadUrl(url);
				return true;
			}
		});}

}
