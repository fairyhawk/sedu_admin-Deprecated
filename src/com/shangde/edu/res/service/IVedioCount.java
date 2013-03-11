package com.shangde.edu.res.service;

import java.util.Date;
import java.util.List;
import java.util.Map;



import com.shangde.edu.res.domain.Status;
import com.shangde.edu.res.domain.VedioCount;




/**
 * @author xiaguangyang
 *
 */

public interface IVedioCount {

	/**
	 * 获取使用人数
	 */
	public int getVedioUserNm(int status);
	/**
	 * 获取状态使用次数
	 */
	public int getStatusUserNm(int status);
	/**
	 * 根据时间段进行查询
	 * @param status
	 * @return
	 */
	public int getUserIdNum(Status status);
	public int getVedioNum(Status status);
	public int getNoteNum(Status status);
	public int getNoteLoad(Status status);
	public int getjiangyi(Status status);
	public int getpingjia(Status status);
	public int getCeshi(Status status);
	public int getqiehuan(Status status);
	public int getzice(Status status);
	public int getiwen(Status status);
	
	//public int getStatusNo(int status);
	
	public int getKjzc(Status status);
	public int getKjz(Status status);
	public int getJjs(Status status);
	public int getXl(Status status);
	public int getJz2(Status status);
	/**
	 * 查询当tian数据
	 * @param status
	 * @return
	 */
	public int getVideoCountbyUserDate(int status);
	public int getVideoCountbydate(int status);
	/**
	 * 查询当周
	 * @param status
	 * @return
	 */
	public int getUserWeek(int status);
	public int getVideoCountbyweek(int status);
	/**
	 * 查询当月
	 * @param status
	 * @return
	 */
	public int getUserMonth(int status);
	public int getVideoCountbymonth(int status);
	
	
	
	public <VedioCountStatus> List getNoByDateStatus();
	
	public <VedioBookCountStatus> List getBookNoByDateStatus();
	
	
	
}
