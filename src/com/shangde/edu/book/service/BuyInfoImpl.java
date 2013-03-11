package com.shangde.edu.book.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shangde.edu.book.domain.BuyInfo;
import com.shangde.edu.book.condition.QueryBuyInfoCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;

/**
 * User:guoqiang.liu
 * Date:2011-09-19
 * */
@SuppressWarnings("unchecked")
public class BuyInfoImpl extends BaseService implements IBuyInfo{

	public void  addBook(BuyInfo buyInfo) {
     simpleDao.createEntity("BookBuyInfo_NS.createBook",buyInfo);
    }

    public void delBookById(){
    }

    public void updateBook(BuyInfo book) {
        simpleDao.updateEntity("BookBuyInfo_NS.updateBook",book);
    }

    public BuyInfo getBookById() {
    	return null;
    }

    public PageResult getBookList(QueryBuyInfoCondition queryBookCondition) {
        return simpleDao.getPageResult("BookBuyInfo_NS.getBookList","BookBuyInfo_NS.getBookListCount",queryBookCondition);
    }

	public void updateBookStatus(Map<String, String> map) {
		simpleDao.updateEntity("BookBuyInfo_NS.updateBookStatus",map);
	}
}
