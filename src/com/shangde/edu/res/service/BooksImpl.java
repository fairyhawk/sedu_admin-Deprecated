package com.shangde.edu.res.service;

import java.util.List;
import com.shangde.edu.res.domain.Books;
import com.shangde.edu.res.condition.QueryBooksCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class BooksImpl extends BaseService implements IBooks{
    public java.lang.Integer addBooks(Books books) {
return simpleDao.createEntity("Books_NS.createBooks",books);
    }

    public void delBooksById(int bkId){
        simpleDao.deleteEntity("Books_NS.deleteBooksById",bkId);
    }

    public void updateBooks(Books books) {
        simpleDao.updateEntity("Books_NS.updateBooks",books);
    }

    public Books getBooksById(int bkId) {
        return simpleDao.getEntity("Books_NS.getBooksById",bkId);
    }

    public PageResult getBooksList(QueryBooksCondition queryBooksCondition) {
        return simpleDao.getPageResult("Books_NS.getBooksList",
				"Books_NS.getBooksListCount", queryBooksCondition);
    }

	public <Books> List getBooksByBgId(int bgId) {
		
		return simpleDao.getForList("Books_NS.getBooksByBgId", bgId);
	}
	
	/**
	 * 按课程ID获取书籍集合
	 * @param courseId
	 * @return
	 */
	public List<Books> getBooksListByCourseId(int courseId) {
		
		return simpleDao.getForList("Books_NS.getBooksListByCourseId", courseId);
	}

	public List<Books> getBooksListByPointId(int pointId) {
		
		return simpleDao.getForList("Books_NS.getBooksListByPointId", pointId);
	}
	
	
}
