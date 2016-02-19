package com.yf.afs.service;

import java.io.File;
import java.text.MessageFormat;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.yf.afs.bean.MsgType;

@Service
public class MsgService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("#{env.smsPath}")
	private String smsPath;
	
	@Value("#{env.netPath}")
	private String netPath;
	
	@Value("#{env.phonePath}")
	private String phonePath;
//	@Value("classpath:/sms/sms_1.txt")
//	private Resource sms_1;
//	
//	@Value("classpath:/sms/sms_2.txt")
//	private Resource sms_2;
//	
//	@Value("classpath:/sms/sms_3.txt")
//	private Resource sms_3;
	
//	@Value("classpath:/sms/net_1.txt")
//	private Resource net_1;
//	
//	@Value("classpath:/sms/net_2.txt")
//	private Resource net_2;
//	
//	@Value("classpath:/sms/net_3.txt")
//	private Resource net_3;
//	
//	@Value("classpath:/sms/phone_1.txt")
//	private Resource phone_1;
//	
//	@Value("classpath:/sms/phone_2.txt")
//	private Resource phone_2;
//	
//	@Value("classpath:/sms/phone_3.txt")
//	private Resource phone_3;
	
	public String getMsg(MsgType type, String suffix, String... param){
		
		String template = "";
		String tempSMS = "";
		String msgPath = "";
		Resource res = null;
		try{
			switch(type){
			case SMS_1: {msgPath = smsPath + File.separator +"sms_1_"+suffix+".txt" ;  res = new FileSystemResource(msgPath); break;}
			case SMS_2: {msgPath = smsPath + File.separator +"sms_2.txt" ;  res = new FileSystemResource(msgPath); break;}
			case SMS_3: {msgPath = smsPath + File.separator +"sms_3_"+suffix+".txt" ;  res = new FileSystemResource(msgPath); break;}
			case NET_1: {msgPath = netPath + File.separator +"net_1_"+suffix+".txt" ;  res = new FileSystemResource(msgPath); break;}
			case NET_2: {msgPath = netPath + File.separator +"net_2.txt" ;  res = new FileSystemResource(msgPath); break;}
			case NET_3: {msgPath = netPath + File.separator +"net_3_"+suffix+".txt" ;  res = new FileSystemResource(msgPath); break;}
			case PHONE_1:  {msgPath = phonePath + File.separator +"phone_1.txt" ;  res = new FileSystemResource(msgPath); break;}
			case PHONE_2:  {msgPath = phonePath + File.separator +"phone_2.txt" ;  res = new FileSystemResource(msgPath); break;}
			case PHONE_3:  {msgPath = phonePath + File.separator +"phone_3_"+suffix+".txt" ;  res = new FileSystemResource(msgPath); break;}
			}
			template = IOUtils.toString(res.getInputStream(), "utf-8");
			tempSMS = MessageFormat.format(template, param);
		}catch (Exception e) {
			tempSMS = "";
			logger.info("交易出现异常,异常信息为{}", e.getMessage());
		}
		return tempSMS;
	}
}
