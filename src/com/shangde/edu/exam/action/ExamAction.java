package com.shangde.edu.exam.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageQuery;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.exam.condition.QueryExampaperCondition;
import com.shangde.edu.exam.condition.QueryQstCondition;
import com.shangde.edu.exam.condition.QueryQstmiddleCondition;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.domain.QstPic;
import com.shangde.edu.exam.domain.Qstmiddle;
import com.shangde.edu.exam.service.IExampaper;
import com.shangde.edu.exam.service.IQst;
import com.shangde.edu.exam.service.IQstmiddle;
import com.shangde.edu.kb.condition.QueryProfessionalCondition;
import com.shangde.edu.kb.domain.Professional;
import com.shangde.edu.kb.service.IProfessional;
import com.shangde.edu.kno.condition.QuerySysNodeCondition;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.service.ISys;
import com.shangde.edu.kno.service.ISysNode;
import com.shangde.edu.sys.domain.Grade;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;

/**
 * 考试Action
 * @author chenshuai wanglei
 */
public class ExamAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(ExamAction.class);
	
	private static final int BUFFER_SIZE=16*1024;
	
	private static final long serialVersionUID = 1L;
	
	private String dir = ServletActionContext.getServletContext().getRealPath( "/excelfile_exam/");
	/**
	 *考试pojo 
	 */
	private Exampaper exam;
	
	/**
	 * 考试和试题中表条件
	 */
	private QueryQstmiddleCondition queryQstmiddleCondition;
	
	/**
	 * 考试服务
	 */
	private IExampaper exampaperService;
	
	
	/**
	 * 试题服务
	 */
	private IQst qstService;
	
	public IQst getQstService() {
		return qstService;
	}

	public void setQstService(IQst qstService) {
		this.qstService = qstService;
	}

	/**
	 * 试卷和试题中间表服务
	 */
	private IQstmiddle qstmiddleService;
	
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	
	/**
	 * 声名知识结构服务
	 */
	private IProfessional professionalService;

	/**
	 *配置服务 
	 */
	private ConfigService configurator;
	
	/**
	 * 考试查询条件
	 */
	private QueryExampaperCondition queryExampaperCondition;
	
	/**
	 * 查询条件
	 */
	private String searchKey;
	
	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	
	/**
	 * 年份集合
	 */
	private List<Grade> gradeList = new ArrayList<Grade>();
	
	/**
	 * 学科集合
	 */
	private List<Subject> subjectList = new ArrayList<Subject>();
	
	/**
	 * 考试难度系数
	 */
	private Map<Integer,String> examlevel = new HashMap<Integer, String>();
	
	private int epid;
	/**
	 * 分页类
	 */
	private PageQuery pageQuery;
	
	private float totalScroe=0f;//总得分
	
	/**
	 * 知识库专业表
	 */
	private List<Professional> professionalList=new ArrayList<Professional>();
	
	/**
	 * 试题IDS
	 */
	private String qstIds;
	
	/**
	 * 课程分类集合
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 试卷IDs 冻结时用到
	 */
	private int[] exampaperIds;
	
	/**
	 * 通过试卷id和试题类型查询
	 */
	private List<Qstmiddle> danxuan;
	private List<Qstmiddle> duoxuan;
	private List<Qstmiddle> panduan;
	private List<Qstmiddle> cailiao;
	private List<Qstmiddle> tubiao;
	private List<Qstmiddle> zhuguan;
	private String exportName;//导出名
	String testname1;//试卷名称
	String testeptype1;//测试类型
	
	String course1 ;
	
	
	/** 王超 添加开始 */
	/**
	 * 知识点id
	 */
	private int ksnId;
	
	/**
	 * 随机试卷试题信息，包括类型数量分值
	 */
	private String parm;
	public String getParm() {
		return parm;
	}

	public void setParm(String parm) {
		this.parm = parm;
	}

	/**
	 * 查询条件
	 */
	private QuerySysNodeCondition querySysNodeCondition;
	/**
	 * 查询试题条件
	 */
	private QueryQstCondition queryQstCondition;
	public QueryQstCondition getQueryQstCondition() {
		if(queryQstCondition == null){
			queryQstCondition = new QueryQstCondition();
		}
		return queryQstCondition;
	}

	public void setQueryQstCondition(QueryQstCondition queryQstCondition) {
		this.queryQstCondition = queryQstCondition;
	}

	/**
	 * 知识树id
	 */
	private int ksId;
	/**
	 * 知识点服务类
	 */
	private ISysNode sysNodeService;
	/**
	 * 知识树服务类
	 */
	private ISys sysService;
	
	private int subjectId;
	
	List<SysNode>sysNodeList;
	
	private SysNode sysNode;
	
	List<SysNode> knoTree;
	
	public ISysNode getSysNodeService() {
		return sysNodeService;
	}

	public void setSysNodeService(ISysNode sysNodeService) {
		this.sysNodeService = sysNodeService;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	
	/**
	 * 随机出卷生成试题 add by yanghaibo 2012-07-26 17:31
	 * @return
	 */
	public void createRandomQuestion(){
		try{
			//知识库知识点
			//int ksnId=14932;
			//试题类型，数量及分值
			//String parm ="1,2,3*3,10,20*4,3,30";
			String[] qstTypeStrings =parm.split("\\,");
			List<Qst> allQsts = new ArrayList<Qst>();
			//实例化随机对象
			Random rd = new Random();
			//实际随机产生出的试题数量
			int count = 0;
			//随机生成的试题ID集合
			List<Integer> idList =  new ArrayList<Integer>();
			//随机产生的序列号
			List<Integer> rdList = new ArrayList<Integer>();
			for(int i = 0 ;i <qstTypeStrings.length ;i++){
				String[] qstDetail = qstTypeStrings[i].split("\\*");
				//如果不是指定格式则跳过
				if(qstDetail.length != 3){
					continue;
				}
				//清除上一次随机生成的试题ID
				idList.clear();
				//清除上一次随机生成的序列号
				rdList.clear();
				//根据类型和知识库知识点得到满足条件的所有试题ID
				this.getQueryQstCondition().setKsnId(ksnId);
				this.getQueryQstCondition().setQstType(Integer.parseInt(qstDetail[0].trim()));
				allQsts = this.qstService.getAllQstByType(this.getQueryQstCondition());
				if(Integer.parseInt(qstDetail[1])<=allQsts.size()){
					count = Integer.parseInt(qstDetail[1]);
				}else{
					count = allQsts.size();
				}
				//如果随机产生的试题数量为0，则跳过
				if(count == 0){
					continue;
				}
				
				//最大的随机范围
				int maxRandomInt = allQsts.size();
				//产生随机试题ID
				for(int v = 0 ; v < count ; v++){
					while (true) {
						int tempId = rd.nextInt(maxRandomInt);
						if(!rdList.contains(tempId)){
							rdList.add(tempId);
							idList.add(allQsts.get(tempId).getQstId());
							break;
						}
					}
				}
				//产生随机的单选类型试题
				if(qstDetail[0] != null && qstDetail[0].equals("1")){
					danxuan=qstmiddleService.getQstmiddleById(idList);
					for(int j = 0 ; j < danxuan.size() ; j ++){
						danxuan.get(j).setQstScore(Float.parseFloat(qstDetail[2]));
					}
				}
				//产生随机的多选类型试题
				if(qstDetail[0] != null && qstDetail[0].equals("2")){
					duoxuan=qstmiddleService.getQstmiddleById(idList);
					for(int j = 0 ; j < duoxuan.size() ; j ++){
						duoxuan.get(j).setQstScore(Float.parseFloat(qstDetail[2]));
					}
				}
				//产生随机的判断类型试题
				if(qstDetail[0] != null && qstDetail[0].equals("3")){
					panduan=qstmiddleService.getQstmiddleById(idList);
					for(int j = 0 ; j < panduan.size() ; j ++){
						panduan.get(j).setQstScore(Float.parseFloat(qstDetail[2]));
					}
				}
				//产生随机的材料类型试题
				if(qstDetail[0] != null && qstDetail[0].equals("4")){
					cailiao=qstmiddleService.getQstmiddleById(idList);
					for(int j = 0 ; j < cailiao.size() ; j ++){
						cailiao.get(j).setQstScore(Float.parseFloat(qstDetail[2]));
					}
				}
				//产生随机的图表类型试题
				if(qstDetail[0] != null && qstDetail[0].equals("5")){
					tubiao=qstmiddleService.getQstmiddleById(idList);
					for(int j = 0 ; j < tubiao.size() ; j ++){
						tubiao.get(j).setQstScore(Float.parseFloat(qstDetail[2]));
					}
				}
				//产生随机的主观类型试题
				if(qstDetail[0] != null && qstDetail[0].equals("6")){
					zhuguan=qstmiddleService.getQstmiddleById(idList);
					for(int j = 0 ; j < zhuguan.size() ; j ++){
						zhuguan.get(j).setQstScore(Float.parseFloat(qstDetail[2]));
					}
				}
			}
		}catch(Exception e){
			logger.error("ExamAction.createRandomQuestion",e);
		}
	}

	/** 王超 添加结束 */
	/**
	 * 预添加考试
	 * 到达更新考试页面，提供试卷原来的信息
	 * URL中需要的参数为exam.epId
	 * @return
	 */
	public String toUpdateExam(){
		try{
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			
			exam = exampaperService.getExampaperById(exam.getEpId());
			if(exam.getCid()!=-1){
				sysNode=sysNodeService.getSysNodeById(exam.getCid());
				sysNodeList=new ArrayList<SysNode>();
				if(sysNode!=null){
					this.getQuerySysNodeCondition().setParentId(sysNode.getParentId());
					sysNodeList=this.sysNodeService.getSysNodeListByCondition(querySysNodeCondition);
					this.getQuerySysNodeCondition().setKsId(sysNode.getKsId());
					querySysNodeCondition.setKsnId(sysNode.getKsnId());
					knoTree=this.getSysNodeService().getSysTreeByKsnId(querySysNodeCondition);
				}
				sysNode=sysNodeService.getSysNodeById(exam.getZkId());
			}
			return "toUpdateExam";
		}catch(Exception ex){
			logger.error("ExamAction.toUpdateExam", ex);
			return ERROR;
		}
	}
	
	/**
	 * 修改试卷
	 * 将添加的试卷进行更新，根据试卷ID
	 * URL中需要的参数为exam.epId
	 * @return
	 */
	public String updateExam(){
		try{
			Exampaper exp=exampaperService.getExampaperById(exam.getEpId());
			String a=servletRequest.getParameter("qstCount");
			exam.setEpKeyword("key");
			exam.setUserId(26);
			exam.setQstmiddlecount(Integer.parseInt(a));
			exam.setJoinNum(exp.getJoinNum());
			exam.setEpTotelScore(exp.getEpTotelScore());
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			exam.setAuthor(users.getLoginName());
			exam.setLastEditTime(new Date());
			exampaperService.updateExampaper(exam);//修改试卷
			
			return "addExam";
		}catch(Exception ex){
			logger.error("ExamAction.updateExam", ex);
			return ERROR;
		}
	}
	
	
	/**
	 * 转向添加试题页面
	 * @author 何海强
	 * @return
	 */
	
	public String toaddQst(){
		try {
			exam = exampaperService.getExampaperToAddQst(epid);
			if(exam.getQstmiddlecount()==0){
				exam.setEpTotelScore(0);
			}
			this.getQueryQstmiddleCondition().setEpId(epid);
			//单选查询
			this.getQueryQstmiddleCondition().setQstType(1);
			danxuan=qstmiddleService.getQstmiddleToAddQstList(queryQstmiddleCondition);
			//多选查询
			this.getQueryQstmiddleCondition().setQstType(2);
			duoxuan=qstmiddleService.getQstmiddleToAddQstList(queryQstmiddleCondition);
			//判断查询
			this.getQueryQstmiddleCondition().setQstType(3);
			panduan=qstmiddleService.getQstmiddleToAddQstList(queryQstmiddleCondition);
			
			//材料
			this.getQueryQstmiddleCondition().setQstType(4);
			cailiao=qstmiddleService.getQstmiddleToAddQstList(queryQstmiddleCondition);
			
			//图表
			this.getQueryQstmiddleCondition().setQstType(5);
			tubiao=qstmiddleService.getQstmiddleToAddQstList(queryQstmiddleCondition);
			
			//主关
			this.getQueryQstmiddleCondition().setQstType(6);
			zhuguan=qstmiddleService.getQstmiddleToAddQstList(queryQstmiddleCondition);
		} catch (RuntimeException e) {
			logger.error("ExamAction.toaddQst", e);
			return ERROR;
		}
		
		return "UpdateQst";
	}
	
	/**
	 * 添加试卷首页--获取所有专业名称
	 * @return
	 */
	public String toAddExam(){
		try{
			subjectList = subjectService.getSubjectListByStatus(Subject.SUBJECT_DEFAULT_STATUS);
			//professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
			//examlevel = configurator.getExamlevel();
			//courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			return "toAddExam";
		}catch(Exception ex){
			logger.error("ExamAction.toAddExam", ex);
			return ERROR;
		}
	}
	
	/**
	 * 试卷预览
	 * @return
	 */
	public String toPreviewExampaper(){
		try{
		exam=exampaperService.getExampaperPaperResult(epid);
		}catch(Exception e){
			logger.error("ExamAction.toPreviewExampaper", e);
			return ERROR;
		}
		return "toPreviewExampaper";
	}
	
	
	/**
	 * 批量冻结试卷
	 * 传入参数为试卷ID集合 
	 * @return
	 */
	public String freezeExampapers(){
		try{
			if(exampaperIds != null){
				Exampaper temp = null;
				for(int i = 0; i <exampaperIds.length ; i++){
					temp = exampaperService.getExampaperById(exampaperIds[i]);
					if(temp != null && temp.getEpState() == Exampaper.EXAMPAPER_DEFAULT_STATUS){
						temp.setEpState(Exampaper.EXAMPAPER_FREEZE_STATUS);
						exampaperService.updateExampaper(temp);
					}
				}
			}
		}catch(Exception ex){
			logger.error("ExamAction.freezeExampapers", ex);
			return ERROR;
		}
		
		return "freezeExampapers";
	}
	
	/**
	 * 列出试卷列表
	 * 根据查询条件（查询关键字，课程或知识点ID 可分别查询或组合查询）
	 * @return
	 */
	public String listExamPaperByCondition(){
		try{
			subjectList = subjectService.getAllSubject();
			if(searchKey != null){
				this.getQueryExampaperCondition().setSearchKey(searchKey);
			}
			
			if(exam != null){
				this.getQueryExampaperCondition().setType(exam.getType());
				this.queryExampaperCondition.setKOrCouId(exam.getKOrCouId());
				
				this.queryExampaperCondition.setCid(exam.getCid());
			}else{
				this.getQueryExampaperCondition().setType(0);
				this.queryExampaperCondition.setKOrCouId(0);
			
				this.queryExampaperCondition.setCid(0);
			}
			String seackey=this.getQueryExampaperCondition().getSearchKey();
			if(seackey!=null){
				getQueryExampaperCondition().setSearchKey((URLDecoder.decode(seackey, "UTF-8")).trim());
			}
			String author = this.getQueryExampaperCondition().getAuthor();
			if(author!=null){
				this.getQueryExampaperCondition().setAuthor((URLDecoder.decode(author, "UTF-8")).trim());
			}
			
			this.getQueryExampaperCondition().setPageSize(20);
			setPage(exampaperService.listExamPaperByConditionexam(this.getQueryExampaperCondition()));
			this.getPage().setPageSize(20);
			setPageUrlParms();
			return "listExamPaperByCondition";
		}catch(Exception ex){
			logger.error("ExamAction.listExamPaperByCondition", ex);
			return ERROR;
		}
	}
	/**
	 *试卷列表导出功能
	 *赵永永
	 */
	public String exportExamPaperExcel(){
		try{
			subjectList = subjectService.getAllSubject();
			professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
			if(searchKey != null){
				this.getQueryExampaperCondition().setSearchKey(searchKey);
			}
			
			if(exam != null){
				this.getQueryExampaperCondition().setType(exam.getType());
				this.queryExampaperCondition.setKOrCouId(exam.getKOrCouId());
				
				this.queryExampaperCondition.setCid(exam.getCid());
			}else{
				this.getQueryExampaperCondition().setType(0);
				this.queryExampaperCondition.setKOrCouId(0);
			
				this.queryExampaperCondition.setCid(0);
			}
			String seackey=this.getQueryExampaperCondition().getSearchKey();
			if(seackey!=null){
				getQueryExampaperCondition().setSearchKey((URLDecoder.decode(seackey, "UTF-8")).trim());
			}
			String author = this.getQueryExampaperCondition().getAuthor();
			if(author!=null){
				this.getQueryExampaperCondition().setAuthor((URLDecoder.decode(author, "UTF-8")).trim());
			}
			setPage(exampaperService.listExamPaperByConditionexam(this.getQueryExampaperCondition()));
			if (getPage() != null) {
				getPage().setPageSize(5000);
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			String expName="highso" +sdf.format(new Date());
			if (ServletActionContext.getRequest().getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0)
			 {
				 setExportName(URLEncoder.encode(expName, "UTF-8"));//IE浏览器
			 }else{
				 setExportName(new String((expName).getBytes(),"gbk"));
			 }
		createExcelFile(expName);
		this.setResult(new Result(true,exportName, null, null));
		return "json";
	} catch (Exception ex) {
		logger.error("ReProblemAction.exportProplemExcel",ex);
		return "error";
	}
	}

	public void createExcelFile(String expName) {
		try {
			// 定义时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			// 表头标题
			String[] headNames = { "试卷ID", "试卷名称",
					"所属专业", "使用人数", "积分", "试题类型", "选择课程","状态",
					"添加时间","试卷题数","修改时间","试卷修改人"};
			List headName = new ArrayList();
			headName.add("试卷ID");
			headName.add("试卷名称");
			headName.add("所属专业");
			headName.add("使用人数");
			headName.add("积分");
			headName.add("试题类型");
			headName.add("选择课程");
			headName.add("状态");
			headName.add("添加时间");
			headName.add("试卷题数");
			headName.add("修改时间");
			headName.add("试卷修改人");
			
			/*File zipfile1 = new File(dir);
			zipfile1.delete();*/
			deleteFile(new File(dir));
			int a = 1;
			if (this.getPage().getPageSize() == 5000) {
				if(this.getPage().getTotalRecord()%5000==0){
					a=this.getPage().getTotalRecord()/5000;
				}else{
					a=this.getPage().getTotalRecord()/5000+1;
				}
			}
			List<File> srcfile = new ArrayList<File>();
			for (int k = 0; k < a; k++) {
				List<Exampaper> exampaperList = new ArrayList<Exampaper>();
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("sheet");
					if (pageQuery != null) {
						pageQuery.setPageSize(5000);
						pageQuery.setCurrentPage(k + 1);
						exampaperList = exampaperService.listExamPaperByConditionexam(getQueryExampaperCondition())
								.getPageResult();
					} else {
						queryExampaperCondition.setPageSize(5000);
						queryExampaperCondition.setCurrentPage(k + 1);
						exampaperList=exampaperService.listExamPaperByConditionexam(getQueryExampaperCondition())
						.getPageResult();
					}

				// 创建列数
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell = row.createCell((short) 0);
				for (int i = 0; i < headName.size(); i++) {
					cell = row.createCell((short) i);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(headName.get(i).toString());
				}
				for (int i = 0; i < exampaperList.size(); i++) {
					row = sheet.createRow(i + 1);
					int j = 0;
					// 试卷id
					cell = row.createCell((short) j);
					j++;
					cell.setCellValue(exampaperList.get(i).getEpId());
					// 试题名称
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(exampaperList.get(i).getEpName());
					//所属专业
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(exampaperList.get(i).getSubjectName());
					// 使用人数
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(exampaperList.get(i).getJoinNum());
					//积分
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(exampaperList.get(i).getJifen());
					//试题类型
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
                    if(exampaperList.get(i).getEptype()==1){
						cell.setCellValue("真题测试");
					}else if(exampaperList.get(i).getEptype()==2){
						cell.setCellValue("仿真自测");
					}else if(exampaperList.get(i).getEptype()==3){
						cell.setCellValue("单元练习");
					}else if(exampaperList.get(i).getEptype()==4){
						cell.setCellValue("专题练习");
					}
					// 选择课程
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if(exampaperList.get(i).getCourseId()!=-1&&exampaperList.get(i).getCourseId()!=0){
					Course course=courseService.getCourseById(exampaperList.get(i).getCourseId());
					String courseName=course.getTitle();
					cell.setCellValue(courseName);
					}
					//状态
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if(exampaperList.get(i).getLevel()==1){
					cell.setCellValue("新稿");
					}else if(exampaperList.get(i).getLevel()==2){
						cell.setCellValue("发布");
					}else if(exampaperList.get(i).getLevel()==3){
						cell.setCellValue("过期");
					}
					//添加时间
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(sdf.format(exampaperList.get(i).getCreateTime()));
					//试卷题数
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(exampaperList.get(i).getQstmiddlecount());
					//修改时间
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(sdf.format(exampaperList.get(i).getLastEditTime()));
					//操作者
					cell=row.createCell((short)j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(exampaperList.get(i).getAuthor());
					
				}
				
				File file=new File(dir+"/excel"+k+".xls");
				FileOutputStream fos = new FileOutputStream(file);
				workbook.write(fos);
				srcfile.add(file);
				fos.close();
			}
			File zipfile = new File(dir+"/"+expName+".rar");
			zipFiles(srcfile, zipfile);
			for(int i=0;i<a;i++){
				File file=new File(dir+"/excel"+i+".xls");
				file.delete();
			}
			
		} catch (IOException e) {

			logger.error("执行exportProplemExcel方法执行createExcelFile错误！", e);
			e.printStackTrace();
		}
	}
	/**
	 * 
	 *删除文件 赵永永 
	 */
    public void deleteFile(File file)   
    {   
            File[] temp = file.listFiles();   
            for(int i=0;i<temp.length;i++)   
            {   
                if(temp[i].isDirectory())   
                {   
                    if(temp[i].listFiles().length!=0)   
                        this.deleteFile(temp[i]);   
                    this.deleteDir(temp[i]);   
                }else  
                {   
                    temp[i].delete();   
                }   
            }   
    }  
	/**
	 * 
	 *删除文件 赵永永 
	 */
    private void deleteDir(File file)   
    {   
        if(file.listFiles().length==0)   
            file.getAbsoluteFile().delete();   
    }  
	/** 
	  * 压缩文件  
	  *   
	  * @param srcfile File[] 需要压缩的文件列表  
	  * @param zipfile File 压缩后的文件  
	  * @author zhaoyongyong
	  */  

	public void zipFiles(List<File> srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		String ZIP_ENCODEING = "GBK"; 
		try {
			// Create the ZIP file
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					zipfile));
			out.setEncoding(ZIP_ENCODEING);
		
			// Compress the files
			for (int i = 0; i < srcfile.size(); i++) {
				File file = srcfile.get(i);
				FileInputStream in = new FileInputStream(file);
				// Add ZIP entry to output stream.
				out.putNextEntry(new ZipEntry(file.getName()));
				// Transfer bytes from the file to the ZIP file
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				// Complete the entry
				out.closeEntry();
				in.close();
			}
			// Complete the ZIP file
			out.close();
		} catch (IOException e) {
			logger.error("ZipUtil zipFiles exception:" + e);
		}
	}
	/**
	 * 添加试卷
	 * 将添加的试卷信息添加到数据库中
	 * @return
	 */
	public String addExam(){
		try{
			exam.setEpKeyword("key");
			exam.setUserId(26);
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			exam.setAuthor(users.getLoginName());
			exam.setLastEditTime(new Date());
			int exampaperid = exampaperService.addExampaper(exam);//添加试卷
			exam = exampaperService.getExampaperById(exampaperid);
			return "toaddQst";
		}catch(Exception ex){
			logger.error("ExamAction.addExam", ex);
			return ERROR;
		}
	}
	
	/**
	 * 试卷阅览效果
	 */

	public String read(){
		try{
			testname1=exam.getEpName();//试卷名称
			testeptype1="";//测试类型
			
			course1 = "";//课程名称
			
			if(exam.getCourseId()==0)
			{
				exam=exampaperService.getExampaperById(exam.getEpId());
			}
			if(exam.getCourseId()!=-1)
			{
				course1 = courseService.getCourseById(exam.getCourseId()).getTitle();
			}
			List<Qst> aa=new ArrayList();	
			
			if(exam.getEptype()==1)
			{
				testeptype1="仿真自测";
			}
			if(exam.getEptype()==2)
			{
				testeptype1="真题测试";
			}
			if(exam.getEptype()==3)
			{
				testeptype1="单元练习";
			}
			if(exam.getEptype()==4)
			{
				testeptype1="专题练习";
			}
			String ids[] = qstIds.split(",");//分成 是否上传 :id:主观客观 的形式
			
			Qst question = null;//问题
			Options option = null;//选项
			QstPic qstpic = null;//试题图片
			String tempid = "";//页面传来id
			String isUpload="";//是否上传图片
			int temptype=0;//试题类型
			String[] typeAndId = null;
			String fileName;//文件名
			String filePath;
			
			int fileIndex = 0;//文件坐标
			for(int i = 0; i < ids.length; i ++){
				question = new Qst();
				typeAndId = ids[i].split(":");//获取type 和Id;
				isUpload = typeAndId[0];
				tempid = typeAndId[1];
				temptype = Integer.parseInt(typeAndId[2]);
				
				//添加试题--------------------------------------------
				question.setScore(Float.parseFloat(servletRequest.getParameter("score" + tempid).toString()));
				question.setQstContent((String)servletRequest.getParameter("shiti" + tempid).replaceAll("\r\n", "<br>"));
				question.setWrongJude((String)servletRequest.getParameter("pingyu" + tempid));
				question.setQstType(temptype);
				
				if(temptype == 6){//主观题时，答案存储于选项表中
					question.setIsAsr((String)servletRequest.getParameter("right" + tempid));
				}else if(temptype == 2){//多选题，答案存储于选项表中
					String [] isAsr=servletRequest.getParameterValues("right" + tempid);
					String asr="";
					for(int i1=0;i1<isAsr.length;i1++)
					{
						asr+=isAsr[i1];
					}
					question.setIsAsr(asr);
				}else if(temptype == 4){//材料题，答案存储于选项表中
					String [] isAsr=servletRequest.getParameterValues("right" + tempid);
					String asr="";
					for(int i1=0;i1<isAsr.length;i1++)
					{
						asr+=isAsr[i1];
					}
					question.setIsAsr(asr);
				}else if(temptype == 5){//图表题，答案存储于选项表中
					String [] isAsr=servletRequest.getParameterValues("right" + tempid);
					String asr="";
					for(int i1=0;i1<isAsr.length;i1++)
					{
						asr+=isAsr[i1];
					}
					question.setIsAsr(asr);
				}else if(temptype == 3){//判断，答案存储于选项表中
					question.setIsAsr((String)servletRequest.getParameter("right" + tempid));
				}else if(temptype == 6){
					question.setIsAsr("main");
				}
				question.setQstType(temptype);//试题的类型（单选，主观）
				
				question.setCtPerson("A");
				question.setLevel(Integer.parseInt(servletRequest.getParameter("level" + tempid)));
				
				List <Options> ss=new ArrayList();
				if(temptype == 0){//客观题
				
					option = new Options();//添加选项---------------------------------
					option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("A");
					ss.add(option);
					
					option = new Options();
					option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("B");
					ss.add(option);
					
					option = new Options();
					option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("C");
					ss.add(option);
					
					option = new Options();
					option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("D");
					ss.add(option);
					
					if(!servletRequest.getParameter("E" + tempid).trim().equals(""))
					{
					option = new Options();
					option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
					option.setOptOrder("E");
					ss.add(option);
					}
					
					if(!servletRequest.getParameter("F" + tempid).trim().equals("")){
					option = new Options();
					option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
					option.setOptOrder("F");
					ss.add(option);
					}
					
					if(!servletRequest.getParameter("G" + tempid).trim().equals("")){
					option = new Options();
					option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
					option.setOptOrder("G");
					ss.add(option);
					}
				}
					else if(temptype == 2){//多选题
						
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("A");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("B");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("C");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("D");
						ss.add(option);
						
						if(!servletRequest.getParameter("E" + tempid).trim().equals(""))
						{
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("E");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("F");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("G");
						ss.add(option);
						}
					}else if(temptype == 4){//材料题
						
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("A");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("B");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("C");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("D");
						ss.add(option);
						
						if(!servletRequest.getParameter("E" + tempid).trim().equals(""))
						{
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("E");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("F");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("G");
						ss.add(option);
						}
					}else if(temptype == 5){//图表题
						
						option = new Options();//添加选项---------------------------------
						option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("A");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("B");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("C" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("C");
						ss.add(option);
						
						option = new Options();
						option.setOptContent(servletRequest.getParameter("D" + tempid).replaceAll("\r\n", "<br>"));
						
						option.setOptOrder("D");
						ss.add(option);
						
						if(!servletRequest.getParameter("E" + tempid).trim().equals(""))
						{
						option = new Options();
						option.setOptContent(servletRequest.getParameter("E" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("E");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("F" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("F" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("F");
						ss.add(option);
						}
						
						if(!servletRequest.getParameter("G" + tempid).trim().equals("")){
						option = new Options();
						option.setOptContent(servletRequest.getParameter("G" + tempid).replaceAll("\r\n", "<br>"));
						option.setOptOrder("G");
						ss.add(option);
						}
					}
						else if(temptype == 3){//判断题
							
							option = new Options();//添加选项---------------------------------
							option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
							
							option.setOptOrder("A");
							ss.add(option);
							
							option = new Options();
							option.setOptContent(servletRequest.getParameter("B" + tempid).replaceAll("\r\n", "<br>"));
							
							option.setOptOrder("B");
							ss.add(option);
				}else if(temptype == 6){//主观题
					option = new Options();
					option.setOptContent(servletRequest.getParameter("right" + tempid).replaceAll("\r\n", "<br>"));
					
					option.setOptOrder("main");
					ss.add(option);
				}
				question.setOptions(ss);
				aa.add(question);
				exam.setQst(aa);
			
			}
			
			
			return "read";
		}catch(Exception ex){
			logger.error("ExamAction.read", ex);
			return ERROR;
		}
	}
	
	/**
	 * 根据学员课程展示题目列表
	 * @return
	 */
	public String getExamPaperList(){
		try{
			//根据学员所购买专业的课程显示试题
			int userid=1;//假设userid			
			subjectList=subjectService.getSubjectListByUserId(userid);
			return "showPaperList";
		}catch(Exception ex){
			logger.error("ExamAction.getExamPaperList", ex);
			return ERROR;
		}
	}
	/**
	 * 添加随机试卷 add by yanghaibo 2012-08-06 09:48
	 * @return
	 */
	public String addRandomPaper(){
		try{
			
			return "addRandomPaper";
		}catch(Exception ex){
			logger.error("ExamAction.addRandomPaper", ex);
			return ERROR;
		}
	}
	
	public String getSysTreeByKsnId(){
		try {
			SysNode sysNode=sysNodeService.getSysNodeById(ksnId);
			this.getQuerySysNodeCondition().setKsId(sysNode.getKsId());
			querySysNodeCondition.setKsnId(ksnId);
			querySysNodeCondition.setParentId(sysNode.getParentId());
			List<SysNode> knoTree=this.getSysNodeService().getSysTreeByKsnId(querySysNodeCondition);
			this.setResult(new Result(true,null,null,knoTree));
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("ExamAction.getSysTreeByKsnId", e);
			return ERROR;
		}
	}
	/***
	 * 添加试卷首页--获得专业下面的知识库知识点 
	 * @return
	 */
	public String getSysNodeListByParentId(){
		try {
			Sys kno=this.getSysNodeService().getSysBySubjectId(subjectId);
			if(kno!=null){
				SysNode sysNode=sysNodeService.getSysNodeBySysId(kno.getKsId());
				this.getQuerySysNodeCondition().setParentId(sysNode.getKsnId());
				List<SysNode>sysNodeList=this.sysNodeService.getSysNodeListByCondition(querySysNodeCondition);
				this.setResult(new Result(true,null,null,sysNodeList));
			}else{
				this.setResult(new Result(false,null,null,null));
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("ExamAction.getSysTreeByKsnId", e);
			this.setResult(new Result(false,null,null,null));
		}
		return "json";
	}

	public Exampaper getExam() {
		return exam;
	}

	public void setExam(Exampaper exam) {
		this.exam = exam;
	}
		
	public IExampaper getExampaperService() {
		return exampaperService;
	}

	public void setExampaperService(IExampaper exampaperService) {
		this.exampaperService = exampaperService;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}
	
	public QueryExampaperCondition getQueryExampaperCondition() {
		if(queryExampaperCondition == null){
			queryExampaperCondition = new QueryExampaperCondition();
		}
		return queryExampaperCondition;
	}

	public void setQueryExampaperCondition(
			QueryExampaperCondition queryExampaperCondition) {
		this.queryExampaperCondition = queryExampaperCondition;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public Map<Integer, String> getExamlevel() {
		return examlevel;
	}

	public void setExamlevel(Map<Integer, String> examlevel) {
		this.examlevel = examlevel;
	}
	public String getQstIds() {
		return qstIds;
	}

	public void setQstIds(String qstIds) {
		this.qstIds = qstIds;
	}
	
	public int getEpid() {
		return epid;
	}

	public void setEpid(int epid) {
		this.epid = epid;
	}

	public float getTotalScroe() {
		return totalScroe;
	}

	public void setTotalScroe(float totalScroe) {
		this.totalScroe = totalScroe;
	}
	public List<Coursesort> getCourseSortList() {
		return courseSortList;
	}

	public void setCourseSortList(List<Coursesort> courseSortList) {
		this.courseSortList = courseSortList;
	}

	public ICoursesort getCoursesortService() {
		return coursesortService;
	}

	public void setCoursesortService(ICoursesort coursesortService) {
		this.coursesortService = coursesortService;
	}
	
	public int[] getExampaperIds() {
		return exampaperIds;
	}

	public void setExampaperIds(int[] exampaperIds) {
		this.exampaperIds = exampaperIds;
	}

	public List<Professional> getProfessionalList() {
		return professionalList;
	}

	public void setProfessionalList(List<Professional> professionalList) {
		this.professionalList = professionalList;
	}

	public IProfessional getProfessionalService() {
		return professionalService;
	}

	public void setProfessionalService(IProfessional professionalService) {
		this.professionalService = professionalService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public String getTestname1() {
		return testname1;
	}

	public void setTestname1(String testname1) {
		this.testname1 = testname1;
	}

	public String getTesteptype1() {
		return testeptype1;
	}

	public void setTesteptype1(String testeptype1) {
		this.testeptype1 = testeptype1;
	}

	public String getCourse1() {
		return course1;
	}

	public void setCourse1(String course1) {
		this.course1 = course1;
	}

	public IQstmiddle getQstmiddleService() {
		return qstmiddleService;
	}

	public void setQstmiddleService(IQstmiddle qstmiddleService) {
		this.qstmiddleService = qstmiddleService;
	}
	
	public QueryQstmiddleCondition getQueryQstmiddleCondition() {
		if(queryQstmiddleCondition==null){
			queryQstmiddleCondition =new QueryQstmiddleCondition();
		}
		return queryQstmiddleCondition;
	}

	public void setQueryQstmiddleCondition(
			QueryQstmiddleCondition queryQstmiddleCondition) {
		this.queryQstmiddleCondition = queryQstmiddleCondition;
	}
	public List<Qstmiddle> getDanxuan() {
		return danxuan;
	}

	public void setDanxuan(List<Qstmiddle> danxuan) {
		this.danxuan = danxuan;
	}

	public List<Qstmiddle> getDuoxuan() {
		return duoxuan;
	}

	public void setDuoxuan(List<Qstmiddle> duoxuan) {
		this.duoxuan = duoxuan;
	}

	public List<Qstmiddle> getPanduan() {
		return panduan;
	}

	public void setPanduan(List<Qstmiddle> panduan) {
		this.panduan = panduan;
	}

	public List<Qstmiddle> getCailiao() {
		return cailiao;
	}

	public void setCailiao(List<Qstmiddle> cailiao) {
		this.cailiao = cailiao;
	}

	public List<Qstmiddle> getTubiao() {
		return tubiao;
	}

	public void setTubiao(List<Qstmiddle> tubiao) {
		this.tubiao = tubiao;
	}

	public List<Qstmiddle> getZhuguan() {
		return zhuguan;
	}

	public void setZhuguan(List<Qstmiddle> zhuguan) {
		this.zhuguan = zhuguan;
	}
	public String getExportName() {
		return exportName;
	}
	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public int getKsnId() {
		return ksnId;
	}

	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}

	public int getKsId() {
		return ksId;
	}

	public void setKsId(int ksId) {
		this.ksId = ksId;
	}

	public QuerySysNodeCondition getQuerySysNodeCondition() {
		if(querySysNodeCondition==null)querySysNodeCondition=new QuerySysNodeCondition();
		return querySysNodeCondition;
	}

	public void setQuerySysNodeCondition(QuerySysNodeCondition querySysNodeCondition) {
		this.querySysNodeCondition = querySysNodeCondition;
	}

	public ISys getSysService() {
		return sysService;
	}

	public void setSysService(ISys sysService) {
		this.sysService = sysService;
	}

	public List<SysNode> getSysNodeList() {
		return sysNodeList;
	}

	public void setSysNodeList(List<SysNode> sysNodeList) {
		this.sysNodeList = sysNodeList;
	}

	public SysNode getSysNode() {
		return sysNode;
	}

	public void setSysNode(SysNode sysNode) {
		this.sysNode = sysNode;
	}

	public List<SysNode> getKnoTree() {
		return knoTree;
	}

	public void setKnoTree(List<SysNode> knoTree) {
		this.knoTree = knoTree;
	}
}
