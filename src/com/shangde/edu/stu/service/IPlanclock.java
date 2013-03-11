package com.shangde.edu.stu.service;

import java.io.IOException;
import java.util.List;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.domain.UserMsg;
import com.shangde.edu.stu.domain.Plan;
import com.shangde.edu.stu.domain.Planclock;
import com.shangde.edu.stu.domain.SubjectEntity;
import com.shangde.edu.stu.domain.SubjectIdBackEntity;
import com.shangde.edu.stu.condition.QueryPlanCondition;
import com.shangde.edu.stu.condition.QueryPlanclockCondition;
import com.shangde.edu.sys.domain.Subject;

/**
 * 计划提醒Service层
 * @author baiang.zhao
 * Date : 2011-5-29 13:04:38
 */
public interface IPlanclock {
    
	/**
	 * 添加一个计划提醒
	 * @param planclock 计划提醒对象
	 * @return 返回添加成功的那个“计划提醒”的ID
	 */
    public Integer addPlanclock(Planclock planclock);

    /**
     * 通过ID删除一个计划提醒
     * @param planclockId 计划提醒的ID
     */
    public void delPlanclockById(Integer planclockId);

    /**
     * 更新一个计划提醒
     * @param planclock 
     */
    public void updatePlanclock(Planclock planclock);

    /**
     * 通过ID获取一个计划提醒的对象
     * @param planclockId 计划提醒ID
     * @return 返回得到的计划对象
     */
    public Planclock getPlanclockById(Integer planclockId);

    /**
     * 得到全部提醒的集合
     * ---------------添加分页后，该接口已废弃！
     * @param queryPlanclockCondition 
     * @return 
     */
    public List<Planclock> getPlanclockList(QueryPlanclockCondition queryPlanclockCondition);
 
    /**
     * 
     * @param queryPlanclockCondition 
     * @return 
     */
    public List<Planclock> getPlanclockListByDate(QueryPlanclockCondition queryPlanclockCondition);

    
    /**
     * 查询全部闹钟对象
     * 			且做分页处理
     * Date : 2011-5-27 16:49:16
     * @param queryPlanclockCondition 查询对象
     * @return 分页对象
     */
    public PageResult getPlanclockPaperByCondition(QueryPlanclockCondition queryPlanclockCondition);
    
    /**
     * 根据检索条件，检索相应的结果
     * ----------------------添加分页后，该接口已废弃！
     * @param queryPlanclockCondition
     * @return 返回Planclock集合
     */
    public List<Planclock> searchPlanclockListByParam(QueryPlanclockCondition queryPlanclockCondition);
    
    /**
     * 根据检索条件，检索相应的结果
     * 					已做分页处理
     * Date : 2011-5-27 16:56:19
     * @param queryPlanclockCondition 查询条件
     * @return
     */
    public PageResult searchPlanclockListByParamCondition(QueryPlanclockCondition queryPlanclockCondition);
    
    /**
     * 批处理，修改计划提醒的状态
     * @param batchParamsId
     * @param batchStatus
     * @return
     */
    //public List<Planclock> batchProcessPlanclockList(String batchId, int batchStatus);
    
	/**
	 * 查询我购买的专业
	 * 
	 * @return
	 */
	List<Integer> getMyBuySubject(Integer cusId);
	/**
	 * 根据时间查询所对应的项目名字；
	 * @return  List
	 */
	public List<Planclock> getSubjectId(QueryPlanclockCondition queryPlanclockCondition);
	/**
	 *  根据项目的ID查询所要发的信息的值；
	 * 
	 * @param subjectId
	 */
	public List<SubjectIdBackEntity>  getSubjectIdOutInfo(int subjectId);
	/**
	 * 发邮件
	 * @param subjectIdBackEntity
	 * @throws IOException 
	 */
	public void sendForgotPwdEmail(SubjectIdBackEntity subjectIdBackEntity) throws IOException;
	/**
	 * 再msg_message_tbl中添加要显示的信息；
	 * @param msgMessage 
	 */
	public int addTSSO(Message message);
	/**
	 * 添加用户信息
	 * @param msgUserMsg
	 */
	public void addtSSOuserInfo(UserMsg UserMsg);
	/**
	 * 根据项目ID查询项目表中所对应的值；
	 * @param queryPlanclockCondition
	 */
	public Subject getSubject(int subjectId);

	/**
	 * 修改表字段
	 * @param planClock
	 */
	public void updatePlanclockIsent(Planclock planClock);
}