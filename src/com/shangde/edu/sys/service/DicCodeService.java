package com.shangde.edu.sys.service;

import java.util.List;
import java.util.Map;
import com.shangde.edu.sys.domain.DicCode;
public interface DicCodeService {
	/**
	 * 查询出字典实体
	 * @param dicCode
	 * @return
	 */
	public List<DicCode> getDicCodeList(String dicType,String dicCodeStr);
	/**
	 * 查询所有字典数据
	 */
	public void getFields();
	/**
	 * 根据字典类型查询字典内容
	 * @param type
	 * @return
	 */
	public Map<String,String> getDicByType(String type);
	/**
	 * 根据字典类型以及字典code查询字典内容
	 * @param dictype
	 * @param dicCode
	 * @return
	 */
	public Map<String,String> getDicContent(String dictype,List<String>list);
}
