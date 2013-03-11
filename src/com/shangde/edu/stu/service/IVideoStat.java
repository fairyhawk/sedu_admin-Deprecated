package com.shangde.edu.stu.service;

import java.util.List;
import com.shangde.edu.stu.domain.VideoStat;
import com.shangde.edu.stu.condition.QueryVideoStatCondition;

public interface IVideoStat {
    /**
     * 
     * @param videoStat 
     * @return id
     */
    public Integer addVideoStat(VideoStat videoStat);

    /**
     * 
     */
    public void delVideoStatById();

    /**
     * 
     * @param videoStat 
     */
    public void updateVideoStat(VideoStat videoStat);

    /**
     * 
     * @return 
     */
    public VideoStat getVideoStatById();

    /**
     * 
     * @param 
     * @return 
     */
    public List<VideoStat> getVideoStatList(QueryVideoStatCondition queryVideoStatCondition);
}