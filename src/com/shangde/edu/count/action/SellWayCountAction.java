package com.shangde.edu.count.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.count.condition.QueryContractCountCondition;
import com.shangde.edu.count.condition.QuerySellWayCountCondition;
import com.shangde.edu.count.dto.SellWayCountDTO;
import com.shangde.edu.count.dto.SellWayCountNewDTO;
import com.shangde.edu.count.dto.SellWayDTO;
import com.shangde.edu.count.service.IContractCount;
import com.shangde.edu.count.service.ISellWayCount;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

public class SellWayCountAction {
	
	private static final Logger logger = Logger.getLogger(SellWayCountAction.class);
	
	private ISellWayCount sellWayCountService;

	private IContractCount contractCountService;

	private QuerySellWayCountCondition querySellWayCountCondition;

	private QueryContractCountCondition queryContractCountCondition;

	private ISubject subjectService;

	private QuerySubjectCondition querySubjectCondition;

	private List<Subject> subjectList;

	private List<SellWayDTO> sellWayList;

	private SellWayCountDTO sellWayCountDTO;

	private List<SellWayCountDTO> sellWayCountList;
	private List<SellWayDTO> WFsellWayCountList;

	private int SUM = 0; // 所有订单总数

	private int zsSum = 0; // 赠送订单总数

	private int zfbSum = 0; // 支付宝订单总数

	private int hdfkSum = 0; // 货到付款订单总数

	private int wyzxSum = 0; // 网银在线订单总数

	private int kqSum = 0; // 块钱订单总数

	private int TFSUM = 0; // 退费总数

	private int WFSUM = 0; // 未付款总数

	private int wfZfbSum = 0; // 未付款支付宝订单总数

	private int wfHdfkSum = 0;// 未付货到付款总数

	private int wfWyzxSum = 0; // 未付网银在线总数

	private int wfKqSum = 0; // 未付块钱总数

	private int sellPriceSum = 0; // 售卖方式价格总和

	private int YFSUM = 0; // 专业已付总数

	private int location; // 当天 一周 一个月 三个月 参数

	private int subjectId;

	private String startTime; // 自定义查询 开始年月日
	private String startHH = " 00:00:00"; // 自定义查询 开始时分秒
	private String endTime; // 自定义 结束年月日
	private String endHH = " 59:59:59"; // 自定义 结束时分秒
	
	private float convertSum = 0f;

	private float yfConvertSum = 0f;
	
	private int i=0;
	
	List<SellWayCountNewDTO> sellWayListNew;//商品优化
	
	public List<SellWayCountNewDTO> getSellWayListNew() {
		return sellWayListNew;
	}

	public void setSellWayListNew(List<SellWayCountNewDTO> sellWayListNew) {
		this.sellWayListNew = sellWayListNew;
	}

	private List<Subject> getAllSubject() {
		return subjectList = subjectService.getAllSubject();
	}

	public String getSellWayBySubjectId() {
		try{
			getAllSubject();
			HttpServletRequest request=ServletActionContext.getRequest();
			if(request.getParameter("l")!=null){
				location=Integer.valueOf(request.getParameter("l").toString());				
			}
			getSelectTime();
			if (this.getQuerySellWayCountCondition().getSubjectId() == 0) {
				this.getQuerySellWayCountCondition().setSubjectId(1);
			}
			sellWayListNew=this.sellWayCountService.getSellWayNew(querySellWayCountCondition);
//			initDateTJ();
			return "sellWayInfo";
		}catch(Exception e){
			logger.error("SellWayCountAction.getSellWayBySubjectId",e);
			return "error";
		}
		
	}
	
	//恢复数据原来格式
	private void initDateTJ() {
		 if(startTime!=null&&!"".equals(startTime)){
			 startTime=startTime.substring(0,startTime.indexOf(startHH));
		 }
		 if(endTime!=null&&!"".equals(endTime)){
			 endTime=endTime.substring(0,endTime.indexOf(endHH));
		 }
	}

	private void getSellWayCountSum(int sellId, int subjectId,
			String subjectName, String sellWayName) {
		this.getQueryContractCountCondition().setSubjectId(subjectId);
		Integer cusCount = contractCountService
				.getCustomerCount(this.queryContractCountCondition);
		this.getQuerySellWayCountCondition().setSellWayId(sellId);
		this.getQuerySellWayCountCondition().setSubjectId(subjectId);
		this.getQuerySellWayCountCondition().setStatus(0);
		Integer cusContractCount = sellWayCountService
				.getCustomerContractCountBySellId(querySellWayCountCondition);
		this.getQuerySellWayCountCondition().setSellWayId(sellId);
		this.getQuerySellWayCountCondition().setSubjectId(subjectId);
		sellWayList = sellWayCountService
				.getSellWayCount(this.querySellWayCountCondition);

		this.sellWayCountDTO = new SellWayCountDTO();
		int GIVE = 0;
		int ZFB = 0;
		int POST = 0;
		int CB = 0;
		int KQ = 0;
		int SUM = 0;
		int zsSum = 0;
		int zfbSum = 0;
		int hdfkSum = 0;
		int wyzxSum = 0;
		int kqSum = 0;
		int sellWaySum = 0;
		int convertSum = 0;
		float convert = 0.00f;
		int yfPrice = 0;
		int ctlSize = sellWayList.size();
		for (int i = 0; i < ctlSize; i++) {
			SellWayDTO sellWayDTO = sellWayList.get(i);

			if (sellWayDTO.getPayType() == 0
					&& sellWayDTO.getSubjectId() == subjectId) { // 赠送订单处理
				SUM++;
				GIVE++; // 当前专业赠送总数
				zsSum++; // 所有专业赠送总数
				sellWaySum++;
				this.SUM++;
				this.zsSum++;
			} else if (sellWayDTO.getPayType() == 1
					&& sellWayDTO.getSubjectId() == subjectId) { // 支付宝订单处理
				yfPrice=getYFContractCount(i, yfPrice);
				SUM++;
				ZFB++;
				zfbSum++;
				sellWaySum++;
				convertSum++;
				this.SUM++;
				this.zfbSum++;
			} else if (sellWayDTO.getPayType() == 2
					&& sellWayDTO.getSubjectId() == subjectId) { // 货到付款订单处理
				yfPrice=getYFContractCount(i, yfPrice);
				SUM++;
				POST++;
				hdfkSum++;
				sellWaySum++;
				convertSum++;
				this.hdfkSum++;
				this.SUM++;
			} else if (sellWayDTO.getPayType() == 3
					&& sellWayDTO.getSubjectId() == subjectId) { // 网银在线订单处理
				yfPrice=getYFContractCount(i, yfPrice);
				SUM++;
				CB++;
				wyzxSum++;
				sellWaySum++;
				convertSum++;
				this.SUM++;
				this.wyzxSum++;
			} else if (sellWayDTO.getPayType() == 4
					&& sellWayDTO.getSubjectId() == subjectId) { // 快钱订单处理
				yfPrice=getYFContractCount(i, yfPrice);
				SUM++;
				KQ++;
				kqSum++;
				sellWaySum++;
				convertSum++;
				this.SUM++;
				this.kqSum++;
			}
		}
		this.sellWayCountDTO.setZsSum(GIVE); // 当前专业赠送总数
		this.sellWayCountDTO.setZfbSum(ZFB); // 当前专业支付宝总数
		this.sellWayCountDTO.setWyzxSum(CB); // 当前专业网银在线总数
		this.sellWayCountDTO.setKqSum(KQ); // 当前专业会计总数
		this.sellWayCountDTO.setHdfkSum(POST); // 当前专业货到付款总数
		this.sellWayCountDTO.setSubjectContractSum(sellWaySum); // 当前专业 订单总数
		this.sellWayCountDTO.setZfbSum(zfbSum); // 所有专业支付宝总计
		this.sellWayCountDTO.setContractSum(SUM); // 所有专业 订单总计
		this.sellWayCountDTO.setKqSum(kqSum); // 所有专业快钱总计
		this.sellWayCountDTO.setWyzxSum(wyzxSum); // 所有专业网银在线总计
		this.sellWayCountDTO.setZsSum(zsSum); // 赠送总计
		this.sellWayCountDTO.setZfbSum(zfbSum); // 支付宝总计
		this.sellWayCountDTO.setHdfkSum(hdfkSum); // 货到付款总计
		this.sellWayCountDTO.setSubjectName(subjectName);
		this.sellWayCountDTO.setConvertSum(convertSum);
		this.sellWayCountDTO.setSellPriceSum(yfPrice);
		this.sellWayCountDTO.setZjSum(this.SUM);
		this.sellWayCountDTO.setZjHdfkSum(this.hdfkSum);
		this.sellWayCountDTO.setZjZfbSum(this.zfbSum);
		this.sellWayCountDTO.setZjWyzxSum(this.wyzxSum);
		this.sellWayCountDTO.setZjKqSum(this.kqSum);
		this.sellWayCountDTO.setZjZsSum(this.zsSum);
		this.sellWayCountDTO.setZjSellPriceSum(sellPriceSum);
		this.sellWayCountDTO.setSellName(sellWayName);

		if (cusContractCount != 0) { // 此处为注册 订单转化率，当前这些注册人数中有多少下了订单/注册人数
			convert = (cusContractCount * 1000) / cusCount;
			convert = convert / 10;
			this.convertSum += convert;
			i++;
		}
		this.sellWayCountDTO.setConvertContract(String.valueOf(convert));
		
		this.getQuerySellWayCountCondition().setStatus(1);
		Integer YFcusContractCount = sellWayCountService
				.getCustomerContractCountBySellId(this.querySellWayCountCondition); // 得到当前注册人数中
		if (YFcusContractCount != 0) { // 支付转化率，支付订单/当前全部订单
			convert = (YFcusContractCount * 1000) / cusContractCount;
			convert = convert / 10;
			this.yfConvertSum +=convert;
		}
		
		this.sellWayCountDTO.setYFconverrContract(String.valueOf(convert));
		
		this.sellWayCountDTO.setYFconverrContract(String.valueOf(convert));
		String str = String.valueOf(this.convertSum);

		this.sellWayCountDTO.setZjConvertSum(str.substring(0,
				str.indexOf(".") + 2));
		str = String.valueOf(this.yfConvertSum / i);
		this.sellWayCountDTO.setZjYfConvertSum(str.substring(0, str
				.indexOf(".") + 2));
	}

	private int getYFContractCount(int index, int yfPrice) {
		SellWayDTO sellWayDTO = sellWayList.get(index);
		if (sellWayDTO.getStatus() == 1) {
			sellPriceSum += sellWayDTO.getSellPrice();
			yfPrice += sellWayDTO.getSellPrice();
			YFSUM++;
		}
		return yfPrice;
	}

	private void getWFSellWayCountSum(int sellWayId, int subjectId) {
		this.getQuerySellWayCountCondition().setSubjectId(subjectId);
		this.getQuerySellWayCountCondition().setSellWayId(sellWayId);
		WFsellWayCountList = this.sellWayCountService
				.getWFSellWayCount(querySellWayCountCondition);
		// 参数为 未付的 支付宝 块钱 网银 货到付款 计数
		int WFZFB = 0;
		int WFPOST = 0;
		int WFCB = 0;
		int WFKQ = 0;
		int WfContractSum = 0; // 当前专业的未付总和
		int TF = 0; // 退费数
//		int wfPrice = 0; // 退费的钱数 暂时未用到 但是方法已经计算了 退费的金额
		if (WFsellWayCountList != null) {
			int ctlSize = WFsellWayCountList.size();
			for (int i = 0; i < ctlSize; i++) {
				SellWayDTO sellWayDTO = WFsellWayCountList.get(i);
				if (sellWayDTO.getPayType() == 1
						&& sellWayDTO.getSubjectId() == subjectId) { // 支付宝订单处理
					if (sellWayDTO.getStatus() == 3) {
						TF++;
						TFSUM++;
					} else {
						WFZFB++;
						wfZfbSum++;
						WfContractSum++; // 当前专业未付款总数
						WFSUM++;
					}
				} else if (sellWayDTO.getPayType() == 2
						&& sellWayDTO.getSubjectId() == subjectId) { // 货到付款订单处理
					if (sellWayDTO.getStatus() == 3) {
						TF++;
						TFSUM++;
					} else {
						WFPOST++;
						wfHdfkSum++;
						WfContractSum++; // 当前专业未付款总数
						WFSUM++;
					}
				} else if (sellWayDTO.getPayType() == 3
						&& sellWayDTO.getSubjectId() == subjectId) { // 网银在线订单处理
					if (sellWayDTO.getStatus() == 3) {
						TF++;
						TFSUM++;
					} else {
						WFCB++;
						wfWyzxSum++;
						WfContractSum++; // 当前专业未付款总数
						WFSUM++;
					}
				} else if (sellWayDTO.getPayType() == 4
						&& sellWayDTO.getSubjectId() == subjectId) { // 快钱订单处理
					if (sellWayDTO.getStatus() == 3) {
						TF++;
						TFSUM++;
					} else {
						WFKQ++;
						wfKqSum++;
						WfContractSum++; // 当前专业未付款总数
						WFSUM++;
					}
				}
			}
		}
		this.sellWayCountDTO.setYfZfbSum(WFZFB); // 当前 已付专业支付宝总数
		this.sellWayCountDTO.setYfWyzxSum(WFCB); // 当前专业 已付网银在线总数
		this.sellWayCountDTO.setYfKqSum(WFKQ); // 当前专业 已付快钱总数
		this.sellWayCountDTO.setYfHdfkSum(WFPOST); // 当前专业货到付款 总数
		this.sellWayCountDTO.setYfSubjectContractSum(WfContractSum); // 当前专业
		this.sellWayCountDTO.setTfSum(TFSUM);
		this.sellWayCountDTO.setTF(TF);
		this.sellWayCountDTO.setZjSellPriceSum(sellPriceSum);
		this.sellWayCountDTO.setZjWfSum(WFSUM);
		this.sellWayCountDTO.setZjWfHdfkSum(wfHdfkSum);
		this.sellWayCountDTO.setZjWfZfbSum(wfZfbSum);
		this.sellWayCountDTO.setZjWfKqSum(wfKqSum);
		this.sellWayCountDTO.setZjWfWyzxSum(wfWyzxSum);

	}

	private void getTime() {

//		if (startTime != null) { // 如果初次查询时间不为空 则添加查询条件 即 当天时间
//			if (startTime.length() != 0) {
//				startTime +=startHH;
//				this.getQuerySellWayCountCondition().setStartCountTime(startTime);
//				this.getQueryContractCountCondition().setStartCountTime(startTime);
//			}
//		}
//		if (endTime != null) {
//			if (endTime.length() != 0) {
//				endTime += endHH;
//				this.getQuerySellWayCountCondition().setEndCountTime(endTime);
//				this.getQueryContractCountCondition().setEndCountTime(endTime);
//			}
//		}
		if (startTime != null) { // 如果初次查询时间不为空 则添加查询条件 即 当天时间
			if (startTime.length() != 0) {
				String startTimeNew=startTime+startHH;
//				startTime +=startHH;
//				this.getQueryContractCountCondition().setStartCountTime(startTime);
				this.getQuerySellWayCountCondition().setStartCountTime(startTimeNew);
			}
		}
		if (endTime != null) {
			if (endTime.length() != 0) {
				String endTimeNew=endTime+endHH;
//				endTime +=endHH;
//				this.getQueryContractCountCondition().setEndCountTime(endTime);
				this.getQuerySellWayCountCondition().setEndCountTime(endTimeNew);
			}
		}
	}

	/**
	 * @author wangzheng 学员统计 附加时间查询条件 当天 一周 当月 三月
	 */
	private void getSelectTime() {
		getTime();
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
		Calendar todayDate = Calendar.getInstance(); // 当天时间对象
		Calendar weekDate = Calendar.getInstance(); // 周时间对象
		Calendar monthDate = Calendar.getInstance(); // 当月时间对象
		Calendar threeMonthDate = Calendar.getInstance(); // 三个月时间对象

		Date today = todayDate.getTime(); // 获取时间
		String todayStrartTime = dateFm.format(today); // 格式化当天开始时间
		String todayEndTime = dateFm.format(today); // 格式化当天结束时间
		todayStrartTime = todayStrartTime + startHH; // 完整开始时间
		todayEndTime = todayEndTime + endHH; // 完整结束时间

//		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		weekDate.add(Calendar.DAY_OF_MONTH, -7); // 一周时间处理
		Date week = weekDate.getTime();
		String weekStartTime = dateFmt.format(week);

		monthDate.add(Calendar.MONTH, -1); // 一月时间处理
		Date month = monthDate.getTime();
		String monthStartTime = dateFmt.format(month);

		threeMonthDate.add(Calendar.MONTH, -3); // 三月时间处理
		Date threeMonth = threeMonthDate.getTime();
		String threeMonthStartTime = dateFmt.format(threeMonth);
		if (location == 1) { // 当天查询条件
			this.getQuerySellWayCountCondition().setStartCountTime(
					todayStrartTime);
			this.getQuerySellWayCountCondition().setEndCountTime(todayEndTime);
		} else if (location == 2) { // 一周查询条件
			this.getQuerySellWayCountCondition().setStartCountTime(
					weekStartTime+startHH);
			this.getQuerySellWayCountCondition().setEndCountTime(
					todayEndTime);

		} else if (location == 3) {

			this.getQuerySellWayCountCondition().setStartCountTime(
					monthStartTime+startHH);
			this.getQuerySellWayCountCondition().setEndCountTime(
					todayEndTime);
		} else if (location == 4) {

			this.getQuerySellWayCountCondition().setStartCountTime(
					threeMonthStartTime+startHH);
			this.getQuerySellWayCountCondition().setEndCountTime(
					todayEndTime);
		} else if (location == 0) {

			this.getQuerySellWayCountCondition().setStartCountTime("");
			this.getQuerySellWayCountCondition().setEndCountTime("");
			this.getQuerySellWayCountCondition().setPayType(-1);
		}
	}

	public ISellWayCount getSellWayCountService() {
		return sellWayCountService;
	}

	public void setSellWayCountService(ISellWayCount sellWayCountService) {
		this.sellWayCountService = sellWayCountService;
	}

	public QuerySellWayCountCondition getQuerySellWayCountCondition() {
		if (this.querySellWayCountCondition == null) {
			this.querySellWayCountCondition = new QuerySellWayCountCondition();
		}
		return querySellWayCountCondition;
	}

	public void setQuerySellWayCountCondition(
			QuerySellWayCountCondition querySellWayCountCondition) {
		this.querySellWayCountCondition = querySellWayCountCondition;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public QuerySubjectCondition getQuerySubjectCondition() {
		if (querySubjectCondition == null) {
			this.querySubjectCondition = new QuerySubjectCondition();
		}
		return querySubjectCondition;
	}

	public void setQuerySubjectCondition(
			QuerySubjectCondition querySubjectCondition) {
		this.querySubjectCondition = querySubjectCondition;
	}

	public List<Subject> getSubjectList() {
		if (subjectList == null) {
			subjectList = new ArrayList<Subject>();
		}
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public SellWayCountDTO getSellWayCountDTO() {
		if (sellWayCountDTO == null) {
			this.sellWayCountDTO = new SellWayCountDTO();
		}
		return sellWayCountDTO;
	}

	public void setSellWayCountDTO(SellWayCountDTO sellWayCountDTO) {
		this.sellWayCountDTO = sellWayCountDTO;
	}

	public List<SellWayDTO> getSellWayList() {
		if (sellWayList == null) {
			this.sellWayList = new ArrayList<SellWayDTO>();
		}
		return sellWayList;
	}

	public void setSellWayList(List<SellWayDTO> sellWayList) {
		this.sellWayList = sellWayList;
	}

	public List<SellWayCountDTO> getSellWayCountList() {
		if (this.sellWayCountList == null) {
			this.sellWayCountList = new ArrayList<SellWayCountDTO>();
		}
		return sellWayCountList;
	}

	public void setSellWayCountList(List<SellWayCountDTO> sellWayCountList) {
		this.sellWayCountList = sellWayCountList;
	}

	public List<SellWayDTO> getWFsellWayCountList() {
		return WFsellWayCountList;
	}

	public void setWFsellWayCountList(List<SellWayDTO> fsellWayCountList) {
		WFsellWayCountList = fsellWayCountList;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndHH() {
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public IContractCount getContractCountService() {
		return contractCountService;
	}

	public void setContractCountService(IContractCount contractCountService) {
		this.contractCountService = contractCountService;
	}

	public QueryContractCountCondition getQueryContractCountCondition() {
		if (this.queryContractCountCondition == null) {
			this.queryContractCountCondition = new QueryContractCountCondition();
		}
		return queryContractCountCondition;
	}

	public void setQueryContractCountCondition(
			QueryContractCountCondition queryContractCountCondition) {
		this.queryContractCountCondition = queryContractCountCondition;
	}

}
