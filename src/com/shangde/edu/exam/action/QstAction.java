/**
 * @author 何海强
 * @time 2011-05-12
 */
package com.shangde.edu.exam.action;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.service.ConfigService;
import com.shangde.common.util.Result;
import com.shangde.common.util.StringUtil;
import com.shangde.edu.cou.condition.QueryCoursesortCondition;
import com.shangde.edu.cou.domain.Coursesort;
import com.shangde.edu.cou.service.ICoursesort;
import com.shangde.edu.exam.condition.QueryQstCondition;
import com.shangde.edu.exam.condition.QueryQstmiddleCondition;
import com.shangde.edu.exam.domain.Exampaper;
import com.shangde.edu.exam.domain.Options;
import com.shangde.edu.exam.domain.Qst;
import com.shangde.edu.exam.domain.Qstmiddle;
import com.shangde.edu.exam.dto.QstAddQueryDTO;
import com.shangde.edu.exam.service.IOptRecord;
import com.shangde.edu.exam.service.IOptions;
import com.shangde.edu.exam.service.IQst;
import com.shangde.edu.exam.service.IQstKb;
import com.shangde.edu.exam.service.IQstmiddle;
import com.shangde.edu.kb.service.IProfessional;
import com.shangde.edu.kno.domain.QstRel;
import com.shangde.edu.kno.domain.Sys;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.dto.SysDTO;
import com.shangde.edu.kno.service.IQstRel;
import com.shangde.edu.kno.service.ISys;
import com.shangde.edu.kno.service.ISysNode;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ISubject;

public class QstAction extends CommonAction implements Serializable {
	
	private static final Logger logger = Logger.getLogger(QstAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//变量
	private Qst qst;

	/**
	 * 学科服务
	 */
	private ISubject subjectService;
	
	/**
	 * 学科集合
	 */
	private List<Subject> subjectList = new ArrayList<Subject>();
	
	/**
	 * 考试难度系数
	 */
	private Map<Integer,String> examlevel = new HashMap<Integer, String>();
	
	/**
	 * 配置
	 */
	private ConfigService configurator;
	
	/**
	 * 课程分类服务
	 */
	private ICoursesort coursesortService;
	
	/**
	 * 课程分类集合
	 */
	private List<Coursesort> courseSortList = new ArrayList<Coursesort>();
	
	/**
	 * 声名知识结构服务
	 */
	private IProfessional professionalService;
	
	/**
	 * 知识树节点
	 */
	private SysDTO sysdto;

	/**
	 * 知识点与试题关联
	 */
	private QstRel qstRel;
	
	/**
	 * 试题服务
	 * @return
	 */
	private IQst qstService ;
	
	/**
	 * author:hhq
	 * 知识库和试题服务
	 */
	private IQstKb qstKbService;
	
	/**
	 * 知识点和实体关联service
	 */
	private IQstRel qstRelService;
	
	/**
	 * 考试记录表service
	 */
	private IOptRecord optRecordService;
	
	/**
	 * 选项服务
	 */
	private IOptions optionsService;
	
	/**
	 *查询 条件
	 */
	private QueryQstCondition queryQstCondition;
	private int qstId;
	
	
	private int type;
	
	private IQstmiddle qstmiddleService;
	
	private List<Qstmiddle> qstmiddleList=new ArrayList<Qstmiddle>();;
	
	private QueryQstmiddleCondition queryQstmiddleCondition;
	
	private String searchKey;
    
    private int isNuber;
	
    private QstAddQueryDTO qstAddQueryDto;
    
	private List chkIdList=new ArrayList();
	private List orderList=new ArrayList();
	
	/**
	 * 知识树service
	 */
	private ISys sysService;
	
	/**
	 * 知识树节点service
	 */
	private ISysNode sysNodeService;
	
	/**
	 * 知识树LIST
	 */
	private List<Sys> sysinfolist ;
	
	/**
	 * 专业项目ID
	 */
	private int subjectId;
	
	/**
	 * 知识树ID
	 */
	private int ksId;
	
	/**
	 * 知识树节点ID
	 */
	private String nodeId;
	
	/**
	 * 知识点ID
	 */
	private int ksnId;
	
	private Exampaper exam;
	
	/*方法*/
	
	/**
	 * 材料分析，通过id查询子题
	 */
	public String ziqst(){
		try {
			this.getQueryQstCondition().setQstindexId(qstId);
			List<Qst> qstlist=qstService.getQstList(this.getQueryQstCondition());
			StringBuffer buf = new StringBuffer();
			for(int i=0;i<qstlist.size();i++){
				buf.append(qstlist.get(i).getQstId()+",");
			}
			this.setResult(new Result<String>(true,buf.toString()+"",null,null));
		} catch (Exception e) {
			logger.error("QstAction.ziqst",e);
			return ERROR;
		}
		return "qstjson";
	}
	/**
	 * 查询试题id的内容
	 * @return json
	 */
	public String getQstContent(){
		try {
			Qst q = qstService.getQstById(qstId);
			if(q!=null){
				String qstContent = StringUtil.filterHTML(q.getQstContent());
				q.setQstContent(qstContent);
				this.setResult(new Result<String>(true,q.getQstContent(),String.valueOf(q.getQstType()),null));
			}else{
				this.setResult(new Result<String>(true,"false",null,null));
			}
		} catch (Exception e) {
			logger.error("QstAction.getQstContent",e);
			return ERROR;
		}
		return "json";
	}
	
	/**
	 * 考试题删除方法
	 */
	public String Qstdel(){
		
		try {
			//删除此试题的考试记录
			optRecordService.deleteOptRecordByQstId(qstId);
			optionsService.delOptionsById(qstId);
			qstService.delQstById(qstId);
			//删除试题与知识点的关联信息
			qstRelService.deleteRelByQstId(qstId);
			this.setResult(new Result(true,"1",null,null));
		} catch (RuntimeException e) {
			logger.error("QstAction.Qstdel",e);
			this.setResult(new Result(true,"0",null,null));
			return ERROR;
		}
		return "json";
	}
	
	/**
	 *转向材料分析试题添加页面
	 */	
	public String toQstcailiaoAdd(){
		
		try {
			qst=qstService.getQstCaiLiaoToAdd(qstId);
			
			//过滤HTML标签  截取题干长度30
			for (Qst ziti : qst.getQstziti()) {
				String qstContent = StringUtil.filterHTML(ziti.getQstContent());
				qstContent = StringUtil.chop(qstContent, 30, "...");
				ziti.setQstContent(qstContent);				
			}
			
			subjectList = subjectService.getAllSubject();
			examlevel = configurator.getExamlevel();
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
	//		professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
		} catch (RuntimeException e) {
			logger.error("QstAction.toQstcailiaoAdd",e);
			return ERROR;
		}
		return "toQstcailiaoAdd";
	}
	
	/**
	  * 
	  * 材料分析试题添加页面
	  * @return
	  */
	 public String QstcailiaoAdd(){
		 try {
			 int qst_Id =qstaddMethodsziti(qst.getQstType());
			 if(qst_Id!=-1)
			 {
				 optionMethods(qst.getQstType(),qst_Id);
				 if(ksnId!=0){
						QstRel qr = new QstRel();
						qr.setKsnId(ksnId);
						qr.setNodeId(nodeId);
						qr.setQstId(qst_Id);
						qr.setQstType(qst.getQstType());
						qr.setQstContent(qst.getQstContent());
						qstRelService.addQstRel(qr);
					}
				qst=qstService.getQstById(qst.getQstId());
				
				//过滤HTML标签  截取题干长度30
				for (Qst ziti : qst.getQstziti()) {
					String qstContent = StringUtil.filterHTML(ziti.getQstContent());
					qstContent = StringUtil.chop(qstContent, 30, "...");
					ziti.setQstContent(qstContent);				
				}
				
				subjectList = subjectService.getAllSubject();
			//	examlevel = configurator.getExamlevel();
				courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
				
			//	professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
				 return "toQstcailiaoAdd";
			 }
			 return "ERROR";
		} catch (RuntimeException e) {
			logger.error("QstAction.QstcailiaoAdd",e);
			return ERROR;
		}
		
	 }

	 /**
	  * 往试题表中添加数据
	  * @param qst_type
	  */
	 public  int qstaddMethodsziti(int qst_type)
	 {
	 	int qstId=0;
		try {
			Qst question = new Qst();
			question.setEpId(37);//添加试题--------------------------------------------
			question.setScore(0);
			question.setQstindexId(qst.getQstId());
			question.setQstContent(qst.getQstContent().replaceAll("\r\n", "<br>"));
			question.setWrongJude(qst.getWrongJude());
			question.setQstType(qst.getQstType());
			question.setQsttype(qst.getQsttype());
			question.setKorcouId(qst.getKorcouId());
			question.setCourseId(qst.getCourseId());
			question.setSortId(qst.getSortId());
			System.out.println(qst.getSortId());
			question.setNote(qst.getNote());
			 if(qst_type == 1){//客观题时，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}else if(qst_type == 2){//多选题，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}
				else if(qst_type == 4){//多选题，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}
				else if(qst_type == 5){//多选题，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}
				else if(qst_type == 3){//判断，答案存储于选项表中
					question.setIsAsr(qst.getIsAsr());
				}else if(qst_type == 6){
					question.setIsAsr("main");
				}
				question.setQstType(qst_type);//试题的类型（单选，主观）
				
				question.setCtPerson("A");
				question.setLevel(qst.getLevel());
				User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
				question.setAuthor(users.getLoginName());
				question.setLastEditTime(new Date());
				 qstId = qstService.addQst(question);
		} catch (RuntimeException e) {
			logger.error("QstAction.qstaddMethodsziti",e);
			return -1;
		}
			return qstId;
	 }
	 
	
	/**
	 * 转向更新页面
	 */
	public String toQstUpdate(){
		try {
			subjectList = subjectService.getAllSubject();

			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
			//知识库所属
			qstRel = qstRelService.getQstRelByQstId(qstId);
			if(qstRel!=null){
				sysdto = sysService.getSysByKsnId(qstRel.getKsnId());
			}
			
			qst=qstService.getQstToUpdateById(qstId);
			this.getQueryQstmiddleCondition().setQstId(qstId);
			qstmiddleList=qstmiddleService.getQstmiddleList(this.getQueryQstmiddleCondition());
			chkIdList.add("Aasr");
			chkIdList.add("Basr");
			chkIdList.add("Casr");
			chkIdList.add("Dasr");
			chkIdList.add("Easr");
			chkIdList.add("Fasr");
			chkIdList.add("Gasr");
		} catch (Exception e) {
			logger.error("QstAction.toQstUpdate",e);
			return ERROR;
		}
		return "toQstUpdate";
	}
	
 /**
  * 试题的更新
  */
	public String QstUpdate(){
		try {
			String al[]=servletRequest.getParameterValues("asr");
			Qst quest = qstService.getQstById(qst.getQstId());
			quest.setCourseId(qst.getCourseId());
			quest.setKorcouId(qst.getKorcouId());
			String aasr[]=qst.getIsAsr().split(",");
			StringBuffer buf = new StringBuffer();
			for(int i=0;i<aasr.length;i++){
				buf.append(aasr[i].trim());
			}
			quest.setIsAsr(buf.toString());
			quest.setLevel(qst.getLevel());
			quest.setNote(qst.getNote());
			quest.setQstContent(qst.getQstContent());
			quest.setQsttype(qst.getQsttype());
			quest.setQstType(qst.getQstType());
			quest.setWrongJude(qst.getWrongJude());
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			quest.setAuthor(users.getLoginName());
			quest.setLastEditTime(new Date());
			qstService.updateQst(quest);
			List<Options> options = quest.getOptions();
			for(int i=0;i<options.size();i++){
				Options option = options.get(i);
				option.setOptContent(al[i].replaceAll("\r\n", "<br>"));
				optionsService.updateOptions(option);
			}
			
			/**
			 * 修改与知识库连接关系
			 */
			if(ksnId!=0){
				QstRel qr = qstRelService.getQstRelByQstId(quest.getQstId());
				if(qr!=null){
					qr.setKsnId(ksnId);
					qr.setNodeId(nodeId);
					qr.setQstContent(quest.getQstContent());
					qr.setQstType(quest.getQstType());
					qstRelService.updateQstRel(qr);
				}else{
					qr = new QstRel();
					qr.setKsnId(ksnId);
					qr.setNodeId(nodeId);
					qr.setQstContent(quest.getQstContent());
					qr.setQstType(quest.getQstType());
					qr.setQstId(quest.getQstId());
					qstRelService.addQstRel(qr);
				}
			}
			
		} catch (Exception e) {
			logger.error("QstAction.QstUpdate",e);
			return ERROR;
		}
		return "QstUpdate";
	}
	
/**
 * 试题全部列表
 */

 public String  QstList(){
		try {
			String res=this.getQueryQstCondition().getSearchKey();
			String author = this.getQueryQstCondition().getAuthor();
			if(res!=null){
				this.getQueryQstCondition().setSearchKey((URLDecoder.decode(res, "UTF-8")).trim());
			}
			if(author!=null){
				this.getQueryQstCondition().setAuthor((URLDecoder.decode(author, "UTF-8")).trim());
			}
			this.getQueryQstCondition().setPageSize(20);
			setPage(qstService.getQstListPageAllqst(this.getQueryQstCondition()));
			this.getPage().setPageSize(20);
			
			subjectList = subjectService.getAllSubject();
			
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("QstAction.QstUpdate",e);
			return ERROR;
		}
	 return "Qstlist";
 }
 
 /**
  * 试题全部列表弹窗
  */

 /**
  * 转向添加试题页面
 * @throws UnsupportedEncodingException 
  */
 public String  QstListtanchuan() {
		try {
			this.getQueryQstCondition().setQstType(type);
			this.getQueryQstCondition().setSearchKey(searchKey);
			//查询所有的课程；
			courseSortList = qstService.getCourseList();
			this.getQueryQstCondition().setPageSize(10);
			setPage(qstService.getQstListPageAll(this.getQueryQstCondition()));
			this.getPage().setPageSize(10);
			setPageUrlParms();
			
		} catch (RuntimeException e) {
			logger.error("QstAction.QstListtanchuan",e);
			return ERROR;
		}
	 
	 return "Qstlisttanchuan";
}
 
 public String toQstadd(){
	 
	 	try {
	 		//查询所有可用专业
			subjectList = subjectService.getAllSubject();
			//得到试题难度
	//		examlevel = configurator.getExamlevel();
			//获取子课程集合
			courseSortList = coursesortService.getChildCoursesortList(new QueryCoursesortCondition());
			
	//		professionalList=this.professionalService.getProfessionalList(new QueryProfessionalCondition());
			
			 if(ActionContext.getContext().getSession().get("qstIdd")!=null){
				 isNuber=1;
				 int q=Integer.parseInt(ActionContext.getContext().getSession().get("qstIdd").toString());
				 qstAddQueryDto = qstService.getQstAddById(q);
			 }else{
				 isNuber=0;
			 }
		} catch (RuntimeException e) {
			logger.error("QstAction.toQstadd",e);
			return ERROR;
		}
	 return "toqstadd";
 }

 /**
  * 
  * 试题的添加方法
  * @return
  */
 public String QstAdd(){
	 
	 try {
		 
		 int qstId =qstaddMethods(qst.getQstType());
		 ActionContext.getContext().getSession().remove("qstIdd");
		 ActionContext.getContext().getSession().put("qstIdd", qstId);
		 if(qstId!=-1)
		 {
			 optionMethods(qst.getQstType(),qstId);
			
			if(ksnId!=0){
				QstRel qr = new QstRel();
				qr.setKsnId(ksnId);
				qr.setNodeId(nodeId);
				qr.setQstId(qstId);
				qr.setQstType(qst.getQstType());
				qr.setQstContent(qst.getQstContent());
				qstRelService.addQstRel(qr);
			}
			 return "QstAdd";
		 }
		 
		 return "ERROR";
	} catch (Exception e) {
		logger.error("QstAction.QstAdd",e);
		return ERROR;
	}
	
 }
 
 /**
  * 往试题表中添加数据
  * @param qst_type
  */
 public  int qstaddMethods(int qst_type)
 {
 	int qstId=0;
	try {
		Qst question = new Qst();
		question.setEpId(37);//添加试题--------------------------------------------
		question.setScore(0);
		question.setQstContent(qst.getQstContent().replaceAll("\r\n", "<br>"));
		question.setWrongJude(qst.getWrongJude());
		question.setQstType(qst.getQstType());
		question.setQsttype(qst.getQsttype());
		question.setKorcouId(qst.getKorcouId());
		question.setCourseId(qst.getCourseId());
		question.setSortId(qst.getSortId());
		question.setNote(qst.getNote());
		 if(qst_type == 1){//客观题时，答案存储于选项表中
				question.setIsAsr(qst.getIsAsr());
			}else if(qst_type == 2){//多选题，答案存储于选项表中
				String aasr[]=qst.getIsAsr().split(",");
				StringBuffer buf = new StringBuffer();
				for(int i=0;i<aasr.length;i++){
					buf.append(aasr[i].trim());
				}
				question.setIsAsr(buf.toString());
			}
			else if(qst_type == 4){//多选题，答案存储于选项表中
				question.setIsAsr(qst.getIsAsr());
			}
			else if(qst_type == 5){//多选题，答案存储于选项表中
				String aasr[]=qst.getIsAsr().split(",");
				StringBuffer buf = new StringBuffer();
				for(int i=0;i<aasr.length;i++){
					buf.append(aasr[i].trim());
				}
				question.setIsAsr(buf.toString());
			}
			else if(qst_type == 3){//判断，答案存储于选项表中
				question.setIsAsr(qst.getIsAsr());
			}else if(qst_type == 6){
				question.setIsAsr("main");
			}
			question.setQstType(qst_type);//试题的类型（单选，主观）
			
			question.setCtPerson("A");
			question.setLevel(qst.getLevel());
			User users=this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			question.setAuthor(users.getLoginName());
			question.setLastEditTime(new Date());
			 qstId = qstService.addQst(question);
	} catch (RuntimeException e) {
		logger.error("QstAction.qstaddMethods",e);
		return -1;
	}
		return qstId;
 }
 
	 /**
	  * 选项表录入数据
	  * @param qst_type
	  * @param qstId
	  */
	 public void optionMethods(int qst_type ,int qstId)
	 { 
		 orderList.add("A");
		 orderList.add("B");
		 orderList.add("C");
		 orderList.add("D");
		 orderList.add("E");
		 orderList.add("F");
		 orderList.add("G");
		 try {
			Options option = null;//选项
			 String tempid="asr";
			 if(qst_type == 1){//单选题
				 
				 String[] al=servletRequest.getParameterValues("asr");
					
					for(int i=0;i<4;i++){
						option = new Options();//添加选项---------------------------------
						option.setQstId(qstId);
						option.setOptContent(al[i].replaceAll("\r\n", "<br>"));
						option.setOptOrder(orderList.get(i).toString());
						optionsService.addOptions(option);
					}
					
				}else if(qst_type == 2){//多选题
					String j=servletRequest.getParameter("duoxuanNumber");
					String[] al=servletRequest.getParameterValues("asr");
						
						for(int i=0;i<Integer.parseInt(j);i++){
							option = new Options();//添加选项---------------------------------
							option.setQstId(qstId);
							option.setOptContent(al[i].replaceAll("\r\n", "<br>"));
							option.setOptOrder(orderList.get(i).toString());
							optionsService.addOptions(option);
						}
					
					
				}else if(qst_type == 4){//材料分析题
					option = new Options();//添加选项---------------------------------
					option.setOptContent(servletRequest.getParameter("A" + tempid).replaceAll("\r\n", "<br>"));
					option.setQstId(qstId);
					option.setOptOrder("A");
					optionsService.addOptions(option);
					
				}
				else if(qst_type == 5){//图表题
					String j=servletRequest.getParameter("tubiaoNumber");
					String[] al=servletRequest.getParameterValues("asr");
						
						for(int i=0;i<Integer.parseInt(j);i++){
							option = new Options();//添加选项---------------------------------
							option.setQstId(qstId);
							option.setOptContent(al[i].replaceAll("\r\n", "<br>"));
							option.setOptOrder(orderList.get(i).toString());
							optionsService.addOptions(option);
						}
					
				}else if(qst_type == 3){//判断题
					String[] al=servletRequest.getParameterValues("asr");
					option = new Options();//添加选项---------------------------------
					option.setOptContent(al[0].replaceAll("\r\n", "<br>"));
					option.setQstId(qstId);
					option.setOptOrder("A");
					optionsService.addOptions(option);
					
					option = new Options();
					option.setOptContent(al[1].replaceAll("\r\n", "<br>"));
					option.setQstId(qstId);
					option.setOptOrder("B");
					optionsService.addOptions(option);
					
					
				}else if(qst_type == 6){//主观题
					option = new Options();
					option.setOptContent(servletRequest.getParameter("zhuguanContent").replaceAll("\r\n", "<br>"));
					option.setQstId(qstId);
					option.setOptOrder("main");
					optionsService.addOptions(option);
				}
		} catch (Exception e) {
			logger.error("QstAction.optionMethods",e);
		} 
		 
	 }
	 
		/**
		 * 根据项目Id获取知识树
		 * @author 代长福
		 * @return
		 */
	public String getSysBySubjectId(){
		try {
			sysinfolist = sysService.getSysBySubjectId(subjectId);
			StringBuffer bf = new StringBuffer();
			bf.append("<option value=\"-1\">请选择知识树</option>");
			for (Sys sys : sysinfolist) {
				bf.append("<option value="+sys.getKsId()+">"+sys.getSubjectName()+"</option><br>");
			}
			this.setResult(new Result<String>(true,bf.toString(),null,null));
		} catch (Exception e) {
			logger.error("QstAction.getSysBySubjectId", e);
			return "error";
		}
		return "json";
	}
	
	/**
	 * 根据专业ID获得知识库知识点列表 add by yanghaibo 2012-08-02 16:35
	 * @return
	 */
	public String getSysNodeBySubjectId(){
		try {
			sysinfolist = sysService.getSysBySubjectId(subjectId);
			if(sysinfolist.size()==1){
				ksId = sysinfolist.get(0).getKsId();
			}
			List<SysNode> sysNodeList = sysNodeService.getSysTreeById(ksId);
			this.setResult(new Result<List<SysNode>>(true,"",null,sysNodeList));
			return "json";
		} catch (Exception e) {
			logger.error("QstAction.getSysNodeBySubjectId", e);
			return "error";
		}
	}

	/**
	 * 得到知识树
	 * @return
	 */
	public String getSysTreeById(){
		try {
			List<SysNode> sysNodeList = sysNodeService.getSysTreeById(ksId);
			this.setResult(new Result<List<SysNode>>(true,"",null,sysNodeList));
			return "json";
		} catch (Exception e) {
			logger.error("QstAction.getSysTreeById", e);
			return "error";
		}
	}
		

	public Qst getQst() {
		return qst;
	}

	public void setQst(Qst qst) {
		this.qst = qst;
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

	public Map<Integer, String> getExamlevel() {
		return examlevel;
	}

	public void setExamlevel(Map<Integer, String> examlevel) {
		this.examlevel = examlevel;
	}

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
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

	public IProfessional getProfessionalService() {
		return professionalService;
	}

	public void setProfessionalService(IProfessional professionalService) {
		this.professionalService = professionalService;
	}

	public IQst getQstService() {
		return qstService;
	}

	public void setQstService(IQst qstService) {
		this.qstService = qstService;
	}

	public IQstKb getQstKbService() {
		return qstKbService;
	}

	public void setQstKbService(IQstKb qstKbService) {
		this.qstKbService = qstKbService;
	}

	public IOptions getOptionsService() {
		return optionsService;
	}

	public void setOptionsService(IOptions optionsService) {
		this.optionsService = optionsService;
	}

	public QueryQstCondition getQueryQstCondition() {
		if (queryQstCondition == null) {
			queryQstCondition = new QueryQstCondition();
		}
		return queryQstCondition;
	}

	public void setQueryQstCondition(QueryQstCondition queryQstCondition) {
		this.queryQstCondition = queryQstCondition;
	}

	public int getQstId() {
		return qstId;
	}

	public void setQstId(int qstId) {
		this.qstId = qstId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public IQstmiddle getQstmiddleService() {
		return qstmiddleService;
	}

	public void setQstmiddleService(IQstmiddle qstmiddleService) {
		this.qstmiddleService = qstmiddleService;
	}

	public List<Qstmiddle> getQstmiddleList() {
		if (qstmiddleList == null) {
			qstmiddleList = new ArrayList<Qstmiddle>();
		}
		return qstmiddleList;
	}

	public void setQstmiddleList(List<Qstmiddle> qstmiddleList) {
		this.qstmiddleList = qstmiddleList;
	}

	public QueryQstmiddleCondition getQueryQstmiddleCondition() {
		if (queryQstmiddleCondition == null) {
			queryQstmiddleCondition = new QueryQstmiddleCondition();
		}
		return queryQstmiddleCondition;
	}

	public void setQueryQstmiddleCondition(
			QueryQstmiddleCondition queryQstmiddleCondition) {
		this.queryQstmiddleCondition = queryQstmiddleCondition;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public List getChkIdList() {
		return chkIdList;
	}

	public void setChkIdList(List chkIdList) {
		this.chkIdList = chkIdList;
	}

	public List getOrderList() {
		return orderList;
	}

	public void setOrderList(List orderList) {
		this.orderList = orderList;
	}

	public int getIsNuber() {
		return isNuber;
	}

	public void setIsNuber(int isNuber) {
		this.isNuber = isNuber;
	}
	
	public QstAddQueryDTO getQstAddQueryDto() {
		return qstAddQueryDto;
	}
	public void setQstAddQueryDto(QstAddQueryDTO qstAddQueryDto) {
		this.qstAddQueryDto = qstAddQueryDto;
	}
	public List<Sys> getSysinfolist() {
		return sysinfolist;
	}
	public void setSysinfolist(List<Sys> sysinfolist) {
		this.sysinfolist = sysinfolist;
	}
	public int getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
	public void setSysService(ISys sysService) {
		this.sysService = sysService;
	}
	public void setSysNodeService(ISysNode sysNodeService) {
		this.sysNodeService = sysNodeService;
	}
	public int getKsId() {
		return ksId;
	}
	public void setKsId(int ksId) {
		this.ksId = ksId;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public int getKsnId() {
		return ksnId;
	}
	public void setKsnId(Integer ksnId) {
		this.ksnId =(ksnId!=null?ksnId:0);
	}
	public void setQstRelService(IQstRel qstRelService) {
		this.qstRelService = qstRelService;
	}

	public SysDTO getSysdto() {
		return sysdto;
	}
	public void setSysdto(SysDTO sysdto) {
		this.sysdto = sysdto;
	}
	public QstRel getQstRel() {
		return qstRel;
	}
	public void setQstRel(QstRel qstRel) {
		this.qstRel = qstRel;
	}
	public IOptRecord getOptRecordService() {
		return optRecordService;
	}
	public void setOptRecordService(IOptRecord optRecordService) {
		this.optRecordService = optRecordService;
	}
	public Exampaper getExam() {
		return exam;
	}
	public void setExam(Exampaper exam) {
		this.exam = exam;
	}
	
}
