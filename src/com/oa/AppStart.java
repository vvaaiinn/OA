package com.oa;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;


public class AppStart extends Activity {
	
	private SharedPreferences theme_choice;
	private Drawable drawable = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        final View view = View.inflate(this, R.layout.start, null);
        setContentView(view);
        
        
        theme_choice  = getSharedPreferences("theme", 0);
        String style_rs = theme_choice.getString("style", "1");
        
 	   	Resources res = getResources();  
 	   
 	   	if(style_rs.equals("1"))
 	   	{
 	   		drawable = res.getDrawable(R.drawable.bluebegin);
 	   		this.getWindow().setBackgroundDrawable(drawable);  
 	   	}
 	   	else {
 	   		drawable = res.getDrawable(R.drawable.redbegin);
 	   		this.getWindow().setBackgroundDrawable(drawable);  
 		}	
        
                                                                      
        //渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.3f,1.0f);
        aa.setDuration(2000);
        view.startAnimation(aa);
        aa.setAnimationListener(new AnimationListener()
        {
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
                                                                          
        });
                                                                      
                                                          
    }
                                                                  
    /**
     * 跳转到...
     */
    private void redirectTo(){       
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
