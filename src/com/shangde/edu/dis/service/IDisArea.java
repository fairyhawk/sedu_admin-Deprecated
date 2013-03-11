package com.shangde.edu.dis.service;

import java.util.List;
import com.shangde.edu.dis.domain.DisArea;
import com.shangde.edu.dis.condition.QueryDisAreaCondition;
/**
 * IDisArea
 * User: guoqiang.liu Date: 2011-05-14
 */
public interface IDisArea {
	/**
	 * 
	 * 
	 * @param disArea
	 * @return id
	 */
	public Integer addDisArea(DisArea disArea);

	/**
	 */
	public void delDisAreaById();

	/**
	 * 
	 * @param disArea
	 */
	public void updateDisArea(DisArea disArea);

	/**
	 * 
	 * @return DisArea
	 */
	public DisArea getDisAreaById();

	/**
	 * 条件查询
	 * @param queryDisAreaCondition
	 * @return List<DisArea>
	 */
	public List<DisArea> getDisAreaList(
			QueryDisAreaCondition queryDisAreaCondition);
}