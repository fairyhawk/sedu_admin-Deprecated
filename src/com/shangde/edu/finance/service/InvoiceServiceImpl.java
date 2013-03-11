package com.shangde.edu.finance.service;


import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.finance.condition.QueryInvoiceCondition;
import com.shangde.edu.finance.domain.Invoice;

public class InvoiceServiceImpl extends BaseService implements InvoiceService {

	/**
	 * 查询发票信息
	 * @param invoice
	 * @return
	 */
	public Invoice getInvoice(Invoice invoice) {
		return simpleDao.getEntity("Invoice_NS.getInvoiceById", invoice);
	}

	/**
	 * 查询发票列表
	 * @return
	 */
	public PageResult getInvoiceList(QueryInvoiceCondition queryInvoiceCondition) {
		return simpleDao.getPageResult("Invoice_NS.getInvoiceList", "Invoice_NS.getInvoiceListCount", queryInvoiceCondition);
	}

	/**
	 *新增或者更新发票信息
	 * @param invoice
	 */
	public void addOrUpdateInvoice(Invoice invoice) {
		if(invoice.getInvoiceId()==null){
			simpleDao.createEntity("Invoice_NS.createInvoice", invoice);
		}else{
			simpleDao.updateEntity("Invoice_NS.updateInvoice", invoice);
		}
	}
	
	/**
	 * 校验发票号码是否重复
	 * @param invoice
	 * @return
	 */
	public String isInvoiceCodeRepeat(Invoice invoice) {
		return simpleDao.getEntity("Invoice_NS.isInvoiceCodeRepeat", invoice);
	}

	/**
	 * 校验运单号码是否重复
	 * @param invoice
	 * @return
	 */
	public String isWaybillCodeRepeat(Invoice invoice) {
		return simpleDao.getEntity("Invoice_NS.isWaybillCodeRepeat", invoice);
	}

}
