package com.shangde.edu.cms.service;

import java.io.IOException;
import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.cms.domain.Columns;
import com.shangde.edu.cms.condition.QueryColumnsCondition;

/**
 * Columns管理接口
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
public interface IColumns {
    /**
     * 添加Columns
     * @param columns 要添加的Columns
     * @return id
     */
	public java.lang.Integer addColumns(Columns columns,String catalog);

    /**
     * 根据id删除一个Columns
     * @param columnId 要删除的id
     */
    public void delColumnsById(int columnId);

    /**
     * 修改Columns
     * @param columns 要修改的Columns
     */
    public void updateColumns(Columns columns);

    /**
     * 根据id获取单个Columns对象
     * @param columnId 要查询的id
     * @return 年级
     */
    public Columns getColumnsById(int columnId);

    /**
     * 根据条件获取Columns列表
     * @param queryColumnsCondition 查询条件
     * @return 查询结果
     */
    public List<Columns> getColumnsList(QueryColumnsCondition queryColumnsCondition);
    /**
     * 分页查询
     * @param 分页查询条件
     * @return PageResult
     */
    public PageResult getColumnsListByParentId(QueryColumnsCondition queryColumnsCondition);
    /**
     * 按parentid查询
     * @param queryColumnsCondition 查询条件
     * @return 结果集合
     */
    public List<Columns>getColumnListByParentId(QueryColumnsCondition queryColumnsCondition);
    
    /**
     * 删除多个column
     * @param ids 条件，多个columnid
     * @return 判断性结果
     */
    public Integer[] delColumnsByIds(String ids);
    public String getColumnNameById(int columnId);

	
	/**
	 * 递归静态化栏目下的所有栏目
	 * @param columns
	 */
	public void recursionGenericColumn(String staticPath, Columns columns) throws Exception;

	/**
	 * 把一个栏目静态化
	 * @param columns
	 */
	public void genericColumn(String staticPath, Columns columns) throws Exception ;

//	2010-09-24 DEL BY zhou zhi qiang   start
//	/**
//	 * 递归静态化栏目下的所有栏目及频道页
//	 * @param columns
//	 */
//	public void recursionGenericChannel(String staticPath, Columns columns) throws Exception;
//
//	/**
//	 * 把一个栏目的频道页静态化
//	 * @param columns
//	 */
//	public void genericChannelByColumn(String staticPath, Columns columns) throws Exception ;
//2010-09-24 DEL BY zhou zhi qiang   end
	
    /**
     * 根据条件获取Columns列表(为静态化处理标签准备)
     * @param queryColumnsCondition 查询条件
     * @return 查询结果
     */
    public List<Columns> getColumnsListForTag(QueryColumnsCondition queryColumnsCondition);

    /**
     * 将一个栏目下的文章静态化
     * @param staticPath
     * @param columns
     */
	public void genericArticle(String staticPath, Columns columns) throws Exception;

	/**
	 * 递归栏目将文章静态化
	 * @param staticPath
	 * @param columns
	 */
	public void recursionGenericArticle(String staticPath, Columns columns) throws Exception;

	/**
	 * 将所有课程静态化
	 * @param string
	 */
	public void genericCourse(String staticPath) throws Exception;

	/**
	 * 将所有教师静态化
	 * @param string
	 */
	public void genericTeacher(String staticPath) throws IOException;
	
	/**
	 * 将所有优惠券静态化
	 * @param string
	 */
	public void genericCoupon(String staticPath) throws IOException;
	
	/**
	 * 产生课程分类静态文件
	 */
	public void generateCourseSort(String staticPath) throws Exception;
	
	/**
	 * 产生班级静态文件
	 */
	public void generateClazz(String staticPath) throws Exception;

	/**
	 * 生成反馈信息
	 * @param string
	 */
	public void genericAnswer(String string) throws IOException;

	/**
	 * 生成评论信息
	 * @param string
	 */
	public void genericComment(String string) throws IOException;

	public Columns getColumnsByKeyWord(String keyWord);
	
	//谢添加开始
	/**
	 * 预览
	 * @param staticPath
	 * @param columns
	 */
	public String PreGenericColumn(String staticPath, Columns columns) throws Exception;
	
	/**
	 * 静态化 
	 * @param staticPath
	 * @param columns
	 * @throws Exception
	 */
	public String PrerecursionGenericColumn(String staticPath, Columns columns)  throws Exception;
	//谢添加结束
}