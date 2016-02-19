package com.yf.afs.bean;

import java.util.Date;

/**
 * 查询结果
 * @author fan
 *
 */
public class QueryRs {

	/**
	 * 真实性
	 */
	private boolean authenticity;
	
	/**
	 * 是否为首次查询
	 */
	private boolean firstQuery;
	
	/**
	 * 查询次数
	 */
	private int querycount;
	
	/**
	 * 查询时间
	 */
	private Date querytime;

	public boolean isAuthenticity() {
		return authenticity;
	}

	public void setAuthenticity(boolean authenticity) {
		this.authenticity = authenticity;
	}

	public boolean isFirstQuery() {
		return firstQuery;
	}

	public void setFirstQuery(boolean firstQuery) {
		this.firstQuery = firstQuery;
	}

	public int getQuerycount() {
		return querycount;
	}

	public void setQuerycount(int querycount) {
		this.querycount = querycount;
	}

	public Date getQuerytime() {
		return querytime;
	}

	public void setQuerytime(Date querytime) {
		this.querytime = querytime;
	}
}
