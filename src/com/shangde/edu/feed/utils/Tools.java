package com.shangde.edu.feed.utils;



/**
 * Created by IntelliJ IDEA.
 * User: Basil
 * Date: 11-9-26
 * Time: 下午4:11
 */
public class Tools
{
    /**
     * 用于测试多个方法的时间测试方法
     *
     * @param callbacks Callback 接口实例
     */
    public static void testTimes(Callback... callbacks)
    {
        if (callbacks != null && callbacks.length > 0)
        {
            int count = 0;
            for (Callback callback : callbacks)
            {
                count += 1;
                testTime(callback, "[Method-" + count + "] use time: ");
            }
        }
    }


    /**
     * 测试函数使用时间，通过定义 Callback 接口的 execute方法
     *
     * @param callBack Callback 接口
     * @param args 方法描述
     * @return 时间值
     */
    public static Long testTime(Callback callBack, String... args)
    {
        // test start time.
        Long begin = System.currentTimeMillis();
        callBack.execute();// 进行回调操作
        // test end time.
        Long end = System.currentTimeMillis();

        Long useTime = end - begin;
        if (args != null && args.length > 0)
        {
            System.out.println(args[0] + useTime);
        }
        return useTime;
    }


}
