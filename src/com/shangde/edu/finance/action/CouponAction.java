package com.shangde.edu.finance.action;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.finance.condition.QueryCouponCondition;
import com.shangde.edu.finance.condition.QueryCouponTypeCondition;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.domain.Cooperation;
import com.shangde.edu.finance.domain.Coupon;
import com.shangde.edu.finance.domain.CouponRecordInfo;
import com.shangde.edu.finance.domain.Coupontype;
import com.shangde.edu.finance.service.ICoupon;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.sys.domain.User;

/**
 * 
 * @author zhouzhiqiang
 * @version 1.0
 */

public class CouponAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(CouponAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 项目集合
	 */
	private List<Subject> subjectList;

	private Subject subject;

	/**
	 * service
	 */
	private ISubject subjectService;
	/**
	 * 活动服务对象
	 */
	private ICoupon financeCouponService;

	/**
	 * 课程分类服务对象
	 */
	private ICoursesort financeCoursesortService;

	/**
	 * 课程分类list
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();

	/**
	 * 活动实体
	 */
	private Coupon coupon = new Coupon();

	/**
	 * 活动查询条件
	 */
	public  QueryCouponCondition queryCouponCodeCondition;
	
	/**
	 * 导出条件
	 */

	public static QueryCouponCondition queryCouponCodeConditionExport;
	
	/**
	 * 活动list
	 */
	private List<Coupon> couponList = new ArrayList<Coupon>();

	/**
	 * id数组
	 */
	private int[] ids;

	/**
	 * 优惠券分类管理
	 */
	private QueryCouponTypeCondition queryCouponTypeCondition;

	private Coupontype couponType = new Coupontype();

	private InputStream excelFile;

	private String exportName;

	private String couponCode;

	private File fileupload; // 和JSP中input标记name同名

	private String fileuploadFileName; // 上传来的文件的名字

	private int couponTypeId;

	private int couponId;

	private int contractId;
	private Contract contract;

	private List<String> sellWayNames;

	private int chuaCouponType; // 页面传值状态
	private String sendLinks;//手机号列表
	private String couponCodes;//优惠券编码列表
	
	/**
	 * 用户服务对象
	 */
	private IsmsService smsService;
	

	// 李明开始
	/**
	 * 新建优惠券entity
	 */
	private Coupontype coupontype;
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	/**
	 * Cooperation Entity
	 */
	private Cooperation cooperation;
	/**
	 * return 合作商<list>
	 */
	private List<Cooperation> cooperationList = new ArrayList<Cooperation>();
	
	/**
	 * Liming
	 * 发送优惠券短信记录实体
	 */
	private CouponRecordInfo couponRecordInfo;
	

	// 李明结束

	/**
	 * 添加优惠券
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String addCoupon() {
		try {
			financeCouponService.addCoupon(coupon);
		} catch (Exception e) {
			logger.error("CouponAction.addCoupon",e);
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 修改优惠券
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String updateCoupon() {
		try {
			financeCouponService.updateCoupon(coupon);
		} catch (Exception e) {
			logger.error("CouponAction.updateCoupon",e);
			return ERROR;
		}
		return "changeSuccess";
	}

	/**
	 * 删除优惠券
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String deleteCoupon() {
		try {
			if (ids != null) {
				for (int i = 0; i < ids.length; i++) {
					financeCouponService.delCouponById(ids[i]);
				}
			}
		} catch (Exception e) {
			logger.error("CouponAction.deleteCoupon",e);
			return ERROR;
		}
		return "relist";
	}

	/**
	 * 分页查询
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String showCouponList() {
		try {
			// String cInfo = getQueryCouponCondition().getCInfo();
			// if(cInfo != null) {
			// getQueryCouponCondition().setCInfo(cInfo.trim());
			// }
			getQueryCouponTypeCondition().setPageSize(20);
			setPage(financeCouponService
					.getCouponTypeListByCondition(getQueryCouponTypeCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
		} catch (Exception e) {
			logger.error("CouponAction.showCouponList",e);
			return ERROR;
		}
		return "list";
	}

	/**
	 * 多条件查询优惠券类别
	 * 
	 * @return
	 */
	public String showCouponListByWhere() {
		try {
			// String cInfo = getQueryCouponCondition().getCInfo();
			// if(cInfo != null) {
			// getQueryCouponCondition().setCInfo(cInfo.trim());
			// }
			getQueryCouponTypeCondition().setPageSize(20);
			setPage(financeCouponService
					.getCouponTypeListByWhere(getQueryCouponTypeCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
		} catch (Exception e) {
			logger.error("CouponAction.showCouponListByWhere",e);
			return ERROR;
		}
		return "list";
	}

	/**
	 * 初始化添加页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String initAddCoupon() {
		try{
			courseSortList = financeCoursesortService
					.getChildCoursesortList(new QueryCoursesortCondition());
		}catch(Exception e){
			logger.error("CouponAction.initAddCoupon",e);
			return ERROR;
		}
		return "addPage";
	}

	/**
	 * 初始化修改页面
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String initUpdateCoupon() {
		try {
			coupon = financeCouponService.getCouponById(coupon.getId());
		} catch (Exception e) {
			logger.error("CouponAction.initUpdateCoupon",e);
			return ERROR;
		}
		return "updatePage";
	}

	/**
	 * 更改优惠券类别状态
	 * 
	 * @return
	 */
	public String UpdateCouponTypeByState() {
		try {
			Coupontype mycouponType = financeCouponService
					.GetCouponTypeById(couponType.getId());
			mycouponType.setState(couponType.getState());
			financeCouponService.UpdateCouponType(mycouponType);
			if(couponType.getState()==4){
			financeCouponService.updateZFCouponByParentId(mycouponType.getId());
			}
			queryCouponTypeCondition = new QueryCouponTypeCondition();
			queryCouponTypeCondition.setCurrentPage(1);
			showCouponList();// 调取查询页面
		} catch (Exception ex) {
			logger.error("CouponAction.UpdateCouponTypeByState",ex);
			return ERROR;
		}
		return "list";
	}

	/**
	 * 多个优惠券类别 同时更新状态
	 * 
	 * @return
	 */
	public String UpdateCouponTypeByStateForMore() {
		try {
			int state = couponType.getState();
			if (ids.length > 0) {
				for (int id : ids) {
					Coupontype mycouponType = financeCouponService
							.GetCouponTypeById(id);
					if (state == 4) {// 作废
						if (mycouponType.getState() == 3
								|| mycouponType.getState() == 2)
							continue;
					}
					if (state == 3) {// 冻结
						if (mycouponType.getState() == 2
								|| mycouponType.getState() == 4)
							continue;
					}
					if (state == 1) {// 解冻
						if (mycouponType.getState() != 3)
							continue;
					}
					mycouponType.setState(couponType.getState());
					financeCouponService.UpdateCouponType(mycouponType);
					if(couponType.getState()==4){
						financeCouponService.updateZFCouponByParentId(mycouponType.getId());
					}
				}
			}
			queryCouponTypeCondition = new QueryCouponTypeCondition();
			queryCouponTypeCondition.setCurrentPage(1);
			showCouponList();// 调取查询页面
		} catch (Exception ex) {
			logger.error("CouponAction.UpdateCouponTypeByStateForMore",ex);
			return ERROR;
		}
		return "list";
	}

	/**
	 * 获取优惠券编码列表
	 */
	public String getCouponCodeList() {
		try {
			getQueryCouponCodeCondition().setPageSize(20);
			setPage(financeCouponService
					.getCouponList(getQueryCouponCodeCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
			return "couponCode";
		} catch (Exception ex) {
			logger.error("CouponAction.getCouponCodeList",ex);
			return ERROR;
		}
	}

	/**
	 * 更新优惠券状态
	 * 
	 * @return
	 */
	public String UpdateCouponCodeByState() {
		try {
			Coupon mycoupon = financeCouponService
					.getCouponById(coupon.getId());
			mycoupon.setState(4);
			financeCouponService.updateCoupon(mycoupon);
			getCouponCodeList();// 调取查询页面
			return "couponCode";
		} catch (Exception ex) {
			logger.error("CouponAction.UpdateCouponCodeByState",ex);
			return "error";
		}

	}

	/**
	 * 更新优惠券状态 多个
	 * 
	 * @return
	 */
	public String UpdateCouponCodeByStateMore() {
		try {
			if (ids.length > 0) {
				for (int id : ids) {
					Coupon mycoupon = financeCouponService.getCouponById(id);
					mycoupon.setState(4);
					financeCouponService.updateCoupon(mycoupon);
				}
			}
			getCouponCodeList();// 调取查询页面
			return "couponCode";
		} catch (Exception ex) {
			logger.error("CouponAction.UpdateCouponCodeByStateMore",ex);
			return "error";
		}

	}

	/**
	 * 多条件查询优惠券
	 * 
	 * @return
	 */
	public String showCouponCodeListByWhere() {
		try {
			queryCouponCodeConditionExport=getQueryCouponCodeCondition();
			getQueryCouponCodeCondition().setPageSize(20);
			setPage(financeCouponService
					.getCouponListByWhere(getQueryCouponCodeCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
			return "couponCode";
		} catch (Exception e) {
			logger.error("CouponAction.showCouponCodeListByWhere",e);
			return "error";
		}
	}

	/**
	 * 导出csv
	 * 
	 * @return
	 */
	public String exportCSV() {
		try {
			String expName="优惠券列表_" + DateUtil.getCurrentDate() + ".xls";
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
				 setExportName(URLEncoder.encode(expName, "UTF-8"));//IE浏览器
			 }else{
				 setExportName(new String((expName).getBytes(),"iso-8859-1"));
			 }
			if(queryCouponCodeConditionExport==null){
				queryCouponCodeConditionExport=new QueryCouponCondition();
				queryCouponCodeConditionExport.setCType(0);
				queryCouponCodeConditionExport.setCurrentPage(1);
				queryCouponCodeConditionExport.setKeyword("");
				queryCouponCodeConditionExport.setSearchType(0);
				queryCouponCodeConditionExport.setState(0);
			}
			queryCouponCodeConditionExport.setPageSize(5000);
			PageResult result = financeCouponService
					.getCouponListByWhere(queryCouponCodeConditionExport);
			List<Coupon> couponlist = result.getPageResult();
			// 优惠券类型、优惠券名称、优惠编码、合作商、使用限额（优惠前）、适用范围、使用开始时间、使用截止时间、生成时间、优惠券状态、支付状态//
			List<List<String>> list = new ArrayList<List<String>>();
			List<String> headList = new ArrayList<String>();
			headList.add("ID");
			headList.add("优惠券类型");
			headList.add("优惠券名称");
			headList.add("优惠编码");
			headList.add("合作商");
			headList.add("使用限额");
			headList.add("适用范围");
			headList.add("使用开始时间");
			headList.add("使用截止时间");
			headList.add("生成时间");
			headList.add("优惠券状态");
			headList.add("支付状态");
			list.add(headList);
			// 头结束

			for (int i = 0; i < couponlist.size(); i++) {
				Coupon coupon = couponlist.get(i);
				List<String> small = new ArrayList<String>();
				small.add(coupon.getId().toString());

				if (coupon.getCouponType().getCType() == 1)
					small.add("折扣券");
				if (coupon.getCouponType().getCType() == 2)
					small.add("定额券");
				small.add(coupon.getCouponType().getTitle());
				small.add(coupon.getTitle());

				small.add(coupon.getCouponType().getCompany());
				small.add(coupon.getCouponType().getLeastPrice().toString());
				if (coupon.getCouponType().getSubjectId().equals("0"))
					small.add("所有项目");
				else
					small.add(coupon.getCouponType().getSubjectName());
				small.add(DateUtil.formatDate(coupon.getCouponType()
						.getUseTime(), "yyyy-MM-dd HH:mm:ss"));
				small.add(DateUtil.formatDate(coupon.getCouponType()
						.getStopTime(), "yyyy-MM-dd HH:mm:ss"));
				small.add(DateUtil.formatDate(coupon.getCreateTime(),
						"yyyy-MM-dd HH:mm:ss"));

				if (coupon.getState() == 1)
					small.add("未使用");
				if (coupon.getState() == 2)
					small.add("已使用");
				if (coupon.getState() == 3)
					small.add("过期");
				if (coupon.getState() == 4)
					small.add("作废");

				if (coupon.getPaystate() == 1)
					small.add("未支付");
				if (coupon.getPaystate() == 2)
					small.add("已支付");

				list.add(small);
			}
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			return "exportcoupon";
		} catch (Exception ex) {
			logger.error("CouponAction.exportCSV",ex);
			return "error";
		}

	}

	/**
	 * 导出优惠券编码
	 * 
	 * @return
	 */
	public String exportCouponCode() {
		try {
     
			List<Coupon> couponlist = financeCouponService
					.getCouponListAll(couponTypeId);			
			String expName="优惠券_"+ couponlist.get(0).getCouponType().getCompany()+ DateUtil.getCurrentDate() + ".xls";
			 if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
				 setExportName(URLEncoder.encode(expName, "UTF-8"));//IE浏览器
			 }else{
				 setExportName(new String((expName).getBytes(),"iso-8859-1"));
			 }
			// 优惠券类型、优惠券名称，优惠编码、使用开始时间、使用截止时间、使用限额
			List<List<String>> list = new ArrayList<List<String>>();
			List<String> headList = new ArrayList<String>();
			headList.add("ID");
			headList.add("优惠券类型");
			headList.add("优惠券名称");
			headList.add("优惠编码");
			headList.add("使用开始时间");
			headList.add("使用截止时间");
			headList.add("使用限额");
			list.add(headList);
			// 头结束

			for (int i = 0; i < couponlist.size(); i++) {
				Coupon coupon = couponlist.get(i);
				List<String> small = new ArrayList<String>();
				small.add(coupon.getId().toString());
				if (coupon.getCouponType().getCType() == 1)
					small.add("折扣券");
				if (coupon.getCouponType().getCType() == 2)
					small.add("定额券");
				small.add(coupon.getCouponType().getTitle());
				small.add(coupon.getTitle());
				small.add(DateUtil.formatDate(coupon.getCouponType()
						.getUseTime(), "yyyy-MM-dd HH:mm:ss"));
				small.add(DateUtil.formatDate(coupon.getCouponType()
						.getStopTime(), "yyyy-MM-dd HH:mm:ss"));
				small.add(coupon.getCouponType().getLeastPrice().toString());
				list.add(small);
			}
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			return "exportcoupon";
		} catch (Exception ex) {
			logger.error("CouponAction.exportCouponCode",ex);
			return ERROR;
		}
	}

	public String getCouponByCode() {
		try {
			Coupon coupon = financeCouponService.GetCouponByCode(couponCode);
			setResult(new Result<Coupon>(true, "返回成功!", null, coupon));
			return "json";
		} catch (Exception ex) {
			logger.error("CouponAction.getCouponByCode",ex);
			return ERROR;
		}
	}

	public void couponImgUpload() {
		try {
			ActionContext ctx = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest) ctx
					.get(ServletActionContext.HTTP_REQUEST);
			HttpServletResponse response = (HttpServletResponse) ctx
					.get(ServletActionContext.HTTP_RESPONSE);
			String extName = ""; // 保存文件拓展名
			String newFileName = ""; // 保存新的文件名
			String nowTimeStr = ""; // 保存当前时间
			SimpleDateFormat sDateFormat;
			Random r = new Random();
			String savePath = request.getRealPath(""); // 获取项目根路径
			savePath = savePath + "\\pic\\secondhand\\"; /* 拼串组成要上传保存文件的路径 */
			response.setCharacterEncoding("utf-8"); // 务必，防止返回文件名是乱码
			// 生成随机文件名：当前年月日时分秒+五位随机数（为了在实际项目中防止文件同名而进行的处理）
			int rannum = (int) (r.nextDouble() * (99999 - 10000 + 1)) + 10000; // 获取随机数
			sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); // 时间格式化的格式
			nowTimeStr = sDateFormat.format(new Date()); // 当前时间
			// 获取拓展名
			if (fileuploadFileName.lastIndexOf(".") >= 0) {
				extName = fileuploadFileName.substring(fileuploadFileName
						.lastIndexOf("."));
			}
			String picName = nowTimeStr + rannum + extName;
			newFileName = savePath + picName; // 文件重命名后的名字
			File newfile = new File(savePath);
			if (!newfile.isDirectory())
				newfile.mkdirs();
			fileupload.renameTo(new File(newFileName)); // 保存文件
			response.getWriter().write(picName);// 向页面端返回结果信息
		} catch (Exception ex) {
			logger.error("CouponAction.couponImgUpload",ex);
		}

	}

	/**
	 * 优惠编码详细
	 * 
	 * @return
	 */
	public String couponCodeXX() {
		try {
			coupon = financeCouponService.getCouponById(couponId);
			//contract = financeCouponService.getFinanceByCouponId(coupon.getId());
			//if (contract != null) {
			//	sellWayNames = financeCouponService
						//.GetSellWayNameByContractId(contract.getContractId());
			//}
			return "codexx";
		} catch (Exception ex) {
			logger.error("CouponAction.couponCodeXX",ex);
			return ERROR;
		}
	}
	
	/**
	 * 查看一个优惠券 return String;
	 */
	public String selectSingleConpon() {
		try {
			int couponId = Integer.parseInt(servletRequest
					.getParameter("couponId").toString().toString());
			coupontype = new Coupontype();
			cooperation = new Cooperation();
			coupontype = financeCouponService.getSingleCoupon(couponId);
			cooperation = financeCouponService.selectSingleMen(getCoupontype()
					.getCooperativeId());
			subject = new Subject();
			if (coupontype.getSubjectId().equals("0")) {
				subject.setSubjectId(0);
				subject.setSubjectName("适用全部课程");
			} else {
				int subjectIdd = Integer.parseInt(coupontype.getSubjectId());
				subject = financeCouponService.getsingleSubjectName(subjectIdd);
			}
			chuaCouponType = 1;
			coupontype = financeCouponService.getSingleCoupon(couponId);
			couponList = financeCouponService
					.getcouponSinge(coupontype.getId());

		} catch (Exception e) {
			logger.error("CouponAction.selectSingleConpon",e);
			return ERROR;
		}
		return "addCreateCoupon";
	}
	
	/**
	 * 优惠券过期定时器 每天早上1点执行
	 */
	public void couponGQ(){
		try{
			financeCouponService.updateGQCoupon();
		}catch(Exception ex){
			logger.error("CouponAction.couponGQ",ex);
		}
	}

	public String sendMobileForCoupon(){
		try{
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
		}catch(Exception e){
			logger.error("CouponAction.sendMobileForCoupon",e);
		}
		return "couponSMS";
	}
	
	public String returnCusMobile(){
		try{
	      List<String> list =financeCouponService.GetCusMObileForSubjectId(subject.getSubjectId());
	      this.setResult(new Result(true, null, null, list));
	      return "json";
		}catch(Exception e){
			logger.error("CouponAction.returnCusMobile",e);
			this.setResult(new Result(false, "error", null, null));
			return null;
		}
	}
	
	public String returnCouponForCusById() {
		try{
		List<Coupon> list=financeCouponService.getcouponSinge(coupon.getParentId());
		this.setResult(new Result(true, null, null, list));
		 return "json";
		}catch(Exception e){
			logger.error("CouponAction.returnCouponForCusById",e);
			this.setResult(new Result(false, "error", null, null));
			return null;
		}
	}
	
	/**
	 * 给学员发送优惠券
	 * @return
	 */
	public String sendSMSForCoupon() throws Exception{
		try {
			String content=ServletActionContext.getRequest().getParameter("content");
			String aa = sendLinks.replaceAll("\r", "").replaceAll("\n", "");
			String[] tels = aa.split(",");// 定义一个数组		
           //解析优惠券码
			aa = couponCodes.replaceAll("\r", "").replaceAll("\n", "");
			String codes[] = aa.split(",");// 定义一个数组		
			sendSMS(tels,codes,0,content);//发送短信
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			couponCode="短信发送成功";
			couponRecordInfo =new CouponRecordInfo();
			couponRecordInfo.setTitle(content);
			couponRecordInfo.setAddTime(new Date());
			User user=(User)this.getSession("sedu_user");
			couponRecordInfo.setAddName(user.getUserName());
			financeCouponService.addCouponRecordInfo(getCouponRecordInfo());
			return "couponSMS";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("CouponAction.sendSMSForCoupon",e);
			return "error";
		}
	
	}
	/**
	 * 发送短信方法
	 * @param tel
	 * @param codes
	 * @param num
	 * @param content
	 * @throws InterruptedException
	 */
	public void sendSMS(String tel[],String codes[],int num,String content) throws InterruptedException{
		for (int i = num; i < tel.length; i++) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(tel[i].toString().trim());
			if (!isNum.matches()) {
				logger.info("第"+(i+1)+"个电话号码为"+tel[i]+"编码为"+tel[i]+"为非法手机号码不予处理");
			} else {
				if (tel[i].trim().length() == 11) {
					try{
					Thread.sleep(100);
					String mytel=tel[i].toString().trim();					
				    smsService.sendEx(content+codes[i].trim(),mytel, "", "", "");
				    logger.info("第"+(i+1)+"个电话号码为"+tel[i]+"编码为"+tel[i]+"发送成功");					
					}catch(Exception e){
						logger.error("第"+(i+1)+"个错误电话号码为"+tel[i]+"错误编码为"+tel[i]+e.toString());
						Thread.sleep(500);
						sendSMS(tel,codes,i,content);
						break;
					}
				} 
			}
		}	
	}
	public String getSendSMSList(){
		try {
			getQueryCouponCodeCondition().setPageSize(20);
			setPage(financeCouponService
					.getCouponRecordInfo(getQueryCouponCodeCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
			return "getSendSMSList";
		} catch (Exception ex) {
			logger.error("CouponAction.getSendSMSList",ex);
			return "error";
		}
	}
	// 李明开始

	/**
	 * 输入合作商编码进行校验
	 */
	public String checkConponCoding() {
		try {
			cooperation = new Cooperation();
			String companyCooe = servletRequest.getParameter("companyCooe");
			cooperation = financeCouponService.checkConponCoding(companyCooe);
			System.out.println(companyCooe);
			if (cooperation == null) {
				setResult(new Result(false, "1", null, null));
			} else {
				setResult(new Result(false, "你输入的合作商编码" + companyCooe
						+ "已经添加，请重新输入！", null, null));
			}
			// mid=String.valueOf(id);

		} catch (Exception e) {
			logger.error("CouponAction.checkConponCoding",e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * 输入合作商名字进行校验
	 */
	public String checkConponName() {
		try {
			cooperation = new Cooperation();
			String company = servletRequest.getParameter("company");

			cooperation.setCompanyName(company);
			cooperation = financeCouponService.checkConponName(company);
			System.out.println(company);
			if (cooperation == null) {
				setResult(new Result(false, "", null, null));
			} else if (cooperation.getCompanyName().equals(company)) {
				setResult(new Result(false, "你输入的合作商名字" + company
						+ "已经添加，请重新输入！", null, null));
			}

		} catch (Exception e) {
			logger.error("CouponAction.checkConponName",e);
			return ERROR;
		}
		return "json";
	}


	/**
	 * 修改updateCouponType return
	 */
	public String updateCouponType() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String shibiedanxuan = servletRequest.getParameter("shibiedanxuan")
					.toString();
			if (coupontype.getSubjectId().equals("1")) {
				coupontype.setSubjectId(shibiedanxuan);
			}
			coupontype.setCInfo("未使用");
			coupontype.setcToscore(0);
			String preferentialPrice = coupontype.getPreferentialPrice();
			String[] preferentialPricee = preferentialPrice.split(",");
			coupontype.setPreferentialPrice(preferentialPricee[0]);
			coupontype.setUseSum(0);
			coupontype.setState(1);
			coupontype.setPaySum(0);
			coupontype.setCreateSum(coupontype.getcNum());
			coupontype.setState(1);
			User user=this.getSession("sedu_user");
			coupontype.setOperatingName(user.getUserName());
			financeCouponService.UpdateCouponType(getCoupontype());
			financeCouponService.getSingleCoupon(getCoupontype().getId());
			subject = new Subject();
			if (coupontype.getSubjectId().equals("0")) {
				subject.setSubjectId(0);
				subject.setSubjectName("适用全部课程");
			} else {
				int subjectIdd = Integer.parseInt(coupontype.getSubjectId());
				subject = financeCouponService.getsingleSubjectName(subjectIdd);
			}
			cooperation = financeCouponService.selectSingleMen(getCoupontype()
					.getCooperativeId());
			coupontype.setStringCreateTime(sdf.format(coupontype.getUseTime()));
			coupontype.setStringstopTime(sdf.format(coupontype.getStopTime()));
		} catch (Exception e) {
			logger.error("CouponAction.updateCouponType",e);
			return ERROR;
		}
		return "addCreateCoupon";
	}

	/**
	 * 点击修改返回修加页面 return
	 */
	public String addCreateCouponXiuGai() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			subjectList = subjectService.getAllSubject();
			subject = new Subject();
			subject.setSubjectId(0);
			subject.setSubjectName("--请选择--");
			subjectList.add(subject);
			// 查询所有合作人
			cooperationList = financeCouponService.selectCollaborateMen();
			coupontype = new Coupontype();
			int codingId = Integer.parseInt(servletRequest.getParameter(
					"coupontypeid").toString());
			coupontype = financeCouponService.getSingleCoupon(codingId);
			coupontype.setStringCreateTime(sdf.format(coupontype.getUseTime()));
			coupontype.setStringstopTime(sdf.format(coupontype.getStopTime()));
		} catch (Exception e) {
			logger.error("CouponAction.addCreateCouponXiuGai",e);
			return ERROR;
		}
		return "xiugai";
	}
	/**
	 * 生成编码
	 */
	public String createcoding(){
		try{
		//List<>  codingList=new ArrayList();
		int codingId =Integer.parseInt(servletRequest.getParameter("codingId").toString());
		int useSum =Integer.parseInt(servletRequest.getParameter("useSum").toString());
		int cooperationid =Integer.parseInt(servletRequest.getParameter("cooperationid").toString());
		//检验是否生成；
		cooperation = financeCouponService.selectSingleMen(cooperationid);
		Coupon	coupon=new Coupon();
		coupon.setParentId(codingId);
		coupon.setPicPath("1.jpg");
		coupon.setPaystate(1);
		coupon.setState(1);
		coupon.setCreateTime(new Date());
		coupon.setState(1);
		coupon.setPaystate(1);
		User user=this.getSession("sedu_user");
		coupon.setOperatingName(user.getUserName());
		String str="";
		int strr=0;
		for (int j = 0; j <useSum; j++) {
			long time = System.currentTimeMillis();
			String s = (new Long(time)).toString();
			if(j==0){
				str= s.substring(s.length() - 11);
				coupon.setTitle(str);
			}else{
				int aa=11-str.length();
				String subS=s.substring(s.length() -aa);
				str=subS+str.trim();
				coupon.setTitle(str);
				coupon.setId(null);
			}
			strr=getFinanceCouponService().addCoupon(coupon);
			str=strr+1+"";
	 	}
		List<Coupon> couponList=financeCouponService.getcouponSinge(codingId);
		coupontype =new Coupontype();
		coupontype.setId(coupon.getParentId());
		coupontype.setGenerateTime(new Date());
		financeCouponService.updateCouponTypeGenerateTime(getCoupontype());
		setResult(new Result(false,null,null,couponList));
			
		}catch(Exception e){
			logger.error("CouponAction.createcoding",e);
			return ERROR;
		}
		return "json";
	}

	

	/**
	 * 添加合作商 return josn
	 */
	public String createConperation() {
		try {
			int id = 0;
			String mid = "";
			cooperation = new Cooperation();
			String company = servletRequest.getParameter("company");

			String companyCooe = servletRequest.getParameter("companyCooe");
			cooperation.setCompanyName(company);
			cooperation.setCompanyCode(companyCooe);
			id = financeCouponService.addConperation(getCooperation());
			mid = String.valueOf(id);
			setResult(new Result(false, company, mid, null));

		} catch (Exception e) {
			logger.error("CouponAction.createConperation",e);
			return ERROR;
		}
		return "json";
	}

	

	/**
	 * 添加一个优惠券 return String;
	 */
	public String addCreateCoupon() {
		try {
			String shibiedanxuan = servletRequest.getParameter("shibiedanxuan")
					.toString();
			System.out.println(coupontype.getSubjectId());
			if (coupontype.getSubjectId().equals("1")) {
				coupontype.setSubjectId(shibiedanxuan);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 根据合作人ID插叙所对应的。。。
			cooperation = new Cooperation();
			cooperation = financeCouponService.selectSingleMen(getCoupontype()
					.getCooperativeId());
			System.out.println(coupontype.getSubjectId());
			subject = new Subject();
			if (coupontype.getSubjectId().equals("0")) {
				subject.setSubjectId(0);
				subject.setSubjectName("适用全部课程");
			} else {
				int subjectIdd = Integer.parseInt(coupontype.getSubjectId());
				subject = financeCouponService.getsingleSubjectName(subjectIdd);
			}
			// 添加优惠券
			coupontype.setCInfo("未使用");
			coupontype.setUseSum(1);
			System.out.println(coupontype.getcToscore());
			if (coupontype.getcToscore() == null) {
				coupontype.setcToscore(0);
			}
			String preferentialPrice = coupontype.getPreferentialPrice();
			String[] preferentialPricee = preferentialPrice.split(",");
			coupontype.setPreferentialPrice(preferentialPricee[0]);
			coupontype.setState(1);
			coupontype.setPaySum(0);
			coupontype.setUseSum(0);
			coupontype.setCreateSum(0);
			User user=this.getSession("sedu_user");
			coupontype.setOperatingName(user.getUserName());
			coupontype.setCreateSum(coupontype.getcNum());
			coupontype.setCreateTime(new Date());
			int couponId = financeCouponService
					.addCreateCoupon(getCoupontype());
			coupontype = financeCouponService.getSingleCoupon(couponId);
			coupontype.setStringCreateTime(sdf.format(coupontype.getUseTime()));
			coupontype.setStringstopTime(sdf.format(coupontype.getStopTime()));

		} catch (Exception e) {
			logger.error("CouponAction.addCreateCoupon",e);
			return ERROR;
		}
		return "addCreateCoupon";
	}

	/**
	 * 跳转到新建conpon_new.jsp页面， 进行新建优惠券
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public String treadCouponNewPage() {
		try {
			subjectList = subjectService.getAllSubject();
			subject = new Subject();
			// 查询所有合作人
			cooperationList = financeCouponService.selectCollaborateMen();
			courseSortList = coursesortService
					.getChildCoursesortList(new QueryCoursesortCondition());
		} catch (Exception e) {
			logger.error("CouponAction.treadCouponNewPage",e);
			return ERROR;
		}
		return "treadSuccess";
	}

	// 李明结束

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public QueryCouponCondition getQueryCouponCodeCondition() {
		return queryCouponCodeCondition;
	}

	public void setQueryCouponCodeCondition(
			QueryCouponCondition queryCouponCodeCondition) {
		this.queryCouponCodeCondition = queryCouponCodeCondition;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
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

	public void setFinanceCouponService(ICoupon financeCouponService) {
		this.financeCouponService = financeCouponService;
	}

	public void setFinanceCoursesortService(ICoursesort financeCoursesortService) {
		this.financeCoursesortService = financeCoursesortService;
	}

	public QueryCouponTypeCondition getQueryCouponTypeCondition() {
		return queryCouponTypeCondition;
	}

	public void setQueryCouponTypeCondition(
			QueryCouponTypeCondition queryCouponTypeCondition) {
		this.queryCouponTypeCondition = queryCouponTypeCondition;
	}

	public Coupontype getCouponType() {
		return couponType;
	}

	public void setCouponType(Coupontype couponType) {
		this.couponType = couponType;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Coupontype getCoupontype() {
		return coupontype;
	}

	public void setCoupontype(Coupontype coupontype) {
		this.coupontype = coupontype;
	}

	public Cooperation getCooperation() {
		return cooperation;
	}

	public void setCooperation(Cooperation cooperation) {
		this.cooperation = cooperation;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<Cooperation> getCooperationList() {
		return cooperationList;
	}

	public void setCooperationList(List<Cooperation> cooperationList) {
		this.cooperationList = cooperationList;
	}

	public ICoupon getFinanceCouponService() {
		return financeCouponService;
	}

	public ICoursesort getFinanceCoursesortService() {
		return financeCoursesortService;
	}

	public File getFileupload() {
		return fileupload;
	}

	public void setFileupload(File fileupload) {
		this.fileupload = fileupload;
	}

	public String getFileuploadFileName() {
		return fileuploadFileName;
	}

	public void setFileuploadFileName(String fileuploadFileName) {
		this.fileuploadFileName = fileuploadFileName;
	}

	public int getCouponTypeId() {
		return couponTypeId;
	}

	public void setCouponTypeId(int couponTypeId) {
		this.couponTypeId = couponTypeId;
	}

	public int getCouponId() {
		return couponId;
	}

	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public List<String> getSellWayNames() {
		return sellWayNames;
	}

	public void setSellWayNames(List<String> sellWayNames) {
		this.sellWayNames = sellWayNames;
	}

	public int getChuaCouponType() {
		return chuaCouponType;
	}

	public void setChuaCouponType(int chuaCouponType) {
		this.chuaCouponType = chuaCouponType;
	}

	public String getSendLinks() {
		return sendLinks;
	}

	public void setSendLinks(String sendLinks) {
		this.sendLinks = sendLinks;
	}

	public String getCouponCodes() {
		return couponCodes;
	}

	public void setCouponCodes(String couponCodes) {
		this.couponCodes = couponCodes;
	}

	public IsmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}

	public CouponRecordInfo getCouponRecordInfo() {
		return couponRecordInfo;
	}

	public void setCouponRecordInfo(CouponRecordInfo couponRecordInfo) {
		this.couponRecordInfo = couponRecordInfo;
	}

}
