package com.example.number;


import android.support.v4.app.Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
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
	
	private long useTime=0;
    private int gameTime=10;
    private int countTime=0;
    private int first=1;
    private String showTime=null;
    private TextView textView=null;
    private TextView record=null;
    private TextView bestRecord=null;
    private double min=0.0;
    private int onlyUseFirst=0;  //只调用一次开始游戏
    private int state=0;     //解决bug
    private Button button1,button2,button3,button4,button5,button6,
    button7,button8,button9;
	Chronometer chronometer=null;
	Button startButton=null;
	TextView showTimeInSecond =null;
	myCountDownTimer mc; 
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
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) { 
				initButton();
				listenButton();
				startButton.setVisibility(View.INVISIBLE);
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
           protected void showBestRecord()
           {
        	   if(first==1)
               {
               min=(gameTime*1000-useTime)/1000.0;
               first=0;
               bestRecord.setText("最佳记录:"+min+"秒");
               }
               else
               {
               if((gameTime*1000-useTime)/1000.0<min)
               min=(gameTime*1000-useTime)/1000.0;
               bestRecord.setText("最佳记录:"+min+"秒");
               }
           }
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

			protected void randomPosButton() {
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
}
