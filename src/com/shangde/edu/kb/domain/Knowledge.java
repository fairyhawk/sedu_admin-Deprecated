package com.shangde.edu.kb.domain;

import java.io.Serializable;

public class Knowledge implements Serializable{
	//父id
    private int kId;
    //子id
    private int kPId;
    //专业id
    private int plId;
    //上级索引
    private String upIndex;
    //知识结构中的名称　
    private String kName;
    //索引
    private String kIndex;
    //时间版本
    private int kVersion;
    //级别　代表知识点的级别
    private int kLevel;
    //创建时间
    private java.util.Date kCreateTime;
    //状态
    private int kStuts;
    //说明
    private String kNote;
    //科目id
    private int scId;
    //排序的序号
    private int kSort;
    //科目包含章数
	private int zSum;
    //科目包含节数
	private int jSum;
    //科目知识单元数
	private int zsSum;
    //科目1级知识单元数
	private int zsSum1;
    //科目2级知识单元数
	private int zsSum2;
    //科目3级知识单元数
	private int zsSum3;
        
    
    
    public int getKId(){
        return kId;
    }

    public void setKId(int kId){
        this.kId = kId; 
    }
        
    public int getKPId(){
        return kPId;
    }

    public void setKPId(int kPId){
        this.kPId = kPId; 
    }
        
    public int getPlId(){
        return plId;
    }

    public void setPlId(int plId){
        this.plId = plId; 
    }
        
        
    public String getKName(){
        return kName;
    }

    public void setKName(String kName){
        this.kName = kName; 
    }
        
    public String getKIndex(){
        return kIndex;
    }

    public void setKIndex(String kIndex){
        this.kIndex = kIndex; 
    }
        
    public int getKVersion(){
        return kVersion;
    }

    public void setKVersion(int kVersion){
        this.kVersion = kVersion; 
    }
        
    public int getKLevel(){
        return kLevel;
    }

    public void setKLevel(int kLevel){
        this.kLevel = kLevel; 
    }
        
    public java.util.Date getKCreateTime(){
        return kCreateTime;
    }

    public void setKCreateTime(java.util.Date kCreateTime){
        this.kCreateTime = kCreateTime; 
    }
        
    public int getKStuts(){
        return kStuts;
    }

    public void setKStuts(int kStuts){
        this.kStuts = kStuts; 
    }
        
    public String getKNote(){
        return kNote;
    }

    public void setKNote(String kNote){
        this.kNote = kNote; 
    }

	public int getScId() {
		return scId;
	}

	public void setScId(int scId) {
		this.scId = scId;
	}

	public String getUpIndex() {
		return upIndex;
	}

	public void setUpIndex(String upIndex) {
		this.upIndex = upIndex;
	}
	public int getKSort() {
		return kSort;
	}

	public void setKSort(int sort) {
		kSort = sort;
	}

	public int getZSum() {
		return zSum;
	}

	public void setZSum(int sum) {
		zSum = sum;
	}

	public int getJSum() {
		return jSum;
	}

	public void setJSum(int sum) {
		jSum = sum;
	}

	public int getZsSum() {
		return zsSum;
	}

	public void setZsSum(int zsSum) {
		this.zsSum = zsSum;
	}

	public int getZsSum1() {
		return zsSum1;
	}

	public void setZsSum1(int zsSum1) {
		this.zsSum1 = zsSum1;
	}

	public int getZsSum2() {
		return zsSum2;
	}

	public void setZsSum2(int zsSum2) {
		this.zsSum2 = zsSum2;
	}

	public int getZsSum3() {
		return zsSum3;
	}

	public void setZsSum3(int zsSum3) {
		this.zsSum3 = zsSum3;
	}
	
	public String getIndex(String index){
		//截取最后的两位数字
		int lnumber;
		//截取除去后面两位剩下的数字
		String lwnumber="";
		//差开最后两位数来做判断
		String oneIndex="";
		String twoIndex="";
	
		lnumber=new Integer(index.substring(index.length()-2).trim());
		
		lwnumber=index.substring(0,index.length()-2);
		
		oneIndex=index.substring(index.length()-2,index.length()-1);
		
		twoIndex=index.substring(index.length()-1);
			if(oneIndex.equals("0")&&!"9".equals(twoIndex)){
				index=lwnumber+oneIndex+(lnumber+1);
			}else{
				index=lwnumber+(lnumber+1);
			}
		
		
		return index;
	}

	
}
