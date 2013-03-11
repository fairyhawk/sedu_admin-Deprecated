package com.shangde.edu.cus.domain;

import java.io.Serializable;

public class TsRecord implements Serializable{
	//积分类别两种
	public static int TRTYPE_FOR =1;//代表兑换积分
	public static int TRTYPE_ACTION=2;//代表成长积分
	//兑换积分的获取方式三种
	public static int ACCESSTYPE_FOR_REGISTE=1;//注册成功赠送100积分
	public static int ACCESSTYPE_FOR_INVITE=2;//每邀请一位好友赠送50积分
	public static int ACCESSTYPE_FOR_BUYCOURSE=3;//每购买一门课程赠送　课程价格*10的积分
	public static int ACCESSTYPE_FOR_ANSWER=4;//问题被采纳，要把问题的分，给到相应的用户上。
	public static int ACCESSTYPE_FOR_TASK=5;//注册成功赠送100积分
	public static int ACCESSTYPE_FOR_NFIRSTLOGIN=6;//每天第一次登录赠送5积分
	public static int ACCESSTYPE_FOR_NEXAM=7;//考一次试，增加5成长积分
	public static int ACCESSTYPE_FOR_NONEANSWER=8;//提一次问题赠送5积分
	public static int ACCESSTYPE_FOR_NANSWERQ=9;//回答一次问题赠送5积分
	public static int ACCESSTYPE_FOR_NANSWERQE=10;//问题被采纳，送，5积分。
	//成长积分的获取方式三种
	public static int ACCESSTYPE_FOR_REG=1;//注册成功赠送50经验
	public static int ACCESSTYPE_FOR_FIRSTLOGIN=2;//每天第一次登录赠送5积分
	public static int ACCESSTYPE_FOR_RECORDNOTE=3;//每记录一次学习笔记赠送50积分
	public static int ACCESSTYPE_FOR_LOOKVEDIO=4;//看视频赠送50积分
	public static int ACCESSTYPE_FOR_TASK_ACTION=5;//注册成功赠送100积分
	public static int ACCESSTYPE_FOR_ANSWERQ=6;//回答一次问题赠送5积分
	public static int ACCESSTYPE_FOR_ANSWERQE=7;//问题被采纳，送，10成长积分。
	public static int ACCESSTYPE_FOR_ONEANSWER=8;//提一次问题赠送5成长积分
	public static int ACCESSTYPE_FOR_EXAM=9;//考一次试，增加5成长积分
	//消耗方式
	public static int USETYPE_COUPON10=1;//1000积分换取10块的优惠券
	public static int USETYPE_COUPON20=2;//5000积分换取20块的优惠券
	public static int USETYPE_COUPON30=3;//12000积分换取30块的优惠券
	public static int USETYPE_ANSWERQ=4;//提出问题，扣除相应的积分数
	public static int USETYPE_ANSSCORE=5;//追加积分，扣除
	public static int USETYPE_RECOMMENDED=6;//推荐小组话题帖子扣除积分,扣除
	/**
	 * 积分记录ID
	 */
    private int trId;
	/**
	 *　积分ID
	 */
    private int tsId;
	/**
	 * 用户ID
	 */
    private int cusId;
	/**
	 * 积分类别
	 */
    private int trType;
	/**
	 * 获取方式
	 */
    private int accessType;
	/**
	 * 消耗方式
	 */
    private int useType;
	/**
	 * 获取时间
	 */
    private java.util.Date accessTime;
	/**
	 * 消耗时间
	 */
    private java.util.Date useTime;
	/**
	 * 积分数值
	 */
    private int trNum;
    
        
    public int getTrId(){
        return trId;
    }

    public void setTrId(int trId){
        this.trId = trId; 
    }
        
    public int getTsId(){
        return tsId;
    }

    public void setTsId(int tsId){
        this.tsId = tsId; 
    }
        
    public int getCusId(){
        return cusId;
    }

    public void setCusId(int cusId){
        this.cusId = cusId; 
    }
        
    public int getTrType(){
        return trType;
    }

    public void setTrType(int trType){
        this.trType = trType; 
    }
        
    public int getAccessType(){
        return accessType;
    }

    public void setAccessType(int accessType){
        this.accessType = accessType; 
    }
        
    public int getUseType(){
        return useType;
    }

    public void setUseType(int useType){
        this.useType = useType; 
    }
        
    public java.util.Date getAccessTime(){
        return accessTime;
    }

    public void setAccessTime(java.util.Date accessTime){
        this.accessTime = accessTime; 
    }
        
    public java.util.Date getUseTime(){
        return useTime;
    }

    public void setUseTime(java.util.Date useTime){
        this.useTime = useTime; 
    }
        
    public int getTrNum(){
        return trNum;
    }

    public void setTrNum(int trNum){
        this.trNum = trNum; 
    }
}
