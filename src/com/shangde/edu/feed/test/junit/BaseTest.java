package com.shangde.edu.feed.test.junit;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;

/**
 * 
 * junit测试模块，基类
 * 
 * @author Libg
 * 
 */
public class BaseTest extends TestCase {

	ApplicationContext springContext = null;

	protected void setUp() throws Exception {

		springContext = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:/config/spring/applicationContext.xml" });

		/**
		 * springContext = new ClassPathXmlApplicationContext(new String[] {
		 * "classpath*:/config/spring/applicationContext.xml",
		 * "classpath:/config/spring/applicationContext-quartz.xml" });
		 */
		;
		super.setUp();
	}

	protected void tearDown() throws Exception {
		springContext = null;
		super.tearDown();
	}

	/**
	 * 测试函数名称，
	 * 
	 * 1、冠以test_开头
	 * 
	 * 2、函数中不含有fail();代码
	 * 
	 * 3、运行方式eclipse key=Shift+Alt+x t
	 */
	public void test_demo() {
		fail();// 表示不执行本函数
		System.out.println("test");
	}
}
