package com.example.number;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class bug extends Activity{
	private Button exit;
	private Button bug;
	private EditText Editbug;
	private String fileName;
	private String uploadFile;
	public  String ID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.bug);
		super.onCreate(savedInstanceState);
		bug=(Button)findViewById(R.id.button1);
		exit=(Button)findViewById(R.id.button2);
		Editbug=(EditText)findViewById(R.id.editText1);
		
		ID=Secure.getString(getBaseContext().getContentResolver(), Secure.ANDROID_ID);  //获得机器码
		exit.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(bug.this,start.class);
				startActivity(intent);
			}

		}

				);
		bug.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				save();
			
    			File file = new File(uploadFile);   //上传代码
				new FileUploadAsyncTask(bug.this).execute(file);
			}

		});
	}
	
    private void save() {
    	fileName="[bug]"+ID+".txt";
    	uploadFile="/data/data/com.example.number/files/"+fileName;
        String content = Editbug.getText().toString();
        
          try {
              FileOutputStream outputStream = openFileOutput(fileName,
                      Activity.MODE_PRIVATE);
              outputStream.write(content.getBytes());
              outputStream.flush();
              outputStream.close();
             // Toast.makeText(bug.this, "保存成功", Toast.LENGTH_LONG).show();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }

      }
	
	

}
