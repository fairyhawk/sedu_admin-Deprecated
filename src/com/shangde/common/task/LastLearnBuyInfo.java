/**
 * ClassName  LastLearnBuyInfo
 *
 * History
 * Create User: liuqinggang
 * Create Date: Apr 2, 2011
 * Update User:
 * Update Date:
 */
package com.shangde.common.task;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangde.common.util.FileUtils;
import com.shangde.edu.cou.condition.QuerySellWayCondition;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.cusmgr.dto.WatchRankDTO;
import com.shangde.edu.cusmgr.service.ICusMgr;
import com.shangde.edu.sys.domain.Subject;
import com.shangde.edu.sys.service.ISubject;
import com.shangde.edu.vst.remote.IWebService;
import com.shangde.edu.vst.remote.WebServiceFactory;

/**
 * 
 * @author liuqinggang
 */

public class LastLearnBuyInfo {
	/**
	 * log
	 */
	protected Log log = LogFactory.getLog(LastLearnBuyInfo.class);
	private ICusMgr cusMgrService;
	private ISubject subjectService;
	private ISellWay sellWayService;

	public void getLastLearnBuyInfo() {
		log.info("每天取得最高观看次数的学员 end");
		try {
			createSubjectData();//项目文件，供未注册用户使用
			//createSellwayData();//商品文件,供购买用户使用
		} catch (Exception e) {
			log.error("每天取得最高观看次数的学员 错误：", e);
		}
		log.info("每天取得最高观看次数的学员 end");
	}

	/**
	 * 生成项目文件
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void createSubjectData() throws Exception {
		List<Subject> subjects = subjectService.getAllSubject();
		IWebService webService = WebServiceFactory.getVideoService();
		if (subjects != null && subjects.size() > 0) {
			for (Subject subject : subjects) {
				String cusStrs = webService.getWatchRank("1",
						subject.getSubjectId() + "");
				if (!"".equals(cusStrs)) {
					List<WatchRankDTO> learnBuyInfolist = cusMgrService
							.getWatchRankCus(cusStrs.split(","));
					/**
					 * type 1按项目 2按商品 watchId type1时为项目ID，2时为商品id 存放目录 type=1:
					 * static\web\learnBuyInfo\{type}\index.shtml type=2:
					 * static\web\learnBuyInfo\sellway\{type}\index.shtml
					 */
					if (learnBuyInfolist != null && learnBuyInfolist.size() > 0) {
						String staticPath = getClass().getClassLoader()
								.getResource("").getPath();
						String finalpath = "";
						finalpath = URLDecoder.decode(staticPath.substring(0,
								staticPath.indexOf("WEB-INF")))
								+ "static"
								+ File.separator
								+ "web"
								+ File.separator
								+ "lastLearnBuyInfo"
								+ File.separator
								+ subject.getSubjectId()
								+ File.separator;
						FileUtils.clearFile(finalpath);
						FileUtils.createPath(finalpath);
						// 以json格式写到文件中
						FileUtils.writeFile(finalpath + "index.shtml",
								JSONArray.fromObject(learnBuyInfolist)
										.toString(), true);
					}
				}

			}
		}
	}

	/**
	 * 生成商品文件
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public void createSellwayData() throws Exception {
		QuerySellWayCondition querySellWayCondition = new QuerySellWayCondition();
		List<SellWay> sellWays = sellWayService
				.getSellWayList(querySellWayCondition);
		IWebService webService = WebServiceFactory.getVideoService();
		if (sellWays != null && sellWays.size() > 0) {
			for (SellWay sellWay : sellWays) {
				String cusStrs = webService.getWatchRank("2",
						sellWay.getSellId() + "");
				if (!"".equals(cusStrs)) {
					List<WatchRankDTO> learnBuyInfolist = cusMgrService
							.getWatchRankCus(cusStrs.split(","));
					/**
					 * type 1按项目 2按商品 watchId type1时为项目ID，2时为商品id 存放目录 type=1:
					 * static\web\learnBuyInfo\{type}\index.shtml type=2:
					 * static\web\learnBuyInfo\sellway\{type}\index.shtml
					 */
					if (learnBuyInfolist != null && learnBuyInfolist.size() > 0) {
						String staticPath = getClass().getClassLoader()
								.getResource("").getPath();
						String finalpath = "";
						finalpath = URLDecoder.decode(staticPath.substring(0,
								staticPath.indexOf("WEB-INF")))
								+ "static"
								+ File.separator
								+ "web"
								+ File.separator
								+ "lastLearnBuyInfo"
								+ File.separator
								+ "sellway"
								+ File.separator
								+ sellWay.getSellId()
								+ File.separator;
						FileUtils.clearFile(finalpath);
						FileUtils.createPath(finalpath);
						// 以json格式写到文件中
						FileUtils.writeFile(finalpath + "index.shtml",
								JSONArray.fromObject(learnBuyInfolist)
										.toString(), true);
					}
				}

			}
		}
	}

	public ICusMgr getCusMgrService() {
		return cusMgrService;
	}

	public void setCusMgrService(ICusMgr cusMgrService) {
		this.cusMgrService = cusMgrService;
	}

	public ISubject getSubjectService() {
		return subjectService;
	}

	public void setSubjectService(ISubject subjectService) {
		this.subjectService = subjectService;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
	}

}
