package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.res.domain.Picture;
import com.shangde.edu.res.condition.QueryPictureCondition;

/**  

 * @author miaoshusen

 * @version 1.0

 */
public interface IPicture {
	/**
	 * 添加图片的方法
	 */
	public java.lang.Integer addPicture(Picture picture);

	/**
	 * 根据图片id来删除图片的方法
	 */
	public void delPictureById(int picId);

	/**
	 * 修改图片的方法
	 */
	public void updatePicture(Picture picture);

	/**
	 * 根据图片id来获得图片的对象的方法
	 */
	public Picture getPictureById(int picId);

	/**
	 * 分页查询返回PageResult对象
	 */
	public PageResult getPictureList(QueryPictureCondition queryPictureCondition);
	/**
	 * 根据视频id查询返回图片类类型的list
	 */
	public <Picture> List getPictureByVoId(int voId);
	/**
	 * 根据书籍id查询返回图片类类型的list
	 */
	public <Picture> List getPictureByBkId(int bkId);
}