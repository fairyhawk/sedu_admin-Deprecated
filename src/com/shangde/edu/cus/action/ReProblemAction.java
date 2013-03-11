package com.shangde.edu.cus.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import net.sf.json.JSONArray;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryProblemCondition;
import com.shangde.edu.cus.condition.QueryReProblemCondition;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.Problem;
import com.shangde.edu.cus.domain.ReProblem;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.cus.service.IProblem;
import com.shangde.edu.cus.service.IReProblem;
import com.shangde.edu.sys.condition.QueryUserCondition;
import com.shangde.edu.sys.condition.QuerySubjectCondition;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IUser;

/**
 * 
 * @author miaoshusen
 * @version 1.0
 */

public class ReProblemAction extends CommonAction {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = -7880869250883740437L;
	private static final Logger logger = Logger
			.getLogger(ReProblemAction.class);
	private String dir = ServletActionContext.getServletContext().getRealPath(
			"/excelfile1/");
	/**
	 * 分页类
	 */
	private PageQuery pageQuery;
	/**
	 * 问题实体对象
	 */
	private Problem problem;
	/**
	 * 问题的服务
	 */
	private IProblem problemService;
	/**
	 * 专业
	 */
	private ISubject subjectService;
	/**
	 * 查询条件
	 */
	private QueryProblemCondition queryProblemCondition;
	private QuerySubjectCondition querySubjectCondition;
	/**
	 * 问题list
	 */
	private List<Problem> problemList;
	/**
	 * 前台学员
	 */
	private ICustomer customerService;
	private List<Customer> customerList = new ArrayList<Customer>();
	private Customer customer;
	private QueryCustomerCondition queryCustomerCondition;
	private List<Customer> scustomerList = new ArrayList<Customer>();
	/**
	 * 课程实体
	 */
	private Course course;
	/**
	 * 课程服务
	 */
	private ICourse courseService;
	/**
	 * 课程list
	 */
	private List<Course> courseList = new ArrayList<Course>();
	private Set<Course> courseSet = new HashSet<Course>();
	private Set<Customer> customerSet = new HashSet<Customer>();
	private List<Course> scourseList = new ArrayList<Course>();
	/**
	 * 课程查询条件
	 */
	private QueryCourseCondition queryCourseCondition;
	/**
	 * 回答问题实体对象
	 */
	private ReProblem reproblem;
	/**
	 * 回答问题服务
	 */
	private IReProblem reProblemService;
	private QueryReProblemCondition queryReProblemCondition;
	private List<ReProblem> reProblemList = new ArrayList<ReProblem>();

	private String exportName;
	/**
	 * 后台用户
	 */
	private User user;
	private IUser userService;
	private QueryUserCondition queryUserCondition;
	private List<User> userList = new ArrayList<User>();

	private int subjectId;// 项目id
	private int pblHot = -1;// 是否热门
	private int pblId;// 问题id
	private String pblTitle;// 问题标题：
	private int pblSolv = -1;// 是否解决
	private int pblStatus = -1;// 是否隐藏
	private int pblType = -1;// 问题类型
	private String startTime;
	private String startHH;
	private String endTime;
	private String endHH;
	private Date queryStartTime;// 查询开始时间
	private Date queryEndTime;// 查询结束时间
	private String useremail;// 用户名
	private String userothername;// 昵称
	private int hasReply = -1;// 是否已经回复
	private InputStream excelFile;
	private int updateType;// 问题修改类型
	private String pblContent;// 问题内容
	private String recontent;// 用户回复内容
	private int reIsResult;// 用户回复内容是否设置为最佳答案
	private int repbid;// 答案id
	private int restatus;// 答案状态
	private int reupdatetype;// 答案修改类型

	private List<Subject> subjectList = new ArrayList<Subject>();

	/**
	 * 
	 * @Title: getProblemList
	 * @Description: TODO(获取问题列表页面)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String getProblemList() {
		try {

			/**
			 * 组装时间选择框列表
			 */
			getSelectTime();
			if (pblTitle != null && !"".equals(pblTitle.trim())) {
				pblTitle = URLDecoder.decode(pblTitle, "UTF-8");// 将这类代码转换成中文
				this.getQueryProblemCondition().setPblTitle(pblTitle);
			}
			if (userothername != null && !"".equals(userothername.trim())) {
				userothername = URLDecoder.decode(userothername, "UTF-8");// 将这类代码转换成中文
				this.getQueryProblemCondition().setUserothername(userothername);
			}

			// 查询条件
			this.getQueryProblemCondition().setSubjectId(subjectId);
			this.getQueryProblemCondition().setPblHot(pblHot);
			this.getQueryProblemCondition().setPblType(pblType);
			this.getQueryProblemCondition().setPblSolv(pblSolv);
			this.getQueryProblemCondition().setPblStatus(pblStatus);
			this.getQueryProblemCondition().setQueryStartTime(queryStartTime);
			this.getQueryProblemCondition().setQueryEndTime(queryEndTime);
			this.getQueryProblemCondition().setHasReply(hasReply);
			this.getQueryProblemCondition().setUseremail(useremail);
			// this.getQueryProblemCondition().setUserothername(userothername);
			this.getQueryProblemCondition().setPageSize(30);

			// 查询问题列表
			setPage(this.problemService
					.getPageProblemList(getQueryProblemCondition()));
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(30);
			}
			// 查询专业列表
			subjectList = subjectService.getSubjectList(querySubjectCondition);

		} catch (Exception ex) {
			logger.error("ReProblemAction.getProblemList", ex);
			return ERROR;
		}
		return "getProblemList";
	}

	/**
	 * 
	 * @Title: exportProplemExcel
	 * @Description: TODO(导出excel)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */

	public String exportProplemExcel() {
		try {
			/**
			 * 组装时间选择框列表
			 */
			getSelectTime();
			if (pblTitle != null && !"".equals(pblTitle.trim())) {
				pblTitle = URLDecoder.decode(pblTitle, "UTF-8");// 将这类代码转换成中文
				this.getQueryProblemCondition().setPblTitle(pblTitle);
			}
			if (userothername != null && !"".equals(userothername.trim())) {
				userothername = URLDecoder.decode(userothername, "UTF-8");// 将这类代码转换成中文
				this.getQueryProblemCondition().setUserothername(userothername);
			}
			// 查询条件
			this.getQueryProblemCondition().setSubjectId(subjectId);
			this.getQueryProblemCondition().setPblHot(pblHot);
			this.getQueryProblemCondition().setPblType(pblType);
			this.getQueryProblemCondition().setPblSolv(pblSolv);
			this.getQueryProblemCondition().setPblStatus(pblStatus);
			this.getQueryProblemCondition().setQueryStartTime(queryStartTime);
			this.getQueryProblemCondition().setQueryEndTime(queryEndTime);
			this.getQueryProblemCondition().setHasReply(hasReply);
			this.getQueryProblemCondition().setUseremail(useremail);
			// this.getQueryProblemCondition().setUserothername(userothername);

			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			String expName = "highso" + sdf.format(new Date());
			if (ServletActionContext.getRequest().getHeader("User-Agent")
					.toUpperCase().indexOf("MSIE") > 0) {
				setExportName(URLEncoder.encode(expName, "UTF-8"));// IE浏览器
			} else {
				setExportName(new String((expName).getBytes(), "gbk"));
			}
			createExcelFile(expName);
			this.setResult(new Result(true, exportName, null, null));
			return "json";
		} catch (Exception ex) {
			logger.error("ReProblemAction.exportProplemExcel", ex);
			ex.printStackTrace();
			return "error";
		}

	}

	/**
	 * 
	 * @Title: createExcelFile
	 * @Description: TODO(创建excel文件)
	 * @param
	 * @param expName
	 *            设定文件
	 * @return void 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	private void createExcelFile(String expName) {
		try {
			logger.debug("====highso createExcelFile():" + expName);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			// 表头标题
			List<String> headName = new ArrayList<String>();
			headName.add("用户名");
			headName.add("昵称");
			headName.add("问题标题");
			headName.add("问题内容");
			headName.add("专业名称");
			headName.add("问题类别");
			headName.add("是否回复");
			headName.add("回复数量");
			headName.add("最佳问题回复时间");
			headName.add("最佳问题回复人");
			headName.add("是否解决");
			headName.add("是否隐藏");
			headName.add("是否热门");
			headName.add("提问时间");

			deleteFile(new File(dir));
			int count = problemService.getProblemCount(queryProblemCondition);
			int a = 1;
			if (count % 5000 == 0) {
				a = count / 5000;
			} else {
				a = count / 5000 + 1;
			}

			List<File> srcfile = new ArrayList<File>();
			for (int k = 0; k < a; k++) {
				List<Problem> ProblemList = new ArrayList<Problem>();
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("sheet");
				if (pageQuery != null) {
					pageQuery.setPageSize(5000);
					pageQuery.setCurrentPage(k + 1);
					ProblemList = problemService.getPageProblemList(
							getQueryProblemCondition()).getPageResult();
				} else {
					queryProblemCondition.setPageSize(5000);
					queryProblemCondition.setCurrentPage(k + 1);
					ProblemList = problemService.getPageProblemListForExcel(
							getQueryProblemCondition()).getPageResult();
				}

				// 创建列数
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell = row.createCell((short) 0);
				for (int i = 0; i < headName.size(); i++) {
					cell = row.createCell((short) i);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(headName.get(i).toString());
				}
				for (int i = 0; i < ProblemList.size(); i++) {
					row = sheet.createRow(i + 1);
					int j = 0;
					// 用户名
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell
							.setCellValue(ProblemList.get(i).getCusEmail() == null ? ""
									: ProblemList.get(i).getCusEmail());
					// 昵称
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell
							.setCellValue(ProblemList.get(i).getCusname() == null ? ""
									: ProblemList.get(i).getCusname());
					// 问题标题
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(ProblemList.get(i).getPblTitle());
					// 问题内容
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(FunctionUtil.Html2Text(ProblemList.get(i)
							.getPblContent()));
					// 专业名称
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(ProblemList.get(i).getSubjectName());
					// 问题类型
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (ProblemList.get(i).getPblType() == 1) {
						cell.setCellValue("考试问题");
					} else if (ProblemList.get(i).getPblType() == 2) {
						cell.setCellValue("课程问题");
					} else if (ProblemList.get(i).getPblType() == 3) {
						cell.setCellValue("视频问题");
					} else if (ProblemList.get(i).getPblType() == 4) {
						cell.setCellValue("讲义问题");
					}
					// 是否回复
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (ProblemList.get(i).getReflayCount() > 0) {
						cell.setCellValue("是");
					} else {
						cell.setCellValue("否");
					}
					// 回复数量
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue("" + ProblemList.get(i).getReflayCount());

					// 最佳答案回复时间
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (ProblemList.get(i).getBestReTime() == null) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(sdf.format(ProblemList.get(i)
								.getBestReTime()));
					}

					// 最佳答案回复人
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (ProblemList.get(i).getBestReEmail() == null) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(ProblemList.get(i).getBestReEmail());
					}

					// 是否解决
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (ProblemList.get(i).getPblSolv() == 1) {
						cell.setCellValue("已解决");
					} else if (ProblemList.get(i).getPblSolv() == 2) {
						cell.setCellValue("未解决");
					}
					// 是否隐藏
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (ProblemList.get(i).getPblStatus() == 0) {
						cell.setCellValue("未隐藏");
					} else if (ProblemList.get(i).getPblStatus() == 1) {
						cell.setCellValue("已隐藏");
					}
					// 是否热门
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (ProblemList.get(i).getPblHot() == 1) {
						cell.setCellValue("是");
					} else if (ProblemList.get(i).getPblHot() == 0) {
						cell.setCellValue("否");
					}
					// 提问时间
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(sdf.format(ProblemList.get(i)
							.getCreateTime()));
				}

				File filedir = new File(dir);
				if (!filedir.exists()) {
					filedir.mkdir();
				}
				File file = new File(dir + "/excel" + k + ".xls");
				FileOutputStream fos = new FileOutputStream(file);
				workbook.write(fos);
				srcfile.add(file);
				fos.close();
			}
			File zipfile = new File(dir + "/" + expName + ".rar");
			zipFiles(srcfile, zipfile);
			for (int i = 0; i < a; i++) {
				File file = new File(dir + "/excel" + i + ".xls");
				file.delete();
			}

		} catch (IOException e) {

			logger.error("执行exportProplemExcel方法执行createExcelFile错误！", e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: deleteFile
	 * @Description: TODO(循环删除file列表)
	 * @param
	 * @param file
	 *            设定文件
	 * @return void 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	private void deleteFile(File file) {
		File[] temp = file.listFiles();
		if (temp != null && temp.length > 0) {
			for (int i = 0; i < temp.length; i++) {
				if (temp[i].isDirectory()) {
					if (temp[i].listFiles().length != 0)
						this.deleteFile(temp[i]);
					this.deleteDir(temp[i]);
				} else {
					temp[i].delete();
				}
			}
		}

	}

	/**
	 * 
	 * @Title: deleteDir
	 * @Description: TODO(删除单个file)
	 * @param
	 * @param file
	 *            设定文件
	 * @return void 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	private void deleteDir(File file) {
		if (file.listFiles().length == 0)
			file.getAbsoluteFile().delete();
	}

	/**
	 * 
	 * @Title: zipFiles
	 * @Description: TODO(压缩文件)
	 * @param
	 * @param srcfile
	 * @param
	 * @param zipfile
	 *            设定文件
	 * @return void 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
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
			e.printStackTrace();
			logger.error("ZipUtil zipFiles exception:" + e);
		}
	}

	/**
	 * 
	 * @Title: deleteProblem
	 * @Description: TODO(删除问题)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String deleteProblem() {
		// 先删除子表在删除父表
		// 删除问题对应的答案
		try {
			if (problem.getPblId() != 0) {
				this.getQueryReProblemCondition().setPblId(problem.getPblId());
			}
			reProblemList = this.reProblemService
					.getReProblemList(getQueryReProblemCondition());
			for (int i = 0; reProblemList != null && i < reProblemList.size(); i++) {
				this.reProblemService.delReProblemById(reProblemList.get(i)
						.getReId());
			}
			// 删除问题
			this.problemService.delProblemById(problem.getPblId());
		} catch (Exception ex) {
			logger.error("ReProblemAction.deleteProblem", ex);
			return ERROR;
		}

		return "deleteProblemSuccess";
	}

	/**
	 * 
	 * @Title: toHotProblem
	 * @Description: TODO(设置或者取消热门)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String toHotProblem() {
		try {
			if (problem.getPblId() != 0) {
				Problem newproblem = this.problemService.getProblemById(problem
						.getPblId());
				newproblem.setPblHot(problem.getPblHot());
				this.problemService.updateProblem(newproblem);
			}

		} catch (Exception ex) {
			logger.error("ReProblemAction.toHotProblem", ex);
			return ERROR;
		}
		return this.getProblemList();
	}

	/**
	 * 
	 * @Title: toHideProblem
	 * @Description: TODO(隐藏或者取消隐藏)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String toHideProblem() {
		try {
			if (problem.getPblId() != 0) {
				Problem newproblem = this.problemService.getProblemById(problem
						.getPblId());
				newproblem.setPblStatus(problem.getPblStatus());
				this.problemService.updateProblem(newproblem);
			}

		} catch (Exception ex) {
			logger.error("ReProblemAction.toHotProblem", ex);
			return ERROR;
		}
		return this.getProblemList();
	}

	/**
	 * 
	 * @Title: toProblemView
	 * @Description: TODO(跳转到问题详情页面)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String toProblemView() {
		try {
			if (problem.getPblId() != 0) {
				problem = this.problemService
						.getProblemById(problem.getPblId());
				/**
				 * 查询最佳答案
				 */
				List<ReProblem> prList = problem.getReProblemList();
				for (ReProblem rp : prList) {
					if (rp.getIsResult() == 1) {
						reproblem = rp;
						reproblem.setReInfo(FunctionUtil.Html2Text(rp
								.getReInfo()));
						break;
					}
				}
				/**
				 * 分页查询答案列表
				 */
				this.getQueryReProblemCondition().setPageSize(10);
				this.getQueryReProblemCondition().setPblId(problem.getPblId());
				PageResult pr = this.reProblemService
						.getPageReProblemList(queryReProblemCondition);
				List<ReProblem> newrepList = new ArrayList<ReProblem>();
				if (pr != null && pr.getPageResult() != null
						&& pr.getPageResult().size() > 0) {
					List<ReProblem> repList = pr.getPageResult();
					for (ReProblem rp : repList) {
						rp.setReInfo(FunctionUtil.Html2Text(rp.getReInfo()));
						newrepList.add(rp);
					}
				}
				pr.setPageResult(newrepList);
				setPage(pr);
				setPageUrlParms();
			}
		} catch (Exception ex) {
			logger.error("ReProblemAction.toProblemViewNew", ex);
			return ERROR;
		}
		return "problemview";
	}

	/**
	 * 
	 * @Title: addReProblem
	 * @Description: TODO(管理员回复问题)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String addReProblem() {
		try {
			if (user == null) {
				user = this.getLoginedUser();
			}
			Date date = new Date();
			reproblem.setReTime(date);
			reproblem.setReManId(user.getUserId());
			reproblem.setReManType(ReProblem.REPROBLEM_USER);
			reproblem.setPblId(problem.getPblId());
			this.reProblemService.addReProblem(reproblem);
		} catch (Exception ex) {
			logger.error("ReProblemAction.addReProblem", ex);
			return ERROR;
		}

		return "changeSuccess";
	}

	/**
	 * 
	 * @Title: addReProblemReply
	 * @Description: TODO(管理员回复答案并且设置最佳答案)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String addReProblemReply() {
		try {
			if (user == null) {
				user = this.getLoginedUser();
			}

			if (this.pblId != 0) {
				Problem pbl = this.problemService.getProblemById(pblId);
				if (pbl.getPblSolv() == 1) {// 问题已经解决
					this.setResult(new Result<Object>(false, "该问题已经解决，不可以再回复",
							null, null));
				} else {
					int count = 0;
					Date date = new Date();
					reproblem = new ReProblem();
					reproblem.setReTime(date);
					reproblem.setReManId(user.getUserId());
					reproblem.setReManType(ReProblem.REPROBLEM_USER);
					reproblem.setPblId(this.pblId);
					reproblem.setReInfo(this.recontent);
					if (this.reIsResult == 1) {// 设置为最佳答案
						reproblem.setIsResult(1);
						count = this.reProblemService.addReProblem(reproblem);
					} else {
						reproblem.setIsResult(0);
						this.reProblemService.addReProblem(reproblem);
					}
					if (count > 0) {// 答案入库成功
						pbl.setPblSolv(1);
						this.problemService.updateProblem(pbl);
					}
					this
							.setResult(new Result<Object>(true, "回复成功", null,
									null));
				}
			}
		} catch (Exception ex) {
			logger.error("ReProblemAction.addReProblem", ex);
			this.setResult(new Result<Object>(false, "回复失败", null, null));
			return "json";
		}

		return "json";
	}

	/**
	 * 
	 * @Title: addProblemallback
	 * @Description: TODO(回复问题前查询问题状态)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String addProblemallback() {
		try {
			if (this.pblId != 0) {

				Problem pbl = this.problemService.getProblemById(pblId);
				if (pbl != null) {
					JSONArray jsPbl = JSONArray.fromObject(pbl);
					this.setResult(new Result<Object>(true, jsPbl.toString(),
							null, null));
				}
			}
		} catch (RuntimeException e) {
			logger.error("ReProblemAction.addReProblem", e);
			return ERROR;
		}
		return "json";
	}

	/**
	 * 
	 * @Title: editProblem
	 * @Description: TODO(修改问题，ajax方式)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String editProblem() {
		try {
			if (this.pblId != 0) {
				Problem newproblem = this.problemService
						.getProblemById(this.pblId);
				if (updateType == 1) {// 修改问题title
					newproblem.setPblTitle(this.pblTitle);
					this.problemService.updateProblem(newproblem);
				} else if (updateType == 2) {// 隐藏或者取消隐藏问题
					newproblem.setPblStatus(this.pblStatus);
					this.problemService.updateProblem(newproblem);
				} else if (updateType == 3) {// 修改问题内容
					newproblem.setPblContent(this.pblContent);
					this.problemService.updateProblem(newproblem);
				}
				this.setResult(new Result<Object>(true, "success", null, null));
				// return "json";
			}
			return "json";
		} catch (Exception ex) {
			logger.error("ReProblemAction.editProblem", ex);
			return "json";
		}
		// return "deleteProblemSuccess";
	}

	/**
	 * 
	 * @Title: editReProblem
	 * @Description: TODO(修改答案)
	 * @param
	 * @return 设定文件
	 * @return String 返回类型
	 * @author shixiaofeng@sunland.org.cn
	 * @throws
	 */
	public String editReProblem() {
		try {
			Result<Object> result = new Result<Object>(true, "success", null,
					null);
			if (this.pblId != 0 && this.repbid != 0) {
				Problem newproblem = this.problemService
						.getProblemById(this.pblId);
				ReProblem reproblem = this.reProblemService
						.getReProblemById(this.repbid);
				if (newproblem != null && reproblem != null) {
					if (this.reupdatetype == 1) {// 隐藏或者显示答案
						reproblem.setReStatus(this.restatus);
						this.reProblemService.updateReProblem(reproblem);
					} else if (this.reupdatetype == 2) {// 删除回复
						if (reproblem.getIsResult() == 0) {// 不是最佳答案，直接删除
							this.reProblemService.delReProblemById(this.repbid);
						} else if (reproblem.getIsResult() == 1) {// 是最佳答案，删除后，修改问题为“未解决”
							this.reProblemService.delReProblemById(this.repbid);
							newproblem.setPblSolv(2);
							this.problemService.updateProblem(newproblem);
						}
					} else if (this.reupdatetype == 3) {// 设置或者取消最佳答案
						if (this.restatus == 1) {// 设置最佳答案
							if (newproblem.getPblSolv() == 1) {// 已解决，表示已经有最佳答案
								result.setJumpType(false);
								result.setReturnMessage("已经有最佳答案，请先取消最佳答案。");
							} else if (newproblem.getPblSolv() == 2) {// 未解决
								reproblem.setIsResult(1);
								this.reProblemService
										.updateReProblem(reproblem);
								newproblem.setPblSolv(1);
								this.problemService.updateProblem(newproblem);
								result.setReturnMessage("最佳答案设置成功");
							}
						} else if (this.restatus == 0) {// 取消最佳答案
							if (newproblem.getPblSolv() == 1
									&& reproblem.getIsResult() == 1) {
								reproblem.setIsResult(0);
								this.reProblemService
										.updateReProblem(reproblem);
								newproblem.setPblSolv(2);
								this.problemService.updateProblem(newproblem);
								result.setReturnMessage("最佳答案取消成功");
							} else {
								result.setJumpType(false);
								result
										.setReturnMessage("该问题没有解决，或者该答案不是最佳答案，不需要取消");
							}
						}
					}
					this.setResult(result);
				}
			}
			return "json";
		} catch (Exception ex) {
			logger.error("ReProblemAction.editReProblem", ex);
			return "json";
		}
	}

	/**
	 * 打开回复问题页面
	 * 
	 * @return
	 */

	// public String toProblemView() {
	// try {
	// if (problem.getPblId() != 0) {
	// problem = this.problemService
	// .getProblemById(problem.getPblId());
	// }
	// } catch (Exception ex) {
	// logger.error("ReProblemAction.toProblemView", ex);
	// return ERROR;
	// }
	// return "problemview";
	// }
	// public String toReProblem() {
	// try {
	// if (problem.getPblId() != 0) {
	// problem = this.problemService
	// .getProblemById(problem.getPblId());
	// customer = this.customerService.getCustomerById(problem
	// .getCusId());
	// this.getQueryReProblemCondition().setPblId(problem.getPblId());
	// reProblemList = this.reProblemService
	// .getReProblemList(getQueryReProblemCondition());
	// }
	// // 得出所有后台用户的集合
	// setPage(this.userService.getUserList(getQueryUserCondition()));
	// userList = userService.getUsetByList(getQueryUserCondition());
	// // 哪个老师回答的，显示出后台用户名
	// User user = this.getSession(BackLoginAction.SYS_USER_SESSION_NAME);
	// this.user = this.userService.getUserById(user.getUserId());
	// // 得出所有前台用户的集合
	// customerList = this.customerService
	// .getCustomerList(getQueryCustomerCondition());
	// } catch (Exception ex) {
	// logger.error("ReProblemAction.toReProblem", ex);
	// return ERROR;
	// }
	// return "toReProblem";
	// }
	//
	//
	//	
	//
	// public String toEditProblem() {
	// try {
	// if (problem.getPblId() != 0) {
	// problem = this.problemService
	// .getProblemById(problem.getPblId());
	// }
	// } catch (Exception ex) {
	// logger.error("ReProblemAction.toEditProblem", ex);
	// return ERROR;
	// }
	// return "toeditproblem";
	// }
	//
	//	
	//
	// public String deleteReProblem() {
	// try {
	// if (reproblem.getReId() != 0) {
	// this.reProblemService.delReProblemById(reproblem.getReId());
	// }
	// if (problem.getPblId() != 0) {
	// problem = this.problemService
	// .getProblemById(problem.getPblId());
	//
	// }
	//
	// } catch (Exception ex) {
	// logger.error("ReProblemAction.deleteReProblem", ex);
	// return ERROR;
	// }
	// return "problemview";
	// }
	public IProblem getProblemService() {
		return problemService;
	}

	public void setProblemService(IProblem problemService) {
		this.problemService = problemService;
	}

	public QueryProblemCondition getQueryProblemCondition() {
		if (this.queryProblemCondition == null) {
			this.queryProblemCondition = new QueryProblemCondition();
		}
		return queryProblemCondition;
	}

	public void setQueryProblemCondition(
			QueryProblemCondition queryProblemCondition) {
		this.queryProblemCondition = queryProblemCondition;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}

	public void setProblemList(List<Problem> problemList) {
		this.problemList = problemList;
	}

	public Set<Course> getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(Set<Course> courseSet) {
		this.courseSet = courseSet;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		if (problem == null) {
			problem = new Problem();
		}
		this.problem = problem;
	}

	public Set<Customer> getCustomerSet() {
		return customerSet;
	}

	public void setCustomerSet(Set<Customer> customerSet) {
		this.customerSet = customerSet;
	}

	public ReProblem getReproblem() {
		return reproblem;
	}

	public void setReproblem(ReProblem reproblem) {
		this.reproblem = reproblem;
	}

	public IReProblem getReProblemService() {
		return reProblemService;
	}

	public void setReProblemService(IReProblem reProblemService) {
		this.reProblemService = reProblemService;
	}

	public QueryReProblemCondition getQueryReProblemCondition() {
		if (this.queryReProblemCondition == null) {
			this.queryReProblemCondition = new QueryReProblemCondition();
		}
		return queryReProblemCondition;

	}

	public void setQueryReProblemCondition(
			QueryReProblemCondition queryReProblemCondition) {
		this.queryReProblemCondition = queryReProblemCondition;
	}

	public List<ReProblem> getReProblemList() {
		return reProblemList;
	}

	public void setReProblemList(List<ReProblem> reProblemList) {
		this.reProblemList = reProblemList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public IUser getUserService() {
		return userService;
	}

	public void setUserService(IUser userService) {
		this.userService = userService;
	}

	public QueryUserCondition getQueryUserCondition() {
		if (queryUserCondition == null) {
			queryUserCondition = new QueryUserCondition();
			queryUserCondition.setUserType(-1);
		}
		return queryUserCondition;
	}

	public void setQueryUserCondition(QueryUserCondition queryUserCondition) {
		this.queryUserCondition = queryUserCondition;
	}

	public QueryCustomerCondition getQueryCustomerCondition() {
		if (queryCustomerCondition == null) {
			queryCustomerCondition = new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}

	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}

	public QueryCourseCondition getQueryCourseCondition() {

		if (this.queryCourseCondition == null) {
			this.queryCourseCondition = new QueryCourseCondition();
		}
		return queryCourseCondition;
	}

	public void setQueryCourseCondition(
			QueryCourseCondition queryCourseCondition) {
		this.queryCourseCondition = queryCourseCondition;
	}

	public List<Customer> getScustomerList() {
		return scustomerList;
	}

	public void setScustomerList(List<Customer> scustomerList) {
		this.scustomerList = scustomerList;
	}

	public List<Course> getScourseList() {
		return scourseList;
	}

	public void setScourseList(List<Course> scourseList) {
		this.scourseList = scourseList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getPblId() {
		return pblId;
	}

	public void setPblId(int pblId) {
		this.pblId = pblId;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public QuerySubjectCondition getQuerySubjectCondition() {
		return querySubjectCondition;
	}

	public void setQuerySubjectCondition(
			QuerySubjectCondition querySubjectCondition) {
		this.querySubjectCondition = querySubjectCondition;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public int getPblHot() {
		return pblHot;
	}

	public void setPblHot(int pblHot) {
		this.pblHot = pblHot;
	}

	public String getPblTitle() {
		return pblTitle;
	}

	public void setPblTitle(String pblTitle) {
		this.pblTitle = pblTitle;
	}

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartHH() {
		return startHH;
	}

	public void setStartHH(String startHH) {
		this.startHH = startHH;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndHH() {
		return endHH;
	}

	public void setEndHH(String endHH) {
		this.endHH = endHH;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUserothername() {
		return userothername;
	}

	public void setUserothername(String userothername) {
		this.userothername = userothername;
	}

	public int getHasReply() {
		return hasReply;
	}

	public void setHasReply(int hasReply) {
		this.hasReply = hasReply;
	}

	/**
	 * @author wangzheng 学员统计 附加时间查询条件 当天 一周 当月 三月
	 */
	private void getSelectTime() {

		SimpleDateFormat newfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		if (startHH == null) {
			startHH = "00:00:00";
		}
		if (endHH == null) {
			endHH = "23:59:59";
		}

		if (startTime != null && startTime.length() > 0) {
			// startTime = todayStrartTime; // 完整开始时间
			try {
				queryStartTime = newfm.parse(startTime + " " + startHH);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (endTime != null && endTime.length() > 0) {
			// endTime = todayEndTime; // 完结束始时间
			try {
				queryEndTime = newfm.parse(endTime + " " + endHH);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 完整结束时间
		}
	}

	public Date getQueryStartTime() {
		return queryStartTime;
	}

	public void setQueryStartTime(Date queryStartTime) {
		this.queryStartTime = queryStartTime;
	}

	public Date getQueryEndTime() {
		return queryEndTime;
	}

	public void setQueryEndTime(Date queryEndTime) {
		this.queryEndTime = queryEndTime;
	}

	public int getPblType() {
		return pblType;
	}

	public void setPblType(int pblType) {
		this.pblType = pblType;
	}

	public int getPblSolv() {
		return pblSolv;
	}

	public void setPblSolv(int pblSolv) {
		this.pblSolv = pblSolv;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public int getPblStatus() {
		return pblStatus;
	}

	public void setPblStatus(int pblStatus) {
		this.pblStatus = pblStatus;
	}

	public int getUpdateType() {
		return updateType;
	}

	public void setUpdateType(int updateType) {
		this.updateType = updateType;
	}

	public String getPblContent() {
		return pblContent;
	}

	public void setPblContent(String pblContent) {
		this.pblContent = pblContent;
	}

	public String getRecontent() {
		return recontent;
	}

	public void setRecontent(String recontent) {
		this.recontent = recontent;
	}

	public int getReIsResult() {
		return reIsResult;
	}

	public void setReIsResult(int reIsResult) {
		this.reIsResult = reIsResult;
	}

	public int getRepbid() {
		return repbid;
	}

	public void setRepbid(int repbid) {
		this.repbid = repbid;
	}

	public int getRestatus() {
		return restatus;
	}

	public void setRestatus(int restatus) {
		this.restatus = restatus;
	}

	public int getReupdatetype() {
		return reupdatetype;
	}

	public void setReupdatetype(int reupdatetype) {
		this.reupdatetype = reupdatetype;
	}
}