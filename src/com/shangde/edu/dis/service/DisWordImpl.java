package com.shangde.edu.dis.service;

import java.util.List;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.dis.domain.DisWord;

public class DisWordImpl extends BaseService implements IDisWord {

	/**
	 * 添加敏感词
	 * 
	 * @param word
	 *            敏感词对象
	 * @return
	 */
	public int add(DisWord word) {
		return simpleDao.createEntity("DisWord_NS.createDisWord", word);
	}

	/**
	 * 分页查看所有敏感词
	 * 
	 * @return 敏感词List集合
	 */
	public PageResult getDisWordList(PageResult page) {

		PageResult pr = simpleDao.getPageResult("DisWord_NS.getDisWordByList",
				"DisWord_NS.getDisWordCount", page);

		return pr;
	}

	/**
	 * 查询所有敏感词
	 * 
	 * @return
	 */
	public List<DisWord> getAllDisWord() {
		return simpleDao.getForList("DisWord_NS.getAllDisWord", null);
	}

	/**
	 * 根据ID删除敏感词
	 * 
	 * @return
	 */
	public int delDisWordById(int id) {
		return simpleDao.delete("DisWord_NS.delDisWordById", id);
	}
}
