package com.shangde.edu.crm.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.crm.condition.QueryChanceCondition;
import com.shangde.edu.crm.condition.QueryStatCondition;
import com.shangde.edu.crm.dto.SalesStatDTO;
import com.shangde.edu.crm.service.IChance;
import com.shangde.edu.crm.service.IChanceRead;
import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IGroup;

public class SalesStatAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(SalesStatAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 只读的Chance接口。读数据库从库，禁止执行增删改操作
     */
    private IChanceRead chanceReadService;
	
	
	private QueryStatCondition queryStatCondition;
	private QueryChanceCondition queryChanceCondition;
	
	private IGroup groupService;
	private IChance chanceService;
	
	private int groupIds;
	private String excelName;
	
	private InputStream excelFile;
	
	private List<Group> groupList=new ArrayList<Group>();
	private List <User> userList=new ArrayList<User>();
	private List<Subject> subjectList=new ArrayList<Subject>();
	private List salesStats=new ArrayList();
	private List <SalesStatDTO> salesStatList=new ArrayList<SalesStatDTO>();
	
	
	public String toUndesignedInfo(){
		
		try {
			List<SalesStatDTO> simStatList=new ArrayList<SalesStatDTO>();
			List testSimStats=new ArrayList();
			
		/*	if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
				this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
				
				
				}
			
			simStatList=chanceReadService.getUndesignedInfoBuyHour(queryStatCondition);*/
			/*if(simStatList!=null&&simStatList.size()!=0){
			int id=simStatList.get(0).getUserId();
			int origin=simStatList.get(0).getOrigin();
			int consultStatus=simStatList.get(0).getConsultStatus();
			List <SalesStatDTO>simstatlist=new ArrayList<SalesStatDTO>();
			for(int i=0;i<simStatList.size();i++){
					if(simStatList.get(i).getUserId()==id){
						if(simStatList.get(i).getOrigin()==origin){
							if(simStatList.get(i).getConsultStatus()==consultStatus){
								simstatlist.add(simStatList.get(i));
							}else{
								String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
								for(int k=0;k<24;k++){
									for(int j=0;j<simstatlist.size();j++){
										if(k==simstatlist.get(j).getHours()){
											s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
											s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										}
									}
								}
								testSimStats.add(s);
								simstatlist=new ArrayList<SalesStatDTO>();
								consultStatus=simStatList.get(i).getConsultStatus();
								simstatlist.add(simStatList.get(i)); 
							}
						}else{
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<24;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										
									}
								}
							}
							testSimStats.add(s);
							simstatlist=new ArrayList<SalesStatDTO>();
							origin=simStatList.get(i).getOrigin();
							consultStatus=simStatList.get(i).getConsultStatus();
							simstatlist.add(simStatList.get(i)); 
						}
					}else{
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									
								}
							}
						}
						testSimStats.add(s);
						simstatlist=new ArrayList<SalesStatDTO>();
						id=simStatList.get(i).getUserId();
						origin=simStatList.get(i).getOrigin();
						consultStatus=simStatList.get(i).getConsultStatus();
						simstatlist.add(simStatList.get(i)); 
					}
					if(i==simStatList.size()-1){
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								}
							}
						}
						testSimStats.add(s);
					}
				
				}
			}
			*/
			salesStatList=chanceReadService.getUndesignedInfo(queryStatCondition);
			subjectList=chanceService.getAllSubject();
			
			if(testSimStats!=null&&testSimStats.size()!=0){
				int length=testSimStats.size();
				for(int i=0;i<length;i++){
					salesStats.add(testSimStats.get(i));
				}
			}
			
			return "toUndesignedInfo";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	//未指派追呼统计xsadmin查询方法
public String toXSUndesignedInfo(){
		
		try {
			List<SalesStatDTO> simStatList=new ArrayList<SalesStatDTO>();
			List testSimStats=new ArrayList();
			
		/*	if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
				this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
				
				
				}
			
			simStatList=chanceReadService.getUndesignedInfoBuyHour(queryStatCondition);*/
			/*if(simStatList!=null&&simStatList.size()!=0){
			int id=simStatList.get(0).getUserId();
			int origin=simStatList.get(0).getOrigin();
			int consultStatus=simStatList.get(0).getConsultStatus();
			List <SalesStatDTO>simstatlist=new ArrayList<SalesStatDTO>();
			for(int i=0;i<simStatList.size();i++){
					if(simStatList.get(i).getUserId()==id){
						if(simStatList.get(i).getOrigin()==origin){
							if(simStatList.get(i).getConsultStatus()==consultStatus){
								simstatlist.add(simStatList.get(i));
							}else{
								String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
								for(int k=0;k<24;k++){
									for(int j=0;j<simstatlist.size();j++){
										if(k==simstatlist.get(j).getHours()){
											s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
											s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										}
									}
								}
								testSimStats.add(s);
								simstatlist=new ArrayList<SalesStatDTO>();
								consultStatus=simStatList.get(i).getConsultStatus();
								simstatlist.add(simStatList.get(i)); 
							}
						}else{
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<24;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										
									}
								}
							}
							testSimStats.add(s);
							simstatlist=new ArrayList<SalesStatDTO>();
							origin=simStatList.get(i).getOrigin();
							consultStatus=simStatList.get(i).getConsultStatus();
							simstatlist.add(simStatList.get(i)); 
						}
					}else{
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									
								}
							}
						}
						testSimStats.add(s);
						simstatlist=new ArrayList<SalesStatDTO>();
						id=simStatList.get(i).getUserId();
						origin=simStatList.get(i).getOrigin();
						consultStatus=simStatList.get(i).getConsultStatus();
						simstatlist.add(simStatList.get(i)); 
					}
					if(i==simStatList.size()-1){
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								}
							}
						}
						testSimStats.add(s);
					}
				
				}
			}
			*/
			salesStatList=chanceReadService.getUndesignedInfo(queryStatCondition);
			subjectList=chanceService.getAllSubject();
			
			if(testSimStats!=null&&testSimStats.size()!=0){
				int length=testSimStats.size();
				for(int i=0;i<length;i++){
					salesStats.add(testSimStats.get(i));
				}
			}
			
			return "toXSUndesignedInfo";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 *赵永永
	 *未指派销售追呼统计初始化 
	 * 
	 **/
public String toFirstUndesignedInfo(){
		
		try {
			List<SalesStatDTO> simStatList=new ArrayList<SalesStatDTO>();
			List testSimStats=new ArrayList();
			subjectList=chanceService.getAllSubject();
			if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
				this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");


				
				}
		
			
			return "toFirstUndesignedInfo";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
/**
 *赵永永
 *未指派销售追呼统计初始化 
 * xsadmin
 **/
public String toXSFirstUndesignedInfo(){
	
	try {
		List<SalesStatDTO> simStatList=new ArrayList<SalesStatDTO>();
		List testSimStats=new ArrayList();
		subjectList=chanceService.getAllSubject();
		if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
			this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");


			
			}
	
		
		return "toXSFirstUndesignedInfo";
	} catch (RuntimeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return ERROR;
	}
}
/**
 * 
 *查询全部未指派追呼统计
 *zyy 
 * 
 */
public String toAllUndesignedInfo(){
	
	try {
		List<SalesStatDTO> simStatList=new ArrayList<SalesStatDTO>();
		List testSimStats=new ArrayList();
		
		if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
			this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
			
			
			}
		
		simStatList=chanceReadService.getUndesignedInfoBuyHour(queryStatCondition);
		if(simStatList!=null&&simStatList.size()!=0){
		int id=simStatList.get(0).getUserId();
		int origin=simStatList.get(0).getOrigin();
		int consultStatus=simStatList.get(0).getConsultStatus();
		List <SalesStatDTO>simstatlist=new ArrayList<SalesStatDTO>();
		for(int i=0;i<simStatList.size();i++){
				if(simStatList.get(i).getUserId()==id){
					if(simStatList.get(i).getOrigin()==origin){
						if(simStatList.get(i).getConsultStatus()==consultStatus){
							simstatlist.add(simStatList.get(i));
						}else{
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<24;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									}
								}
							}
							testSimStats.add(s);
							simstatlist=new ArrayList<SalesStatDTO>();
							consultStatus=simStatList.get(i).getConsultStatus();
							simstatlist.add(simStatList.get(i)); 
						}
					}else{
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									
								}
							}
						}
						testSimStats.add(s);
						simstatlist=new ArrayList<SalesStatDTO>();
						origin=simStatList.get(i).getOrigin();
						consultStatus=simStatList.get(i).getConsultStatus();
						simstatlist.add(simStatList.get(i)); 
					}
				}else{
					String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
					for(int k=0;k<24;k++){
						for(int j=0;j<simstatlist.size();j++){
							if(k==simstatlist.get(j).getHours()){
								s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
								s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								
							}
						}
					}
					testSimStats.add(s);
					simstatlist=new ArrayList<SalesStatDTO>();
					id=simStatList.get(i).getUserId();
					origin=simStatList.get(i).getOrigin();
					consultStatus=simStatList.get(i).getConsultStatus();
					simstatlist.add(simStatList.get(i)); 
				}
				if(i==simStatList.size()-1){
					String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
					for(int k=0;k<24;k++){
						for(int j=0;j<simstatlist.size();j++){
							if(k==simstatlist.get(j).getHours()){
								s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
								s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
							}
						}
					}
					testSimStats.add(s);
				}
			
			}
		}
		
		salesStatList=chanceReadService.getUndesignedInfo(queryStatCondition);
		subjectList=chanceService.getAllSubject();
		
		if(testSimStats!=null&&testSimStats.size()!=0){
			int length=testSimStats.size();
			for(int i=0;i<length;i++){
				salesStats.add(testSimStats.get(i));
			}
		}
		
		return "toAllUndesignedInfo";
	} catch (RuntimeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return ERROR;
	}
}
	/**
	 * @王超
	 * 进入销售追呼统计
	 * @return
	 */
	public String toAllSalesStat(){
		try {
			groupList=groupService.getChildGroupById("40");
			userList=chanceService.getSoldUserList(this.getQueryChanceCondition());
			subjectList=chanceService.getAllSubject();
			List testSimStats=new ArrayList();
			if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
			this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
			
			
			}
			
			List<SalesStatDTO> simStatList=new ArrayList<SalesStatDTO>();
			
			simStatList=chanceReadService.getSalesStatByHour(queryStatCondition);
			if(simStatList!=null&&simStatList.size()!=0){
			int id=simStatList.get(0).getUserId();
			int origin=simStatList.get(0).getOrigin();
			int consultStatus=simStatList.get(0).getConsultStatus();
			List <SalesStatDTO>simstatlist=new ArrayList<SalesStatDTO>();
			for(int i=0;i<simStatList.size();i++){
					if(simStatList.get(i).getUserId()==id){
						if(simStatList.get(i).getOrigin()==origin){
							if(simStatList.get(i).getConsultStatus()==consultStatus){
								simstatlist.add(simStatList.get(i));
							}else{
								String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
								for(int k=0;k<24;k++){
									for(int j=0;j<simstatlist.size();j++){
										if(k==simstatlist.get(j).getHours()){
											s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
											s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										}
									}
								}
								testSimStats.add(s);
								simstatlist=new ArrayList<SalesStatDTO>();
								consultStatus=simStatList.get(i).getConsultStatus();
								simstatlist.add(simStatList.get(i)); 
							}
						}else{
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<24;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										
									}
								}
							}
							testSimStats.add(s);
							simstatlist=new ArrayList<SalesStatDTO>();
							origin=simStatList.get(i).getOrigin();
							consultStatus=simStatList.get(i).getConsultStatus();
							simstatlist.add(simStatList.get(i)); 
						}
					}else{
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									
								}
							}
						}
						testSimStats.add(s);
						simstatlist=new ArrayList<SalesStatDTO>();
						id=simStatList.get(i).getUserId();
						origin=simStatList.get(i).getOrigin();
						consultStatus=simStatList.get(i).getConsultStatus();
						simstatlist.add(simStatList.get(i)); 
					}
					if(i==simStatList.size()-1){
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								}
							}
						}
						testSimStats.add(s);
					}
				
				}
			}
			
			this.getQueryStatCondition().setPageSize(200);
			this.setPage(chanceReadService.getSalesStat(queryStatCondition));
			this.getPage().setPageSize(200);
			this.setPageUrlParms();
			if(testSimStats!=null&&testSimStats.size()!=0){
				int size=this.getPage().getPageSize();
				int currentPage=this.getQueryStatCondition().getCurrentPage();
				int length=testSimStats.size();
				for(int i=size*(currentPage-1);i<length&&i<size*currentPage;i++){
					salesStats.add(testSimStats.get(i));
				}
			}
			return "toAllSalesStat";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行toSalesStat错误！", e);
			 return ERROR;
		}
	}
	//第一次跳转
	//跳 空页面 永永
	public String toSelectSalesStat(){
		try {
			groupList=groupService.getChildGroupById("40");
			userList=chanceService.getSoldUserList(this.getQueryChanceCondition());
			subjectList=chanceService.getAllSubject();
			List testSimStats=new ArrayList();
			if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
			this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
/*			this.setPage(chanceReadService.getSalesStat(queryStatCondition));
			this.getPage().setPageSize(200);
			this.setPageUrlParms();*/
			
			}
			
		
			return "toSalesStat";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行toSalesStat错误！", e);
			 return ERROR;
		}
	}
	//第一次跳转
	//xsadmin跳 空页面 永永
	public String toXSfirstSalesStat(){
		try {
			groupList=groupService.getChildGroupById("40");
			userList=chanceService.getSoldUserList(this.getQueryChanceCondition());
			subjectList=chanceService.getAllSubject();
			List testSimStats=new ArrayList();
			if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
			this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
/*			this.setPage(chanceReadService.getSalesStat(queryStatCondition));
			this.getPage().setPageSize(200);
			this.setPageUrlParms();*/
			
			}
			
		
			return "toXSfirstSalesStat";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行toSalesStat错误！", e);
			 return ERROR;
		}
	}
	//xsadmin进入查询方法
	public String toXSSalesStat(){
		try {

			groupList=groupService.getChildGroupById("40");
			userList=chanceService.getSoldUserList(this.getQueryChanceCondition());
			subjectList=chanceService.getAllSubject();
			List testSimStats=new ArrayList();
			if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
			this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
			}
			this.getQueryStatCondition().setPageSize(200);
			PageResult pageResult=chanceReadService.getSalesStat(queryStatCondition);
			this.setPage(pageResult);
			this.getPage().setPageSize(200);
			this.setPageUrlParms();
			List<SalesStatDTO> simStatList=new ArrayList<SalesStatDTO>();
			simStatList=pageResult.getPageResult();
			if(simStatList!=null&&simStatList.size()!=0){
			int id=simStatList.get(0).getUserId();
			int origin=simStatList.get(0).getOrigin();
			int consultStatus=simStatList.get(0).getConsultStatus();
			List <SalesStatDTO>simstatlist=new ArrayList<SalesStatDTO>();
			for(int i=0;i<simStatList.size();i++){
					if(simStatList.get(i).getUserId()==id){
						if(simStatList.get(i).getOrigin()==origin){
							if(simStatList.get(i).getConsultStatus()==consultStatus){
								simstatlist.add(simStatList.get(i));
							}else{
								String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
								for(int k=0;k<24;k++){
									for(int j=0;j<simstatlist.size();j++){
										if(k==simstatlist.get(j).getHours()){
											s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
											s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										}
									}
								}
								testSimStats.add(s);
								simstatlist=new ArrayList<SalesStatDTO>();
								consultStatus=simStatList.get(i).getConsultStatus();
								simstatlist.add(simStatList.get(i)); 
							}
						}else{
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<24;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									}
								}
							}
							testSimStats.add(s);
							simstatlist=new ArrayList<SalesStatDTO>();
							origin=simStatList.get(i).getOrigin();
							consultStatus=simStatList.get(i).getConsultStatus();
							simstatlist.add(simStatList.get(i)); 
						}
					}else{
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								}
							}
						}
						testSimStats.add(s);
						simstatlist=new ArrayList<SalesStatDTO>();
						id=simStatList.get(i).getUserId();
						origin=simStatList.get(i).getOrigin();
						consultStatus=simStatList.get(i).getConsultStatus();
						simstatlist.add(simStatList.get(i)); 
					}
					if(i==simStatList.size()-1){
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								}
							}
						}
						testSimStats.add(s);
					}
				}
			}
		
			if(testSimStats!=null&&testSimStats.size()!=0){
				int size=this.getPage().getPageSize();
				int currentPage=this.getQueryStatCondition().getCurrentPage();
				int length=testSimStats.size();
				for(int i=0;i<pageResult.getPageResult().size();i++){
					salesStats.add(testSimStats.get(i));
				}
			}
			return "toXSSalesStat";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行toSalesStat错误！", e);
			 return ERROR;
		}
	}
	//进入查询方法
	public String toSalesStat(){
		try {

			groupList=groupService.getChildGroupById("40");
			userList=chanceService.getSoldUserList(this.getQueryChanceCondition());
			subjectList=chanceService.getAllSubject();
			List testSimStats=new ArrayList();
			if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
			this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
			}
			this.getQueryStatCondition().setPageSize(200);
			PageResult pageResult=chanceReadService.getSalesStat(queryStatCondition);
			this.setPage(pageResult);
			this.getPage().setPageSize(200);
			this.setPageUrlParms();
			List<SalesStatDTO> simStatList=new ArrayList<SalesStatDTO>();
			simStatList=pageResult.getPageResult();
			if(simStatList!=null&&simStatList.size()!=0){
			int id=simStatList.get(0).getUserId();
			int origin=simStatList.get(0).getOrigin();
			int consultStatus=simStatList.get(0).getConsultStatus();
			List <SalesStatDTO>simstatlist=new ArrayList<SalesStatDTO>();
			for(int i=0;i<simStatList.size();i++){
					if(simStatList.get(i).getUserId()==id){
						if(simStatList.get(i).getOrigin()==origin){
							if(simStatList.get(i).getConsultStatus()==consultStatus){
								simstatlist.add(simStatList.get(i));
							}else{
								String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
								for(int k=0;k<24;k++){
									for(int j=0;j<simstatlist.size();j++){
										if(k==simstatlist.get(j).getHours()){
											s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
											s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										}
									}
								}
								testSimStats.add(s);
								simstatlist=new ArrayList<SalesStatDTO>();
								consultStatus=simStatList.get(i).getConsultStatus();
								simstatlist.add(simStatList.get(i)); 
							}
						}else{
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<24;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									}
								}
							}
							testSimStats.add(s);
							simstatlist=new ArrayList<SalesStatDTO>();
							origin=simStatList.get(i).getOrigin();
							consultStatus=simStatList.get(i).getConsultStatus();
							simstatlist.add(simStatList.get(i)); 
						}
					}else{
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								}
							}
						}
						testSimStats.add(s);
						simstatlist=new ArrayList<SalesStatDTO>();
						id=simStatList.get(i).getUserId();
						origin=simStatList.get(i).getOrigin();
						consultStatus=simStatList.get(i).getConsultStatus();
						simstatlist.add(simStatList.get(i)); 
					}
					if(i==simStatList.size()-1){
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								}
							}
						}
						testSimStats.add(s);
					}
				}
			}
		
			if(testSimStats!=null&&testSimStats.size()!=0){
				int size=this.getPage().getPageSize();
				int currentPage=this.getQueryStatCondition().getCurrentPage();
				int length=testSimStats.size();
				for(int i=0;i<pageResult.getPageResult().size();i++){
					salesStats.add(testSimStats.get(i));
				}
			}
			return "toSalesStat";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行toSalesStat错误！", e);
			 return ERROR;
		}
	}
	
	public String getGroupUserInfo(){
		try {
			userList=chanceService.getGroupUserInfo(groupIds);
			StringBuffer s=new StringBuffer();
				s.append("<select name='queryStatCondition.userId' id='userId'><br><option value='0'>请选择坐席</option>");
			for(int i=0;i<userList.size();i++){
				s.append("<option value="+userList.get(i).getUserId()+">"+userList.get(i).getUserName()+"</option><br>");
			}
			s.append("</select>");
			this.setResult(new Result(true,s.toString(),null,null));
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行getGroupUserInfo错误！", e);
			return ERROR;
		}
	}
	
	
	public String salesStatExportExcelFile(){
		try {
			List testSimStats=new ArrayList();
			if(this.getQueryStatCondition().getStartTime()==null||this.getQueryStatCondition().getEndTime()==null||this.getQueryStatCondition().getStartTime()==""||this.getQueryStatCondition().getEndTime()==""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime((sdf.format(new Date())+" 00:00:00"));
			this.getQueryStatCondition().setEndTime(sdf.format(new Date())+" 23:59:59");
			}
			
			List<SalesStatDTO> simStatList=new ArrayList<SalesStatDTO>();
			this.getQueryStatCondition().setPageSize(5000);
			PageResult pageResult=chanceReadService.getSalesStat(queryStatCondition);
			this.setPage(pageResult);
			this.getPage().setPageSize(5000);
			this.setPageUrlParms();
			simStatList=pageResult.getPageResult();
			//simStatList=chanceService.getSalesStatByHour(queryStatCondition);
			if(simStatList!=null&&simStatList.size()!=0){
			int id=simStatList.get(0).getUserId();
			int origin=simStatList.get(0).getOrigin();
			int consultStatus=simStatList.get(0).getConsultStatus();
			List <SalesStatDTO>simstatlist=new ArrayList<SalesStatDTO>();
			for(int i=0;i<simStatList.size();i++){
					if(simStatList.get(i).getUserId()==id){
						if(simStatList.get(i).getOrigin()==origin){
							if(simStatList.get(i).getConsultStatus()==consultStatus){
								simstatlist.add(simStatList.get(i));
							}else{
								String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
								for(int k=0;k<24;k++){
									for(int j=0;j<simstatlist.size();j++){
										if(k==simstatlist.get(j).getHours()){
											s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
											s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
											
										}
									}
								}
								testSimStats.add(s);
								simstatlist=new ArrayList<SalesStatDTO>();
								consultStatus=simStatList.get(i).getConsultStatus();
								simstatlist.add(simStatList.get(i)); 
							}
						}else{
							String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
							for(int k=0;k<24;k++){
								for(int j=0;j<simstatlist.size();j++){
									if(k==simstatlist.get(j).getHours()){
										s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
										s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
										
									}
								}
							}
							testSimStats.add(s);
							simstatlist=new ArrayList<SalesStatDTO>();
							origin=simStatList.get(i).getOrigin();
							consultStatus=simStatList.get(i).getConsultStatus();
							simstatlist.add(simStatList.get(i)); 
						}
					}else{
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
									
								}
							}
						}
						testSimStats.add(s);
						simstatlist=new ArrayList<SalesStatDTO>();
						id=simStatList.get(i).getUserId();
						origin=simStatList.get(i).getOrigin();
						consultStatus=simStatList.get(i).getConsultStatus();
						simstatlist.add(simStatList.get(i)); 
					}
					if(i==simStatList.size()-1){
						String[][] s={{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""},{"--",""}};
						for(int k=0;k<24;k++){
							for(int j=0;j<simstatlist.size();j++){
								if(k==simstatlist.get(j).getHours()){
									s[k][0]=simstatlist.get(j).getSellCount()+"|"+simstatlist.get(j).getCallCount()+"|"+simstatlist.get(j).getSuccessCount()+"|";
									s[k][1]=simstatlist.get(j).getPayCount()+"+"+simstatlist.get(j).getSendCount();
								}
							}
						}
						testSimStats.add(s);
					}
				
				}
			}

			if(testSimStats!=null&&testSimStats.size()!=0){
				int size=this.getPage().getPageSize();
				int currentPage=this.getQueryStatCondition().getCurrentPage();
				int length=testSimStats.size();
				for(int i=size*(currentPage-1);i<simStatList.size();i++){
					salesStats.add(testSimStats.get(i));
				}
			}
			salesStatList=this.getPage().getPageResult();
			createSalesStatExcelFile();
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
				setExcelName(URLEncoder.encode("销售追呼统计"+sdf.format(new Date())+".xls", "UTF-8"));//IE浏览器
			 }else{
				 setExcelName(new String(("销售追呼统计"+sdf.format(new Date())+".xls").getBytes(),"iso-8859-1"));
			 }
			return "simStatExportExcelFile";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("执行salesStatExportExcelFile错误！", e);
			return ERROR;
		}
	}
	
	
	public void createSalesStatExcelFile() {
		try {
			// 设置表头
			String[] headName = { "项目","销售坐席","来源","咨询状态","订单流水","货到付款激活量","货到付款激活金额","分配量","拨打量","接通量","成交量","成交总量","取消量","转化率","00:00","01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00","13:00",
					"14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00","22:00","23:00"};
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			for (int i = 0; i < headName.length; i++) {
				cell = row.createCell((short) i);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(headName[i]);
			}
			if(salesStatList!=null&&salesStats!=null){
				for (int i = 0; i < salesStatList.size(); i++) {
					row = sheet.createRow(i + 1);
					//项目
					cell = row.createCell((short) 0);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(salesStatList.get(i).getGroupName());
					//销售坐席
					cell = row.createCell((short) 1);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
						cell.setCellValue(salesStatList.get(i).getUserName());
					// 来源
					cell = row.createCell((short) 2);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if(salesStatList.get(i).getOrigin()==0){
					cell.setCellValue("--");
					}else if(salesStatList.get(i).getOrigin()==1){
					cell.setCellValue("自然注册");
					}else if(salesStatList.get(i).getOrigin()==2){
					cell.setCellValue("乐语在线");
					}else if(salesStatList.get(i).getOrigin()==4){
					cell.setCellValue("自然留言");
					}
					//咨询状态
					cell = row.createCell((short) 3);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if(salesStatList.get(i).getConsultStatus()==0){
					cell.setCellValue("--");
					}else if(salesStatList.get(i).getConsultStatus()==1){
					cell.setCellValue("未联系");
					}else if(salesStatList.get(i).getConsultStatus()==2){
					cell.setCellValue("首咨");
					}else if(salesStatList.get(i).getConsultStatus()==3){
					cell.setCellValue("常规回访");
					}else if(salesStatList.get(i).getConsultStatus()==4){
					cell.setCellValue("库存回访");
					}
					//订单流水
					cell = row.createCell((short) 4);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					String s=salesStatList.get(i).getIntBankTotelPrice()+"";
					cell.setCellValue(s.substring(0,s.indexOf(".")+2));
					// 货到付款激活量	
					cell = row.createCell((short) 5);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(salesStatList.get(i).getSendSuccessCount());
					//货到付款激活金额
					cell = row.createCell((short) 6);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					s=salesStatList.get(i).getSendTotelPrice()+"";
					cell.setCellValue(s.substring(0,s.indexOf(".")+2));
					// 分配量
					cell = row.createCell((short) 7);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(salesStatList.get(i).getSellCount());
					// 拨打量
					cell = row.createCell((short) 8);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(salesStatList.get(i).getCallCount());
					//接通量
					cell = row.createCell((short) 9);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(salesStatList.get(i).getSuccessCount());
					//成交量
					cell = row.createCell((short) 10);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(salesStatList.get(i).getPayCount()+"+"+salesStatList.get(i).getSendCount());
					//成交总量
					cell = row.createCell((short) 11);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(salesStatList.get(i).getPayCount()+salesStatList.get(i).getSendCount());
					//取消量
					cell = row.createCell((short) 12);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(salesStatList.get(i).getBackCancelCount()+salesStatList.get(i).getCancelCount()+"|"+salesStatList.get(i).getCancelCount()+"+"+salesStatList.get(i).getBackCancelCount());
					//转化率
					cell = row.createCell((short) 13);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					s=(salesStatList.get(i).getPayCount()+salesStatList.get(i).getSendCount())*100.0/salesStatList.get(i).getSellCount()+"%";
					cell.setCellValue(s.substring(0,s.indexOf(".")+2));
					String[][] ss=(String[][])salesStats.get(i);
					//00:00-23:00
					for(int j=0;j<24;j++){
						int k=14+j;
						cell = row.createCell((short) k);
						cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
						cell.setCellValue(ss[j][0]+ss[j][1]);
					}
				}
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFile(bais);
		} catch (IOException e) {
			logger.error("执行createSalesStatExcelFile错误！", e);
		}
	}

	public QueryStatCondition getQueryStatCondition() {
		if(queryStatCondition==null){
			queryStatCondition=new QueryStatCondition();
		}
		return queryStatCondition;
	}

	public void setQueryStatCondition(QueryStatCondition queryStatCondition) {
		this.queryStatCondition = queryStatCondition;
	}

	public IGroup getGroupService() {
		return groupService;
	}

	public void setGroupService(IGroup groupService) {
		this.groupService = groupService;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public QueryChanceCondition getQueryChanceCondition() {
		if(queryChanceCondition==null){
			queryChanceCondition=new QueryChanceCondition();
		}
		return queryChanceCondition;
	}

	public void setQueryChanceCondition(QueryChanceCondition queryChanceCondition) {
		this.queryChanceCondition = queryChanceCondition;
	}

	public IChance getChanceService() {
		return chanceService;
	}

	public void setChanceService(IChance chanceService) {
		this.chanceService = chanceService;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}






	public List<Subject> getSubjectList() {
		return subjectList;
	}






	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}






	public List getSalesStats() {
		return salesStats;
	}






	public void setSalesStats(List salesStats) {
		this.salesStats = salesStats;
	}


	public int getGroupIds() {
		return groupIds;
	}


	public void setGroupIds(int groupIds) {
		this.groupIds = groupIds;
	}


	public InputStream getExcelFile() {
		return excelFile;
	}


	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}


	public List<SalesStatDTO> getSalesStatList() {
		return salesStatList;
	}


	public void setSalesStatList(List<SalesStatDTO> salesStatList) {
		this.salesStatList = salesStatList;
	}

	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}


    public IChanceRead getChanceReadService() {
        return chanceReadService;
    }


    public void setChanceReadService(IChanceRead chanceReadService) {
        this.chanceReadService = chanceReadService;
    }

}
