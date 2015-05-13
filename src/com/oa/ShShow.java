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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class ShShow extends Activity{
	private HqParse parser;  
    public List<Hq> hqs; 
    String result = null;
	private SharedPreferences ss;
	private SharedPreferences webURL ;
    private String urladdress;
	
	public void onCreate(Bundle savedInstanceState) 
	{
		webURL = getSharedPreferences("url", 0);
		urladdress = webURL.getString("address", "");
		
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.hqshow); 
		ss = getSharedPreferences("userInfo", 0);
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("getsh.xml");
		try {
	        URL url = new URL(urladdress+"GetSH");     
	    	GetSh g = new GetSh();
			result = g.getResult(inStream,ss.getString("USER_NAME", ""), url).toString();
			System.out.println(result);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        ListView mylist=(ListView)findViewById(R.id.listview);
        //simpleAdapter ��list
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String []from ={"hqtitle","hqname","hqtime","hqstate"};
        int []to = {R.id.hqtitle,R.id.hqname,R.id.hqtime,R.id.hqstate};
        
        ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(result.getBytes());	        
        try  
        { 
	        parser = new PullShParser();
	    	hqs = parser.parse(tInputStringStream);  	    	
	         for (Hq hq : hqs) {  
	             //Log.i(TAG, em.getTitle()); 
	             ////��
	             Map<String, String> m = new HashMap<String, String>();
	             m.put("hqtitle",hq.getHQTitle());
	             m.put("hqname", hq.getHQSuser());
	             m.put("hqtime",hq.getHQTime());
	             m.put("hqstate", hq.getHQState());
	             list.add(m);
	            // System.out.println(em.cout());
	         } 
	         SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.hqitem, from, to);
	         mylist.setAdapter(adapter);         	
        }
        catch (Exception e)  
        {                       
            //Toast.makeText(EmShow.this, "����ʧ��", 1).show();  
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
				
				 Hq hq = hqs.get(arg2);
				 Intent intent = new Intent(ShShow.this,HqDetail.class);
				 Bundle bundle = new Bundle();
				 bundle.putSerializable("hq", hq);
				 intent.putExtras(bundle);
				 intent.putExtra("type", "2");
				 startActivity(intent);		
				 finish();
			}                	
        });
	}

}
