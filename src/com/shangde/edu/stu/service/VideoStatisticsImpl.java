package com.shangde.edu.stu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shangde.edu.stu.domain.VideoStatistics;
import com.shangde.edu.stu.condition.QueryVideoStatisticsCondition;
import com.shangde.common.service.BaseService;

@SuppressWarnings("unchecked")
public class VideoStatisticsImpl extends BaseService implements IVideoStatistics {
    public Integer addVideoStatistics(VideoStatistics videoStatistics) {
        return simpleDao.createEntity("VideoStatistics_NS.createVideoStatistics",
                videoStatistics);
    }

    public void delVideoStatisticsById() {
    }

    public void updateVideoStatistics(VideoStatistics videoStatistics) {
        simpleDao.updateEntity("VideoStatistics_NS.updateVideoStatistics",
                videoStatistics);
    }

    public VideoStatistics getVideoStatisticsById() {
        return new VideoStatistics();
    }

    public List<VideoStatistics> getVideoStatisticsList(
            QueryVideoStatisticsCondition queryVideoStatisticsCondition) {
        return simpleDao.getForList("VideoStatistics_NS.getVideoStatisticsList",
                queryVideoStatisticsCondition);
    }

    public List<String> getUserVideoKiponitListByCourseId(Map<String, String> map) {
        return simpleDao.getForList(
                "VideoStatistics_NS.getUserVideoKiponitListByCourseId", map);
    }

    public List<String> getUserVideoKiponitListBySubjectId(Map<String, String> map) {
        return simpleDao.getForList(
                "VideoStatistics_NS.getUserVideoKiponitListBySubjectId", map);
    }

    /**
     * 根据课程ID和用户ID查找是否存在本课程的观看记录
     * 
     */
    public List<VideoStatistics> getVideoStatisticsListByCouseId(
            QueryVideoStatisticsCondition queryVideoStatisticsCondition) {
        return simpleDao.getForList("VideoStatistics_NS.getVideoStatisticsListByCouseIdCusId",
                queryVideoStatisticsCondition);
    }

    /**
     * 根据学员课程知识点ID更新用户知识点 把看过的知识点更新到表中 参数格式
     * 
     * @param String
     *            courseId@@KpointId@@cusId
     * @return
     */
    public void updateUserWatchKpoint(String courseInfo) {
        if (courseInfo == null || "".equals(courseInfo)) {
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = simpleDateFormat.format(new Date());
        // 日期+日期分隔符号+节点
        String dateSplit = "D";// 日期分格符号
        String spflag = "K";// 多个节点之间的分格符号
        try {
            // 传来的参数格式 courseID@@KpointId@@KpoindName
            String strInfo[] = courseInfo.split("@@");
            // 课程ID
            if ("".equals(strInfo[0])){
                return;
            }
            int courseId = Integer.valueOf(strInfo[0]);
            // 知识点id
            String lastviewid = strInfo[1];
            // 用户ID
            int cusId = Integer.valueOf(strInfo[2]);

            QueryVideoStatisticsCondition queryVideoStatisticsCondition = new QueryVideoStatisticsCondition();
            queryVideoStatisticsCondition.setCusId(cusId);
            queryVideoStatisticsCondition.setCourseId(courseId);
            // 根据课程ID和用户ID查找是否存在记录
            List<VideoStatistics> list = getVideoStatisticsListByCouseId(queryVideoStatisticsCondition);
            logger.info("select getVideoStatisticsListByCouseId");
            if (list == null || list.size() == 0) {// 不存在时插入新数据
                VideoStatistics videoStatistics = new VideoStatistics();
                videoStatistics.setCusId(queryVideoStatisticsCondition.getCusId());
                videoStatistics.setCourseId(courseId);
                // 第一次插入时直接加课程节点的kid+日期分隔符+日期
                videoStatistics.setViewCode(today + dateSplit + lastviewid);
                addVideoStatistics(videoStatistics);
                logger.info("logger addVideoStatistics");
            } else {// 存在时
                VideoStatistics videoStatistics = list.get(0);
                // 先判断今天此课时的节点已经添加过，添加过的不做操作
                String strkid = videoStatistics.getViewCode();
                String strkidarray[] = strkid.split(spflag);// 拆分获得每次看过的时间和节点
                boolean upflag = true;
                for (String skidtmp : strkidarray) {
                    // 判断是否已经存过
                    String[] sidanddate = skidtmp.split(dateSplit);// 将时间和节点拆分，0是节点，1是时间
                    if (lastviewid.equals(sidanddate[1])
                            && today.equals(sidanddate[0])) {
                        upflag = false;// 已经存在不做任何操作
                        break;
                    }
                }
                if (upflag) {
                    // 更新时为以前看过的+分隔符KID+现在看的课程节点
                    videoStatistics.setViewCode(videoStatistics.getViewCode() + spflag
                            + today + dateSplit + lastviewid);
                    updateVideoStatistics(videoStatistics);
                    logger.info("logger updateVideoStatistics");
                }
            }

        } catch (Exception ex) {
            logger.error("更新已看过课程错误！"+courseInfo, ex);
        }
    }

    /**
     * 根据subjectId和用户id获得已经看过的节点list
     * 
     * @param int
     *            subjectId 项目id, int cusId用户id
     * @return List<String>
     */

    public List<String> getUserLearnKpointBySubjectId(int subjectId, int cusId) {
        List<String> resList = new ArrayList<String>();

        // 节点+日期分隔符号+日期
        String dateSplit = "D";// 日期分格符号
        String spflag = "K";// 多个节点之间的分格符号

        Map<String, String> map = new HashMap<String, String>();
        map.put("cusId", cusId + "");
        map.put("subjectId", subjectId + "");
        // 根据项目ID和用户ID查找是否存在记录
        List<String> list = getUserVideoKiponitListBySubjectId(map);
        Set<String> set = new HashSet<String>();
        for (String strlist : list) {
            String[] skidDate = strlist.split(spflag);
            for (String stkid : skidDate) {
                String[] stkidarray = stkid.split(dateSplit);
                if (set.contains(stkidarray[1])) {
                    continue;
                } else {
                    set.add(stkidarray[1]);
                    resList.add(stkidarray[1]);
                }
            }
        }
        return resList;
    }

    /**
     * 根据课程ID和用户ID获得已经看过的所有节点list
     * 
     * @param int
     *            courseId 课程id, int cusId 用户id
     * @return List<String>
     */

    public List<String> getUserLearnKpointByCourseId(int courseId, int cusId) {
        List<String> resList = new ArrayList<String>();
        // 节点+日期分隔符号+日期
        String dateSplit = "D";// 日期分格符号
        String spflag = "K";// 多个节点之间的分格符号
        Map<String, String> map = new HashMap<String, String>();
        map.put("cusId", cusId + "");
        map.put("courseId", courseId + "");
        // 根据课程ID和用户ID查找是否存在记录
        List<String> list = simpleDao.getForList(
                "VideoStatistics_NS.getUserVideoKiponitListByCourseId", map);

        Set<String> set = new HashSet<String>();
        for (String strlist : list) {
            String[] skidDate = strlist.split(spflag);
            for (String stkid : skidDate) {
                String[] stkidarray = stkid.split(dateSplit);
                if (set.contains(stkidarray[1])) {
                    continue;
                } else {
                    set.add(stkidarray[1]);
                    resList.add(stkidarray[1]);
                }
            }
        }
        return resList;
    }

    /**
     * 根据指定日期和用户id获得今天看过的节点list
     * 
     * @param String
     *            today 日期格式 yyyyMMdd ,int cusId用户id
     * @return List<String>
     */

    public List<String> getUserLearnKpointByDate(String today, int cusId) {
        List<String> resList = new ArrayList<String>();
        // 节点+日期分隔符号+日期
        String dateSplit = "D";// 日期分格符号
        String spflag = "K";// 多个节点之间的分格符号
        Map<String, String> map = new HashMap<String, String>();
        map.put("cusId", cusId + "");
        map.put("today", today + "");
        // 根据日期和用户ID查找是否存在记录
        List<String> list = simpleDao.getForList(
                "VideoStatistics_NS.getUserVideoKiponitListByDate", map);
        Set<String> set = new HashSet<String>();
        for (String strlist : list) {
            String[] skidDate = strlist.split(spflag);
            for (String stkid : skidDate) {
                String[] stkidarray = stkid.split(dateSplit);
                if (set.contains(stkidarray[1])) {
                    continue;
                } else {
                    set.add(stkidarray[1]);
                    resList.add(stkidarray[1]);
                }
            }
        }
        return resList;
    }

    public int getVideoStatisticsCount(){
    	return simpleDao.getEntity("VideoStatistics_NS.getVideoStaticsCount", null);
    }
    
	public List<VideoStatistics> getVideoStatisticsListCollector(
			QueryVideoStatisticsCondition queryVideoStatisticsCondition) {
		// TODO Auto-generated method stub
	return simpleDao.getForList("VideoStatistics_NS.getVideoStatisticsListColletor",
                queryVideoStatisticsCondition);
	}
}
