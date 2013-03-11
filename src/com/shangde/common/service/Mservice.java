package com.shangde.common.service;

import com.shangde.common.dao.ISimpleDao;

import java.util.List;

import exp.vo.TestSsiVO;

/**
 * Created by IntelliJ IDEA.
 * User: guoqiang.liu
 * Date: 2009-9-28
 * Time: 11:05:32
 * To change this template use File | Settings | File Templates.
 */
public class Mservice {
    private List<ISimpleDao> daoList;

    public List<ISimpleDao> getDaoList() {
        return daoList;
    }

    public void setDaoList(List<ISimpleDao> daoList) {
        this.daoList = daoList;
    }

    public void testTx(){
        TestSsiVO testSsiVO = new TestSsiVO();
        testSsiVO.setName("测试中文");
        int i=0;
        for(ISimpleDao dao:daoList){
            i++;
            if(i==3){
                throw new RuntimeException("test");
            }
            dao.createEntity("test_ssi.createTestSsi",testSsiVO);
        }
    }
}
