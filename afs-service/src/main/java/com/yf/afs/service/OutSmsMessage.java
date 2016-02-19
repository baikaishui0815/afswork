package com.yf.afs.service;

/**
 * 接收的短信内容
 * @author fan
 *
 */
public class OutSmsMessage {
	
	private String phoneNo;
	
	private String msgContent;
	
	private int msgStatus;

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public int getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(int msgStatus) {
		this.msgStatus = msgStatus;
	}
}
