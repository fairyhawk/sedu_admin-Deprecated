package com.shangde.common.task;


import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.shangde.common.util.MD5;
import com.shangde.common.vo.PageResult;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.res.condition.QueryVedioCondition;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.service.IVideoTaskService;

public class VideoCCpicTask {
	//分页大小，每次从库中取一千条数据进行操作
	private static final Integer PAGESIZE = 100;
	//cc视频key与id
	private final static String CC_USER_ID = "25CCD0665D668BCE";
	private final static String SALT = "0qclEXiEOoBssCoLDOAQYXTSephEq7IG";
	
	private static final Logger logger = Logger.getLogger(VideoCCpicTask.class);
	
	
	private IVideoTaskService videoTaskService;

	public IVideoTaskService getVideoTaskService() {
		return videoTaskService;
	}

	public void setVideoTaskService(IVideoTaskService videoTaskService) {
		this.videoTaskService = videoTaskService;
	}
	/**
	 * 导入cc视频URL
	 * @throws Exception
	 */
	public synchronized void importVideoPic(){
		try{
			logger.info("importVideoPic_begin");
			Integer count = videoTaskService.getNotPicListVedioCount();
			if(count > 0){
				QueryVedioCondition condition = new QueryVedioCondition();
				condition.setPageSize(PAGESIZE);
				int currentPage = 1;
				int totalPage = count / PAGESIZE + 1;
				if(totalPage >= currentPage){
					for(int i=1;i<=totalPage;i++){
						PageResult page = videoTaskService.getNotPicListVedio(condition);
						if(page != null){
							List<Vedio> vedioList = page.getPageResult();
							if(vedioList != null && vedioList.size() > 0){
								for (Vedio vedio : vedioList) {
									String ccKey = vedio.getCcUrl().trim();
									String url = getCCImageURL(ccKey);
									if(url != null && url.trim().length() > 0){
										vedio.setCcSmallPic(url);
										videoTaskService.updatevideoPicByid(vedio);
									}
								}
							}
						}
						currentPage++;
						//等待60秒后再抓取,防止抓取太大，cc拒绝
						this.wait(60000);
						System.out.println("wait 1 min");
					}
				}
			}
		}catch(Exception e){
			logger.error("VideoCCpicTask.importVideoPic", e);
		}
	}
	
	/**
	 * 导入课程图片
	 */
	public void importCoursePic(){
		try{
			logger.info("importCoursePic_begin");
			List<Course> courses = videoTaskService.getNotPicListCourse();
			if(courses != null && courses.size() > 0){
				for (Course course : courses) {
					if(course != null){
						String picUrl = videoTaskService.getCoursePicViaVedioPic(course.getCourseId());
						if(picUrl != null && picUrl.trim().length() > 0){
							course.setVedioPicUrl(picUrl);
							videoTaskService.updateCoursePicByid(course);
						}
					}
				}
			}
		}catch(Exception e){
			logger.error("VideoCCpicTask.importCoursePic", e);
		}
	}
	
	private String getCCImageURL(String vId) throws Exception{
		HttpClient client = new HttpClient();
		String url="http://spark.bokecc.com/api/video";
		Map<String,String> map = new HashMap<String, String>();
		map.put("userid", CC_USER_ID);
		map.put("videoid", vId);
		long time = System.currentTimeMillis();
		String parameterString = createHashedQueryString(map,time,SALT);
		url = url + "?" + parameterString;
		GetMethod method = new GetMethod(url);
		client.executeMethod(method);
		StringReader sr = new StringReader(method.getResponseBodyAsString());
		InputSource source = new InputSource(sr); 
	    //创建一个新的SAXBuilder 
	    SAXBuilder sb = new SAXBuilder(); 
	    //通过输入源构造一个Document 
        Document doc = sb.build(source); 
        //取的根元素 
        Element root = doc.getRootElement(); 
        //得到根元素所有子元素的集合 
        List jiedian = root.getChildren(); 
        Element et = null; 
        //图片地址
        String imageURL = "";
        for(int i=0,j = jiedian.size();i<j;i++){ 
            et = (Element) jiedian.get(i);//循环依次得到子元素 
            if(et.getName().equals("image")){
            	imageURL = et.getText();
            } 
        }
		return imageURL;
	}
	
	/**
	 * 功能：将一个Map按照Key字母升序构成一个QueryString. 并且加入时间混淆的hash串。
	 * 
	 * @param queryMap
	 *            query内容
	 * @param time
	 *            加密时候，为当前时间；解密时，为从querystring得到的时间；
	 * @param salt
	 *            加密salt
	 * @return
	 */
	private String createHashedQueryString(Map<String, String> queryMap,
			long time, String salt) {

		Map<String, String> map = new TreeMap<String, String>(queryMap);
		String qs = createQueryString(map);
		time = time / 1000;
		String hash=MD5.getMD5(String.format("%s&time=%d&salt=%s", qs,time, salt));
		String htqs = String.format("%s&time=%d&hash=%s", qs, time, hash);
		return htqs;
	}

	/**
	 * 功能：用一个Map生成一个QueryString，参数的顺序不可预知。
	 * 
	 * @param queryString
	 * @return
	 */
	private String createQueryString(Map<String, String> queryMap) {

		if (queryMap == null) {
			return null;
		}

		try {
			StringBuilder sb = new StringBuilder();
			for (Map.Entry<String, String> entry : queryMap.entrySet()) {
				if (entry.getValue() == null) {
					continue;
				}
				String key = entry.getKey().trim();
				String value = URLEncoder.encode(entry.getValue().trim(),
						"utf-8");
				sb.append(String.format("%s=%s&", key, value));
			}
			return sb.substring(0, sb.length() - 1);
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
