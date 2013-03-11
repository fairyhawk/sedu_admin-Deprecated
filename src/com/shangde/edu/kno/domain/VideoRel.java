package com.shangde.edu.kno.domain;

import java.io.Serializable;

public class VideoRel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int vrId;
    /**
     * 知识树节点id
     */
    private int ksnId;
    /**
     * 知识树节点规范命名Id
     */
    private String nodeId;
    /**
     * 视频id
     */
    private int voId;
    /**
     * 视频名称
     */
    private String voName;
    /**
     * 课程Id
     */
    private int courseId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 商品名称
     */
    private String title;
        
    public int getVrId(){
        return vrId;
    }

    public void setVrId(int vrId){
        this.vrId = vrId; 
    }
        
    public int getKsnId(){
        return ksnId;
    }

    public void setKsnId(int ksnId){
        this.ksnId = ksnId; 
    }
        
    public String getNodeId(){
        return nodeId;
    }

    public void setNodeId(String nodeId){
        this.nodeId = nodeId; 
    }
        
    public int getVoId(){
        return voId;
    }

    public void setVoId(int voId){
        this.voId = voId; 
    }
        
    public String getVoName(){
        return voName;
    }

    public void setVoName(String voName){
        this.voName = voName; 
    }
        
    public int getCourseId(){
        return courseId;
    }

    public void setCourseId(int courseId){
        this.courseId = courseId; 
    }
        
    public String getCourseName(){
        return courseName;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName; 
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
