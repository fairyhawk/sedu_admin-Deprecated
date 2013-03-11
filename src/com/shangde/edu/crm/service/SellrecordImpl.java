package com.shangde.edu.crm.service;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.service.BaseServiceManyDb;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.crm.condition.QuerySellrecordCondition;
import com.shangde.edu.crm.domain.Sellrecord;
import com.shangde.edu.crm.dto.SimpleStatDTO;
import com.shangde.edu.sys.condition.QueryUserCondition;
import com.shangde.edu.sys.domain.User;

/**
 *Sellrecord 接口管理
 *User:guoqiang.liu
 *Date:2011-11-03 
 */
@SuppressWarnings("unchecked")
public class SellrecordImpl extends BaseServiceManyDb implements ISellrecord{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public java.lang.Integer addSellrecord(Sellrecord sellrecord) {
    	return simpleDao.createEntity("Sellrecord_NS.createSellrecord",sellrecord);
    }

    public void delSellrecordById(){
    }

    public void updateSellrecord(Sellrecord sellrecord) {
        simpleDao.updateEntity("Sellrecord_NS.updateSellrecord",sellrecord);
    }

    public Sellrecord getSellrecordByCUSId(int cus_id) {
    	 return simpleDao.getEntity("Sellrecord_NS.getSellrecordByCUSId",cus_id);
    }

    public List<Sellrecord> getSellrecordList(QuerySellrecordCondition querySellrecordCondition) {
        return simpleDao.getForList("Sellrecord_NS.getSellrecordList",querySellrecordCondition);
    }
    
    /**
     * 通过crmId查询Sellrecord
     * @param crm_id
     * @return
     */
    public Sellrecord getSellrecordByCrmId(int crm_id){
    	return simpleDao.getEntity("Sellrecord_NS.getSellrecordByCrmId",crm_id);
    }
    
    /**
     *  @author 王超
     *  获取所有用户列表
     * @return
     */
    public List<User> getSoldUserList(QueryUserCondition queryUserCondition){
    	return simpleDao.getForList("Sellrecord_NS.getSoldUserList", null);
    }
    
    /**
     * @author 王超
     * 获取简单统计对象
     * @return
     */
    public PageResult getSimpleStat(QuerySellrecordCondition querySellrecordCondition){
    	return simpleDaoRead.getPageResult("Sellrecord_NS.getSimpleStat", "Sellrecord_NS.getSimpleStatCount", querySellrecordCondition);
    }
    
    /**
     * @author 王超
     * 获取简单统计列表
     * @param querySellRecordCondition
     * @return
     */
    public List<SimpleStatDTO> getSimpleStatList(QuerySellrecordCondition querySellRecordCondition){
    	return simpleDaoRead.getForList("Sellrecord_NS.getSimpleStatList", querySellRecordCondition);
    }
    
    /**
     * 根据用户Id列表获取对象列表
     * @param cusIdList
     * @return
     */
    public Integer getSellrecordByCUSIdList(ArrayList cusIdList){
    	return simpleDao.getEntity("Sellrecord_NS.getSellrecordCountByCUSIdList", cusIdList);
    }
    
    /**
     * 获取单位时间内数据
     * @author 王超
     * @param querySellRecordCondition
     * @return
     */
    public List<SimpleStatDTO> getSimpleStatObj(QuerySellrecordCondition querySellRecordCondition){
    	return simpleDao.getForList("Sellrecord_NS.getSimpleStatObj", querySellRecordCondition);
    }
    
    /**
     * 根据groupId获取用户列表
     * @param groupId
     * @return
     */
    public List<User> getGroupUserInfo(int groupId){
    	return simpleDao.getForList("User_NS.getUserListByGroupId2", groupId) ;
    }
}
