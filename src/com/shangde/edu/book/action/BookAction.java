package com.shangde.edu.book.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.book.condition.QueryBookCondition;
import com.shangde.edu.book.condition.QueryBuyInfoCondition;
import com.shangde.edu.book.domain.Book;
import com.shangde.edu.book.domain.BuyInfo;
import com.shangde.edu.book.dto.BookDTO;
import com.shangde.edu.book.service.IBook;
import com.shangde.edu.book.service.IBuyInfo;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;

public class BookAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(BookAction.class);
	
	IBuyInfo buyInfoService = null;
	IBook bookService = null;
	BuyInfo buyInfo = null;
	QueryBuyInfoCondition buyInfocondition = new QueryBuyInfoCondition();
	QueryBookCondition bookCondition = new QueryBookCondition();
	private List<Subject> subjectList = new ArrayList<Subject>();
	Book book = new Book();
	String[] disProperty;
	String status;
	String url;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	//批量
	String Batch;

	public String getBatch() {
		return Batch;
	}

	public void setBatch(String batch) {
		Batch = batch;
	}
	/**
	 * 专业服务
	 */
	private ISubject subjectService;
	List<Book> bookList = new ArrayList<Book>();
	List<BookDTO> bookDTOList = new ArrayList<BookDTO>();
	/* *
	 * id数组
	 */
	private int[] ids;

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public QueryBuyInfoCondition getBuyInfocondition() {
		return buyInfocondition;
	}

	public void setBuyInfocondition(QueryBuyInfoCondition buyInfocondition) {
		this.buyInfocondition = buyInfocondition;
	}

	public BuyInfo getBuyInfo() {
		return buyInfo;
	}

	public void setBuyInfo(BuyInfo buyInfo) {
		this.buyInfo = buyInfo;
	}

	public IBuyInfo getBuyInfoService() {
		return buyInfoService;
	}

	public void setBuyInfoService(IBuyInfo buyInfoService) {
		this.buyInfoService = buyInfoService;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public QueryBookCondition getBookCondition() {
		return bookCondition;
	}

	public void setBookCondition(QueryBookCondition bookCondition) {
		this.bookCondition = bookCondition;
	}

	public List<BookDTO> getBookDTOList() {
		return bookDTOList;
	}

	public void setBookDTOList(List<BookDTO> bookDTOList) {
		this.bookDTOList = bookDTOList;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public IBook getBookService() {
		return bookService;
	}

	public void setBookService(IBook bookService) {
		this.bookService = bookService;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String[] getDisProperty() {
		return disProperty;
	}

	public void setDisProperty(String[] disProperty) {
		this.disProperty = disProperty;
	}

	/**
	 * 添加图书订单
	 * 
	 * @return
	 */
	public String addBuyInfo() {
		try {
			buyInfo.setCreatetime(new Date());
			buyInfoService.addBook(buyInfo);
			buyInfo.setCusId(666);
			return "success";
		} catch (Exception ex) {
			logger.error("BookAction.addBuyInfo",ex);
			return "error";
		}
	}

	/**
	 * 查询所有订单
	 * 
	 * @return
	 */
	public String GetBuyInfoList() {
		try {
			buyInfocondition.setPageSize(20);
			setPage(buyInfoService.getBookList(buyInfocondition));
			if(url==null){
				setPageUrlParms();				
			}
			if (getPage() != null) {
				getPage().setPageSize(20);
			}
			return "buyinfolist";
		} catch (Exception ex) {
			logger.error("BookAction.GetBuyInfoList",ex);
			return "error";
		}
	}

	/**
	 * 进入到图书增加页面
	 * 
	 * @return
	 */
	public String toAddBook() {
		try{
			subjectList = subjectService.getAllSubject();
			return "addbook";
		}catch(Exception e){
			logger.error("BookAction.toAddBook",e);
			return "error";
		}
	}

	/**
	 * 增加图书
	 */
	public String AddBook() {
		try {
			User user = (User) servletRequest.getSession().getAttribute(
					"sedu_user");
			if (user != null) {
				book.setUpdateUser(user.getLoginName());
			}
			if (book.getShopState() == 1) {
				book.setUpTime(new Date());
			}
			String[] disProperty = servletRequest
					.getParameterValues("disProperty");
			String disPropertys = "";
			if (disProperty != null && disProperty.length != 0) {
				for (String dis : disProperty) {
					disPropertys += (dis + ",");
				}
			}
			book.setDisproperty(disPropertys);
			book.setAddTime(new Date());
			book.setUpdateTime(new Date());
			book.setStatus(1);
			if(book.getShopState()==2)
			{
				book.setDropTime(new Date());
			}
			if (book.getWeight() != null)
				book.setWeight(book.getWeight()
						+ servletRequest.getParameter("qianke"));
			bookService.addBook(book);
			if (servletRequest.getParameter("submitState") != null
					&& servletRequest.getParameter("submitState").equals("1")) {
				return toAddBook();
			} else {
				return GetBookList();
			}
		} catch (Exception e) {
			logger.error("BookAction.AddBook",e);
			return "error";
		}
	}

	/**
	 * 获取图书列表
	 * 
	 * @return
	 */
	public String GetBookList() {
		try {
			subjectList = subjectService.getAllSubject();
			String keywordValue=this.getBookCondition().getKeyword();
			if(keywordValue!=null&&!"".equals(keywordValue)){
				this.getBookCondition().setKeyword((URLDecoder.decode(keywordValue, "UTF-8")).trim());
			}
			setPage(bookService.getBookList(this.getBookCondition()));
			setPageUrlParms();
			return "boolist";
		} catch (Exception e) {
			logger.error("BookAction.GetBookList",e);
			return "boolist";
		}
	}

	/**
	 * 获取图书列表条件查询
	 * 
	 * @return
	 */
	public String showBookListByWhere() {
		try {
			if (bookCondition == null) {
				bookCondition = new QueryBookCondition();
				bookCondition.setCurrentPage(1);
			}
			String keywordValue=this.getBookCondition().getKeyword();
			if(keywordValue!=null&&!"".equals(keywordValue)){
				this.getBookCondition().setKeyword((URLDecoder.decode(keywordValue, "UTF-8")).trim());
			}
			subjectList = subjectService.getAllSubject();
			setPage(bookService.showBookListByWhere(this.getBookCondition()));
			setPageUrlParms();
			return "boolist";
		} catch (Exception e) {
			logger.error("BookAction.showBookListByWhere",e);
			return "boolist";
		}
	}

	public String updateBookStatus() {
		try {
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			map.put("bookId", book.getBookId());
			map.put("status", book.getStatus());
			bookService.updateBookStatus(map);
		} catch (Exception e) {
			logger.error("BookAction.updateBookStatus",e);
			return ERROR;
		}
		if (bookCondition == null) {
			bookCondition = new QueryBookCondition();
			bookCondition.setCurrentPage(1);
		}
		return GetBookList();// 调取查询页面
	}

	/**
	 * 更新多个图书状态
	 * 
	 * @return
	 */
	public String UpdateBookByStateForMore() {
		try {

			int status = book.getStatus();
			if (ids.length > 0) {
				for (int bookId : ids) {

					HashMap<String, Integer> map = new HashMap<String, Integer>();
					map.put("bookId", bookId);
					map.put("status", status);
					bookService.updateBookStatus(map);
				}
			}
			return GetBookList();
		} catch (Exception ex) {
			logger.error("BookAction.UpdateBookByStateForMore",ex);
			return "error";
		}
	}

	/**
	 * 更新商品上下架状态
	 * 
	 * @return
	 */
	public String updateShopState() {
		try {
			if (ids.length > 0) {
				for (int bookId : ids) {
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					map.put("bookId", bookId);
					map.put("shopState", book.getShopState());
					bookService.updateBookShopState(map);
				}
			}
		} catch (Exception e) {
			logger.error("BookAction.updateShopState",e);
			return ERROR;
		}
		if (bookCondition == null) {
			bookCondition = new QueryBookCondition();
			bookCondition.setCurrentPage(1);
		}
		return GetBookList();// 调取查询页面
	}

	/**
	 * 去图书更新页面
	 * 
	 * @return
	 */
	public String toUpdateBook() {
		try {
			subjectList = subjectService.getAllSubject();
			book = bookService.getBookById(book.getBookId());
		} catch (Exception e) {
			logger.error("BookAction.toUpdateBook",e);
			return ERROR;
		}
		return "updatebook";
	}

	/**
	 * 更新图书
	 * 
	 * @return
	 */
	public String updateBook() {
		try {
			User user = (User) servletRequest.getSession().getAttribute(
					"sedu_user");
			if (user != null) {
				book.setUpdateUser(user.getLoginName());
			}
			
			String[] disProperty = servletRequest
					.getParameterValues("disProperty");
			String disPropertys = "";
			if (disProperty != null && disProperty.length != 0) {
				for (String dis : disProperty) {
					disPropertys += (dis + ",");
				}
			}
			String myShopState=servletRequest.getParameter("myshopstate");
			if(!myShopState.equals(book.getShopState().toString()))
			{
			if(book.getShopState()==2){
				book.setDropTime(new Date());
			}
			if (book.getShopState() == 1) {
				book.setUpTime(new Date());
			}
			}
			book.setDisproperty(disPropertys);
			book.setUpdateTime(new Date());
			if (book.getWeight() != null)
				book.setWeight(book.getWeight()
						+ servletRequest.getParameter("qianke"));
			bookService.updateBook(book);
		} catch (Exception e) {
			logger.error("BookAction.updateBook",e);
			return ERROR;
		}
		return GetBookList();
	}

	/**
	 * 去图书更新页面
	 * 
	 * @return
	 */
	public String bookInfo() {
		try {
			subjectList = subjectService.getAllSubject();
			book = bookService.getBookById(book.getBookId());
		} catch (Exception e) {
			logger.error("BookAction.updateBook",e);
			return ERROR;
		}
		return "bookinfo";
	}
	
	/**
	 * 更新状态(删除)
	 * 时间：2011-11-11 16:00
	 * wd
	 */
	public String bookInfoUpdate(){
		String id="";
		Map<String,String> map=new HashMap<String,String>();
		try{
//			int num=url.indexOf("?");
//			if(num!=-1){
//				url=url.substring(0,num);
//			}
			setPageUrlParms(url);
			if("Batch".equals(Batch)){
				if (ids.length > 0) {
					for (int book : ids) {
						Map<String,String> mapBatch = new HashMap<String,String>();
						mapBatch.put("id", String.valueOf(book));
						mapBatch.put("status", status);
						buyInfoService.updateBookStatus(mapBatch);
					}
				}
			}else{			
				if(buyInfo!=null){
					id=String.valueOf(buyInfo.getId());
				}
				map.put("status",status);
				map.put("id",id);
				buyInfoService.updateBookStatus(map);			
			}
//			buyInfocondition=new QueryBuyInfoCondition();
//			buyInfocondition.setCurrentPage(1);
			return GetBuyInfoList();
		}catch(Exception e){
			System.out.println(e.toString());
			return "error";
		}
	}
}
