package com.shangde.edu.trolley.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.StringUtil;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
public class trolleyAction extends CommonAction {

	private static final Logger logger = Logger.getLogger(trolleyAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Course course;
	private int cid;
	private List<Course> courses = new ArrayList<Course>();
	private ICourse courseService;
	private float totalPrice = 0.00f;
	private boolean isSame = false;// 验证相同课程变量
	private long orderId;
	
	/**
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;
	
	private List<Course> courseList;
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService; 
	
	private List<CourseSortListDTO> courseSortListDTOList;
	
	private ICustomer customerService;

	/**
	 * 添加商品
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addTrolley() {
		try {
			return "addsuccess";
		} catch (Exception ex) {
			logger.error("trolleyAction.addTrolley", ex);
			return ERROR;
		}

	}

	/**
	 * 删除商品
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String delTrolley() {
		try {
			return "delsuccess";
		} catch (Exception ex) {
			logger.error("trolleyAction.delTrolley", ex);
			return ERROR;
		}
	}

	/**
	 * 选择支付
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String chossePay() {
		try {
			return "chosesuccess";
		} catch (Exception ex) {
			logger.error("trolleyAction.chossePay", ex);
			return ERROR;
		}

	}
	/**
	 * 制作订单
	 * 
	 * @return
	 */
	public String makeOrder() {
		try {
			// 随即产生订单号
			int userid = 12;// 假设userid
			Date date = new Date();
			String temp = Long.toString(date.getTime());
			String oidStr = temp + Integer.toString(userid);
			orderId = Long.parseLong(oidStr);
			// 存入cookie
			this.addCookie("orderId", oidStr);
			// 存入session
			// HttpSession session = this.servletRequest.getSession();
			// session.removeAttribute("trolley");
			// session.removeAttribute("totalPrice");
			HttpServletResponse response = ServletActionContext.getResponse();
			CookieHandler.deleteCookieByName(this.servletRequest, response, "courses");
			CookieHandler.deleteCookieByName(this.servletRequest, response, "totalPrice");
			CookieHandler.deleteCookieByName(this.servletRequest, response, "courseorder");
			return "ordersuccess";
		} catch (Exception ex) {
			logger.error("trolleyAction.makeOrder", ex);
			return ERROR;
		}
	}
	
	/**
	 * 查商品列表
	 * 
	 */
	
	public void addCookie(String name, String value) {
		HttpServletResponse response = ServletActionContext.getResponse();
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(2000000);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public void readCookieToList() {
		String cookieString = CookieHandler.getCookieValueByName(
				this.servletRequest, "courses");
		try {
			cookieString=java.net.URLDecoder.decode(cookieString,"utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("trolleyAction.readCookieToList", e);
		}
		String cookieTemp[] = cookieString.split("#");
		if (cookieTemp.length > 0) {
			for (int i = 0; i < cookieTemp.length; i++) {
				if(!StringUtil.isNullString(cookieTemp[i])){
					String goodsProperty[]= cookieTemp[i].split(",");
						Course item = new Course();
						item.setCourseId(Integer.parseInt(goodsProperty[0]));
						item.setTitle(goodsProperty[1]);
						Teacher teacher=new Teacher();
						teacher.setName(goodsProperty[2]);
						item.setTeacher(teacher);
						item.setLessionTime(Float.parseFloat(goodsProperty[3]));
						item.setPrice(Float.parseFloat(goodsProperty[4]));
						item.setGmNum(Integer.parseInt(goodsProperty[5]));
						item.setVedioPicUrl(goodsProperty[6]);
						courses.add(item);
					
				}
			}
		}
	}
	
	/**
	 * 个人中心购物车
	 * @return
	 */
	public String toOrderListForUCenter() {
		try {
			
			int userId = getLoginUserId();
			this.setCourseSortListDTOList(userId);
			
			return "toOrderListForUCenter";
		} catch (Exception ex) {
			logger.error("trolleyAction.toOrderListForUCenter", ex);
			return ERROR;
		}

	}
	
	/**
	 * 设置左侧工具栏
	 * @param userId
	 */
	protected void setCourseSortListDTOList(int userId){
		Customer customer = customerService.getCustomerById(userId);
		
		if(customer.getIsComplete() != 1 ||(customer.getIsComplete() == 1 && customerService.isComplete(userId) >= 0)){
			courseSortListDTOList = coursesortService.getCourseSortListDTOList();
		}
		
		if(userId != 0){
			courseList = cusCouKpointService.getCusCouKpointListByCusId(userId);
		}
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean getIsSame() {
		return isSame;
	}

	public void setIsSame(boolean isSame) {
		this.isSame = isSame;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<CourseSortListDTO> getCourseSortListDTOList() {
		return courseSortListDTOList;
	}

	public void setCourseSortListDTOList(
			List<CourseSortListDTO> courseSortListDTOList) {
		this.courseSortListDTOList = courseSortListDTOList;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

}
