package com.learn.dao;

import com.learn.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*

    在mybatis中针对CRUD一共有四个注解
    @SELECT
    @INSERT
    @UPDATE
    @DELETE

 */
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select(value = "select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert(value = "insert into user(username, address, sex, birthday) values(#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    /**
     *  更新用户
     * @param user
     */
    @Update(value = "update user set username=#{username}, sex=#{sex}, birthday=#{birthday}, address=#{address} where id=#{id} ")
    void UpdateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    @Delete(value = "delete from user where id=#{id}")
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select(value = "select * from user where id=#{id}")
    User findById(Integer userId);

    /**
     * 根据用户名称模糊查询
     * @param username
     * @return
     */
//    @Select("select * from user where username like #{username}")
    @Select("select * from user where username like '%${value}%' ")
    List<User> findUserByName(String username);

    @Select("select count(*) from user")
    int findTotalUser();
}












