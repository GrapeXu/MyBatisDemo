package com.learn.dao;

import com.learn.domain.Account;
import com.learn.domain.AccountUser;

import java.util.List;

public interface AccountDao {

    /**
     * 查询所有账户 同时还要获取到当前账户的所属用户信息
     *      select u.*, a.id as aid, a.uid, a.money from account a, user u where u.id=a.uid;
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户，
     *      并且带有用户名称和地址信息
     *      账户信息+ username + address 需要封装个类啊
     * @return
     */
    List<AccountUser> findAllAccount();
}
