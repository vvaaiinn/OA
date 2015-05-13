package com.oa;

import java.io.Serializable;

public class Sps implements Serializable {
	private String id;
	private String title;
	private String uptime;
	private String upname;
	private String updetail;
	private String SPadvice;
	private String SPname;
	private String SPtime;
	private String state;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUptime() {
		return uptime;
	}
	public void setUptime(String uptime) {
		this.uptime = uptime;
	}
	public String getUpname() {
		return upname;
	}
	public void setUpname(String upname) {
		this.upname = upname;
	}
	public String getUpdetail() {
		return updetail;
	}
	public void setUpdetail(String updetail) {
		this.updetail = updetail;
	}
	public String getSPadvice() {
		return SPadvice;
	}
	public void setSPadvice(String sPadvice) {
		SPadvice = sPadvice;
	}
	public String getSPname() {
		return SPname;
	}
	public void setSPname(String sPname) {
		SPname = sPname;
	}
	public String getSPtime() {
		return SPtime;
	}
	public void setSPtime(String sPtime) {
		SPtime = sPtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String cout()
	{
		return "序号:"+id+", 标题:"+title +"提交时间:"+uptime+",提交人名字："+ upname+",  内容"+updetail
				+"审批意见"+SPadvice+"审批人"+SPname+"审批时间"+SPtime;
	}
	

}
