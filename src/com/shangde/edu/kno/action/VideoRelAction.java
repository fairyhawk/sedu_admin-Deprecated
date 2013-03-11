package com.shangde.edu.kno.action;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.cou.domain.Course;
import com.shangde.edu.cou.domain.SellWay;
import com.shangde.edu.cou.service.ICourse;
import com.shangde.edu.cou.service.ISellWay;
import com.shangde.edu.kno.domain.SysNode;
import com.shangde.edu.kno.domain.VideoRel;
import com.shangde.edu.kno.service.ISysNode;
import com.shangde.edu.kno.service.IVideoRel;
import com.shangde.edu.res.domain.Vedio;
import com.shangde.edu.res.service.IVedio;

public class VideoRelAction extends CommonAction {
	
	private IVideoRel videoRelService;
	private List<VideoRel> videoRelList=new ArrayList <VideoRel>();
	private int ksnId;
	private int vrId;
	private int voId;
	private List <SysNode> sysNodeList=new ArrayList <SysNode>();
	private List <SysNode> sysNodeList1=new ArrayList <SysNode>();
	/**
	 * 公共方法所需变量
	 * 开始
	 * @return
	 */
	private SysNode sysNode;
	private Course course;
	private SellWay	sellWay;
	private Vedio vedio;
	private IVedio vedioService;
	private ISysNode sysNodeService;
	private ICourse courseService;
	private ISellWay sellWayService;
	/**
	 * 公共方法所需变量结束
	 */
	
	/**
	 * @author 王超
	 * 转到视频知识点关联列表
	 */
	public String toVideoRelList(){
		try {
			int a=ksnId;
			while(a!=-1){
				sysNode=sysNodeService.getSysNodeById(a);
				a=sysNode.getParentId();
				sysNodeList1.add(sysNode);
			}
			for(int i=sysNodeList1.size();i>0;i--){
				sysNodeList.add(sysNodeList1.get(i-1));
			}
			videoRelList=videoRelService.getVideoRelListByKsnId(ksnId);
			return "toVideoRelList";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 删除视频知识点关联
	 * @return
	 */
	public String delVideoRel(){
		try {
			videoRelService.delVideoRelById(vrId);
			this.setResult(new Result(true,"","",null));
			return "json";
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
	}
	/**
	 * @author 王超
	 * 转到视频预览页面
	 * @return
	 */
	public String videoPreview(){
		ksnId=Integer.parseInt(servletRequest.getParameter("ksnId"));
		vedio=vedioService.getVedioById(voId);
		return "videoPreview";
	}
	/**
	 * @author 王超
	 * 增加视频关联公共方法
	 * @param ksnId  知识树节点id
	 * @param courseId 课程id
	 * @param sellId  售卖方式id
	 * @param voId 视频id 
	 */
	public void addVideoRel(int ksnId,int courseId,int sellId,int voId){
		sysNode=sysNodeService.getSysNodeById(ksnId);
		sellWay=sellWayService.getSellWayById(sellId);
		course=courseService.getCourseById(courseId);
		vedio=vedioService.getVedioById(voId);
		VideoRel videoRel=new VideoRel();
		videoRel.setCourseId(course.getCourseId());
		videoRel.setCourseName(course.getTitle());
		videoRel.setKsnId(sysNode.getKsnId());
		videoRel.setNodeId(sysNode.getNodeId());
		videoRel.setTitle(sellWay.getSellName());
		videoRel.setVoId(voId);
		videoRel.setVoName(vedio.getVoName());
		videoRelService.addVideoRel(videoRel);
	}
	
	public List<VideoRel> getVideoRelList() {
		return videoRelList;
	}
	public void setVideoRelList(List<VideoRel> videoRelList) {
		this.videoRelList = videoRelList;
	}
	public int getKsnId() {
		return ksnId;
	}
	public void setKsnId(int ksnId) {
		this.ksnId = ksnId;
	}

	public int getVrId() {
		return vrId;
	}

	public void setVrId(int vrId) {
		this.vrId = vrId;
	}

	public IVideoRel getVideoRelService() {
		return videoRelService;
	}

	public void setVideoRelService(IVideoRel videoRelService) {
		this.videoRelService = videoRelService;
	}

	public SysNode getSysNode() {
		return sysNode;
	}

	public void setSysNode(SysNode sysNode) {
		this.sysNode = sysNode;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public SellWay getSellWay() {
		return sellWay;
	}

	public void setSellWay(SellWay sellWay) {
		this.sellWay = sellWay;
	}

	public ISysNode getSysNodeService() {
		return sysNodeService;
	}

	public void setSysNodeService(ISysNode sysNodeService) {
		this.sysNodeService = sysNodeService;
	}

	public ICourse getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourse courseService) {
		this.courseService = courseService;
	}

	public ISellWay getSellWayService() {
		return sellWayService;
	}

	public void setSellWayService(ISellWay sellWayService) {
		this.sellWayService = sellWayService;
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

	public List<SysNode> getSysNodeList() {
		return sysNodeList;
	}

	public void setSysNodeList(List<SysNode> sysNodeList) {
		this.sysNodeList = sysNodeList;
	}
	public int getVoId() {
		return voId;
	}
	public void setVoId(int voId) {
		this.voId = voId;
	}
}
