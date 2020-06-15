package com.learn.dao;

import com.learn.domain.User;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface UserDao {

    /**
     * 查询所有用户
     *      同时获取 用户下 所有账户的信息
     * @return
     */
    List<User> findAll();



    /**
     * 根据id查询用户信息
     * @param userId
     * @return
     */
    User findById(Integer userId);


}
