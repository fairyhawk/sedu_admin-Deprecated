package com.shangde.edu.finance.service;

import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryCouponCondition;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.domain.Cooperation;
import com.shangde.edu.finance.domain.Coupon;
import com.shangde.edu.finance.domain.CouponRecordInfo;
import com.shangde.edu.finance.domain.Coupontype;
import com.shangde.edu.sys.domain.Subject;

/**
 * Coupon管理接口
 * User: guoqiang.liu
 * Date: 2010-08-19
 */
public interface ICoupon {
    /**
     * 添加Coupon
     * @param coupon 要添加的Coupon
     * @return id
     */
    public java.lang.Integer addCoupon(Coupon coupon);

    /**
     * 根据id删除一个Coupon
     * @param id 要删除的id
     */
    public void delCouponById(int id);

    /**
     * 修改Coupon
     * @param coupon 要修改的Coupon
     */
    public void updateCoupon(Coupon coupon);

    /**
     * 根据id获取单个Coupon对象
     * @param id 要查询的id
     * @return 年级
     */
    public Coupon getCouponById(int id);

    /**
     * 根据条件获取Coupon列表
     * @param queryCouponCondition 查询条件
     * @return 查询结果
     */
    public PageResult getCouponList(QueryCouponCondition queryCouponCondition);

    /**
     * 分页查询优惠券
     * @param queryCouponCondition
     * @return 查询结果
     */
	public PageResult getCouponTypeListByCondition(
			PageQuery queryCouponCondition);

	/**
	 * 根据学员id查询优惠券数量
	 *
     * @param userId 学员id
     * @return
	 */
	public Integer getCouponCountByCus(int userId);

	/**
	 * 根据学员id和课程ids查询优惠券list
	 * @param queryCouponCondition 学员id，课程ids
	 * @return
	 */
	public List<Coupon> getCouponListByCusCou(
			QueryCouponCondition queryCouponCondition);

	/**
	 * 根据用户id查询优惠券列表
	 * @param queryCouponCondition
	 * @return
	 */
	public PageResult getCouponListByCusId(QueryCouponCondition queryCouponCondition);

    /**
     * 根据优惠金额获取优惠券
     * @param price 金额
     * @return
     */
    public Coupon getCouponByPrice(int price);

    /**
     * 为静态化获取优惠券列表
     * @param queryCouponCondition
     * @return
     */
	public List<Coupon> getCouponListForTag(
			QueryCouponCondition queryCouponCondition);
	
	public PageResult getCouponTypeListByWhere(PageQuery queryCouponCondition) ;
	
	/**
	 * 更新优惠券
	 * @param couponType
	 */
	public void UpdateCouponType(Coupontype couponType);
	
	/**
	 * 获取优惠券ById
	 * @param id
	 * @return
	 */
	public Coupontype GetCouponTypeById(int id);
	
	/**
	 * 查询优惠券
	 * @param queryCouponCondition
	 * @return
	 */
	 public PageResult getCouponListByWhere(QueryCouponCondition queryCouponCondition);
	 
	 /**
	  * 查询优惠券根据优惠券码
	  * @param code
	  * @return
	  */
	 public Coupon GetCouponByCode(String code);
	 
	 /**
	  * 导入全部优惠券根据couponid
	  * @param couponTypeId
	  * @return
	  */
	 public List<Coupon> getCouponListAll(int couponTypeId);
	 
	 /**
	  * 获取订单根据id
	  * @param contractId
	  * @return
	  */
	 public Contract getContractById(int contractId);
	 
	 /**
	  * 查询售卖方式名称
	  * @param contractId
	  * @return
	  */
	 public List<String> GetSellWayNameByContractId(String contractId);
	 
	 /**
	  * 更新过期优惠券
	  */
	 public void updateGQCoupon();
	 
	 /**
	  * 作废父类下的所有优惠券
	  * @param parentId
	  */
	 public void updateZFCouponByParentId(int parentId);
	 
	 /**
	  * 查询项目下的所有支付过的学员
	  * @param subId
	  * @return
	  */
	 public List<String> GetCusMObileForSubjectId(int subId);
	 
	 //李明开始
	 public List<Cooperation> selectCollaborateMen();
		/**
		 * 添加优惠券
		 * @return 
		 */
		public Integer addCreateCoupon(Coupontype coupontype);
		/**
		 * 根据ID查询所对应的优惠券
		 */
		public Coupontype getSingleCoupon(int couponId);
		/**
		 * 添加合作商
         * @param cooperation
         */

		public Integer addConperation(Cooperation cooperation);
		/**
		 * 添加编码
         * @param coupon
         */
		public Integer addCouPon(Coupon coupon);
		/**
		 * 根据type查询所对应的coding
		 * @param codingId
		 */
		public List<Coupon> getcouponSinge(int codingId);
		/**
		 *  修改updateCouponType
		 * return list
		 */
		public void updateCouponType(Coupontype coupontype);
		 /**
	     * 查询单个的合伙人
		 * @param integer 
	     * @param 
	     * @return entity；
	     */
		public Cooperation selectSingleMen(Integer integer);

		public void getSingleCouponn(int conponTypeId);
		/**
		 * 查询单个的项目
		 * @param subjectId
		 */
		public Subject getsingleSubjectName(int subjectId);
		
		
		/**
		 * 输入合作商名字进行校验
		 */
		public Cooperation checkConponName(String company);
		/**
		 * 输入合作商编码进行校验
		 */
		public Cooperation checkConponCoding(String companyCooe);
		/**
		 * 在生成编码的时候，给数据库中的编码生策划给你时间添加时间
		 * 
		 */
		public void updateCouponTypeGenerateTime(Coupontype coupontype);
		/**
		 * 检验是否已近生成了又会变吗；
		 * 
		 */

		public List<Cooperation> checkConponCoding(int codingId);
	 //李明结束
		
		/**
		 * 根据订单号  得到当前订单优惠卷
		 */
		public Coupon getCouponByContractId(int contractId);
		/**
		 * 记录发短信的信息
		 * @param couponRecordInfo
		 * Liming
		 */
		public void addCouponRecordInfo(CouponRecordInfo couponRecordInfo);
		/**
		 * 查询所有的发送短信记录
		 * @param queryCouponCodeCondition
		 * @return PageResult
		 * Liming
		 */
		public PageResult getCouponRecordInfo(
				QueryCouponCondition queryCouponCodeCondition);

		public Contract getFinanceByCouponId(int couponId);
	

	
}