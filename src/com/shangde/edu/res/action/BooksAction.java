package com.shangde.edu.res.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.condition.QueryKpointCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.domain.Kpoint;
import com.shangde.edu.cou.dto.KeyValueDTO;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cou.service.IKpoint;
import com.shangde.edu.res.condition.QueryBooksCondition;
import com.shangde.edu.res.domain.Books;
import com.shangde.edu.res.domain.Picture;
import com.shangde.edu.res.service.IBooks;
import com.shangde.edu.res.service.IPicture;

/**
 * 书籍管理action
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class BooksAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(BooksAction.class);
	/**
	 * 声名书籍的PO对象
	 */
	private Books books;
	/**
	 * 声名书籍服务
	 */
	private IBooks booksService;
	/**
	 * 查询用到的condition
	 */
	private QueryBooksCondition queryBooksCondition;
	/**
	 * 声名一个整型数组
	 */
	private int[] bkId;
	
	/**
	 * 查询条件
	 */
	private String searchKey;
	
	/**
	 * 课程分类查询条件
	 */
	private QueryCoursesortCondition queryCourseSortCondition;

	/**
	 * 课程分类集
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 课程Id
	 */
	private int courseId;
	
	/**
	 * 知识点服务
	 */
	private IKpoint kpointService;;
	
	/**
	 * 知识点查询条件
	 */
	private QueryKpointCondition queryKpointCondition;
	/**
	 * 知识点集合
	 */
	private List<Kpoint> kpointList;
	/**
	 * 知识点对象
	 */
	private Kpoint kpoint;
	
	/**
	 * 声明图片的服务
	 */
	private IPicture pictureService;
	
	/**
	 * 图片用到的List
	 */
	private List<File> fileList=new ArrayList<File>(); 
	
	private List<String> fileListFileName=new ArrayList<String>();
	
	private List<Picture> pictureList;

	private int mainpicid;
	
	/**
	 * 视频附属图片
	 */
	private List<File> otherPics = new ArrayList<File>();
	
	private List<String> otherPicsFileName = new ArrayList<String>();
	
	/**
	 * 视频首图片
	 */
	private List<File> firstPic = new ArrayList<File>();
	
	private List<String> firstPicFileName = new ArrayList<String>();
	
	/**
	 * 视频已添加的视频附属图片
	 */
	private List<File> otherPicEd = new ArrayList<File>();
	
	private List<String> otherPicEdFileName = new ArrayList<String>();
	
	private int[] pic;//图片ids

	private int fuSortId = -2;
	private int suSortId;
	private int tuSortId;
	private int cuSortId;
	
	private String fuSortIdString;
	private String suSortIdString;
	private String tuSortIdString;
	private String cuSortIdString;
	
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	
	/**
	 * 获得书籍列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getBooksList() {
		try {
			if(searchKey!=null&&!"".equals(searchKey.trim())){
				this.getQueryBooksCondition().setSearchKey((URLDecoder.decode(searchKey, "UTF-8")).trim());
			}
			setPage(this.booksService.getBooksList(getQueryBooksCondition()));
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("BooksAction.getBooksList",e);
			return ERROR;
		}
		return "listBooks";

	}

	
	/**
	 * 打开添加书籍页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toAddBooks() {
		try{
			this.getQueryCourseSortCondition().setPId(-1);
			courseSortList = coursesortService.getChildCoursesortList(queryCourseSortCondition);
		}catch(Exception ex){
			logger.error("BooksAction.toAddBooks",ex);
			return ERROR;
		}
		return "toAddBooks";

	}

	/**
	 * 添加书籍
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addBooks() {
		try {
			String path="/upload"+this.getSavePath();
			Date date = new Date();
			books.setCreateTime(date);
			books.setBkAuthor("HighSo");
			books.setBarcode("barcode");
			if(books.getBkName()!=null&&!"".equals(books.getBkName())){
				books.setBkTitle(books.getBkName());
			}
			this.booksService.addBooks(books);
			if(this.getFileList()!=null){
				Picture picture=null;
				Picture pictureUp=null;
				List<String> nameList=new ArrayList<String>();
				for(int i=0;i<this.getFileList().size();i++){
					picture=new Picture();
					picture.setPicName("暂时不需要");
					if(i==0){
					picture.setIsIndex(Picture.PIC_MAIN_PIC);
					}else{
					picture.setIsIndex(Picture.PIC_OTHER_PIC);	
					}
					picture.setPicContent("aaa");
					picture.setBkId(books.getBkId());
					picture.setCreatTime(date);
					picture.setPicSize(String.valueOf(this.getFileList().get(i).length()));
					String name=this.getFileListFileName().get(i);
					//String name=this.getFileList().get(i).getName();
					name=name.substring(name.indexOf("."),name.length());
					picture.setPicType(name);
					int pId=this.pictureService.addPicture(picture);
					pictureUp=this.pictureService.getPictureById(pId);
					String fileName=sdf.format(date)+"-"+pId+name;
					pictureUp.setPicUrl(path+"/"+fileName);
					pictureUp.setPicName(fileName);
					this.pictureService.updatePicture(pictureUp);
					nameList.add(fileName);
					
				}
				this.upload(nameList, fileList);
				
			}
			

			
			
		} catch (Exception e) {
			logger.error("BooksAction.addBooks",e);
			return ERROR;
		}
		return "listAllBooks";
	}
	
 
  
	/**
	 * 打开修改书籍页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toEditBooks() {
		try {
			books = this.booksService.getBooksById(books.getBkId());
			kpoint=this.kpointService.getKpointById(books.getPointid());
			this.getQueryCourseSortCondition().setPId(-1);
			courseSortList = coursesortService.getChildCoursesortList(queryCourseSortCondition);
			pictureList =this.pictureService.getPictureByBkId(books.getBkId());
			
			if(kpoint!=null){

				Course course = courseService.getCourseById(kpoint.getCourseId());
				
				Coursesort temp1 = coursesortService.getCoursesortById(course.getSortId());
				Coursesort temp2 = coursesortService.getCoursesortById(temp1.getPId());
				Coursesort temp3 = coursesortService.getCoursesortById(temp2.getPId());
				
				this.fuSortIdString = temp3.getCoursesortName();
				this.suSortIdString = temp2.getCoursesortName();
				this.tuSortIdString = temp1.getCoursesortName();
				this.cuSortIdString=course.getTitle();
				
				this.fuSortId = temp3.getCoursesortId();
				this.suSortId = temp2.getCoursesortId();
				this.tuSortId = temp1.getCoursesortId();
				this.cuSortId=course.getCourseId();
				
			}
		} catch (Exception e) {
			logger.error("BooksAction.toEditBooks",e);
			return ERROR;
		}
		return "toEditBooks";

	}

	/**
	 * 修改书籍
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editBooks() {
		try {
			Date date = new Date();
			books.setCreateTime(date);
			books.setBkAuthor("HighSo");
			books.setBarcode("barcode");
			if(books.getBkName()!=null&&!"".equals(books.getBkName())){
				books.setBkTitle(books.getBkName());
			}
			this.booksService.updateBooks(books);
			String isExistFile = servletRequest.getParameter("mainpic"
					+ mainpicid);// 主图片是否被修改
			List<String> filenames = new ArrayList<String>();

			Picture vpic = null;
			String fileName = null;
			String filePath = null;

			if (isExistFile != null && !isExistFile.equals("")) {// 主图片改动

				fileName = sdf.format(new Date()) + "-" + this.mainpicid
						+ this.getFileType(this.firstPicFileName.get(0));
				filePath = "/upload/res/book/" + fileName;
				vpic = pictureService.getPictureById(this.mainpicid);

				vpic.setPicType(this.getFileType(this.firstPicFileName.get(0)));
				vpic.setPicName(fileName);
				vpic.setPicUrl(filePath);
				// vpic.setCreatTime(date);
				// vpic.set大小
				this.pictureService.updatePicture(vpic);
				filenames.add(fileName);
				this.upload(filenames, this.firstPic);
				filenames = new ArrayList<String>();
			}
			if (pic != null) {
				for (int i = 0; i < this.pic.length; i++) {
					isExistFile = servletRequest.getParameter("otherPicEd"
							+ pic[i]);
					if (isExistFile != null && !isExistFile.equals("")) {// 附属图片改动

						vpic = pictureService.getPictureById(this.pic[i]);
						fileName = sdf.format(new Date())
								+ "-"
								+ pic[i]
								+ this.getFileType(this.otherPicEdFileName
										.get(0));
						filePath = "/upload/res/book/" + fileName;
						vpic.setPicType(this
								.getFileType(this.otherPicEdFileName.get(0)));
						vpic.setPicName(fileName);
						vpic.setPicUrl(filePath);
						filenames.add(fileName);
						pictureService.updatePicture(vpic);

					}
				}
				this.upload(filenames, otherPicEd);
			}

			
			filenames = new ArrayList<String>();

			File fileTemp = null;
			String filetype = null;

			int picid = 0;
			long filesize = 0;

			for (int i = 0; i < otherPics.size(); i++) {// 添加新的附属图片
				vpic = new Picture();

				fileTemp = otherPics.get(i);
				fileName = sdf.format(new Date());
				filePath = "/upload/res/book/" + fileName;
				filesize = fileTemp.length();
				filetype = this.getFileType(otherPicsFileName.get(i));
				vpic.setBkId(books.getBkId());
				vpic.setIsIndex(Picture.PIC_OTHER_PIC);
				vpic.setPicName(fileName);
				vpic.setPicUrl(filePath);
				vpic.setPicSize(String.valueOf(filesize));
				vpic.setPicType(filetype);
				vpic.setCreatTime(date);
				picid = pictureService.addPicture(vpic);
				vpic = pictureService.getPictureById(picid);

				fileName = fileName + "-" + picid + this.getFileType(filetype);
				filePath = filePath + "-" + picid + this.getFileType(filetype);
				vpic.setPicName(fileName);
				vpic.setPicUrl(filePath);
				filenames.add(fileName);
				pictureService.updatePicture(vpic);

				this.upload(filenames, otherPics);

			}
			
			
		} catch (Exception e) {
			logger.error("BooksAction.editBooks",e);
			return ERROR;
		}
		return "changeSuccess";

	}

	/**
	 * 删除单个书籍
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteBooks() {
		try {
			this.booksService.delBooksById(books.getBkId());
		} catch (Exception e) {
			logger.error("BooksAction.deleteBooks",e);
			return ERROR;
		}
		return "listAllBooks";
	}

	/**
	 * 删除多个书籍
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String deleteAllBooks() {
		try {
			if (bkId != null) {
				for (int i = 0; i < bkId.length; i++) {
					this.booksService.delBooksById(new Integer(bkId[i]));

				}

			}
		} catch (Exception e) {
			logger.error("BooksAction.deleteAllBooks",e);
			return ERROR;
		}
		return "listAllBooks";
	}
	/**
	 * 把书籍添加知识点上
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addBooksPoint(){
		try{
			this.getQueryKpointCondition().setCourseId(courseId);
			queryKpointCondition.setLeaf(-1);
			//kpointList=kpointService.getKpointListByCourseId(queryKpointCondition);
			List<KeyValueDTO> myList = kpointService.getKpointDTOListByCourseId(queryKpointCondition);
			//List<KeyValueDTO> myList = new ArrayList<KeyValueDTO>();
//			
//			KeyValueDTO keyvalue = null;
//			
//			for(int i=0; i < kpointList.size(); i++) {
//				keyvalue = new KeyValueDTO();
//				Kpoint grouptemp = kpointList.get(i); 
//				keyvalue.setKe(grouptemp.getPId());
//				keyvalue.setKey(grouptemp.getPointId());
//				keyvalue.setValue(grouptemp.getName());
//				myList.add(keyvalue);
//			}
			this.setResult(new Result<List<KeyValueDTO>>(true,"",null,myList));
	}catch(Exception ex){
		logger.error("BooksAction.addBooksPoint",ex);
		return ERROR;
	}
	return "getKpointById";

}
	
	public String deletePic(){
		 
		try {
			this.pictureService.delPictureById(this.mainpicid);
			
		} catch (Exception e) {
			logger.error("BooksAction.deletePic",e);
			return ERROR;
		}
		return "deletePicSuccess";
	}

	public List<String> getFileListFileName() {
		return fileListFileName;
	}

	public void setFileListFileName(List<String> fileListFileName) {
		this.fileListFileName = fileListFileName;
	}

	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	public IPicture getPictureService() {
		return pictureService;
	}

	public void setPictureService(IPicture pictureService) {
		this.pictureService = pictureService;
	}

	public Kpoint getKpoint() {
		return kpoint;
	}

	public void setKpoint(Kpoint kpoint) {
		this.kpoint = kpoint;
	}

	public List<Kpoint> getKpointList() {
		return kpointList;
	}

	public void setKpointList(List<Kpoint> kpointList) {
		this.kpointList = kpointList;
	}

	public QueryKpointCondition getQueryKpointCondition() {
		if(queryKpointCondition == null){
			queryKpointCondition = new QueryKpointCondition();
		}
		return queryKpointCondition;
	}

	public void setQueryKpointCondition(QueryKpointCondition queryKpointCondition) {
		this.queryKpointCondition = queryKpointCondition;
	}
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public IKpoint getKpointService() {
		return kpointService;
	}

	public void setKpointService(IKpoint kpointService) {
		this.kpointService = kpointService;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}

	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public QueryCoursesortCondition getQueryCourseSortCondition() {

		if(queryCourseSortCondition == null){
			queryCourseSortCondition = new QueryCoursesortCondition();
		}
		return queryCourseSortCondition;
	}

	public void setQueryCourseSortCondition(
			QueryCoursesortCondition queryCourseSortCondition) {
		this.queryCourseSortCondition = queryCourseSortCondition;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public int[] getBkId() {
		return bkId;
	}

	public void setBkId(int[] bkId) {
		this.bkId = bkId;
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

	public IBooks getBooksService() {
		return booksService;
	}

	public void setBooksService(IBooks booksService) {
		this.booksService = booksService;
	}

	public Books getBooks() {
		return books;
	}

	public void setBooks(Books books) {
		this.books = books;
	}


	public List<File> getOtherPics() {
		return otherPics;
	}


	public void setOtherPics(List<File> otherPics) {
		this.otherPics = otherPics;
	}


	public List<String> getOtherPicsFileName() {
		return otherPicsFileName;
	}


	public void setOtherPicsFileName(List<String> otherPicsFileName) {
		this.otherPicsFileName = otherPicsFileName;
	}


	public List<File> getFirstPic() {
		return firstPic;
	}


	public void setFirstPic(List<File> firstPic) {
		this.firstPic = firstPic;
	}


	public List<String> getFirstPicFileName() {
		return firstPicFileName;
	}


	public void setFirstPicFileName(List<String> firstPicFileName) {
		this.firstPicFileName = firstPicFileName;
	}


	public List<File> getOtherPicEd() {
		return otherPicEd;
	}


	public void setOtherPicEd(List<File> otherPicEd) {
		this.otherPicEd = otherPicEd;
	}


	public List<String> getOtherPicEdFileName() {
		return otherPicEdFileName;
	}


	public void setOtherPicEdFileName(List<String> otherPicEdFileName) {
		this.otherPicEdFileName = otherPicEdFileName;
	}


	public int[] getPic() {
		return pic;
	}


	public void setPic(int[] pic) {
		this.pic = pic;
	}


	public List<Picture> getPictureList() {
		return pictureList;
	}


	public void setPictureList(List<Picture> pictureList) {
		this.pictureList = pictureList;
	}


	public int getMainpicid() {
		return mainpicid;
	}


	public void setMainpicid(int mainpicid) {
		this.mainpicid = mainpicid;
	}


	public int getFuSortId() {
		return fuSortId;
	}


	public void setFuSortId(int fuSortId) {
		this.fuSortId = fuSortId;
	}


	public int getSuSortId() {
		return suSortId;
	}


	public void setSuSortId(int suSortId) {
		this.suSortId = suSortId;
	}


	public int getTuSortId() {
		return tuSortId;
	}


	public void setTuSortId(int tuSortId) {
		this.tuSortId = tuSortId;
	}


	public int getCuSortId() {
		return cuSortId;
	}


	public void setCuSortId(int cuSortId) {
		this.cuSortId = cuSortId;
	}


	public String getFuSortIdString() {
		return fuSortIdString;
	}


	public void setFuSortIdString(String fuSortIdString) {
		this.fuSortIdString = fuSortIdString;
	}


	public String getSuSortIdString() {
		return suSortIdString;
	}


	public void setSuSortIdString(String suSortIdString) {
		this.suSortIdString = suSortIdString;
	}


	public String getTuSortIdString() {
		return tuSortIdString;
	}


	public void setTuSortIdString(String tuSortIdString) {
		this.tuSortIdString = tuSortIdString;
	}


	public String getCuSortIdString() {
		return cuSortIdString;
	}


	public void setCuSortIdString(String cuSortIdString) {
		this.cuSortIdString = cuSortIdString;
	}


	public ICourse getCourseService() {
		return courseService;
	}


	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

}
