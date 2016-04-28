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
	public  String nametxt1; //文件名
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
		
		nametxt1=Secure.getString(getBaseContext().getContentResolver(), Secure.ANDROID_ID);  //获得机器码
		
		readname();
		
		//nametxt1=editText.getText().toString();
		Load.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {  //上传时间信息
				uploadFile="/data/data/com.example.number/files/"+nametxt1+".txt";
				readname();      //读取用户名
                save("用户名："+readname1()+"\n第一关："+read(1)+"\n第二关："+read(2)+"\n第三关："+read(3)+"\n第四关："+read(4)+"\n第五关："+read(5)+"\n第六关："+read(6)+"\n第七关："+read(7)+"\n第八关："+read(8)+"\n第九关："+read(9));
    			File file = new File(uploadFile);   //上传代码
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
        //WebView加载web资源
       webView.loadUrl("http://wxz.name/tmp/Rank/Rank.html");    //排名
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
       webView.setWebViewClient(new WebViewClient(){
           @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
               //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
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
			/* 根据用户提供的文件名，以及文件的应用模式，打开一个输出流.文件不存系统会为你创建一个的，
			 * 至于为什么这个地方还有FileNotFoundException抛出，我也比较纳闷。在Context中是这样定义的
			 *   public abstract FileOutputStream openFileOutput(String name, int mode)
			 *   throws FileNotFoundException;
			 * openFileOutput(String name, int mode);
			 * 第一个参数，代表文件名称，注意这里的文件名称不能包括任何的/或者/这种分隔符，只能是文件名
			 *          该文件会被保存在/data/data/com.example.number/files/bestRecord.txt
			 * 第二个参数，代表文件的操作模式
			 *             MODE_PRIVATE 私有（只能创建它的应用访问） 重复写入时会文件覆盖
			 *             MODE_APPEND  私有   重复写入时会在文件的末尾进行追加，而不是覆盖掉原来的文件
			 *             MODE_WORLD_READABLE 公用  可读
			 *             MODE_WORLD_WRITEABLE 公用 可读写
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
