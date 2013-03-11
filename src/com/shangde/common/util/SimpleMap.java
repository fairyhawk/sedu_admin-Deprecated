package com.shangde.common.util;

import java.util.Map;
import java.util.HashMap;

/**
 * 简单包装HashMap，提供可变参数的构造函数
 * User: guoqiang.liu
 * Date: 2009-9-24
 * Time: 9:23:17
 */
@SuppressWarnings("unchecked")
public class SimpleMap<K,V> extends HashMap implements Map {
    private static final long serialVersionUID = 9033295326343845240L;

    public SimpleMap(Object ... parms) {
        super();
        if(parms.length%2 != 0){
            throw new RuntimeException("parms's length must be even number!");
        }
        for(int i=0;i<parms.length;i++){
            put(parms[i],parms[++i]);
        }
    }
}
