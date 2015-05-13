package com.oa;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class PullHqParser implements HqParse {
    
	List<Hq>hqs = null;
	Hq hq = null;
	@Override
	public List<Hq> parse(InputStream is) throws Exception {
		// TODO 自动生成的方法存根
		XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例  
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式  
		
        int eventType = parser.getEventType();  
        while (eventType != XmlPullParser.END_DOCUMENT) {  
            switch (eventType) {  
            case XmlPullParser.START_DOCUMENT:  
                hqs = new ArrayList<Hq>();  
                break;  
            case XmlPullParser.START_TAG:  
                if (parser.getName().equals("ds")) {  
                    hq = new Hq();  
                } else if (parser.getName().equals("ID")) {  
                    eventType = parser.next();  
                    hq.setID(parser.getText());  
                } else if (parser.getName().equals("HQTitle")) {  
                    eventType = parser.next();  
                    hq.setHQTitle(parser.getText());  
                }else if (parser.getName().equals("HQTime")) {  
                    eventType = parser.next();  
                    hq.setHQTime(parser.getText()); 
                }    else if (parser.getName().equals("HQContent")) {  
                    eventType = parser.next();  
                    hq.setHQContent(parser.getText());  
                }  else if (parser.getName().equals("HQBeizhu")) {  
                    eventType = parser.next();  
                    hq.setHQBeizhu(parser.getText());  
                }   else if (parser.getName().equals("HQSuser")) {  
                    eventType = parser.next();  
                    hq.setHQSuser(parser.getText());  
                }else if (parser.getName().equals("HQTuser")) {  
                    eventType = parser.next();  
                    hq.setHQTuser(parser.getText());  
                } else if (parser.getName().equals("HQState")) {  
                    eventType = parser.next();  
                    hq.setHQState(parser.getText());  
                } 
                break;                 
            case XmlPullParser.END_TAG:  
                if (parser.getName().equals("ds")) {  
                    hqs.add(hq);  
                    hq = null;      
                }  
                break;  
            }  
            eventType = parser.next();  
        }  
        return hqs;
	}

}
