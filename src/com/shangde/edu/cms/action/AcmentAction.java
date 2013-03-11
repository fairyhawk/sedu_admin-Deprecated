package com.shangde.edu.cms.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.PageQuery;
import com.shangde.edu.cms.condition.QueryAcmentCondition;
import com.shangde.edu.cms.domain.Announcement;
import com.shangde.edu.cms.service.IAnnouncement;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
/**
 * 公告后台管理
 * @author 代长福
 */
public class AcmentAction extends CommonAction{

	/**
	 * 公告servrce
	 */
	private IAnnouncement acementService;

	/**
	 * 专业service
	 */
	private ISubject subjectService;
	
	/**
	 * 公告实体类
	 */
	private Announcement announcement;
	
	/**
	 * 用来存储专业集合list
	 */
	private List<Subject> sujectbList;
	
	/**
	 * 分页查询类
	 */
	private PageQuery pageQuery;
	
	/**
	 * 后台公告查询时，用来存储结束时间
	 */
	private String endTime;
	
	/**
	 * 公告查询的条件
	 */
	private QueryAcmentCondition queryAcmentCondition;
	
	/**
	 * 公告ID
	 */
	private int id = 0;
	
	/**
	 * 公告id拼接的字符串
	 */
	private String ids;
	
	/**
	 * 公告状态
	 */
	private int status;
	
	/**
	 * ajax请求json数据返回
	 */
	private int jsonResult;
	
	public IAnnouncement getAcementService() {
		return acementService;
	}

	public void setAcementService(IAnnouncement acementService) {
		this.acementService = acementService;
	}
	
	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}
	
	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Subject> getSujectbList() {
		return sujectbList;
	}

	public void setSujectbList(List<Subject> sujectbList) {
		this.sujectbList = sujectbList;
	}

	public PageQuery getPageQuery() {
		if(pageQuery==null){
			pageQuery = new PageQuery();
		}
		return pageQuery;
	}

	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public QueryAcmentCondition getQueryAcmentCondition() {
		return queryAcmentCondition;
	}

	public void setQueryAcmentCondition(QueryAcmentCondition queryAcmentCondition) {
		this.queryAcmentCondition = queryAcmentCondition;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(int jsonResult) {
		this.jsonResult = jsonResult;
	}

	/**
	 * 跳转到公告添加
	 * @return
	 */
	public String toAdd(){
		sujectbList = subjectService.getAllSubject();
		Subject sub = new Subject();
		sub.setSubjectName("全站公告");
		sub.setSubjectId(0);
		sujectbList.add(0, sub);
		return "acment_add";
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String add(){
		Date date = new Date();
		announcement.setAddTime(date);	//发布时间
		announcement.setClickNum(0);			//点击数
		acementService.add(announcement);
		return "changeSuccess" ;
	}
	
	/**
	 * 预览
	 * @return
	 */
	public String preview(){
		if(id!=0){
			announcement = acementService.getById(id);
		}else{
			announcement.setAddTime(new Date());
			announcement.setClickNum(0);
		}
		return "preview";
	}
	
	/**
	 * 获取全部公告
	 * @return
	 */
	public String getAll(){
		sujectbList = subjectService.getAllSubject();
		Subject sub = new Subject();
		sub.setSubjectName("全站公告");
		sub.setSubjectId(0);
		sujectbList.add(0, sub);
		setPage(acementService.getAll(this.getPageQuery()));
		setPageUrlParms();
		return "acment_list";
	}
	
	/**
	 * 删除公告
	 * @return
	 */
	public String delete(){
		try {
			acementService.delete(ids);
			jsonResult = 1;
		} catch (Exception e) {
			jsonResult = 0;
			e.printStackTrace();
		}
		return "json";
	}
	
	/**
	 * 跳转到公告编辑页
	 * @return
	 */
	public String toEdit(){
		announcement = acementService.getById(id);
		sujectbList = subjectService.getAllSubject();
		Subject sub = new Subject();
		sub.setSubjectName("全站公告");
		sub.setSubjectId(0);
		sujectbList.add(0, sub);
		return "acment_edit";
	}
	
	/**
	 * 编辑公告
	 * @return
	 */
	public String edit(){
		acementService.update(announcement);
		return "changeSuccess";
	}
	
	/**
	 * 查询单个公告
	 * @return
	 */
	public String getById(){
		
		return null ;
	}
	
	/**
	 * 搜索
	 * @return
	 */
	public String search() {
		try {
			if (queryAcmentCondition.getTitle() != null) {
				queryAcmentCondition.setTitle(URLDecoder.decode(
						queryAcmentCondition.getTitle(), "UTF-8"));
			}
			if(endTime!=null){
				queryAcmentCondition.setEndTime(endTime+" 23:59:59");
			}
			sujectbList = subjectService.getAllSubject();
			Subject sub = new Subject();
			sub.setSubjectName("全站公告");
			sub.setSubjectId(0);
			sujectbList.add(0, sub);
			setPage(acementService.search(queryAcmentCondition));
			setPageUrlParms();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "acment_list";
	}
	
	/**
	 * 修改公告状态（可批量）
	 * @return
	 */
	public String editStatus(){
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", status);
			map.put("ids", ids);
			acementService.updateStatus(map);
			jsonResult = 1;
		} catch (Exception e) {
			jsonResult = 0;
			e.printStackTrace();
		}
		return "json";
	}
	
}
