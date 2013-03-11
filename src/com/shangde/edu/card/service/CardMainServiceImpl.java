package com.shangde.edu.card.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.card.condition.QueryCardMainCondition;
import com.shangde.edu.card.domain.*;

public class CardMainServiceImpl extends BaseService implements CardMainService {
	private CardCourseService cardCourseService;//课程卡service

	public CardCourseService getCardCourseService() {
		return cardCourseService;
	}
	public void setCardCourseService(CardCourseService cardCourseService) {
		this.cardCourseService = cardCourseService;
	}
	/**
	 * 用于保存与课程卡相关的信息
	 */
	public void saveCardCourseInfo(CardMain cardMain,List<Integer> sellIds) throws Exception {
		//保存卡公用信息，以及将本次插入数据库中数据的主键set到cardMain中
		cardMain.setCardMainId(saveMainInfo(cardMain,sellIds));
		//保存本卡中包含的商品信息
		saveSell(cardMain,sellIds);
		//保存课程卡信息
		saveCardCourseDetail(cardMain, sellIds);
	}
	/**
	 * 课程卡，优惠卡，充值卡保存时，都先调用此方法保存他们的公共信息
	 * @param cardMain
	 * @return
	 */
	private Integer saveMainInfo(CardMain cardMain,List<Integer> sellIds){
		return simpleDao.createEntity("CardMain_NS.insert", cardMain);
	}
	/**
	 * 保存课程卡包含的商品
	 * @param cardMain
	 * @param sellIds
	 */
	private void saveSell(CardMain cardMain,List<Integer> sellIds){
		for(Integer sellId : sellIds){
			CardCourseSell cardCouseSell=new CardCourseSell();
			cardCouseSell.setCardMainId(cardMain.getCardMainId());
			cardCouseSell.setSellId(sellId);
			simpleDao.createEntity("CardCourseSell_NS.insert", cardCouseSell);
		}
	}
	/**
	 * 保存课程卡的详细信息
	 * @param cardMain
	 * @throws Exception
	 */
	private void saveCardCourseDetail(CardMain cardMain,List<Integer> sellIds) throws Exception{
		for(int i=0;i<cardMain.getCardCount();i++){
			cardCourseService.saveCourseCard(cardMain,sellIds);
		}
	}
	/**
	 * 获取课程卡详细信息
	 * @param cardCourse
	 * @return
	 * @throws Exception
	 */
	public PageResult getCardCourseMainList(
			QueryCardMainCondition queryCardMainCondition) throws Exception {
		return simpleDao.getPageResult("CardMain_NS.getCardMainList", "CardMain_NS.getCardMainCount", queryCardMainCondition);
	}
	/**
	 * 获取卡基本信息
	 * @param cardMain
	 * @return
	 * @throws Exception
	 */
	public CardMainModel getCardMainInfo(CardMain cardMain) throws Exception {
		return simpleDao.getEntity("CardMain_NS.getCardMainInfo", cardMain);
	}
	/**
	 * 更新卡基本信息
	 * @param cardMain
	 * @throws Exception
	 */
	public void updateCardMain(CardMain cardMain) throws Exception {
		simpleDao.updateEntity("CardMain_NS.updateCardMain", cardMain);
	}
	/**
	 * 作废课程基本卡时，必须要作废该基本卡所属的明细卡
	 * @param cardMain
	 * @throws Exception
	 */
	public void abolishCardCourseMain(CardMain cardMain) throws Exception {
		CardCourse cardCourse=new CardCourse();
		cardCourse.setCardMainId(cardMain.getCardMainId());
		cardCourse.setCardCourseStatus(4);//已作废
		//作废课程明细卡
		cardCourseService.abolishCardCourse(cardCourse);
		//作废课程基本卡，将cardUserStatus更新为3表示已作废
		updateCardMain(cardMain);
	}
	
	/**
	 * 获取当日已过期的卡
	 * @return
	 * @throws Exception
	 */
	public List<CardMain> getOutDateCard() throws Exception {
		return simpleDao.getForList("CardMain_NS.getOutDateCard", null);
	}
	/**
	 * 激活课程卡
	 * @param cardMain
	 * @throws Exception
	 */
	public void activateCardMain(CardMain cardMain,CardCourse cardCourse) throws Exception {
		cardCourse.setCardMainId(cardMain.getCardMainId());
		cardCourse.setCardCourseStatus(1);//表示已激活
		//更新课程卡明细信息
		cardCourseService.activateCardCourse(cardCourse);
		//更新课程卡主信息
		updateCardMain(cardMain);
	}
}
