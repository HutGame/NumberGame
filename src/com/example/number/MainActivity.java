package com.example.number;


import android.support.v4.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends start{
	/* 毫秒倒计时内部类*/
	private String fileName = "bestRecord.txt";

	public class myCountDownTimer extends CountDownTimer{

		public myCountDownTimer(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}


		@Override
		public void onTick(long millisUntilFinished) {
			showTimeInSecond.setText("倒计时(" + millisUntilFinished / 1000.0 + ")...");
			useTime=millisUntilFinished;
		}

		@Override
		public void onFinish() {
			showTimeInSecond.setText(" 游戏失败");
			showDialog(); 
		}
	}
    
    public static boolean check=true;
	private long useTime=0;
	private int gameTime=10;
	private int countTime=0;
	private int first=1;
	private String showTime=null;
	private TextView textView=null;
	private TextView record=null;
	private TextView bestRecord=null;
	private double min=10.0;
	private int onlyUseFirst=0;  //只调用一次开始游戏
	private int state=0;     //解决bug
	private Button button1,button2,button3,button4,button5,button6,
	button7,button8,button9;
	Chronometer chronometer=null;
	Button startButton=null;
	TextView showTimeInSecond =null;
	static myCountDownTimer mc; 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
        
		initButton();
	
		showTimeInSecond = (TextView)findViewById(R.id.textView2);
		startButton = (Button) findViewById(R.id.Button10);
		record = (TextView) findViewById(R.id.textView3);
		bestRecord = (TextView) findViewById(R.id.textView4);
		textView = (TextView) findViewById(R.id.textView1);
		chronometer = (Chronometer) findViewById(R.id.chronometer);


		min=read(getDifficulty());   //程序打开时读取文本里面最佳成绩，并赋值给min;
		bestRecord.setText("最佳记录:"+min+"秒");

		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) { 
				initButton();
				listenButton();
				startButton.setVisibility(View.INVISIBLE);
				check=true;
				state=0;
				Toast.makeText(MainActivity.this,"计时开始", 1).show();
				if(getDifficulty()==1)
				{
					mc = new myCountDownTimer(gameTime*1000,1);   //总共10s  1毫秒执行一次onTick
					mc.start();
				}
				else if(getDifficulty()==2)   //只调用一次开始游戏
				{
					if(onlyUseFirst==0)
					{
						mc = new myCountDownTimer(gameTime*1000,1);   //总共10s  1毫秒执行一次onTick
						mc.start();
						onlyUseFirst=1;
					}
				}
				else if(getDifficulty()==3)
				{
					mc = new myCountDownTimer(gameTime*1000,1);   //总共10s  1毫秒执行一次onTick
					mc.start();
				}
				// 设置开始讲时时间   
				/*chronometer.setBase(SystemClock.elapsedRealtime());   
                // 开始记时   
                chronometer.start(); */
			}


		});
		/*chronometer             //设置时间监听器
         .setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {   
             @Override  
             public void onChronometerTick(Chronometer chronometer1) {   
                 // 如果开始计时到现在超过了startime秒   
                 if (SystemClock.elapsedRealtime()   
                         - chronometer1.getBase() > gameTime * 1000) {  
                	 state=1;
                     chronometer.stop();   
                     // 给用户提示   
                     showDialog();   
                 }
             }   
         });   */
	}   

	private void initButton() {
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
		button4 = (Button) findViewById(R.id.button4);
		button5 = (Button) findViewById(R.id.button5);
		button6 = (Button) findViewById(R.id.button6);
		button7 = (Button) findViewById(R.id.button7);
		button8 = (Button) findViewById(R.id.button8);
		button9 = (Button) findViewById(R.id.button9);
	}

	private void listenButton() {
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				button1.setVisibility(View.INVISIBLE);
				if(getDifficulty()==3)
					randomPosButton();
				button2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						button2.setVisibility(View.INVISIBLE);
						if(getDifficulty()==3)
							randomPosButton(); 
						button3.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								button3.setVisibility(View.INVISIBLE);
								if(getDifficulty()==3)
									randomPosButton();
								button4.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
										button4.setVisibility(View.INVISIBLE);
										if(getDifficulty()==3)
											randomPosButton();
										button5.setOnClickListener(new OnClickListener() {
											@Override
											public void onClick(View v) {
												button5.setVisibility(View.INVISIBLE);
												if(getDifficulty()==3)
													randomPosButton();
												button6.setOnClickListener(new OnClickListener() {
													@Override
													public void onClick(View v) {
														button6.setVisibility(View.INVISIBLE);
														if(getDifficulty()==3)
															randomPosButton();
														button7.setOnClickListener(new OnClickListener() {
															@Override
															public void onClick(View v) {
																button7.setVisibility(View.INVISIBLE);
																if(getDifficulty()==3)
																	randomPosButton();
																button8.setOnClickListener(new OnClickListener() {
																	@Override
																	public void onClick(View v) {
																		button8.setVisibility(View.INVISIBLE);
																		if(getDifficulty()==3)
																			randomPosButton();
																		button9.setOnClickListener(new OnClickListener() {
																			@Override
																			public void onClick(View v) {
																				button9.setVisibility(View.INVISIBLE);
																				if(state!=1)
																				{

																					mc.cancel();
																					if(useTime<gameTime*1000)
																					{
																						showSuccessDialog(); 
																						showTimeInSecond.setText(" 游戏成功");
																						record.setText("本次记录:"+(gameTime*1000-useTime)/1000.0+"秒");
																					}
																					showBestRecord();
																				}
																			}


																		});
																	}
																});
															}
														});
													}
												});
											}
										});
									}
								});
							}
						});
					}
				});
			}
		});
	}

	private void showButton() {
		button1.setVisibility(View.VISIBLE);
		button2.setVisibility(View.VISIBLE);
		button3.setVisibility(View.VISIBLE);
		button4.setVisibility(View.VISIBLE);
		button5.setVisibility(View.VISIBLE);
		button6.setVisibility(View.VISIBLE);
		button7.setVisibility(View.VISIBLE);
		button8.setVisibility(View.VISIBLE);
		button9.setVisibility(View.VISIBLE);
	}  

	protected void showSuccessDialog() {
 
		AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		builder.setIcon(R.drawable.happy);   
		builder.setTitle("恭喜您").setMessage("挑战成功")   
		.setPositiveButton("下一关", new DialogInterface.OnClickListener() {   
			@Override  
			public void onClick(DialogInterface dialog, int which) { 
				gameTime--;
				randomPosButton();
				//chronometer.setText("00:00");
				textView.setText("限时"+gameTime+"秒");
				showTimeInSecond.setText("倒计时工具");
				cancleListen();
				showButton();
				if(getDifficulty()==1)
				{
					startButton.setVisibility(View.VISIBLE);
				}
				else if(getDifficulty()==2)
				{
					listenButton();
					mc = new myCountDownTimer(gameTime*1000,1);   //总共10s  1毫秒执行一次onTick
					mc.start();                                    //直接开始计时
				}
				else if(getDifficulty()==3)
				{
					startButton.setVisibility(View.VISIBLE);
				}
			}
		});   
		builder.setCancelable(false);
		AlertDialog dialog = builder.create();   
		dialog.show(); 
	}
	/*
	 * 展现最佳时间
	 * 
	 * */
	protected void showBestRecord()        

	{
/*		if(first==1)
		{
			min=(gameTime*1000-useTime)/1000.0;
			first=0;
			bestRecord.setText("最佳记录:"+min+"秒");
			save(min);          //保存最佳纪录文件
		}
		else
		{*/
			if((gameTime*1000-useTime)/1000.0<min)
				min=(gameTime*1000-useTime)/1000.0;
			bestRecord.setText("最佳记录:"+min+"秒");
			save(min,getDifficulty());      //保存最佳纪录文件

	    //}

	}
	/*
	 * 游戏失败对话提示
	 * */
	protected void showDialog() {   
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);   
		builder.setIcon(R.drawable.cry);   
		builder.setTitle("好可惜哦!").setMessage("时间到,游戏失败")   
		.setPositiveButton("重新开始", new DialogInterface.OnClickListener() {   
			@Override  
			public void onClick(DialogInterface dialog, int which) {  
				initButton();
				randomPosButton();
				showButton();
				//chronometer.setText("00:00");
				cancleListen();  
				showTimeInSecond.setText("倒计时工具");
				if(getDifficulty()==1)
				{
					startButton.setVisibility(View.VISIBLE);
				}
				else if(getDifficulty()==2)
				{
					listenButton();
					mc = new myCountDownTimer(gameTime*1000,1);   //总共10s  1毫秒执行一次onTick
					mc.start();                                    //直接开始计时
				}
				else if(getDifficulty()==3)
				{
					startButton.setVisibility(View.VISIBLE);
				}
			}   
		});   
		builder.setCancelable(false);
		AlertDialog dialog = builder.create();   
		dialog.show();   
	}
	/*
	 * 按钮随机位置
	 * */
	/*protected void randomPosButton() {
		float x,y;
		y=(float) (Math.random()*700+100);
		x=(float) (Math.random()*40+60);
		button1.setX(x);
		button1.setY(y);
		y=(float) (Math.random()*700+100);
		x=(float) (Math.random()*40+120);
		button2.setX(x);
		button2.setY(y);
		y=(float) (Math.random()*700+100);
		x=(float) (Math.random()*40+180);
		button3.setX(x);
		button3.setY(y);
		y=(float) (Math.random()*700+100);
		x=(float) (Math.random()*40+240);
		button4.setX(x);
		button4.setY(y);
		y=(float) (Math.random()*700+100);
		x=(float) (Math.random()*40+300);
		button5.setX(x);
		button5.setY(y);
		y=(float) (Math.random()*700+100);
		x=(float) (Math.random()*40+360);
		button6.setX(x);
		button6.setY(y);
		y=(float) (Math.random()*700+100);
		x=(float) (Math.random()*40+420);
		button7.setX(x);
		button7.setY(y);
		y=(float) (Math.random()*700+100);
		x=(float) (Math.random()*40+480);
		button8.setX(x);
		button8.setY(y);
		y=(float) (Math.random()*700+100);
		x=(float) (Math.random()*40+540);
		button9.setX(x);
		button9.setY(y);
	}*/
	//按钮高度96 宽度128
	protected void randomPosButton() {
		
		button1.setY((float) (Math.random()*700+100));
		button1.setX((float) (Math.random()*540+40));
		button2.setY((float) (Math.random()*700+100));
		button2.setX((float) (Math.random()*540+40));
		button3.setY((float) (Math.random()*700+100));
		button3.setX((float) (Math.random()*540+40));
		button4.setY((float) (Math.random()*700+100));
		button4.setX((float) (Math.random()*540+40));
		button5.setY((float) (Math.random()*700+100));
		button5.setX((float) (Math.random()*540+40));
		button6.setY((float) (Math.random()*700+100));
		button6.setX((float) (Math.random()*540+40));
		button7.setY((float) (Math.random()*700+100));
		button7.setX((float) (Math.random()*540+40));
		button8.setY((float) (Math.random()*700+100));
		button8.setX((float) (Math.random()*540+40));
		button9.setY((float) (Math.random()*700+100));
		button9.setX((float) (Math.random()*540));
	}
	
	/*float getGoodX(float x)
	{
		float x1=(float) (Math.random()*540+40);
		while(Math.abs(x-x1)<96)
		{
			x1=(float) (Math.random()*540+40);
		}
		return x1;
	}
	float getGoodY(float y)
	{
		float y1=(float) (Math.random()*700+100);
		while(Math.abs(y-y1)<128)
		{
			y1=(float) (Math.random()*700+100);
		}
		return y1;
	}                //待优化
    protected void randomPosButton() {
    	float x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6,x7,y7,x8,y8,x9,y9;
		x1=(float) (Math.random()*540+40);
		y1=(float) (Math.random()*700+100);
		button1.setY(y1);
		button1.setX(x1);
		x2=getGoodX(x1);
		y2=getGoodY(y1);
		button2.setY(y2);
		button2.setX(x2);
		x3=getGoodX(x2);
		y3=getGoodY(y2);
		button3.setY(y3);
		button3.setX(x3);
		x4=getGoodX(x3);
		y4=getGoodY(y3);
		button4.setY(y4);
		button4.setX(x4);
		x5=getGoodX(x4);
		y5=getGoodY(y4);
		button5.setY(y5);
		button5.setX(x5);
		x6=getGoodX(x5);
		y6=getGoodY(y5);
		button6.setY(y6);
		button6.setX(x6);
		x7=getGoodX(x6);
		y7=getGoodY(y6);
		button7.setY(y7);
		button7.setX(x7);
		x8=getGoodX(x7);
		y8=getGoodY(y7);
		button8.setY(y8);
		button8.setX(x8);
		x9=getGoodX(x8);
		y9=getGoodY(y8);
		button9.setY(y9);
		button9.setX(x9);
	}*/

	protected void cancleListen() {
		button1.setOnClickListener(null);
		button2.setOnClickListener(null);
		button3.setOnClickListener(null);
		button4.setOnClickListener(null);
		button5.setOnClickListener(null);
		button6.setOnClickListener(null);
		button7.setOnClickListener(null);
		button8.setOnClickListener(null);
		button9.setOnClickListener(null);
	}   

	/*
	 * 保存功能
	 * Ninse  2016.4.19
	 * */
	private void save(double number,int diffcult) {
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
		String content =  String.valueOf(number);

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
			Toast.makeText(MainActivity.this, "保存成功", Toast.LENGTH_LONG).show();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/*
	 * 读取功能
	 * Ninse  2016.4.19
	 * */
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

/*	按返回键时返回start*/
	public boolean onKeyDown(int keyCode,KeyEvent event) {     
	if(keyCode == KeyEvent.KEYCODE_BACK){           //want to do
		Intent intent = new Intent(MainActivity.this,start.class);
		startActivity(intent);
		finish();
	}
	return super.onKeyDown(keyCode, event);
} 




}
