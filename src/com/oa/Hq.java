package com.oa;

import java.io.Serializable;

public class Hq implements Serializable{
	private String ID;
	private String HQTitle;
	private String HQTime;
	private String HQContent;
	private String HQBeizhu;
	private String HQSuser;
	private String HQTuser;
	private String HQState;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getHQTitle() {
		return HQTitle;
	}
	public void setHQTitle(String hQTitle) {
		HQTitle = hQTitle;
	}
	public String getHQTime() {
		return HQTime;
	}
	public void setHQTime(String hQTime) {
		HQTime = hQTime;
	}
	public String getHQContent() {
		return HQContent;
	}
	public void setHQContent(String hQContent) {
		HQContent = hQContent;
	}
	public String getHQBeizhu() {
		return HQBeizhu;
	}
	public void setHQBeizhu(String hQBeizhu) {
		HQBeizhu = hQBeizhu;
	}
	public String getHQState() {
		return HQState;
	}
	public void setHQState(String hQState) {
		HQState = hQState;
	}
	public String getHQSuser() {
		return HQSuser;
	}
	public void setHQSuser(String hQSuser) {
		HQSuser = hQSuser;
	}
	public String getHQTuser() {
		return HQTuser;
	}
	public void setHQTuser(String hQTuser) {
		HQTuser = hQTuser;
	}
	public String cout()
	{
		return ID+HQTitle+HQContent+HQTime;
	}
}
