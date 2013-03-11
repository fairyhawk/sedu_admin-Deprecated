package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.Picture;
import com.shangde.edu.res.condition.QueryPictureCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class PictureImpl extends BaseService implements IPicture{
    public java.lang.Integer addPicture(Picture picture) {
return simpleDao.createEntity("Picture_NS.createPicture",picture);
    }

    public void delPictureById(int picId){
        simpleDao.deleteEntity("Picture_NS.deletePictureById",picId);
    }

    public void updatePicture(Picture picture) {
        simpleDao.updateEntity("Picture_NS.updatePicture",picture);
    }

    public Picture getPictureById(int picId) {
        return simpleDao.getEntity("Picture_NS.getPictureById",picId);
    }

    public PageResult getPictureList(QueryPictureCondition queryPictureCondition) {
        return simpleDao.getPageResult("Picture_NS.getPictureList", "Picture_NS.getPictureListCount", queryPictureCondition);
    	
    }

	public <Picture> List getPictureByVoId(int voId) {
		return simpleDao.getForList("Picture_NS.getPictureByVoId", voId);
		
	}

	public <Picture> List getPictureByBkId(int bkId) {
		
		return simpleDao.getForList("Picture_NS.getPictureByBkId", bkId);
	}
	
}
