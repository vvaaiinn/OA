package com.oa;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EmShow extends Activity{
	
	private EmParse parser;  
    public List<Em> ems; 
    String result = null;
    private static final String TAG = "EMSHOW";
    private SharedPreferences ss;
	private SharedPreferences webURL ;
    private String urladdress;
  	 
	public void onCreate(Bundle savedInstanceState) 
	{
		webURL = getSharedPreferences("url", 0);
		urladdress = webURL.getString("address", "");
			
		super.onCreate(savedInstanceState); 
		
       // requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //ʹ�ò����ļ������������	
       setContentView(R.layout.emshow);    
      // getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
      // TextView ts = (TextView)findViewById(R.id.titleset);
       //ts.setText("�ҵ��ʼ�");
       ss = getSharedPreferences("userInfo", 0);
       
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("getem.xml");
        try {
	        URL url = new URL(urladdress+"GetEM");     
	    	GetEm g = new GetEm();
			result = g.getResult(inStream,ss.getString("USER_NAME", ""), url).toString();
			System.out.println(result);
			//System.out.println("hahahah");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        ListView mylist=(ListView)findViewById(R.id.listview);
        //simpleAdapter ��list
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String []from ={"emtitle","emname","emtime","emstate"};
        int []to = {R.id.emtitle,R.id.emname,R.id.emtime,R.id.emstate};
        
        ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(result.getBytes());	        
        try  
        { 
	        parser = new PullEmParser();
	    	ems = parser.parse(tInputStringStream);  	    	
	         for (Em em : ems) {  
	             //Log.i(TAG, em.getTitle()); 
	             ////��
	             Map<String, String> m = new HashMap<String, String>();
	             m.put("emtitle",em.getEmailTitle());
	             m.put("emname", em.getFromUser());
	             m.put("emtime",em.getTimeStr());
	             m.put("emstate", em.getEmailState());
	             list.add(m);
	            // System.out.println(em.cout());
	         } 
	         SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.emitem, from, to);
	         mylist.setAdapter(adapter);         	
        }
        catch (Exception e)  
        {                       
            Toast.makeText(EmShow.this, "����ʧ��", 1).show();  
        }     
        
          
        ////Listview ��ý���
        mylist.setFocusable(true);
        mylist.setFocusableInTouchMode(true);
 
       //////����ÿһ�е���Ӧ
        mylist.setOnItemClickListener(new OnItemClickListener()
        {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO �Զ����ɵķ������
				
				 Em em = ems.get(arg2);
				 Intent intent = new Intent(EmShow.this,EmDetail.class);
				 Bundle bundle = new Bundle();
				 bundle.putSerializable("em", em);
				 intent.putExtras(bundle);
				 startActivity(intent);		
				 finish();
			}                	
        });
	}

}
