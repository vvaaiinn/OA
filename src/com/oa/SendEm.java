package com.oa;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.xml.sax.Parser;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SendEm extends Activity{
	
	private SharedPreferences ss;
	private Button send;
	private EditText EDEmailTitle,EDEmailContent,EDToUser;
	InputStream inStream = null;
	String EmailTitle=null;
	String EmailContent = null;
	String FromUser =null;
	String TimeStr = null;
	String ToUser  = null;
	String result = null;
	private SharedPreferences webURL ;
    private String urladdress;
    private SharedPreferences info;
    private String peoInfo;
    InfoParse parser = null;
    ArrayList<String> peoName  = new ArrayList<String>();
    String drp[];
    private Spinner type;
    private ArrayAdapter<String> adapter;
	public void onCreate(Bundle savedInstanceState) 
	{
		info = getSharedPreferences("info", 0);
		peoInfo = info.getString("info","");
		System.out.println(peoInfo);
		ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(peoInfo.getBytes());	
		try {			
			parser = new PullInfo();
			peoName =  parser.parse(tInputStringStream);
			int size = peoName.size();
			drp = new String[size];
			for(int i = 0 ;i < size ; i ++)
			{
				drp[i] = (String)peoName.get(i);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		webURL = getSharedPreferences("url", 0);
		urladdress = webURL.getString("address", "");
		
		super.onCreate(savedInstanceState); 		
        setContentView(R.layout.sendem); 
        
        type = (Spinner)findViewById(R.id.type);
        type.setPadding(15,0,0,0);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,drp);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter); 
        type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO 自动生成的方法存根
				ToUser = drp[arg2];
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO 自动生成的方法存根
				
			}        	           	
        });
        
        ss = getSharedPreferences("userInfo", 0);
        send = (Button)findViewById(R.id.send);
        EDEmailTitle = (EditText)findViewById(R.id.EmailTitle);
        EDEmailContent = (EditText)findViewById(R.id.EmailContent);
       // EDToUser = (EditText)findViewById(R.id.ToUser);
        inStream  =this.getClass().getClassLoader().getResourceAsStream("setem.xml");
        EDEmailContent.setPadding(15,0,0,0);
        EDEmailTitle.setPadding(15,0,0,0);
        //EDToUser.setPadding(15,0,0,0);
        
        send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EmailTitle = EDEmailTitle.getText().toString();
				EmailContent = EDEmailContent.getText().toString();
				//ToUser = EDToUser.getText().toString();
				FromUser = ss.getString("USER_NAME", "");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				TimeStr = df.format(new Date()).toString();
				
				// TODO 自动生成的方法存根
				try {
			        URL url = new URL(urladdress+"SendEM");
			        SetEm g = new SetEm();
			        result = g.getResult(inStream,EmailTitle,EmailContent, FromUser, ToUser,url).toString();  
			        System.out.println(result);
			        if(result.equals("发送成功"))
                	{              		           		
                		Toast.makeText(SendEm.this, "发送成功",
                                Toast.LENGTH_SHORT).show(); 
                		Intent intent = new Intent(SendEm.this,Menu.class);            
                		intent.putExtra("flag", 22);
                		startActivity(intent);
                		finish();
                	}
                	else 
                		Toast.makeText(SendEm.this, "发送失败",
                                Toast.LENGTH_SHORT).show(); 
				} catch (Exception e1) {
					Toast.makeText(SendEm.this, "Error",
                       Toast.LENGTH_SHORT).show(); 
				}
				
			}
		});        
                     
	}
	public void onBackPressed() {        
    		Intent intent = new Intent(SendEm.this,Menu.class);            
    		intent.putExtra("flag", 22);
    		startActivity(intent);
    		finish();
        super.onBackPressed();  
    }  
}
