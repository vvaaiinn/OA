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

public class EmDetail extends Activity {
    TextView tv = null;
    TextView tv1 = null;
    TextView tv2 =null;
    TextView tv3 = null;
    TextView tv4 = null;
    TextView tv5 = null;
    String name = null;
    InputStream inStream = null;
    String result = null;
    Em em;
    private SharedPreferences ss;
    Drawable top_drDrawable = null;
	private SharedPreferences theme_choice;
	Resources res = null;
	private SharedPreferences webURL ;
    private String urladdress;
    
    private String[] text = { "会签主题", "发文人", "会签人", "发送时间", "会签内容","会前备注" };  
	public void onCreate(Bundle savedInstanceState) 
	{
		webURL = getSharedPreferences("url", 0);
		urladdress = webURL.getString("address", "");
		
		
		super.onCreate(savedInstanceState); 		
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.emdetail);    
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
        TextView ts = (TextView)findViewById(R.id.titleset);
        ts.setText("我的邮件");
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
        ss = getSharedPreferences("userInfo", 0);
        name=ss.getString("USER_NAME", "");
        em = (Em)getIntent().getSerializableExtra("em");
        inStream  =this.getClass().getClassLoader().getResourceAsStream("setemstate.xml");
		try {			
	        URL url = new URL(urladdress+"EMStateChange");
	        SetEmState g = new SetEmState();
	        result = g.getResult(inStream,em.getId(),url).toString();  
		}
		 catch (Exception e1) {
				Toast.makeText(EmDetail.this, "Error",
	               Toast.LENGTH_SHORT).show(); 
	  }
        
        
        String [] emIn = {em.getEmailTitle(),em.getFromUser(),name,em.getTimeStr(),em.getEmailContent()};
        
        GridView gridview = (GridView) findViewById(R.id.gridView1);
        ArrayList<HashMap<String, Object>> imagelist = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 5; i++) {  
            HashMap<String, Object> map = new HashMap<String, Object>();             
            map.put("text", text[i]);  
            map.put("em", emIn[i]);
            imagelist.add(map);  
        }  
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, imagelist,  
                R.layout.spdetailitem, new String[] { "text","em" }, new int[] {  
                        R.id.title, R.id.detail });  
          // 设置GridView的适配器为新建的simpleAdapter  
        gridview.setAdapter(simpleAdapter);  
        
        


        
        
//        tv1 = (TextView)findViewById(R.id.EmailTitle);
//        tv2 = (TextView)findViewById(R.id.FromUser);
//        tv3 = (TextView)findViewById(R.id.ToUser);
//        tv4 = (TextView)findViewById(R.id.TimeStr);
//        tv5 = (TextView)findViewById(R.id.EmailContent);

//        tv1.setText(em.getEmailTitle());
//        tv2.setText(em.getFromUser());
//        tv3.setText(name);
//        tv4.setText(em.getTimeStr());
//        tv5.setText(em.getEmailContent());
      
	}
	public void onBackPressed() {  
        // do something what you want  
		System.out.println(result+"hahahaha");
        if(result.equals("成功"))
    	{              		           		
    		Intent intent = new Intent(EmDetail.this,Menu.class);            
    		intent.putExtra("flag", 22);
    		startActivity(intent);
    		super.onDestroy();
    	}
        super.onBackPressed();  
    }  
	
	
//	protected void onDestroy() {
//		
//	        
////        	else 
////        		Toast.makeText(EmDetail.this, "发送失败",
////                        Toast.LENGTH_SHORT).show(); 
//	
////		Intent intent = new Intent(EmDetail.this,Menu.class);
////		intent.putExtra("flag", 22);
//        
//        
//        // The activity is about to be destroyed.
//    }
	
//    /**监听对话框里面的button点击事件*/  
//    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener()  
//    {  
//        public void onClick(DialogInterface dialog, int which)  
//        {  
//            switch (which)  
//            {  
//            case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序  
//                finish();  
//                break;  
//            case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框  
//            	
//                break;  
//            default:  
//                break;  
//            }  
//        }  
//    }; 

}
