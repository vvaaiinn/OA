package com.oa;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class PullEmParser implements EmParse {
    
	List<Em>ems = null;
	Em em = null;
	@Override
	public List<Em> parse(InputStream is) throws Exception {
		// TODO �Զ����ɵķ������
		XmlPullParser parser = Xml.newPullParser(); //��android.util.Xml����һ��XmlPullParserʵ��  
        parser.setInput(is, "UTF-8");               //���������� ��ָ�����뷽ʽ  
		
        int eventType = parser.getEventType();  
        while (eventType != XmlPullParser.END_DOCUMENT) {  
            switch (eventType) {  
            case XmlPullParser.START_DOCUMENT:  
                ems = new ArrayList<Em>();  
                break;  
            case XmlPullParser.START_TAG:  
                if (parser.getName().equals("ds")) {  
                    em = new Em();  
                } else if (parser.getName().equals("ID")) {  
                    eventType = parser.next();  
                    em.setId(parser.getText());  
                } else if (parser.getName().equals("EmailTitle")) {  
                    eventType = parser.next();  
                    em.setEmailTitle(parser.getText());  
                }else if (parser.getName().equals("TimeStr")) {  
                    eventType = parser.next();  
                    em.setTimeStr(parser.getText()); 
                }    else if (parser.getName().equals("EmailContent")) {  
                    eventType = parser.next();  
                    em.setEmailContent(parser.getText());  
                }  else if (parser.getName().equals("FromUser")) {  
                    eventType = parser.next();  
                    em.setFromUser(parser.getText());  
                } else if (parser.getName().equals("EmailState")) {  
                    eventType = parser.next();  
                    em.setEmailState(parser.getText());  
                } 
                break;  
                
            case XmlPullParser.END_TAG:  
                if (parser.getName().equals("ds")) {  
                    ems.add(em);  
                    em = null;      
                }  
                break;  
            }  
            eventType = parser.next();  
        }  
        return ems;
	}

}
