package com.oa;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class GetSh {

	public  String getResult(InputStream inStream, String name,URL url) throws Exception  
    {  
        // 替换xml文件中的电话号码  
        String soap = readSoapFile(inStream, name);  
        byte[] data = soap.getBytes();  
        
        // 提交Post请求  
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
        conn.setRequestMethod("POST");  
        conn.setConnectTimeout(5 * 1000);  
        conn.setDoOutput(true);  
        conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");  
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));  
        OutputStream outStream = conn.getOutputStream();  
        outStream.write(data);  
        outStream.flush();  
        outStream.close();  
        
        if (conn.getResponseCode() == 200)  
        {  
            // 解析返回信息  
            return parseResponseXML(conn.getInputStream());  
        }  
        return "Error";  
    }  
	
	 public String readSoapFile(InputStream inStream, String name) throws Exception  
	    {  
	        // 从流中获取文件信息  
	        byte[] data = readInputStream(inStream);  
	        String soapxml = new String(data);  
	        // 占位符参数  
	        Map<String, String> params = new HashMap<String, String>();  
	        params.put("name", name);  
	       
	        // 替换文件中占位符  
	        return replace(soapxml, params);  
	    }  
	  /** 
	     * 读取流信息 
	     *  
	     * @param inputStream 
	     * @return 
	     * @throws Exception 
	   */  
	    private byte[] readInputStream(InputStream inputStream) throws Exception  
	    {  
	        byte[] buffer = new byte[1024];  
	        int len = -1;  
	        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();  
	        while ((len = inputStream.read(buffer)) != -1)  
	        {  
	            outSteam.write(buffer, 0, len);  
	        }  
	        outSteam.close();  
	        inputStream.close();  
	        return outSteam.toByteArray();  
	    }  	    
		/** 
	     * 替换文件中占位符 
	     *  
	     * @param xml 
	     * @param params 
	     * @return 
	     * @throws Exception 
	     */  
	    private String replace(String xml, Map<String, String> params) throws Exception  
	    {  
	        String result = xml;  
	        if (params != null && !params.isEmpty())  
	        {  
	            for (Map.Entry<String, String> entry : params.entrySet())  
	            {  
	                String name = "\\$"+entry.getKey();  
	                Pattern pattern = Pattern.compile(name);  
	                Matcher matcher = pattern.matcher(result);  
	                if (matcher.find())  
	                {  
	                    result = matcher.replaceAll(entry.getValue());  
	                }  
	            }  
	        }  
	        return result;  
	    } 
	    /** 
	     * 解析XML文件 
	     * @param inStream 
	     * @return 
	     * @throws Exception 
	     */ 
		  private static String parseResponseXML(InputStream inStream) throws Exception  
		    {  
		        XmlPullParser parser = Xml.newPullParser();  
		        parser.setInput(inStream, "UTF-8");  
		        int eventType = parser.getEventType();// 产生第一个事件  
		        while (eventType != XmlPullParser.END_DOCUMENT)  
		        {  
		            // 只要不是文档结束事件  
		            switch (eventType)  
		            {  
		            case XmlPullParser.START_TAG:  
		                String name = parser.getName();// 获取解析器当前指向的元素的名称  
		                if ("GetSHResult".equals(name))  
		                {  
		                    return parser.nextText();  
		                }  
		                break;  
		            }  
		            eventType = parser.next();  
		        }  
		        return null;  
		    }  	
}
