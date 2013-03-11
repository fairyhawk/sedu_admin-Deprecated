package com.shangde.edu.feed.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线程池
 * 
 * @author Basil
 * 
 */
public abstract class MyThread implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(MyThread.class);
	
	/** 线程出错后重新运行次数 */
	private static int errorTimes = 2;

	/** 最多同时执行任务数 */
	private static int maxThread = 2;

	/** 线程使用状态：false未使用、true使用中 */
	private String thread_states = "false";

	/**
	 * 得到空闲线程、并传递参数
	 * 
	 * @param threads
	 *            线程池
	 * @param args
	 *            参数
	 * @return
	 */
	protected static MyThread getMyThread(List<MyThread> threads, Object... args) {
		for (MyThread t : threads) {
			if (t.thread_states.equals("false")) {
				t.thread_states = "true"; // 标记使用中
				t.setArgs(args);
				return t;
			}
		}
		try {
			Thread.sleep(1500L);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getMyThread(threads, args);
	}

	public void close() {
		this.thread_states = "false";
	}

	/** 执行代码 */
	public abstract void runCode() throws Exception;

	/** 设置参数 */
	public abstract void setArgs(Object... args);;

	public abstract void setError();
	public abstract void setSuccess();
	
	/** 异常获取、重新执行代码 */
	public void run() {
		try {
			runCode();
			setSuccess();//记录执行成功数据
			this.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			if (getErrorTimes() > 0) {
				setErrorTimes(getErrorTimes() - 1);
				run();
			}
			//这里修改，而且仅当，只记录一次(由于这里有两次错误处理重新发送，如果重新发送出错那么错误次数就不对称了)
			if(getErrorTimes() >= 2){
				setError();//记录执行失败数据
			}
			this.close();
		}
	}

	// Test...
	public static void main(String[] args) {
		/*
		 * ExecutorService service = Executors.newFixedThreadPool(2);
		 * 
		 * List<MyThread> list = new ArrayList(); // list.add(new MyThread());
		 * 
		 * for (int i = 0; i < 1; i++) { MyThread m = getMyThread(list, i + "",
		 * i + "" + i); service.submit(m); m.close(); }
		 */
	}

	// --------------------------------------

	public int getErrorTimes() {
		return errorTimes;
	}

	public void setErrorTimes(int errorTimes) {
		this.errorTimes = errorTimes;
	}

	public static int getMaxThread() {
		return maxThread;
	}

	public static void setMaxThread(int maxThread) {
		MyThread.maxThread = maxThread;
	}

	public String getThread_states() {
		return thread_states;
	}

	public void setThread_states(String threadStates) {
		thread_states = threadStates;
	}

}