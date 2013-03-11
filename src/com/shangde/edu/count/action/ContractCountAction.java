package com.shangde.edu.count.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.shangde.edu.count.condition.QueryContractCountCondition;
import com.shangde.edu.count.dto.ContractCountDTO;
import com.shangde.edu.count.dto.ContractCountNewDTO;
import com.shangde.edu.count.dto.ContractDTO;
import com.shangde.edu.count.service.IContractCount;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

/**
 * 订单统计类
 * 
 * @author wangzheng
 * 
 */
public class ContractCountAction {
	
	private static final Logger logger = Logger.getLogger(ContractCountAction.class);
	/**
	 * 专业接口
	 */
	private ISubject subjectService;

	/**
	 * 订单统计接口
	 */
	private IContractCount contractCountService;

	/**
	 * 订单数量对象
	 */
	private ContractCountDTO contractCountDTO;

	/**
	 * 订单统计附加条件
	 */
	private QueryContractCountCondition queryContractCountCondition;

	/**
	 * 所有订单集合
	 */
	private List<ContractDTO> contractList;

	/**
	 * 所有已付订单集合
	 */
	private List<ContractDTO> WFcontractList;

	/**
	 * 所有结果集 集合
	 */
	private List<ContractCountDTO> contractCountList;

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

	private String startTime; // 自定义查询 开始年月日
	private String startHH = " 00:00:00"; // 自定义查询 开始时分秒
	private String endTime; // 自定义 结束年月日
	private String endHH = " 59:59:59"; // 自定义 结束时分秒

	private List<Subject> allSubject; // 专业集合

	private float convertSum = 0f;

	private float yfConvertSum = 0f;

	private int i = 0;;
	
	List<ContractCountNewDTO> contractcountNewDTOlist=null;//订单统计

	public List<ContractCountNewDTO> getContractcountNewDTOlist() {
		return contractcountNewDTOlist;
	}

	public void setContractcountNewDTOlist(
			List<ContractCountNewDTO> contractcountNewDTOlist) {
		this.contractcountNewDTOlist = contractcountNewDTOlist;
	}

	/**
	 * 得到所有专业
	 * 
	 * @return 返回专业集合
	 */
	private List<Subject> getAllSubjectList() {
		allSubject = subjectService.getAllSubject();
		return allSubject;
	}

	/**
	 * 订单统计主方法,
	 * 
	 * @return
	 */
	public String getContractInfo() {
		try{
			HttpServletRequest request=ServletActionContext.getRequest();
			if(request.getParameter("l")!=null){
				location=Integer.valueOf(request.getParameter("l").toString());				
			}
			allSubject = getAllSubjectList(); // 得到所有专业
			getTime(); // 调用时间查询条件添加方法
			getSelectTime(); // 调用时间查询条件方法
			contractcountNewDTOlist=this.contractCountService.getCountractCountAll(queryContractCountCondition);
			return "contractInfo";
		}
		catch(Exception e){
			logger.error("ContractCountAction.getContractInfo",e);
			return "error";
		}
	}
	
	private void initDateTJ() {
		 if(startTime!=null&&!"".equals(startTime)){
			 startTime=startTime.substring(0,startTime.indexOf(startHH));
		 }
		 if(endTime!=null&&!"".equals(endTime)){
			 endTime=endTime.substring(0,endTime.indexOf(endHH));
		 }
	}

	/**
	 * 按专业得到已付订单集合 专业订单集合
	 */
	private void getContractAll(int subjectId) {
		this.getQueryContractCountCondition().setSubjectId(subjectId);
		contractList = this.contractCountService.getCountractCount(this
				.getQueryContractCountCondition()); // 得到全部订单
		WFcontractList = this.contractCountService.getWFCountractCount(this
				.getQueryContractCountCondition());// 得到已付订单
	}

	/**
	 * 此方法为得到所有订单数据 包括 已付 未付 赠送等等 payType付款方式0 为赠送 1支付宝 2货到付款 3 网银在线 4 块钱 stutas
	 * 付款状态 //赠送的包括 2赠送 4修复 0未付 1已付 3退费; 货到付款的包括 0未激活 1已激活 3已取消 4退费
	 * 
	 * @param subjectId
	 *            专业ID
	 */
	private void getContractSum(int subjectId, String subjectName) {
		Integer customerCount = (int) this.contractCountService
				.getCustomerCount(this.getQueryContractCountCondition()); // 得到注册人数
		Integer cusContractCount = this.contractCountService
				.getCustomerContractCount(this.getQueryContractCountCondition()); // 得到当前注册人数中
		// 有多少用户下了订单
		YFSUM = 0; // 每一个专业开始 都将已付总和 归0
		this.contractCountDTO = new ContractCountDTO();
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
		int ContractSum = 0;
		int convertSum = 0;
		float convert = 0.00f;
		int yfPrice = 0;
		int ctlSize = contractList.size();
		for (int i = 0; i < ctlSize; i++) {
			ContractDTO contractDTO = contractList.get(i);
			if (contractDTO.getPayType() == 0
					&& contractDTO.getSubjectId() == subjectId) { // 赠送订单处理
				SUM++;
				GIVE++; // 当前专业赠送总数
				zsSum++; // 所有专业赠送总数
				ContractSum++;
				this.SUM++;
				this.zsSum++;
			} else if (contractDTO.getPayType() == 1
					&& contractDTO.getSubjectId() == subjectId) { // 支付宝订单处理
				yfPrice = getYFContractCount(i, yfPrice);
				SUM++;
				ZFB++;
				zfbSum++;
				ContractSum++;
				convertSum++;
				this.SUM++;
				this.zfbSum++;
			} else if (contractDTO.getPayType() == 2
					&& contractDTO.getSubjectId() == subjectId) { // 货到付款订单处理
				yfPrice = getYFContractCount(i, yfPrice);
				SUM++;
				POST++;
				hdfkSum++;
				ContractSum++;
				convertSum++;
				this.hdfkSum++;
				this.SUM++;
			} else if (contractDTO.getPayType() == 3
					&& contractDTO.getSubjectId() == subjectId) { // 网银在线订单处理
				yfPrice = getYFContractCount(i, yfPrice);
				SUM++;
				CB++;
				wyzxSum++;
				ContractSum++;
				convertSum++;
				this.SUM++;
				this.wyzxSum++;
			} else if (contractDTO.getPayType() == 4
					&& contractDTO.getSubjectId() == subjectId) { // 快钱订单处理
				yfPrice = getYFContractCount(i, yfPrice);
				SUM++;
				KQ++;
				kqSum++;
				ContractSum++;
				convertSum++;
				this.SUM++;
				this.kqSum++;
			}
		}
		this.contractCountDTO.setZsSum(GIVE); // 当前专业赠送总数
		this.contractCountDTO.setZfbSum(ZFB); // 当前专业支付宝总数
		this.contractCountDTO.setWyzxSum(CB); // 当前专业网银在线总数
		this.contractCountDTO.setKqSum(KQ); // 当前专业会计总数
		this.contractCountDTO.setHdfkSum(POST); // 当前专业货到付款总数
		this.contractCountDTO.setSubjectContractSum(ContractSum); // 当前专业 订单总数
		this.contractCountDTO.setZfbSum(zfbSum); // 所有专业支付宝总计
		this.contractCountDTO.setContractSum(SUM); // 所有专业 订单总计
		this.contractCountDTO.setKqSum(kqSum); // 所有专业快钱总计
		this.contractCountDTO.setWyzxSum(wyzxSum); // 所有专业网银在线总计
		this.contractCountDTO.setZsSum(zsSum); // 赠送总计
		this.contractCountDTO.setZfbSum(zfbSum); // 支付宝总计
		this.contractCountDTO.setHdfkSum(hdfkSum); // 货到付款总计
		this.contractCountDTO.setSubjectName(subjectName);
		this.contractCountDTO.setConvertSum(convertSum);
		this.contractCountDTO.setSellPriceSum(yfPrice);
		this.contractCountDTO.setZjSum(this.SUM);
		this.contractCountDTO.setZjHdfkSum(this.hdfkSum);
		this.contractCountDTO.setZjZfbSum(this.zfbSum);
		this.contractCountDTO.setZjWyzxSum(this.wyzxSum);
		this.contractCountDTO.setZjKqSum(this.kqSum);
		this.contractCountDTO.setZjZsSum(this.zsSum);
		this.contractCountDTO.setZjSellPriceSum(sellPriceSum);
		if (cusContractCount != 0) { // 此处为注册 订单转化率，当前这些注册人数中有多少下了订单/注册人数
			convert = (cusContractCount * 1000) / customerCount;
			convert = convert / 10;
			if (convert != 0) {
				this.convertSum += convert;
				i++;
			}
		}
		this.contractCountDTO.setConvertContract(String.valueOf(convert));

		if (this.contractCountDTO.getConvertSum() != 0) { // 支付转化率，支付订单/当前全部订单
			convert = (YFSUM * 1000) / this.contractCountDTO.getConvertSum();
			convert = convert / 10;
			if (convert != 0) {
				yfConvertSum += convert;
			}
		}
		this.contractCountDTO.setYFconverrContract(String.valueOf(convert));
		String str = String.valueOf(this.convertSum / i);

		this.contractCountDTO.setZjConvertSum(str.substring(0,
				str.indexOf(".") + 2));
		str = String.valueOf(this.yfConvertSum / i);
		this.contractCountDTO.setZjYfConvertSum(str.substring(0, str
				.indexOf(".") + 2));
	}

	/**
	 * 已付订单递增方法
	 * 
	 * @param index
	 *            当前集合索引
	 */
	private int getYFContractCount(int index, int yfPrice) {
		ContractDTO contractDTO = contractList.get(index);
		if (contractDTO.getStatus() == 1) {
			sellPriceSum += contractDTO.getSellPrice();
			yfPrice += contractDTO.getSellPrice();
			YFSUM++;
		}
		return yfPrice;
	}

	/**
	 * 或得当前专业所有未付订单数
	 * 
	 * @param subjectId
	 */
	private void getContractWFSum(int subjectId) { // 查询各个专业 各个类型未付订单数
		// 参数为 未付的 支付宝 块钱 网银 货到付款 计数
		int WFZFB = 0;
		int WFPOST = 0;
		int WFCB = 0;
		int WFKQ = 0;
		int WfContractSum = 0; // 当前专业的未付总和
		int TF = 0; // 退费数
		if (WFcontractList != null) {
			int ctlSize = WFcontractList.size();
			for (int i = 0; i < ctlSize; i++) {
				ContractDTO contractDTO = WFcontractList.get(i);
				if (contractDTO.getPayType() == 1
						&& contractDTO.getSubjectId() == subjectId) { // 支付宝订单处理
					if (contractDTO.getStatus() == 3) {
						TF++;
						TFSUM++;
					} else {

						WFZFB++;
						wfZfbSum++;
						WfContractSum++; // 当前专业未付款总数
						WFSUM++;
					}
				} else if (contractDTO.getPayType() == 2
						&& contractDTO.getSubjectId() == subjectId) { // 货到付款订单处理
					if (contractDTO.getStatus() == 3) {
						TF++;
						TFSUM++;
					} else {
						WFPOST++;
						wfHdfkSum++;
						WfContractSum++; // 当前专业未付款总数
						WFSUM++;
					}
				} else if (contractDTO.getPayType() == 3
						&& contractDTO.getSubjectId() == subjectId) { // 网银在线订单处理
					if (contractDTO.getStatus() == 3) {
						TF++;
						TFSUM++;
					} else {
						WFCB++;
						wfWyzxSum++;
						WfContractSum++; // 当前专业未付款总数
						WFSUM++;
					}
				} else if (contractDTO.getPayType() == 4
						&& contractDTO.getSubjectId() == subjectId) { // 快钱订单处理
					if (contractDTO.getStatus() == 3) {
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
		this.contractCountDTO.setYfZfbSum(WFZFB); // 当前 已付专业支付宝总数
		this.contractCountDTO.setYfWyzxSum(WFCB); // 当前专业 已付网银在线总数
		this.contractCountDTO.setYfKqSum(WFKQ); // 当前专业 已付快钱总数
		this.contractCountDTO.setYfHdfkSum(WFPOST); // 当前专业货到付款 总数
		this.contractCountDTO.setYfSubjectContractSum(WfContractSum); // 当前专业
		this.contractCountDTO.setTfSum(TFSUM);
		this.contractCountDTO.setTF(TF);
		this.contractCountDTO.setZjWfSum(WFSUM);
		this.contractCountDTO.setZjWfHdfkSum(wfHdfkSum);
		this.contractCountDTO.setZjWfZfbSum(wfZfbSum);
		this.contractCountDTO.setZjWfKqSum(wfKqSum);
		this.contractCountDTO.setZjWfWyzxSum(wfWyzxSum);

	}

	private void getTime() {

		if (startTime != null) { // 如果初次查询时间不为空 则添加查询条件 即 当天时间
			if (startTime.length() != 0) {
				String startTimeNew=startTime+startHH;
//				startTime +=startHH;
//				this.getQueryContractCountCondition().setStartCountTime(startTime);
				this.getQueryContractCountCondition().setStartCountTime(startTimeNew);
			}
		}
		if (endTime != null) {
			if (endTime.length() != 0) {
				String endTimeNew=endTime+endHH;
//				endTime +=endHH;
//				this.getQueryContractCountCondition().setEndCountTime(endTime);
				this.getQueryContractCountCondition().setEndCountTime(endTimeNew);
			}
		}
	}

	/**
	 * @author wangzheng 学员统计 附加时间查询条件 当天 一周 当月 三月
	 */
	private void getSelectTime() {
		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
			this.getQueryContractCountCondition().setStartCountTime(
					todayStrartTime);
			this.getQueryContractCountCondition().setEndCountTime(todayEndTime);
		} else if (location == 2) { // 一周查询条件
			this.getQueryContractCountCondition().setStartCountTime(
					weekStartTime+startHH);
			this.getQueryContractCountCondition().setEndCountTime(
					todayEndTime);

		} else if (location == 3) {

			this.getQueryContractCountCondition().setStartCountTime(
					monthStartTime+startHH);
			this.getQueryContractCountCondition().setEndCountTime(
					todayEndTime);
		} else if (location == 4) {

			this.getQueryContractCountCondition().setStartCountTime(
					threeMonthStartTime+startHH);
			this.getQueryContractCountCondition().setEndCountTime(
					todayEndTime);
		} else if (location == 0) {

			this.getQueryContractCountCondition().setStartCountTime("");
			this.getQueryContractCountCondition().setEndCountTime("");
			this.getQueryContractCountCondition().setPayType(-1);
		}
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public ContractCountDTO getContractCountDTO() {
		if (this.contractCountDTO == null) {
			this.contractCountDTO = new ContractCountDTO();
		}
		return contractCountDTO;
	}

	public void setContractCountDTO(ContractCountDTO contractCountDTO) {
		this.contractCountDTO = contractCountDTO;
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

	public List<ContractDTO> getContractList() {
		if (this.contractList == null) {
			this.contractList = new ArrayList<ContractDTO>();
		}
		return contractList;
	}

	public void setContractList(List<ContractDTO> contractList) {
		this.contractList = contractList;
	}

	public IContractCount getContractCountService() {
		return contractCountService;
	}

	public void setContractCountService(IContractCount contractCountService) {
		this.contractCountService = contractCountService;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
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

	public List<ContractCountDTO> getContractCountList() {
		if (this.contractCountList == null) {
			this.contractCountList = new ArrayList<ContractCountDTO>();
		}
		return contractCountList;
	}

	public void setContractCountList(List<ContractCountDTO> contractCountList) {
		this.contractCountList = contractCountList;
	}

	public List<Subject> getAllSubject() {
		if (this.allSubject == null) {
			this.allSubject = new ArrayList<Subject>();
		}
		return allSubject;
	}

	public void setAllSubject(List<Subject> allSubject) {
		this.allSubject = allSubject;
	}

	public List<ContractDTO> getWFcontractList() {
		return WFcontractList;
	}

	public void setWFcontractList(List<ContractDTO> fcontractList) {
		WFcontractList = fcontractList;
	}

	public int getYFSUM() {
		return YFSUM;
	}

	public void setYFSUM(int yfsum) {
		YFSUM = yfsum;
	}

}
