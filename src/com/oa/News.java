package com.oa;

import java.io.Serializable;

import android.app.Application;

public class News implements Serializable{

	private String Id;
	private String Title;
	private String Neirong;
	private String Shijian;
	private String UserName;
	
	public String getId()
	{
		return Id;
	}
	
	public String getTitle()
	{
		return Title;
	}
	public String getNeirong()
	{
		return Neirong;
	}
	public String getShijian()
	{
		return Shijian;
	}
	public void setId(String id)
	{
		this.Id = id;
	}
	public void setTitle(String title)
	{
		this.Title = title;
	}
	public void setNeirong(String neirong)
	{
		this.Neirong = neirong;
	}
	public void setShijian(String shijian)
	{
		this.Shijian = shijian;
	}
	public String cout()
	{
		return "序号:"+Id+", 名字:"+Title +",  内容"+Neirong+",  时间"+Shijian+""+",发布人"+UserName;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}
}
