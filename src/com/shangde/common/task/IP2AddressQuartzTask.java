package com.shangde.common.task;

import java.util.List;

import com.shangde.common.util.IPUtil;
import com.shangde.edu.cus.domain.LoginRecord;
import com.shangde.edu.cus.service.ILoginRecord;

/**
 * 定时任务
 * @author chenshuai
 *
 */
public class IP2AddressQuartzTask{
	
	/**
	 * 登陆信息服务对象
	 */
	private ILoginRecord loginRecordService;
	
	protected void getAddressByIP(){
		try {
		
			List<LoginRecord> list = loginRecordService.getAddressNullLoginRecordList();
			for(int i=0; i<list.size(); i++) {
				try {
					LoginRecord loginRecord = list.get(i);
					String ip = "";
					if(loginRecord.getLoginIp() != null  &&  loginRecord.getLoginIp().indexOf(",") != -1) {
						ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
					} else {
						ip = loginRecord.getLoginIp();
					}
					loginRecord.setAddress(IPUtil.getAddressByIP(ip));
					loginRecordService.updateLoginRecord(loginRecord);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ILoginRecord getLoginRecordService() {
		return loginRecordService;
	}

	public void setLoginRecordService(ILoginRecord loginRecordService) {
		this.loginRecordService = loginRecordService;
	}
}
