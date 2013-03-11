package com.shangde.edu.res.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;

import com.shangde.edu.res.domain.Status;
import com.shangde.edu.res.domain.VedioCount;
import com.shangde.edu.res.service.IVedioCount;
/**
 * 查询Action
 * @author xiaguangyang
 *
 */
public class VedioCountAction extends CommonAction {
	private static final Logger logger = Logger.getLogger(VedioAction.class);
		
	private IVedioCount vediocountService;
	
	private List<Integer> list;
	
	private List<Status> lista; 
	
	private List<Status> listabook; 
	
	private Status status = new Status();

	
	public List<Status> getLista() {
		return lista;
	}

	public void setLista(List<Status> lista) {
		this.lista = lista;
	}

	public List<Status> getListabook() {
		return listabook;
	}

	public void setListabook(List<Status> listabook) {
		this.listabook = listabook;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public IVedioCount getVediocountService() {
		return vediocountService;
	}

	public void setVediocountService(IVedioCount vediocountService) {
		this.vediocountService = vediocountService;
	}
	
	public List<Integer> getList() {
		return list;
	}

	public void setList(List<Integer> list) {
		this.list = list;
	}

	public String queryFirstListforUser(){
		
		try {
	
		} catch (Exception e) {
			logger.error("VedioCountAction.queryListforUser", e);
			return ERROR;
		}
		return "queryListforUser";
	}
	public String queryListforUser(){
		
		try {
			this.lista = this.vediocountService.getNoByDateStatus();
		} catch (Exception e) {
			logger.error("VedioCountAction.queryListforUser", e);
			return ERROR;
		}
		return "queryListforUser";
	}
	
	public String queryListforUserBook(){
		
		try {
			this.listabook = this.vediocountService.getBookNoByDateStatus();
		} catch (Exception e) {
			logger.error("VedioCountAction.queryListforUser", e);
			return ERROR;
		}
		return "queryListforUserBook";
	}
	
	
	public String queryList(){
		try {
			if(status.getBeginDate()!=null&&!"".equals(status.getBeginDate())||status.getEndDate()!=null&&!"".equals(status.getEndDate())){
				list = new ArrayList<Integer>();
				list.add(vediocountService.getUserIdNum(status));
				list.add(vediocountService.getVedioNum(status));
				list.add(vediocountService.getNoteNum(status));
				list.add(vediocountService.getNoteLoad(status));
				list.add(vediocountService.getjiangyi(status));
				list.add(vediocountService.getpingjia(status));
				list.add(vediocountService.getCeshi(status));
				list.add(vediocountService.getqiehuan(status));
				list.add(vediocountService.getzice(status));
				list.add(vediocountService.getiwen(status));
				
			}else if(status.getNow()!=null&&!"".equals(status.getNow())){
				list = new ArrayList<Integer>();
				list.add(vediocountService.getVedioUserNm(2));
				list.add(vediocountService.getVideoCountbydate(2));
				list.add(vediocountService.getVideoCountbydate(3));
				list.add(vediocountService.getVideoCountbydate(4));
				list.add(vediocountService.getVideoCountbydate(5));
				list.add(vediocountService.getVideoCountbydate(6));
				list.add(vediocountService.getVideoCountbydate(7));
				list.add(vediocountService.getVideoCountbydate(8));
				list.add(vediocountService.getVideoCountbydate(9));
				list.add(vediocountService.getVideoCountbydate(10));
				
			}else if(status.getWeek()!=null&&!"".equals(status.getWeek())){
				list = new ArrayList<Integer>();
				list.add(vediocountService.getUserWeek(2));
				list.add(vediocountService.getVideoCountbyweek(2));
				list.add(vediocountService.getVideoCountbyweek(3));
				list.add(vediocountService.getVideoCountbyweek(4));
				list.add(vediocountService.getVideoCountbyweek(5));
				list.add(vediocountService.getVideoCountbyweek(6));
				list.add(vediocountService.getVideoCountbyweek(7));
				list.add(vediocountService.getVideoCountbyweek(8));
				list.add(vediocountService.getVideoCountbyweek(9));
				list.add(vediocountService.getVideoCountbyweek(10));

			}else if(status.getMonth()!=null&&!"".equals(status.getMonth())){
				list = new ArrayList<Integer>();
				list.add(vediocountService.getUserMonth(2));
				list.add(vediocountService.getVideoCountbymonth(2));
				list.add(vediocountService.getVideoCountbymonth(3));
				list.add(vediocountService.getVideoCountbymonth(4));
				list.add(vediocountService.getVideoCountbymonth(5));
				list.add(vediocountService.getVideoCountbymonth(6));
				list.add(vediocountService.getVideoCountbymonth(7));
				list.add(vediocountService.getVideoCountbymonth(8));
				list.add(vediocountService.getVideoCountbymonth(9));
				list.add(vediocountService.getVideoCountbymonth(10));
			}else{
				this.lista=this.vediocountService.getNoByDateStatus();
				return "queryListforUser";
			}
		
		} catch (Exception e) {
			logger.error("VedioCountAction.queryList", e);
			return ERROR;
		}
		return "queryList";
		
	}
	
	
	
	public String queryListBook(){
		try {
			if(status.getBeginDate()!=null&&!"".equals(status.getBeginDate())){
				list = new ArrayList<Integer>();
				list.add(vediocountService.getKjzc(status));
				list.add(vediocountService.getKjz(status));
				list.add(vediocountService.getJjs(status));
				list.add(vediocountService.getXl(status));
				list.add(vediocountService.getJz2(status));
				
			}else if(status.getNow()!=null&&!"".equals(status.getNow())){
				list = new ArrayList<Integer>();
				
				list.add(vediocountService.getVideoCountbydate(11));
				list.add(vediocountService.getVideoCountbydate(12));
				list.add(vediocountService.getVideoCountbydate(13));
				list.add(vediocountService.getVideoCountbydate(14));
				list.add(vediocountService.getVideoCountbydate(15));
				
			}else if(status.getWeek()!=null&&!"".equals(status.getWeek())){
				list = new ArrayList<Integer>();
				
				list.add(vediocountService.getVideoCountbyweek(11));
				list.add(vediocountService.getVideoCountbyweek(12));
				list.add(vediocountService.getVideoCountbyweek(13));
				list.add(vediocountService.getVideoCountbyweek(14));
				list.add(vediocountService.getVideoCountbyweek(15));

			}else if(status.getMonth()!=null&&!"".equals(status.getMonth())){
				list = new ArrayList<Integer>();
				
				list.add(vediocountService.getVideoCountbymonth(11));
				list.add(vediocountService.getVideoCountbymonth(12));
				list.add(vediocountService.getVideoCountbymonth(13));
				list.add(vediocountService.getVideoCountbymonth(14));
				list.add(vediocountService.getVideoCountbymonth(15));
			
			}else{
				this.listabook = this.vediocountService.getBookNoByDateStatus();
				return "queryListforUserBook";
			}
		
		} catch (Exception e) {
			logger.error("VedioCountAction.queryListBook", e);
			return ERROR;
		}
		return "queryListBook";
		
	}
	
}
