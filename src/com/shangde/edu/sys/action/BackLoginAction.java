package com.shangde.edu.sys.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.shangde.common.action.CommonAction;
import com.shangde.common.util.AddrIp;
import com.shangde.common.util.MD5;
import com.shangde.edu.sys.domain.Function;
import com.shangde.edu.sys.domain.LoginLog;
import com.shangde.edu.sys.domain.Role;
import com.shangde.edu.sys.domain.User;
import com.shangde.edu.sys.service.ILoginLog;
import com.shangde.edu.sys.service.IUser;

/**
 * 系统登录action
 * 
 * @author guoqiang.liu
 * @version 1.0
 */
public class BackLoginAction extends CommonAction {

    private static final Logger logger = Logger.getLogger(BackLoginAction.class);
    /**
	 * 
	 */
    private static final long serialVersionUID = 6238900344107660927L;
    public static String ERROR_LIMIT_VERIFY = "limitVerifyError";
    /** 登录用户 */
    private User user;
    private IUser userService;
    private ILoginLog loginLogService;
    private String randCode;
    private int rdtType;
    private String loginFrom;
    private List<Function> functionList;
    private List<Function> curUserFunctionList;
    private List<List<Function>> tabUserFunctionList;
    private String randomCode;

    public String getLoginFrom() {
        return loginFrom;
    }

    public void setLoginFrom(String loginFrom) {
        this.loginFrom = loginFrom;
    }

    public int getRdtType() {
        return rdtType;
    }

    public void setRdtType(int rdtType) {
        this.rdtType = rdtType;
    }

    public String loginInput() {
        return "success";
    }

    /**
     * 登陆操作
     * 
     * @return String
     * @throws Exception
     */
    public String login() {
        String returnPage = null;
        try {
            User tempUser = null;
            if (user != null && !user.getLoginName().equals("")) {
                tempUser = userService.getUserByLoginname(user.getLoginName());
                // 用户名长度
                if (user.getLoginName().length() < 6 || user.getLoginName().length() > 20) {
                    addActionError("用户名长度大于6，小于20!");
                    returnPage = "loginError";
                    return returnPage;
                }
                // 密码长度
                if (user.getLoginPwd().length() < 6 || user.getLoginPwd().length() > 20) {
                    addActionError("密码长度大于6，小于20!");
                    returnPage = "loginError";
                    return returnPage;
                }
                // 验证码
                // System.out.print(this.getSession("back_rand_code"));
                if (!randomCode.equals(this.getSession("back_rand_code"))) {
                    addActionError(getText("err.randCode"));
                    returnPage = "loginError";
                    return returnPage;
                }
            }
            if (tempUser != null && user != null
                    && tempUser.getLoginPwd().equals(MD5.getMD5(user.getLoginPwd()))) {
                List<Role> roleList = tempUser.getRoleList();
                functionList = this.userService.getUserFunctionMap(roleList);
                // 用户所属功能权限，用于权限树生成
                curUserFunctionList = this.userService.getUserFunction(roleList);
                tabUserFunctionList = processTabUserFunctionList(new ArrayList<Function>(
                        curUserFunctionList));

                String ip = AddrIp.getIp(servletRequest); // 获取登录人的IP并记录
                LoginLog loginLog = new LoginLog();
                loginLog.setLoginTime(new Date());
                loginLog.setIp(ip);
                loginLog.setUserId(tempUser.getUserId());
                loginLogService.add(loginLog);

                ActionContext.getContext().getSession().clear();
                this.setSession("userfunctionList", functionList);
                this.setSession("curUserFunctionList", curUserFunctionList);
                this.setSession("tabUserFunctionList", tabUserFunctionList);
                this.setSession(CommonAction.SYS_USER_SESSION_NAME, tempUser);
                this.getLogger().info("user:" + user.getLoginName() + " is logined!");
                returnPage = "login";
            } else {
                addActionError(getText("err_login_verify"));
                returnPage = "loginError";
            }
        } catch (Exception e) {
            logger.error("BackLoginAction.login", e);
            return ERROR;
        }
        return returnPage;
    }

    private List<List<Function>> processTabUserFunctionList(List<Function> funcList) {
        List<List<Function>> tabList = new ArrayList<List<Function>>();
        for (int i = 0; i < funcList.size(); i++) {
            Function func = funcList.get(i);
            if (func.getLevel() == 0) {
                List<Function> list = new ArrayList<Function>();
                list.add(func);
                tabList.add(list);
            }
        }
        int count = funcList.size() + 1;
        while (count > funcList.size()) {
            count = funcList.size();
            for (int i = 0; i < funcList.size(); i++) {
                Function func = funcList.get(i);
                for (int j = 0; j < tabList.size(); j++) {
                    List<Function> list = tabList.get(j);
                    for (int k = 0; k < list.size(); k++) {
                        if (list.get(k).getFunctionId() == func.getParentFunctionId()) {
                            list.add(func);
                            funcList.remove(func);
                        }
                    }
                }
            }
        }
        return tabList;
    }

    /**
     * 退出系统操作
     * 
     * @return String
     * @throws Exception
     */
    public String logout() {
        ActionContext.getContext().getSession().clear();
        return "loginOut";
    }

    public String loginThenRedirect() {
        return "welcome";
    }

    public String welcome() {
        return "welcome";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public IUser getUserService() {
        return userService;
    }

    public void setUserService(IUser userService) {
        this.userService = userService;
    }

    public String getRandCode() {
        return randCode;
    }

    public void setRandCode(String randCode) {
        this.randCode = randCode;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
    }

    public List<Function> getCurUserFunctionList() {
        return curUserFunctionList;
    }

    public void setCurUserFunctionList(List<Function> curUserFunctionList) {
        this.curUserFunctionList = curUserFunctionList;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public List<List<Function>> getTabUserFunctionList() {
        return tabUserFunctionList;
    }

    public void setTabUserFunctionList(List<List<Function>> tabUserFunctionList) {
        this.tabUserFunctionList = tabUserFunctionList;
    }

    public void setLoginLogService(ILoginLog loginLogService) {
        this.loginLogService = loginLogService;
    }

}
