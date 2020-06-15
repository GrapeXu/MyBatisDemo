package com.learn;

import com.learn.dao.UserDao;
import com.learn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private SqlSessionFactory factory;
    private UserDao userDao;

    @Before //用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输出流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        session = factory.openSession();
        //4.获取dao的代理对象
        userDao = session.getMapper(UserDao.class);
    }

    @After //用于在测试方法之后执行
    public void destroy() throws Exception  {
        //事务提交
        session.commit();
        //6.释放资源
        session.close();
        in.close();
    }



    @Test
    public void testFirstLevelCache() {
        User user1 = userDao.findById(41);
        System.out.println(user1);

        //关闭 释放缓存
        session.close();
        //再次开启
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
        //此方法可以清空缓存
//        session.clearCache();

        User user2 = userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    @Test
    public void testClearCache() {
        User user1 = userDao.findById(41);
        System.out.println(user1);

        //更新用户信息
        user1.setUsername("update user clear cache");
        user1.setAddress("xxxxx市");
        userDao.updateUser(user1);

        User user2 = userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1 == user2);//false
    }
}
