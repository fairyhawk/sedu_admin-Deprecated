package com.shangde.edu.sms.test;

import java.util.ArrayList;
import java.util.List;

import  com.shangde.edu.sms.service.IsmsService;
import  com.shangde.edu.sms.service.SmsServiceImpl;
import  com.shangde.edu.sms.webService.SmsServiceStub.SendExResp;

public class TestSmsPost {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IsmsService sms = new SmsServiceImpl();
		try {
//			System.out.println(sms.getBatchSendID());
			List<String> mobileNumber = new ArrayList<String>();
//			mobileNumber.add("13600010504");
//			mobileNumber.add("15210221753");
//			mobileNumber.add("13439901227");
//			mobileNumber.add("13581806308");
			mobileNumber.add("13466795330");
//			mobileNumber.add("15901183025");
//			mobileNumber.add("13811964113");
//			mobileNumber.add("15910622338");
//			mobileNumber.add("15101178825");
//			mobileNumber.add("13581772414");
//			mobileNumber.add("15010293475");
//			mobileNumber.add("13466795330");
//			mobileNumber.add("15101026030");
			for(String mn:mobileNumber){
				SendExResp sp = sms.sendEx("你好，1****", mn, "", "", "");
//				System.out.println("Result:"+sp.getResult());
//				System.out.println("ErrorDesc:"+sp.getErrorDesc());
//				System.out.println("PayCount:"+sp.getPayCount());
//				System.out.println("BlackWords:"+sp.getBlackWords());
//				System.out.println("ErrorMobiles:"+sp.getErrorMobiles());
//				System.out.println("BlackMobiles:"+sp.getBlackMobiles());
//				System.out.println("BatchSendID:"+sp.getBatchSendID());
			}
//			System.out.println(sms.GetBalance());
//			System.out.println(sms.GetBlackWords());
//			Thread.sleep(10000);
//			for(String s: sms.getSmsStateEx().split("\\$")){
//				System.out.println(s);
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
