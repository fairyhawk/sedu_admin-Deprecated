package com.shangde.edu.res.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.exception.CommException;
import com.shangde.edu.res.condition.QueryVedioCondition;
import com.shangde.edu.res.condition.QueryVediogroupCondition;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.domain.Vediogroup;
import com.shangde.edu.res.service.IVedio;
import com.shangde.edu.res.service.IVediogroup;

/**
 * 视频组管理
 * 
 * @author miaoshusen
 * @version 1.0
 */
public class VedioGroupAction extends CommonAction {
	
	private static final Logger logger = Logger.getLogger(VedioGroupAction.class);
	/**
	 * 声名视频组的PO对象
	 */
	private Vediogroup vedioGroup;
	/**
	 * 声名视频组的服务
	 */
	private IVediogroup vediogroupService;
	/**
	 * 查询用到的condition
	 */
	private QueryVediogroupCondition queryVedioGroupCondition;
	/**
	 * 声名视频的PO对象
	 */
	private Vedio vedio;
	/**
	 * 声名视频的服务
	 */
	private IVedio vedioService;
	/**
	 * 声名视频的conditon
	 */
	private QueryVedioCondition queryVedioCondition;
	/**
	 * 声名一个整型的数组
	 */
	private int[] voId;
	/**
	 * 声名Vedio类型的list
	 */
	private List<Vedio> list;

	/**
	 * 查询条件
	 */
	private String searchKey;
	/**
	 * 声名Vediogroup类型的list
	 */
	private List<Vediogroup> vediogroupList;
	

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public List<Vedio> getList() {
		return list;
	}

	public void setList(List<Vedio> list) {
		this.list = list;
	}

	public int[] getVoId() {
		return voId;
	}

	public void setVoId(int[] voId) {
		this.voId = voId;
	}

	public QueryVedioCondition getQueryVedioCondition() {

		if (queryVedioCondition == null) {
			queryVedioCondition = new QueryVedioCondition();

		}

		return queryVedioCondition;
	}

	public void setQueryVedioCondition(QueryVedioCondition queryVedioCondition) {
		this.queryVedioCondition = queryVedioCondition;
	}

	public Vedio getVedio() {
		return vedio;
	}

	public void setVedio(Vedio vedio) {
		this.vedio = vedio;
	}

	public IVedio getVedioService() {
		return vedioService;
	}

	public void setVedioService(IVedio vedioService) {
		this.vedioService = vedioService;
	}

	public Vediogroup getVedioGroup() {
		return vedioGroup;
	}

	public void setVedioGroup(Vediogroup vedioGroup) {
		this.vedioGroup = vedioGroup;
	}

	public IVediogroup getVediogroupService() {
		return vediogroupService;
	}

	public void setVediogroupService(IVediogroup vediogroupService) {
		this.vediogroupService = vediogroupService;
	}

	public QueryVediogroupCondition getQueryVedioGroupCondition() {
		if (queryVedioGroupCondition == null) {
			queryVedioGroupCondition = new QueryVediogroupCondition();

		}
		return queryVedioGroupCondition;
	}

	public void setQueryVedioGroupCondition(
			QueryVediogroupCondition queryVedioGroupCondition) {
		this.queryVedioGroupCondition = queryVedioGroupCondition;
	}
	public List<Vediogroup> getVediogroupList() {
		return vediogroupList;
	}

	public void setVediogroupList(List<Vediogroup> vediogroupList) {
		this.vediogroupList = vediogroupList;
	}

	
	/**
	 * 获得视频组列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String getVedioGroupList() {
		try {
			if (searchKey != null && !"".equals(searchKey.trim())) {
				this.getQueryVedioGroupCondition().setSearchKey(
						searchKey.trim());
			}
			setPage(this.vediogroupService
					.getVediogroupList(getQueryVedioGroupCondition()));
			setPageUrlParms();
		} catch (Exception e) {
			logger.error("VedioGroupAction.getVedioGroupList", e);
			return ERROR;
		}
		return "listVedioGroup";

	}

	/**
	 * 打开视频的列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toAddVedioList() {
		try {
			setPage(this.vedioService.getVedioList(getQueryVedioCondition()));
			setPageUrlParms();
			
		} catch (Exception e) {
			logger.error("VedioGroupAction.toAddVedioList", e);
			return ERROR;
		}
		return "toAddVedioList";

	}

	/**
	 * 打开添加视频组的页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toAddVedioGroup() {
		try {
			List vediolist = new ArrayList();
			int[] voId = this.getVoId();
			if (voId != null) {
				for (int i = 0; i < voId.length; i++) {

					vediolist.add(voId[i]);

				}
				this.setSession("vediolist", vediolist);

			}
		} catch (Exception e) {
			logger.error("VedioGroupAction.toAddVedioGroup", e);
			return ERROR;
		}
		return "toAddVedioGroup";
	}

	/**
	 * 把视频添加到视频组的时候，在选择添加到哪个组的页面，打开新建视频组的页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toAddVedioGroupNew() {
		try {
			List vediolist = new ArrayList();
			int[] voId = this.getVoId();
			if (voId != null) {
				for (int i = 0; i < voId.length; i++) {

					vediolist.add(voId[i]);

				}
				this.setSession("vediolist", vediolist);

			}
//			setPage(this.vediogroupService
//					.getVediogroupList(getQueryVedioGroupCondition()));
			vediogroupList=this.vediogroupService.getVediogroupByList(getQueryVedioGroupCondition());
		} catch (Exception e) {
			logger.error("VedioGroupAction.toAddVedioGroupNew", e);
			return ERROR;
		}
		return "toAddVedioGroupNew";
	}

	/**
	 * 添加视频到视频组
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addVedioGroup() {
		try {
			Date date = new Date();
			vedioGroup.setVgTime(date);
			this.vediogroupService.addVediogroup(vedioGroup);

			// 更新视频表里的vgId字段
			List vediolist = this.getSession("vediolist");
			if (vediolist != null) {
				for (int i = 0; i < vediolist.size(); i++) {
					vedio = this.vedioService.getVedioById((Integer) vediolist
							.get(i));
					vedio.setVgId(vedioGroup.getVgId());
					this.vedioService.updateVedio(vedio);
				}
			}
		} catch (Exception e) {
			logger.error("VedioGroupAction.addVedioGroup", e);
			return ERROR;
		}
		return "listAllVedioGroup";
	}

	/**
	 * 把视频添加到视频组的时候，在选择添加到哪个组的页面， 打开新建视频组的页面， 把视频添加到这个新建的组里
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String addVedioGroupNew() {
		try {
			// 更新视频表里的vgId字段
			List vediolist = this.getSession("vediolist");
			if (vediolist != null) {
				for (int i = 0; i < vediolist.size(); i++) {
					vedio = this.vedioService.getVedioById((Integer) vediolist
							.get(i));
					vedio.setVgId(vedioGroup.getVgId());
					this.vedioService.updateVedio(vedio);
				}
			}
		} catch (Exception e) {
			logger.error("VedioGroupAction.addVedioGroupNew", e);
			return ERROR;
		}
		return "listAllVedioGroup";
	}

	/**
	 * 在视频组名称下面显示视频的列表
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String veiwVedioList() {
		try {
			this.list = this.vedioService.getVedioByVgId(vedioGroup.getVgId());
		} catch (Exception e) {
			logger.error("VedioGroupAction.veiwVedioList", e);
			return ERROR;
		}
		return "veiwVedioList";

	}

	/**
	 * 打开编辑视频组的页面
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String toEditVedioGroup() {
		try {
			vedioGroup = this.vediogroupService.getVediogroupById(vedioGroup
					.getVgId());
		} catch (Exception e) {
			logger.error("VedioGroupAction.toEditVedioGroup", e);
			return ERROR;
		}
		return "toEditVedioGroup";

	}

	/**
	 * 修改视频组的信息
	 * 
	 * @return String
	 * @throws Exception
	 */
	public String editVedioGroup() {
		try {
			Date date = new Date();
			vedioGroup.setVgTime(date);
			this.vediogroupService.updateVediogroup(vedioGroup);
		} catch (Exception e) {
			logger.error("VedioGroupAction.editVedioGroup", e);
			return ERROR;
		}
		return "listAllVedioGroup";

	}
	/**
	 * 删除视频组，同时修改组下面的视频跟组的关联，解除关联
	 * 
	 * @return String
	 * @throws Exception
	 */

   public String deleteVedioGroup() {
		try {
			Vedio vedio=null;
			if(vedioGroup.getVgId()!=0){
				//把组下面的视频关联解除
				List<Vedio> vedioList=this.vedioService.getVedioByVgId(vedioGroup.getVgId());
				for(int i=0;vedioList!=null&&i<vedioList.size();i++){
					vedio=new Vedio();
					vedio=vedioList.get(i);
					vedio.setVgId(0);
					this.vedioService.updateVedio(vedio);
				}
				//删除组
				this.vediogroupService.delVediogroupById(vedioGroup.getVgId());
			}
			
			
			
		} catch (Exception e) {
			logger.error("VedioGroupAction.deleteVedioGroup", e);
			return ERROR;
		}
		return "listAllVedioGroup";
	}

}
