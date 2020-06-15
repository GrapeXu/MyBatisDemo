package com.learn.dao;

import com.learn.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface UserDao {

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

}
