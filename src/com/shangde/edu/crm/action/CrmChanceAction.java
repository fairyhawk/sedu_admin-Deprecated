package com.shangde.edu.crm.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageQuery;
import com.shangde.edu.crm.condition.QueryChanceCondition;
import com.shangde.edu.crm.condition.QueryStatCondition;
import com.shangde.edu.crm.condition.QueryUsersCondition;
import com.shangde.edu.crm.domain.Chance;
import com.shangde.edu.crm.domain.Record;
import com.shangde.edu.crm.domain.UserDTO;
import com.shangde.edu.crm.domain.Users;
import com.shangde.edu.crm.dto.ChanceDTO;
import com.shangde.edu.crm.dto.ChanceRecordDTO;
import com.shangde.edu.crm.dto.ContractCrmDTO;
import com.shangde.edu.crm.dto.RecordDTO;
import com.shangde.edu.crm.dto.SalesStatDTO;
import com.shangde.edu.crm.service.IChance;
import com.shangde.edu.crm.service.IChanceRead;
import com.shangde.edu.crm.service.IRecord;
import com.shangde.edu.crm.service.IUsers;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.domain.Address;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.service.IAddress;
import com.shangde.edu.cus.service.ICustomer;
import com.shangde.edu.finance.domain.Contract;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.sys.domain.Group;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.IGroup;
import com.shangde.edu.sys.service.IUser;

public class CrmChanceAction extends CommonAction {

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 分页类
	 */
	private PageQuery pageQuery;

	/** log对象 */
	private Logger logger = Logger.getLogger(CrmChanceAction.class);
	private String dir = ServletActionContext.getServletContext().getRealPath( "/excelfile/");
	private Record record;
	private Chance chance;
	private Users user;
	private ChanceRecordDTO chanceRecordDTO;

	private IUsers usersService;
	private IChance chanceService;
	/**
	 * 只读的Chance接口。读数据库从库，禁止执行增删改操作
	 */
	private IChanceRead chanceReadService;
	
	private IRecord recordService;
	private IGroup groupService;
	private IUser userService;

	private ICustomer customerService;
	private IContract contractService;

	private IAddress addressService;

	private QueryCustomerCondition queryCustomerCondition;
	private QueryStatCondition queryStatCondition;

	private List<Customer> customerList;
	private List<Contract> contractList = new ArrayList<Contract>();

	private List<ContractCrmDTO> contractDTOList = new ArrayList<ContractCrmDTO>();

	private List<Address> addressList = new ArrayList<Address>();

	private List<SalesStatDTO> salesStatList = new ArrayList<SalesStatDTO>();

	private QueryUsersCondition queryUsersCondition;

	/**
	 * 销售机会ID拼接的字符串
	 */
	private String ids;

	/**
	 * 销售机会查询条件
	 */
	private QueryChanceCondition queryChanceCondition;

	private int crmChanceId;
	private int userId;
	private int roleId;
	private String mobile;
	private int groupIds;
	private int scanType;
	private String chanceIdBox;
	private InputStream excelFile;
	private int contractNum;
	private int contractNumPay;
	private int usersId;
	private String excelName;

	private List<Subject> subjectList = new ArrayList<Subject>();
	private List<User> userList = new ArrayList<User>();
	private List<Group> groupList = new ArrayList<Group>();
	private List<RecordDTO> recordDTOList = new ArrayList<RecordDTO>();
	private List<RecordDTO> recordFirstList = new ArrayList<RecordDTO>();
	private List<RecordDTO> recordList = new ArrayList<RecordDTO>();
	private List<RecordDTO> recordSecList = new ArrayList<RecordDTO>();
	private List<Users> usersList = new ArrayList<Users>();
	public String toChanceAdd() {
		try {
			// 获取销售组别
			groupList = groupService.getChildGroupById("40");
			// 获取销售人员列表
			userList = chanceService.getSoldUserList(this
					.getQueryChanceCondition());
			// 获取专业列表
			subjectList = chanceService.getAllSubject();
			// 获取登录用户对象，id和权限id
			User users = this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			usersId = users.getUserId();
			roleId = users.getRoleList().get(0).getRoleId();
			return "toChanceAdd";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行toChanceAdd错误！", e);
			return ERROR;
		}
	}

	/**
	 * 在用户库表添加数据。（添加之前必须查询，有没有存在的手机号，如果存在，不加入用户库表）
	 * 
	 * @author 王超
	 * @return
	 */
	public String crmAdduser() {
		try {
			this.getQueryUsersCondition().setEmail(user.getEmail());
			this.getQueryUsersCondition().setMobile(user.getMobile());
			// 向用户库添加乐语用户
			user.setRegTime(new Date());
			user.setUserType(chance.getOrigin());//可能两种来源 一种乐语 一种callin
			user.setSubjectId(chance.getSubjectId());
			usersService.addUser(user);
			// 向机会表添加乐语机会，指派人为登录用户
			chance.setUserId(userId);
			chance.setCreateUserId(userId);
			chance.setBackUserId(userId);
			chance.setChanceStime(new Date());
			chance.setChanceUtime(new Date());
			chance.setCrmUserId(user.getId());
			chance.setChanceNumber(0);
			chance.setEndCommStatus(0);
			chance.setConsultStatus(1);
			chanceService.addChance(chance);
			// 添加指派历史信息
			Record rec = new Record();
			rec.setCommStatus(0);
			rec.setUserId(chance.getUserId());
			rec.setCreateTime(new Date());
			rec.setCrmChanceId(chance.getId());
			recordService.addRecord(rec);

			return "success";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行crmAdduser错误！", e);
			return ERROR;
		}
	}

	/**
	 * 批量指派
	 * 
	 * @author 王超
	 * @return
	 */
	public String crmBulkAllocated() {

		try {
			// 获取批量指派机会id
			String[] ss = chanceIdBox.split(",");
			// 查询已领取的未成交机会数量
			int count = chanceService.getDrawChanceCount(userId);
			// 批量改派放弃机会
			for (int i = 0; i < ss.length; i++) {
				chance = chanceService.getChanceById(Integer.parseInt(ss[i]));
				if(chance.getUserId()==userId)continue;
				if (chance.getDrawStatus() == 1) {
					if (count < 60) {// 已领取数量小于20
						count++;
					} else {// 已领取数量大于20
						count++;
						continue;
					}
				}
				chance.setUserId(userId);
				chance.setBackUserId(userId);
				chance.setChanceUtime(new Date());
				chanceService.updateChance(chance);// 领取该放弃机会
				// 添加改派记录
				record = new Record();
				record.setCommStatus(0);
				record.setUserId(userId);
				record.setCreateTime(new Date());
				record.setCrmChanceId(chance.getId());
				recordService.addRecord(record);
			}
			if (count > 60) {
				this.setResult(new Result(false, null, null, null));
			} else {
				this.setResult(new Result(true, null, null, null));
			}
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行crmBulkAllocated错误！", e);
			return ERROR;
		}
	}

	public String toAddRecord() {
		try {
			scanType = this.getScanType();// 获取查看详情状态，0为添加记录，1为查看详情
			groupList = groupService.getChildGroupById("40");// 获取销售组别列表
			User users = this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			roleId = users.getRoleList().get(0).getRoleId();// 获取登录用户权限id
			subjectList = chanceService.getAllSubject();// 获取全部专业列表
			chanceRecordDTO = chanceService.getChanceRecordDTO(crmChanceId);// 获取页面信息对象
			if (chanceRecordDTO.getUserId() != 0) {// 已有指派人
				groupIds = userService.getUserById(chanceRecordDTO.getUserId())
						.getGroupId();// 获取组别id
				if (groupIds > 40 && groupIds <= 50) {// 若是销售人员则获取所在组别的销售人员列表
					userList = chanceService.getGroupUserInfo(groupIds);
				} else {// 获取所有销售人员列表
					userList = chanceService.getSoldUserList(this
							.getQueryChanceCondition());
				}
			}
			user = usersService.getUserById(userId);// 获取用户对象
			recordDTOList = recordService
					.getRecordDTOListByChanceId(crmChanceId);// 获取指派人员历史记录
			if (recordDTOList != null && recordDTOList.size() != 0) {// 不显示当前指派人员
				recordDTOList.remove(0);
			}
			recordList = recordService.getRecordListByChanceId(crmChanceId);// 获取沟通记录
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 0; i < recordList.size(); i++) {
				if (sdf.format(chanceRecordDTO.getChanceStime()).equals(
						sdf.format(recordList.get(i).getCreateTime()))) {// 沟通记录是当天创建则为首咨
					recordFirstList.add(recordList.get(i));
				} else {// 沟通记录非当天创建则为回访
					recordSecList.add(recordList.get(i));
				}
			}
			return "tosalesAddRecord";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行toAddRecord错误！", e);
			return ERROR;
		}
	}

	/**
	 * 添加记录
	 * 
	 * @author 王超
	 * @return
	 */
	public String crmAddRecord() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			int followStatus = chance.getFollowStatus();
			chance = chanceService.getChanceById(crmChanceId);
			chance.setFollowStatus(followStatus);
			record.setCreateTime(new Date());
			chance.setEndCommStatus(record.getCommStatus());
			chance.setEndCommTime(record.getCreateTime());
			if (chance.getConsultStatus() != 4) {// 非库存回访状态下(库存回访为最终状态)
				if (chance.getEndCommStatus() == 5
						|| chance.getEndCommStatus() == 8
						|| chance.getEndCommStatus() == 3) {// 沟通状态为再联系，正常接通或已购买情况下
					chance.setChanceNumber(chance.getChanceNumber() + 1);
					if (sdf.format(chance.getChanceStime()).equals(
							sdf.format(chance.getEndCommTime()))) {// 记录创建时间和机会创建时间为同一天则设置为首咨
						chance.setConsultStatus(2);
					} else {// 记录创建时间和机会创建时间不为同一天则设置为回访
						chance.setConsultStatus(3);
					}
				}
			}
			record.setUserId(userId);
			record.setCrmChanceId(chance.getId());
			if (chance.getFollowStatus() == 4 || chance.getFollowStatus() == 1) {// 放弃时将领取状态改为未领取状态
				chance.setDrawStatus(0);
			}
			if(chance.getFollowStatus() == 1)chance.setAutoGiveup(1);
			chanceService.updateChance(chance);// 更新机会
			recordService.addRecord(record);// 添加记录
			userId = chance.getCrmUserId();
			return toAddRecord();// 页面刷新,重新跳转
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行crmAddRecord错误！", e);
			return ERROR;
		}
	}

	/**
	 * 重新指派
	 * 
	 * @author 王超
	 * @return
	 */
	public String crmUpdateAllocated() {
		try {
			// 重新指派坐席
			Chance chan = chanceService.getChanceById(crmChanceId);
			if (chan.getUserId() != userId) {
				chan.setUserId(userId);
				chan.setBackUserId(userId);
				chan.setChanceUtime(new Date());
				chanceService.updateChance(chan);
				// 添加坐席指派记录
				Record rec = new Record();
				rec.setCommStatus(0);
				rec.setUserId(userId);
				rec.setCreateTime(new Date());
				rec.setCrmChanceId(chan.getId());
				recordService.addRecord(rec);
			}
			userId = chan.getCrmUserId();
			return toAddRecord();// 页面刷新,重新跳转
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行crmUpdateAllocated错误！", e);
			return ERROR;
		}
	}

	public String getGroupUserInfo() {
		try {
			// 获取对应用户组销售人员列表
			userList = chanceService.getGroupUserInfo(groupIds);
			// 编写html字符串
			StringBuffer s = new StringBuffer();
			s.append("<select name='userId' id='userId'><br>");
			for (int i = 0; i < userList.size(); i++) {
				s.append("<option value=" + userList.get(i).getUserId() + ">"
						+ userList.get(i).getUserName() + "</option><br>");
			}
			s.append("</select>");
			this.setResult(new Result(true, s.toString(), null, null));
			return "json";
		} catch (RuntimeException e) {
			logger.error("执行getGroupUserInfo错误！", e);
			return ERROR;
		}
	}

	public String testMobile() {
		try {
			List<UserDTO> userDTOList = chanceService
					.getChanceCountByMobile(mobile);
			if (userDTOList == null || userDTOList.size() == 0) {
				this.setResult(new Result(true, "该手机可以新建机会", null, null));
			} else {
				String s = "<table border='0' width='70%' style='margin-top:2em;' cellpadding='0' cellspacing='1' class='lists_info'>";
				s += "<tr><td class='crm_tableFL' colspan='3'>该手机号下有"
						+ userDTOList.size() + "条记录</td></tr>";
				int a = 0, b = 0;
				for (int i = 0; i < userDTOList.size(); i++) {
					if (a == 0 && userDTOList.get(i).getFollowStatus() == 1) {
						b++;
						s += "<tr><td class='crm_tableFL' style='font-size: 12pt;'  colspan='3'>&nbsp"
								+ b + ".销售机会库</td></tr>";
						a++;
					}
					if (b == 0 && userDTOList.get(i).getFollowStatus() != 1) {
						s += "<tr><td class='crm_tableFL' style='font-size: 12pt;'  colspan='3'>&nbsp1.销售机会管理</td></tr>";
						b++;
					}
					s += "<tr><td class='crm_tableFL' style='font-size: 10pt;'>&nbsp&nbsp";
					if (userDTOList.get(i).getEmail() != null) {
						s += userDTOList.get(i).getEmail();
					} else {
						s += "无邮箱";
					}
					s += "</td><td class='crm_tableFL' style='font-size: 10pt;'>";
					if (userDTOList.get(i).getPayCount() > 0) {
						if (userDTOList.get(i).getUserId() == 0) {

							s += "未指派(已成单)</td><td width='20px'></td></tr>";
						} else {
							s += userDTOList.get(i).getUserName()
									+ "(已成单)</td><td  width='40px'></td></tr>";
						}
					} else {
						if (userDTOList.get(i).getUserId() == 0) {

							s += "未指派</td><td  width='40px'><a href='#' style='color:#1E90FF;font-size: 10pt;' onclick='getChance("
									+ userDTOList.get(i).getChanceId()
									+ ")'>认领</a></td></tr>";
						} else if (userDTOList.get(i).getFollowStatus() == 1) {
							s += userDTOList.get(i).getUserName()
									+ "</td><td  width='40px'><a href='#' style='color:#1E90FF;font-size: 10pt;'  onclick='getChance("
									+ userDTOList.get(i).getChanceId()
									+ ")'>认领</a></td></tr>";
						} else {
							s += userDTOList.get(i).getUserName()
									+ "</td><td  width='40px'></td></tr>";
						}
					}
				}
				s += "</table>";
				this.setResult(new Result(false, s, null, null));
			}
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行testMobile错误！", e);
			return ERROR;
		}
	}

	/**
	 * 获取全部销售机会列表
	 * 
	 * @author 代长福
	 * @return
	 */
	public String getAllChance() {
		try {
			if(this.getQueryChanceCondition().getStartTime()==null||this.getQueryChanceCondition().getEndTime()==null||this.getQueryChanceCondition().getStartTime()==""||this.getQueryChanceCondition().getEndTime()==""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				this.getQueryChanceCondition().setStartTime(sdf.format(new Date()));
				this.getQueryChanceCondition().setEndTime(sdf.format(new Date()));
				
				
				}
			groupList = groupService.getChildGroupById("40");
			userList = chanceService.getSoldUserList(this
					.getQueryChanceCondition());
			pageQuery.setPageSize(20);
			setPage(chanceService.getAllChance(pageQuery));
			if (this.getPage() != null) {
				this.getPage().setPageSize(20);
			}
			subjectList = chanceService.getAllSubject();
			this.setPageUrlParms();
			return "manageChanceList";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行getAllChance错误！", e);
			return ERROR;
		}
	}

	/**
	 * 搜索销售记录
	 * 
	 * @author 代长福
	 * @return
	 */
	public String searchChance() {
		try {
			groupList = groupService.getChildGroupById("40");
			userList = chanceService.getSoldUserList(this
					.getQueryChanceCondition());
			String userName = this.getQueryChanceCondition().getUserName();
			if (userName != null) {
				queryChanceCondition.setUserName(URLDecoder.decode(userName,
						"UTF-8"));
			}
			subjectList = chanceService.getAllSubject();
			this.queryChanceCondition.setPageSize(20);
			setPage(chanceService.searchChance(queryChanceCondition));
			if (this.getPage() != null) {
				this.getPage().setPageSize(20);
			}
			this.setPageUrlParms();
			return "manageChanceList";
		} catch (Exception e) {
			logger.error("执行searchChance错误！", e);
			return ERROR;
		}

	}

	/**
	 * 获取全部用户卡列表
	 * 
	 * @author 范昕 2011-11-05
	 * @return
	 */
	public String getAllUserCardList() {
		try {
			getQueryUsersCondition().setPageSize(20);
			setPage(usersService
					.getUsersBackPaperByCondition(getQueryUsersCondition()));
			// 需要再设置一次分页
			getPage().setPageSize(20);
			setPageUrlParms();

			return "toUserCardList";

		} catch (Exception e) {
			logger.error("getAllUserCardList()方法出错了！", e);
			return "error";
		}
	}

	/**
	 * 获取全部用户卡列表
	 * 
	 * @author 范昕 2011-11-05
	 * @return
	 */
	public String seachUserCardList() {
		try {
			getQueryUsersCondition().setPageSize(20);
			setPage(usersService
					.searchUserListByParamCondition(getQueryUsersCondition()));
			// 需要再设置一次分页
			getPage().setPageSize(20);
			setPageUrlParms();
			return "seachUserCardList";
		} catch (Exception e) {
			logger.error("seachUserCardList()方法出错了！", e);
			return "error";
		}
	}

	/**
	 * 根据电话号码 获取户卡列
	 * 
	 * @author 范昕 2011-11-06
	 * @return
	 */
	public String getUserCardById() {
		try {
			user = usersService.getUserById(userId);

			if (user != null) {
				String mobile = user.getMobile();

				usersList = usersService.getUserByMobile(mobile);

				getQueryCustomerCondition().setMobile(mobile);
				// 根据 电话 查找 用户
				customerList = customerService
						.getCustomerListByMobile(getQueryCustomerCondition());
				subjectList = chanceService.getAllSubject();

				if (customerList != null && customerList.size() > 0) {
					for (int i = 0; i < customerList.size(); i++) {
						Customer customeTemp = customerList.get(i);
						int cusId = customeTemp.getCusId();

						// List<ContractDTO> contractListTemp =
						// contractService.getContractByCusId(cusId);
						// 根据用户 查找订单
						List<ContractCrmDTO> contractListTemp = chanceService
								.getContractByCusId(cusId);

						if (contractListTemp != null
								&& contractListTemp.size() > 0) {
							contractDTOList.addAll(contractListTemp);
						}
					}
				}

				// 货到付款信息
				int customerId = user.getCusId();
				addressList = addressService.getAddressByCusId(customerId);

				contractNum = chanceService.getContractNum(customerId);
				contractNumPay = chanceService.getContractNumPay(customerId);

				chance = chanceService.getChanceByCrmUserId(userId);

				if (chance != null) {
					int chanceId = chance.getId();
					recordList = recordService
							.getRecordListByChanceId(chanceId);
				}

			}

			return "getUserCardById";

		} catch (Exception e) {
			logger.error("getUserCardById()方法出错了!", e);
			return "error";
		}
	}

	/**
	 * 销售机会库查询
	 * 
	 * @return
	 */
	public String getChanceList() {
		try {
			if(this.getQueryChanceCondition().getStartTime()==null||this.getQueryChanceCondition().getEndTime()==null||this.getQueryChanceCondition().getStartTime()==""||this.getQueryChanceCondition().getEndTime()==""){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				this.getQueryChanceCondition().setStartTime(sdf.format(new Date()));
				this.getQueryChanceCondition().setEndTime(sdf.format(new Date()));
				
				
				}
			this.getPageQuery().setPageSize(20);
			setPage(chanceService.getChanceList(pageQuery));
			if (this.getPage() != null) {
				this.getPage().setPageSize(20);
			}
			subjectList = chanceService.getAllSubject();
			this.setPageUrlParms();
			return "chanceList";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			logger.error("执行getChanceList错误！", e);
			return ERROR;
		}
	}

	/**
	 * 销售机会库搜索销售机会记录
	 * 
	 * @author 代长福
	 * @return
	 */
	public String searchChanceList() {
		try {
			String userName = this.getQueryChanceCondition().getUserName();
			if (userName != null) {
				queryChanceCondition.setUserName(URLDecoder.decode(userName,
						"UTF-8"));
			}
			queryChanceCondition.setFollowStatus(1); // 用户状态已放弃（默认）
			subjectList = chanceService.getAllSubject();
			this.queryChanceCondition.setPageSize(20);
			setPage(chanceService.searchChance(queryChanceCondition));
			if (this.getPage() != null) {
				this.getPage().setPageSize(20);
			}
			this.setPageUrlParms();
			return "chanceList";
		} catch (Exception e) {
			logger.error("执行searchChanceList错误！", e);
			return ERROR;
		}
	}

	/**
	 * 批量领取销售机会
	 * 
	 * @author 代长福
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getBatchChance() {
		try {
			int userId = this.getLoginedUser().getUserId();
			// 查询已领取的销售机会数量（除去已购买的销售机会）
			int count = chanceService.getDrawChanceCount(userId);
			// 已领取的销售机会小于20
			if ((count + (ids.split(",").length)) <= 60) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", (userId));
				map.put("ids", ids);
				int i = chanceService.updategetBatchChance(map);
				if (i > 0) {
					setResult(new Result(true, "领取成功", null, null));
				} else {
					setResult(new Result(false, "领取失败", null, null));
				}
			} else {
				setResult(new Result(false, "领取失败，最多只能领取60个未购买的销售机会，当前已有"
						+ count + "个", null, null));
			}
			return "json";
		} catch (Exception e) {
			logger.error("执行getBatchChance错误！", e);
			return ERROR;
		}

	}

	public String getOneChance() {
		try {
			int userId = this.getLoginedUser().getUserId();
			chance = chanceService.getChanceById(Integer.parseInt(ids));
			if (chance.getUserId() != 0 && chance.getFollowStatus() != 1) {
				setResult(new Result(true, "该销售机会已被领取", null, null));
				return "json";
			}
			if (chance.getFollowStatus() == 1) {
				// 查询已领取的销售机会数量（除去已购买的销售机会）
				int count = chanceService.getDrawChanceCount(userId);
				// 已领取的销售机会小于20
				if (count < 60) {
					chance.setUserId(userId);
					chance.setBackUserId(userId);
					chance.setFollowStatus(2);
					chance.setDrawStatus(1);
					chance.setConsultStatus(4);
					chance.setChanceUtime(new Date());
					chance.setAutoGiveup(0);
					chanceService.updateChance(chance);

					Record rec = new Record();
					rec.setCommStatus(0);
					rec.setUserId(userId);
					rec.setCreateTime(new Date());
					rec.setCrmChanceId(chance.getId());
					recordService.addRecord(rec);
					count++;
					setResult(new Result(true, "销售机会领取成功，您目前已从销售机会库领取了" + count
							+ "个机会", null, null));
				} else {
					setResult(new Result(false, "您从销售机会库领取的机会已到60,请放弃部分机会再领取",
							null, null));
				}
			} else {
				if (chance.getUserId() == 0) {
					chance.setOrigin(2);
				}
				chance.setUserId(userId);
				chance.setBackUserId(userId);
				chance.setChanceUtime(new Date());
				chanceService.updateChance(chance);

				Record rec = new Record();
				rec.setCommStatus(0);
				rec.setUserId(userId);
				rec.setCreateTime(new Date());
				rec.setCrmChanceId(chance.getId());
				recordService.addRecord(rec);
				setResult(new Result(true, "销售机会领取成功", null, null));
			}
			return "json";
		} catch (Exception e) {
			logger.error("执行getBatchChance错误！", e);
			return ERROR;
		}

	}

	/**
	 * 根据userId获取户基本信息
	 * 
	 * @author 范昕 2011-11-06
	 * @return
	 */
	public String getUserCard() {
		try {
			user = usersService.getUserById(userId);
			// user = usersService.getUserByMobile(mobile);

			return "getUserCard";

		} catch (Exception e) {
			logger.error("getUserCard()方法出错了!", e);
			return "error";
		}
	}

	/**
	 * 根据userId 更新户基本信息
	 * 
	 * @author 范昕 2011-11-06
	 * @return
	 */
	public String updateUserCard() {
		try {
			Users upUser = usersService.getUserById(userId);
			if (upUser != null) {
				upUser.setRealName(queryUsersCondition.getRealName());
				upUser.setProfession(queryUsersCondition.getProfession());
				upUser.setSex(queryUsersCondition.getSex());
				upUser.setAddress(queryUsersCondition.getAddress());
				upUser.setBirthday(queryUsersCondition.getBirthday());
				upUser.setRemarks(queryUsersCondition.getRemarks());

				usersService.updateUser(upUser);
			}
			return "updateUserCard";

		} catch (Exception e) {
			logger.error("updateUserCard()方法出错了!", e);
			return "error";
		}
	}

	/**
	 * 某个销售人员的机会列表
	 * 
	 * @author 代长福
	 * @return
	 */
	public String getSalesChanceList() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime(
					(sdf.format(new Date()) + " 00:00:00"));
			this.getQueryStatCondition().setEndTime(
					sdf.format(new Date()) + " 23:59:59");
			User users = this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			this.getQueryStatCondition().setUserId(users.getUserId());
			salesStatList = chanceService.getSalesStatList(queryStatCondition);
			this.getQueryChanceCondition().setPageSize(20);
			this.getQueryChanceCondition().setUserId(
					this.getLoginedUser().getUserId());
			setPage(chanceService.getSalesChanceList(queryChanceCondition));
			if (this.getPage() != null) {
				this.getPage().setPageSize(20);
			}
			subjectList = chanceService.getAllSubject();
			this.setPageUrlParms();
			return "salesChanceList";
		} catch (Exception e) {
			logger.error("执行getSalesChanceList错误！", e);
			return "eror";
		}
	}

	/**
	 * 销售人员搜索销售机会
	 * 
	 * @author 代长福
	 * @return
	 */
	public String searchSalesChance() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.getQueryStatCondition().setStartTime(
					(sdf.format(new Date()) + " 00:00:00"));
			this.getQueryStatCondition().setEndTime(
					sdf.format(new Date()) + " 23:59:59");
			User users = this.getSession(CommonAction.SYS_USER_SESSION_NAME);
			this.getQueryStatCondition().setUserId(users.getUserId());
			salesStatList = chanceReadService.getSalesStatList(queryStatCondition);
			this.getQueryChanceCondition().setPageSize(20);
			this.getQueryChanceCondition().setUserId(
					this.getLoginedUser().getUserId());
			setPage(chanceService.searchSalesChance(queryChanceCondition));
			if (this.getPage() != null) {
				this.getPage().setPageSize(20);
			}
			subjectList = chanceService.getAllSubject();
			this.setPageUrlParms();
			return "salesChanceList";
		} catch (Exception e) {
			logger.error("执行searchSalesChance错误！", e);
			return "eror";
		}
	}

	/**
	 * 导出excel表格
	 * 
	 * @author 王超
	 * @return
	 * @throws InterruptedException
	 */
	public String exportExcelFileManage() {
		try {
			// 销售管理导出表格
			if (pageQuery != null) {
				pageQuery.setPageSize(5000);
				setPage(chanceService.getAllChanceCheckOut(pageQuery));
			} else {
				String userName = this.getQueryChanceCondition().getUserName();
				if (userName != null) {
					queryChanceCondition.setPageSize(5000);
					queryChanceCondition.setUserName(URLDecoder.decode(
							userName, "UTF-8"));
				}
				subjectList = chanceService.getAllSubject();
				this.queryChanceCondition.setPageSize(5000);
				setPage(chanceService.searchChanceCheckOut(queryChanceCondition));
			}
			this.getPage().setPageSize(5000);
			createExcelFile5();
			this.setResult(new Result(true,null, null, null));
			return "json";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("执行exportExcelFileManage错误！", e);
			return "error";
		}
	}

	/**
	 * 导出excel表格
	 * 
	 * @author 王超
	 * @return
	 * @throws InterruptedException
	 */
	public String exportExcelFile() {
		try {
			// 销售人员导出表格
			//this.getQueryChanceCondition().setPageSize(20);
			this.getQueryChanceCondition().setUserId(this.getLoginedUser().getUserId());
			this.queryChanceCondition.setPageSize(200);
			setPage(chanceService.searchSalesChance(queryChanceCondition));
			List<ChanceDTO> chanceDTOList = new ArrayList<ChanceDTO>();
			chanceDTOList = this.getPage().getPageResult();
			createExcelFile(chanceDTOList, 1);
			if (ServletActionContext.getRequest().getHeader("User-Agent")
					.toUpperCase().indexOf("MSIE") > 0) {
				setExcelName(URLEncoder.encode("销售个人" + sdf.format(new Date())
						+ ".xls", "UTF-8"));// IE浏览器
			} else {
				setExcelName(new String(
						("销售个人" + sdf.format(new Date()) + ".xls").getBytes(),
						"iso-8859-1"));
			}
			return "exportExcelCrm";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("执行exportExcelFile错误！", e);
			return ERROR;
		}
	}

	/**
	 * 导出excel表格
	 * 
	 * @author 王超
	 * @return
	 * @throws InterruptedException
	 */
	public String exportExcelFileChance() {
		try {
			if (pageQuery != null) {
				this.getPageQuery().setCurrentPage(1);
				User users = this.getSession(CommonAction.SYS_USER_SESSION_NAME);
				usersId = users.getUserId();
				roleId = users.getRoleList().get(0).getRoleId();
				if(roleId==73){
					this.getPageQuery().setPageSize(200);
				}else{
				this.getPageQuery().setPageSize(5000);
				}
				setPage(chanceService.getChanceList(pageQuery));
			} else {
				this.queryChanceCondition.setCurrentPage(1);
				String userName = this.getQueryChanceCondition().getUserName();
				if (userName != null) {
					queryChanceCondition.setUserName(URLDecoder.decode(
							userName, "UTF-8"));
				}
				queryChanceCondition.setFollowStatus(1); // 用户状态已放弃（默认）
				User users = this.getSession(CommonAction.SYS_USER_SESSION_NAME);
				usersId = users.getUserId();
				roleId = users.getRoleList().get(0).getRoleId();
				if(roleId==73){
					this.queryChanceCondition.setPageSize(200);
				}else{
				this.queryChanceCondition.setPageSize(5000);
				}
				setPage(chanceService.searchChanceCheckOut(queryChanceCondition));
			}
			List<ChanceDTO> chanceDTOList = new ArrayList<ChanceDTO>();
			chanceDTOList = this.getPage().getPageResult();
			createExcelFile(chanceDTOList, 2);
			if (ServletActionContext.getRequest().getHeader("User-Agent")
					.toUpperCase().indexOf("MSIE") > 0) {
				setExcelName(URLEncoder.encode("销售机会库" + sdf.format(new Date())
						+ ".xls", "UTF-8"));// IE浏览器
			} else {
				setExcelName(new String(
						("销售机会库" + sdf.format(new Date()) + ".xls").getBytes(),
						"iso-8859-1"));
			}
			return "exportExcelCrm";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("执行exportExcelFileChance错误！", e);
			return ERROR;
		}
	}

	/**
	 * 〃excel
	 * 
	 * @return String
	 * @thorows Exception
	 */
	public void createExcelFile(List<ChanceDTO> chanceDTOList, int type) {
		try {
			// 定义时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			// 表头标题
			String[] headNames = { "手机号码", "用户email", "所属项目", "域名来源",
					"销售机会状态", "机会来源", "咨询状态", "用户状态","流量来源", "创建时间", "最后指派时间",
					"最后沟通时间", "最后沟通状态", "支付/订单数","备注" };
			List headName = new ArrayList();
			headName.add("手机");
			headName.add("用户email");
			headName.add("所属项目");
			headName.add("域名来源");
			headName.add("销售机会状态");
			headName.add("机会来源");
			headName.add("咨询状态");
			if (type == 0) {
				headName.add("销售坐席");
			}
			if (type == 2) {
				headName.add("销售原坐席");
			}
			headName.add("乐语创建坐席");
			headName.add("用户状态");
			headName.add("流量来源");
			headName.add("创建时间");
			headName.add("最后指派时间");
			headName.add("最后沟通时间");
			headName.add("最后沟通状态");
			headName.add("订单数");
			headName.add("网银支付数");
			headName.add("网银支付金额");
			headName.add("货到付款下单数");
			headName.add("货到付款下单金额");
			headName.add("银行汇款支付数");
			headName.add("银行汇款支付金额");
			headName.add("备注");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("sheet1");
			// 创建列数
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell((short) 0);
			for (int i = 0; i < headName.size(); i++) {
				cell = row.createCell((short) i);
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(headName.get(i).toString());
			}
			for (int i = 0; i < chanceDTOList.size(); i++) {
				row = sheet.createRow(i + 1);
				int j = 0;
//				// 机会id
//				cell = row.createCell((short) j);
//				j++;
//				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
//				cell.setCellValue(chanceDTOList.get(i).getId());
				// 手机号码
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getMobile());
				// 用户email
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getEmail());
				// 所属项目
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getSubjectName());
				// 域名来源
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getWebName());
				// 销售机会状态
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (chanceDTOList.get(i).getEmail() == "") {
					cell.setCellValue("未注册");
				} else if (chanceDTOList.get(i).getFcount2() == 0) {
					cell.setCellValue("已注册");
				} else if (chanceDTOList.get(i).getFcount1() == 0) {
					cell.setCellValue("已下单");
				} else {
					cell.setCellValue("已购买");
				}
				// 机会来源
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if(chanceDTOList.get(i).getOrigin()==1){
					cell.setCellValue("自然注册");
				}
				if(chanceDTOList.get(i).getOrigin()==2){
					cell.setCellValue("乐语在线");
				}
				if(chanceDTOList.get(i).getOrigin()==4){
					cell.setCellValue("自然留言");
				}
				// 咨询状态
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (chanceDTOList.get(i).getConsultStatus() == 1) {
					cell.setCellValue("未联系");
				}
				if (chanceDTOList.get(i).getConsultStatus() == 2) {
					cell.setCellValue("首次");
				}
				if (chanceDTOList.get(i).getConsultStatus() == 3) {
					cell.setCellValue("回访");
				}
				// 销售坐席
				if (type != 1) {
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getUserName());
				}
				// 乐语创建坐席
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getFirstUserName());
				// 用户状态
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (chanceDTOList.get(i).getFollowStatus() == 1) {
					if(chanceDTOList.get(i).getAutoGiveup()==1)
					cell.setCellValue("放弃");
					else cell.setCellValue("自然回库");
				}
				if (chanceDTOList.get(i).getFollowStatus() == 2) {
					cell.setCellValue("跟踪");
				}
				if (chanceDTOList.get(i).getFollowStatus() == 3) {
					cell.setCellValue("热点");
				}
				if (chanceDTOList.get(i).getFollowStatus() == 4) {
					cell.setCellValue("成交");
				}
				//流量来源
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getCusWebFrom());
				// 创建时间
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(sdf.format(chanceDTOList.get(i)
						.getChanceSTime()));
				// 最后指派时间
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (chanceDTOList.get(i).getChanceUTime() != null) {
					cell.setCellValue(sdf.format(chanceDTOList.get(i)
							.getChanceUTime()));
				} else {
					cell.setCellValue("--");
				}
				// 最后沟通时间
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (chanceDTOList.get(i).getEndCommTime() != null) {
					cell.setCellValue(sdf.format(chanceDTOList.get(i)
							.getEndCommTime()));
				} else {
					cell.setCellValue("--");
				}
				// 最后沟通状态
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				if (chanceDTOList.get(i).getEndCommStatus() == 1) {
					cell.setCellValue("空号");
				}
				if (chanceDTOList.get(i).getEndCommStatus() == 2) {
					cell.setCellValue("通话中");
				}
				if (chanceDTOList.get(i).getEndCommStatus() == 3) {
					cell.setCellValue("再联系");
				}
				if (chanceDTOList.get(i).getEndCommStatus() == 4) {
					cell.setCellValue("测试");
				}
				if (chanceDTOList.get(i).getEndCommStatus() == 5) {
					cell.setCellValue("正常接通");
				}
				if (chanceDTOList.get(i).getEndCommStatus() == 6) {
					cell.setCellValue("未接通");
				}
				if (chanceDTOList.get(i).getEndCommStatus() == 7) {
					cell.setCellValue("假号码");
				}
				if (chanceDTOList.get(i).getEndCommStatus() == 8) {
					cell.setCellValue("购买");
				}
				// 支付/订单数
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getFcount1() + "/"
						+ chanceDTOList.get(i).getFcount2());
				// 网银支付数
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getIntBankCount());
				// 网银支付金额
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getIntBankPrice());
				//货到付款下单数
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getSendCount());
				// 货到付款下单金额
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getSendPrice());
				// 银行汇款支付数
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getBankCount());
				// 银行汇款支付金额
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				cell.setCellValue(chanceDTOList.get(i).getBankPrice());
				// 备注
				cell = row.createCell((short) j);
				j++;
				cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
				String remark="";
				if(chanceDTOList.get(i).getRecord()!=null){
					switch(chanceDTOList.get(i).getRecord().getCommStatus()){
					case 1:remark+="沟通状态：空号"; break;
					case 2:remark+="沟通状态：通话中"; break;
					case 3:remark+="沟通状态：再联系"; break;
					case 4:remark+="沟通状态：测试"; break;
					case 5:remark+="沟通状态：正常接通"; break;
					case 6:remark+="沟通状态：未接通"; break;
					case 7:remark+="沟通状态：假号码"; break;
					case 8:remark+="沟通状态：已购买"; break;
					}
					if(chanceDTOList.get(i).getRecord().getReasons()!=null&&!chanceDTOList.get(i).getRecord().getReasons().equals(""))
					remark+=";   未成单原因："+chanceDTOList.get(i).getRecord().getReasons();
					if(chanceDTOList.get(i).getRecord().getConcerns()!=null&&!chanceDTOList.get(i).getRecord().getConcerns().trim().equals(""))
					remark+=";   关注点："+chanceDTOList.get(i).getRecord().getConcerns();
					if(chanceDTOList.get(i).getRecord().getWhyLearm()!=null&&!chanceDTOList.get(i).getRecord().getWhyLearm().trim().equals(""))
					remark+=";   学习原因"+chanceDTOList.get(i).getRecord().getWhyLearm();
					if(chanceDTOList.get(i).getRecord().getRemarks()!=null&&!chanceDTOList.get(i).getRecord().getRemarks().trim().equals(""))
					remark+=";   备注"+chanceDTOList.get(i).getRecord().getRemarks();
				}
				cell.setCellValue(remark);
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			workbook.write(baos);
			byte[] ba = baos.toByteArray();
			baos.flush();
			baos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(ba);
			this.setExcelFile(bais);
		} catch (IOException e) {
			logger.error("执行createExcelFile错误！", e);
			e.printStackTrace();
		}
	}

	/**
	  * 创建excel文件  
	  *   
	  * 
	  */ 
	public void createExcelFile5() {
		try {
			// 定义时间格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			// 表头标题
			String[] headNames = {  "手机号码", "用户email", "所属项目", "域名来源",
					"销售机会状态", "机会来源", "咨询状态", "用户状态", "创建时间", "最后指派时间",
					"最后沟通时间", "最后沟通状态", "支付/订单数" };
			List headName = new ArrayList();
			headName.add("手机");
			headName.add("用户email");
			headName.add("所属项目");
			headName.add("域名来源");
			headName.add("销售机会状态");
			headName.add("机会来源");
			headName.add("咨询状态");
			headName.add("销售坐席");
			headName.add("乐语创建坐席");
			headName.add("用户状态");
			headName.add("流量来源");
			headName.add("创建时间");
			headName.add("最后指派时间");
			headName.add("最后沟通时间");
			headName.add("最后沟通状态");
			headName.add("订单数");
			headName.add("网银支付数");
			headName.add("网银支付金额");
			headName.add("货到付款下单数");
			headName.add("货到付款下单金额");
			headName.add("银行汇款支付数");
			headName.add("银行汇款支付金额");
			headName.add("备注");
			File zipfile1 = new File(dir+"/"+"xsgl"+".rar");
			zipfile1.delete();

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
				List<ChanceDTO> chanceDTOList = new ArrayList<ChanceDTO>();
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet("sheet");
					if (pageQuery != null) {
						pageQuery.setPageSize(5000);
						pageQuery.setCurrentPage(k + 1);
						chanceDTOList = chanceService.getAllChanceCheckOut(pageQuery)
								.getPageResult();
					} else {
						queryChanceCondition.setPageSize(5000);
						queryChanceCondition.setCurrentPage(k + 1);
						chanceDTOList = chanceService.searchChanceCheckOut(
								queryChanceCondition).getPageResult();
					}

				// 创建列数
				HSSFRow row = sheet.createRow(0);
				HSSFCell cell = row.createCell((short) 0);
				for (int i = 0; i < headName.size(); i++) {
					cell = row.createCell((short) i);
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(headName.get(i).toString());
				}
				for (int i = 0; i < chanceDTOList.size(); i++) {
					row = sheet.createRow(i + 1);
					int j = 0;
//					// 机会id
//					cell = row.createCell((short) j);
//					j++;
//					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
//					cell.setCellValue(chanceDTOList.get(i).getId());
					// 手机号码
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getMobile());
					// 用户email
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getEmail());
					// 所属项目
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getSubjectName());
					// 域名来源
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getWebName());
					// 销售机会状态
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					 if (chanceDTOList.get(i).getEmail()==null ||chanceDTOList.get(i).getEmail() == "") {
							cell.setCellValue("未注册");
					} else if (chanceDTOList.get(i).getFcount2() == 0) {
						cell.setCellValue("已注册");
					} else if (chanceDTOList.get(i).getFcount1() == 0) {
						cell.setCellValue("已下单");
					} else{
						cell.setCellValue("已购买");
					}
					// 机会来源
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if(chanceDTOList.get(i).getOrigin()==1){
						cell.setCellValue("自然注册");
					}
					if(chanceDTOList.get(i).getOrigin()==2){
						cell.setCellValue("乐语在线");
					}
					if(chanceDTOList.get(i).getOrigin()==4){
						cell.setCellValue("自然留言");
					}
					// 咨询状态
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (chanceDTOList.get(i).getConsultStatus() == 1) {
						cell.setCellValue("未联系");
					}
					if (chanceDTOList.get(i).getConsultStatus() == 2) {
						cell.setCellValue("首次");
					}
					if (chanceDTOList.get(i).getConsultStatus() == 3) {
						cell.setCellValue("回访");
					}
					// 销售坐席
						cell = row.createCell((short) j);
						j++;
						cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
						cell.setCellValue(chanceDTOList.get(i).getUserName());
					// 乐语创建坐席
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getFirstUserName());
					// 用户状态
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (chanceDTOList.get(i).getFollowStatus() == 1) {
						cell.setCellValue("放弃");
					}
					if (chanceDTOList.get(i).getFollowStatus() == 2) {
						cell.setCellValue("跟踪");
					}
					if (chanceDTOList.get(i).getFollowStatus() == 3) {
						cell.setCellValue("热点");
					}
					if (chanceDTOList.get(i).getFollowStatus() == 4) {
						cell.setCellValue("成交");
					}
					//流量来源
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getCusWebFrom());
					// 创建时间
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(sdf.format(chanceDTOList.get(i)
							.getChanceSTime()));
					// 最后指派时间
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (chanceDTOList.get(i).getChanceUTime() != null) {
						cell.setCellValue(sdf.format(chanceDTOList.get(i)
								.getChanceUTime()));
					} else {
						cell.setCellValue("--");
					}
					// 最后沟通时间
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (chanceDTOList.get(i).getEndCommTime() != null) {
						cell.setCellValue(sdf.format(chanceDTOList.get(i)
								.getEndCommTime()));
					} else {
						cell.setCellValue("--");
					}
					// 最后沟通状态
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					if (chanceDTOList.get(i).getEndCommStatus() == 1) {
						cell.setCellValue("空号");
					}
					if (chanceDTOList.get(i).getEndCommStatus() == 2) {
						cell.setCellValue("通话中");
					}
					if (chanceDTOList.get(i).getEndCommStatus() == 3) {
						cell.setCellValue("再联系");
					}
					if (chanceDTOList.get(i).getEndCommStatus() == 4) {
						cell.setCellValue("测试");
					}
					if (chanceDTOList.get(i).getEndCommStatus() == 5) {
						cell.setCellValue("正常接通");
					}
					if (chanceDTOList.get(i).getEndCommStatus() == 6) {
						cell.setCellValue("未接通");
					}
					if (chanceDTOList.get(i).getEndCommStatus() == 7) {
						cell.setCellValue("假号码");
					}
					if (chanceDTOList.get(i).getEndCommStatus() == 8) {
						cell.setCellValue("购买");
					}
					// 支付/订单数
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getFcount1() + "/"
							+ chanceDTOList.get(i).getFcount2());
					// 网银支付数
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getIntBankCount());
					// 网银支付金额
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getIntBankPrice());
					//货到付款下单数
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getSendCount());
					// 货到付款下单金额
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getSendPrice());
					// 银行汇款支付数
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getBankCount());
					// 银行汇款支付金额
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					cell.setCellValue(chanceDTOList.get(i).getBankPrice());
					// 备注
					cell = row.createCell((short) j);
					j++;
					cell.setEncoding(HSSFWorkbook.ENCODING_UTF_16);
					String remark="";
					if(chanceDTOList.get(i).getRecord()!=null){
						switch(chanceDTOList.get(i).getRecord().getCommStatus()){
						case 1:remark+="沟通状态：空号"; break;
						case 2:remark+="沟通状态：通话中"; break;
						case 3:remark+="沟通状态：再联系"; break;
						case 4:remark+="沟通状态：测试"; break;
						case 5:remark+="沟通状态：正常接通"; break;
						case 6:remark+="沟通状态：未接通"; break;
						case 7:remark+="沟通状态：假号码"; break;
						case 8:remark+="沟通状态：已购买"; break;
						}
					if(chanceDTOList.get(i).getRecord().getReasons()!=null&&!chanceDTOList.get(i).getRecord().getReasons().equals(""))
							remark+=";   未成单原因："+chanceDTOList.get(i).getRecord().getReasons();
					if(chanceDTOList.get(i).getRecord().getConcerns()!=null)
						remark+=";   关注点："+chanceDTOList.get(i).getRecord().getConcerns();
					if(chanceDTOList.get(i).getRecord().getWhyLearm()!=null)
						remark+=";   学习原因"+chanceDTOList.get(i).getRecord().getWhyLearm();
					if(chanceDTOList.get(i).getRecord().getRemarks()!=null)
						remark+=";   备注"+chanceDTOList.get(i).getRecord().getRemarks();
					}
					cell.setCellValue(remark);
				}
				
				File file=new File(dir+"/excel"+k+".xls");
				FileOutputStream fos = new FileOutputStream(file);
				workbook.write(fos);
				srcfile.add(file);
				fos.close();
			}
			File zipfile = new File(dir+"/"+"xsgl"+".rar");
			zipFiles(srcfile, zipfile);
			for(int i=0;i<a;i++){
				File file=new File(dir+"/excel"+i+".xls");
				file.delete();
			}
			
		} catch (IOException e) {

			logger.error("执行createExcelFile错误！", e);
			e.printStackTrace();
		}
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

	public Record getRecord() {
		return record;
	}

	public void setRecord(Record record) {
		this.record = record;
	}

	public Chance getChance() {
		return chance;
	}

	public void setChance(Chance chance) {
		this.chance = chance;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public IChance getChanceService() {
		return chanceService;
	}

	public void setChanceService(IChance chanceService) {
		this.chanceService = chanceService;
	}

	public IRecord getRecordService() {
		return recordService;
	}

	public void setRecordService(IRecord recordService) {
		this.recordService = recordService;
	}

	public IUsers getUsersService() {
		return usersService;
	}

	public void setUsersService(IUsers usersService) {
		this.usersService = usersService;
	}

	public QueryUsersCondition getQueryUsersCondition() {
		if (queryUsersCondition == null) {
			queryUsersCondition = new QueryUsersCondition();
		}
		return queryUsersCondition;
	}

	public void setQueryUsersCondition(QueryUsersCondition queryUsersCondition) {
		this.queryUsersCondition = queryUsersCondition;
	}

	public int getCrmChanceId() {
		return crmChanceId;
	}

	public void setCrmChanceId(int crmChanceId) {
		this.crmChanceId = crmChanceId;
	}

	public PageQuery getPageQuery() {
		return pageQuery;
	}

	public void setPageQuery(PageQuery pageQuery) {
		this.pageQuery = pageQuery;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public QueryChanceCondition getQueryChanceCondition() {
		if (queryChanceCondition == null) {
			queryChanceCondition = new QueryChanceCondition();
		}
		return queryChanceCondition;
	}

	public void setQueryChanceCondition(
			QueryChanceCondition queryChanceCondition) {
		this.queryChanceCondition = queryChanceCondition;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public ChanceRecordDTO getChanceRecordDTO() {
		return chanceRecordDTO;
	}

	public void setChanceRecordDTO(ChanceRecordDTO chanceRecordDTO) {
		this.chanceRecordDTO = chanceRecordDTO;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public IGroup getGroupService() {
		return groupService;
	}

	public void setGroupService(IGroup groupService) {
		this.groupService = groupService;
	}

	public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public List<RecordDTO> getRecordDTOList() {
		return recordDTOList;
	}

	public void setRecordDTOList(List<RecordDTO> recordDTOList) {
		this.recordDTOList = recordDTOList;
	}

	public int getGroupIds() {
		return groupIds;
	}

	public List<RecordDTO> getRecordFirstList() {
		return recordFirstList;
	}

	public void setRecordFirstList(List<RecordDTO> recordFirstList) {
		this.recordFirstList = recordFirstList;
	}

	public List<RecordDTO> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<RecordDTO> recordList) {
		this.recordList = recordList;
	}

	public List<RecordDTO> getRecordSecList() {
		return recordSecList;
	}

	public void setRecordSecList(List<RecordDTO> recordSecList) {
		this.recordSecList = recordSecList;
	}

	public void setGroupIds(int groupIds) {
		this.groupIds = groupIds;
	}

	public int getScanType() {
		return scanType;
	}

	public void setScanType(int scanType) {
		this.scanType = scanType;
	}

	public String getChanceIdBox() {
		return chanceIdBox;
	}

	public ICustomer getCustomerService() {
		return customerService;
	}

	public void setCustomerService(ICustomer customerService) {
		this.customerService = customerService;
	}

	public QueryCustomerCondition getQueryCustomerCondition() {
		if (queryCustomerCondition == null) {
			queryCustomerCondition = new QueryCustomerCondition();
		}
		return queryCustomerCondition;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public List<Contract> getContractList() {
		return contractList;
	}

	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}

	public IAddress getAddressService() {
		return addressService;
	}

	public void setAddressService(IAddress addressService) {
		this.addressService = addressService;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public List<ContractCrmDTO> getContractDTOList() {
		return contractDTOList;
	}

	public void setContractDTOList(List<ContractCrmDTO> contractDTOList) {
		this.contractDTOList = contractDTOList;
	}

	public void setChanceIdBox(String chanceIdBox) {
		this.chanceIdBox = chanceIdBox;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(InputStream excelFile) {
		this.excelFile = excelFile;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getContractNum() {
		return contractNum;
	}

	public void setContractNum(int contractNum) {
		this.contractNum = contractNum;
	}

	public int getContractNumPay() {
		return contractNumPay;
	}

	public void setContractNumPay(int contractNumPay) {
		this.contractNumPay = contractNumPay;
	}

	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public void setQueryCustomerCondition(
			QueryCustomerCondition queryCustomerCondition) {
		this.queryCustomerCondition = queryCustomerCondition;
	}

	public List<Users> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<Users> usersList) {
		this.usersList = usersList;
	}

	public IUser getUserService() {
		return userService;
	}

	public void setUserService(IUser userService) {
		this.userService = userService;
	}

	public int getUsersId() {
		return usersId;
	}

	public void setUsersId(int usersId) {
		this.usersId = usersId;
	}

	public QueryStatCondition getQueryStatCondition() {
		if (queryStatCondition == null) {
			queryStatCondition = new QueryStatCondition();
		}
		return queryStatCondition;
	}

	public void setQueryStatCondition(QueryStatCondition queryStatCondition) {
		this.queryStatCondition = queryStatCondition;
	}

	public List<SalesStatDTO> getSalesStatList() {
		return salesStatList;
	}

	public void setSalesStatList(List<SalesStatDTO> salesStatList) {
		this.salesStatList = salesStatList;
	}

    public IChanceRead getChanceReadService() {
        return chanceReadService;
    }

    public void setChanceReadService(IChanceRead chanceReadService) {
        this.chanceReadService = chanceReadService;
    }
	
}
