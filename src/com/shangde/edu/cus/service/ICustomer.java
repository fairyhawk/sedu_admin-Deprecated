package com.shangde.edu.cus.service;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cus.condition.QueryCustomerCondition;
import com.shangde.edu.cus.condition.QueryNoteContentCondition;
import com.shangde.edu.cus.domain.CellPhone;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.cus.domain.CustomerNewDTO;
import com.shangde.edu.cus.domain.CustomerWithConSizeDTO;
import com.shangde.edu.cus.domain.NoteContent;
import com.shangde.edu.cus.domain.SubjectCustomerDTO;
import com.shangde.edu.cus.dto.AddressDTO;
import com.shangde.edu.cus.dto.CusLoginCountDTO;
import com.shangde.edu.cus.dto.CustomerCountNewDTO;
import com.shangde.edu.cus.dto.NoteContentDTO;
import com.shangde.edu.cus.dto.SimpleCustomerDTO;
import com.shangde.edu.sys.domain.Subject;

/**
 * Customer管理接口
 * User: guoqiang.liu
 * Date: 2010-07-26
 */
public interface ICustomer extends Serializable{
    /**
     * 添加Customer
     * @param customer 要添加的Customer
     * @return id
     */
    public java.lang.Integer addCustomer(Customer customer);

    /**
     * 根据id删除一个Customer
     * @param cusId 要删除的id
     */
    public void delCustomerById(int cusId);

    /**
     * 修改Customer
     * @param customer 要修改的Customer
     */
    public void updateCustomer(Customer customer);

    /**
     * 根据id获取单个Customer对象
     * @param cusId 要查询的id
     * @return 年级
     */
    public Customer getCustomerById(int cusId);

    /**
     * 根据条件获取Customer列表
     * @param queryCustomerCondition 查询条件
     * @return 查询结果
     */
    public List<Customer> getCustomerList(QueryCustomerCondition queryCustomerCondition);

    /**
     * 分页查询
     * @param queryCustomerCondition 查询条件
     * @return 分页结果
     */
	public PageResult getCustomerListByCondition(
			PageQuery queryCustomerCondition);

	/**
	 * 检查原密码是否正确
	 * @param customer 学员
	 * @return 是否正确
	 */
	public boolean checkOldPwd(Customer customer);

	/**
	 * 登录并返回学员id
	 * @param customer 用户名，密码
	 * @return 学员id
	 */
	public Integer getUIDByLogin(Customer customer);

	/**
	 * 检查电子邮件是否可用
	 * @param email 邮件
	 * @return 是否可用
	 */
	public boolean checkEmail(String email);
	/**
	 * 验证手机号是否已经注册过
	 * @param moblie
	 * @return
	 */
	public boolean checkMoblie(String moblie);
	/**
	 * 根据邮箱查询学员
	 * @param email 邮箱
	 */
	public Customer getCustomerByEmail(String email);
	
	/*
	 * 根据手机号查询学员
	 * @param moblie 手机号
	 */
	public List<Customer> getCustomerListByMobile(QueryCustomerCondition queryCustomerCondition);
	
	
	/**
	 * 根据用户id获取用户购买的课程与哪些subjectid对应
	 * @param userId
	 * @return
	 */
	public List<Subject> getSubjectListByCus(int userId);
	
	/**
	 * 判断是否可以试听
	 * @param userId
	 * @return
	 */
	public int isComplete(int userId);

	/**
	 * 发送注册成功email通知
	 * @param customer
	 */
	public void sendRegEmail(Customer customer) throws IOException ;

	/**
	 * 生成9位随机数字密码
	 * @return
	 */
	public String genericRandomPwd();

	/**
	 * 发送邮件通知新密码为多少（找回秘密）
	 * @param customer
	 */
	public void sendForgotPwdEmail(Customer customer) throws IOException;

	/**
	 * 生成6位随机验证码
	 * @return
	 */
	public String genericConfirmCode();
	
	/**
	 * 统计每日注册量
	 * @return
	 */
	public int getRegistNumber(QueryCustomerCondition queryCustomerCondition);
	public List<SubjectCustomerDTO> getRegistNumberList(QueryCustomerCondition queryCustomerCondition);
	/**
	 * 统计有几个月不重复
	 * @return
	 */
	public List getMonthList();
	/**
	 * 统计每个月的注册量
	 * @return
	 */
	public int getMonthRegistNumber(String month);
	/**
	 * 统计每天的注册量,月下面的日期
	 * @return
	 */
	public List getMonthDay(String month);
	
	public List getDay(String dateDay);
	
	public List addBathCustomer(List cusList) throws SQLException;

	/**
	 * 查看学员
	 * @param cusId
	 * @return
	 */
	public Customer getCustomerByView(int cusId);
	
	/**
	 * @author chenshuai
	 * 功能：查询简单的用户DTO信息集合
	 * @param args
	 * @param queryCustomerCondition
	 * @return
	 */
	public List<SimpleCustomerDTO> getSimpleCustomerDTOListByCondition(QueryCustomerCondition queryCustomerCondition);
	
	//修复订单
	public void fixFinance();
	//修复订单
	public void cusTypeFix();
		
	/**
	 * 删除体验（临时）学员的体验（临时）数据
	 * @param cusId
	 */
	public void recoverTempCustomer(int cusId);

	/**
	 * 删除体验（临时）学员除了传入订单的的其他体验（临时）数据
	 * @param cusId
	 * @param ctId
	 */
	public void recoverTempCustomer(int cusId, int ctId);
	
	/**
	 * @author wangzheng
	 * 功能：查询学员统计
	 * @param subjectId 专业ID
	 * @param cusType 学员注册类型
	 */
	public Integer getCustomerCount(QueryCustomerCondition queryCustomerCondition);
	
	
	/**
     * 修改Customer 的newerflag 新手引导用
     * @param customer 要修改的Customer
     */
    public void updateCustomerNewerflag(Customer customer);

	/**
	 * 范昕 2011-08-03
     * 修改Customer 的昵称
     * @param customer 要修改的Customer
     */
	public void updateCustomerName(Customer customer);
    
    /**
     * 获取收件人地址
     * @param cusId
     * @return
     */
    public List<AddressDTO> GetAddrByCusId(int cusId);
    
    
    /**
     *   范昕
     * 统计通过某个推广网站或站长注册人数
     * Date : 2011-8-10 16:49:16
     */
    public int getCustomerRegNum(QueryCustomerCondition queryCustomerCondition);

    /**
     *   范昕
     * 统计通过某个推广网站或站长注册人数
     * Date : 2011-8-23 16:49:16
     */
    public int getCustomerRegNumCount(QueryCustomerCondition queryCustomerCondition);   
    
    /**
     *   范昕
     * 统计通过某个推广网站或站长注册人数
     * Date : 2011-8-24 16:49:16
     */
    public List<SimpleCustomerDTO> getSimpleCustomerDTOListCusIdByCondition(QueryCustomerCondition queryCustomerCondition);
    
    /**
     *   范昕
     * //推 广 网 盟  已下订单学员量
     * Date : 2011-8-24 16:49:16
     */
    public int getContractNum(QueryCustomerCondition queryCustomerCondition);
    
    /**
     *   范昕
     * //推 广 网 盟  已下订单学员 下订单总数量
     * Date : 2011-8-24 16:49:16
     */
    public int getPayNum(QueryCustomerCondition queryCustomerCondition);
    
    /**
     *   范昕
     * //推 广 网 盟  已下订单学员 支付成功数量
     * Date : 2011-8-24 16:49:16
     */
    public int getPaySucNum(QueryCustomerCondition queryCustomerCondition);
   
    
    
    /**
     *@author  何海强
     * 返回排序的条件
     * Date: 2011-9-20 16:47
     */
    public int getTimesPaixu();
    
    /**
     * 李明
     * 发短信，查询所有的短信
     */
	public PageResult getNoteContentList(QueryNoteContentCondition queryNoteContentCondition);
	/**
	 * 李明
	 * @param queryNoteContentCondition
	 * 发短信，多条件查询
	 * @return PageResult
	 */
	public PageResult sendNoteContentListWhere(
			QueryNoteContentCondition queryNoteContentCondition);
	/**
	 * 李明 查询单个的短信内容
	 * @param queryNoteContentCondition
	 */
	public NoteContent getSingleContent(
			int notedId);
	/**
	 * 李明 根绝短息内容ID查询所对应的手机号码；
	 * @param queryNoteContentCondition
	 * @return
	 */
	public List<CellPhone> getListCellPhone(
			QueryNoteContentCondition queryNoteContentCondition);
	/**
	 * 李明 根绝
	 * @param queryNoteContentCondition
	 * @return
	 */

	public List<NoteContentDTO> showSinggleSellListJson(int subjectId);
	/**
	 * 添加短信内容
	 * @param noteContent
	 */
	public int addNoteContent(NoteContent noteContent);
	/**
	 * 添加手机号
	 * @param noteContent
	 */
	public void addNoteCellPhone(CellPhone cellPhone);

	/**
	 * 查询发送失败的短信
	 * @param noteId
	 * @return
	 */
	public List<CellPhone> getListCellPhoneCrabs(QueryNoteContentCondition queryNoteContentCondition);
	/**
	 * 查询发送所有短信
	 * @param noteId
	 * @return
	 */
	public int getListCellPhoneCout(int noteId);
	 /**
     * 分页查询
     * @param queryCustomerCondition 查询条件
     * @return 分页结果
     */
	public PageResult getCustomerCellPhoneListByCondition(
			QueryCustomerCondition queryCustomerCondition);
	/**
	 * 比较信息记录
	 * @param sendInfo
	 * @return
	 */
	public NoteContentDTO getDiscernEntity(int discern);
	/**
	 * 根据手机号查询EMAIL
	 * @param trim
	 */
	public List<Customer> getCustomerEmail(QueryCustomerCondition queryCustomerCondition);
	  /**
     *   张聚强
     * 导出EXCEL时用
     * Date : 2011-10-9 16:49:16
     */
    public List<CustomerWithConSizeDTO> getCustomerWithConSizeDTOList(QueryCustomerCondition queryCustomerCondition);
    
    /**
     * Yangning 根据邮箱查找是否存在此用户
     * @param ids
     * @return
     */
    public List<Map> getUserEmailByStrings(String ids);
    
    /**
     * Yangning 批量删除用户操作(级联)
     * @param ids  用户id(123121,1213212)格式
     * @return   删除记录信息(表名称，删除记录条数)
     * Author:Yangning
     * CreateDate:2011-12-2
     */
    public Map<String,Integer> delCustomerBath(String ids);
    
    /**
     * @param list
     * @return	增加记录信息
     * Author:Yangning
     * CreateDate:2011-12-5
     */
    public Integer addCustomerExistBathComplete(List<Map> list);
    
    /**
     * 
     * @param list
     * @return	增加记录信息
     * Author:Yangning
     * CreateDate:2011-12-5
     */
    public Integer addCustomerExistBathPart(List<Map> list);
    
    /**
     * Yangning  批量增加用户操作（此用户以前不存在）
     * @param list
     * @return	增加记录信息
     * Author:Yangning
     * CreateDate:2011-12-2
     */
    public Integer addCustomerNotExistBath(List<Map> list,Integer cusType,Integer cusSubjectId,Boolean isTemp);
    
    /**
     * 学员统计
     */
    public List<CustomerNewDTO> getCustomerAll(QueryCustomerCondition queryCustomerCondition);
    
    /**
     * 订单实收统计(月份)
     */
    public List<CustomerCountNewDTO> getOrdersPaid(QueryCustomerCondition queryCustomerCondition);
    
    /**
     * 订单实收统计（时间段）
     */
    public List<CustomerCountNewDTO> getOrdersPaidTime(QueryCustomerCondition queryCustomerCondition);
    
    /**
     * 学员统计（按月）
     */
    public List<CustomerCountNewDTO> getStudentMonth(QueryCustomerCondition queryCustomerCondition);
    
    /**
     * DS商品学员统计
     * @param queryCustomerCondition
     * @return
     */
    public List<CustomerNewDTO> getDSCustomerAll(QueryCustomerCondition queryCustomerCondition);
    
    /**
     * 获取学员登录统计
     * @param day
     * @return
     */
    public List<CusLoginCountDTO> getCusLoginCount(Map map);
    
    public List<CusLoginCountDTO> getCusLoginSum(Map map);
    /**
     *根据项目获取crm里的分类学员数量
     * @param queryCustomerCondition
     * @return
     */
    public List<CustomerNewDTO> getUserBySubjectGroup(QueryCustomerCondition queryCustomerCondition);
    
    
    /**
     * 杨宁  2012/04/12
     * 根据cusids查看是否有注册用户
     * @param cusIds
     * @return
     */
    public int getCustomerNotTempCount(String cusIds);
    /**
     * 用户信息统计
     */
	public void addCusRankToTable();
    /**
	 * @author 王超
	 * 通过userId获取当天发送短信的个数
	 * @param userId
	 * @return
	 */
	public int getTodaySendCountByUserId(int userId);
}