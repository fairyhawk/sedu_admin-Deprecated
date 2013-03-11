package com.shangde.edu.dis.web.action;

import com.shangde.common.action.CommonAction;
import com.shangde.edu.dis.condition.QueryPlateCondition;
import com.shangde.edu.dis.service.IPlateManager;

public class PlateManagerAction extends CommonAction {

	private IPlateManager plateService;
	
	private QueryPlateCondition queryPlate;
	
	public QueryPlateCondition getQueryPlate() {
		if (queryPlate == null) {
			queryPlate = new QueryPlateCondition();
		}
		return queryPlate;
	}
	public void setQueryPlate(QueryPlateCondition queryPlate) {
		this.queryPlate = queryPlate;
	}
	public IPlateManager getPlateService() {
		return plateService;
	}
	public void setPlateService(IPlateManager plateService) {
		this.plateService = plateService;
	}
	
	public String searchPlate(){
		this.getQueryPlate().setPageSize(20);
		setPage(plateService.GetPlate(getQueryPlate()));
		setPageUrlParms();
		if (getPage() != null) {
			getPage().setPageSize(20);
		}
		return "success";
	}
}

