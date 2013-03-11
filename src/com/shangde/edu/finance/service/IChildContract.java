package com.shangde.edu.finance.service;

import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.domain.ChildContract;
import com.shangde.edu.finance.condition.QueryChildContractCondition;

/**
 * ChildContract接口管理
 * User: guoqiang.liu
 * Date: 2012-02-23
 */
public interface IChildContract {
   
    public Integer addChildContract(ChildContract childContract);

    public void delChildContractById();

  
    public void updateChildContract(ChildContract childContract);

  
    public ChildContract getChildContractById(int id);
    
    public ChildContract getChildContract(QueryChildContractCondition queryChildContractCondition);

    public PageResult getChildContractList(PageQuery queryChildContractCondition);
    
    public ChildContract getChildContractByNo(String subContractNo);
    
    public Integer updateChildContractPayOK(ChildContract childContract);
    
    public   QueryChildContractCondition getPayChildContract(QueryChildContractCondition queryChildContractCondition);
    
}