package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.TvdTvd;
import com.shangde.edu.res.condition.QueryTvdTvdCondition;
import com.shangde.common.service.BaseService;


@SuppressWarnings("unchecked")
public class TvdTvdImpl extends BaseService implements ITvdTvd {
	public java.lang.Integer addTvdTvd(TvdTvd tvdTvd) {
		return simpleDao.createEntity("TvdTvd_NS.createTvdTvd", tvdTvd);
	}

	public void delTvdTvdById(int id) {
		simpleDao.deleteEntity("TvdTvd_NS.deleteTvdTvdById", id);
	}

	public void updateTvdTvd(TvdTvd tvdTvd) {
		simpleDao.updateEntity("TvdTvd_NS.updateTvdTvd", tvdTvd);
	}

	public TvdTvd getTvdTvdById(int id) {
		return simpleDao.getEntity("TvdTvd_NS.getTvdTvdById", id);
	}

	public List<TvdTvd> getTvdTvdList(QueryTvdTvdCondition queryTvdTvdCondition) {
		return simpleDao.getForList("TvdTvd_NS.getTvdTvdList",
				queryTvdTvdCondition);
	}
}
