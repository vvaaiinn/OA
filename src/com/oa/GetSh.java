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
        // �滻xml�ļ��еĵ绰����  
        String soap = readSoapFile(inStream, name);  
        byte[] data = soap.getBytes();  
        
        // �ύPost����  
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
            // ����������Ϣ  
            return parseResponseXML(conn.getInputStream());  
        }  
        return "Error";  
    }  
	
	 public String readSoapFile(InputStream inStream, String name) throws Exception  
	    {  
	        // �����л�ȡ�ļ���Ϣ  
	        byte[] data = readInputStream(inStream);  
	        String soapxml = new String(data);  
	        // ռλ������  
	        Map<String, String> params = new HashMap<String, String>();  
	        params.put("name", name);  
	       
	        // �滻�ļ���ռλ��  
	        return replace(soapxml, params);  
	    }  
	  /** 
	     * ��ȡ����Ϣ 
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
	     * �滻�ļ���ռλ�� 
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
	     * ����XML�ļ� 
	     * @param inStream 
	     * @return 
	     * @throws Exception 
	     */ 
		  private static String parseResponseXML(InputStream inStream) throws Exception  
		    {  
		        XmlPullParser parser = Xml.newPullParser();  
		        parser.setInput(inStream, "UTF-8");  
		        int eventType = parser.getEventType();// ������һ���¼�  
		        while (eventType != XmlPullParser.END_DOCUMENT)  
		        {  
		            // ֻҪ�����ĵ������¼�  
		            switch (eventType)  
		            {  
		            case XmlPullParser.START_TAG:  
		                String name = parser.getName();// ��ȡ��������ǰָ���Ԫ�ص�����  
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
