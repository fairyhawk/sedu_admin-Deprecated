/**
 * ClassName  SubjectAction
 *
 * History
 * Create User: liuqinggang
 * Create Date: May 17, 2011
 * Update User:
 * Update Date:
 */
package com.shangde.edu.sys.action;

import org.apache.log4j.Logger;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;
import com.shangde.edu.sys.condition.QueryNoticeCondition;
import com.shangde.edu.sys.domain.Notice;
import com.shangde.edu.sys.service.INotice;

/**
 * 
 * @author liuqinggang
 */

public class NoticeAction extends CommonAction {

    private static Logger logger = Logger.getLogger(NoticeAction.class);
    private QueryNoticeCondition queryNoticeCondition;
    private INotice noticeService;// 学科服务

    public String getNoticeInfo() {
        try {
            Notice notice = noticeService.getNoticeBySubject(getQueryNoticeCondition());// ;subjectService.getSubjectById(noticeCondition.getSubjectId());
            if (null == notice) {
                setResult(new Result(false, "", null, null));
            } else {
                String str=notice.getContent();
                 /*   str = str.replaceAll("&", "&amp;");
                str = str.replaceAll("<", "&lt;");
                str = str.replaceAll(">", "&gt;");
                str = str.replaceAll("\"", "&quot;");*/
                str = str.replaceAll("\"", "'");
                System.out.println("str:"
                        + str);
                String regex = "\\s*|(\r\n)";
                System.out.println(str.replaceAll(regex, ""));
                setResult(new Result(false, str, null, null));
            }
        } catch (Exception e) {
            logger.error("取得公告信息错误！", e);
        }
        return "json";
    }

    public INotice getNoticeService() {
        return noticeService;
    }
    public void setNoticeService(INotice noticeService) {
        this.noticeService = noticeService;
    }

    public QueryNoticeCondition getQueryNoticeCondition() {
        if (queryNoticeCondition ==null){
            queryNoticeCondition= new QueryNoticeCondition();
        }
        return queryNoticeCondition;
    }

    public void setQueryNoticeCondition(QueryNoticeCondition queryNoticeCondition) {
        this.queryNoticeCondition = queryNoticeCondition;
    }

}
