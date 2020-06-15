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

public class SecondLevelCacheTest {

    private InputStream in;
    private SqlSessionFactory factory;


    @Before //用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输出流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);

    }

    @After //用于在测试方法之后执行
    public void destroy() throws Exception  {

        in.close();
    }



    @Test
    public void testFirstLevelCache() {
        SqlSession session1 = factory.openSession();
        UserDao userDao1 = session1.getMapper(UserDao.class);
        User user1 = userDao1.findById(41);
        System.out.println(user1);

        session1.close();//一级缓存消失

        SqlSession session2 = factory.openSession();
        UserDao userDao2 = session2.getMapper(UserDao.class);
        User user2 = userDao2.findById(41);
        System.out.println(user2);

        session2.close();

        System.out.println(user1 == user2);
    }

}
