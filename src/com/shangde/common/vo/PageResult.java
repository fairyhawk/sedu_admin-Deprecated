package com.shangde.common.vo;

import java.util.List;
import java.util.ArrayList;

/**
 * 分页查询结果vo类
 * User: guoqiang.liu
 * Date: 2008-12-22
 * Time: 18:29:29
 * 
 */
@SuppressWarnings("unchecked")
public class PageResult extends PageQuery {
	
	/**
	 * 总记录数
	 */
    private int totalRecord;
    
    /**
     * 每页的集合
     */
    private List pageResult;
    
    /**
     * 每页显示数量
     */
    private int pageNumPY=10;

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalRecord/getPageSize()*getPageSize() == totalRecord?totalRecord/getPageSize():totalRecord/getPageSize()+1;
    }

    public <T> List<T> getPageResult() {
        return pageResult;
    }

    public void setPageResult(List pageResult) {
        this.pageResult = pageResult;
    }

    public boolean isFirst(){
        return getCurrentPage()<=1;
    }

    public boolean isLast(){
        return getCurrentPage()>=getTotalPage();
    }

    public List<Integer> getPageNums(){
        List<Integer> returnList = new ArrayList();
        int startNum = getCurrentPage()-pageNumPY<1?1:getCurrentPage()-pageNumPY;
        int endNum  = getCurrentPage()+pageNumPY>getTotalPage()?getTotalPage():getCurrentPage()+pageNumPY;
        for(int i=startNum;i<=endNum;i++){
            returnList.add(i);
        }
        return returnList;
    }
}
