package com.shangde.edu.book.service;

import java.util.HashMap;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.book.condition.QueryBookCondition;
import com.shangde.edu.book.domain.Book;

/**
 * User:guoqiang.liu
 * Date:2011-09-19
 * */
public interface IBook {
   
	/**
	 * @parm Book book
	 * @return id
	 * 
	 */
    public  void addBook(Book book);

    /**
	 * 删除
	 * 
	 */
    public void delBookById();

    /**
	 * @param Book book
	 * 更新图书
	 */
    public void updateBook(Book book);

   /**
    * 
    *@param bookId
    *根据bookId查
    *@return Book 
    */
    public Book getBookById(int bookId);
    
    /**
     *@param queryBookCondition
     *@return PageResult 
     */
   
    public PageResult getBookList(QueryBookCondition queryBookCondition);
    
    /**
     * 更新图书状态
     * @param bookid
     * @param map
     */
    public void updateBookStatus(HashMap map);
    
    /**
     * 更新商品上下架状态
     * @param map
     */
    public void updateBookShopState(HashMap map);
    
    /**
     * 条件查询图书
     * @param queryBookCondition
     * @return
     */
    public PageResult showBookListByWhere(QueryBookCondition queryBookCondition);
}