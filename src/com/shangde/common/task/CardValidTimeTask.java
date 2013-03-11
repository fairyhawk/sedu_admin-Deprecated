package com.shangde.common.task;

import org.apache.log4j.Logger;

import com.shangde.edu.card.service.CardCourseValidTimeService;

public class CardValidTimeTask {
	private static final Logger logger = Logger.getLogger(CardValidTimeTask.class);
	private CardCourseValidTimeService cardCourseValidTimeService;
	public CardCourseValidTimeService getCardCourseValidTimeService() {
		return cardCourseValidTimeService;
	}
	public void setCardCourseValidTimeService(
			CardCourseValidTimeService cardCourseValidTimeService) {
		this.cardCourseValidTimeService = cardCourseValidTimeService;
	}
	public void validTimeQuartz(){
		try{
			logger.info("******************课程卡有效期定时器开始***********************");
			cardCourseValidTimeService.validTimeQuartz();
			logger.info("******************课程卡有效期定时器结束***********************");
		}catch(Exception e){
			logger.info("******************课程卡有效期定时器异常***********************");
			logger.error("CardValidTimeTask.validTimeQuartz", e);
		}
	}
}
