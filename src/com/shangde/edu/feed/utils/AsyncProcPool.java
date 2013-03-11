package com.shangde.edu.feed.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.*;

/**
 * 异步线程池
 * 
 * @author Basil
 *
 */
public class AsyncProcPool {  
	  
	private static final Logger LOG = LoggerFactory.getLogger(AsyncProcPool.class);
    /** 
     * 线程池维护线程的最少数量 
     */  
    private static final int CORE_POOL_SIZE = 3;  
  
    /** 
     * 线程池维护线程的最大数量(采用LinkedBlockingQueue时没有作用) 
     */  
    private static final int MAX_POOL_SIZE = 10;  
  
    /** 
     * 线程池维护线程所允许的空闲时间 
     */  
    private static final int KEEP_ALIVE_TIME = 100;  
  
    /** 
     * 线程池维护线程所允许的空闲时间的单位:秒 
     */  
    private static final TimeUnit KEEP_ALIVE_UNIT = TimeUnit.SECONDS;  
  
    /** 
     * 线程池所使用的缓冲队列 
     */  
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();  
  
    /** 
     * 线程池对拒绝任务的处理策略(重试添加当前的任务，自动重复调用execute()方法) 
     */  
    private static final RejectedExecutionHandler REJECTED_HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();  
  
    private static ThreadPoolExecutor threadPool;  
  
    /** 
     * 单例 
     */  
    @SuppressWarnings("unused")  
    private static final AsyncProcPool INSTANCE = new AsyncProcPool();  
  
    private AsyncProcPool() {  
        init();  
    }  
  
    /** 
     * 初始化 
     */  
    private static synchronized final void init() {  
  
        // 构造一个线程池  
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,  
                KEEP_ALIVE_TIME, KEEP_ALIVE_UNIT, WORK_QUEUE, REJECTED_HANDLER);  
    }  
  
    /** 
     * 提交请求 
     *  
     * @param Runnable 
     */  
    public static void putTask(Runnable command) {  
    	LOG.debug("put task @ ",command.getClass().getName());
        threadPool.execute(command);  
    }  
  
    /** 
     * 关闭线程池 
     */  
    public static void shutdown() {  
        threadPool.shutdown();  
    }  
}  
 