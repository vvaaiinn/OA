package com.oa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
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

import com.oa.R;

public class SpDetail extends Activity {

    TextView tv = null;
    TextView tv1 = null;
    TextView tv2 =null;
    TextView tv3 = null;
    TextView tv4 = null;
    TextView tv5 = null;
    EditText ad = null;
    Button ag = null;
    Button dag = null;
    String name = null;
    private String[] text = { "��������", "����ʱ��", "������", "��ǰ״̬", "��ϸ��Ϣ", "������" };  
    private SharedPreferences ss;
    Drawable top_drDrawable = null;
	private SharedPreferences theme_choice;
	Resources res = null;
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);  
       // setContentView(R.layout.spdetail); 
        
       requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        //ʹ�ò����ļ������������	
       setContentView(R.layout.spdetail);    
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
       ts.setText("�ҵ�����");
       
       final Sps sp = (Sps)getIntent().getSerializableExtra("sp");
       String[] spIn  ={sp.getTitle(),sp.getUptime(),sp.getUpname(),sp.getState(),sp.getUpdetail(),sp.getSPadvice()};
       
       GridView gridview = (GridView) findViewById(R.id.gridView1);
       ArrayList<HashMap<String, Object>> imagelist = new ArrayList<HashMap<String, Object>>();
       for (int i = 0; i < 6; i++) {  
           HashMap<String, Object> map = new HashMap<String, Object>();             
           map.put("text", text[i]);  
           if(spIn[i]==null)
        	   map.put("sp", "����");
           else 
        	   map.put("sp", Html.fromHtml(spIn[i]));
           imagelist.add(map);  
       }  
       SimpleAdapter simpleAdapter = new SimpleAdapter(this, imagelist,  
               R.layout.spdetailitem, new String[] { "text","sp" }, new int[] {  
                       R.id.title, R.id.detail });  
         // ����GridView��������Ϊ�½���simpleAdapter  
       gridview.setAdapter(simpleAdapter);  

       
//        
//         tv = (TextView)findViewById(R.id.showtitle);
//         tv1 = (TextView)findViewById(R.id.showtime);
//         tv2 = (TextView)findViewById(R.id.showdetail);
//         tv3 = (TextView)findViewById(R.id.showname);
//         tv4 =(TextView)findViewById(R.id.showstate);
//         tv5 =(TextView)findViewById(R.id.showad);
//         
//         
////         ad = (EditText)findViewById(R.id.advice);
////         ag = (Button)findViewById(R.id.agree);
// //        dag = (Button)findViewById(R.id.disagree);      
//         
//        // if(!sp.getState().toString().equals("���ڰ���"))
//         {
////        	 ag.setVisibility(View.INVISIBLE);
////        	 dag.setVisibility(View.INVISIBLE);
////        	 ad.setVisibility(View.INVISIBLE);
////        	 tv5.setVisibility(View.VISIBLE);
//        	 
//         }
//     //   else
//        {
////       	    tv5.setVisibility(View.INVISIBLE); 
////        	 ag.setVisibility(View.VISIBLE);
////        	 dag.setVisibility(View.VISIBLE);
////        	 ad.setVisibility(View.VISIBLE);
//         }
//       
//        tv2.setMovementMethod(ScrollingMovementMethod.getInstance());
//        System.out.println(sp.cout());             
//        //System.out.println("hahahaha");
//        tv.setText(sp.getTitle());
//        tv1.setText(sp.getUptime());
//        tv2.setText(sp.getUpdetail());
//        tv3.setText(sp.getUpname());
//        tv4.setText(sp.getState());
//        //ad.setText(sp.getSPadvice());
//        if(sp.getSPadvice()==null)
//        {
//        	tv5.setText("�����������");
//        }
//        else 
//        	tv5.setText(sp.getSPadvice());
//        ss = getSharedPreferences("userInfo", 0);
//        name=ss.getString("USER_NAME", "");
//                    
////        ag.setOnClickListener(new OnClickListener()
////        {
////
////			@Override
////			public void onClick(View v) {
////				// TODO �Զ����ɵķ������
////				String advice = ad.getText().toString();
////				sp.setSPadvice(advice);
////				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
////				sp.setSPtime(df.format(new Date()).toString());
////				sp.setSPname(name);
////				sp.setState("���ͨ��");
////				System.out.println(sp.cout());
////				
////				InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("setsp.xml");                                   
////			    try  
////                { 
////                	URL url = new URL("http://10.34.11.224:82/WebService.asmx?op=SPchange");     
////                	Setsp g = new Setsp();
////                	String result = g.getResult(inStream, sp.getId(),sp.getSPadvice(),sp.getSPname(),sp.getSPtime(),sp.getState(),url).toString();
////                	System.out.println(result);
////                	if(result.equals("����ɹ�"))
////                	{              		
////                		Intent intent = new Intent(SpDetail.this,SpShow.class);
////                		startActivity(intent);
////                		Toast.makeText(SpDetail.this, "��������ɹ�",
////                                Toast.LENGTH_SHORT).show(); 
////                	}
////                	else 
////                		Toast.makeText(SpDetail.this, "�޸�ʧ��",
////                                Toast.LENGTH_SHORT).show(); 
////                } 
////                catch (Exception e)  
////                {  
////                    //Log.e(TAG, e.toString());  
////                    Toast.makeText(SpDetail.this, "Error",
////                            Toast.LENGTH_SHORT).show(); 
////                } 
////				
////				
////				//finish();
////			}
////        	
////        });
////        
////        dag.setOnClickListener(new OnClickListener()
////        {
////
////			@Override
////			public void onClick(View v) {
////				// TODO �Զ����ɵķ������
////				String advice = ad.getText().toString();
////				sp.setSPadvice(advice);
////				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
////				sp.setSPtime(df.format(new Date()).toString());
////				sp.setSPname(name);
////				sp.setState("��˲�ͨ��");
////				System.out.println(sp.cout());	
////				InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("setsp.xml");                                   
////			    try  
////                { 
////                	URL url = new URL("http://10.34.11.224:82/WebService.asmx?op=SPchange");     
////                	Setsp g = new Setsp();
////                	String result = g.getResult(inStream, sp.getId(),sp.getSPadvice(),sp.getSPname(),sp.getSPtime(),sp.getState(),url).toString();
////                	System.out.println(result);
////                	if(result.equals("����ɹ�"))
////                	{              		
////                		Intent intent = new Intent(SpDetail.this,SpShow.class);
////                		startActivity(intent);
////                		Toast.makeText(SpDetail.this, "��������ɹ�",
////                                Toast.LENGTH_SHORT).show(); 
////                	}
////                	else 
////                		Toast.makeText(SpDetail.this, "�޸�ʧ��",
////                                Toast.LENGTH_SHORT).show(); 
////                } 
////                catch (Exception e)  
////                {  
////                    //Log.e(TAG, e.toString());  
////                    Toast.makeText(SpDetail.this, "Error",
////                            Toast.LENGTH_SHORT).show(); 
////                }
////			}
////        	
////        });
////        
	}
}
