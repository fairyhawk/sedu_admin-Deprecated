package com.shangde.common.ibatisExtend;

/**
 * 为ibatis分页创建的方言接口，提供各数据库特殊的分页方言
 * User: guoqiang.liu
 * Date: 2009-9-22
 * Time: 16:44:55
 */
public interface Dialect {
    public boolean supportsLimit();
    public String getPageString(String sql, boolean hasOffset);
    public String getPageString(String sql, int offset, int limit);
}
