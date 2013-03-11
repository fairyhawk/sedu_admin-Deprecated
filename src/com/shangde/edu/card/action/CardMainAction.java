package com.shangde.edu.card.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.DateUtil;
import com.shangde.common.util.FileExportImportUtil;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.card.condition.*;
import com.shangde.edu.card.domain.*;
import com.shangde.edu.card.dto.CardCourseDTO;
import com.shangde.edu.card.service.*;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.DicCodeService;

/**
 * 优惠卡，充值卡，课程卡都通过该action来管理
 * @author dks
 *
 */
public class CardMainAction extends CommonAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CardMainAction.class); 
	private CardMainService cardMainService;
	private CardMain cardMain;
	private Integer sellId;//商品ID
	private CardCourseService cardCourseService;//课程卡service
	private List<Integer> sellIds=new ArrayList<Integer>();//获取商品ID的集合
	private ISellWay sellWayService;//商品service
	private QueryCardCourseCondition  queryCardCourseCondition;//课程卡查询条件
	private Map<String,String> cardStatusMap=new HashMap<String,String>();//课程卡状态MAP
	private DicCodeService dicCodeService;//字典表service
	private CardCourse cardCourse;
	private String exportName;
	private InputStream excelFile;
	private CardCourseModel cardCourseModel;//用于课程卡详细页面显示
	private List<SellWay>sellWayList=new ArrayList<SellWay>();//存储商品
	private QueryCardMainCondition queryCardMainCondition;//卡基本信息查询条件
	private Map<String,String> cardUserStatusMap;//后台人员操作状态
	private CardMainModel cardMainModel;//用于卡基本信息页面显示model
	private Map<String,String>cardCourseUseStatusMap;//课程卡使用状态
	public Map<String, String> getCardCourseUseStatusMap() {
		return cardCourseUseStatusMap;
	}
	public void setCardCourseUseStatusMap(Map<String, String> cardCourseUseStatusMap) {
		this.cardCourseUseStatusMap = cardCourseUseStatusMap;
	}
	public CardMainModel getCardMainModel() {
		return cardMainModel;
	}
	public void setCardMainModel(CardMainModel cardMainModel) {
		this.cardMainModel = cardMainModel;
	}
	public QueryCardMainCondition getQueryCardMainCondition() {
		return queryCardMainCondition;
	}
	public void setQueryCardMainCondition(
			QueryCardMainCondition queryCardMainCondition) {
		this.queryCardMainCondition = queryCardMainCondition;
	}
	public Map<String, String> getCardUserStatusMap() {
		return cardUserStatusMap;
	}
	public void setCardUserStatusMap(Map<String, String> cardUserStatusMap) {
		this.cardUserStatusMap = cardUserStatusMap;
	}
	public List<SellWay> getSellWayList() {
		return sellWayList;
	}
	public void setSellWayList(List<SellWay> sellWayList) {
		this.sellWayList = sellWayList;
	}
	public CardCourseModel getCardCourseModel() {
		return cardCourseModel;
	}
	public void setCardCourseModel(CardCourseModel cardCourseModel) {
		this.cardCourseModel = cardCourseModel;
	}
	public String getExportName() {
		return exportName;
	}
	public void setExportName(String exportName) {
		this.exportName = exportName;
	}
	public InputStream getExcelFile() {
		return excelFile;
	}
	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}
	public CardCourse getCardCourse() {
		return cardCourse;
	}
	public void setCardCourse(CardCourse cardCourse) {
		this.cardCourse = cardCourse;
	}
	public DicCodeService getDicCodeService() {
		return dicCodeService;
	}
	public void setDicCodeService(DicCodeService dicCodeService) {
		this.dicCodeService = dicCodeService;
	}
	public Map<String, String> getCardStatusMap() {
		return cardStatusMap;
	}
	public void setCardStatusMap(Map<String, String> cardStatusMap) {
		this.cardStatusMap = cardStatusMap;
	}
	public QueryCardCourseCondition getQueryCardCourseCondition() {
		return queryCardCourseCondition;
	}
	public void setQueryCardCourseCondition(
			QueryCardCourseCondition queryCardCourseCondition) {
		this.queryCardCourseCondition = queryCardCourseCondition;
	}
	public ISellWay getSellWayService() {
		return sellWayService;
	}
	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}
	

	public CardCourseService getCardCourseService() {
		return cardCourseService;
	}
	public void setCardCourseService(CardCourseService cardCourseService) {
		this.cardCourseService = cardCourseService;
	}
	public List<Integer> getSellIds() {
		return sellIds;
	}
	public void setSellIds(List<Integer> sellIds) {
		this.sellIds = sellIds;
	}
	public Integer getSellId() {
		return sellId;
	}
	public void setSellId(Integer sellId) {
		this.sellId = sellId;
	}
	public CardMainService getCardMainService() {
		return cardMainService;
	}
	public CardMain getCardMain() {
		return cardMain;
	}
	public void setCardMain(CardMain cardMain) {
		this.cardMain = cardMain;
	}
	public void setCardMainService(CardMainService cardMainService) {
		this.cardMainService = cardMainService;
	}
	/**
	 * 进入新建课程卡页面
	 * @return
	 */
	public String getCourseCardPage(){
		return "courseCardInfo";
	}
	/**
	 * 课程卡保存
	 * @return
	 */
	public String saveCourseCardInfo(){
		try{
			//填充页面中获取不到的cardMain信息
			fillCardMain();
			//保存课程卡
			getCardMainService().saveCardCourseInfo(cardMain,sellIds);
		}catch(Exception e){
			logger.error("CardMainAction.saveCourseCardInfo", e);
			return ERROR;
		}
		return "saveCourseCardInfo";
	}
	/**
	 * 填充页面中获取不到的cardMain信息
	 */
	private void fillCardMain(){
		User user = (User) servletRequest.getSession().getAttribute(
				"sedu_user");
		cardMain.setCardUserStatus(2);
		cardMain.setCreator(user.getUserName());
		Date date=new Date(System.currentTimeMillis());
		cardMain.setCreateTime(date);
		cardMain.setUpdateUser(user.getUserName());
		cardMain.setUpdateTime(date);
	}
	/**
	 * 获取商品对象
	 * @return
	 */
	public String getSellById(){
		try{
			setResult(new Result<SellWay>(false, "", "", sellWayService.getSellWayById(sellId)));
		}catch(Exception e){
			logger.error("CardMainAction.getSellById", e);
			return ERROR;
		}
		return "getSellById";
	}
	/**
	 * 获取课程卡明细列表
	 * @return
	 */
	public String getCardCourseList(){
		try{
			//设定查询条件中的课程卡状态的值
			setCardStatusMap(dicCodeService.getDicByType("CARD_COURSE_STATUS"));
			//设定查询条件中的课程卡使用状态的值
			setCardCourseUseStatusMap(dicCodeService.getDicByType("CARD_COURSE_USE_STATUS"));
			//查询卡
			setPage(cardCourseService.getCardCourseList(queryCardCourseCondition));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("CardMainAction.getCardCourseList", e);
			return ERROR;
		}
		
		return "getCardCourseList";
	}
	/**
	 * 作废课程卡
	 * @return
	 */
	public String abolishCardCourse(){
		try{
			User user = (User) servletRequest.getSession().getAttribute(
					"sedu_user");
			cardCourse.setUpdateUser(user.getUserName());
			cardCourse.setUpdateTime(new Date(System.currentTimeMillis()));
			cardCourseService.abolishCardCourse(cardCourse);
		}catch(Exception e){
			logger.error("CardMainAction.abolishCardCourse", e);
			return ERROR;
		}
		return null;
	}
	/**
	 * excel导出
	 * @return
	 */
	public String exportCardCouseDetail(){
		try{
			//设置导出excel文件名称
			this.addExpName("课程卡列表");
			//设定允许最多导出条数
			this.setExportPageSize(10000);
			List<List<String>> list = new ArrayList<List<String>>();
			//将表头字段放入list中
			list.add(this.getExcelHeadInfo());
			//取得需要填充至Excel中的详细信息
			this.getExcelDetailInfo(cardCourseList(),list);
			FileExportImportUtil export = new FileExportImportUtil();
			InputStream is = export.export(list);
			setExcelFile(is);
			return "exportCardCourse";
		}catch(Exception e){
			logger.error("CardMainAction.exportCSV",e);
			return ERROR;
		}
		
	}
	
	private void addExpName(String expNameStr) throws UnsupportedEncodingException{
		String expName=expNameStr + DateUtil.getCurrentDate() + ".xls";
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
				 setExportName(URLEncoder.encode(expName, "UTF-8"));//IE浏览器
			 }else{
				 setExportName(new String((expName).getBytes(),"iso-8859-1"));
			 }
		
	}
	private void setExportPageSize(int size){
		queryCardCourseCondition.setPageSize(size);
	}
	
	private List<String> getExcelHeadInfo(){
		List<String> headList = new ArrayList<String>();
		headList.add("课程卡名称");
		headList.add("课程卡编号");
		headList.add("课程卡密码");
		headList.add("商品ID");
		headList.add("生成时间");
		headList.add("金额");
		headList.add("创建人");
		headList.add("激活人");
		headList.add("有效期");
		headList.add("用户账号");
		headList.add("使用状态");
		headList.add("课程卡状态");
		headList.add("备注");
		return headList;
	}
	private List<CardCourseDTO> cardCourseList() throws Exception{
		PageResult result = cardCourseService.getCardCourseList(queryCardCourseCondition);
		return result.getPageResult();
	}
	private List<List<String>>getExcelDetailInfo(List<CardCourseDTO> cardCourseList,List<List<String>> list){
		List<String> tempList=null;
		java.text.DateFormat df=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(CardCourseDTO cardCourseDTO:cardCourseList){
			tempList=new ArrayList<String>();
			tempList.add(cardCourseDTO.getCardName());
			tempList.add(cardCourseDTO.getCardCourseCode());
			tempList.add(cardCourseDTO.getCardCoursePassword());
			tempList.add(cardCourseDTO.getSellIds());
			tempList.add(df.format(cardCourseDTO.getCreateTime()));
			tempList.add(cardCourseDTO.getCardMoney().toString());
			tempList.add(cardCourseDTO.getCreator());
			tempList.add(cardCourseDTO.getActivateUser());
			tempList.add(df.format(cardCourseDTO.getValidBeginTime())+" - "+df.format(cardCourseDTO.getValidEndTime()));
			tempList.add(cardCourseDTO.getUserAccount());
			tempList.add(cardCourseDTO.getCardCourseUseStatusContent());
			tempList.add(cardCourseDTO.getCardCourseStatusContent());
			tempList.add(cardCourseDTO.getRemark());
			list.add(tempList);
		}
		return list;
	}
	/**
	 * 获取单张课程卡详细信息
	 * @return
	 */
	public String getCardCourseDetail(){
		try{
			//获取课程卡详细信息
			setCardCourseModel(cardCourseService.getCardCourseDetail(cardCourse));
			//获取商品列表用于页面迭代显示
			setSellWayList(sellWayService.getCardCourseSell(cardCourseModel.getCardMainId()));
		}catch(Exception e){
			logger.error("CardMainAction.getCardCourseDetail", e);
			return ERROR;
		}
		return "getCardCourseDetail";
	}
	/**
	 * 获取卡列表基本信息
	 * @return
	 */
	public String getCardMainList(){
		try{
			setCardUserStatusMap(dicCodeService.getDicByType("CARD_USER_STATUS"));
			setPage(cardMainService.getCardCourseMainList(queryCardMainCondition));
			setPageUrlParms();
		}catch(Exception e){
			logger.error("CardMainAction.getCardCourseMainList", e);
			return ERROR;
		}
		return "getCardCourseMainList";
	}
	public String getCardCourseMainInfo(){
		try{
			//获取卡基本信息
			getCardMainInfo(cardMain);
			//获取商品信息
			setSellWayList(sellWayService.getCardCourseSell(cardMain.getCardMainId()));
		}catch(Exception e){
			logger.error("CardMainAction.getCardCourseMainInfo", e);
			return ERROR;
		}
		return "getCardCourseMainInfo";
	}
	/**
	 * 获取卡基本信息
	 * @return
	 */
	public void getCardMainInfo(CardMain cardMain) throws Exception{
			setCardMainModel(cardMainService.getCardMainInfo(cardMain));
	}
	/**
	 * 更新卡基本信息
	 * @return
	 */
	public String activateCardMain(){
		try {
			User user = (User) servletRequest.getSession().getAttribute(
					"sedu_user");
			cardMain.setActivateUser(user.getUserName());
			cardMainService.activateCardMain(cardMain,new CardCourse());
		}catch(Exception e){
			logger.error("CardMainAction.updateCardMain", e);
			return ERROR;
		}
		return null;
	}
	/**
	 * 作废课程卡
	 * @return
	 */
	public String abolishCardCourseMain(){
		try{
			User user = (User) servletRequest.getSession().getAttribute(
					"sedu_user");
			cardMain.setActivateUser(user.getUserName());
			cardMainService.abolishCardCourseMain(cardMain);
		}catch(Exception e){
			logger.error("CardMainAction.abolishCardMain", e);
			return ERROR;
		}
		return null;
	}
	
	public void updateCardCourseOutDate(){
		try{
			List<CardMain> cardMainList=cardMainService.getOutDateCard();
			for(CardMain cardMain : cardMainList){
				cardCourseService.updateCardCourseOutDate(cardMain,new CardCourse());
			}
		}catch(Exception e){
			logger.error("课程卡定时器错误", e);
		}
	}
	
}
