package com.shangde.common.task;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangde.common.util.FileUtils;
import com.shangde.common.util.json.DateJsonValueProcessor;
import com.shangde.edu.vst.domain.SellWay;
import com.shangde.edu.vst.domain.VideoCusInfo;
import com.shangde.edu.vst.service.Ihdata;

/**
 * 每间隔12小时取得，按商品存放
 * 
 * @author HQL
 */

public class CusPackAgeInfoTask {
	/**
	 * log
	 */
	protected Log log = LogFactory.getLog(CusPackAgeInfoTask.class);
	
	private static final String SELL_NEW_PATH = "web" + File.separator + "sellNew";
	
	private static final String SELL_CLICK_PATH = "web" + File.separator + "sellClick";
	
	private Ihdata hadataService;//hadata数据服务
	
	/**
	 * 获取最新商品存放路径
	 * @return
	 */
	private String getSellNewPath(){
		String staticPath = getClass().getClassLoader().getResource("").getPath();
		String sellNewPath = URLDecoder.decode(staticPath.substring(0, staticPath.indexOf("WEB-INF")))
				+ "static" + File.separator + SELL_NEW_PATH	+ File.separator;
		return sellNewPath;
	}
	/**
	 * 获取用户点击商品存放路径
	 * @return
	 */
	private String getSellClickPath(){
		String staticPath = getClass().getClassLoader().getResource("").getPath();
		String sellClickPath = URLDecoder.decode(staticPath.substring(0, staticPath.indexOf("WEB-INF")))
				+ "static" + File.separator + SELL_CLICK_PATH	+ File.separator;
		return sellClickPath;
	}
	

	public void getSellBuyInfo() {
		log.info("create sellResult start......");
		//查询所有商品
		List<SellWay> sellList = hadataService.getAllSell();
		for(SellWay var : sellList){
			//根据商品ID获取最新商品
			List<VideoCusInfo> news = hadataService.getAllPackageCourseTop(var.getSellId());
			
			FileUtils.createPath(getSellNewPath() + var.getSellId());
			try {
				FileUtils.writeFile(getSellNewPath()
						+ (var.getSellId() + "") + File.separator
						+ "index_1.shtml", JSONArray.fromObject(news)
						.toString(), true);
			} catch (IOException e) {
				log.error("create sellResult exception :", e);
			}
			news.clear();
			
			//根据商品ID获取点击率商品
			List<VideoCusInfo> tops = hadataService.getAllPackageCourseClick(var.getSellId());
			
			FileUtils.createPath(getSellClickPath() + var.getSellId());
			try {
				FileUtils.writeFile(getSellClickPath()
						+ (var.getSellId() + "") + File.separator
						+ "index_1.shtml", JSONArray.fromObject(tops)
						.toString(), true);
			} catch (IOException e) {
				log.error("create sellResult exception :", e);
			}
			tops.clear();
			
		}
		log.info("create sellResult end......");
		
	}

	public Ihdata getHadataService() {
		return hadataService;
	}

	public void setHadataService(Ihdata hadataService) {
		this.hadataService = hadataService;
	}
	
}
