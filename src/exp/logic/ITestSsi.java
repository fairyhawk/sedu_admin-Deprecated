package exp.logic;

import exp.vo.TestSsiVO;

import java.util.Map;

import com.shangde.common.vo.PageQuery;
import com.shangde.common.vo.PageResult;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-11-29
 * Time: 16:16:17
 * To change this template use File | Settings | File Templates.
 */
public interface ITestSsi {
    public TestSsiVO getTestSsiVOById(int id);
    public PageResult getAllTestSsiVO(PageQuery pageQuery);
    public Map<Integer,TestSsiVO> getAllTestSsiVOMap(PageQuery pageQuery);
    public void addTestSsiVO(TestSsiVO testSsiVO);
}
