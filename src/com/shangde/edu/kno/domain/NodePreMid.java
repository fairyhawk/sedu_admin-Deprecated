package com.shangde.edu.kno.domain;

import java.io.Serializable;

public class NodePreMid implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    /**
     * 知识树节点Id
     */
    private int ksnId;
    /**
     * 预设项id
     */
    private int preId;
    /**
     * 节点规范命名id
     */
    private String nodeId;
    /**
     * 预设项节点id
     */
    private int preNodeId;
    /**
     * 预设项节点名称
     */
    private String preNodeName;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id; 
    }
        
    public int getKsnId(){
        return ksnId;
    }

    public void setKsnId(int ksnId){
        this.ksnId = ksnId; 
    }
        
    public int getPreId(){
        return preId;
    }

    public void setPreId(int preId){
        this.preId = preId; 
    }
        
        
    public int getPreNodeId(){
        return preNodeId;
    }

    public void setPreNodeId(int preNodeId){
        this.preNodeId = preNodeId; 
    }
        
    public String getPreNodeName(){
        return preNodeName;
    }

    public void setPreNodeName(String preNodeName){
        this.preNodeName = preNodeName; 
    }

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
}
