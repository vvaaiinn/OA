package com.oa;

import java.io.Serializable;

public class Em implements Serializable{
	private String Id;
	private String EmailTitle;
	private String TimeStr;
	private String EmailContent;
	private String FromUser;
	private String EmailState;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getEmailTitle() {
		return EmailTitle;
	}
	public void setEmailTitle(String emailTitle) {
		EmailTitle = emailTitle;
	}
	public String getTimeStr() {
		return TimeStr;
	}
	public void setTimeStr(String timeStr) {
		TimeStr = timeStr;
	}
	public String getEmailContent() {
		return EmailContent;
	}
	public void setEmailContent(String emailContent) {
		EmailContent = emailContent;
	}
	public String getFromUser() {
		return FromUser;
	}
	public void setFromUser(String fromUser) {
		FromUser = fromUser;
	}
	public String getEmailState() {
		return EmailState;
	}
	public void setEmailState(String emailState) {
		EmailState = emailState;
	}
	public String cout()
	{
		return "序号:"+Id+", 名字:"+EmailTitle +",  内容"+EmailContent+",  时间"+TimeStr+""+",发布人"+FromUser+",状态"+EmailState;
	}

}
