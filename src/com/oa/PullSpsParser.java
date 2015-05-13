package com.oa;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class PullSpsParser implements SpsParse {
    
	List<Sps>sps = null;
	Sps sp = null;
	@Override
	public List<Sps> parse(InputStream is) throws Exception {
		// TODO �Զ����ɵķ������
		XmlPullParser parser = Xml.newPullParser(); //��android.util.Xml����һ��XmlPullParserʵ��  
        parser.setInput(is, "UTF-8");               //���������� ��ָ�����뷽ʽ  
		
        int eventType = parser.getEventType();  
        while (eventType != XmlPullParser.END_DOCUMENT) {  
            switch (eventType) {  
            case XmlPullParser.START_DOCUMENT:  
                sps = new ArrayList<Sps>();  
                break;  
            case XmlPullParser.START_TAG:  
                if (parser.getName().equals("ds")) {  
                    sp = new Sps();  
                } else if (parser.getName().equals("ID")) {  
                    eventType = parser.next();  
                    sp.setId(parser.getText());  
                } else if (parser.getName().equals("FormName")) {  
                    eventType = parser.next();  
                    sp.setTitle(parser.getText());  
                }else if (parser.getName().equals("TimeStr")) {  
                    eventType = parser.next();  
                    sp.setUptime(parser.getText()); 
                }    else if (parser.getName().equals("UserName")) {  
                    eventType = parser.next();  
                    sp.setUpname(parser.getText());  
                }  else if (parser.getName().equals("WorkName")) {  
                    eventType = parser.next();  
                    sp.setUpdetail(parser.getText());  
                } else if (parser.getName().equals("ShenPiYiJian")) {  
                    eventType = parser.next();  
                    sp.setSPadvice(parser.getText());  
                } else if (parser.getName().equals("TongGuoRenList")) {  
                    eventType = parser.next();  
                    sp.setSPname(parser.getText());  
                }else if (parser.getName().equals("LastTime")) {  
                    eventType = parser.next();  
                    sp.setSPtime(parser.getText());  
                }
                else if (parser.getName().equals("StateNow")) {  
                    eventType = parser.next();  
                    sp.setState(parser.getText());  
                }
                break;  
            case XmlPullParser.END_TAG:  
                if (parser.getName().equals("ds")) {  
                    sps.add(sp);  
                    sp = null;      
                }  
                break;  
            }  
            eventType = parser.next();  
        }  
        return sps;
	}

}
