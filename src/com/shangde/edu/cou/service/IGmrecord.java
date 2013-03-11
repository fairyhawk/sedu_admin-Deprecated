package com.shangde.edu.cou.service;

import java.util.List;
import com.shangde.edu.cou.domain.Gmrecord;
import com.shangde.edu.cou.condition.QueryGmrecordCondition;

/**
 *Gmrecord购买记录服务接口
 *User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface IGmrecord {
    /**
     * 添加 购买记录
     * @param gmrecord 购买记录Gmrecord
     * @return id
     */
    public java.lang.Integer addGmrecord(Gmrecord gmrecord);

    /**
     * 删除 购买记录
     * @param id 购买记录id
     */
    public void delGmrecordById(int id);

    /**
     * 更新 购买记录
     * @param gmrecord 要更新的购买记录
     */
    public void updateGmrecord(Gmrecord gmrecord);

    /**
     * 通过id获取 购买记录
     * @param id 购买记录id
     * @return 购买记录
     */
    public Gmrecord getGmrecordById(int id);

    /**
     * 根据条件查询 购买记录集合
     * @param queryGmrecordCondition 查询条件
     * @return 结果集合
     */
    public List<Gmrecord> getGmrecordList(QueryGmrecordCondition queryGmrecordCondition);

    /**
     * 根据登陆学员id查询最近一次
     * @param userId 学员id
     * @return 购买记录
     */
	public Gmrecord getLastGMRecord(int userId);
}