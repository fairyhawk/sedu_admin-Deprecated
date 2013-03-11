package com.shangde.common.task;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shangde.common.service.ConfigService;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.sms.service.IsmsService;
import com.shangde.edu.sp.domain.SentPerson;
import com.shangde.edu.sp.domain.SentPersonInfo;
import com.shangde.edu.sp.service.ISentPerson;
import com.shangde.edu.sp.service.ISentPersonInfo;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;

/**
 * 定时任务
 * @author wangchao
 *
 */
public class DayDataQuartzTask{
	
	private static final Logger logger = Logger.getLogger(DayDataQuartzTask.class);
	
	//private static final String ALLOW_IP = "219.232.227.91";//允许发送IP地址
	//private static final String ALLOW_IP = "127.0.0.1";//允许发送IP地址
	private static final String ORDER = "order"; 
	private static final String REGISTER = "register";
	private static final String PAIDORDER = "paidOrder";
	private static final String SUBJECTNAME = "subjectName";
	
	private static final int ALL_ORDER = 1; //不包括赠送与取消
	private static final int PAID_ORDER = 2; //支付成功标志
	
	private IsmsService smsService;
	private ICustomer customerService;
	private IContract contractService;
	private ConfigService configurator;
	private ISubject subjectService;
	private List <Subject> subjects=new  ArrayList<Subject>();
	//private StringBuffer s;
	//private int a=0;
	//private int b=0;
	//private int c=0;
	
	private ISentPerson sentPersonService;
	
	private ISentPersonInfo sentPersonInfoService;
	
	/**
	 * 
	 * @author 王超
	 * 功能：定时通知网站管理人员网站信息
	 * @param args
	 * 
	protected void infoToManager2(){
		try {
			 List subName=new ArrayList();
			 List subZhuCe=new ArrayList();
			 List subDingDan=new ArrayList();
			 List subZhiFu=new ArrayList();
			 boolean status=true;
			//设置时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat mdSdf = new SimpleDateFormat("MM月dd日");
			//设置时间
			Calendar cal = Calendar.getInstance();
			
			cal.add(Calendar.DAY_OF_MONTH, -1);

			
			//设置查询条件
			QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
			queryCustomerCondition.setStartCountTime(sdf.format(cal.getTime()));
			queryCustomerCondition.setEndCountTime(sdf.format(new Date()));
			queryCustomerCondition.setCusType("0");
			
			QueryContractCondition queryContractCondition = new QueryContractCondition();
			queryContractCondition.setStartCountTime(sdf.format(cal.getTime()));
			queryContractCondition.setEndCountTime(sdf.format(new Date()));
			
			subjects=subjectService.getAllSubject();
				

			
			//得到定时短信的配置信息并拆分
			Map<String,String> telephones = configurator.getTelephoneMap();
			
			Iterator<Entry<String, String>> keyValueIterator = (Iterator<Entry<String, String>>) telephones.entrySet().iterator();
			while(keyValueIterator.hasNext()){
			  Entry<String,String> entry =  keyValueIterator.next();
			  String name = (String)entry.getKey();
			  String telephone = (String)entry.getValue();
			  //拆分得出手机号和专业简称
			  String[] tel = telephone.split(",");
				String newTel = tel[0];
				String subject = tel[1];
				s=new StringBuffer();
				//通过专业名称拼接成短信并发送
				if (subject != null && !"".equals(subject)) {
						queryContractCondition.setPayType(-1);
						String sub[] = subject.split(";");
						for (int j = 0; j < sub.length; j++) {
							
							for(int i=0;i<subjects.size();i++){
								if(status){
									queryCustomerCondition.setSubjectId(subjects.get(i).getSubjectId());
									queryContractCondition.setSubjectId(subjects.get(i).getSubjectId());
									subName.add(i,subjects.get(i).getSubjectName());
									subZhuCe.add(i,customerService.getRegistNumber(queryCustomerCondition));
									queryContractCondition.setConStr(1);
									subDingDan.add(i,contractService.getContractNumberBySubject(queryContractCondition));
									queryContractCondition.setConStr(2);
									subZhiFu.add(i,contractService.getContractNumberBySubject(queryContractCondition));
								
									if(i==(subjects.size()-1)){
										status=false;
									}
								}
								if(sub[j].equals(subjects.get(i).getSubjectName())){
									
									s.append(subName.get(i));
									s.append(":");
									s.append(subZhuCe.get(i));
									s.append(":");
									a+=Integer.parseInt(subZhuCe.get(i).toString());
									s.append(subDingDan.get(i));
									s.append(":");
									b+=Integer.parseInt(subDingDan.get(i).toString());
									
									s.append(subZhiFu.get(i));
									s.append("\n");
									c+=Integer.parseInt(subZhiFu.get(i).toString());
								}
							}												
						}
//						smsService.sendEx(name +"您好,过去24小时内,注册、订单、支付比:" +"\n" + s + "\nHighSo-技术中心",newTel, "", "", "");
						smsService.sendEx(name +"您好,过去24小时内,注册、订单、支付比:"+"\n"+"all"+":"+a+":"+b+":" +c  +"\n" + s + "\nHighSo-技术中心",newTel, "", "", "");
						a=0;
						b=0;
						c=0;
					}
			}
		} catch (Exception e) {
			logger.error("DayDataQuartzTask.infoToManager",e);
		}
	}
	*/
	
	
	/**
	 * 
	 * 
	 * Author:Yangning
	 * CreateDate:2012-1-31
	 * 功能：定时通知网站管理人员网站信息-改写
	 */
	@SuppressWarnings({"rawtypes" })
	public void infoToManager(){
		try{
			logger.info("------toSentMssage--------");
			if(isAllowSendMsg()){
				logger.info("------allow--------");
				//得到发送信息汇总
				Map allStatisticsMap = getAllSubjectStatics();
				
				//得到所有发送人
				List<SentPerson> allSendPerson = sentPersonService.getSentPersonByList(null);
				if(allSendPerson != null && allSendPerson.size() > 0){
					for (SentPerson person : allSendPerson) {
						StringBuffer sbSendMsg = new StringBuffer();
						Integer allRegister = 0;
						Integer allPaidOrder = 0;
						Integer allOrder = 0;
						int personId = person.getId();
						List<SentPersonInfo> sendList = sentPersonInfoService.getSPInfoByspId(personId);
						if(sendList != null && sendList.size() > 0){
							for (SentPersonInfo sentPersonInfo : sendList) {
								//查找所注册专业
								Map staticsMap = (Map) allStatisticsMap.get(String.valueOf(sentPersonInfo.getSubjectId()));
								if(staticsMap != null){
									//统计总数量与拼串
									sbSendMsg.append(staticsMap.get(SUBJECTNAME));
									sbSendMsg.append(":");
									sbSendMsg.append(staticsMap.get(REGISTER));
									allRegister += Integer.parseInt(staticsMap.get(REGISTER).toString());
									sbSendMsg.append(":");
									sbSendMsg.append(staticsMap.get(ORDER));
									sbSendMsg.append(":");
									allPaidOrder += Integer.parseInt(staticsMap.get(PAIDORDER).toString());
									sbSendMsg.append(staticsMap.get(PAIDORDER));
									allOrder += Integer.parseInt(staticsMap.get(ORDER).toString());
								}
								sbSendMsg.append("\n");
							}
						//System.out.println("all" + allOrder + ":" + allRegister + ":" + allPaidOrder);
						//System.out.println(sbSendMsg.toString());
						String sendMsg = person.getPersonName() + "您好,过去24小时内,注册、订单、支付比:"+"\n"+"all"+":"+allRegister+":"+allOrder+":" +allPaidOrder +"\n" + sbSendMsg.toString() + "\nHighSo-技术中心";
						//logger.info("_____sendMessage____");;
						smsService.sendEx(sendMsg,person.getPersonPhone(), "", "", "");
						}
					}
					
				}
			}
		}catch(Exception e){
			logger.error("DayDataQuartzTask.infoToManager");
		}
	}
	
	/**
	 * 得到所有统计信息:按专业
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-2-3
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map getAllSubjectStatics(){
		//设置时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//设置时间
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		//设置查询条件
		QueryCustomerCondition queryCustomerCondition = new QueryCustomerCondition();
		queryCustomerCondition.setStartCountTime(sdf.format(cal.getTime()));
		queryCustomerCondition.setEndCountTime(sdf.format(new Date()));
		queryCustomerCondition.setCusType("0");
		
		QueryContractCondition queryContractCondition = new QueryContractCondition();
		queryContractCondition.setStartCountTime(sdf.format(cal.getTime()));
		queryContractCondition.setEndCountTime(sdf.format(new Date()));
		
		subjects=subjectService.getAllSubject();
		
		//将所有subjects,统计信息,封装于allStatistics
		Map<String,Map> allStatisticsMap = new HashMap<String,Map>();
		//得到所有专业统计信息
		if(subjects != null && subjects.size() > 0){
			for (Subject  subject : subjects) {
				queryCustomerCondition.setSubjectId(subject.getSubjectId());
				queryContractCondition.setSubjectId(subject.getSubjectId());
				Map staticsMap = new HashMap<String,String>();
				staticsMap.put(SUBJECTNAME, subject.getSubjectName());
				//注册量
				staticsMap.put(REGISTER, customerService.getRegistNumber(queryCustomerCondition));
				queryContractCondition.setConStr(ALL_ORDER);
				int orderNum = contractService.getContractNumberBySubjectToMsg(queryContractCondition);
				queryContractCondition.setConStr(PAID_ORDER);
				int paid = contractService.getContractNumberBySubjectToMsg(queryContractCondition);
				//支付比
				staticsMap.put(PAIDORDER, String.valueOf(paid));
				//订单量
				staticsMap.put(ORDER, String.valueOf(orderNum));
				allStatisticsMap.put(String.valueOf(subject.getSubjectId()), staticsMap);
			}
		}
		return allStatisticsMap;
	}
	
	/**
	 * 查看本地ip(客户端方式)
	 * @return
	 * Author:Yangning
	 * CreateDate:2012-2-3
	 */
	@SuppressWarnings("rawtypes")
	private boolean isAllowSendMsg(){
			boolean isAllow = false;
			Enumeration netInterfaces = null;
			    try {
			        netInterfaces = NetworkInterface.getNetworkInterfaces();
			        while (netInterfaces.hasMoreElements()) {
			            NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
			            Enumeration ips = ni.getInetAddresses();
			            while (ips.hasMoreElements()) {
			                 if(((InetAddress) ips.nextElement()).getHostAddress().equals(configurator.getAllowIpRange())){
			                	 isAllow = true;
			                	 return isAllow;
			                 }
			            }
			        }
			    } catch (Exception e) {
			        logger.error("DayDataQuartzTask.isAllowSendMsg", e);
			        //isAllow = true;
			    }
			return isAllow;
	}
	
	public IsmsService getSmsService() {
		return smsService;
	}

	public void setSmsService(IsmsService smsService) {
		this.smsService = smsService;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public ISentPerson getSentPersonService() {
		return sentPersonService;
	}

	public void setSentPersonService(ISentPerson sentPersonService) {
		this.sentPersonService = sentPersonService;
	}

	public ISentPersonInfo getSentPersonInfoService() {
		return sentPersonInfoService;
	}

	public void setSentPersonInfoService(ISentPersonInfo sentPersonInfoService) {
		this.sentPersonInfoService = sentPersonInfoService;
	}
}
