package com.shangde.edu.exam.dto;

import java.io.Serializable;

/**
 * 用户考试分析DTO类
 * @author chenshuai
 *
 */
public class ExamAnalysisDTO implements Serializable{
	 
	/**
	 * 考试次数
	 */
	private int examSum;
	
	/**
	 * 最大正确率
	 */
	private float maxAccuracy;
	
	/**
	 * 最小正确率
	 */
	private float minAccuracy;
	
	/**
	 * 试题数目
	 */
	private int qstSum;
	
	/**
	 * 答对的正确题数目
	 */
	private int rightSum;
	
	/**
	 * 平均正确率
	 */
	private float avgAccuracy;
	
	/**
	 * 平均分数
	 */
	private float avgScore;

	public int getExamSum() {
		return examSum;
	}

	public void setExamSum(int examSum) {
		this.examSum = examSum;
	}

	public float getMaxAccuracy() {
		return maxAccuracy;
	}

	public void setMaxAccuracy(float maxAccuracy) {
		this.maxAccuracy = maxAccuracy;
	}

	public float getMinAccuracy() {
		return minAccuracy;
	}

	public void setMinAccuracy(float minAccuracy) {
		this.minAccuracy = minAccuracy;
	}

	public int getQstSum() {
		return qstSum;
	}

	public void setQstSum(int qstSum) {
		this.qstSum = qstSum;
	}

	public int getRightSum() {
		return rightSum;
	}

	public void setRightSum(int rightSum) {
		this.rightSum = rightSum;
	}

	public float getAvgAccuracy() {
		return avgAccuracy;
	}

	public void setAvgAccuracy(float avgAccuracy) {
		this.avgAccuracy = avgAccuracy;
	}

	public float getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(float avgScore) {
		this.avgScore = avgScore;
	}
}
