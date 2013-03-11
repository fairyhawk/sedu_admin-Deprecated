package com.shangde.edu.crm.condition;

import java.io.Serializable;

import com.shangde.common.vo.PageQuery;

public class QuerySellrecordCondition extends PageQuery implements Serializable{
        /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String sellName;
        private String sysUserName;
        private String startTime;
        private String endTime;
        private int showSelf;
        private int sysUserId;
        private int groupId;
        private int scene;
        
		public int getShowSelf() {
			return showSelf;
		}
		public void setShowSelf(int showSelf) {
			this.showSelf = showSelf;
		}
		public String getSellName() {
			return sellName;
		}
		public void setSellName(String sellName) {
			this.sellName = sellName;
		}
		public String getSysUserName() {
			return sysUserName;
		}
		public void setSysUserName(String sysUserName) {
			this.sysUserName = sysUserName;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public int getSysUserId() {
			return sysUserId;
		}
		public void setSysUserId(int sysUserId) {
			this.sysUserId = sysUserId;
		}
		public int getScene() {
			return scene;
		}
		public void setScene(int scene) {
			this.scene = scene;
		}
		public int getGroupId() {
			return groupId;
		}
		public void setGroupId(int groupId) {
			this.groupId = groupId;
		}
}