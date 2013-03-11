package com.shangde.edu.msg.service;

import java.util.List;
import java.util.Map;

import com.shangde.edu.cou.condition.QueryCourseCondition;
import com.shangde.edu.msg.domain.Message;
import com.shangde.edu.msg.condition.QueryMessageCondition;
import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageResult;


@SuppressWarnings("unchecked")
public class MessageImpl extends BaseService implements IMessage{
    public java.lang.Integer addMessage(Message message) {
    	return simpleDao.createEntity("Message_NS.createMessage",message);
    }

    public void delMessageById(int msgId){
    	//simpleDao.deleteEntity("UserMsg_NS.deleteUserMsgByMsgId", msgId);
        simpleDao.deleteEntity("Message_NS.deleteMessageById",msgId);
    }

    public void updateMessage(Message message) {
        simpleDao.updateEntity("Message_NS.updateMessage",message);
    }

    public Message getMessageById(int msgId) {
        return simpleDao.getEntity("Message_NS.getMessageById",msgId);
    }

    public List<Message> getMessageList(QueryMessageCondition queryMessageCondition) {
        return simpleDao.getForList("Message_NS.getMessageList",queryMessageCondition);
    }
    
    /**
     * @author chenshuai
     * 功能：消息分页管理
     * @param args
     * @param queryMessageCondition
     * @return
     */
    public PageResult getMsgListByConditon(QueryMessageCondition queryMessageCondition) {
		return simpleDao.getPageResult("Message_NS.getMsgListByConditon", "Message_NS.getMsgListByConditonCount", queryMessageCondition);
	}
    
    /**
     * @author cxs
     * 功能：按关键字查询消息
     * @param args
     * @param key
     * @return
     */
    public Message getMessageByKey(String key){
    	return simpleDao.getEntity("Message_NS.getMessageByKey", key);
    }

	public void deleteMessageByCmtIdAndType(Map args){
		// TODO Auto-generated method stub
		simpleDao.deleteEntity("Message_NS.deleteMessageByCmtIdAndType", args);
	}

	public List<Message> getMsgIdByCmtIdAndType(Map args) {
		// TODO Auto-generated method stub
		return simpleDao.getForList("Message_NS.getMsgIdByCmtIdAndType", args);
	}

	public int getMsgIdByRepIdAndType(Map args) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Message_NS.getMsgIdByRepIdAndType", args);
	}

	public int getMsgCountByUserIdAndType(Map args) {
		// TODO Auto-generated method stub
		return simpleDao.getEntity("Message_NS.getMsgCountByUserIdAndType", args);
	}
}
