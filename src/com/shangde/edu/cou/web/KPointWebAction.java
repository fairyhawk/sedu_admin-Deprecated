package com.shangde.edu.cou.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.JSONUtil;
import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import net.sf.json.JSONArray;

import bsh.This;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.Result;
import com.shangde.common.util.json.JsonUtil;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourKpJSONCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.dto.JilianKpointDTO;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.service.IVedio;

/**
 * 知识点Web action
 * @author chenshuai
 */
public class KPointWebAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 知识点
	 */
	public Kpoint kpoint;
	
	/**
	 * 课程Id
	 */
	private int courseId;
	
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	
	/**
	 * 课程服务
	 */
	private ICourse courseService; 
	
	/**
	 * 配置
	 */
	private ConfigService configurator;
	
	/**
	 * 知识点集合
	 */
	private List<Kpoint> kpointList;
	
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	
	private int isLeaf = 0;
	
	private IVedio vedioService;
	/**
	 * 课程id
	 */
	private int cusid;
	
	/**
	 * kpointId 知识点ID
	 */
	private int kpointId;
	
	/**
	 * 专业类型
	 */
	private int eptype;
	
	/**
	 * cusid
	 * 通过课程id查询json
	 */
	@SuppressWarnings("unchecked")
	public String getKpointlistjilian()
	{	
		QueryCourKpJSONCondition qj= new QueryCourKpJSONCondition();
		qj.setCousid(cusid);
		qj.setEptye(eptype);
		try {
//		getQueryKpointCondition().setCourseId(cusid);
		List<JilianKpointDTO> kpointList1;
		
			kpointList1 = kpointService.getJilianKpoint(qj);
			
			this.setResult(new Result(true,null,null,kpointList1));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		return "kpontjson";
	}
	
	/**
	 * 获取知识点下拉列表
	 * @return
	 */
	/* 2010/08/24 Mod By ZhangDong Start */
	public List<KeyValueDTO> getJSONKpoints(int courseId){
		List<KeyValueDTO> myList = null;
		try{
			myList = kpointService.getKeyDTOWebResultByCourseId(courseId);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return myList;
	}
	/* 2010/08/24 Mod By ZhangDong End */
	
	/**
	 * 获取所有知识点下拉列表(待用)
	 * @return
	 */
	public String getAllJSONKpoints(){
		try{
			this.getQueryKpointCondition().setCourseId(kpoint.getCourseId());
			queryKpointCondition.setLeaf(-1);
			List<KeyValueDTO> myList = kpointService.getKpointDTOListByCourseId(queryKpointCondition);
			
			JSONArray jslist = JSONArray.fromObject(myList);
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
			List<Kpoint> result = new ArrayList<Kpoint>();
			getDSCKpoint(20, result);
			
		}catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	/**
	 * 根据子节点递归所有父节点
	 * @param kpointId
	 * @param result
	 */
	public void getDSCKpoint(int kpointId,List<Kpoint> result){
		kpoint = kpointService.getKpointById(kpointId);
		result.add(kpoint);
		if(kpoint.getPId() != 0){
			getDSCKpoint(kpoint.getPId(),result);
		}
	}
	
	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public QueryKpointCondition getQueryKpointCondition() {
		if(queryKpointCondition == null){
			queryKpointCondition = new QueryKpointCondition();
		}
		return queryKpointCondition;
	}

	public void setQueryKpointCondition(QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}

	public List<Kpoint> getKpointList() {
		if(this.kpointList==null){
			kpointList = new ArrayList<Kpoint>();
		}
		return kpointList;
	}
	public void setKpointList(List<Kpoint> kpointList) {
		this.kpointList = kpointList;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}
	
	public int getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public int getCusid() {
		return cusid;
	}

	public void setCusid(int cusid) {
		this.cusid = cusid;
	}

	public int getEptype() {
		return eptype;
	}

	public void setEptype(int eptype) {
		this.eptype = eptype;
	}

	public IVedio getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVedio vedioService) {
		this.vedioService = vedioService;
	}

	public int getKpointId() {
		return kpointId;
	}

	public void setKpointId(int kpointId) {
		this.kpointId = kpointId;
	}
}
