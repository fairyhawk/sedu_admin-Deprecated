package com.shangde.common.ibatisExtend;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
import com.ibatis.sqlmap.engine.scope.StatementScope;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ibatis的SqlExecutor的子类扩展，截获ibatis的sql执行，添加数据库端物理分页
 * User: guoqiang.liu
 * Date: 2009-9-22
 * Time: 16:32:33
 */
public class PageSqlExecutor extends SqlExecutor {
    private static final Log logger = LogFactory.getLog(PageSqlExecutor.class);   

    private Dialect dialect;

    private boolean enableLimit = true;

    public Dialect getDialect() {
        return dialect;
    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public boolean isEnableLimit() {
        return enableLimit;
    }

    public void setEnableLimit(boolean enableLimit) {
        this.enableLimit = enableLimit;
    }

    public void executeQuery(StatementScope statementScope, Connection conn, String sql, Object[] parameters, int skipResults, int maxResults, RowHandlerCallback callback) throws SQLException {
        if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS)
                && supportsLimit()) {
            sql = dialect.getPageString(sql, skipResults, maxResults);
            if(logger.isDebugEnabled()){
                logger.debug(sql);
            }
            skipResults = NO_SKIPPED_RESULTS;
            maxResults = NO_MAXIMUM_RESULTS;
        }
        super.executeQuery(statementScope, conn, sql, parameters, skipResults,   
                maxResults, callback);
    }

    public boolean supportsLimit() {
        return enableLimit && dialect != null && dialect.supportsLimit();
    }
}
