package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.TvdTvd;
import com.shangde.edu.res.condition.QueryTvdTvdCondition;


/**
 *User:guoqiang.liu
 *Date:2011-03-04 
 */
public interface ITvdTvd {
	/**
	 *添加
	 *@param TvdTvd tvdTvd
	 *@return Integer
	 */
    public java.lang.Integer addTvdTvd(TvdTvd tvdTvd);
    /**
	 *删除
	 *@param id
	 *@return void
	 */
    public void delTvdTvdById(int id);

    /**
	 *更新
	 *@param tvdTvd
	 *@return void
	 */
    public void updateTvdTvd(TvdTvd tvdTvd);

    /**
	 *根据id查询
	 *@param id
	 *@return TvdTvd
	 */
    public TvdTvd getTvdTvdById(int id);


    /**
	 *根据查询条件查询
	 *@param queryTvdTvdCondition
	 *@return TvdTvd集合
	 */
    public List<TvdTvd> getTvdTvdList(QueryTvdTvdCondition queryTvdTvdCondition);
}