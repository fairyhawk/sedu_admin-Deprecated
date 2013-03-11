package com.shangde.flex.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;
import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.exam.condition.QueryQstConditionBFQ;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.service.IExampaper;
import com.shangde.edu.exam.service.IQst;
import com.shangde.edu.res.condition.QueryNotesCondition;
import com.shangde.edu.res.domain.Books;
import com.shangde.edu.res.domain.Notes;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.service.IBooks;
import com.shangde.edu.res.service.INotes;
import com.shangde.edu.res.service.IVedio;

/**
 * Copyright (c) Sunland Career CO.LTD. All rights are reserved.
 * LICENSE INFORMATION
 * 
 * 正式听课双击课程目录树时,调用的ACTION
 *
 * @author		Zhang Dong
 * @date		2010-09-17
 * @version 	修改人:宁肖
 *              修改日期:2011.5-17 
 *              修改功能:flash播放通过js取得值需要传输数据格式JSON
 */
public class FlexVideoAction extends CommonAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2213960401326640960L;
	
	private static final Logger logger = Logger.getLogger(FlexVideoAction.class);  
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;
	/**
	 * 笔记服务对象
	 */
	private INotes notesService;
	/**
	 * 声名视频的服务
	 */
	private IVedio vedioService;
	/**
	 * 考试服务
	 */
	private IExampaper exampaperService;
	/**
	 * 试题服务
	 */
	private IQst qstService;
	/**
	 * 笔记查询条件
	 */
	private QueryNotesCondition queryNotesCondition;
	/**
	 * 声名书籍服务
	 */
	private IBooks booksService;
/*---------------宁肖添加的变量 ---------------*/
	/**
	 *知识点ID
	 */
	private  int pointId;
	/**
	 *视频ID
	 */
	private  int videoId;
	/**
	 * 当前号
	 */
	private  int pageNo;
	/**
	 * 总长度
	 */
	private  int pageSize;
	/**
	 * 封装ppt和mp3的视频地址
	 */
   private List<String> urlList;
	/**
	 * 双击视频以后取得改视频相关的视频信息
	 * @param pointId
	 * @return
	 * @throws Exception 
	 */
	public void  queryVideoInfo() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
			//查询笔记
			PageResult pageResult = getNotesListByPoint();
			//查询讲义
			Kpoint kPoint = kpointService.getKpointById(getPointId());
			//查询习题
		//	List<QueryQstConditionBFQ> list = getExamPaperkpointid(getPointId());
			//查询分数
			Number point = queryVedioScores(getVideoId());
			urlList = getBooksUrl(getPointId());
			//下载讲义URL
			String bookUrl = urlList.get(0);
			//下载MP3音频文件
			String ypmpUrl = urlList.get(1);
			map.put("pageResult", pageResult);
			map.put("kPoint", kPoint);
	//		map.put("examPaperList", list);
			map.put("point", point);
			map.put("bookUrl", bookUrl);
			map.put("ypUrl", ypmpUrl);
			JSONObject jo = JSONObject.fromObject(map);
			Post_outObj(jo.toString());
	}
	
	/**
	 * 根据知识点获取笔记
	 * @param pointId
	 * @return
	 */
	public PageResult getNotesListByPoint() {
		PageResult pr = null;
			getQueryNotesCondition().setPageSize(getPageSize());
			getQueryNotesCondition().setCurrentPage(getPageNo());
			getQueryNotesCondition().setPointId(getPointId());
			getQueryNotesCondition().setCusId(getLoginUserId());
//			getQueryNotesCondition().setCusId(347);
			
			pr = notesService.getNotesListByPointId(getQueryNotesCondition());
			for(int i=pr.getPageResult().size()-1; i>=0; i--) {
				((Notes)pr.getPageResult().get(i)).setFlexIndex(i+1);
				if(i != 0) {
					((Notes)pr.getPageResult().get(i-1)).setNextNotes((Notes)pr.getPageResult().get(i));
				}
			}
		return pr;
	}

	/**
	 * 查询视频打的分数
	 * @param vId
	 * @return
	 */
	public Number queryVedioScores(int videoId){
		Number voScores = 0;
		if(videoId!=0){
			Vedio vedio = vedioService.getVedioById(videoId);
			if(vedio != null){
				voScores = vedio.getVoScores();
				if(voScores.intValue() == 0){
					voScores = 10;
				}
			}
		}
		return voScores;
	}
	
	/**
	 * 下载讲义
	 * @param vId
	 * @return
	 */
	public List<String> getBooksUrl(int pointId){
		urlList = new ArrayList<String>();
		List<Books> bookList = this.booksService.getBooksListByPointId(pointId);
		if(bookList != null && !bookList.isEmpty()){
			urlList.add(bookList.get(0).getBkUrl());
			urlList.add(bookList.get(0).getYpUrl());
		}
		return urlList;
	}
	
	/**
	 * 查询本章习题
	 * @param kPoint
	 * @return
	 */
	public List<QueryQstConditionBFQ> getExamPaperkpointid(int kPoint) {
		List<QueryQstConditionBFQ> qstlistall = new ArrayList<QueryQstConditionBFQ>();
		try {
			int index = 0;
			List<Exampaper> exampaperList = exampaperService
					.getExampaperByKpointList(kPoint);
			for (int a = 0; a < exampaperList.size(); a++) {
				Exampaper exam = exampaperList.get(a);
				List<Qst> qstlist = qstService.getQstByIdList(exam.getEpId());
				for (int i = 0; i < qstlist.size(); i++) {

					QueryQstConditionBFQ sst = new QueryQstConditionBFQ();
					sst.setQstId(qstlist.get(i).getQstId());
					sst.setEpId(qstlist.get(i).getEpId());
					sst.setIsAsr(qstlist.get(i).getIsAsr());
					sst.setWrongJude(qstlist.get(i).getWrongJude());
					sst.setQstContent(qstlist.get(i).getQstContent());
					sst.setAoptOrder(qstlist.get(i).getOptions().get(0)
							.getOptOrder());
					sst.setBoptOrder(qstlist.get(i).getOptions().get(1)
							.getOptOrder());
					sst.setCoptOrder(qstlist.get(i).getOptions().get(2)
							.getOptOrder());
					sst.setDoptOrder(qstlist.get(i).getOptions().get(3)
							.getOptOrder());
					index++;
					sst.setQstIndex(index);
					if (qstlist.get(i).getOptions().get(0).getOptContent() == null
							&& qstlist.get(i).getOptions().get(0)
									.getOptContent() == "") {
						sst.setAoptContent("");
					} else {
						sst.setAoptContent(qstlist.get(i).getOptions().get(0)
								.getOptContent());
					}
					if (qstlist.get(i).getOptions().get(1).getOptContent() == null
							&& qstlist.get(i).getOptions().get(1)
									.getOptContent() == "") {
						sst.setBoptContent("");
					} else {
						sst.setBoptContent(qstlist.get(i).getOptions().get(1)
								.getOptContent());
					}
					if (qstlist.get(i).getOptions().get(2).getOptContent() == null
							&& qstlist.get(i).getOptions().get(2)
									.getOptContent() == "") {
						sst.setCoptContent("");
					} else {
						sst.setCoptContent(qstlist.get(i).getOptions().get(2)
								.getOptContent());
					}
					if (qstlist.get(i).getOptions().get(3).getOptContent() == null
							&& qstlist.get(i).getOptions().get(3)
									.getOptContent() == "") {
						sst.setDoptContent("");
					} else {
						sst.setDoptContent(qstlist.get(i).getOptions().get(3)
								.getOptContent());
					}
					qstlistall.add(sst);

				}
			}
		} catch (Exception e) {
			logger.error("FlexVideoAction.getExamPaperkpointid", e);
			return null;
		}
		return qstlistall;
	}
	public QueryNotesCondition getQueryNotesCondition() {
		if(queryNotesCondition == null) {
			queryNotesCondition = new QueryNotesCondition();
		}
		return queryNotesCondition;
	}

	public IExampaper getExampaperService() {
		return exampaperService;
	}

	public void setExampaperService(IExampaper exampaperService) {
		this.exampaperService = exampaperService;
	}

	public IQst getQstService() {
		return qstService;
	}

	public void setQstService(IQst qstService) {
		this.qstService = qstService;
	}

	public INotes getNotesService() {
		return notesService;
	}

	public void setNotesService(INotes notesService) {
		this.notesService = notesService;
	}

	public IVedio getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVedio vedioService) {
		this.vedioService = vedioService;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public IBooks getBooksService() {
		return booksService;
	}

	public void setBooksService(IBooks booksService) {
		this.booksService = booksService;
	}
	/*-----宁肖修改-----------------发送流的方式-------------------------------------------------*/
    /**
    * 将封装好的JSON数据以流的形式发送到客户端
    */
	public void Post_outObj(String json) throws Exception{
    	try {
    	getServletResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter out = getServletResponse().getWriter();//获取页面输出流
		out.println(json.toString());
		out.flush();
		out.close();
    	} catch (Exception e) {
    		logger.error("FlexVideoAction.Post_outObj", e);
		}
    }
	/*-----宁肖修改-----------------Get/Set变量值-------------------------------------------------*/
	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public int getVideoId() {
		return videoId;
	}

	public void setVideoId(int videoId) {
		this.videoId = videoId;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
}
