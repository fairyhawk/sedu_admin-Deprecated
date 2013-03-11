package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.LoginRecord;
import com.shangde.edu.cus.condition.QueryLoginRecordCondition;

/**
 * LoginRecord服务类
 * User: guoqiang.liu
 * Date: 2010-11-17
 */
public interface ILoginRecord {
    /**
     * 添加LoginRecord
     * @param loginRecord 登陆信息LoginRecord
     * @return id
     */
    public void addLoginRecord(LoginRecord loginRecord);

    /**
     * 删除LoginRecord
     */
    public void delLoginRecordById();

    /**
     * 修改LoginRecord
     * @param loginRecord 登陆信息LoginRecord
     */
    public void updateLoginRecord(LoginRecord loginRecord);

    /**
     * 根据id获取LoginRecord
     * @return LoginRecord
     */
    public LoginRecord getFirstLoginRecordByCusId(int cusId);

    /**
     * 获取LoginRecord列表
     * @param queryLoginRecordCondition 查询条件
     * @return 登陆信息列表
     */
    public List<LoginRecord> getLoginRecordList(QueryLoginRecordCondition queryLoginRecordCondition);

    /**
     * 获取地址为空的登录信息列表
     * @return
     */
	public List<LoginRecord> getAddressNullLoginRecordList();
    /**
     * 删除LoginRecord
     */
    public void delLoginRecordByCusId(int cusId);
    /**
     * 根据用户ID按照登录时间升序排列
     * @param queryLoginRecordCondition
     * @return
     */
    public List<LoginRecord> getLoginRecordListASC(QueryLoginRecordCondition queryLoginRecordCondition);
    
    /**
     * Yangning 2011/12/06 后台批量删除用户时同时级联删除delLoginRecoerd
     * @param cusIds
     * @return
     * Author:Yangning
     * CreateDate:2011-12-6
     */
    public Integer delBathLoginRecordByCusIds(String cusIds);

}