package com.example.number;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class pk extends Activity{
	private TextView pk1;
	private TextView pk2;
	private TextView pk3;
	private TextView pk4;
	private TextView pk5;
	private TextView pk6;
	private TextView pk7;
	private TextView pk8;
	private TextView pk9;
	private Button Load;
	private Button exit;
	private String fileName;
	private String uploadFile ;
	private WebView webView;

	public  String nametxt;
	public  String nametxt1; //�ļ���
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pk);
		super.onCreate(savedInstanceState);	
		Load=(Button)findViewById(R.id.button1);
		exit=(Button)findViewById(R.id.button2);
/*		webView = (WebView)findViewById(R.id.webView1);
		webView.loadUrl("http://baidu.com");*/
/*		pk1 = (TextView) findViewById(R.id.textView1);
		pk2= (TextView) findViewById(R.id.textView2);
		pk3 = (TextView) findViewById(R.id.textView3);
		pk4 = (TextView) findViewById(R.id.textView4);
		pk5= (TextView) findViewById(R.id.textView5);
		pk6 = (TextView) findViewById(R.id.textView6);
		pk7 = (TextView) findViewById(R.id.textView7);
		pk8= (TextView) findViewById(R.id.textView8);
		pk9 = (TextView) findViewById(R.id.textView9);*/
		init();
		
		nametxt1=Secure.getString(getBaseContext().getContentResolver(), Secure.ANDROID_ID);  //��û�����
		
		readname();
		
		//nametxt1=editText.getText().toString();
		Load.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {  //�ϴ�ʱ����Ϣ
				uploadFile="/data/data/com.example.number/files/"+nametxt1+".txt";
				readname();      //��ȡ�û���
                save("�û�����"+readname1()+"\n��һ�أ�"+read(1)+"\n�ڶ��أ�"+read(2)+"\n�����أ�"+read(3)+"\n���Ĺأ�"+read(4)+"\n����أ�"+read(5)+"\n�����أ�"+read(6)+"\n���߹أ�"+read(7)+"\n�ڰ˹أ�"+read(8)+"\n�ھŹأ�"+read(9));
    			File file = new File(uploadFile);   //�ϴ�����
				new FileUploadAsyncTask(pk.this).execute(file);
			}
			
		});	
		
		exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(pk.this,start.class);
				startActivity(intent);
			}
			
		});
	}
	
    private void init(){
        webView = (WebView) findViewById(R.id.webView1);
        //WebView����web��Դ
       webView.loadUrl("http://wxz.name/tmp/Rank/Rank.html");    //����
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
	private double read(int diffcult) {     
		switch(diffcult){
		case 1: 
			fileName = "bestRecord1.txt";
			break;
		case 2: 
			fileName = "bestRecord2.txt";
			break;
		case 3: 
			fileName = "bestRecord3.txt";
			break;
		case 4: 
			fileName = "bestRecord4.txt";
			break;
		case 5: 
			fileName = "bestRecord5.txt";
			break;
		case 6: 
			fileName = "bestRecord6.txt";
			break;
		case 7: 
			fileName = "bestRecord7.txt";
			break;
		case 8: 
			fileName = "bestRecord8.txt";
			break;
		case 9: 
			fileName = "bestRecord9.txt";
			break;
		}
		try {
			FileInputStream inputStream = this.openFileInput(fileName);
			byte[] bytes = new byte[1024];
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			while (inputStream.read(bytes) != -1) {
				arrayOutputStream.write(bytes, 0, bytes.length);
			}
			inputStream.close();
			arrayOutputStream.close();
			String content = new String(arrayOutputStream.toByteArray());
			//  showTextView.setText(content);
			return Double.valueOf(content.toString());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



		return 10.0;
	}
	
	private void readname() {     	
			fileName = "name.txt";
		try {
			FileInputStream inputStream = this.openFileInput(fileName);
			byte[] bytes = new byte[1024];
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			while (inputStream.read(bytes) != -1) {
				arrayOutputStream.write(bytes, 0, bytes.length);
			}
			inputStream.close();
			arrayOutputStream.close();
			String content = new String(arrayOutputStream.toByteArray());
			//  showTextView.setText(content);
			nametxt=content;
			


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
	private String readname1() {     	
		fileName = "name.txt";
	try {
		FileInputStream inputStream = this.openFileInput(fileName);
		byte[] bytes = new byte[1024];
		ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
		while (inputStream.read(bytes) != -1) {
			arrayOutputStream.write(bytes, 0, bytes.length);
		}
		inputStream.close();
		arrayOutputStream.close();
		String content = new String(arrayOutputStream.toByteArray());
		//  showTextView.setText(content);
		return content;
		


	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
return "none";
	
}
	public void save(String content) {
	
			fileName = nametxt1+".txt";
		
		try {
			/* �����û��ṩ���ļ������Լ��ļ���Ӧ��ģʽ����һ�������.�ļ�����ϵͳ��Ϊ�㴴��һ���ģ�
			 * ����Ϊʲô����ط�����FileNotFoundException�׳�����Ҳ�Ƚ����ơ���Context�������������
			 *   public abstract FileOutputStream openFileOutput(String name, int mode)
			 *   throws FileNotFoundException;
			 * openFileOutput(String name, int mode);
			 * ��һ�������������ļ����ƣ�ע��������ļ����Ʋ��ܰ����κε�/����/���ַָ�����ֻ�����ļ���
			 *          ���ļ��ᱻ������/data/data/com.example.number/files/bestRecord.txt
			 * �ڶ��������������ļ��Ĳ���ģʽ
			 *             MODE_PRIVATE ˽�У�ֻ�ܴ�������Ӧ�÷��ʣ� �ظ�д��ʱ���ļ�����
			 *             MODE_APPEND  ˽��   �ظ�д��ʱ�����ļ���ĩβ����׷�ӣ������Ǹ��ǵ�ԭ�����ļ�
			 *             MODE_WORLD_READABLE ����  �ɶ�
			 *             MODE_WORLD_WRITEABLE ���� �ɶ�д
			 *  */
			FileOutputStream outputStream = openFileOutput(fileName,
					Activity.MODE_PRIVATE);
			outputStream.write(content.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
