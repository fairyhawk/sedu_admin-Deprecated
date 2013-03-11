package com.shangde.edu.mail.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.shangde.edu.mail.service.IMail;

public class ReadFile {
	public  SendMailAction getFilePath(String fileName, Map map,String mailType) {
		SendMailAction sendMail = new SendMailAction();
		String staticPath = getClass().getClassLoader().getResource("").getPath();
        String path = URLDecoder.decode(staticPath.substring(0, staticPath.indexOf("WEB-INF")))+
        	"back"+File.separator+"jsp"+File.separator+"mail"+File.separator+fileName;
//        /BufferedReader reader = null;
		StringBuffer buff = new StringBuffer();
		//为解决乱码测试代码
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			//源代码
//			reader = new BufferedReader(new FileReader(path));
			
			InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(path)),"GBK");
			reader = new BufferedReader(isr);
			String tempString = null;

			int line = 1;
			//一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				//显示行号
				//buff.append(tempString + "\r\n");
				//System.out.println("line " + line + ": " + tempString);
				if(mailType.equals(IMail.DEMO_MAIL)){
				String name = (String) map.get("name");
					//String regDate=(String)map.get("regDate");
					if (tempString.indexOf("#name#") != -1
							&& tempString.indexOf("#regDate#") != -1) {
						tempString = tempString.replace("#name#", name);
						//tempString = tempString.replace("#regDate#",regDate);
						//用方法把这个字符串存起来
						sendMail.setContent(tempString);
					}
				} else if(IMail.CUS_REG_MAIL.equals(mailType)){
					String cusName = (String) map.get("cusName");
					String date = (String)map.get("date");
					String email = (String)map.get("email");
					if( tempString.indexOf("#date#") != -1) {
						tempString = tempString.replace("#date#",date);
					}
					if (tempString.indexOf("#cusName#") != -1 && cusName != null) {
						tempString = tempString.replaceAll("#cusName#", cusName);
					}
					if (tempString.indexOf("#email#") != -1) {
						tempString = tempString.replaceAll("#email#", email);
					}
					buff.append(tempString + "\r\n");
				} else if(IMail.FORGOT_PWD_MAIL.equals(mailType)){
					String cusName = (String) map.get("cusName");
					String date = (String)map.get("date");
					String newPwd = (String)map.get("newPwd");
					String email = (String)map.get("email");
					if (tempString.indexOf("#email#") != -1) {
						tempString = tempString.replaceAll("#email#", email);
					}
					if (tempString.indexOf("#cusName#") != -1 && cusName != null) {
						tempString = tempString.replaceAll("#cusName#", cusName);
					}
					if( tempString.indexOf("#date#") != -1) {
						tempString = tempString.replace("#date#",date);
					}
					if( tempString.indexOf("#newPwd#") != -1) {
						tempString = tempString.replace("#newPwd#",newPwd);
					}
					buff.append(tempString + "\r\n");
				} else if(IMail.APPLY_COUPON_MAIL.equals(mailType)){
					String cusName = (String) map.get("cusName");
					String date = (String)map.get("date");
					String serialNumber = (String)map.get("serialNumber");
					if( tempString.indexOf("#date#") != -1) {
						tempString = tempString.replace("#date#",date);
					}
					if (tempString.indexOf("#cusName#") != -1) {
						tempString = tempString.replaceAll("#cusName#", cusName);
					}
					if (tempString.indexOf("#serialNumber#") != -1) {
						tempString = tempString.replaceAll("#serialNumber#", serialNumber);
					}
					buff.append(tempString + "\r\n");
				}else if(IMail.STU_PLANCLOCK_MAIL.equals(mailType)){
					String cusName = null;
					if(!"".equals((String) map.get("cusName"))){
						cusName=(String) map.get("cusName");
					}else{
						cusName=(String) map.get("cusEmail");
					}
					String nonceDate=(String)map.get("nonceDate");
					String headTittle=(String)map.get("subjectName");
					String createTime=(String)map.get("createdate");
					String ccontent=(String)map.get("ccontent");
					String subjectName=(String)map.get("subjectName");
					if( tempString.indexOf("#headTittle#") != -1) {
						tempString = tempString.replace("#headTittle#",headTittle);
					}
					if( tempString.indexOf("#createTime#") != -1) {
						tempString = tempString.replace("#createTime#",createTime);
					}	
					if( tempString.indexOf("#content#") != -1) {
						tempString = tempString.replace("#content#",ccontent);
					}
					if( tempString.indexOf("#nonceDate#") != -1) {
						tempString = tempString.replace("#nonceDate#",nonceDate);
					}
					if( tempString.indexOf("#subjectName#") != -1) {
						tempString = tempString.replace("#subjectName#",subjectName);
					}
					if( tempString.indexOf("#cusName#") != -1) {
						tempString = tempString.replace("#cusName#",cusName);
					}
					buff.append(tempString + "\r\n"); 
				}
				line++;
			}
			sendMail.setContent(buff.toString());
			//System.out.println(buff.toString());
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}

			return sendMail;

		}
	}
}
