package com.shangde.edu.cusmgr.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.HTMLDecoder;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.condition.QueryCusMgrCondition;
import com.shangde.edu.cusmgr.dto.CourseDTO;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.cusmgr.service.ICusMgr;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Role;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IUser;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

@SuppressWarnings("serial")
public class CusMgrAction extends CommonAction{
	private static final Logger logger = Logger.getLogger(CusMgrAction.class);
	/**
	 * 用户课程知识点关系服务对象
	 */
	private ICusCouKpoint cusCouKpointService;
	
	/**
	 * 用户管理服务对象
	 */
	private ICusMgr cusMgrService;
	
	/**
	 * 课程分类服务对象
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;

	/**
	 * 管理员服务对象
	 */
	private IUser userService;
	
	/**
	 * 用户实体
	 */
	private Customer customer = new Customer();
	
	/**
	 * 用户管理查询条件
	 */
	private QueryCusMgrCondition queryCusMgrCondition;
	
	/**
	 * 课程分类list
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 课程分类list
	 */
	private List<Coursesort> courseSortList2 = new ArrayList<Coursesort>();
	
	/**
	 * 年份list
	 */
	private List<Grade> gradeList = new ArrayList<Grade>();
	
	/**
	 * subjectSet
	 */
	private Set<Subject> subjectSet =new HashSet<Subject>();

	/**
	 * 课程DTOlist
	 */
	private List<CourseDTO> courseDTOList = new ArrayList<CourseDTO>();
	
	/**
	 * id数组
	 */
	private int[] ids;
	
	/**
	 * 管理员实体
	 */
	private User user;
	
	/**
	 * 初始化选课页面
	 * @return
	 */
	public String toElective() {
		try {
			//测试数据
			Cookie cookie = new Cookie("loginedCustomer", null);
			cookie.setValue("3");
			this.getServletResponse().addCookie(cookie);
			
			getQueryCusMgrCondition().setCusId(new Integer(cookie.getValue()));
			if(getQueryCusMgrCondition().getCourseName() != null && !getQueryCusMgrCondition().getCourseName().trim().equals("")){
				if(getQueryCusMgrCondition().getCourseName().indexOf("&#") > -1){
					getQueryCusMgrCondition().setCourseName(HTMLDecoder.decode(getQueryCusMgrCondition().getCourseName()));//将这类代码转换成中文
				}
			}
			
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			//获取课程所属分类
			Coursesort fSort = null;
			Coursesort sSort = null;
			Coursesort tSort = null;
			if(getQueryCusMgrCondition().getSortId() != 0){
				tSort = coursesortService.getCoursesortById(getQueryCusMgrCondition().getSortId());
			}
			
			if(tSort != null && tSort.getPId() != 0){
				sSort = coursesortService.getCoursesortById(tSort.getPId());
			}
			
			if(sSort!= null && sSort.getPId() != 0){
				fSort = coursesortService.getCoursesortById(sSort.getPId());
			}
			
			if(fSort != null){
				courseSortList2.add(fSort);
			}
			
			if(sSort != null){
				courseSortList2.add(sSort);
			}
			
			if(tSort != null){
				courseSortList2.add(tSort);
			}
			
			setPage(cusMgrService.getNomalCourseListBySortId(this.getQueryCusMgrCondition()));
			setPageUrlParms();
		} catch(Exception e) {
			logger.error("CusMgrAction.toElective",e);
			return ERROR;
		}
		return "electivePage";
	}
	
	/**
	 * 获取用户列表
	 * @return
	 */
	public String showCustomerList() {
		try {
			user = getSession(CommonAction.SYS_USER_SESSION_NAME);
			//查询该管理员全下下所能管理的用户
			getQueryCusMgrCondition().setUserId(user.getUserId());
			user = userService.getUserDtoByUserId(user.getUserId());
			
			subjectSet.clear();
			for(int i=0; i<user.getRoleList().size(); i++) {
				Role role = user.getRoleList().get(i);
				for(int j=0; j<role.getSubjectList().size(); j++) {
					subjectSet.add(role.getSubjectList().get(j));
				}
			}
			
			if(getQueryCusMgrCondition().getSubjectId() != 0) {
				gradeList = cusMgrService.getgetGradeListBySubjectId(this.getQueryCusMgrCondition());
			}
			setPage(cusMgrService.getCustomerListByUser(getQueryCusMgrCondition()));
			setPageUrlParms();
		} catch(Exception e) {
			logger.error("CusMgrAction.showCustomerList",e);
			return ERROR;
		}
		return "customerList";
	}
	
	/**
	 * 根据subjectid查询年份列表
	 * @return
	 */
	public String getGradeListBySubjectId() {
		try {
			user = getSession(CommonAction.SYS_USER_SESSION_NAME);
			getQueryCusMgrCondition().setUserId(user.getUserId());
			setResult(new Result<List<Grade>>(true,"",null,cusMgrService.getgetGradeListBySubjectId(this.getQueryCusMgrCondition())));
		} catch(Exception e) {
			logger.error("CusMgrAction.getGradeListBySubjectId",e);
			return ERROR;
		}
		return "gradeList";
	}
	

	/**
	 * 取消选课
	 * @return
	 */
	public String unelective() {
		try {
			getQueryCusMgrCondition().setCusId(getLoginUserId());
			cusCouKpointService.delCusCouKpointByCus(this.getQueryCusMgrCondition());
			sendMessage("success");
		} catch(Exception e) {
			logger.error("CusMgrAction.unelective",e);
			return ERROR;
		}
		return null;
	}
	
	/**
	 * 查看学员信息
	 * @return
	 */
	public String viewCustomer() {
		try {
			customer = customerService.getCustomerById(getQueryCusMgrCondition().getCusId());
			courseDTOList = cusMgrService.getCourseListByCusId(getQueryCusMgrCondition().getCusId());
		} catch(Exception e) {
			logger.error("CusMgrAction.viewCustomer",e);
			return ERROR;
		}
		return "viewCustomer";
	}

	public void sendMessage(String message) throws IOException {
		this.getServletResponse().setCharacterEncoding("utf-8");
		this.getServletResponse().getWriter().write(message);
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public QueryCusMgrCondition getQueryCusMgrCondition() {
		if(queryCusMgrCondition == null) {
			queryCusMgrCondition = new QueryCusMgrCondition();
		}
		return queryCusMgrCondition;
	}

	public void setQueryCusMgrCondition(QueryCusMgrCondition queryCusMgrCondition) {
		this.queryCusMgrCondition = queryCusMgrCondition;
	}

	public void setCusMgrService(ICusMgr cusMgrService) {
		this.cusMgrService = cusMgrService;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<Coursesort> getCourseSortList2() {
		return courseSortList2;
	}

	public void setCourseSortList2(List<Coursesort> courseSortList2) {
		this.courseSortList2 = courseSortList2;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public void setUserService(IUser userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Subject> getSubjectSet() {
		return subjectSet;
	}

	public void setSubjectSet(Set<Subject> subjectSet) {
		this.subjectSet = subjectSet;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public List<CourseDTO> getCourseDTOList() {
		return courseDTOList;
	}

	public void setCourseDTOList(List<CourseDTO> courseDTOList) {
		this.courseDTOList = courseDTOList;
	}
}
