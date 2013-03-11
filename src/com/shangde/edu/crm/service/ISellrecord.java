package com.shangde.edu.crm.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.crm.condition.QuerySellrecordCondition;
import com.shangde.edu.crm.domain.Sellrecord;
import com.shangde.edu.crm.dto.SimpleStatDTO;
import com.shangde.edu.sys.condition.QueryUserCondition;
import com.shangde.edu.sys.domain.User;

/**
 * Sellrecord 接口管理
 * User: guoqiang.liu
 * Date: 2011-09-21
 */
public interface ISellrecord extends Serializable{
	/**
     * 添加Sellrecord
     * @param sellrecord 要添加的Sellrecord
     * @return Integer
     */
    public  java.lang.Integer addSellrecord(Sellrecord sellrecord);
    /**
     * 删除一个Sellrecord
     * 
     */
    public void delSellrecordById();

    /**
     * 修改Sellrecord
     * @param sellrecord 要修改的Sellrecord
     */
    public void updateSellrecord(Sellrecord sellrecord);

 
    public Sellrecord getSellrecordByCUSId(int cus_id);

  
    public List<Sellrecord> getSellrecordList(QuerySellrecordCondition querySellrecordCondition);
    /**
     * 通过crmId查询Sellrecord
     * @param crm_id
     * @return
     */
    public Sellrecord getSellrecordByCrmId(int crm_id);
    
    /**
     *  @author 王超
     *  获取所有用户列表
     * @return
     */
    public List<User> getSoldUserList(QueryUserCondition queryUserCondition);
    
    /**
     * @author 王超
     * 获取简单统计页面
     * @return
     */
    public PageResult getSimpleStat(QuerySellrecordCondition querySellRecordCondition);
    
    /**
     * @author 王超
     * 获取简单统计列表
     * @param querySellRecordCondition
     * @return
     */
    public List<SimpleStatDTO> getSimpleStatList(QuerySellrecordCondition querySellRecordCondition);
    
    /**
     * 根据用户Id列表获取对象列表
     * @param cusIdList
     * @return
     */
    public Integer getSellrecordByCUSIdList(ArrayList cusIdList);
    
    /**
     * 获取单位时间内数据
     * @author 王超
     * @param querySellRecordCondition
     * @return
     */
    public List<SimpleStatDTO> getSimpleStatObj(QuerySellrecordCondition querySellRecordCondition);
    
    /**
     * 根据groupId获取用户列表
     * @param groupId
     * @return
     */
    public List<User> getGroupUserInfo(int groupId);
    
}