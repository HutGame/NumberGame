package com.example.number;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class start extends Activity{
	private Button easy;   
	private Button easyUp; 
	private Button middle; 
	private Button difficult;
	private TextView show1;
	private TextView show2;
	private TextView show3;
	public static MainActivity m1;
	private String fileName = "bestRecord.txt";
	private static int difficulty=0;    //用户选择的游戏难度静态成员
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.start);
	    m1=new MainActivity();
	    m1.check=false;
		easy =(Button) findViewById(R.id.button11);
		easyUp = (Button) findViewById(R.id.button12);
		middle = (Button) findViewById(R.id.button13);
		show1 = (TextView) findViewById(R.id.textView2);
		show2 = (TextView) findViewById(R.id.TextView01);
		show3 = (TextView) findViewById(R.id.TextView02);
		
		showtxt();  //更新排名榜
		
		/*三个不同难度的按钮监听*/
		easy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(1);
				intentGameActivity();        //跳转到游戏界面
				finish();
			}
		});
		easyUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(2);
				intentGameActivity();
				finish();
			}
		});
		middle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(3);
				intentGameActivity();
				finish();
			}
		});
		
	}
	
	
	
	protected void intentGameActivity() {
		Intent intent = new Intent(start.this,MainActivity.class);
		startActivity(intent);
	}
/*	得到困难级别*/
	public int getDifficulty() {
		return difficulty;
	}
/*	设置困难级别*/
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	
	/*读取文本里排名时间*/
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
	
	
/*	监听Main返回事件*/
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
	if(m1.check==true)
		{
		    MainActivity m = new MainActivity();
		    m.mc.cancel();
		}
	

    return super.onKeyDown(keyCode, event);  
} 
	
	
/*   主界面按返回键回到桌面*/
	public void onBackPressed() {  
	    super.onBackPressed();  
		Intent home = new Intent(Intent.ACTION_MAIN);  
		home.addCategory(Intent.CATEGORY_HOME);   
		startActivity(home); 
	}  

	   
	   

/*      显示排名榜*/
	public void showtxt(){
		show1.setText("入门:"+read(1)+"秒");
		show2.setText("简单:"+read(2)+"秒");
		show3.setText("中等:"+read(3)+"秒");
	}
	

	
}
