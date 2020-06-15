package com.learn.test;

import com.learn.dao.UserDao;
import com.learn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("annotation");
        user.setAddress("xxxx市");

        userDao.saveUser(user);

    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(60);
        user.setUsername("anno update");
        user.setAddress("广州");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.UpdateUser(user);
    }

    @Test
    public void testDelete() {
        userDao.deleteUser(60);
    }

    @Test
    public void testFindOne() {
        User user = userDao.findById(50);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
//        List<User> users = userDao.findUserByName("%王%");
        List<User> users = userDao.findUserByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal() {
        int total = userDao.findTotalUser();
        System.out.println(total);
    }
}















