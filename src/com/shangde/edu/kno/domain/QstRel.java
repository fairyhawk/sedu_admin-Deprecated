package com.shangde.edu.kno.domain;

import java.io.Serializable;

public class QstRel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int qrId;
    /**
     * 知识树节点id
     */
    private int ksnId;
    /**
     * 节点规范命名id
     */
    private String nodeId;
    /**
     * 试题内容
     */
    private String qstContent;
    /**
     * 试题类型
     */
    private int qstType;
    /**
     * 试题id
     */
    
    private int qstId;
        
    public int getQrId(){
        return qrId;
    }

    public void setQrId(int qrId){
        this.qrId = qrId; 
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
        
    public String getQstContent(){
        return qstContent;
    }

    public void setQstContent(String qstContent){
        this.qstContent = qstContent; 
    }
        
    public int getQstType(){
        return qstType;
    }

    public void setQstType(int qstType){
        this.qstType = qstType; 
    }

	public int getQstId() {
		return qstId;
	}

	public void setQstId(int qstId) {
		this.qstId = qstId;
	}
}
