package com.yf.afs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 短信服务类
 * @author fan
 *
 */
@Service
public class SmsService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private JdbcTemplate jt;
	
	@Value("#{env.afsurl}")
	private String afsUrl;
	
	public void procSms(){
		
		List<InSmsMessage> list = getMsg();
		logger.info("记录数为:"+ list.size());
		if(list.size() > 0){
			sendMsgs(list);
		}
	}
	
	/**
	 * 获取接收的短信内容
	 * @return
	 */
	public List<InSmsMessage> getMsg(){
		List<InSmsMessage> msgs = new ArrayList<InSmsMessage>();
		String sql = "select * from TB_CONTROL where STATE=?";
		List<Map<String, Object>> msgList;
		try {
			msgList = jt.queryForList(sql, new Integer[]{0});
		} catch (DataAccessException e) {
			msgList = null;
		}
		
		if(msgList != null){
			
			for(Map<String, Object> map : msgList){
				InSmsMessage insms = new InSmsMessage();
				insms.setMsgId((Integer)map.get("ID"));
				insms.setPhoneNo((String)map.get("USERID"));
				insms.setMsgContent((String)map.get("RESULT"));
				insms.setMsgStatus((Integer)map.get("STATE"));
				msgs.add(insms);
			}
		}
		return msgs;
	}
	
	/**
	 * 发送消息方法
	 * @param list
	 */
	public void sendMsgs(List<InSmsMessage> list){
		String url = afsUrl;
		RestTemplate rt = new RestTemplate();
		String sql = null;
		for(InSmsMessage insms : list){
			//发送http请求到xxx
			if(!StringUtils.isNumeric(insms.getMsgContent())){
				//更新消息状态
				sql = "UPDATE TB_CONTROL SET  state = 1 WHERE id ="+insms.getMsgId();
				jt.update(sql);
				continue;
			}
					
			url = url + insms.getMsgContent();
			logger.info("请求的url为{}", url);
			try {
				String resp = rt.getForObject(url, String.class);
				logger.info("接收到的响应为{}", resp);
				JSONObject js = new JSONObject(resp);
				String res = js.getString("res");
				sql = "INSERT INTO TB_QUEUE ( pno, spno, linkid, phone, msg, fee, userid, pwd, sid) VALUES (-1, '-1', '-1', '"+insms.getPhoneNo()+"', '"+res+"', 0, '13123456789', '1', '0')";
				//插入发送队列表
				jt.update(sql);
				//更新消息状态
				sql = "UPDATE TB_CONTROL SET  state = 1 WHERE id ="+insms.getMsgId();
				jt.update(sql);
			} catch (JSONException e) {
				e.printStackTrace();
				logger.info("出现异常,异常信息为{}", e.getMessage());
			}
		}
	}
}
