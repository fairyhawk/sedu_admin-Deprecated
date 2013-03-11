package com.shangde.edu.kno.service;

import java.util.List;
import com.shangde.edu.kno.domain.VideoRel;
import com.shangde.edu.kno.condition.QueryVideoRelCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class VideoRelImpl extends BaseService implements IVideoRel{
    public java.lang.Integer addVideoRel(VideoRel videoRel) {
return simpleDao.createEntity("VideoRel_NS.createVideoRel",videoRel);
    }

    public void delVideoRelById(int vrId){
        simpleDao.deleteEntity("VideoRel_NS.deleteVideoRelById",vrId);
    }

    public void updateVideoRel(VideoRel videoRel) {
        simpleDao.updateEntity("VideoRel_NS.updateVideoRel",videoRel);
    }

    public VideoRel getVideoRelById(int vrId) {
        return simpleDao.getEntity("VideoRel_NS.getVideoRelById",vrId);
    }

    public List<VideoRel> getVideoRelList(QueryVideoRelCondition queryVideoRelCondition) {
        return simpleDao.getForList("VideoRel_NS.getVideoRelList",queryVideoRelCondition);
    }
    
    /**
     * 根据ksnId获取VideoRel对象列表
     * @return
     */
    public List<VideoRel> getVideoRelListByKsnId(int ksnId){
    	return simpleDao.getForList("VideoRel_NS.getVideoRelListByKsnId", ksnId);
    }
}
