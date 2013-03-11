package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.edu.dis.domain.CusUserDis;
import com.shangde.edu.dis.dto.SubjectDTO;

/**
 * 小组/用户关系接口
 */
public interface ICusUserDis {

	/**
	 * 查询我购买的专业
	 * 
	 * @return
	 */
	List<SubjectDTO> getMyBuySubject(Integer cusId);

	/**
	 * 添加用户关系
	 * 
	 * @param cusUserDis
	 * @return
	 */
	Integer addCusUserDis(CusUserDis cusUserDis);

	/**
	 * 根据用户ID查询用户加入过的小组
	 * 
	 * @param cusId
	 * @return
	 */
	List<CusUserDis> findCusUserDisByCusId(Integer cusId);

	/**
	 * 更新用户信息
	 * 
	 * @param cusUserDis
	 */
	public void updateCusUserDis(CusUserDis cusUserDis);

	/**
	 * 添加或更新
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(CusUserDis entity);
}
