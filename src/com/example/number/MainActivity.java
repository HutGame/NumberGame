package com.example.number;


import android.support.v4.app.Fragment;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
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
import android.view.WindowManager;
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
			state=1;
			if(succese==0)
			{
			showTimeInSecond.setText(" 游戏失败");
			showDialog();
			}
		}
	}
    private String type[][]={{"","","",""},         //这行不用
    		                  {"1","一","one","Ⅰ","Ⅰ"},
    		                  {"2","二","two","Ⅱ","Ⅱ"},		
    		                  {"3","三","three","Ⅲ","Ⅲ"},
    		                  {"4","四","four","Ⅳ","Ⅳ"},
    		                  {"5","五","five","Ⅴ","Ⅴ"},
    		                  {"6","六","six","Ⅵ","Ⅵ"},
    		                  {"7","七","seven","Ⅶ","Ⅶ"},
    		                  {"8","八","eight","Ⅷ","Ⅷ"},
    		                  {"(9)","九","nine","Ⅸ","Ⅸ"}
    };
    public static boolean check=true;
    public static boolean checkMcTemp=false;  //检查临时计时器是否关闭
	private long useTime=0;
	public  int gameTime=10;  //可能需要修改的
	public static int g1=10,g2=10,g3=10,g4=10,g5=10,g6=10,g7=10,g8=10,g9=10;  //作为start判断条件
	private int countTime=0;
	private int first=1;
	private String showTime=null;
	private TextView textView=null;
	private TextView record=null;
	private TextView bestRecord=null;
	private double min=10.0;
	private int onlyUseFirst=0;  //只调用一次开始游戏
	private int state=0;     //解决bug 
	private int succese=0;    //成功标志
	//当计时器没停的时候state=0你点完了所有按钮为成功succece=1不输出游戏失败界面
	//当计时器停了state=1但你点完了所有按钮不输出成功界面
	private Button button1,button2,button3,button4,button5,button6,
	button7,button8,button9;
	Chronometer chronometer=null;
	Button startButton=null;
	int width=0,height=0;
	TextView showTimeInSecond =null;
	private MediaPlayer music;
	static myCountDownTimer mc; 
	static myCountDownTimer mcTemp; 
	TextView next;
	public int tempMcCheck=0;   //解决计时器停止的问题 没按开始游戏不能调用
	public int onlyTempMc=0; //每次开始游戏只准调用一次  
	public static int isReturn=0;
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
        
		initButton();
		setRandomPosButton(); 
		showTimeInSecond = (TextView)findViewById(R.id.textView2);
		startButton = (Button) findViewById(R.id.Button10);
		record = (TextView) findViewById(R.id.textView3);
		bestRecord = (TextView) findViewById(R.id.textView4);
		textView = (TextView) findViewById(R.id.textView1);
		chronometer = (Chronometer) findViewById(R.id.chronometer);
		next = (TextView) findViewById(R.id.next);
       
 
		min=read(getDifficulty());   //程序打开时读取文本里面最佳成绩，并赋值给min;
		gameTime=(int) readGameTime(getDifficulty());
		bestRecord.setText("最佳记录:"+min+"秒");
		textView.setText("限时"+gameTime+"秒");
		//提示用户
		giveInfoTouser();
		WindowManager wm = this.getWindowManager();
		 
	     width = wm.getDefaultDisplay().getWidth();
	     height = wm.getDefaultDisplay().getHeight();
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) { 
				succese=0;
				tempMcCheck=1;
				onlyTempMc=0;  //只调用一次
				initButton();
				listenButton();
				startButton.setVisibility(View.INVISIBLE);
				giveInfoTouser();
				check=true;
				state=0;
				checkMcTemp=false;    //点击开始启动的是计时器 所以临时的为false
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
				else
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
    protected void giveInfoTouser() {
		// TODO Auto-generated method stub
    	if(getDifficulty()==6)
		{
		if(gameTime-5<=0)
		next.setText("已成功解锁下一关");
		else
		next.setText("距离解锁下一关还差:"+(gameTime-5)+"个关卡");
		}
		else if(getDifficulty()>6)
		{
		if(gameTime-6<=0)
		next.setText("已成功解锁下一关");
		else
		next.setText("距离解锁下一关还差:"+(gameTime-5)+"个关卡");
		}
		else
		{
		if(gameTime-3<=0)
		next.setText("已成功解锁下一关");
		else
		next.setText("距离解锁下一关还差:"+(gameTime-3)+"个关卡");
		}
	}
	/*
     * 略微改变初始位置
     */
	private void setRandomPosButton() {
		button1.setY((float) (Math.random()*100+100));
		button1.setX((float) (Math.random()*70+40));
		button2.setY((float) (Math.random()*100+100));
		button2.setX((float) (Math.random()*70+40));
		button3.setY((float) (Math.random()*100+100));
		button3.setX((float) (Math.random()*70+40));
		button4.setY((float) (Math.random()*100+100));
		button4.setX((float) (Math.random()*70+40));
		button5.setY((float) (Math.random()*100+100));
		button5.setX((float) (Math.random()*70+40));
		button6.setY((float) (Math.random()*100+100));
		button6.setX((float) (Math.random()*70+40));
		button7.setY((float) (Math.random()*100+100));
		button7.setX((float) (Math.random()*70+40));
		button8.setY((float) (Math.random()*100+100));
		button8.setX((float) (Math.random()*70+40));
		button9.setY((float) (Math.random()*100+100));
		button9.setX((float) (Math.random()*70+40));
	}

	void initButton() {
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
				/*textTypeChangeButton();
				rotationButton();*/
				button1.setVisibility(View.INVISIBLE);
				PlayMusic(R.raw.mengmengda+(int)(Math.random()*6+1));
				effectChange();
				button2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						/*rotationButton();
						textTypeChangeButton();*/
						effectChange();
						
						button2.setVisibility(View.INVISIBLE);
						PlayMusic(R.raw.mengmengda+(int)(Math.random()*6+1));
						button3.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								/*rotationButton();
								textTypeChangeButton();*/
								effectChange();
								
								button3.setVisibility(View.INVISIBLE);
								PlayMusic(R.raw.mengmengda+(int)(Math.random()*6+1));
								button4.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
										button4.setVisibility(View.INVISIBLE);
										/*rotationButton();
										textTypeChangeButton();*/
										PlayMusic(R.raw.mengmengda+(int)(Math.random()*6+1));
										effectChange();
										button5.setOnClickListener(new OnClickListener() {
											@Override
											public void onClick(View v) {
												button5.setVisibility(View.INVISIBLE);
												/*rotationButton();
												textTypeChangeButton();*/
												effectChange();
												PlayMusic(R.raw.mengmengda+(int)(Math.random()*6+1));
												button6.setOnClickListener(new OnClickListener() {
													@Override
													public void onClick(View v) {
														button6.setVisibility(View.INVISIBLE);
														/*rotationButton();
														textTypeChangeButton();*/
														effectChange();
														PlayMusic(R.raw.mengmengda+(int)(Math.random()*6+1));
														button7.setOnClickListener(new OnClickListener() {
															@Override
															public void onClick(View v) {
																button7.setVisibility(View.INVISIBLE);
																/*rotationButton();
																textTypeChangeButton();*/
																effectChange();
																PlayMusic(R.raw.mengmengda+(int)(Math.random()*6+1));
																button8.setOnClickListener(new OnClickListener() {
																	@Override
																	public void onClick(View v) {
																		button8.setVisibility(View.INVISIBLE);
																		/*rotationButton();
																		textTypeChangeButton();*/
																		effectChange();
																		PlayMusic(R.raw.mengmengda+(int)(Math.random()*6+1));
																		button9.setOnClickListener(new OnClickListener() {
																			@Override
																			public void onClick(View v) {
																				button9.setVisibility(View.INVISIBLE);
																				PlayMusic(R.raw.mengmengda+(int)(Math.random()*6+1));
																				
																				if(state!=1)
																				{
																					if(checkMcTemp==true)
																					mcTemp.cancel();  //关掉临时计时器
																				    mc.cancel();
																				    if(gameTime==0)
																				    {
																				    	startButton.setVisibility(View.INVISIBLE); 
																				    	Toast.makeText(MainActivity.this,"好厉害,您已通关该关卡的所有关卡",1).show();
																				    }
																					//if((gameTime*1000-useTime)/1000.0<gameTime+0.2)
																					//{
																						succese=1;
																						showSuccessDialog(); 
																						showTimeInSecond.setText(" 游戏成功");
																						record.setText("本次记录:"+(gameTime*1000-useTime)/1000.0+"秒");
																					//}
																					showMessage();
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
				tempMcCheck=0;
				giveInfoTouser();
				saveGameTime(gameTime,getDifficulty());      //存档
				randomPosButton();
				//chronometer.setText("00:00");
				textView.setText("限时"+gameTime+"秒");
				showTimeInSecond.setText("倒计时工具");
				readGameTime(getDifficulty());         //赋值给g1-g9
				cancleListen();
				showButton();
				if(getDifficulty()==1)
				{
					startButton.setVisibility(View.VISIBLE);
				}
				else if(getDifficulty()==2)
				{
					onlyTempMc=0;
					tempMcCheck=1;
					checkMcTemp=false;    //点击下一关启动的是计时器 所以临时的为false
					listenButton();
					succese=0;
					state=0; 
					mc = new myCountDownTimer(gameTime*1000,1);   //总共10s  1毫秒执行一次onTick
					mc.start();                                    //直接开始计时
				}
				else
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
				min=(gameTime*1000-useTime)/1000.00;
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
				tempMcCheck=0;
				showTimeInSecond.setText("倒计时工具");
				if(getDifficulty()==1)
				{
					startButton.setVisibility(View.VISIBLE);
				}
				else if(getDifficulty()==2)
				{
					checkMcTemp=false;    //点击重新开始启动的是计时器 所以临时的为false
					tempMcCheck=1;
					onlyTempMc=0;
					state=0;
					listenButton();
					mc = new myCountDownTimer(gameTime*1000,1);   //总共10s  1毫秒执行一次onTick
					mc.start();                                    //直接开始计时
				}
				else
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
     * 显示解锁关卡信息
     */
	void showMessage()
	{
		if(getDifficulty()==1 && gameTime<=4)
		{
		Toast.makeText(this, "您已成功解锁第二关", 1).show();
		}
		if(getDifficulty()==2 && gameTime<=4)
		{
		Toast.makeText(this, "您已成功解锁第三关", 1).show();
		}
		if(getDifficulty()==3 && gameTime<=4)
		{
		Toast.makeText(this, "您已成功解锁第四关", 1).show();
		}
		if(getDifficulty()==4 && gameTime<=4)
		{
		Toast.makeText(this, "您已成功解锁第五关", 1).show();
		}
		if(getDifficulty()==5 && gameTime<=4)
		{
		Toast.makeText(this, "您已成功解锁第六关", 1).show();
		}
		if(getDifficulty()==6 && gameTime<=6)
		{
		Toast.makeText(this, "您已成功解锁第七关", 1).show();
		}
		if(getDifficulty()==7 && gameTime<=7)
		{
		Toast.makeText(this, "您已成功解锁第八关", 1).show();
		}
		if(getDifficulty()==8 && gameTime<=7)
		{
		Toast.makeText(this, "您已成功解锁第九关", 1).show();
		}
		if(getDifficulty()==9 && gameTime<=7)
		{
		Toast.makeText(this, "您已成功通过所有关卡", 1).show();
		}
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
	//按钮高度96 宽度128  普通随机
	
/*	protected void randomPosButton() {
		
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
	*/
	/* ACMTen10 2016/4/20
	 * 随机算法
	 */
	float getGoodX(float x)
	{
		float x1=(float) (Math.random()*((int)(width/1.4))+width/18);
		while(Math.abs(x-x1)<96)
		{
			x1=(float) (Math.random()*((int)(width/1.4))+width/18);
		}
		return x1;
	}
	float getGoodY(float y)
	{
		float y1=(float) (Math.random()*((int)(height/2.5))+height/6);
		while(Math.abs(y-y1)<128)
		{
			y1=(float) (Math.random()*((int)(height/2.5))+height/6);
		}
		return y1;
	}                //待优
	boolean notOverLay(float x1,float y1,float x2,float y2) //判断坐标是否重叠
	{
		if(Math.abs(x1-x2)>96 || Math.abs(y1-y2)>128)
		return true;
		/*else
		{
			while(Math.absx1-x2<96 && y1-y2<128)
			{
		    x1 = (float) (Math.random()*540+40);
		    y1 = (float) (Math.random()*700+100);
			}
		}*/
		return false;
	}
    protected void randomPosButton() {
    	float x1,y1,x2,y2,x3,y3,x4,y4,x5,y5,x6,y6,x7,y7,x8,y8,x9,y9;
		/*x1=(float) (Math.random()*540+40);
		y1=(float) (Math.random()*520+180);*/
    	x1=(float) (Math.random()*((int)(width/1.4))+width/18);
    	y1=(float) (Math.random()*((int)(height/2.5))+height/6);
		button1.setY(y1);
		button1.setX(x1);
		x2=getGoodX(x1);
		y2=getGoodY(y1);
		button2.setY(y2);
		button2.setX(x2);
		x3=getGoodX(x2);
		y3=getGoodY(y2);
		while(notOverLay(x3,y3,x1,y1)!=true)
		{
		x3=getGoodX(x2);
		y3=getGoodY(y2);
		}
		button3.setY(y3);
		button3.setX(x3);
		x4=getGoodX(x3);
		y4=getGoodY(y3);
		while(notOverLay(x4,y4,x1,y1)!=true || notOverLay(x4,y4,x2,y2)!=true)
		{
		x4=getGoodX(x3);
		y4=getGoodY(y3);
		}
		button4.setY(y4);
		button4.setX(x4);
		x5=getGoodX(x4);
		y5=getGoodY(y4);
		while(notOverLay(x5,y5,x1,y1)!=true || notOverLay(x5,y5,x2,y2)!=true || notOverLay(x5,y5,x3,y3)!=true)
		{
		x5=getGoodX(x4);
		y5=getGoodY(y4);
		}
		button5.setY(y5);
		button5.setX(x5);
		x6=getGoodX(x5);
		y6=getGoodY(y5);
		while(notOverLay(x6,y6,x1,y1)!=true || notOverLay(x6,y6,x2,y2)!=true || notOverLay(x6,y6,x3,y3)!=true || notOverLay(x6,y6,x4,y4)!=true)
		{
		x6=getGoodX(x5);
		y6=getGoodY(y5);
		}
		button6.setY(y6);
		button6.setX(x6);
		x7=getGoodX(x6);
		y7=getGoodY(y6);
		while(notOverLay(x7,y7,x1,y1)!=true || notOverLay(x7,y7,x2,y2)!=true || notOverLay(x7,y7,x3,y3)!=true || notOverLay(x7,y7,x4,y4)!=true || notOverLay(x7,y7,x5,y5)!=true)
		{
		x7=getGoodX(x6);
		y7=getGoodY(y6);
		}
		button7.setY(y7);
		button7.setX(x7);
		x8=getGoodX(x7);
		y8=getGoodY(y7);
		while(notOverLay(x8,y8,x1,y1)!=true || notOverLay(x8,y8,x2,y2)!=true || notOverLay(x8,y8,x3,y3)!=true || notOverLay(x8,y8,x4,y4)!=true || notOverLay(x8,y8,x5,y5)!=true || notOverLay(x8,y8,x6,y6)!=true)
		{
		x8=getGoodX(x7);
		y8=getGoodY(y7);
		}
		button8.setY(y8);
		button8.setX(x8);
		x9=getGoodX(x8);
		y9=getGoodY(y8);
		while(notOverLay(x9,y9,x1,y1)!=true || notOverLay(x9,y9,x2,y2)!=true || notOverLay(x9,y9,x3,y3)!=true || notOverLay(x9,y9,x4,y4)!=true || notOverLay(x9,y9,x5,y5)!=true || notOverLay(x9,y9,x6,y6)!=true || notOverLay(x9,y9,x7,y7)!=true)
		{
		x9=getGoodX(x8);
		y9=getGoodY(y8);
		}
		button9.setY(y9);
		button9.setX(x9);
	}
    /*
     * 按钮旋转
     */
    void rotationButton()
    {
    	button1.setRotation((float) (Math.random()*180));
		button2.setRotation((float) (Math.random()*180));
		button3.setRotation((float) (Math.random()*180));
		button4.setRotation((float) (Math.random()*180));
		button5.setRotation((float) (Math.random()*180));
		button6.setRotation((float) (Math.random()*180));
		button7.setRotation((float) (Math.random()*180));
		button8.setRotation((float) (Math.random()*180));
		button9.setRotation((float) (Math.random()*180));
    }
    void textTypeChangeButton()    //按钮的文本变化
    {
    	button1.setText(type[1][(int) (Math.random()*4)]);
		button2.setText(type[2][(int) (Math.random()*4)]);
		button3.setText(type[3][(int) (Math.random()*4)]);
		button4.setText(type[4][(int) (Math.random()*4)]);
		button5.setText(type[5][(int) (Math.random()*4)]);
		button6.setText(type[6][(int) (Math.random()*4)]);
		button7.setText(type[7][(int) (Math.random()*4)]);
		button8.setText(type[8][(int) (Math.random()*4)]);
		button9.setText(type[9][(int) (Math.random()*4)]);
    }
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
	 * 难度对效果的影响
	 */
    void effectChange()
    {
		if(getDifficulty()==3)
			rotationButton();
		if(getDifficulty()==4)
			textTypeChangeButton();
		if(getDifficulty()==5)
		{
			textTypeChangeButton();
			rotationButton();
		}
		if(getDifficulty()==6)
			randomPosButton();
		if(getDifficulty()==7)
		{
			rotationButton();
		    randomPosButton();
		}
		if(getDifficulty()==8)
		{
			textTypeChangeButton();
		    randomPosButton();
		}
		if(getDifficulty()==9)
		{
			textTypeChangeButton();
			rotationButton();
			randomPosButton();
		}
    }
    //播放音乐
	@SuppressWarnings("unused")
	private void PlayMusic(int MusicId) {

		music = MediaPlayer.create(this, MusicId);
		music.start();

		}
	/*
	 * 保存功能
	 * Ninse  2016.4.23
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
	/*
	 * 存储游戏时间 存档
	 */
	@SuppressWarnings("unused")
	private void saveGameTime(int gameTime,int diffcult) {
		switch(diffcult){
		case 1: 
			fileName = "gameTime1.txt";
			break;
		case 2: 
			fileName = "gameTime2.txt";
			break;
		case 3: 
			fileName = "gameTime3.txt";
			break;
		case 4: 
			fileName = "gameTime4.txt";
			break;
		case 5: 
			fileName = "gameTime5.txt";
			break;
		case 6: 
			fileName = "gameTime6.txt";
			break;
		case 7: 
			fileName = "gameTime7.txt";
			break;
		case 8: 
			fileName = "gameTime8.txt";
			break;
		case 9: 
			fileName = "gameTime9.txt";
			break;
		}
		String content =  String.valueOf(gameTime);

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
	@SuppressWarnings("unused")
	private double readGameTime(int diffcult) {     
		switch(diffcult){
		case 1: 
			g1=gameTime;
			fileName = "gameTime1.txt";
			break;
		case 2: 
			g2=gameTime;
			fileName = "gameTime2.txt";
			break;
		case 3: 
			g3=gameTime;
			fileName = "gameTime3.txt";
			break;
		case 4: 
			g4=gameTime;
			fileName = "gameTime4.txt";
			break;
		case 5: 
			g5=gameTime;
			fileName = "gameTime5.txt";
			break;
		case 6: 
			g6=gameTime;
			fileName = "gameTime6.txt";
			break;
		case 7: 
			g7=gameTime;
			fileName = "gameTime7.txt";
			break;
		case 8: 
			g8=gameTime;
			fileName = "gameTime8.txt";
			break;
		case 9: 
			g9=gameTime;
			fileName = "gameTime9.txt";
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
	
	/*按返回键时返回start*/
	public boolean onKeyDown(int keyCode,KeyEvent event) {   
		
	if(keyCode == KeyEvent.KEYCODE_BACK){           //want to do
		isReturn=1;
		Intent intent = new Intent(MainActivity.this,start.class);
		startActivity(intent);
		finish();
	}
	else
	{
		isReturn=0;
		if(tempMcCheck==1)
		{
		mc.cancel();
		if(onlyTempMc==0)
		{
		onlyTempMc=1;
		mcTemp=new myCountDownTimer(useTime, 1);   //临时计时器和计时器共享一个结束函数
		mcTemp.start();
		checkMcTemp=true;
		}
		}
	}
	return super.onKeyDown(keyCode, event);
} 
	
	/* public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        switch (keyCode) {  
	   
	        case KeyEvent.KEYCODE_VOLUME_DOWN:  
	        	//setVolumeControlStream(AudioManager.STREAM_MUSIC);
	        	mc.onTick(useTime);
	            return true;  

	        case KeyEvent.KEYCODE_VOLUME_UP:  
	        	//setVolumeControlStream(AudioManager.STREAM_MUSIC);
	        	mc.onTick(useTime);
	            return true;
	        case KeyEvent.KEYCODE_BACK:  
	        	Intent intent = new Intent(MainActivity.this,start.class);
	    		startActivity(intent);
	    		finish();
	            return true;  
	        }  
	        return super.onKeyDown(keyCode, event);  
	    }  */


}