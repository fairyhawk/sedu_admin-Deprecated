package com.shangde.edu.exam.service;

import com.shangde.edu.exam.domain.Options;

/**
 * Options 选项接口
 * 试题选项接口
 * User: guoqiang.liu
 * Date: 2010-07-30
 */
public interface IOptions {
    /**
     * 添加选项
     * @param options 添加选项
     * @return id
     */
    public java.lang.Integer addOptions(Options options);

    /**
     * 删除选项
     * @param optId 选项ID
     */
    public void delOptionsById(int optId);

    /**
     * 更新选项
     * @param options 要更新的选项
     */
    public void updateOptions(Options options);

}