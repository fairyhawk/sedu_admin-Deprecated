package com.shangde.edu.res.service;


import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.edu.res.domain.Status;
import com.shangde.edu.res.domain.VedioBookCountStatus;
import com.shangde.edu.res.domain.VedioCountStatus;

import java.util.List;

public class VedioCountImpl  extends BaseServiceManyDb implements IVedioCount{

	public int getVedioUserNm(int status) {
		
		return simpleDao.getEntity("Vedio_CT.getCusListCountbydate",status);
	}
	
	public int getStatusUserNm(int status) {
	
		return simpleDao.getEntity("Vedio_CT.getVideoCountbydate",status);
	}

	

	public int getUserIdNum(Status status) {
		return simpleDao.getEntity("Vedio_CT.getUserIdNum",status);
	}

	public int getVedioNum(Status status) {
		return simpleDao.getEntity("Vedio_CT.getVedioNum",status);
	}

	public int getNoteNum(Status status) {
		return simpleDao.getEntity("Vedio_CT.getNoteNum",status);
	}

	public int getNoteLoad(Status status) {
		return simpleDao.getEntity("Vedio_CT.getNoteLoad",status);
	}

	public int getjiangyi(Status status) {
		return simpleDao.getEntity("Vedio_CT.getjiangyi",status);
	}

	public int getpingjia(Status status) {
		return simpleDao.getEntity("Vedio_CT.getpingjia",status);
	}

	public int getCeshi(Status status) {
		return simpleDao.getEntity("Vedio_CT.getCeshi",status);
	}

	public int getqiehuan(Status status) {
		return simpleDao.getEntity("Vedio_CT.getqiehuan",status);
	}

	public int getzice(Status status) {
		return simpleDao.getEntity("Vedio_CT.getzice",status);
	}

	public int getiwen(Status status) {
		return simpleDao.getEntity("Vedio_CT.getiwen",status);
	}

	public int getStatusNo(int status){
		
		return simpleDao.getEntity("Vedio_CT.getStatusNo",status);
	}
	
	public int getKjzc(Status status){
		return simpleDao.getEntity("Vedio_CT.getKjzc",status);
	}
	public int getKjz(Status status){
		return simpleDao.getEntity("Vedio_CT.getKjz",status);
	};
	public int getJjs(Status status){
		return simpleDao.getEntity("Vedio_CT.getJjs",status);
	};
	public int getXl(Status status){
		return simpleDao.getEntity("Vedio_CT.getXl",status);
	};
	public int getJz2(Status status){
		return simpleDao.getEntity("Vedio_CT.getJz2",status);
	};
	
	
	
	
	
	
	
	
	
	public int getVideoCountbydate(int status) {
		
		return simpleDao.getEntity("Vedio_CT.getVideoCountbydate",status);
	}

	public int getVideoCountbyweek(int status) {
		return simpleDao.getEntity("Vedio_CT.getVideoCountbyweek",status);
	}

	public int getVideoCountbymonth(int status) {
		return simpleDao.getEntity("Vedio_CT.getVideoCountbymonth",status);
	}

	public int getVideoCountbyUserDate(int status) {
		return simpleDao.getEntity("Vedio_CT.getCusListCountbydate",status);
	}

	public int getUserWeek(int status) {
		return simpleDao.getEntity("Vedio_CT.getCusListCountbyweek",status);
	}

	public int getUserMonth(int status) {
		return simpleDao.getEntity("Vedio_CT.getCusListCountbymonth",status);	
		}

	public  List<VedioCountStatus> getNoByDateStatus() {
		
		return simpleDao.getForList("Vedio_CT.getNoByDate", null);	
	}
	
	public List<VedioBookCountStatus> getBookNoByDateStatus() {
		
		return simpleDaoRead.getForList("Vedio_CT.getBookNoByDate", null);	
	}
	

}

	

	
	

