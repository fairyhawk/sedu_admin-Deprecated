package com.shangde.edu.feed.test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import com.shangde.common.util.DateUtil;
import com.shangde.edu.feed.utils.PropertiesUtil;

public class Test {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws Exception
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		//getUserData();
		
		//System.out.println(DateUtil.addDays2(DateUtil.formatDate(new Date(), "yyyy-MM-dd"), -1));
		
		System.out.println(getUserData());
	}

	/**
	 * 截取用户列表
	 * 
	 * @return
	 */
	private static String getUserData() {

		String userData = null;
		String[] userDataArr = null;
		StringBuffer s = new StringBuffer();
		try {
			userData = PropertiesUtil.getEntryValue("user_data",
					"user_data.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(userData);
		if (userData != null) {

			userDataArr = userData.split(",");
			// 从第几位开始
			for (int i = 874; i < userDataArr.length; i++) {
				s.append(userDataArr[i]).append(",");
			}
		}

		//System.out.println(s.toString());
		return s.toString();
	}

}
