package com.oa;

import java.io.InputStream;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SendGg extends Activity {
	private SharedPreferences ss;
	private Button send;
	private EditText ggtitle,ggcontent;
	private Spinner type;
	private static final String[] m={"单位","部门"};
	private ArrayAdapter<String> adapter;
	private String TitleStr,TypeStr,Username,ContentStr;
	private String result = null;
	InputStream inStream = null;
	private SharedPreferences webURL ;
    private String urladdress;
	public void onCreate(Bundle savedInstanceState) 
	{
		webURL = getSharedPreferences("url", 0);
		urladdress = webURL.getString("address", "");
		
		super.onCreate(savedInstanceState); 		
        setContentView(R.layout.sendgg);    
        send = (Button)findViewById(R.id.send);
        ggtitle = (EditText)findViewById(R.id.GGTitle);
        ggtitle.setPadding(15,0,0,0);
        ggcontent = (EditText)findViewById(R.id.GGContent);
        ggcontent.setPadding(15,0,0,0);
        type = (Spinner)findViewById(R.id.type);
        type.setPadding(15,0,0,0);
        ss = getSharedPreferences("userInfo", 0);
        
        inStream  =this.getClass().getClassLoader().getResourceAsStream("setgg.xml");
        
      //将可选内容与ArrayAdapter连接起来
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
         
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter); 
        type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO 自动生成的方法存根
				TypeStr = m[arg2];
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO 自动生成的方法存根
				
			}
        	
		});
        
        send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				TitleStr = ggtitle.getText().toString();
				ContentStr = ggcontent.getText().toString();
				Username = ss.getString("USER_NAME", "");
				try {
			        URL url = new URL(urladdress+"SendGG");
			        SetGg g = new SetGg();
			        result = g.getResult(inStream,TitleStr,ContentStr, Username, TypeStr,url).toString();  
			        System.out.println(result);
			        if(result.equals("成功"))
                	{              		           		
                		Toast.makeText(SendGg.this, "发送成功",
                                Toast.LENGTH_SHORT).show(); 
                		Intent intent = new Intent(SendGg.this,Menu.class);
                		intent.putExtra("flag", 12);
                		startActivity(intent);
                		finish();
                	}
                	else 
                		Toast.makeText(SendGg.this, "发送失败",
                                Toast.LENGTH_SHORT).show(); 
				} catch (Exception e1) {
					Toast.makeText(SendGg.this, "Error",
                       Toast.LENGTH_SHORT).show(); 
				}
				
			}
		});
        
	}
	
}
