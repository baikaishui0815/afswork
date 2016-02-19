package com.yf.afs.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.yf.afs.bean.Channel;
import com.yf.afs.bean.QueryRs;

@Service
public class SCodeQueryService {

	@Autowired
	private JdbcTemplate jt;
	
	/**
	 * @param scode
	 * @param channel
	 * @return
	 */
	public QueryRs queryRs(String scode, Channel channel){
		QueryRs rs = new QueryRs();
		
		//查询申码
		String sql = "select * from AFS_SCODE where SCODE=?";
		Map<String, Object> map;
		try {
			map = jt.queryForMap(sql, new String[]{scode});
		} catch (DataAccessException e) {
			map = null;
		}
		
		if(map != null){
			
			rs.setAuthenticity(true);
			String status = (String)map.get("STATUS");
			if("Y".equals(status)){
				rs.setFirstQuery(false);
				int qnum = countqNum(scode);
				rs.setQuerycount(qnum);
				Date fqTime = (Date)map.get("F_Q_TIME");
				rs.setQuerytime(fqTime);
			}else{
				rs.setFirstQuery(true);
				//首次查询需要更新首次查询时间和状态
				sql = "update AFS_SCODE set F_Q_TIME='"+ getNowTime() +"',STATUS='Y' where SCODE='"+scode+"'";
				jt.update(sql);
			}
		}else{
			rs.setAuthenticity(false);
		}
		//记录查询日志
		log(scode,channel,rs);
		return rs;
	}
	
	/**
	 * 统计查询次数
	 * @param scode
	 * @return
	 */
	public int countqNum(String scode){
		String sql = "select count(*) from AFS_SCODE_LOG where SCODE='"+scode+"'";
		
		return jt.queryForInt(sql)+1;
	}
	
	/**
	 * 记录日志
	 * @param scode
	 * @param channel
	 * @param rs
	 */
	public void log(String scode, Channel channel,QueryRs rs){
		
		String sql = null;
		String time = getNowTime();
		if(rs.isAuthenticity()){
			 sql = "insert into AFS_SCODE_LOG(SCODE,CHANNEL_ID,Q_TIME,FLG) values('"+scode+"','"+channel+"','"+ time +"','Y')";
			
		}else{
			 sql = "insert into AFS_SCODE_LOG(SCODE,CHANNEL_ID,Q_TIME,FLG) values('"+scode+"','"+channel+"','"+ time +"','N')";
		}
		jt.update(sql);
	}
	
	private String getNowTime(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
