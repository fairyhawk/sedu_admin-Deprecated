package exp.logic.impl;

import exp.logic.ITestSsi;
import exp.vo.TestSsiVO;

import java.util.Map;

import com.shangde.common.service.BaseService;
import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-11-29
 * Time: 16:18:49
 * To change this template use File | Settings | File Templates.
 */
public class TestSsiImpl extends BaseService implements ITestSsi{
    public TestSsiVO getTestSsiVOById(int id) {
        return simpleDao.getEntity("test_ssi.getTestSsiById",id);
    }

    public PageResult getAllTestSsiVO(PageQuery pageQuery) {
        return simpleDao.getPageResult("test_ssi.getTestSsis","test_ssi.getTestSsisCount", pageQuery);
    }

    public Map<Integer, TestSsiVO> getAllTestSsiVOMap(PageQuery pageQuery) {
        return simpleDao.getForMap("test_ssi.getTestSsis", pageQuery,"id");
    }

    public void addTestSsiVO(TestSsiVO testSsiVO) {
        simpleDao.createEntity("test_ssi.createTestSsi",testSsiVO);
    }
}
