package com.shangde.edu.vst.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.vst.condition.VideoLevelCondition;
import com.shangde.edu.vst.condition.VideoStatisticsCondition;
import com.shangde.edu.vst.domain.VideoStatisticsCountDTO;
import com.shangde.edu.vst.domain.VideoStatisticsRecord;
import com.shangde.edu.vst.domain.VideoStatisticsSingleDTO;
import com.shangde.edu.vst.domain.VideoWatchLog;


/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 主体功能:
 *
 * @author		Yangning
 * @date		2012-2-6
 * @version 	修改人:
 * 				修改日期:
 * 				
 *              
 */
public interface IVideoStaticstics {
	public List<VideoStatisticsCountDTO> getVStByAllSubject(VideoStatisticsCountDTO condition);
	public List<VideoStatisticsSingleDTO> getVStBySingalSubject(int subjectId);
	public List<VideoStatisticsRecord> getVideoStatisticsRecordList(VideoStatisticsCondition condition) throws ParseException;
	public Integer getVideoStatisticsRecordCount(VideoStatisticsCondition condition);
	public List<VideoWatchLog> getVideoWatchLogList(QuerySubjectCondition condition);
	public List getVideoSelledCount(VideoLevelCondition condtion);
}
