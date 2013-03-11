package com.shangde.common.vo;

/**
 * 分页查询条件vo类
 * User: guoqiang.liu
 * Date: 2008-12-22
 * Time: 18:19:33
 * To change this template use File | Settings | File Templates.
 */
public class PageQuery {
    private int currentPage=1;
    private int pageSize=10;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
    	this.currentPage = currentPage;    		
    }
    public void setCurrentPage(String currentPage) {
        try {
			this.currentPage = Integer.parseInt(currentPage);
		} catch (NumberFormatException e) {
			this.currentPage = 1;
		}
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    
    /**
     * 获取起始记录数
     * @return
     */
    public int getStartRecord(){
        return (currentPage-1)*pageSize;
    }
    
    /**
     * 获得结束记录数
     * @return
     */
    public int getEndRecord(){
        return getStartRecord()+pageSize-1;
    }
}
