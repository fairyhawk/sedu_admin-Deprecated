package com.shangde.edu.crm.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.crm.condition.QueryChanceCondition;
import com.shangde.edu.crm.condition.QueryStatCondition;
import com.shangde.edu.crm.domain.Chance;
import com.shangde.edu.crm.domain.UserDTO;
import com.shangde.edu.crm.dto.ChanceRecordDTO;
import com.shangde.edu.crm.dto.ContractCrmDTO;
import com.shangde.edu.crm.dto.SalesStatDTO;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;


/**
 * 只读的查询类。增加删除等方法禁止在写在此类中!!
 * User: guoqiang.liu
 * Date: 2011-11-03
 */
@SuppressWarnings("unchecked")
public class ChanceReadImpl extends BaseServiceManyDb implements IChanceRead{

    public Chance getChanceById(int id) {
        return simpleDaoRead.getEntity("Chance_NS.getChanceById",id);
    }
    /**
     * fanxin  2011-11-06
     * 根据crmUserId获取单个Chance对象
     * @param crmUserId 
     * @return 
     */
    public Chance getChanceByCrmUserId(int crmUserId){
    	
    	 return simpleDaoRead.getEntity("Chance_NS.getChanceByCrmUserId",crmUserId);
    }
    
   
    public List<Subject> getAllSubject() {
		return simpleDaoRead.getForList("Subject_NS.getAllSubject",null);
	}
    
    /**
     * 获取全部销售机会列表
     * @author 代长福
     * @return
     */
    public PageResult getAllChance(PageQuery pageQuery){
    	return simpleDaoRead.getPageResult("Chance_NS.getAllChance", "Chance_NS.getAllChanceCount", pageQuery);
    }
    
    /**
     * 获取机会表里该手机号对应记录数量
     * @param mobile
     * @return
     */
    public List<UserDTO> getChanceCountByMobile(String mobile){
    	return simpleDaoRead.getForList("Chance_NS.getChanceByMobile", mobile);
    }
    
    /**
     * 搜索销售机会
     * @author 代长福
     * @param queryChanceCondition
     * @return
     */
    public PageResult searchChance(QueryChanceCondition queryChanceCondition){
    	return simpleDaoRead.getPageResult("Chance_NS.searchChance", "Chance_NS.searchChanceCount", queryChanceCondition);
    }
    
    /**
     * 根据销售机会Id获取ChanceRecordDTO对象
     * @param crmChanceId
     * @return
     */
    public ChanceRecordDTO getChanceRecordDTO(int crmChanceId){
    	return simpleDaoRead.getEntity("Chance_NS.getChanceRecordDTO", crmChanceId);
    }
    
    /**
     *  @author 王超
     *  获取所有用户列表
     * @return
     */
    public List<User> getSoldUserList(QueryChanceCondition queryChanceCondition){
    	return simpleDaoRead.getForList("Sellrecord_NS.getSoldUserList", null);
    }
    
    /**
     * 根据groupId获取用户列表
     * @param groupId
     * @return
     */
    public List<User> getGroupUserInfo(int groupId){
    	return simpleDaoRead.getForList("User_NS.getUserListByGroupId2", groupId) ;
    }
    
    /**
     * 某个销售人员的机会列表
     * @author 代长福
     * @param queryChanceCondition
     * @return
     */
    public PageResult getSalesChanceList(QueryChanceCondition queryChanceCondition){
    	return simpleDaoRead.getPageResult("Chance_NS.getSalesChanceList", "Chance_NS.getSalesChanceListCount", queryChanceCondition);
    }
    
    /**
     * 销售人员搜索销售机会
     * @param queryChanceCondition
     * @return
     */
    public PageResult searchSalesChance(QueryChanceCondition queryChanceCondition){
    	return simpleDaoRead.getPageResult("Chance_NS.searchSalesChance", "Chance_NS.searchSalesChanceCount", queryChanceCondition);
    }
    
    /**
     * 通过groupId获取非自然注册指派销售人员列表
     * @author 王超 
     * @param groupId
     * @return
     */
    public List<UserDTO>getSeatsListByGroupId(int groupId){
    	return simpleDaoRead.getForList("Chance_NS.getSeatsListByGroupId", groupId);
    }
    /**
     * 通过groupId获取自然注册指派销售人员列表
     * @author 王超 
     * @param groupId
     * @return
     */
    public List<UserDTO>getSignSeatsListByGroupId(int groupId){
    	return simpleDaoRead.getForList("Chance_NS.getSignSeatsListByGroupId", groupId);
    }
    /**
     * 获取自然注册指派销售人员列表
     * @author 王超 
     * @return
     */
    public List<UserDTO> getSignSeatsList(){
    	return simpleDaoRead.getForList("Chance_NS.getSignSeatsList", null);
    }
    
    /**
     * 获取自然注册非指派销售人员列表
     * @author 王超 
     * @return
     */
    public List<UserDTO> getUnsignSeatsList(){
    	return simpleDaoRead.getForList("Chance_NS.getUnsignSeatsList", null);
    }
    /**
     * 自定义自然注册销售指派
     * @param map
     */
    public void updateSeatsDefine(Map<String, Object> map){
    	simpleDaoRead.updateEntity("Chance_NS.seatsDefine",map);
    }
    
    /**
     * 清空自然注册销售指派
     * @param groupId
     */
    public void updateSeatsEmpty(int groupId){
    	simpleDaoRead.updateEntity("Chance_NS.seatsEmpty",groupId);
    }
    
	/**
     * 根据CusId获得订单对象集合
     * @author 范昕 2011-11-06
     * @return
     */   
    public List<ContractCrmDTO> getContractByCusId(int cusId){
    	return simpleDaoRead.getForList("Chance_NS.getBackContractByCusId",cusId);
    }
    
    public Integer getContractNum(int cusId){
    	return simpleDaoRead.getEntity("Chance_NS.getContractNum", cusId);
    }
    public Integer getContractNumPay(int cusId){
    	return simpleDaoRead.getEntity("Chance_NS.getContractNumPay", cusId);
    }
    
    /**
     * 查询销售机会库列表
     * @author 代长福
     * @param pageQuery
     * @return
     */
    public PageResult getChanceList(PageQuery pageQuery){
    	return simpleDaoRead.getPageResult("Chance_NS.getChanceList", "Chance_NS.getChanceListCount", pageQuery);
    }
    
    /**
     * 查询销售追呼统计页面
     * @author 代长福
     * @param queryStatCondition
     * @return
     */
    public PageResult getSalesStat(QueryStatCondition queryStatCondition){
    	return simpleDaoRead.getPageResult("Chance_NS.getSalesStatNew", "Chance_NS.getSalesStatNewCount", queryStatCondition);
    }
    
    /**
     * 查询销售追呼统计列表
     * @author 代长福
     * @param queryStatCondition
     * @return
     */
    public List<SalesStatDTO> getSalesStatList(QueryStatCondition queryStatCondition){
    	return simpleDaoRead.getForList("Chance_NS.getSalesStatList", queryStatCondition);
    }
    
    /**
     * 按小时查询销售追呼统计
     * @author 代长福
     * @param queryStatCondition
     * @return
     */
    public List<SalesStatDTO> getSalesStatByHour(QueryStatCondition queryStatCondition){
    	return simpleDaoRead.getForList("Chance_NS.getSalesStatByHourNew", queryStatCondition);
    }
    
    /**
     * 批量领取销售机会
     * @author 代长福
     * @return
     */
    public Integer updategetBatchChance(Map<String, Object> map){
    	return simpleDaoRead.update("Chance_NS.getBatchChance", map);
    }
    
    /**
     * 查询已领取的销售机会数量（除去已购买的销售机会）
     *
     * @param userId
     * @return
     */
    public Integer getDrawChanceCount(int userId){
    	return simpleDaoRead.getEntity("Chance_NS.getDrawChanceCount", userId);
    }

	public List<UserDTO> getSoldUserList() {
		return simpleDaoRead.getForList("Chance_NS.getSoldUserList", null);
	}
    
	/**
     * @author 王超
     * 定时将过期机会转入机会库
     */
    public void updatecheckChance(QueryChanceCondition queryChanceCondition){
    	simpleDaoRead.update("Chance_NS.checkChance", queryChanceCondition);
    }
    
    /**
     * 检查已购买用户
     */
    public void updatecheckIsBuy(){
    	simpleDaoRead.update("Chance_NS.checkIsBuy", null);
    }
    
    /**
     * 获取定时延时时间
     * @author 代长福
     * @return
     */
    public Integer getTiming(){
    	return simpleDaoRead.getEntity("Chance_NS.getTiming", null);
    }
    
    /**
     * 修改定时延迟时间
     * @author 代长福
     * @param timingNum
     * @return
     */
    public Integer updateTiming(int timingNum){
    	return simpleDaoRead.update("Chance_NS.updateTiming", timingNum);
    }
    /**
     * 获取全部销售机会列表导出excel
     * @author 代长福
     * @return
     */
    public PageResult getAllChanceCheckOut(PageQuery pageQuery){
    	return simpleDaoRead.getPageResult("Chance_NS.getAllChanceCheckOut", "Chance_NS.getAllChanceCount", pageQuery);
    }
    
    /**
     * 搜索销售机会导出excel
     * @author 王超
     * @param queryChanceCondition
     * @return
     */
    public PageResult searchChanceCheckOut(QueryChanceCondition queryChanceCondition){
    	return simpleDaoRead.getPageResult("Chance_NS.searchChanceCheckOut", "Chance_NS.searchChanceCount", queryChanceCondition);
    }
    
    /**
     * 查询未指派销售追呼统计
     * @王超
     * @param queryStatCondition
     * @return
     */
    public List<SalesStatDTO> getUndesignedInfo(QueryStatCondition queryStatCondition){
    	return simpleDao.getForList("Chance_NS.getUndesignedInfo", queryStatCondition);
    }
    
    /**
     * 查询未指派销售追呼统计按小时
     * @王超
     * @param queryStatCondition
     * @return
     */
    public List<SalesStatDTO> getUndesignedInfoBuyHour(QueryStatCondition queryStatCondition){
    	return simpleDao.getForList("Chance_NS.getUndesignedInfoBuyHour", queryStatCondition);
    }
}
