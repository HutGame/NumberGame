package com.example.number;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class welcome extends Activity{
private Button Start;
private EditText editText;
private String fileName="name.txt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome);
		super.onCreate(savedInstanceState);	
		Start=(Button)findViewById(R.id.button1);		
		editText = (EditText) findViewById(R.id.editText1);
		
	try{        //如果已经存储了用户名 直接进入游戏界面
               File f=new File("/data/data/com.example.number/files/name.txt");
               if(f.exists()){
   				Intent intent = new Intent(welcome.this,start.class);
   				startActivity(intent);  
   				finish();
               }
               
       }catch (Exception e) {
              
            
       }
	Start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				save();

				Intent intent = new Intent(welcome.this,start.class);
				startActivity(intent);
				finish();
			}
			
		});

	}

	private void save() {
		  String content = editText.getText().toString();
		try {

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
