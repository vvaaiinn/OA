package com.oa;

import android.app.Activity;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import com.oa.R;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class NewsShow extends Activity{
	
	
	private NewsParse parser;  
    public List<News> news; 
    private static final String TAG = "XML";  
    
//    private BaseAdapter adapter=new BaseAdapter() {
//  	  
//  	  @Override
//  	  public View getView(int arg0, View arg1, ViewGroup arg2) {
//	    	   TextView text=new TextView(NewsShow.this);
//	    	   text.setText(books.get(arg0).getTitle());  	    	  
//	    	   text.setTextSize(18); 	    	  
//	    	   text.setPadding(40,30,40,30);
//	    	   return text;
//  	  }
//  	  
//  	  @Override
//  	  public long getItemId(int arg0) {
//  	   // TODO Auto-generated method stub
//  	   return arg0;
//  	  }
//  	  
//  	  @Override
//  	  public Object getItem(int arg0) {
//  	   // TODO Auto-generated method stub
//  	   return books.get(arg0);
//  	  }
//  	  
//  	  @Override
//  	  public int getCount() {
//  	   // TODO Auto-generated method stub
//  	   return books.size();
//  	  }
//  	 };
  	 
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); 
//		requestWindowFeature(Window.FEATURE_NO_TITLE);//取消标题栏
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//		        WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏
       // setContentView(R.layout.newsshow);  
        
       // requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //使用布局文件来定义标题栏	
       setContentView(R.layout.newsshow);    
       //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
      // TextView ts = (TextView)findViewById(R.id.titleset);
       //ts.setText("单位公告");
        
        
        Intent intent = getIntent();
        String result = intent.getStringExtra("result");
        
        ListView mylist=(ListView)findViewById(R.id.listview);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String []from ={"newstitle","newsdec","newssource","newstime"};
        int []to = {R.id.newstitle,R.id.newsdec,R.id.newssource,R.id.newstime};
        
        ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(result.getBytes());	
        try  
        { 
	        parser = new PullNewsParser();
	    	news = parser.parse(tInputStringStream);  	    	
	         for (News ne : news) {  
	             Log.i(TAG, ne.getShijian());  
	             Map<String, String> m = new HashMap<String, String>();
	             m.put("newstitle",ne.getTitle());
	             m.put("newsdec", ne.getNeirong());
	             m.put("newssource",ne.getUserName());
	             m.put("newstime",ne.getShijian());
	             list.add(m);
	         }                            	
         	//System.out.println(result);
	         SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.newsitem, from, to);
	         mylist.setAdapter(adapter);

        }
        catch (Exception e)  
        {                       
            Toast.makeText(NewsShow.this, "查询失败", 1).show();  
        }     
        
       
      
        
        //mylist.setAdapter(adapter);
        mylist.setFocusable(true);
        mylist.setFocusableInTouchMode(true);
        mylist.setOnItemClickListener(new OnItemClickListener()
        {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO 自动生成的方法存根
				 News ne = news.get(arg2);

				 Intent intent = new Intent(NewsShow.this,NewsDetail.class);
//				 Bundle bundle = new Bundle();
//				 bundle.putInt("id", arg2);
//				 intent.putExtras(bundle);
				 Bundle bundle = new Bundle();
				 bundle.putSerializable("book", ne);
				 intent.putExtras(bundle);
				 startActivity(intent);
				 
	    		 //System.out.println(book.cout());
				 
				
			}
        	
                	
        });
	}

 

}
