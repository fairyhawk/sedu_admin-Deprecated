package com.shangde.edu.crm.service;

import java.util.List;
import java.util.Map;

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
 * Chance管理接口
 * User: guoqiang.liu
 * Date: 2011-11-03
 */
public interface IChance {
    /**
     * 添加Chance
     * @param chance 要添加的Chance
     * @return id
     */
    public java.lang.Integer addChance(Chance chance);

    /**
     * 根据id删除一个Chance
     * @param id 要删除的id
     */
    public void delChanceById(int id);

    /**
     * 修改Chance
     * @param chance 要修改的Chance
     */
    public void updateChance(Chance chance);

    /**
     * 根据id获取单个Chance对象
     * @param id 要查询的id
     * @return 年级
     */
    public Chance getChanceById(int id);

    /**
     * fanxin  2011-11-06
     * 根据crmUserId获取单个Chance对象
     * @param crmUserId 
     * @return 
     */
    public Chance getChanceByCrmUserId(int crmUserId);    
    
    /**
     * @author 王超
     * 获取专业列表
     * @return
     */
    public List<Subject> getAllSubject();
    
    /**
     * 获取全部销售机会列表
     * @author 代长福
     * @return
     */
    public PageResult getAllChance(PageQuery pageQuery);
    
    /**
     * 获取机会表里该手机号对应记录数量
     * @param mobile
     * @return
     */
    public  List<UserDTO> getChanceCountByMobile(String mobile);
    
    /**
     * 搜索销售机会
     * @author 代长福
     * @param queryChanceCondition
     * @return
     */
    public PageResult searchChance(QueryChanceCondition queryChanceCondition);
    
    /**
     * 获取全部销售机会列表导出excel
     * @author 代长福
     * @return
     */
    public PageResult getAllChanceCheckOut(PageQuery pageQuery);
    
    /**
     * 搜索销售机会导出excel
     * @author 王超
     * @param queryChanceCondition
     * @return
     */
    public PageResult searchChanceCheckOut(QueryChanceCondition queryChanceCondition);
    
    /**
     * 根据销售机会Id获取ChanceRecordDTO对象
     * @param ID
     * @return
     */
    public ChanceRecordDTO getChanceRecordDTO(int crmChanceId);
    
    /**
     *  @author 王超
     *  获取所有用户列表
     * @return
     */
    public List<User> getSoldUserList(QueryChanceCondition queryChanceCondition);
    
    /**
     * 根据groupId获取用户列表
     * @author 王超
     * @param groupId
     * @return
     */
    public List<User> getGroupUserInfo(int groupId);
    /**
     * 通过groupId获取非自然注册指派销售人员列表
     * @author 王超 
     * @param groupId
     * @return
     */
    public List<UserDTO>getSeatsListByGroupId(int groupId);
    /**
     * 通过groupId获取自然注册指派销售人员列表
     * @author 王超 
     * @param groupId
     * @return
     */
    public List<UserDTO>getSignSeatsListByGroupId(int groupId);
    /**
     * 获取自然注册指派销售人员列表
     * @author 王超 
     * @param groupId
     * @return
     */
    public List<UserDTO> getSignSeatsList();
    /**
     * 获取自然注册非指派销售人员列表
     * @author 王超 
     * @param groupId
     * @return
     */
    public List<UserDTO> getUnsignSeatsList();
    /**
     * 快捷筛选获取自然注册
     * 非指派销售人员列表
     * @author 赵永永
     * @return
     */
    public List<UserDTO> getSearchUnsignSeats(String keyword);
    /**
     * 获取销售人员列表
     * @author 王超 
     * @param groupId
     * @return
     */
    public List<UserDTO>getSoldUserList();
    /**
     * 查询某个销售人员的机会列表
     * @author 代长福
     * @param queryChanceCondition
     * @return
     */
    public PageResult getSalesChanceList(QueryChanceCondition queryChanceCondition);
    
    /**
     * 销售人员搜索销售机会
     * @param queryChanceCondition
     * @return
     */
    public PageResult searchSalesChance(QueryChanceCondition queryChanceCondition);
    
    /**
     * 自定义自然注册销售指派
     * @author 王超
     * @param seatsIdList
     */
    public void updateSeatsDefine(Map<String, Object> map);
    
    /**
     * 清空自然注册销售指派
     * @author 王超
     * @param seatsIdList
     */
    public void updateSeatsEmpty(int groupId);
    
    /**
     * 查询销售追呼统计页面
     * @author 代长福
     * @return
     */
    public PageResult getSalesStat(QueryStatCondition queryStatCondition);
    
    /**
     * 查询销售追呼统计列表
     * @author 代长福
     * @param pageQuery
     * @return
     */
    public List<SalesStatDTO> getSalesStatList(QueryStatCondition queryStatCondition);
    
    /**
     * 按小时查询销售追呼统计
     * @author 代长福
     * @param pageQuery
     * @return
     */
    public List<SalesStatDTO> getSalesStatByHour(QueryStatCondition queryStatCondition);
    
    /**
     * 查询未指派销售追呼统计
     * @王超
     * @param queryStatCondition
     * @return
     */
    public List<SalesStatDTO> getUndesignedInfo(QueryStatCondition queryStatCondition);
    
    /**
     * 查询未指派销售追呼统计按小时
     * @王超
     * @param queryStatCondition
     * @return
     */
    public List<SalesStatDTO> getUndesignedInfoBuyHour(QueryStatCondition queryStatCondition);
    
    
	/**
     * 根据CusId获得订单对象集合
     * @author 范昕 2011-11-06
     * @return
     */   
    public List<ContractCrmDTO> getContractByCusId(int cusId) ;
    
    public Integer getContractNum(int cusId);
    public Integer getContractNumPay(int cusId);
    
    /**
     * 查询销售机会库列表
     * @author 代长福
     * @param pageQuery
     * @return
     */
    public PageResult getChanceList(PageQuery pageQuery);
    
    
    
    /**
     * 批量领取销售机会
     * @author 代长福
     * @return
     */
    public Integer updategetBatchChance(Map<String, Object> map);
    
    /**
     * 查询已领取的销售机会数量（除去已购买的销售机会）
     *
     * @param userId
     * @return
     */
    public Integer getDrawChanceCount(int userId);
    
    /**
     * @author 王超
     * 定时将过期机会转入机会库
     */
    public void updateCheckChance(QueryChanceCondition queryChanceCondition);
    
    /**
     * 检查已购买用户
     */
    public void updateCheckIsBuy();
    
    /**
     * 获取定时延时时间
     * @author 代长福
     * @return
     */
    public Integer getTiming();
    
    /**
     * 修改定时延迟时间
     * @author 代长福
     * @param timingNum
     * @return
     */
    public Integer updateTiming(int timingNum);
    
}