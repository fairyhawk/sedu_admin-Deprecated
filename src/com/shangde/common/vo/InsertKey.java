package com.shangde.common.vo;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: guoqiang.liu
 * Date: 2009-11-28
 * Time: 10:54:57
 * To change this template use File | Settings | File Templates.
 */
public class InsertKey {
    private int returnKey;
    private Object param;
    private Map paramMap;

    public int getReturnKey() {
        return returnKey;
    }

    public void setReturnKey(int returnKey) {
        this.returnKey = returnKey;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public Map getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map paramMap) {
        this.paramMap = paramMap;
    }
}
