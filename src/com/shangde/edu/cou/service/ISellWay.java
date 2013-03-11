package com.shangde.edu.cou.service;

import java.util.List;


import com.shangde.common.vo.PageResult;


import com.shangde.edu.cou.domain.Course;

import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cou.dto.SellWayDTO;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.sys.domain.Subject;
/**
 * ISellWay 售卖方式接口
 * User: guoqiang.liu
 * Date: 2011-03-30
 */
public interface ISellWay {
	/**
	 * 
	 *添加售卖方式
	 *@param sellway
	 *@return id 
	 */
    public java.lang.Integer addSellWay(SellWay sellWay);

    /**
	 * 
	 *删除售卖方式
	 *@param sellId
	 *@return void 
	 */
    public void delSellWayById(int sellId);
    /**
	 * 
	 *更新售卖方式
	 *@param sellWay
	 *@return void 
	 */
    public void updateSellWay(SellWay sellWay);

    /**
	 * 
	 *根据售卖方式sellId查询
	 *@param sellId
	 *@return SellWay 
	 */
    public SellWay getSellWayById(int sellId);

    /**
	 * 
	 *根据售卖方式querySellWayCondition查询
	 *@param querySellWayCondition
	 *@return SellWay集合 
	 */
    public List<SellWay> getSellWayList(QuerySellWayCondition querySellWayCondition);
    /**
     * 获得售卖方式信息，包括专业名
     * @param querySellWayCondition
     * @return
     */
    public PageResult getSellWayInfoList(QuerySellWayCondition querySellWayCondition);
    
	public String  getContractForCouponIdById(String contractId);
 
    public void updateCouponForPayState(String Id,int couponTypeId);
    
    public Integer GetParentIdBycouponId(String couponId);

    /**
     * 根据项目ID查询所对应的老师
     */
	public List<Teacher> getTeacherByName(int subjectId);
  /**
   * 多条件查询
   * @param querySellWayCondition
   * @return
   */
	public PageResult showSellWayListByWhere(
			QuerySellWayCondition querySellWayCondition);

    
    /**
  	 * 根据商品id获取课程
  	 * @param sellId
  	 * @return
  	 */
  	public List<Course> getCourseBySellId(int sellId);
  	
  	/**
  	 * 根据专业id 得到当前专业下的所有售卖方式
  	 * @param subjectId
  	 * @return
  	 */
  	public List<SellWay> getSellWayBySubjectId(int subjectId);
  	
	/**
  	 * 更改商品上架状态
  	 * @param sellId
  	 */
  	public void updatesShopState(int sellId);
  	
  	/**
  	 * 销售记录财务表
  	 * @param querySellWayCondition
  	 * @return
  	 */
	public PageResult showSellWayListRecord(QuerySellWayCondition querySellWayCondition);
	/**
	 * 获取课程卡所属的商品信息
	 * @param cardCourseId
	 * @return
	 * @throws Exception
	 */
	List<SellWay> getCardCourseSell(Integer cardMainId) throws Exception;

}