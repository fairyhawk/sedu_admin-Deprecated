package com.shangde.edu.finance.service;

import java.util.List;
import com.shangde.edu.finance.domain.Account;
import com.shangde.edu.finance.condition.QueryAccountCondition;


public interface IAccount {
    /**
     * 添加Account
     * @param account Account
     * @return id
     */
    public java.lang.Integer addAccount(Account account);

    /**
     * 根据id删除
     * @param id
     */
    public void delAccountById(int id);

    /**
     * 更新
     * @param Account account
     */
    public void updateAccount(Account account);

    /**
     * @param id
     * @return Account
     */
    public Account getAccountById(int id);

    /**
     * 
     * @param queryAccountCondition
     * @return List<Account>集合
     */
    public List<Account> getAccountList(QueryAccountCondition queryAccountCondition);
}