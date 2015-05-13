package com.oa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Config extends Activity{

	RadioGroup radiogroup;  
    RadioButton radio1,radio2;
    private SharedPreferences theme_choice;
    SharedPreferences.Editor ed;
    Button btn ;
    String style_rs = null;
    
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.config); 
		radio1 = (RadioButton)findViewById(R.id.blue);
		radio2 = (RadioButton)findViewById(R.id.red);
		radiogroup = (RadioGroup)findViewById(R.id.rgp);
		btn = (Button)findViewById(R.id.btn);
		theme_choice  = getSharedPreferences("theme", 0);
		ed = theme_choice.edit();
		style_rs = theme_choice.getString("style", "1");
		if(style_rs.equals("1"))
			radio1.setChecked(true);
		else 
			radio2.setChecked(true);
		radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {		
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO 自动生成的方法存根
				if(checkedId==radio1.getId())
				{	                
	                ed.putString("style","1");
	                Toast.makeText(Config.this, "你选择的主题是深海魅蓝", Toast.LENGTH_SHORT).show();	                					
				}
				else 
				{
					ed.putString("style", "2");
					Toast.makeText(Config.this, "你选择的主题是璀璨艳红", Toast.LENGTH_SHORT).show();
				}
			}
		});
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
				ed.commit();
				Toast.makeText(Config.this, "主题保存成功，重新启动程序", Toast.LENGTH_SHORT).show();
				Intent i = getBaseContext().getPackageManager()  
				        .getLaunchIntentForPackage(getBaseContext().getPackageName());  
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  
				startActivity(i);  
			}
		});
		
	}
}
