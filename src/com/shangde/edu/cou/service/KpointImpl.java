package com.shangde.edu.cou.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.shangde.common.dao.ISimpleDao;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourKpJSONCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.dto.JilianKpointDTO;
import com.shangde.edu.cou.dto.KPointDTO;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.dto.LeafKpointUrlDTO;
import com.shangde.edu.cou.dto.UserKpointDTO;
import com.shangde.edu.cusmgr.condition.QueryCusCouKpointCondition;
import com.shangde.edu.cusmgr.service.ICusCouKpoint;

/**
 * Kpoint知识点服务实现类
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class KpointImpl extends BaseService implements IKpoint{
	
	/**
	 * 服务
	 */
	private ICusCouKpoint  cusCouKpointService;
	/**
     * 添加知识点Kpoint
     * @param kpoint 要添加的知识点
     * @return id
     */
    public Integer addKpoint(Kpoint kpoint){
        Integer result = simpleDao.createEntity("Kpoint_NS.createKpoint",kpoint);
    	QueryCusCouKpointCondition queryCusCouKpointCondition = new QueryCusCouKpointCondition();//条件
    	
    	queryCusCouKpointCondition.setCourseId(kpoint.getCourseId());
    	queryCusCouKpointCondition.setPointId(result);

    	List cusCouList = simpleDao.getForList("CusCouKpoint_NS.getCusCouKpointListByCourseId", queryCusCouKpointCondition);
    	simpleDao.batchExecuteSingleSql("CusCouKpoint_NS.createCusCouKpoint", cusCouList, ISimpleDao.BATCH_EXECUTE_METHOD_TYPE_INSERT, 200);
    	return result;
    }
    
    /**
     * 通过知识点ID删除知识点
     * @param pointId 删除知识点
     */
    public void delKpointById(int pointId){
    	simpleDao.deleteEntity("Books_NS.deleteBooksByPointId", pointId);
    	simpleDao.deleteEntity("Vedio_NS.deleteVedioByPointid", pointId);
    	simpleDao.deleteEntity("CusCouKpoint_NS.deleteCusCouKpointByPointId", pointId);//删除cuscoukpoint表中与知识点相关的数据
    	simpleDao.deleteEntity("Notes_NS.deleteNotesByPointId",pointId);
        simpleDao.deleteEntity("Kpoint_NS.deleteKpointById",pointId);
    }
    
    /**
     * 通过课程ID删除该课程的知识点
     */
    public void deleteKpointByCourseId(int courseId){
		QueryKpointCondition queryKpointCondition = new QueryKpointCondition();
		queryKpointCondition.setCourseId(courseId);
		List<Kpoint> kpointList = simpleDao.getForList("Kpoint_NS.getAllKpointListByCourseId", queryKpointCondition);
		
		for(int i = 0; i < kpointList.size(); i ++){
			delKpointById(kpointList.get(i).getPointId());
		}
		//simpleDao.deleteEntity("Kpoint_NS.deleteKpointByCourseId", courseId);
	}
	
    /**
     * 更新知识点Kpoint
     * @param kpoint 要更新的知识点
     */
    public void updateKpoint(Kpoint kpoint) {
        simpleDao.updateEntity("Kpoint_NS.updateKpoint",kpoint);
    }
    
    /**
     * 通过知识点ID获取知识点
     * @param pointId 知识点ID
     * @return 知识点
     */
    public Kpoint getKpointById(int pointId) {
        return simpleDao.getEntity("Kpoint_NS.getKpointById",pointId);
    }
    
    /**
     * 查询知识点
     * @param queryKpointCondition 查询条件
     * @return 查询结果
     */
    public List<Kpoint> getKpointList(QueryKpointCondition queryKpointCondition) {
        return simpleDao.getForList("Kpoint_NS.getKpointList",queryKpointCondition);
    }
	
	/**
     * 通过课程ID获取知识点集合
     * @param queryKpointCondition
     * @return 查询结果
     */
    public List<Kpoint> getKpointListByCourseId(QueryKpointCondition queryKpointCondition) {
    	List<Kpoint> result = simpleDao.getForList("Kpoint_NS.getKpointListByCourseId",queryKpointCondition);
    	if(result != null){
    		Collections.sort(result);
    	}
        return result;
    }
    
	/**
     * 条件查询
     * @param queryKpointCondition
     * @return 查询结果
     */
    public PageResult getKpointListByCondition(QueryKpointCondition queryKpointCondition) {
    	
        return simpleDao.getPageResult("Kpoint_NS.getKpointListByCondition", "Kpoint_NS.getKpointListByConditionCount", queryKpointCondition);
    }
	
	/**
     * 通过PID获取子集合
     * @param queryKpointCondition
     * @return
     */
	public List<Kpoint> getKpointListByPid(QueryKpointCondition queryKpointCondition) {
		
		List result = simpleDao.getForList("Kpoint_NS.getKpointListByPid",queryKpointCondition);
		Collections.sort(result);
		return result;
	}
	
	/**
     * 后台获取知识点DTO
     * @param queryKpointCondition
     * @return
     */
	public List<KeyValueDTO> getKpointDTOListByCourseId(QueryKpointCondition queryKpointCondition) {
		List result = simpleDao.getForList("Kpoint_NS.getKpointDTOListByCourseId", queryKpointCondition);
		Collections.sort(result);
		return result;
	}
	
	 /**
     * 前台视频获取知识点DTO ，包含vedioUrl
     * @param courseId
     * @return
     */
	public List<KeyValueDTO> getKeyDTOWebResultByCourseId(int courseId){
		List result = simpleDao.getForList("Kpoint_NS.getKeyDTOWebResultByCourseId", courseId);
		Collections.sort(result);
		return result;
	}
	/**
     * 获取试听课程
     * @param courseId
     * @return
     */
	public List<UserKpointDTO> getTryKpointTreeByCourseId(int courseId){
		List result = simpleDao.getForList("Kpoint_NS.getTryKpointTreeByCourseId", courseId);
		Collections.sort(result);
		return result;
	}
	
	/**
     * 根据课程ID 获取该课程的的所有叶子节点
     * @param courseId
     * @return 
     */
	public List<LeafKpointUrlDTO> getLeafKpointDTOByCourseId(int courseId){
		List result = simpleDao.getForList("Kpoint_NS.getLeafKpointDTOByCourseId", courseId);
		Collections.sort(result);
		return result;
	}
	
	/**
     * 根据知识点ID查看该知识点是否存在子节点
     * @param pId
     * @return 
     */
	public int getChildKpointCount(int pId){
		return simpleDao.getEntity("Kpoint_NS.getChildKpointCount", pId);
	}
	
	/**
	 * 通过课程id查询知识点
	 */
	 public List<JilianKpointDTO> getJilianKpoint(QueryCourKpJSONCondition couid){
		 
		 return simpleDao.getForList("Kpoint_NS.getKpointListjilian", couid);
	 }

	public ICusCouKpoint getCusCouKpointService() {
		return cusCouKpointService;
	}

	public void setCusCouKpointService(ICusCouKpoint cusCouKpointService) {
		this.cusCouKpointService = cusCouKpointService;
	}

	public int getFirstLevelPointId(Kpoint kp) {
		if(kp.getLevel() == 2) {
			return Integer.parseInt(simpleDao.getEntity("Kpoint_NS.getFirstLevelPointIdBy2", kp.getPointId()).toString());
		} else if(kp.getLevel() == 3) {
			return Integer.parseInt(simpleDao.getEntity("Kpoint_NS.getFirstLevelPointIdBy3", kp.getPointId()).toString());
		} else {
			return kp.getPointId();
		}
	}
	//分页查找
	public PageResult getMoreKpointListForUnAss(QueryKpointCondition condition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("Kpoint_NS.getMoreKpointListForUnAss", "Kpoint_NS.getKpointCount", condition);
	}

	public List<Kpoint> getKpointListForUnAss(QueryKpointCondition qkCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Kpoint_NS.getKpointListForUnAss", qkCondition);
	}

	public List<Integer> getSellWayIdsByKpointId(QueryKpointCondition qkCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Kpoint_NS.getSellWayIdsByKpointId", qkCondition);
	}

	/**
	 * 为张栋提供iphone端数据
	 */
	public int getKpointSumByCourseId(int courseId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Kpoint_NS.getKpointSumByCourseId", courseId);
	}

	public String getKpointVoUrlById(int courseId) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Kpoint_NS.getKpointVoUrlById", courseId);
	}

	public PageResult getIphoneKpointListByPid(
			QueryKpointCondition queryKpointCondition) {
		// TODO Auto-generated method stub
		return simpleDao.getPageResult("Kpoint_NS.getIphoneKpointListByPid", "Kpoint_NS.getIphoneKpointCountByPid", queryKpointCondition);
	}

	@Override
	public int getRealUpNumByCourseId(int course_id) {
		
		return simpleDao.getEntity("Kpoint_NS.getRealUpNumByCourseId", course_id);
	}
}
