package com.shangde.edu.feed.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件执行线程池
 * 
 * @author Basil
 *
 */
public class EmailExecutorPool {
	private static final Logger logger = LoggerFactory.getLogger(EmailExecutorPool.class);
	private static EmailExecutorPool instance;
	// 线程池大小
	private int POOL_SIZE = 5;
	private ExecutorService executorService;

	private EmailExecutorPool() {
		initExecutors();
	}

	public static EmailExecutorPool getInstance() {
		if (instance == null) {
			instance = new EmailExecutorPool();
		}
		return instance;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void initExecutors() {
		try {
			logger.info("初始化邮件线程池大小为：{}", POOL_SIZE);
			executorService = Executors.newFixedThreadPool(POOL_SIZE);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.info("初始化邮件发送线程池失败：{}", e.getMessage());
		}
	}
}
