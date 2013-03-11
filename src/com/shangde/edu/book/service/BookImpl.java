package com.shangde.edu.book.service;

import java.util.HashMap;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.book.condition.QueryBookCondition;
import com.shangde.edu.book.domain.Book;


/**
 * User:guoqiang.liu
 * Date:2011-09-19
 * */
@SuppressWarnings("unchecked")
public class BookImpl extends BaseService implements IBook{
    public  void addBook(Book book) {
    simpleDao.createEntity("Book_NS.createBook",book);
    }

    public void delBookById(){
    }

    public void updateBook(Book book) {
        simpleDao.updateEntity("Book_NS.updateBook",book);
    }

    public Book getBookById(int bookId) {
		return simpleDao.getEntity("Book_NS.getBookById", bookId);
    }

    public PageResult getBookList(QueryBookCondition queryBookCondition) {
        return simpleDao.getPageResult("Book_NS.getBookList","Book_NS.getBookListCount",queryBookCondition);
    }
    
    public void updateBookStatus(HashMap map){
    	simpleDao.update("Book_NS.updateBookStatus", map);
    }
    
    public void updateBookShopState(HashMap map){
    	simpleDao.update("Book_NS.updateBookShopState", map);
    }
    
    public PageResult showBookListByWhere(QueryBookCondition queryBookCondition) {
        return simpleDao.getPageResult("Book_NS.showBookListByWhere","Book_NS.showBookListByWhereCount",queryBookCondition);
    }
    
    
}
