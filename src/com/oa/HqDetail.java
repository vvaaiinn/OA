package com.oa;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date; 
import java.util.HashMap;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;

public class HqDetail extends Activity  {
	
    private SharedPreferences ss;
    Drawable top_drDrawable = null;
	private SharedPreferences theme_choice;
	Resources res = null;
	Hq hq ;
	EditText ed ;
	Button btn;
	String result = null;
	InputStream inStream = null;
	String type;
	private String[] text = { "�ʼ�����", "������", "�ռ���", "����ʱ��", "�ʼ�����" ,"��ע"}; 
	private SharedPreferences webURL ;
    private String urladdress;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		webURL = getSharedPreferences("url", 0);
		urladdress = webURL.getString("address", "");
		
		super.onCreate(savedInstanceState); 		
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.hqdetail); 
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
        TextView ts = (TextView)findViewById(R.id.titleset);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if(type.equals("1"))
        	ts.setText("�ҵĻ�ǩ");
        else 
        	ts.setText("�ҵ�����");
        RelativeLayout tit = (RelativeLayout)findViewById(R.id.title);
        theme_choice  = getSharedPreferences("theme", 0);
        String style_rs = theme_choice.getString("style", "1");
        res = getResources();
        if(style_rs.equals("1"))
        {
            top_drDrawable  =res.getDrawable(R.drawable.top_blue);
            tit.setBackgroundDrawable(top_drDrawable);  	
            
        }
        else
        {
        	top_drDrawable  =res.getDrawable(R.drawable.top_red_1);
            tit.setBackgroundDrawable(top_drDrawable);
        }
        
        hq = (Hq)getIntent().getSerializableExtra("hq");
        String [] hqIn = {hq.getHQTitle(),hq.getHQSuser(),hq.getHQTuser(),hq.getHQTime(),hq.getHQContent(),hq.getHQBeizhu()};
        GridView gridview = (GridView) findViewById(R.id.gridView1);
        ArrayList<HashMap<String, Object>> imagelist = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 6; i++) {  
            HashMap<String, Object> map = new HashMap<String, Object>();             
            map.put("text", text[i]);  
            map.put("hq", hqIn[i]);
            imagelist.add(map);  
        }  
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, imagelist,  
                R.layout.spdetailitem, new String[] { "text","hq" }, new int[] {  
                        R.id.title, R.id.detail });  
          // ����GridView��������Ϊ�½���simpleAdapter  
        gridview.setAdapter(simpleAdapter);  
        
        ed = (EditText)findViewById(R.id.de);
        btn = (Button)findViewById(R.id.btn);
        
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				if(type.equals("1"))
				{
					try {			
						inStream  =this.getClass().getClassLoader().getResourceAsStream("sethqstate.xml");
				        URL url = new URL(urladdress+"HQStateChange");
				        SetHqState g = new SetHqState();
				        ss = getSharedPreferences("userInfo", 0);
				        String name =ss.getString("USER_NAME", "");
				        String ad = ed.getText().toString();
				        String id = hq.getID();
				        String feedback = ed.getText().toString();
				        result = g.getResult(inStream,id,name,feedback,url).toString();  
				        System.out.println(result);
				        if(result.equals("�ɹ�"))
				        {
				        	Toast.makeText(HqDetail.this, "��ǩ�ɹ�", Toast.LENGTH_SHORT).show();
				        	Intent intent = new Intent(HqDetail.this,Menu.class);            
				        	intent.putExtra("flag", 3);
				        	startActivity(intent);
				        	finish();
				        }
				        else if(result.equals("��ǩ����")){
				        	Toast.makeText(HqDetail.this, "�Ѿ���ǩ��ɣ������ٴλ�ǩ", Toast.LENGTH_SHORT).show();
				        	Intent intent = new Intent(HqDetail.this,Menu.class);            
				        	intent.putExtra("flag", 3);
				        	startActivity(intent);
				        	finish();
				        }
				        else 
				        {
				        	Toast.makeText(HqDetail.this, "�����˲�������", Toast.LENGTH_SHORT).show();
				        }
					}
					 catch (Exception e1) {
							Toast.makeText(HqDetail.this, "Error",
				               Toast.LENGTH_SHORT).show(); 
				  }
				}
				else 
				{
					try {	
						inStream  =this.getClass().getClassLoader().getResourceAsStream("setshstate.xml");
				        URL url = new URL(urladdress+"SHStateChange");
				        SetShState g = new SetShState();
				        ss = getSharedPreferences("userInfo", 0);
				        String name =ss.getString("USER_NAME", "");
				        String ad = ed.getText().toString();
				        String id = hq.getID();
				        String feedback = ed.getText().toString();
				        result = g.getResult(inStream,id,name,feedback,url).toString();  
				        System.out.println(result);
				        if(result.equals("�ɹ�"))
				        {
				        	Toast.makeText(HqDetail.this, "�����ɹ�", Toast.LENGTH_SHORT).show();
				        	
				        }
				        else if(result.equals("��˹���")){
				        	Toast.makeText(HqDetail.this, "�Ѿ������ɣ������ٴ����", Toast.LENGTH_SHORT).show();
//				        	Intent intent = new Intent(HqDetail.this,Menu.class);            
//				        	intent.putExtra("flag", 3);
//				        	startActivity(intent);
//				        	finish();
				        }
				        else 
				        {
				        	Toast.makeText(HqDetail.this, "û��Ȩ�����", Toast.LENGTH_SHORT).show();
				        }
				        Intent intent = new Intent(HqDetail.this,Menu.class);            
			        	intent.putExtra("flag", 0);
			        	startActivity(intent);
			        	finish();
					}
					 catch (Exception e1) {
							Toast.makeText(HqDetail.this, "Error",
				               Toast.LENGTH_SHORT).show(); 
				  }
				}
				
			}
		});
        
                
	}
	public void onBackPressed() {  
    	          		           		
    	Intent intent = new Intent(HqDetail.this,Menu.class);   
    	if(type.equals("1"))
    		intent.putExtra("flag", 3);
    	else 
    		intent.putExtra("flag", 0);
    	startActivity(intent);
    	super.onDestroy();
    	
        super.onBackPressed();  
    }  

}
