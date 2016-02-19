package com.yf.afs.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.yf.afs.bean.Channel;
import com.yf.afs.bean.MsgType;
import com.yf.afs.bean.QueryRs;
import com.yf.afs.service.MsgService;
import com.yf.afs.service.SCodeQueryService;

@Controller
@RequestMapping("/afsQuery")
public class AfsQuery {

	@Autowired
	private SCodeQueryService service;
	
	@Autowired
	private MsgService msgService;
	
	@Autowired
	private MappingJackson2JsonView json;
	
	private final static String RES_DATE_FORMAT = "yyyy年MM月dd日 HH时mm分ss秒";
	
	@RequestMapping(value = "/netQuery", method=RequestMethod.GET)
	public  ModelAndView netQuery(@RequestParam(defaultValue="") String scode) {
		
		String result = "";
		String flg = "N";
		HashMap<String, Object> map = new HashMap();
		//检查页面输入是否合法
		if(!StringUtils.isNumeric(scode)){
			map.put("res", "输入不合法,请输入纯数字的防伪码");
			map.put("flg", flg);
			return new ModelAndView(json, map);
		}
		QueryRs res = service.queryRs(scode, Channel.NET);
		if(res.isAuthenticity()){
			
			String prefix = StringUtils.substring(scode, 0, 6);
			if(res.isFirstQuery()){
				
				result = msgService.getMsg(MsgType.NET_1, prefix, "");
				flg = "Y";
			}else{
				
				SimpleDateFormat sf = new SimpleDateFormat(RES_DATE_FORMAT);
				result = msgService.getMsg(MsgType.NET_3, prefix, new String[]{res.getQuerycount()+"", sf.format(res.getQuerytime())});//"您查询的防伪码已被查询,为第"+res.getQuerycount()+ "次查询,首次查询时间为"+sf.format(res.getQuerytime()+"谨防假冒！ 感谢您的本次查询！ 再见！");
			}
		}else{
			result = msgService.getMsg(MsgType.NET_2, "","");//"您好!您查询的该防伪码不存在,谨防假冒!感谢您的本次查询!谢谢!";
		}
		
		map.put("res", result);
		map.put("flg", flg);
		return new ModelAndView(json, map);
	}
	
	@RequestMapping(value = "/smsQuery", method=RequestMethod.GET)
	public  ModelAndView smsQuery(@RequestParam(defaultValue="") String scode) {
		
		String result = "";
		String flg = "N";
		HashMap<String, Object> map = new HashMap();
		//检查页面输入是否合法
		if(!StringUtils.isNumeric(scode)){
			map.put("res", "输入不合法,请输入纯数字的防伪码");
			map.put("flg", flg);
			return new ModelAndView(json, map);
		}
		QueryRs res = service.queryRs(scode, Channel.SMS);
		if(res.isAuthenticity()){
			
			String prefix = StringUtils.substring(scode, 0, 6);
			if(res.isFirstQuery()){
				
				result = msgService.getMsg(MsgType.SMS_1, prefix,"");
				flg = "Y";
			}else{
				
				SimpleDateFormat sf = new SimpleDateFormat(RES_DATE_FORMAT);
				result = msgService.getMsg(MsgType.SMS_3, prefix,new String[]{res.getQuerycount()+"", sf.format(res.getQuerytime())});//"您查询的防伪码已被查询,为第"+res.getQuerycount()+ "次查询,首次查询时间为"+sf.format(res.getQuerytime()+"谨防假冒！ 感谢您的本次查询！ 再见！");
			}
		}else{
			result = msgService.getMsg(MsgType.SMS_2,"", "");//"您好!您查询的该防伪码不存在,谨防假冒!感谢您的本次查询!谢谢!";
		}
		
		map.put("res", result);
		map.put("flg", flg);
		return new ModelAndView(json, map);
	}
	
	@RequestMapping(value = "/phoneQuery", method=RequestMethod.GET)
	public  ModelAndView phoneQuery(@RequestParam(defaultValue="") String scode) {
		
		String result = "";
		//是否首次查询标志
		String flg = "N";
		//是否需要播报语音文件
		String vcfFlg = "N";
		//语音文件名
		String vcfName = "";
		QueryRs res = service.queryRs(scode, Channel.PHONE);
		if(res.isAuthenticity()){
			
			String prefix = StringUtils.substring(scode, 0, 6);
			if(res.isFirstQuery()){
				
				result = msgService.getMsg(MsgType.PHONE_1, "");
				flg = "Y";
				vcfFlg = "Y";
				vcfName = prefix + ".wav";
			}else{
				
				SimpleDateFormat sf = new SimpleDateFormat(RES_DATE_FORMAT);
				result = msgService.getMsg(MsgType.PHONE_3, prefix,new String[]{res.getQuerycount()+"", sf.format(res.getQuerytime())});//"您查询的防伪码已被查询,为第"+res.getQuerycount()+ "次查询,首次查询时间为"+sf.format(res.getQuerytime()+"谨防假冒！ 感谢您的本次查询！ 再见！");
			}
		}else{
			result = msgService.getMsg(MsgType.PHONE_2, "","");//"您好!您查询的该防伪码不存在,谨防假冒!感谢您的本次查询!谢谢!";
			vcfFlg = "Y";
			vcfName = "9999.wav";
		}
		HashMap<String, Object> map = new HashMap();
		map.put("res", result);
		map.put("flg", flg);
		map.put("vcfFlg", vcfFlg);
		map.put("vcfName", vcfName);
		return new ModelAndView(json, map);
	}
}
