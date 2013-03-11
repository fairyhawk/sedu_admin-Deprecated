package com.shangde.edu.msg.service;

import java.util.List;
import java.util.Map;

import com.shangde.common.vo.PageResult;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.condition.QueryMessageCondition;

/**
 *消息接口管理
 * 
 * 
 */
public interface IMessage {
	/**
	 * 添加消息
	 * @param message
	 * 
	 */
    public java.lang.Integer addMessage(Message message);

    /**
     * 根据msgId删除
     * @param msgId
     * 
     */
    public void delMessageById(int msgId);

    /**
     * 更新消息
     * @param message
     * 
     */
    public void updateMessage(Message message);
    /**
     * 根据msgId查询
     * @param msgId
     */
    public Message getMessageById(int msgId);

    /**
     * 
     * 根据查询条件查询
     * @param queryMessageCondition
     */
    public List<Message> getMessageList(QueryMessageCondition queryMessageCondition);
    
    /**
     * @author chenshuai
     * 功能：消息分页管理
     * @param args
     * @param queryMessageCondition
     * @return
     */
    public PageResult getMsgListByConditon(QueryMessageCondition queryMessageCondition);
    
    /**
     * @author cxs
     * 功能：按关键字查询消息
     * @param args
     * @param key
     * @return
     */
    public Message getMessageByKey(String key);
    /**
     * @author zhangjuqiang
     * 功能：按用戶意見ID刪除信息
     * @prama int
     * @prama id
     */
    public void deleteMessageByCmtIdAndType(Map args);
    /**
     * @author zhangjuqiang
     * 功能：根據內容ID查找消息ID,查詢用戶提示信息表時用。
     * 
     */
    public List<Message> getMsgIdByCmtIdAndType(Map args);
    
    public int getMsgIdByRepIdAndType(Map args);
    /**
     * @author zhangjuqiang
     * 功能：根据用户ID和版块来源查询信息总数
     * @param receiverId:接收用户的ID
     * @param type:来源版块标识
     */
    public int getMsgCountByUserIdAndType(Map args);
}