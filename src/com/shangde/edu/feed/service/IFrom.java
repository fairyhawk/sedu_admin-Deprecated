/**
 * 
 */
package com.shangde.edu.feed.service;

import java.util.List;

import com.shangde.edu.feed.domain.From;

/**
 * @author Libg
 * 
 */
public interface IFrom {

	/**
	 * 添加
	 * 
	 *
     * @param from
     * @return
	 */
	public Integer addFrom(From from);

	/**
	 * 根据id获取个数,一般校验这个id知否存在
	 * 
	 *
     * @param id
     * @return
	 */
	public Integer getFromCount(int id);
	
	
	/**
	 * 查询所有的来源条件
	 */
	public List<From> queryFromList();
	
	
}
