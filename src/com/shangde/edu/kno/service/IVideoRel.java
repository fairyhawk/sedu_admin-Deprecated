package com.shangde.edu.kno.service;

import java.io.Serializable;
import java.util.List;
import com.shangde.edu.kno.domain.VideoRel;
import com.shangde.edu.kno.condition.QueryVideoRelCondition;

/**
 * VideoRel管理接口
 * User: guoqiang.liu
 * Date: 2011-09-14
 */
public interface IVideoRel extends Serializable{
    /**
     * 添加VideoRel
     * @param videoRel 要添加的VideoRel
     * @return id
     */
    public java.lang.Integer addVideoRel(VideoRel videoRel);

    /**
     * 根据id删除一个VideoRel
     * @param vrId 要删除的id
     */
    public void delVideoRelById(int vrId);

    /**
     * 修改VideoRel
     * @param videoRel 要修改的VideoRel
     */
    public void updateVideoRel(VideoRel videoRel);

    /**
     * 根据id获取单个VideoRel对象
     * @param vrId 要查询的id
     * @return 年级
     */
    public VideoRel getVideoRelById(int vrId);
    
    /**
     * 根据ksnId获取VideoRel对象列表
     * @return
     */
    public List<VideoRel> getVideoRelListByKsnId(int ksnId);

    /**
     * 根据条件获取VideoRel列表
     * @param queryVideoRelCondition 查询条件
     * @return 查询结果
     */
    public List<VideoRel> getVideoRelList(QueryVideoRelCondition queryVideoRelCondition);
}