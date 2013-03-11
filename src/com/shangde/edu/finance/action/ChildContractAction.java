package com.shangde.edu.finance.action;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.shangde.common.action.CommonAction;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.finance.condition.QueryChildContractCondition;
import com.shangde.edu.finance.domain.ChildContract;
import com.shangde.edu.finance.service.IChildContract;

/**
 * ChildContract管理action
 * 
 * @author fanxin
 * @version 1.0
 */
public class ChildContractAction extends CommonAction {
	private static final Logger logger = Logger.getLogger(ChildContractAction.class);
	private static final long serialVersionUID = 1L;
	/**
	 * 声名ChildContract的PO对象
	 */
	private ChildContract childContract = new ChildContract();
	private QueryChildContractCondition queryChildCondition;
	/**
	 * 声明订单服务
	 */
	private IChildContract childContractService;
	private List<ChildContract> childConList = new ArrayList<ChildContract>();
	private List<SellWay> sellWayList=new ArrayList<SellWay>();
	/**
     * 根据条件获取 ChildContract集合
     * @return  String
     * @author fanxin
     * @version 1.0
     */
    public String getChildContractList(){
    	try{
    	    getQueryChildCondition().setPageSize(30);
    	    queryChildCondition.setPageSize(30);
    	    System.out.println("aaa:"+getQueryChildCondition().getPageSize());
    	    setPage(childContractService.getChildContractList(getQueryChildCondition()));
            setPageUrlParms();
            if (getPage() != null) {
                getPage().setPageSize(30);
            }
    		return "childContractList";
    	}catch(Exception e){
    		logger.error("getChildContractList方法出现异常！",e);
    	}
    	return "error";
    }
	
	public ChildContract getChildContract() {
		return childContract;
	}
	
	public void setChildContract(ChildContract childContract) {
		this.childContract = childContract;
	}
	
	public IChildContract getChildContractService() {
		return childContractService;
	}
	
	public void setChildContractService(IChildContract childContractService) {
		this.childContractService = childContractService;
	}

	public List<ChildContract> getChildConList() {
		return childConList;
	}

	public void setChildConList(List<ChildContract> childConList) {
		this.childConList = childConList;
	}

    public List<SellWay> getSellWayList() {
        return sellWayList;
    }

    public void setSellWayList(List<SellWay> sellWayList) {
        this.sellWayList = sellWayList;
    }

    public QueryChildContractCondition getQueryChildCondition() {
        if (queryChildCondition==null){
            queryChildCondition= new QueryChildContractCondition();
        }
        return queryChildCondition;
    }

    public void setQueryChildCondition(QueryChildContractCondition queryChildCondition) {
        this.queryChildCondition = queryChildCondition;
    }
	
}
