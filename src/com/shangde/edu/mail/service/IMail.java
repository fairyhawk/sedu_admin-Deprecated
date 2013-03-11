package com.shangde.edu.mail.service;



import java.io.IOException;
import java.util.Map;

/**  

 * @author miaoshusen

 * @version 1.0

 */
public interface IMail {
	
	public static String DEMO_MAIL = "DEMO";// 测试发送邮件模板
	public static String CUS_REG_MAIL = "CUS_REG";
	public static String FORGOT_PWD_MAIL = "FORGOT_PWD";
	public static String APPLY_COUPON_MAIL = "APPLY_COUPON";
	public static String STU_PLANCLOCK_MAIL = "STU_EMAIL";
	
		public void getMail(String mailType,Map map)throws IOException ;

}