package com.shangde.edu.cou.condition;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import com.shangde.common.vo.PageQuery;
import com.shangde.edu.cou.dto.TeacherCookieDTO;
import com.shangde.edu.sys.domain.Subject;

public class QuerySellWayCondition extends PageQuery{
	
	 private int saleId;
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
    private String status;
    /**
	 *  添加时间
	 **/
    private java.util.Date addTime;
    /**
	 *  售卖方式价格
	 **/
    private float sellPrice;
    /**
	 *  。。。。。
	 **/
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
    private String shopState;
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
    
    /**
     * 关键字
     * @return
     */
    private String keyword;
    /**
     * 最后关键字
     * @return
     */
     private String word;
     
     /** 所属类别 */
     private Integer sellType;
     
    private Date upShelveBeginTime;//查询条件===》上架开始时间
    private Date upShelveEndTime;//查询条件===>上架结束时间
    private Date downShelveBeginTime;//查询条件===>下架开始时间
    private Date downShelveEndTime;//查询条件===>下架结束时间
    private String teacherName;//查询条件=====>主讲教师 (模糊查询)
    private Date beganSellTimeBegin;//查询条件===》开销开始时间
    private Date beganSellTimeEnd;//查询条件===》开销结束时间
    private Date stopSellTimeBegin;//查询条件===》停销开始时间
    private Date stopSellTimeEnd;//查询条件===》停销结束时间
    private String isSelling;//查询条件===》是否在售
    private Integer isProtocal;//查询条件===》是否保过

	public Date getBeganSellTimeBegin() {
		return beganSellTimeBegin;
	}

	public void setBeganSellTimeBegin(Date beganSellTimeBegin) {
		this.beganSellTimeBegin = beganSellTimeBegin;
	}

	public Date getBeganSellTimeEnd() {
		return beganSellTimeEnd;
	}

	public void setBeganSellTimeEnd(Date beganSellTimeEnd) {
		this.beganSellTimeEnd = beganSellTimeEnd;
	}

	public Date getStopSellTimeBegin() {
		return stopSellTimeBegin;
	}

	public void setStopSellTimeBegin(Date stopSellTimeBegin) {
		this.stopSellTimeBegin = stopSellTimeBegin;
	}

	public Date getStopSellTimeEnd() {
		return stopSellTimeEnd;
	}

	public void setStopSellTimeEnd(Date stopSellTimeEnd) {
		this.stopSellTimeEnd = stopSellTimeEnd;
	}

	public Date getUpShelveBeginTime() {
		return upShelveBeginTime;
	}

	public void setUpShelveBeginTime(Date upShelveBeginTime) {
		this.upShelveBeginTime = upShelveBeginTime;
	}

	public Date getUpShelveEndTime() {
		return upShelveEndTime;
	}

	public void setUpShelveEndTime(Date upShelveEndTime) {
		this.upShelveEndTime = upShelveEndTime;
	}

	public Date getDownShelveBeginTime() {
		return downShelveBeginTime;
	}

	public void setDownShelveBeginTime(Date downShelveBeginTime) {
		this.downShelveBeginTime = downShelveBeginTime;
	}

	public Date getDownShelveEndTime() {
		return downShelveEndTime;
	}

	public void setDownShelveEndTime(Date downShelveEndTime) {
		this.downShelveEndTime = downShelveEndTime;
	}

	public int getSellId() {
		return sellId;
	}

	public void setSellId(int sellId) {
		this.sellId = sellId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public String getSellName() {
		return sellName;
	}

	public void setSellName(String sellName) {
		this.sellName = sellName;
	}

	public String getStatus() {
		return status;
	}

	public java.util.Date getAddTime() {
		return addTime;
	}

	public void setAddTime(java.util.Date addTime) {
		this.addTime = addTime;
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

	public void setTeacher(String teacher) throws UnsupportedEncodingException {
		if(teacher.equals("0")){
		this.teacher ="";
		}else{
			this.teacher =URLDecoder.decode(teacher,"utf-8").replace(" ", "");;
		}
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

	public java.util.Date getDropTime() {
		return dropTime;
	}

	public void setDropTime(java.util.Date dropTime) {
		this.dropTime = dropTime;
	}

	public String getShopState() {
		return shopState;
	}

	public void setShopState(String shopState) {
		this.shopState = shopState;
	}

	public String getTimeVersion() {
		return timeVersion;
	}

	public void setTimeVersion(String timeVersion) {
		this.timeVersion = timeVersion;
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

	public String getImagesUrl() {
		return imagesUrl;
	}

	public void setImagesUrl(String imagesUrl) {
		this.imagesUrl = imagesUrl;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) throws UnsupportedEncodingException {
		this.keyword =URLDecoder.decode(keyword,"utf-8").replace(" ", "");
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) throws UnsupportedEncodingException {
		this.word =URLDecoder.decode(word,"utf-8").replace(" ", "");
	}

	private String startCountTime; //开始时间
	private String endCountTime; //结束时间
	public String getStartCountTime() {
		return startCountTime;
	}

	public void setStartCountTime(String startCountTime) {
		this.startCountTime = startCountTime;
	}

	public String getEndCountTime() {
		return endCountTime;
	}

	public void setEndCountTime(String endCountTime) {
		this.endCountTime = endCountTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getIsSelling() {
		return isSelling;
	}

	public void setIsSelling(String isSelling) {
		this.isSelling = isSelling;
	}

	public Integer getIsProtocal() {
		return isProtocal;
	}

	public void setIsProtocal(Integer isProtocal) {
		this.isProtocal = isProtocal;
	}

	public Integer getSellType() {
		return sellType;
	}

	public void setSellType(Integer sellType) {
		this.sellType = sellType;
	}
	
	
	
	

}