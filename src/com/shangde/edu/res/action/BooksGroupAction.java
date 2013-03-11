package com.shangde.edu.res.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.exception.CommException;
import com.shangde.edu.res.condition.QueryBooksCondition;
import com.shangde.edu.res.condition.QueryBooksgroupCondition;
import com.shangde.edu.res.domain.Books;
import com.shangde.edu.res.domain.Booksgroup;
import com.shangde.edu.res.service.IBooks;
import com.shangde.edu.res.service.IBooksgroup;

/**
 * 书籍组管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class BooksGroupAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(BooksGroupAction.class);
	/**
	 * 声名书籍组的PO对象
	 */
	private Booksgroup booksGroup;
	/**
	 * 声名书籍组的服务
	 */
	private IBooksgroup booksgroupService;
	/**
	 * 查询用到的condition
	 */
	private QueryBooksgroupCondition queryBooksgroupCondition;
	/**
	 * 声名书籍
	 */
	private Books books;
	/**
	 * 声名书籍的服务
	 */
	private IBooks booksService;
	/**
	 * 查询用到的condition
	 */
	private QueryBooksCondition queryBooksCondition;
	/**
	 * 声名一个整型的数组
	 */
	private int[] bkId;
	/**
	 * 声名一个Books类型的list
	 */
	private List<Books> list;
	/**
	 * 查询条件
	 */
	private String searchKey;
	/**
	 * 声名Booksgroup类型的list
	 * 
	 */
	private List<Booksgroup> booksgroupList;
	
	

	public List<Booksgroup> getBooksgroupList() {
		return booksgroupList;
	}

	public void setBooksgroupList(List<Booksgroup> booksgroupList) {
		this.booksgroupList = booksgroupList;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public List<Books> getList() {
		return list;
	}

	public void setList(List<Books> list) {
		this.list = list;
	}

	public int[] getBkId() {
		return bkId;
	}

	public void setBkId(int[] bkId) {
		this.bkId = bkId;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}

	public IBooks getBooksService() {
		return booksService;
	}

	public void setBooksService(IBooks booksService) {
		this.booksService = booksService;
	}

	public QueryBooksCondition getQueryBooksCondition() {
		if (queryBooksCondition == null) {
			queryBooksCondition = new QueryBooksCondition();

		}
		return queryBooksCondition;
	}

	public void setQueryBooksCondition(QueryBooksCondition queryBooksCondition) {
		this.queryBooksCondition = queryBooksCondition;
	}

	public Booksgroup getBooksGroup() {
		return booksGroup;
	}

	public void setBooksGroup(Booksgroup booksGroup) {
		this.booksGroup = booksGroup;
	}

	public IBooksgroup getBooksgroupService() {
		return booksgroupService;
	}

	public void setBooksgroupService(IBooksgroup booksgroupService) {
		this.booksgroupService = booksgroupService;
	}

	public QueryBooksgroupCondition getQueryBooksgroupCondition() {
		if (queryBooksgroupCondition == null) {
			queryBooksgroupCondition = new QueryBooksgroupCondition();

		}
		return queryBooksgroupCondition;
	}

	public void setQueryBooksgroupCondition(
			QueryBooksgroupCondition queryBooksgroupCondition) {
		this.queryBooksgroupCondition = queryBooksgroupCondition;
	}

	/**
	 * 获得书籍组列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getBooksGroupList() {
		try {
			if(searchKey!=null&&!"".equals(searchKey.trim())){
				this.getQueryBooksgroupCondition().setSearchKey(searchKey.trim());
			}
			setPage(this.booksgroupService
					.getBooksgroupList(getQueryBooksgroupCondition()));
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("BooksGroupAction.getBooksGroupList", e);
			return ERROR;
		}
		return "listBooksgroup";

	}

	/**
	 * 添加书籍组
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addBooksGroup() {
		try {
			Date date = new Date();
			booksGroup.setCreateTime(date);
			this.booksgroupService.addBooksgroup(booksGroup);

			// 更新书籍表里视频组的字段
			List bookslist = this.getSession("bookslist");
			if (bookslist != null) {
				for (int i = 0; i < bookslist.size(); i++) {
					books = this.booksService.getBooksById((Integer) bookslist
							.get(i));
					books.setBgId(booksGroup.getBgId());
					this.booksService.updateBooks(books);

				}
			}
		} catch (Exception e) {
			logger.error("BooksGroupAction.addBooksGroup", e);
			return ERROR;
		}
		return "listAllBooksGroup";
	}

	/**
	 * 把书添加到组，打开书籍列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toAddBooksList() {
		try {
			setPage(this.booksService.getBooksList(getQueryBooksCondition()));
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("BooksGroupAction.toAddBooksList", e);
			return ERROR;
		}
		return "toAddBooksList";

	}

	/**
	 * 在选择添加到哪个组的页面，打开创建组的页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toAddBooksGroupNew() {
		try {
			List bookslist = new ArrayList();
			int[] bkId = this.getBkId();
			if (bkId != null) {
				for (int i = 0; i < bkId.length; i++) {

					bookslist.add(bkId[i]);

				}
				this.setSession("bookslist", bookslist);
			}
//			setPage(this.booksgroupService
//					.getBooksgroupList(getQueryBooksgroupCondition()));
			booksgroupList=this.booksgroupService.getBooksgroupByList(getQueryBooksgroupCondition());
		} catch (Exception e) {
			logger.error("BooksGroupAction.toAddBooksGroupNew", e);
			return ERROR;
		}
		return "toAddBooksGroupNew";
	}

	/**
	 * 把书添加到，在选择添加哪个组的面中，新创建的组里面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addBooksGroupNew() {
		try {
			// 更新视频表里视频组的字段
			List bookslist = this.getSession("bookslist");
			if (bookslist != null) {
				for (int i = 0; i < bookslist.size(); i++) {
					books = this.booksService.getBooksById((Integer) bookslist
							.get(i));
					books.setBgId(booksGroup.getBgId());
					this.booksService.updateBooks(books);
				}
			}
		} catch (Exception e) {
			logger.error("BooksGroupAction.addBooksGroupNew", e);
			return ERROR;
		}
		return "listAllBooksGroup";
	}

	/**
	 * 把书籍添加到组
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toAddBooksGroup() {
		try {
			List bookslist = new ArrayList();
			int[] bkId = this.getBkId();
			if (bkId != null) {
				for (int i = 0; i < bkId.length; i++) {

					bookslist.add(bkId[i]);

				}
				this.setSession("bookslist", bookslist);

			}
		} catch (Exception e) {
			logger.error("BooksGroupAction.toAddBooksGroup", e);
			return ERROR;
		}
		return "toAddBooksgroup";
	}

	/**
	 * 打开修改书籍组的页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toEditBooksGroup() {
		try {
			booksGroup = this.booksgroupService.getBooksgroupById(booksGroup
					.getBgId());
		} catch (Exception e) {
			logger.error("BooksGroupAction.toEditBooksGroup", e);
			return ERROR;
		}
		return "toEditBooksGroup";

	}

	/**
	 * 修改书籍组的信息
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editBooksGroup() {
		try {
			Date date = new Date();
			booksGroup.setCreateTime(date);
			this.booksgroupService.updateBooksgroup(booksGroup);
		} catch (Exception e) {
			logger.error("BooksGroupAction.editBooksGroup", e);
			return ERROR;
		}
		return "listAllBooksGroup";

	}

	/**
	 * 在每一个组的名称下面，显示出属于这个组的书籍
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String viewBooksList() {
		try {
			list = this.booksService.getBooksByBgId(booksGroup.getBgId());
		} catch (Exception e) {
			logger.error("BooksGroupAction.viewBooksList", e);
			return ERROR;
		}
		return "viewBooksList";

	}
	/**
	 * 删除书籍组，把组下面的书箱，关联关系删除
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteBooksGroup() {
		try {
			Books books = null;
			if (booksGroup.getBgId() != 0) {
				// 修改书籍表中的书籍组的ＩD
				List<Books> booksList = this.booksService
						.getBooksByBgId(booksGroup.getBgId());
				for (int i = 0; booksList != null && i < booksList.size(); i++) {
					books = new Books();
					books = booksList.get(i);
					books.setBgId(0);
					this.booksService.updateBooks(books);
				}
				// 删除书箱组
				this.booksgroupService.delBooksgroupById(booksGroup.getBgId());
			}
		} catch (Exception e) {
			logger.error("BooksGroupAction.deleteBooksGroup", e);
			return ERROR;
		}
		return "listAllBooksGroup";
	}
	
}
