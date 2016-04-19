package com.example.number;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class start extends Activity{
	private Button easy;   
	private Button easyUp; 
	private Button middle; 
	private Button difficult;
	private static int difficulty=0;    //用户选择的游戏难度静态成员
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.start);
	    
		easy =(Button) findViewById(R.id.button11);
		easyUp = (Button) findViewById(R.id.button12);
		middle = (Button) findViewById(R.id.button13);
		
		easy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(1);
				intentGameActivity();        //跳转到游戏界面
			}
		});
		easyUp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(2);
				intentGameActivity();
			}
		});
		middle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setDifficulty(3);
				intentGameActivity();
			}
		});
		
	}
	protected void intentGameActivity() {
		Intent intent = new Intent(start.this,MainActivity.class);
		startActivity(intent);
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
}
