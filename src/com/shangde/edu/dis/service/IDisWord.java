package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.dis.domain.DisWord;

public interface IDisWord {

	/**
	 * 添加敏感词
	 * 
	 * @param word
	 *            敏感词对象
	 * @return
	 */
	public int add(DisWord word);

	/**
	 * 分页查看所有敏感词
	 * 
	 * @return 敏感词List集合
	 */
	public PageResult getDisWordList(PageResult page);

	/**
	 * 查询所有敏感词
	 * 
	 * @return
	 */
	public List<DisWord> getAllDisWord();

	/**
	 * 根据ID删除敏感词
	 * 
	 * @return
	 */
	public int delDisWordById(int id);

	/** ***V1.1方法(method)声明区域 开始**** */

	/** ***V1.1方法(method)声明区域 结束**** */

}
