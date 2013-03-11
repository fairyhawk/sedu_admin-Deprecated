package com.shangde.edu.res.domain;

import java.io.Serializable;
import java.util.List;

public class Vedio implements Serializable{
	/** 视频主键id   */
    private int voId;
    /** 视频组外键id   */
    private int vgId;
    /** 知识点外键id   */
    private int pointid;
    /** 视频名称   */
    private String voName;
    /** 视频路径   */
    private String voUrl;
    /** 视频上传时间   */
    private java.util.Date createTime;
    /** 视频下架时间   */
    private java.util.Date downTime;
    /** 讲师名称   */
    private String lecture;
    /** 视频描述  */
    private String content;
    /** 视听次数   */
    private int viewNum;
    /** 视频大小   */
    private String voSize;
    /** 是否可以分享   */
    private String isShare;
    /** 收藏数量   */
    private int collectionNum;
    /** 是否允许试听   */
    private int isAudition;
    /** 关联视频   */
    private String reachUrl;
    /** 排序   */
    private int voOrder;
    /** 备注   */
    private String voInfo;
    /** 是否为首选视频   */
    private String voRadOne;
    /** 选修视频**/
    private String xuanXiu;
    /** 教师ＩＤ**/
    private int tcId;
    /** 视频分数 打分用的**/
    private Double voScores = 0.0;
    /** 初始基础分数**/
    private int voBScores;
    
    private String ccUrl;

	private Integer playType;
	//cc图片地址
	private String ccSmallPic;
    
    private List<Picture> vedioPicList;
    private String teacherName;//老师名称
    private String auditionSize;//试听时长
    private String auditionUrl;//试听地址
    private String freeVedio;//是否免费
    private String recommend;//显示在推荐位
    private String vedioPicUrl;//视频图片
    public String getVedioPicUrl() {
		return vedioPicUrl;
	}

	public void setVedioPicUrl(String vedioPicUrl) {
		this.vedioPicUrl = vedioPicUrl;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getAuditionSize() {
		return auditionSize;
	}

	public void setAuditionSize(String auditionSize) {
		this.auditionSize = auditionSize;
	}

	public String getAuditionUrl() {
		return auditionUrl;
	}

	public void setAuditionUrl(String auditionUrl) {
		this.auditionUrl = auditionUrl;
	}

	public String getFreeVedio() {
		return freeVedio;
	}

	public void setFreeVedio(String freeVedio) {
		this.freeVedio = freeVedio;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	
    
    /**
     * 知识点名称
     */
    private String kpointName;
    
    public String getKpointName() {
		return kpointName;
	}

	public void setKpointName(String kpointName) {
		this.kpointName = kpointName;
	}

	public List<Picture> getVedioPicList() {
		return vedioPicList;
	}

	public void setVedioPicList(List<Picture> vedioPicList) {
		this.vedioPicList = vedioPicList;
	}

	public int getVoId(){
        return voId;
    }

    public void setVoId(int voId){
        this.voId = voId; 
    }
        
    public int getVgId(){
        return vgId;
    }

    public void setVgId(int vgId){
        this.vgId = vgId; 
    }
        
    public int getPointid(){
        return pointid;
    }

    public void setPointid(int pointid){
        this.pointid = pointid; 
    }
        
    public String getVoName(){
        return voName;
    }

    public void setVoName(String voName){
        this.voName = voName; 
    }
        
    public String getVoUrl(){
        return voUrl;
    }

    public void setVoUrl(String voUrl){
        this.voUrl = voUrl; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public java.util.Date getDownTime(){
        return downTime;
    }

    public void setDownTime(java.util.Date downTime){
        this.downTime = downTime; 
    }
        
    public String getLecture(){
        return lecture;
    }

    public void setLecture(String lecture){
        this.lecture = lecture; 
    }
        
    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content; 
    }
        
    public int getViewNum(){
        return viewNum;
    }

    public void setViewNum(int viewNum){
        this.viewNum = viewNum; 
    }
        
    public String getVoSize(){
        return voSize;
    }

    public void setVoSize(String voSize){
        this.voSize = voSize; 
    }
        
    public String getIsShare(){
        return isShare;
    }

    public void setIsShare(String isShare){
        this.isShare = isShare; 
    }
        
    public int getCollectionNum(){
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum){
        this.collectionNum = collectionNum; 
    }
        
    public int getIsAudition(){
        return isAudition;
    }

    public void setIsAudition(int isAudition){
        this.isAudition = isAudition; 
    }
        
    public String getReachUrl(){
        return reachUrl;
    }

    public void setReachUrl(String reachUrl){
        this.reachUrl = reachUrl; 
    }
        
    public int getVoOrder(){
        return voOrder;
    }

    public void setVoOrder(int voOrder){
        this.voOrder = voOrder; 
    }
        
    public String getVoInfo(){
        return voInfo;
    }

    public void setVoInfo(String voInfo){
        this.voInfo = voInfo; 
    }

	public String getVoRadOne() {
		return voRadOne;
	}

	public void setVoRadOne(String voRadOne) {
		this.voRadOne = voRadOne;
	}

	public String getXuanXiu() {
		return xuanXiu;
	}

	public void setXuanXiu(String xuanXiu) {
		this.xuanXiu = xuanXiu;
	}

	public int getTcId() {
		return tcId;
	}

	public void setTcId(int tcId) {
		this.tcId = tcId;
	}

	public Double getVoScores() {
		return voScores;
	}

	public void setVoScores(Double voScores) {
		this.voScores = voScores;
	}

	public int getVoBScores() {
		return voBScores;
	}

	public void setVoBScores(int voBScores) {
		this.voBScores = voBScores;
	}
	
	 public String getCcUrl() {
			return ccUrl;
		}

		public void setCcUrl(String ccUrl) {
			this.ccUrl = ccUrl;
		}

		public Integer getPlayType() {
			return playType;
		}

		public void setPlayType(Integer playType) {
			this.playType = playType;
		}

		public String getCcSmallPic() {
			return ccSmallPic;
		}

		public void setCcSmallPic(String ccSmallPic) {
			this.ccSmallPic = ccSmallPic;
		}
}
