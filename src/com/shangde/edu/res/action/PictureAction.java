package com.shangde.edu.res.action;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.exception.CommException;
import com.shangde.edu.res.condition.QueryPictureCondition;
import com.shangde.edu.res.domain.Picture;
import com.shangde.edu.res.service.IPicture;

/**  
 * 图片管理action
 * @author miaoshusen
 * @version 1.0
*/
public class PictureAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(PictureAction.class);
	
	private Picture picture;
	private IPicture pictureService;
	private QueryPictureCondition queryPictureCondition;

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public IPicture getPictureService() {
		return pictureService;
	}

	public void setPictureService(IPicture pictureService) {
		this.pictureService = pictureService;
	}

	public QueryPictureCondition getQueryPictureCondition() {
		if (queryPictureCondition == null) {
			queryPictureCondition = new QueryPictureCondition();
			
		}
		return queryPictureCondition;
	}

	public void setQueryPictureCondition(
			QueryPictureCondition queryPictureCondition) {
		this.queryPictureCondition = queryPictureCondition;
	}

	public String getPictureList(){
		try{
			setPage(this.pictureService.getPictureList(getQueryPictureCondition()));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("PictureAction.getPictureList", e);
			return ERROR;
		}
		return "listPicture";

	}

}
