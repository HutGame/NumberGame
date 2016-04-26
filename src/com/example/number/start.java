package com.example.number;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class start extends Activity{
	private Button level1,level2,level3,level4,level5,level6,level7,level8,level9;
	private int flag1=1,flag2=0,flag3=0,flag4=0,flag5=0,flag6=0,flag7=0,flag8=0,flag9=0; 
	//��ǹؿ��Ƿ���
	private Button difficult;
	int []round; 
	private TextView show1;
	private TextView show2;
	private TextView show3;
	private TextView show4;
	private TextView show5;
	private TextView show6;
	private TextView show7;
	private TextView show8;
	private TextView show9;
	private Button pk;
	private Button bug;
	private Button about;
	public static MainActivity m1;
	private String fileName = "bestRecord.txt";
	private static int difficulty=0;    //�û�ѡ�����Ϸ�ѶȾ�̬��Ա
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start);
	    m1=new MainActivity();
	    m1.check=false;
	    round = new int[10];
	    level1 =(Button) findViewById(R.id.button11);
		level2 =(Button) findViewById(R.id.button12);
		level3 =(Button) findViewById(R.id.button13);
		level4 =(Button) findViewById(R.id.button14);
		level5 =(Button) findViewById(R.id.button15);
		level6 =(Button) findViewById(R.id.button16);
		level7 =(Button) findViewById(R.id.button17);
		level8 =(Button) findViewById(R.id.button18);
		level9 =(Button) findViewById(R.id.button19);
		show1 = (TextView) findViewById(R.id.textView2);
		show2 = (TextView) findViewById(R.id.TextView01);
		show3 = (TextView) findViewById(R.id.TextView02);
		pk=(Button)findViewById(R.id.button1);
		bug=(Button)findViewById(R.id.bug);
		about=(Button)findViewById(R.id.about);
/*		show4 = (TextView) findViewById(R.id.Text03);
		show5 = (TextView) findViewById(R.id.TextView05);
		show6 = (TextView) findViewById(R.id.TextView04);
		show7 = (TextView) findViewById(R.id.TextView06);
		show8 = (TextView) findViewById(R.id.TextView08);
		show9 = (TextView) findViewById(R.id.TextView07);*/
	//	showbutton();  //����������
	    showtxt();  //����������
	    readAllFlagOfRound();
		/*������ͬ�Ѷȵİ�ť����*/
		level1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(1);
				intentGameActivity();        //��ת����Ϸ����
				finish();
			}
		});
		
		pk.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(start.this,pk.class);
				startActivity(intent);
			}
			
		});	
		
		about.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(start.this,about.class);
				startActivity(intent);
			}
			
		});	
		bug.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(start.this,bug.class);
				startActivity(intent);
			}
			
		});	
		
		/*if(getDifficulty()==1 && m1.g1<=3)  //ֻ�е��ؿ��Ѷ���1 ���ҵ�1�ص�ʱ����3�����µ�ʱ��ſ����ڶ���
	    {
			flag2=1;
			 saveFlagOfRound(flag2,2);
		//Toast.makeText(start.this, "���ѳɹ������ڶ���", 1).show();
	    }*/
		if(flag2==1)
		{
			level2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setDifficulty(2);
					intentGameActivity();
					finish();
				}
			});
		}
		else
		{
			level2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(start.this, "�ùؿ�δ����", 1).show();
				}
			});
		}
		if(flag3==1)
		{
			level3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setDifficulty(3);
					intentGameActivity();
					finish();
				}
			});
		}
		else
		{
			level3.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(start.this, "�ùؿ�δ����", 1).show();
				}
			});
		}
		if(flag4==1)
		{
			level4.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setDifficulty(4);
					intentGameActivity();
					finish();
				}
			});
		}
		else
		{
			level4.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(start.this, "�ùؿ�δ����", 1).show();
				}
			});
		}
		if(flag5==1)
		{
			level5.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setDifficulty(5);
					intentGameActivity();
					finish();
				}
			});
		}
		else
		{
			level5.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(start.this, "�ùؿ�δ����", 1).show();
				}
			});
		}
		if(flag6==1)
		{
			level6.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setDifficulty(6);
					intentGameActivity();
					finish();
				}
			});
		}
		else
		{
			level6.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(start.this, "�ùؿ�δ����", 1).show();
				}
			});
		}
		if(flag7==1)
		{
			level7.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setDifficulty(7);
					intentGameActivity();
					finish();
				}
			});
		}
		else
		{
			level7.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(start.this, "�ùؿ�δ����", 1).show();
				}
			});
		}
		if(flag8==1)
		{
			level8.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setDifficulty(8);
					intentGameActivity();
					finish();
				}
			});
		}
		else
		{
			level8.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(start.this, "�ùؿ�δ����", 1).show();
				}
			});
		}
		if(flag9==1)
		{
			level9.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					setDifficulty(9);
					intentGameActivity();
					finish();
				}
			});
		}
		else
		{
			level9.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(start.this, "�ùؿ�δ����", 1).show();
				}
			});
		}
	}
	
	



	/*
	 * ��ȡ���йؿ���״̬  �ǿ����˻���û����
	 */
	private void readAllFlagOfRound() {
		// TODO Auto-generated method stub
		flag2=(int) readFlagOfRound(2);
		flag3=(int) readFlagOfRound(3);
		flag4=(int) readFlagOfRound(4);
		flag5=(int) readFlagOfRound(5);
		flag6=(int) readFlagOfRound(6);
		flag7=(int) readFlagOfRound(7);
		flag8=(int) readFlagOfRound(8);
		flag9=(int) readFlagOfRound(9);
	}



	protected void intentGameActivity() {
		Intent intent = new Intent(start.this,MainActivity.class);
		startActivity(intent);
	}
/*	�õ����Ѽ���*/
	public int getDifficulty() {
		return difficulty;
	}
/*	�������Ѽ���*/
	public  void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	
	/*��ȡ�ı�������ʱ��*/
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
	
	public String readd(int diffcult){
		double temp;
		temp=read(diffcult);
         if(temp==10.0){
      return "none";
                 }
         
         NumberFormat ddf1=NumberFormat.getNumberInstance();    
        		    ddf1.setMaximumFractionDigits(3);    
        		    String s= ddf1.format(temp);

		return s+"";
	}
	
	public String readread(int diffcult){
		double temp;
		temp=read(diffcult);
         if(temp==10.0){
        	 switch(diffcult){
     		case 1: 
    			return "��һ��";	
    		case 2: 
       			return "�ڶ���";
    		case 3: 
       			return "������";
    		case 4: 
       			return "���Ĺ�";
    		case 5: 
       			return "�����";
    		case 6: 
       			return "������";
    		case 7: 
       			return "���߹�";
    		case 8: 
       			return "�ڰ˹�";
    		case 9: 
       			return "�ھŹ�";
        	 }  }
         
         NumberFormat ddf1=NumberFormat.getNumberInstance();    
        		    ddf1.setMaximumFractionDigits(3);    
        		    String s= ddf1.format(temp);
		return temp+"��";
	}
	
/*	����Main�����¼�*/ //�ȷ��� �ٴ���onCreate()
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
	if(m1.check==true)
	{
		    MainActivity m = new MainActivity();
		    m.mc.cancel();
	}
	if(getDifficulty()==1 && m1.g1<=3)  //ֻ�е��ؿ��Ѷ���1 ���ҵ�1�ص�ʱ����3�����µ�ʱ��ſ����ڶ���
    {
		 flag2=1;
		 saveFlagOfRound(flag2,2);
    }
	if(getDifficulty()==2 && m1.g2<=3)
	{
		flag3=1;
	    saveFlagOfRound(flag3,3);
	}
	if(getDifficulty()==3 && m1.g3<=3)
	{
		flag4=1;
		saveFlagOfRound(flag4,4);
	}
	if(getDifficulty()==4 && m1.g4<=3)
	{
		flag5=1;
		saveFlagOfRound(flag5,5);
	}
	if(getDifficulty()==5 && m1.g5<=3)
	{
		flag6=1;
		saveFlagOfRound(flag6,6);
	}
	if(getDifficulty()==6 && m1.g6<=5)
	{
		flag7=1;
		saveFlagOfRound(flag7,7);
	}
	if(getDifficulty()==7 && m1.g7<=6)
	{
		flag8=1;
		saveFlagOfRound(flag8,8);
	}
	if(getDifficulty()==8 && m1.g8<=6)
	{
		flag9=1;
		saveFlagOfRound(flag9,9);
	}
    return super.onKeyDown(keyCode, event);  
} 
	
	
/*   �����水���ؼ��ص�����*/
	public void onBackPressed() {  
	    super.onBackPressed();  
		Intent home = new Intent(Intent.ACTION_MAIN);  
		home.addCategory(Intent.CATEGORY_HOME);   
		startActivity(home); 
	}  
 

/*      ��ʾ������*/
	public void showtxt(){
		show1.setText("��һ��:"+readd(1)+"                "+"���Ĺ�:"+readd(4)+"                "+"���߹�:"+readd(7));
		show2.setText("�ڶ���:"+readd(2)+"                "+"�����:"+readd(5)+"                "+"�ڰ˹�:"+readd(8));
		show3.setText("������:"+readd(3)+"                "+"������:"+readd(6)+"                "+"�ھŹ�:"+readd(9));
/*		show4.setText("���Ĺ�:"+readd(4));
		show5.setText("�����:"+readd(5));
		show6.setText("������:"+readd(6));
		show7.setText("���߹�:"+readd(7));
		show8.setText("�ڰ˹�:"+readd(8));
		show9.setText("�ھŹ�:"+readd(9));*/
	}
/*	public void showbutton(){
		level1.setText(readread(1));
		level2.setText(readread(2));
		level3.setText(readread(3));
		level4.setText(readread(4));
		level5.setText(readread(5));
		level6.setText(readread(6));
		level7.setText(readread(7));
		level8.setText(readread(8));
		level9.setText(readread(9));
	}
	*/
	@SuppressWarnings("unused")
	private void saveFlagOfRound(int flag,int diffcult) {
		switch(diffcult){
		case 1: 
			fileName = "flagOfRount1.txt";
			break;
		case 2: 
			fileName = "flagOfRount2.txt";
			break;
		case 3: 
			fileName = "flagOfRount3.txt";
			break;
		case 4: 
			fileName = "flagOfRount4.txt";
			break;
		case 5: 
			fileName = "flagOfRount5.txt";
			break;
		case 6: 
			fileName = "flagOfRount6.txt";
			break;
		case 7: 
			fileName = "flagOfRount7.txt";
			break;
		case 8: 
			fileName = "flagOfRount8.txt";
			break;
		case 9: 
			fileName = "flagOfRount9.txt";
			break;
		}
		String content =  String.valueOf(flag);

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
	@SuppressWarnings("unused")
	private double readFlagOfRound(int diffcult) {     
		switch(diffcult){
		case 1: 
			fileName = "flagOfRount1.txt";
			break;
		case 2: 
			fileName = "flagOfRount2.txt";
			break;
		case 3: 
			fileName = "flagOfRount3.txt";
			break;
		case 4: 
			fileName = "flagOfRount4.txt";
			break;
		case 5: 
			fileName = "flagOfRount5.txt";
			break;
		case 6: 
			fileName = "flagOfRount6.txt";
			break;
		case 7: 
			fileName = "flagOfRount7.txt";
			break;
		case 8: 
			fileName = "flagOfRount8.txt";
			break;
		case 9: 
			fileName = "flagOfRount9.txt";
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
	
}