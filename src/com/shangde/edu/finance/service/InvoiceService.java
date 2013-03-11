package com.shangde.edu.finance.service;


import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryInvoiceCondition;
import com.shangde.edu.finance.domain.Invoice;

public interface InvoiceService {
	/**
	 * 查询发票信息
	 * @param invoice
	 * @return
	 */
	public Invoice getInvoice(Invoice invoice);
	/**
	 * 查询发票列表
	 * @return
	 */
	public PageResult getInvoiceList(QueryInvoiceCondition queryInvoiceCondition);
	/**
	 *新增或者更新发票信息
	 * @param invoice
	 */
	public void addOrUpdateInvoice(Invoice invoice);
	/**
	 * 校验发票号码是否重复
	 * @param invoice
	 * @return
	 */
	public String isInvoiceCodeRepeat(Invoice invoice);
	/**
	 * 校验运单号码是否重复
	 * @param invoice
	 * @return
	 */
	public String isWaybillCodeRepeat(Invoice invoice);
}
