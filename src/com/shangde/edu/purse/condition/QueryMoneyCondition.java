package com.shangde.edu.purse.condition;

import java.util.Date;

import com.shangde.common.vo.PageQuery;

public class QueryMoneyCondition extends PageQuery {
        private String userAccount;//用户账号
        private Date createBeginTime;//创建开始时间
        private Date createEndTime;//创建结束时间
        private String mobile;//手机
        private Date updateBeginTime;//最后充值开始时间
        private Date updateEndTime;//最后充值结束时间
        private Integer status;//账户状态
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getUserAccount() {
			return userAccount;
		}
		public void setUserAccount(String userAccount) {
			this.userAccount = userAccount;
		}
		public Date getCreateBeginTime() {
			return createBeginTime;
		}
		public void setCreateBeginTime(Date createBeginTime) {
			this.createBeginTime = createBeginTime;
		}
		public Date getCreateEndTime() {
			return createEndTime;
		}
		public void setCreateEndTime(Date createEndTime) {
			this.createEndTime = createEndTime;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public Date getUpdateBeginTime() {
			return updateBeginTime;
		}
		public void setUpdateBeginTime(Date updateBeginTime) {
			this.updateBeginTime = updateBeginTime;
		}
		public Date getUpdateEndTime() {
			return updateEndTime;
		}
		public void setUpdateEndTime(Date updateEndTime) {
			this.updateEndTime = updateEndTime;
		}
		
        
}