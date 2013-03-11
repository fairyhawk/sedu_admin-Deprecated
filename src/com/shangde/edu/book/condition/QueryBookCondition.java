package com.shangde.edu.book.condition;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryBookCondition extends PageQuery{

   private Integer bookId;
   /**
	 *   专业ID
	 **/
   private Integer subjectId;
   /**
	 *  图书类别
	 **/
   private String bookType;
   /**
	 *  图书状态
	 **/
   private Integer status=-1;
   
   /**
    * 上下架状态
    */
   private Integer shopState=-1;
   /**
	 *  添加时间
	 **/
   private java.util.Date addTime;
   /**
    * 上架时间
    */
   private Date upTime;
   /**
    * 下架时间
    */
   private java.util.Date  dropTime;
   
   /**
    * 关键字
    * @return
    */
   private String keyword;
  
   /**
    * 显示属性
    */
   private String disProperty;

public Integer getBookId() {
	return bookId;
}

public void setBookId(Integer bookId) {
	this.bookId = bookId;
}

public Integer getSubjectId() {
	return subjectId;
}

public void setSubjectId(Integer subjectId) {
	this.subjectId = subjectId;
}

public String getBookType() {
	return bookType;
}

public void setBookType(String bookType) {
	this.bookType = bookType;
}

public Integer getStatus() {
	return status;
}

public void setStatus(Integer status) {
	this.status = status;
}

public Integer getShopState() {
	return shopState;
}

public void setShopState(Integer shopState) {
	this.shopState = shopState;
}

public java.util.Date getAddTime() {
	return addTime;
}

public void setAddTime(java.util.Date addTime) {
	this.addTime = addTime;
}

public Date getUpTime() {
	return upTime;
}

public void setUpTime(Date upTime) {
	this.upTime = upTime;
}

public java.util.Date getDropTime() {
	return dropTime;
}

public void setDropTime(java.util.Date dropTime) {
	this.dropTime = dropTime;
}

public String getKeyword() {
	return keyword;
}

public void setKeyword(String keyword) throws UnsupportedEncodingException {
	this.keyword = URLDecoder.decode(keyword,"utf-8").replace(" ", "");;
}

public String getDisProperty() {
	return disProperty;
}

public void setDisProperty(String disProperty) {
	this.disProperty = disProperty;
}



 
}