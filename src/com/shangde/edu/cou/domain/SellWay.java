package com.shangde.edu.cou.domain;

import java.io.Serializable;
import java.util.Date;

public class SellWay implements Serializable{
	/**
	 *  ID
	 **/
    private int sellId;
    /**
	 *   专业ID
	 **/
    private int subjectId;
    /**
	 *  售卖方式名
	 **/
    private String sellName;
    /**
	 *  售卖方式状态
	 **/
    private int status;
    /**
	 *  添加时间
	 **/
    private java.util.Date addTime;
    /**
	 *  售卖方式价格
	 **/
    private float sellPrice;
  
    private String sellMark;
    /**
     * 讲师名称
     */
    private String teacher;
    /**
     * 专业关键字
     */
    private String subjectKey;
    /**
     * 课时数
     */
    private int lessonNumber;
    /**
     * 课程开始上传时间
     * 
     */
    private String startTime;
    
    /**
     *说明
     */
    private String remark;
    
    /**
     * 课程有效期
     */
    private String validity;
    /**
     * 下架时间
     */
    private java.util.Date  dropTime;
    /**
     * 上下架状态
     */
    private int shopState;
    /**
     * 
     * 时间版本
     */
    private String timeVersion;
    
    /**
     * 创建时间
     */
    private Date upTime;
    
    /**
     * 显示属性
     */
    private String disProperty;
    
    /**
     * 显示位置 
     */
    private String disPosition;
    
    /**
     * 过期时间
     */
    private Date validityTime;
    
    /**
     * 升级说明
     */
    private String upgradeShow;
    
    /**
     * 商品图片
     */
    private String imagesUrl;
    
    /**
     * 商品视频
     */
    private String vedioUrl;
    
    //修改时间 
    private Date updateTime;
    
    //修改人
    private String updateUser;
    
    private Float nowPrice;
    private Float rebatePrice;
    
    //Date类型字段
    private Date beganSellTime;//开售时间
    
    
    private Integer specialtyId;//所属项目专业
    private String teacherName;//主讲教师
    private String suitPerson;//适合人群
    private String detail;//商品详情
    private String directory;//商品目录
    private String label;//商品标签
    private Date upShelveTime;//上架时间
    private String directUpShelve ;//直接上架
    private Date downShelveTime;//下架时间
    private String directDownShelve;//直接下架
    private String mode;//商品模式
    private String sellMode;//出售模式
    private String validTime;//服务有效期
    private Date validityBeginTime;//服务开始时间
    private Date validityEndTime;//服务结束时间
    private String integral;//赠送积分
    private String isUpgrade;//是否升级
    
    private Boolean isProtocal; // 是否有保过协议
    private String protocalContent; // 保过协议内容
    


	public Integer getSpecialtyId() {
		return specialtyId;
	}

	public void setSpecialtyId(Integer specialtyId) {
		this.specialtyId = specialtyId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSuitPerson() {
		return suitPerson;
	}

	public void setSuitPerson(String suitPerson) {
		this.suitPerson = suitPerson;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Date getUpShelveTime() {
		return upShelveTime;
	}

	public void setUpShelveTime(Date upShelveTime) {
		this.upShelveTime = upShelveTime;
	}

	public String getDirectUpShelve() {
		return directUpShelve;
	}

	public void setDirectUpShelve(String directUpShelve) {
		this.directUpShelve = directUpShelve;
	}

	public Date getDownShelveTime() {
		return downShelveTime;
	}

	public void setDownShelveTime(Date downShelveTime) {
		this.downShelveTime = downShelveTime;
	}

	public String getDirectDownShelve() {
		return directDownShelve;
	}

	public void setDirectDownShelve(String directDownShelve) {
		this.directDownShelve = directDownShelve;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getSellMode() {
		return sellMode;
	}

	public void setSellMode(String sellMode) {
		this.sellMode = sellMode;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public Date getValidityBeginTime() {
		return validityBeginTime;
	}

	public void setValidityBeginTime(Date validityBeginTime) {
		this.validityBeginTime = validityBeginTime;
	}

	public Date getValidityEndTime() {
		return validityEndTime;
	}

	public void setValidityEndTime(Date validityEndTime) {
		this.validityEndTime = validityEndTime;
	}

	public String getIntegral() {
		return integral;
	}

	public void setIntegral(String integral) {
		this.integral = integral;
	}

	public String getIsUpgrade() {
		return isUpgrade;
	}

	public void setIsUpgrade(String isUpgrade) {
		this.isUpgrade = isUpgrade;
	}

	public Date getBeganSellTime() {
		return beganSellTime;
	}

	public void setBeganSellTime(Date beganSellTime) {
		this.beganSellTime = beganSellTime;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Date getStopSellTime() {
		return stopSellTime;
	}

	public void setStopSellTime(Date stopSellTime) {
		this.stopSellTime = stopSellTime;
	}

	public Date getStopServiceTime() {
		return stopServiceTime;
	}

	public void setStopServiceTime(Date stopServiceTime) {
		this.stopServiceTime = stopServiceTime;
	}

	public Integer getValidityNum() {
		return validityNum;
	}

	public void setValidityNum(Integer validityNum) {
		this.validityNum = validityNum;
	}

	private Date confirmTime;//确认时间
    private Date stopSellTime;//停售时间
    private Date stopServiceTime;//停服时间
    private Integer validityNum;//有效天数 
    private Integer sellType; //商品类型

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public String getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}

	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public String getDisProperty() {
		return disProperty;
	}

	public void setDisProperty(String disProperty) {
		this.disProperty = disProperty;
	}

	public String getDisPosition() {
		return disPosition;
	}

	public void setDisPosition(String disPosition) {
		this.disPosition = disPosition;
	}

	public Date getValidityTime() {
		return validityTime;
	}

	public void setValidityTime(Date validityTime) {
		this.validityTime = validityTime;
	}

	public String getUpgradeShow() {
		return upgradeShow;
	}

	public void setUpgradeShow(String upgradeShow) {
		this.upgradeShow = upgradeShow;
	}

	public java.util.Date getDropTime() {
		return dropTime;
	}

	public void setDropTime(java.util.Date dropTime) {
		this.dropTime = dropTime;
	}

	public int getShopState() {
		return shopState;
	}

	public void setShopState(int shopState) {
		this.shopState = shopState;
	}

	public String getTimeVersion() {
		return timeVersion;
	}

	public void setTimeVersion(String timeVersion) {
		this.timeVersion = timeVersion;
	}

	public int getSubjectId(){
        return subjectId;
    }

    public void setSubjectId(int subjectId){
        this.subjectId = subjectId; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public java.util.Date getAddTime(){
        return addTime;
    }

    public void setAddTime(java.util.Date addTime){
        this.addTime = addTime; 
    }

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}


	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getSellMark() {
		return sellMark;
	}

	public void setSellMark(String sellMark) {
		this.sellMark = sellMark;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getSubjectKey() {
		return subjectKey;
	}

	public void setSubjectKey(String subjectKey) {
		this.subjectKey = subjectKey;
	}

	public int getLessonNumber() {
		return lessonNumber;
	}

	public void setLessonNumber(int lessonNumber) {
		this.lessonNumber = lessonNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Float getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(Float nowPrice) {
		this.nowPrice = nowPrice;
	}

	public Object getRebatePrice() {
		return rebatePrice;
	}

	public void setRebatePrice(Float rebatePrice) {
		this.rebatePrice = rebatePrice;
	}

	public Boolean getIsProtocal() {
		return isProtocal;
	}

	public void setIsProtocal(Boolean isProtocal) {
		this.isProtocal = isProtocal;
	}

	public String getProtocalContent() {
		return protocalContent;
	}

	public void setProtocalContent(String protocalContent) {
		this.protocalContent = protocalContent;
	}
	
	
        
   
}
