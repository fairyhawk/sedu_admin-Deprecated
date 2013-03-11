package com.shangde.edu.cou.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCouponCondition;
import com.shangde.edu.cou.condition.QueryCpCusCondition;
import com.shangde.edu.cou.domain.Coupon;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.CpCus;
import com.shangde.edu.cou.domain.Uncoupon;
import com.shangde.edu.cou.dto.CourseSortListDTO;
import com.shangde.edu.cou.service.ICoupon;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.ICpCus;
import com.shangde.edu.cou.service.IUncoupon;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;
import com.shangde.edu.mail.service.IMail;

/**  
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */
public class CouponWebAction extends CommonAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 课程知识点记录
	 */
	private ICusCouKpoint cusCouKpointService;
	
	/**
	 * 活动服务对象
	 */
	private ICoupon couponService;
	
	/**
	 * 课程分类服务对象
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 用户服务对象
	 */
	private ICustomer customerService;
	
	/**
	 * 邮件服务对象
	 */
	private IMail mailService;
	
	/**
	 * 优惠券服务对象
	 */
	private IUncoupon uncouponService;
	
	/**
	 * 优惠券用户关系服务对象
	 */
	private ICpCus cpCusService;
	
	/**
	 * 课程服务对象
	 */
	private ICourse courseService;
	
	/**
	 * 活动实体
	 */
	private Coupon coupon = new Coupon();
	
	/**
	 * 优惠券
	 */
	private Uncoupon uncoupon = new Uncoupon();
	
	/**
	 * 课程list
	 */
	private List<Course> courseList;
	
	/**
	 * 课程分类listDTOlist
	 */
	private List<CourseSortListDTO> courseSortListDTOList;
	
	/**
	 * 活动查询条件
	 */
	private QueryCouponCondition queryCouponCondition;
	
	/**
	 * 活动查询条件
	 */
	private QueryCpCusCondition queryCpCusCondition;
	
	/**
	 * 活动list
	 */
	private List<Coupon> couponList = new ArrayList<Coupon>();
	
	/**
	 * id数组
	 */
	private int[] ids;
	
	/**
	 * 优惠券序列号
	 */
	private String serialNumber;
	
	/**
	 * 是否登录
	 */
	private boolean logined = false;
	/**
	 * 切换参数
	 */
	private int location;
	/**
	 * 初始化申请优惠券功能
	 * @return String
	 * @thorows Exception
	 */
	@SuppressWarnings("unchecked")
	public String initApplyCoupon(){
		try {
			if(getLoginUserId()!= 0) {
				logined = true;
			}
			coupon = couponService.getCouponById(coupon.getId());
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "initApplyCoupon";
	}
	
	public String showUserCpCusList() {
		getQueryCpCusCondition().setCusId(getLoginUserId());
		setPage(cpCusService.showUserCpCusList(getQueryCpCusCondition()));
		setPageUrlParms();
		return "showUserCpCusList";
	}
	
	public String addCpCus() {
		uncoupon = uncouponService.getUncouponBySerialNumber(uncoupon.getSerialNumber());
		if(uncoupon == null) {
			setResult(new Result(false, "serialNumberError", "", null));
		} else if(uncoupon.getStatus() == Uncoupon.UNCP_STATUS_USABLE) {
			CpCus cpCus = new CpCus();
			cpCus.setCusId(getLoginUserId());
			cpCus.setId(uncoupon.getId());
			cpCus.setSerialNumber(uncoupon.getSerialNumber());
			cpCus.setStatus(CpCus.CPCUS_STATUS_USABLE);
			
			cpCusService.addCpCus(cpCus);
			setResult(new Result(false, "success", "", null));
		} else {
			setResult(new Result(false, "used", "", null));
		}
		return "json";
	}
	
	/**
	 * 申请优惠券
	 * @return
	 */
	public String applyCoupon() {
		try {
			Customer customer = null;
			int userId = getLoginUserId();
			CpCus cpCus = new CpCus();
			
			//如果用户未登录，那么去数据库中查询此email是否已经注册
			if(userId == 0) {
				customer = customerService.getCustomerByEmail(uncoupon.getEmail());
				if(customer != null) {
					userId = customer.getCusId();
				}
			}
			
			if(userId != 0) {
				//如果email为注册用户，那么根据注册用户id算出序列号，并添加优惠券与用户关系。
				serialNumber = DateUtil.getCouponTime()+userId;
				customer = customerService.getCustomerById(userId);
				uncoupon.setEmail(customer.getEmail());
				
				//添加优惠券与用户关系，这里是准备数据但是并未添加到数据库，
				//下面还需要验证是否已经申请过，在方法末尾添加到数据库中。
				cpCus.setCusId(userId);
				cpCus.setId(uncoupon.getId());
				cpCus.setSerialNumber(serialNumber);
				cpCus.setStatus(CpCus.CPCUS_STATUS_USABLE);
			} else {
				serialNumber = DateUtil.getCouponTime() + new java.util.Random().nextInt(100000);
			}

			//验证是否申请过，如果已申请过，那么返回申请页面并提示。
			if(uncouponService.checkHasApplyed(uncoupon)) {
				List<String> list = new ArrayList<String>();
				list.add("hasApplyed");
				setActionMessages(list);
				coupon.setId(uncoupon.getId());
				return initApplyCoupon();
			}
			
			uncoupon.setSerialNumber(serialNumber);
			uncouponService.addUncoupon(uncoupon);

			//如果email已经注册，那么直接添加优惠券
			if(userId != 0) {
				cpCusService.addCpCus(cpCus);
			}
			sendEmail(customer);
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "changeSuccess";
	}
	
	/**
	 * 申请成功后发送邮件通知
	 * @param customer
	 * @throws IOException
	 */
	private void sendEmail(Customer customer) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		//准备称呼，如果未注册用户，那么直呼用户，否则以昵称或者email成语
		String cusName = "";
		if(customer != null) {
			customer.getCusName();
			if(cusName == null || cusName.trim().equals("")) {
				cusName = customer.getEmail();
			}
		} else {
			cusName = "用户";
		}
		
		//准备发送邮件参数
		map.put("cusName", cusName);
		map.put("email", uncoupon.getEmail());
		map.put("serialNumber", serialNumber);
		map.put("date", format.format(new Date()));
		
		mailService.getMail(IMail.APPLY_COUPON_MAIL, map);
	}
	
	/**
	 * 根据课程ids查询优惠券
	 * @return String
	 * @thorows Exception
	 */
	@SuppressWarnings("unchecked")
	public String showCouponByCou(){
		try {
			//取出userid和课程的ids
			int userId = getLoginUserId();
			
			//查询学员所拥有的优惠券数量
			int couponCount = couponService.getCouponCountByCus(userId);
			
			//设置参数并查询出所购买课程相关的优惠券list
			prepareCouponPram();
			List<Coupon> couponList = couponService.getCouponListByCusCou(getQueryCouponCondition());
			
			//将查询结果装进list
			List list = new ArrayList();
			list.add(couponCount);
			
			//准备返回数据，list的索引0位置中存放一共有多少个优惠券，
			//索引1位置存放有几个可用优惠券，索引2位置存放可用优惠券总金额，但目前没有用到
			if(couponList!=null  &&  couponList.size()>0) {
				//算出优惠券总额
				float totalPrice = 0;
				for(int i=0; i<couponList.size(); i++) {
					totalPrice += couponList.get(i).getPrice();
				}
				
				list.add(couponList.size());
				list.add(totalPrice);
				list.add(couponList.get(0).getCourseId()==0?"":couponList.get(0).getCourseId());
			} else {
				list.add(0);
				list.add(0);
				list.add("");
			}
			
			setResult(new Result<List>(false, "", null, list));
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 准备查询优惠券列表的参数
	 */
	private void prepareCouponPram() {
		//准备用户id和课程的id数组
		getQueryCouponCondition().setCusId(getLoginUserId());
		String[] courses = getQueryCouponCondition().getCourses().split("#");;
		int[] ids = new int[courses.length];
		
		for(int i=0; i<courses.length; i++) {
			try {
				if(courses[i] != null  &&  !courses[i].trim().equals("")) {
					ids[i] = Integer.valueOf(courses[i].split(",")[0]);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		getQueryCouponCondition().setIds(ids);
	}
	
	/**
	 * 获取用户优惠券个数
	 * @return
	 */
	public String getCouponCount() {
		try {
			int couponCount = couponService.getCouponCountByCus(getLoginUserId());
			setResult(new Result<Integer>(false, "", null, couponCount));
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 获取优惠券列表
	 * @return
	 */
	public String showMyCoupon() {
		try {
			int userId = getLoginUserId();
			getQueryCouponCondition().setCusId(userId);
			setPage(couponService.getCouponListByCusId(getQueryCouponCondition()));
			setPageUrlParms();
			//学员中心 左侧功能条
			setCourseSortListDTOList(userId);
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "showMyCoupon";
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
	
	/**
	 * 申请优惠券（已废弃）
	 * @return
	 */
	public String applyCp() {
		try {
			int userId = getLoginUserId();
			
			Coupon cp = new Coupon();
			cp.setPicPath("");
			cp.setCInfo("");
			Integer cpId = couponService.addCoupon(cp);
			
			serialNumber = DateUtil.getCouponTime()+userId;
			CpCus cpCus = new CpCus();
			cpCus.setCusId(userId);
			cpCus.setId(cpId);
			cpCus.setSerialNumber(serialNumber);
			cpCus.setStatus(CpCus.CPCUS_STATUS_USABLE);
			
			Map<String, String> map = new HashMap<String, String>();
			Customer customer = customerService.getCustomerById(getLoginUserId());
			map.put("cusName", customer.getCusName());
			map.put("email", customer.getEmail());
			map.put("serialNumber", serialNumber);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			map.put("date", format.format(new Date()));
			mailService.getMail(IMail.APPLY_COUPON_MAIL, map);
			
			cpCusService.addCpCus(cpCus);
			setResult(new Result<Boolean>(false, "", null, true));
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 使用优惠券
	 * @return
	 */
	public String useCp() {
		try {
			if(serialNumber != null && !serialNumber.startsWith("cp")) {
				serialNumber = "cp" + serialNumber;
			}
			//根据序列号查询出优惠券用户关系
			CpCus cpCus = cpCusService.getCpCusBySN(serialNumber);
			
			if(cpCus == null) {
				Uncoupon uncoupon = uncouponService.getUncouponBySerialNumber(serialNumber);
				if(uncoupon == null) {
					//如果查询结果为空，那么返回
					setResult(new Result<Double>(false, "false", null, 0.0));
				} else {
					//如果不为空，说明此优惠券没激活，下面进行激活并返回价格
					cpCus = new CpCus();
					cpCus.setCusId(getLoginUserId());
					cpCus.setId(uncoupon.getId());
					cpCus.setSerialNumber(serialNumber);
					cpCus.setStatus(CpCus.CPCUS_STATUS_USABLE);
					
					cpCusService.addCpCus(cpCus);
					cpCus = this.cpCusService.getCpCusBySN(serialNumber);
					setResult(new Result(false, "success", "", cpCus.getCoupon().getPrice()));
				}
				return "json";
			} else if(cpCus.getStatus() == CpCus.CPCUS_STAUTS_USED) {
				setResult(new Result<Double>(false, "used", null, 0.0));
			} else {
				
				//准备查询可用优惠券参数
//				prepareCouponPram();
//				List<Coupon> couponList = couponService.getCouponListByCusCou(getQueryCouponCondition());
				
				//迭代查询出的可用优惠券列表 ，看看所输入的序列号是否包含在内，
				//包含，那么就说明可以用，将优惠金额返回，否则返回并提示序列号对应
				//的优惠券所能优惠的课程。
//				for(int i=0; i<couponList.size(); i++) {
//					if(couponList.get(i).getId() == cpCus.getId()) {
//						setResult(new Result<Float>(false, "success", null, couponList.get(i).getPrice()));
//						return "json";
//					}
//				}
				
//				coupon = couponService.getCouponById(cpCus.getId());
//				Course course = courseService.getCourseById(coupon.getCourseId());
//				setResult(new Result<Double>(false, "invalid:" + course.getTitle(), null, 0.0));
				setResult(new Result<Double>(false, "success", null, (double)cpCus.getCoupon().getPrice()));
			}
		} catch(Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public QueryCouponCondition getQueryCouponCondition() {
		if(queryCouponCondition == null) {
			queryCouponCondition = new QueryCouponCondition();
		}
		return queryCouponCondition;
	}

	public void setQueryCouponCondition(QueryCouponCondition queryCouponCondition) {
		this.queryCouponCondition = queryCouponCondition;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}

	public void setUncouponService(IUncoupon uncouponService) {
		this.uncouponService = uncouponService;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public void setCouponService(ICoupon couponService) {
		this.couponService = couponService;
	}

	public void setCpCusService(ICpCus cpCusService) {
		this.cpCusService = cpCusService;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setMailService(IMail mailService) {
		this.mailService = mailService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public Uncoupon getUncoupon() {
		return uncoupon;
	}

	public void setUncoupon(Uncoupon uncoupon) {
		this.uncoupon = uncoupon;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
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
	}	public ICoursesort getCoursesortService() {
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

	public QueryCpCusCondition getQueryCpCusCondition() {
		if(queryCpCusCondition == null) {
			queryCpCusCondition = new QueryCpCusCondition();
		}
		return queryCpCusCondition;
	}

	public void setQueryCpCusCondition(QueryCpCusCondition queryCpCusCondition) {
		this.queryCpCusCondition = queryCpCusCondition;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
}
