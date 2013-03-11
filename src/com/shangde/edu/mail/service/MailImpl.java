package com.shangde.edu.mail.service;

import java.io.IOException;
import java.util.Map;

import com.shangde.edu.mail.action.ReadFile;
import com.shangde.edu.mail.action.SendMailAction;

@SuppressWarnings("unchecked")
public class MailImpl implements IMail {
	
    String fileName="";
    SendMailAction sendMail=null;
	public void getMail(String mailType,Map map) throws IOException {
		//SendMailAction sendMail = new SendMailAction();
		
		if (IMail.DEMO_MAIL.equals(mailType)) {
			fileName="newDemoMail.html";
			ReadFile readFile=new ReadFile();
			sendMail=readFile.getFilePath(fileName, map,mailType);
			// 测试发送邮件demo，读取html邮件模板
//			String name=(String) map.get("name");
//			String date=(String)map.get("date");
//			File directory = new File("");   
//			String path=directory.getAbsolutePath(); 
//			path=path.substring(0, path.indexOf("bin"))+"webapps\\sedu\\jsp\\mail\\newDemoMail.html";
//				//System.out.println("&&&&&&&&&&&&"+path+"%%%%%%%%%");
//				//File file = new File("C:\\bb.html");
//		        BufferedReader reader = null;
//		        StringBuffer buff = new StringBuffer(); 
//		        try {
//		        System.out.println("以行为单位读取文件内容，一次读一整行：");
//		        reader = new BufferedReader(new FileReader(path));
//		        String tempString = null;
//		         
//		        int line = 1;
//		        //一次读入一行，直到读入null为文件结束
//		        while ((tempString = reader.readLine()) != null){
//		        //显示行号
//		         buff.append(tempString+"\r\n");
//		        System.out.println("line " + line + ": " + tempString);
//		        if(tempString.indexOf("#name#")!=-1&&tempString.indexOf("#regDate#")!=-1){
//		        	tempString=tempString.replace("#name#",name);
//		        	tempString=tempString.replace("#regDate#","");
//		        	//用方法把这个字符串存起来
//		        	sendMail.setContent(tempString);
//		        }
//		        line++;
//		        }
//		        System.out.println(buff.toString());
//		        reader.close();
//		        } catch (IOException e) {
//		        e.printStackTrace();
//		        } finally {
//		        if (reader != null){
//		        try {
//		        reader.close();
//		        } catch (IOException e1) {
//		        }
//		        }
//		        }
			
		} else if (IMail.CUS_REG_MAIL.equals(mailType)) {
			fileName="mail_cus_reg.html";
			sendMail=new ReadFile().getFilePath(fileName, map,mailType);
			sendMail.setTo((String)map.get("email"));
			sendMail.setSubject("尚德机构远程教育平台----恭喜您注册成功！");
		} else if (IMail.FORGOT_PWD_MAIL.equals(mailType)) {
			fileName="mail_forget_pwd.html";
			sendMail=new ReadFile().getFilePath(fileName, map,mailType);
			sendMail.setTo((String)map.get("email"));
			sendMail.setSubject("尚德机构远程教育平台----找回密码");
		} else if (IMail.APPLY_COUPON_MAIL.equals(mailType)) {
			fileName="mail_apply_coupon.html";
			sendMail=new ReadFile().getFilePath(fileName, map,mailType);
			sendMail.setTo((String)map.get("email"));
			sendMail.setSubject("尚德机构远程教育平台----申请优惠券成功！");
		} else if(IMail.STU_PLANCLOCK_MAIL.equals(mailType)){
			fileName="mail_stu_planclock.html";
			sendMail=new ReadFile().getFilePath(fileName, map,mailType);
			sendMail.setTo((String)map.get("cusEmail"));
			sendMail.setSubject((String)map.get("subjectName")+(String)map.get("ccontent")+"时间提示");
		}
		//126的
		
//		sendMail.setSMTPHost("smtp.126.com");
//		sendMail.setUser("shangdesedu");
//		sendMail.setPassword("123456789");
//		sendMail.setFrom("shangdesedu@126.com");
		
		//263的
		sendMail.setSMTPHost("multi.263xmail.com");
		//如需要，请配置pop
		sendMail.setPOPHost("popcom.263xmail.com");
		sendMail.setUser("highso@sunland.org.cn");
		sendMail.setPassword("shangde2010");
		sendMail.setFrom("highso@sunland.org.cn");
		sendMail.sendMail();
		
	}


}
