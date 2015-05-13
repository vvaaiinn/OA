package com.oa;

import java.io.InputStream;  
import java.io.StringWriter;  
import java.util.ArrayList;  
import java.util.List;  

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;
public class PullNewsParser implements NewsParse {

	@Override
	public List<News> parse(InputStream is) throws Exception {
		// TODO 自动生成的方法存根
		List<News> books = null;
		News book = null;
		
		XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例  
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式  
		
        int eventType = parser.getEventType();  
        while (eventType != XmlPullParser.END_DOCUMENT) {  
            switch (eventType) {  
            case XmlPullParser.START_DOCUMENT:  
                books = new ArrayList<News>();  
                break;  
            case XmlPullParser.START_TAG:  
                if (parser.getName().equals("ds")) {  
                    book = new News();  
                } else if (parser.getName().equals("ID")) {  
                    eventType = parser.next();  
                    book.setId(parser.getText());  
                } else if (parser.getName().equals("TitleStr")) {  
                    eventType = parser.next();  
                    book.setTitle(parser.getText());  
                }else if (parser.getName().equals("TimeStr")) {  
                    eventType = parser.next();  
                    book.setShijian(parser.getText()); 
                }    else if (parser.getName().equals("ContentStr")) {  
                    eventType = parser.next();  
                    book.setNeirong(parser.getText());  
                } else if (parser.getName().equals("UserName")) {  
                    eventType = parser.next();  
                    book.setUserName(parser.getText());  
                }
               
                break;  
            case XmlPullParser.END_TAG:  
                if (parser.getName().equals("ds")) {  
                    books.add(book);  
                    book = null;      
                }  
                break;  
            }  
            eventType = parser.next();  
        }  
        return books;
	}
	

}
