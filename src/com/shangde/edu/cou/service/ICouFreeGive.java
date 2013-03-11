package com.shangde.edu.cou.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCouFreeGiveCondition;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.condition.QuerySellCouCondition;
import com.shangde.edu.cou.domain.CouFreeGive;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.dto.CourseDTO;
import com.shangde.edu.cou.dto.CourseIsAuditionDTO;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.dto.ListeningCourseDTO;
import com.shangde.edu.cou.webdto.UserCenterCourseDTO;
import com.shangde.edu.stu.condition.QueryPlanCondition;


public interface ICouFreeGive {
  
    public java.lang.Integer addCouFreeGive(CouFreeGive couFreeGive);

    public void delCouFreeGiveById(int couFreeGiveId);
    
    public void updateCouFreeGive(CouFreeGive couFreeGive);

    public CouFreeGive getCouFreeGiveById(int couFreeGiveId);

    public List<CouFreeGive> getCouFreeGiveList(QueryCouFreeGiveCondition queryCouFreeGiveCondition);
    
    public PageResult getCouFreeGiveByCondition(QueryCouFreeGiveCondition queryCouFreeGiveCondition);
   
}