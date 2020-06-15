package com.learn.dao;

import com.learn.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/*

    在mybatis中针对CRUD一共有四个注解
    @SELECT
    @INSERT
    @UPDATE
    @DELETE

 */
@CacheNamespace(blocking = true)//开启二级缓存
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select(value = "select * from user")
    @Results( id = "userMap",
            value = {
                @Result(id =true, column = "id", property = "id"),
                @Result(column = "username", property = "username"),
                @Result(column = "address", property = "address"),
                @Result(column = "sex", property = "sex"),
                @Result(column = "birthday", property = "birthday"),
                @Result(property = "accounts", column = "id",
                            many = @Many(select = "com.learn.dao.AccountDao.findAccountByUid",
                            fetchType = FetchType.LAZY)
                ),
            }
    )
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select(value = "select * from user where id=#{id}")
    @ResultMap(value = {"userMap"})
    User findById(Integer userId);

    /**
     * 根据用户名称模糊查询
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username}")
//    @Select("select * from user where username like '%${value}%' ")
    @ResultMap(value = {"userMap"})
    List<User> findUserByName(String username);

}












