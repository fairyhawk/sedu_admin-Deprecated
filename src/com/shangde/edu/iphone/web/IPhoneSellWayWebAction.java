package com.shangde.edu.iphone.web;

import java.util.List;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Teacher;
import com.shangde.edu.cus.domain.Customer;
import com.shangde.edu.finance.condition.QueryContractCondition;
import com.shangde.edu.finance.service.IContract;
import com.shangde.edu.iphone.condition.QuerySellWayIPhoneCondition;
import com.shangde.edu.iphone.dto.IphoneModel;
import com.shangde.edu.iphone.service.ISellWayIPhone;

public class IPhoneSellWayWebAction extends CommonAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1187622558201602281L;

	/**
	 * 订单服务
	 */
	private IContract contractService;

	private ISellWayIPhone sellWayIPhoneService;

	private QuerySellWayIPhoneCondition querySellWayIPhoneCondition;

	/**
	 * 订单查询条件
	 */
	private QueryContractCondition queryContractCondition;

	/**
	 * 学员
	 */
	private Customer customer;

	/**
	 * 给张栋提供iphone端数据 得到当前用户购买的售卖方式集合 获取当前用户所包含的课程
	 * 
	 * @return
	 */
	public String getSellWayByCusId() {
		PageResult pageResult = null;
		String returnMessage = "";
		if (getLoginUserId() != 0) {
			this.getCustomer().setCusId(getLoginUserId());
		}
		if (this.customer != null && this.customer.getCusId() != 0) {

			this.getQuerySellWayIPhoneCondition()
					.setUserId(customer.getCusId()); // 添加查询条件，当前用户登陆ID
			// this.getQueryContractCondition().setPageSize(10); //设置每页显示数量
			pageResult = this.sellWayIPhoneService
					.getContractSellWayByCusId(this
							.getQuerySellWayIPhoneCondition()); // 查询当前用户所购买的售卖方式
			List<IphoneModel> sellList = pageResult.getPageResult();
			if (sellList != null && !sellList.isEmpty()) {
				for (IphoneModel iphoneModel : sellList) {
					iphoneModel
							.setTeachers(getCourseTeacherBySellId(iphoneModel
									.getVideoId()));
				}
			}
			setPage(pageResult);
			setPageUrlParms();
			if (getPage() != null) {
				getPage().setPageSize(10);
			}
			returnMessage = "success";
		} else {
			returnMessage = "fail";
		}
		setResult(new Result<PageResult>(false, returnMessage, null, pageResult));
		return "json";
	}

	/**
	 * 得到当前售卖方式下的课程老师！
	 * 
	 * @param sellWayId
	 *            售卖方式ID
	 * @return
	 */
	private String getCourseTeacherBySellId(int sellWayId) {
		String str = "";
		List<Teacher> teacherList = this.sellWayIPhoneService
				.getTeacherBySellWayId(sellWayId); // 根据售卖方式ID 得到售卖方式下所有课程的老师
		if (teacherList.size() != 0) { // 遍历老师 做处理
			for (int i = 0; i < teacherList.size(); i++) {
				if (teacherList.get(i) != null) {
					str += teacherList.get(i).getName() + "、";
				}
			}
		}
		return str.substring(0, str.length() - 1); // 返回老师字符串，截掉最后一个（、）
	}

	public IContract getContractService() {
		return contractService;
	}

	public void setContractService(IContract contractService) {
		this.contractService = contractService;
	}

	public QueryContractCondition getQueryContractCondition() {
		return queryContractCondition;
	}

	public void setQueryContractCondition(
			QueryContractCondition queryContractCondition) {
		this.queryContractCondition = queryContractCondition;
	}

	public Customer getCustomer() {
		if (this.customer == null) {
			this.customer = new Customer();
		}
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ISellWayIPhone getSellWayIPhoneService() {
		return sellWayIPhoneService;
	}

	public void setSellWayIPhoneService(ISellWayIPhone sellWayIPhoneService) {
		this.sellWayIPhoneService = sellWayIPhoneService;
	}

	public QuerySellWayIPhoneCondition getQuerySellWayIPhoneCondition() {
		if (this.querySellWayIPhoneCondition == null) {
			this.querySellWayIPhoneCondition = new QuerySellWayIPhoneCondition();
		}
		return querySellWayIPhoneCondition;
	}

	public void setQuerySellWayIPhoneCondition(
			QuerySellWayIPhoneCondition querySellWayIPhoneCondition) {
		this.querySellWayIPhoneCondition = querySellWayIPhoneCondition;
	}

}
