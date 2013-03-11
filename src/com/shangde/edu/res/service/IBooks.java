package com.shangde.edu.res.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.res.domain.Books;
import com.shangde.edu.res.condition.QueryBooksCondition;
/**  

 * @author miaoshusen

 * @version 1.0

 */
public interface IBooks {

	/**
	 * 添加书籍的方法
	 */
	public java.lang.Integer addBooks(Books books);

	/**
	 * 根据书籍id删除书籍的方法
	 */
	public void delBooksById(int bkId);

	/**
	 * 修改书籍的方法
	 */
	public void updateBooks(Books books);

	/**
	 * 根据书籍id来获取书籍对象的方法
	 */
	public Books getBooksById(int bkId);

	/**
	 * 分页查询返回PageResult对象
	 */
	public PageResult getBooksList(QueryBooksCondition queryBooksCondition);

	/**
	 * 根据书籍组的id查询返回一个书籍类类型的list
	 */
	public <Books> List getBooksByBgId(int bgId);
	
	/**
	 * 按照课程ID获取书记集合
	 * @param courseId
	 * @return
	 */
	public List<Books> getBooksListByCourseId(int courseId);
	
	//根据知识点来查询下面的书籍
	public List<Books> getBooksListByPointId(int pointId);

}