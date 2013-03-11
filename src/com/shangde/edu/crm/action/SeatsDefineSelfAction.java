package com.shangde.edu.crm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import net.sf.json.JSONArray;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.crm.domain.UserDTO;
import com.shangde.edu.crm.service.IChance;
import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IGroup;

public class SeatsDefineSelfAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(SeatsDefineSelfAction.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IGroup groupService;
	private IChance chanceService;
	private List<Group> groupList=new ArrayList<Group>();
	private List <UserDTO> userList=new ArrayList<UserDTO>();
	private List <UserDTO> seatsList=new ArrayList<UserDTO>();
	private List <Integer> seatsIdList=new ArrayList<Integer>();
	private int groupIds;
	private String seatsId;
	private String keyword;
	/**
	 * 定时延时时间（分钟）
	 */
	private int timingNum;
	
	
	public String toSeatsDefine(){
		try {
			groupList=groupService.getChildGroupById("40");
			userList=chanceService.getSignSeatsList();
			seatsList=chanceService.getSoldUserList();
			
			//定时延时时间（分钟）
			timingNum = chanceService.getTiming();
			return "toSeatsDefine";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行toSeatsDefine错误！", e);
			return ERROR;
		}
	}
	/*
	 * 快捷筛选
	 * 赵永永
	 * */
	public String getSearchUnsignSeats(){
		try {
			if(keyword!=null&&!keyword.equals("")){
				userList=chanceService.getSearchUnsignSeats(this.getKeyword());
			
			}
			JSONArray jsPbl = JSONArray.fromObject(userList);
			this.setResult(new Result(true,jsPbl.toString(),null,null));
			return "json";
			
		
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行getGroupUserInfo错误！", e);
			return ERROR;
		}
	}
	public String getGroupUserInfo(){
		try {
			userList=chanceService.getUnsignSeatsList();
				JSONArray jsPbl = JSONArray.fromObject(userList);
				this.setResult(new Result(true,jsPbl.toString(),null,null));
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行getGroupUserInfo错误！", e);
			return ERROR;
		}
	}
	
	public String seatsAdd(){
		try {
			String []seats=this.getSeatsId().split(",");
			if(!seats[0].equals("")){
			chanceService.updateSeatsEmpty(groupIds);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("groupId", groupIds);
			map.put("seats", this.getSeatsId());
			chanceService.updateSeatsDefine(map);
			}else{
				chanceService.updateSeatsEmpty(groupIds);
			}
			this.setResult(new Result(true,null,null,null));
			return "json";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("执行seatsAdd错误！", e);
			return ERROR;
		}
	}
	
    /**
     * 修改定时延迟时间
     * @author 代长福
     * @param timingNum
     * @return
     */
	public String updateTiming(){
		try {
			chanceService.updateTiming(timingNum);
			this.setResult(new Result(true,null,null,null));
			return "json";
		} catch (Exception e) {
			logger.error("执行updateTiming错误！", e);
			this.setResult(new Result(false,null,null,null));
			return ERROR;
		}
		
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

	public IChance getChanceService() {
		return chanceService;
	}

	public void setChanceService(IChance chanceService) {
		this.chanceService = chanceService;
	}


	public int getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(int groupIds) {
		this.groupIds = groupIds;
	}

	public String getSeatsId() {
		return seatsId;
	}

	public void setSeatsId(String seatsId) {
		this.seatsId = seatsId;
	}

	public List<Integer> getSeatsIdList() {
		return seatsIdList;
	}

	public void setSeatsIdList(List<Integer> seatsIdList) {
		this.seatsIdList = seatsIdList;
	}

	public List<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserDTO> userList) {
		this.userList = userList;
	}

	public List<UserDTO> getSeatsList() {
		return seatsList;
	}

	public void setSeatsList(List<UserDTO> seatsList) {
		this.seatsList = seatsList;
	}

	public int getTimingNum() {
		return timingNum;
	}

	public void setTimingNum(int timingNum) {
		this.timingNum = timingNum;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
