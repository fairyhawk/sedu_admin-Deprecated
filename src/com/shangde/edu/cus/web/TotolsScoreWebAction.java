package com.shangde.edu.cus.web;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.shangde.common.action.CommonAction;
import com.shangde.common.intercepter.LimitIntercepterForWeb;
import com.shangde.common.util.CookieHandler;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCouponCondition;
import com.shangde.edu.cou.domain.Coupon;
import com.shangde.edu.cou.domain.Uncoupon;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.service.ICoupon;
import com.shangde.edu.cou.service.IUncoupon;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryTotolsScoreCondition;
import com.shangde.edu.cus.condition.QueryTsRecordCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.Experience;
import com.shangde.edu.cus.domain.TotolsScore;
import com.shangde.edu.cus.domain.TsRecord;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.ITotolsScore;
import com.shangde.edu.cus.service.ITsRecord;


/**
 * 
 * @author miaoshusen
 * @version 1.0
 */

public class TotolsScoreWebAction extends CommonAction {
	
	/**
	 * 积分的服务
	 */
	private ITotolsScore totolsScoreService;
	/**
	 * 查询条件
	 */
	private QueryTotolsScoreCondition queryTotolsScoreCondition;
	/**
	 * 前台学员
	 */
	private ICustomer customerService;
	private QueryCustomerCondition queryCustomerCondition;
	private List<Customer> customerList=new ArrayList<Customer>();
	
	/**
	 * 积分的实体对象
	 */
	private TotolsScore totolsScore;
	/**
	 * 积分记录的服务
	 */
	private ITsRecord tsRecordService;
	/**
	 * 查询条件
	 */
	private QueryTsRecordCondition queryTsRecordCondition;
	/**
	 * 优惠券实体对象
	 */
	private Coupon coupon=new Coupon();
	/**
	 * 活动服务对象
	 */
	private ICoupon couponService;
	/**
	 * 活动查询条件
	 */
	private QueryCouponCondition queryCouponCondition;
	/**
	 * 积分记录表服务
	 */
	private IUncoupon uncouponService;
	
	/**
	 * 获得积分列表的页面
	 * 
	 * @return
	 */
	public String forCoupon() {
		String PageResult="";
		try {
			String userIdStr = CookieHandler.getCookieValueByName(servletRequest, LimitIntercepterForWeb.COOKIE_REMEMBERME_KEY);
			int userId = 0;
			if(userIdStr != null){
				userId = Integer.parseInt(userIdStr.split(",")[0]);
			}
			TotolsScore totolsScore=null;
			if(userId!=0){
				totolsScore=this.totolsScoreService.getTotolsScore(userId);
			}
			Coupon newCoupon=null;
			if(coupon.getId()!=0){
				 newCoupon=this.couponService.getCouponById(coupon.getId());
			}
			//如果个人积分够兑换优惠券
			
			if(totolsScore.getTsCurrent()>newCoupon.getToScore()){
				int withTotolsScore=totolsScore.getTsCurrent()-newCoupon.getToScore();
				totolsScore.setTsCurrent(withTotolsScore);
				this.totolsScoreService.updateTotolsScore(totolsScore);
				
				//积分记录表进行记录  消耗积分
				TsRecord tsRecord=new TsRecord();
				tsRecord.setCusId(userId);
				tsRecord.setTrType(TsRecord.TRTYPE_FOR);
				
				Date date=new Date();
				int toScore=newCoupon.getToScore();
				if(toScore==1000){
					tsRecord.setUseType(TsRecord.USETYPE_COUPON10);
				}
				if(toScore==5000){
					tsRecord.setUseType(TsRecord.USETYPE_COUPON20);
				}
				if(toScore==12000){
					tsRecord.setUseType(TsRecord.USETYPE_COUPON30);
				}
//				tsRecord.setAccessType(00);
//				tsRecord.setAccessTime(date);
				tsRecord.setUseTime(date);
				tsRecord.setTsId(totolsScore.getTsId());
				tsRecord.setTrNum(newCoupon.getToScore());
				this.tsRecordService.addTsRecord(tsRecord);
				
				//优惠券记录表 添加一条记录兑换的优惠券没有激活　
				String serialNumber = DateUtil.getCouponTime() +  + new java.util.Random().nextInt(100000);
				Uncoupon uncoupon = new Uncoupon();
				uncoupon.setSerialNumber(serialNumber);
				uncoupon.setId(coupon.getId());
				uncoupon.setStatus(Uncoupon.UNCP_STATUS_USABLE);
				
				uncouponService.addUncoupon(uncoupon);
				
				this.setResult(new Result<String>(true,"true",null,serialNumber));
				//PageResult="json";
			}else{
				System.out.println("您的积分还不够兑换！");
				//PageResult="fail";
				this.setResult(new Result<String>(true,"false",null,null));
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	}
	
	
	public String getExperienceLevel(){
		try {
			int userId=this.getLoginUserId();
			TotolsScore tScore=new TotolsScore();
			tScore=this.totolsScoreService.getTotolsScore(userId);
			Experience exp=new Experience();
			if(tScore!=null){
			int expvalue=tScore.getTsAction();
			exp.setExpValue(expvalue);
			if(expvalue<60){
				exp.setExpLevel(1);
				exp.setExpName("新手上路");
				exp.setNextExpValue(60);
			}
			if(expvalue>=60&&expvalue<75){
				exp.setExpLevel(2);
				exp.setExpName("手艺学徒");
				exp.setNextExpValue(75);
			}
			if(expvalue>=75&&expvalue<100){
				exp.setExpLevel(3);
				exp.setExpName("初级学员");
				exp.setNextExpValue(100);
			}
			if(expvalue>=100&&expvalue<150){
				exp.setExpLevel(4);
				exp.setExpName("初级学员");
				exp.setNextExpValue(150);
			}
			if(expvalue>=150&&expvalue<230){
				exp.setExpLevel(5);
				exp.setExpName("中级学员");
				exp.setNextExpValue(230);
			}
			if(expvalue>=230&&expvalue<400){
				exp.setExpLevel(6);
				exp.setExpName("中级学员");
				exp.setNextExpValue(400);
			}
			if(expvalue>=400&&expvalue<800){
				exp.setExpLevel(7);
				exp.setExpName("高级学员");
				exp.setNextExpValue(800);
			}
			if(expvalue>=800&&expvalue<1500){
				exp.setExpLevel(8);
				exp.setExpName("高级学员");
				exp.setNextExpValue(1500);
			}
			if(expvalue>=1500&&expvalue<3500){
				exp.setExpLevel(9);
				exp.setExpName("特级学员");
				exp.setNextExpValue(3500);
			}
			if(expvalue>=3500&&expvalue<7000){
				exp.setExpLevel(10);
				exp.setExpName("明星学员");
				exp.setNextExpValue(7000);
			}
			if(expvalue>=7000&&expvalue<15000){
				exp.setExpLevel(11);
				exp.setExpName("超级偶像");
				exp.setNextExpValue(15000);
			}
			if(expvalue>=15000&&expvalue<25000){
				exp.setExpLevel(12);
				exp.setExpName("资深专家");
				exp.setNextExpValue(25000);
			}
			if(expvalue>=25000){
				exp.setExpLevel(13);
				exp.setExpName("百科全书");
				exp.setNextExpValue(25000);
			}
			}
			JSONArray jslist = JSONArray.fromObject(exp);
			
			this.setResult(new Result<Object>(true, jslist.toString(), null, null));
		
		} catch (Exception ex) {
			ex.printStackTrace();
			return ERROR;
		}
		return "json";
	} 



	public ITotolsScore getTotolsScoreService() {
		return totolsScoreService;
	}


	public void setTotolsScoreService(ITotolsScore totolsScoreService) {
		this.totolsScoreService = totolsScoreService;
	}


	public QueryTotolsScoreCondition getQueryTotolsScoreCondition() {
		if(this.queryTotolsScoreCondition == null){
			this.queryTotolsScoreCondition = new QueryTotolsScoreCondition();
		}
		return queryTotolsScoreCondition;
	}


	public void setQueryTotolsScoreCondition(
			QueryTotolsScoreCondition queryTotolsScoreCondition) {
		this.queryTotolsScoreCondition = queryTotolsScoreCondition;
	}
	

	public ICustomer getCustomerService() {
		return customerService;
	}



	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}



	public QueryCustomerCondition getQueryCustomerCondition() {
		if(this.queryCustomerCondition == null){
			this.queryCustomerCondition = new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}



	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}



	public List<Customer> getCustomerList() {
		return customerList;
	}



	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public TotolsScore getTotolsScore() {
		return totolsScore;
	}

	public void setTotolsScore(TotolsScore totolsScore) {
		this.totolsScore = totolsScore;
	}

	public ITsRecord getTsRecordService() {
		return tsRecordService;
	}

	public void setTsRecordService(ITsRecord tsRecordService) {
		this.tsRecordService = tsRecordService;
	}

	public QueryTsRecordCondition getQueryTsRecordCondition() {
		if(this.queryTsRecordCondition == null){
			this.queryTsRecordCondition = new QueryTsRecordCondition();
		}
		return queryTsRecordCondition;
	}

	public void setQueryTsRecordCondition(
			QueryTsRecordCondition queryTsRecordCondition) {
		this.queryTsRecordCondition = queryTsRecordCondition;
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

	public ICoupon getCouponService() {
		return couponService;
	}
	public void setCouponService(ICoupon couponService) {
		this.couponService = couponService;
	}



	public IUncoupon getUncouponService() {
		return uncouponService;
	}



	public void setUncouponService(IUncoupon uncouponService) {
		this.uncouponService = uncouponService;
	}
	


}
