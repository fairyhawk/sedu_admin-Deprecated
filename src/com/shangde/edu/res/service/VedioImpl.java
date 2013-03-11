package com.shangde.edu.res.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.dto.SubjectPlayTypeDTO;
import com.shangde.edu.res.condition.QueryVedioCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class VedioImpl extends BaseService implements IVedio{
    public java.lang.Integer addVedio(Vedio vedio) {
        return simpleDao.createEntity("Vedio_NS.createVedio",vedio);
    }

    public void delVedioById(int voId){
        simpleDao.deleteEntity("Vedio_NS.deleteVedioById",voId);
    }

    public void updateVedio(Vedio vedio) {
        simpleDao.updateEntity("Vedio_NS.updateVedio",vedio);
    }

    public Vedio getVedioById(int voId) {
        return simpleDao.getEntity("Vedio_NS.getVedioById",voId);
    }

    public PageResult getVedioList(QueryVedioCondition queryVedioCondition) {
         return simpleDao.getPageResult("Vedio_NS.getVedioList", "Vedio_NS.getVedioListCount", queryVedioCondition);
    	}

	public List<Vedio> getVedioByVgId(int vgId) {
		return simpleDao.getForList("Vedio_NS.getVedioByVgId", vgId);
		
	}
	
	public void deleteVedioByPointid(int pointId) {
		simpleDao.deleteEntity("Vedio_NS.deleteVedioByPointid", pointId);
	}
	
	public Vedio getVedioByPointid(int pointId){
		return simpleDao.getEntity("Vedio_NS.getVedioByPointid", pointId);
	}

	@Override
	public int getKponitIdByUrl(String url) {
		return simpleDao.getEntity("Vedio_NS.getKponitIdByUrl", url);
	}
	
	public int updateCCURLbyVOId(Map paramMap){
		return simpleDao.update("Vedio_NS.updateCCURLbyVOId", paramMap);
	}
	
	public PageResult getKpointList4cc(QueryVedioCondition condition){
		return simpleDao.getPageResult("Vedio_NS.getKpointList4cc", "Vedio_NS.getKpointList4ccCount", condition);
	}
	
	 /**
	  * 閫氳繃subjectId鏇存柊res_vedio_tbl涓殑play_type锛堟挱鏀剧被鍨嬶細0=highso  1=cc锛�
	  * @param subjectId
	  * @throws Exception
	  */
	public String updatePlayType(Integer subjectId,Integer playType) throws Exception {
		if(subjectId!=null&&playType!=null){
			Map<String,Integer> map=new HashMap<String,Integer>();
			map.put("subjectId", subjectId);
			map.put("playType", playType);
			simpleDao.updateEntity("Vedio_NS.updatePlayType", map);
			return "1";
		}
		return "0";
	}
	
	   public  List<SubjectPlayTypeDTO>getSubjectPlayType(){
		   return simpleDao.getForList("Vedio_NS.getSubjectPlayType", null);
	   }
}
