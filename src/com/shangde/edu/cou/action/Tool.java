package com.shangde.edu.cou.action;

import java.math.BigDecimal;

public class Tool {
	public static void main(String[] args) {

		double a = 1;
		double b = 5;
		int c = divide(a, b, 2);
		System.out.println(c + "%");
	}

	public static int divide(double v1, double v2, int scale) {
		if(v2<=0){
			return 0;
		}
		if(v1==0){
			return 0;
		}
		if(v2>0 && v1>0 && v1>=v2){
			return 100;
		}
		double c = div(v1, v2, scale + 2);
		double d = div(c * 100.0, 1, scale);
		long rate = Math.round(d);
		if(rate==0){
			return 1;
		}
		return (int)rate;
	}

	/**
	 * 
	 * @Title: div 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @param @param v1
	 * @param @param v2
	 * @param @param scale
	 * @param @return    设定文件 
	 * @return double    返回类型
	 * @author shixiaofeng@sunland.org.cn 
	 * @throws
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
