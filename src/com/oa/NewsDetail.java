package com.oa;

import java.util.List;

import com.oa.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewsDetail extends Activity{
	Drawable top_drDrawable = null;
	private SharedPreferences theme_choice;
	Resources res = null;
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);  
        //setContentView(R.layout.newsdetail);  
        
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //使用布局文件来定义标题栏	
       setContentView(R.layout.newsdetail);    
       getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
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

       TextView ts = (TextView)findViewById(R.id.titleset);
       ts.setText("单位公告");
        
        TextView tv = (TextView)findViewById(R.id.showtitle);
        TextView tv3 = (TextView)findViewById(R.id.showname);
        TextView tv1 = (TextView)findViewById(R.id.showtime);
        TextView tv2 = (TextView)findViewById(R.id.showdetail);
        tv2.setMovementMethod(ScrollingMovementMethod.getInstance());
//        Bundle bundle = this.getIntent().getExtras();  
//        int result =bundle.getInt("id");
//        
//        
//       
//        final List<News> books = (List<News>)getApplication();
//        System.out.println(books.get(1).cout());
        
        News book = (News)getIntent().getSerializableExtra("book");
        System.out.println(book.cout());             
        //System.out.println("hahahaha");
        tv.setText(book.getTitle());
        tv1.setText(book.getShijian());
        tv2.setText(book.getNeirong());
        tv3.setText(book.getUserName());
                       
	}

}
