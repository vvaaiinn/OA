package com.oa;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class PullInfo implements InfoParse {
	ArrayList<String> info = new ArrayList<String>() ;
	String temp =null;
	@Override
	public ArrayList<String> parse(InputStream is)throws Exception {
		// TODO �Զ����ɵķ������
			
		XmlPullParser parser = Xml.newPullParser(); //��android.util.Xml����һ��XmlPullParserʵ��  
        parser.setInput(is, "UTF-8");  
        int eventType = parser.getEventType();  
        while (eventType != XmlPullParser.END_DOCUMENT)
        {
        	 switch (eventType) {   
        	 case XmlPullParser.START_DOCUMENT: 
        		 temp = null;
        		 break;
        	 case XmlPullParser.START_TAG:  
                 if (parser.getName().equals("UserName")) {  
                     eventType = parser.next();  
                     temp = parser.getText();                     
                 }
                 break;
        	 case XmlPullParser.END_TAG:  
                 if (parser.getName().equals("ds")) {  
                	 info.add(temp);     
                 }  
                 break;    
        	 }
        	 eventType = parser.next();         	 
        }
//		int size = info.size();
//		String []arr = new String[size];
//		for(int i = 0 ; i < size ; i ++)
//		{
//			arr[i] = (String)info.get(i);
//		}
        
		return info;
	}
	

}
