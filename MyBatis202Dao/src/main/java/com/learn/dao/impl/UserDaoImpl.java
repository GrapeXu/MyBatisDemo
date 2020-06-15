package com.learn.dao.impl;

import com.learn.dao.UserDao;
import com.learn.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SqlSessionFactory factory;

    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法 实现查询列表
        //  参数就是 配置文件中的信息 全限定类名+id方法名
        List<User> users = session.selectList("com.learn.dao.UserDao.findAll");
        //3.释放资源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用方法实现保存
        session.insert("com.learn.dao.UserDao.saveUser", user);//参数就是能获取配置信息的key
        //3.提交事务
        session.commit();;
        //4.释放资源
        session.close();
    }

    public void updateUser(User user) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用方法实现保存
        session.update("com.learn.dao.UserDao.updateUser", user);
        //3.提交事务
        session.commit();;
        //4.释放资源
        session.close();
    }

    public void deleteUser(Integer userId) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用方法实现保存
        session.update("com.learn.dao.UserDao.deleteUser", userId);
        //3.提交事务
        session.commit();;
        //4.释放资源
        session.close();
    }

    public User findById(Integer userId) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法 实现查询列表
        //  参数就是 配置文件中的信息 全限定类名+id方法名
        User user = session.selectOne("com.learn.dao.UserDao.findById", userId);
        //3.释放资源
        session.close();
        return user;
    }

    public List<User> findByName(String username) {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法 实现查询列表
        //  参数就是能获取 配置文件中的信息的key： 全限定类名+id方法名
        List<User> users = session.selectList("com.learn.dao.UserDao.findByName", username);
        //3.释放资源
        session.close();
        return users;
    }

    public int findTotal() {
        //1.根据factory获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession中的方法 实现查询列表
        //  参数就是 配置文件中的信息 全限定类名+id方法名
        Integer count = session.selectOne("com.learn.dao.UserDao.findTotal");
        //3.释放资源
        session.close();
        return count;
    }
}
