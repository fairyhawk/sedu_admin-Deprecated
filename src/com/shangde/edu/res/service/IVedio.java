package com.shangde.edu.res.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.dto.SubjectPlayTypeDTO;
import com.shangde.edu.res.condition.QueryVedioCondition;

/**  

 * @author miaoshusen

 * @version 1.0

 */

public interface IVedio {
	/**
	 * 添加视频的方法
	 */
	public java.lang.Integer addVedio(Vedio vedio);

	/**
	 * 根据视频id来删除视频的方法
	 */
	public void delVedioById(int voId);

	/**
	 * 修改视频的方法
	 */
	public void updateVedio(Vedio vedio);

	/**
	 * 根据视频id来获得视频对象的方法
	 */
	public Vedio getVedioById(int voId);

	/**
	 * 分页查询返回PageResult对象
	 */
	public PageResult getVedioList(QueryVedioCondition queryVedioCondition);

	/**
	 * 根据视频组id查询返回视频类类型的list
	 */
	public List<Vedio>  getVedioByVgId(int vgId);
	
	/**
	 * 
	 * @param pointId
	 * @authorcxs
	 * 功能：按知识点删除知识点视频
	 * @param pointId
	 */
	public void deleteVedioByPointid(int pointId);
	/**
	 * 根据知识点ＩＤ查找这个对应的视频
	 */
	public Vedio getVedioByPointid(int pointId);
	
	/**
     * 通过视频url查找节点id
     * @param url
     * @return
     */
    public int getKponitIdByUrl(String url);
    
    /**
     * 查询相关专业的视频类型
     * */
    
   public  List<SubjectPlayTypeDTO>getSubjectPlayType();
    
    /**
     * 写入到cc视频中
     * @param paramMap
     * @return
     */
    public int updateCCURLbyVOId(Map paramMap);
    
    public PageResult getKpointList4cc(QueryVedioCondition condition);
	
	 String updatePlayType(Integer subjectId,Integer playType) throws Exception;
	
}