package com.shangde.edu.cms.service;

import java.util.List;
import com.shangde.edu.cms.domain.Commentfrom;
import com.shangde.edu.cms.condition.QueryCommentfromCondition;
import com.shangde.common.service.BaseService;

/**
 * Commentfrom对象操作实现类
 * User: guoqiang.liu
 * Date: 2010-07-14
 */
@SuppressWarnings("unchecked")
public class CommentfromImpl extends BaseService implements ICommentfrom{
    public java.lang.Integer addCommentfrom(Commentfrom commentfrom) {
return simpleDao.createEntity("Commentfrom_NS.createCommentfrom",commentfrom);
    }

    public void delCommentfromById(int cfId){
        simpleDao.deleteEntity("Commentfrom_NS.deleteCommentfromById",cfId);
    }

    public void updateCommentfrom(Commentfrom commentfrom) {
        simpleDao.updateEntity("Commentfrom_NS.updateCommentfrom",commentfrom);
    }

    public Commentfrom getCommentfromById(int cfId) {
        return simpleDao.getEntity("Commentfrom_NS.getCommentfromById",cfId);
    }

    public List<Commentfrom> getCommentfromList(QueryCommentfromCondition queryCommentfromCondition) {
        return simpleDao.getForList("Commentfrom_NS.getCommentfromList",queryCommentfromCondition);
    }
}
