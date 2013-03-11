package com.shangde.edu.card.service;

import java.util.*;

import com.shangde.common.service.BaseService;
import com.shangde.edu.card.domain.CardCourseQuartz;

public class CardCourseValidTimeServiceImpl extends BaseService  implements
		CardCourseValidTimeService {
	public void validTimeQuartz() throws Exception {
		//查询有效期为当日的，将其置为已过期状态，并且将已经存在的订单置为无效
		Map<String,Date> dateMap=new HashMap<String,Date>();
		dateMap.put("currentDate", new Date());
		List<CardCourseQuartz> cardCourseList=simpleDao.getForList("CardCourseQuartz_NS.selectCardCourse",dateMap);
		List<String> cardCourseIdList=new ArrayList<String>();
		List<String> orderCodeList=new ArrayList<String>();
		Map<String,List<String>> map=new HashMap<String,List<String>>();
		if(cardCourseList!=null&&cardCourseList.size()>0){
			for(CardCourseQuartz cardCourseQuartz : cardCourseList){
				cardCourseIdList.add(cardCourseQuartz.getCardCourseId().toString());
				if(cardCourseQuartz.getOrderCode()!=null&&!cardCourseQuartz.getOrderCode().equals("")){
					orderCodeList.add(cardCourseQuartz.getOrderCode());
				}
			}
			map.put("cardCourseIdList", cardCourseIdList);
			simpleDao.updateEntity("CardCourseQuartz_NS.updateOutDate", map);
			if(orderCodeList.size()>0){
				map.put("orderCodeList", orderCodeList);
				simpleDao.updateEntity("CardCourseQuartz_NS.updateOrder", map);
			}
		}
	}
}
