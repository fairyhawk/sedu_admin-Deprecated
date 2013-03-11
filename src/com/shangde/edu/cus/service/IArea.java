package com.shangde.edu.cus.service;

import java.util.List;
import com.shangde.edu.cus.domain.Area;
import com.shangde.edu.cus.condition.QueryAreaCondition;

public interface IArea {
	 /**
     * 根据area对象添加Area
     * @param area
     * @return
     */
    public java.lang.Integer addArea(Area area);

    /**
     * 根据用户id删除Area
     * @param id
     * @return
     */
    public void delAreaById(int id);

    /**
     * 根据area更新
     * @param area
     * @return
     */
    public void updateArea(Area area);

    /**
     * 根据用户id更新
     * @param id
     * @return Area
     */
    public Area getAreaById(int id);

    /**
     * 根据用户id更新
     * @param queryAreaCondition
     * @return
     */
    public List<Area> getAreaList(QueryAreaCondition queryAreaCondition);
}