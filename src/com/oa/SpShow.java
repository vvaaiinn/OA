package com.oa;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oa.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SpShow extends Activity {
	private SpsParse parser;  
    public List<Sps> sps; 
    String result = null;
    String rr = null;
    private static final String TAG = "SHOW";
    private float screenWidth; //��Ļ��
    private float screenHeight; //��Ļ��
    
  	  	 
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); 
		
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //ʹ�ò����ļ������������	
        setContentView(R.layout.spshow);    
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
//        TextView ts = (TextView)findViewById(R.id.titleset);
       
        
//        Intent intent = getIntent();
//        rr = intent.getStringExtra("result");
        
        
//        if(rr.equals("1"))
//        	ts.setText("�ҵ�����");
//        else 
//        	ts.setText("�ҵ�����");
		
       // setContentView(R.layout.spshow);  
        ///�õ���Ļ�ĳ���
//        DisplayMetrics dm = new DisplayMetrics();  
//        dm = this.getResources().getDisplayMetrics();
//        screenWidth = dm.widthPixels;   
//        screenHeight = dm.heightPixels;
//        System.out.println(screenWidth+"\t\t\t\t"+screenHeight);
//        TextView tv1 = (TextView)findViewById(R.id.sptitle);
//        //LayoutParams p=tv.getLayoutParams();
//        tv1.setWidth((int) (screenWidth/4));
//        TextView tv2 = (TextView)findViewById(R.id.spname);
//        tv2.setWidth((int) (screenWidth/4));
//        TextView tv3 = (TextView)findViewById(R.id.spstate);
//        tv3.setWidth((int) (screenWidth/4));
       
        InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("getsp.xml");
        try {
	        URL url = new URL("http://10.34.11.224:88/WebService.asmx?op=GetSP");     
	    	GetSp g = new GetSp();
			result = g.getResult(inStream, url).toString();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        
        ListView mylist=(ListView)findViewById(R.id.listview);
        //simpleAdapter ��list
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        List<Map<String, String>> list2 = new ArrayList<Map<String, String>>();
        String []from ={"sptitle","spname","spstate"};
        int []to = {R.id.sptitle,R.id.spname,R.id.spstate};
        
        ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(result.getBytes());	
        try  
        { 
	        parser = new PullSpsParser();
	    	sps = parser.parse(tInputStringStream);  	    	
	         for (Sps sp : sps) {  
	             Log.i(TAG, sp.getTitle()); 
	             ////��
	             Map<String, String> m = new HashMap<String, String>();
	             m.put("sptitle",sp.getTitle());
	             m.put("spname", sp.getUpname());
	             m.put("spstate",sp.getState());
	             list.add(m);
	             if(sp.getState().toString().equals("���ڰ���"))
	             	list2.add(m);
	            // System.out.println(sp.cout());
	         } 
	         if(rr.equals("1"))
	         {
	        	 System.out.println(list.size());
	        	 SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.spitem, from, to);
	        	 mylist.setAdapter(adapter);   
	         } 
	         else 
	         {
	        	 SimpleAdapter adapter = new SimpleAdapter(this, list2, R.layout.spitem, from, to);
		         mylist.setAdapter(adapter);  
	         }
        }
        catch (Exception e)  
        {                       
            Toast.makeText(SpShow.this, "����ʧ��", 1).show();  
        }     
        
          
        ////Listview ��ý���
        mylist.setFocusable(true);
        mylist.setFocusableInTouchMode(true);
 
        ///����ÿһ�е���Ӧ
        mylist.setOnItemClickListener(new OnItemClickListener()
        {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO �Զ����ɵķ������
				
				 Sps sp = sps.get(arg2);
				 Intent intent = new Intent(SpShow.this,SpDetail.class);
				 Bundle bundle = new Bundle();
				 bundle.putSerializable("sp", sp);
				 intent.putExtras(bundle);
				 startActivity(intent);			
			}                	
        });
	}

}
