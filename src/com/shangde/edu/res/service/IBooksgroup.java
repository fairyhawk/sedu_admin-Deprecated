package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.res.domain.Booksgroup;
import com.shangde.edu.res.condition.QueryBooksgroupCondition;

/**  

 * @author miaoshusen

 * @version 1.0

 */
public interface IBooksgroup {
	/**
	 * 添加书籍组的方法
	 */
	public java.lang.Integer addBooksgroup(Booksgroup booksgroup);

	/**
	 * 根据书籍组id来删除书籍组的方法
	 */
	public void delBooksgroupById(int bgId);

	/**
	 * 修改书籍组的方法
	 */
	public void updateBooksgroup(Booksgroup booksgroup);

	/**
	 * 根据书籍组id来获得书籍组对象的方法
	 */
	public Booksgroup getBooksgroupById(int bgId);

	/**
	 * 分页查询返回PageResult对象
	 */
	public PageResult getBooksgroupList(
			QueryBooksgroupCondition queryBooksgroupCondition);
	/**
	 * 获得书籍组list
	 * 
	 */
	public List<Booksgroup> getBooksgroupByList(QueryBooksgroupCondition queryBooksgroupCondition);
}