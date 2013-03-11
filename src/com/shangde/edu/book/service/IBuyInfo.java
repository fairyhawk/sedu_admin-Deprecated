package com.shangde.edu.book.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.book.domain.BuyInfo;
import com.shangde.edu.book.condition.QueryBuyInfoCondition;

/**
 * User:guoqiang.liu
 * Date:2011-09-19
 * */
public interface IBuyInfo {
	/**
	 * @parm Book book
	 * @return id
	 * 
	 */
    public void addBook(BuyInfo book);
    /**
	 * 删除
	 * 
	 */
    public void delBookById();

    /**
	 * @param Book book
	 * 更新图书
	 */
    public void updateBook(BuyInfo book);
    /**
     * 
     *@param bookId
     *根据bookId查
     *@return Book 
     */
    public BuyInfo getBookById();
    /**
     *@param queryBookCondition
     *@return PageResult 
     */
    public PageResult getBookList(QueryBuyInfoCondition queryBookCondition);
    
    /**
     * 更新图书状态
     * @param map
     * wd
     */
    public void updateBookStatus(Map<String, String> map);
}