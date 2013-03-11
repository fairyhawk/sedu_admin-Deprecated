package com.shangde.edu.card.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.card.condition.QueryCardCourseCondition;
import com.shangde.edu.card.domain.CardCourseModel;
import com.shangde.edu.card.domain.CardMain;
import com.shangde.edu.card.domain.CardCourse;

public class CardCourseServiceImpl extends BaseService implements
		CardCourseService {

	/**
	 * 保存课程卡
	 */
	public void saveCourseCard(CardMain cardMain,List<Integer> sellIds) throws Exception {
		CardCourse cardCourse=new CardCourse();
		//填充课程卡所需信息
		fillCouseCard(cardMain,cardCourse,sellIds);
		simpleDao.createEntity("CardCourse_NS.insert", cardCourse);
	}
	/**
	 * 填充课程卡所需信息
	 * @param cardMain
	 * @param couseCard
	 */
	private void fillCouseCard(CardMain cardMain,CardCourse cardCourse,List<Integer> sellIds){
		StringBuffer buffer=new StringBuffer();
		for(Integer itg:sellIds){
			buffer.append(itg+",");
		}
		if(sellIds.size()>0){
			cardCourse.setSellIds(buffer.deleteCharAt(buffer.length()-1).toString());
		}
		cardCourse.setCardMainId(cardMain.getCardMainId());
		cardCourse.setCardCourseStatus(2);//未激活
		cardCourse.setCardCourseCode(getRandomCardCode());//卡编码
		cardCourse.setCardCoursePassword(getRandomPassWord(8));//随机生成密码
		cardCourse.setCreator(cardMain.getCreator());
		cardCourse.setCreateTime(cardMain.getCreateTime());
		cardCourse.setUpdateTime(cardMain.getUpdateTime());
		cardCourse.setUpdateUser(cardMain.getUpdateUser());
		cardCourse.setCardCourseUseStatus(2);//为使用
	}
	/**
	 * 随机生成密码
	 * @param pwd_len
	 * @return
	 */
   private static String getRandomPassWord(int pwd_len){
       final int maxNum = 10;  
       int i; //生成的随机数  
       int count = 0; //生成的密码的长度  
       char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
       StringBuffer pwd = new StringBuffer("");  
       Random r = new Random();  
       while(count < pwd_len){
		   //生成随机数，取绝对值，防止生成负数，  
		   i = Math.abs(r.nextInt(maxNum)); 
		   if (i >= 0 && i < str.length) {  
			   pwd.append(str[i]);  
			   count ++;  
		   }  
       }  
      return pwd.toString();  
    }
   /**
    * 随机生成16位卡编号
    * @return
    */
   private String getRandomCardCode(){
	  DateFormat df=new SimpleDateFormat("yyyyMMdd");
	  Integer pk= simpleDao.getEntity("CardCourse_NS.getLastInsertId", null);
	  Integer count=10000001+pk;
	  return df.format(new Date())+count;
   }
	/**
	 * 查询课程列表
	 * @param queryCardCourseCondition
	 * @return
	 * @throws Exception
	 */
	public PageResult getCardCourseList(
			QueryCardCourseCondition queryCardCourseCondition) throws Exception {
		return simpleDao.getPageResult("CardCourse_NS.getCardCourseList", "CardCourse_NS.getCardCourseCount", queryCardCourseCondition);
	}
	/**
	 * 作废课程卡
	 */
	public void abolishCardCourse(CardCourse cardCourse) throws Exception {
		simpleDao.update("CardCourse_NS.abolishCardCourse", cardCourse);
		//当作废时，更新订单状态为取消
		updateOrderStatus(cardCourse);
	}
	/**
	 * 根据CARD_MAIN_ID 或者 CARD_COURSE_ID查询出订单编号,通过订单编号更新订单状态
	 * @param cardCourse
	 */
	private void  updateOrderStatus(CardCourse cardCourse) throws Exception{
		List<String> orderCodeList= getOrderCodeList(cardCourse);
		if(orderCodeList.size()>0){
			Map<String,List<String>>orderCodeListMap=new HashMap<String,List<String>>();
			orderCodeListMap.put("orderCodeList", orderCodeList);
			simpleDao.update("CardCourse_NS.updateOrderStauts", orderCodeListMap);
		}
	}
	/**
	 * 获取课程卡详细信息
	 * @param cardCourse
	 * @return
	 * @throws Exception
	 */
	public CardCourseModel getCardCourseDetail(CardCourse cardCourse)
			throws Exception {
		return simpleDao.getEntity("CardCourse_NS.getCardCourseDetail", cardCourse);
	}
	/**
	 * 判断当日过期的课程卡，并进行更新
	 * @throws Exception
	 */
	public void updateCardCourseOutDate(CardMain cardMain,CardCourse cardCourse) throws Exception {
		cardCourse.setCardMainId(cardMain.getCardMainId());
		cardCourse.setCardCourseStatus(3);
		simpleDao.updateEntity("CardCourse_NS.updateCardCourseStatus", cardCourse);
	}
	/**
	 * 获取订单编号列表
	 * @param cardCourse
	 * @return
	 * @throws Exception
	 */
	public List<String> getOrderCodeList(CardCourse cardCourse)
			throws Exception {
		return simpleDao.getForList("CardCourse_NS.getOrderCodeList", cardCourse);
	}
	/**
	 * 激活课程卡明细信息
	 * @param cardCourse
	 * @throws Exception
	 */
	public void activateCardCourse(CardCourse cardCourse) throws Exception {
		simpleDao.updateEntity("CardCourse_NS.updateCardCourseStatus", cardCourse);
	}
	
}
