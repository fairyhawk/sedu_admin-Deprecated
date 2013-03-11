package exp.action;

import java.util.ArrayList;
import java.util.List;

import com.shangde.common.action.CommonAction;
import com.shangde.common.util.Result;

import exp.logic.ITestSsi;
import exp.vo.TestQueryConditionVO;
import exp.vo.TestSsiVO;

public class TestStruts2 extends CommonAction {
    private TestQueryConditionVO conditionVO;
    private String message;
    private ITestSsi testSsi;
    private TestSsiVO testSsiVO;
    private List<TestSsiVO> testSsiVOList;
    
    
    public String execute() throws Exception {
        testSsiVO = new TestSsiVO();
        testSsiVO.setId(4);
        setMessage("hello world");
        testSsiVOList = new ArrayList<TestSsiVO>();
        for(int i=0;i<10;i++){
            TestSsiVO testSsiVO = new TestSsiVO();
            testSsiVO.setId(i);
            testSsiVO.setName("vo��ƣ�" + String.valueOf(i));
            testSsiVOList.add(testSsiVO);
        }
        return SUCCESS;
    }

    public String testJson(){
    	try {
    		List<String> sList = new ArrayList<String>();
    		sList.add("testJson");
    		sList.add("testAjaxJson");
    		this.setResult(new Result<List<String>>(true, "返回成功!", null, sList));
			return "jsonTest";
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
    }
    public String list() throws Exception{
        setPageUrlParms();
        setPage(testSsi.getAllTestSsiVO(conditionVO == null?new TestQueryConditionVO():conditionVO));
        return "list";
    }
    


	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ITestSsi getTestSsi() {
        return testSsi;
    }

    public void setTestSsi(ITestSsi testSsi) {
        this.testSsi = testSsi;
    }

    public List<TestSsiVO> getTestSsiVOList() {
        return testSsiVOList;
    }

    public void setTestSsiVOList(List<TestSsiVO> testSsiVOList) {
        this.testSsiVOList = testSsiVOList;
    }

    public TestSsiVO getTestSsiVO() {
        return testSsiVO;
    }

    public void setTestSsiVO(TestSsiVO testSsiVO) {
        this.testSsiVO = testSsiVO;
    }
    
    public String input() throws Exception{
        return "input";
    }

    public String inputSave() throws Exception{
        testSsi.addTestSsiVO(testSsiVO);
        return "input";
    }

    public TestQueryConditionVO getConditionVO() {
        return conditionVO;
    }

    public void setConditionVO(TestQueryConditionVO conditionVO) {
        this.conditionVO = conditionVO;
    }
}
