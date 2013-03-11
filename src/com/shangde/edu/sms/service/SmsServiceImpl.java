package com.shangde.edu.sms.service;

import org.apache.axis2.databinding.ADBBean;

import com.shangde.edu.sms.webService.SmsServiceStub;
import com.shangde.edu.sms.webService.SmsServiceStub.GetBalance;
import com.shangde.edu.sms.webService.SmsServiceStub.GetBatchSendID;
import com.shangde.edu.sms.webService.SmsServiceStub.GetBlackWords;
import com.shangde.edu.sms.webService.SmsServiceStub.GetSmsStateEx;
import com.shangde.edu.sms.webService.SmsServiceStub.GetSmsStateExResp;
import com.shangde.edu.sms.webService.SmsServiceStub.Message;
import com.shangde.edu.sms.webService.SmsServiceStub.SendEx;
import com.shangde.edu.sms.webService.SmsServiceStub.SendExResp;

public class SmsServiceImpl implements IsmsService{
	
	private final String USERID = "E2696A450A"; 
	private final String PASSWORD = "120606";
	private final int BIZTYPE = 26;
	
	
	public String GetBalance() throws java.lang.Exception {
		SmsServiceStub stub = new SmsServiceStub();
		GetBalance getBalance = getObject(GetBalance.class);
		getBalance.setUserId(USERID);
		getBalance.setPassword(PASSWORD);
		getBalance.setBizType(BIZTYPE);
		return stub.getBalance(getBalance).getGetBalanceResult();
	}

	public String getBatchSendID() throws java.lang.Exception {
		SmsServiceStub stub = new SmsServiceStub();
		GetBatchSendID getBatchSendID = getObject(GetBatchSendID.class);
		return stub.getBatchSendID(getBatchSendID).getGetBatchSendIDResult();
	}

	public String GetBlackWords() throws java.lang.Exception {
		SmsServiceStub stub = new SmsServiceStub();
		GetBlackWords getBlackWords = getObject(GetBlackWords.class);
		getBlackWords.setBizType(BIZTYPE);
		stub.getBlackWords(getBlackWords);
		return null;
	}

	public String getSmsStateEx() throws Exception {
		SmsServiceStub stub = new SmsServiceStub();
		GetSmsStateEx getSmsStateEx = getObject(GetSmsStateEx.class);
		getSmsStateEx.setUserId(USERID);
		getSmsStateEx.setPassword(PASSWORD);
		GetSmsStateExResp ssep = stub.getSmsStateEx(getSmsStateEx).getGetSmsStateExResult();
		String retMsg = ssep.getResult()+"$"+ssep.getCount()+"$"+ssep.getErrorDesc()+"$";
		if(ssep.getSms().getMessage()!=null){
			for(Message m : ssep.getSms().getMessage()){
				retMsg+="$["+m.getBatchSendID()+","+m.getMobile()+","+m.getContent()+","+m.getStatus()+","+m.getSubmitTime()+","+m.getDoneTime()+"]";
			}
		}
		return retMsg;
	}

	public String getUpSms() {
		return null;
	}

	public SendExResp  sendEx(String msgContent, String destNumber, String sendTime, String subNumber, String wapURL) throws java.lang.Exception{
		SmsServiceStub stub = new SmsServiceStub();
		SendEx se = getObject(SendEx.class);
		SendEx seTemp = getObject(SendEx.class);
		se.setUserId(USERID);
		se.setPassword(PASSWORD);
		se.setBizType(BIZTYPE);
		se.setBatchSendID(getBatchSendID());
		se.setMsgContent(msgContent);
		se.setDestNumber(destNumber);
		se.setSendTime(sendTime);
		se.setSubNumber(subNumber);
		se.setWapURL(wapURL);
		int num = Integer.parseInt(GetBalance());
		
		if(num == 2000 || num == 1500){//到2000时提醒充值
			seTemp.setUserId(USERID);
			seTemp.setPassword(PASSWORD);
			seTemp.setBizType(BIZTYPE);
			seTemp.setBatchSendID(getBatchSendID());
			seTemp.setMsgContent("手机服务余额不足2000,请充值！ 北京尚德远程教育平台");
			seTemp.setDestNumber("13260008070");
			seTemp.setSendTime(sendTime);
			seTemp.setSubNumber(subNumber);
			seTemp.setWapURL(wapURL);
			stub.sendEx(seTemp).getSendExResult();
		}
		
		return stub.sendEx(se).getSendExResult();
	}

	public String SetPassword(String UserId, String OldPassword,
			String NewPassword) {
		// TODO Auto-generated method stub
		return null;
	}
	private <E extends ADBBean> E getObject(Class<E> type) throws java.lang.Exception{
        return type.newInstance();
     }

}
