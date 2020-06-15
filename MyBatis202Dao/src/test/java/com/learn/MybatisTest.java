package com.learn;

import com.learn.dao.UserDao;
import com.learn.dao.impl.UserDaoImpl;
import com.learn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private UserDao dao;

    @Before //用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输出流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂对象 创建dao对象
        dao = new UserDaoImpl(factory);
    }

    @After //用于在测试方法之后执行
    public void destroy() throws Exception  {
        in.close();
    }


    @Test
    public void testFindAll() {
        //5.执行查询所有方法
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("save1 username");
        user.setAddress("save1 address");
        user.setSex("男");
        user.setBirthday(new Date());

        System.out.println(user);

        //5.执行查询所有方法
        dao.saveUser(user);

        System.out.println(user);

        // log4j 事务没提交
//        session.commit();
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(54);
        user.setUsername("update1 username");
        user.setAddress("update1 address");
        user.setSex("女");
        user.setBirthday(new Date());
        System.out.println(user);
        //5.执行查询所有方法
        dao.updateUser(user);
        System.out.println(user);
    }

    @Test
    public void testDelete() {
        dao.deleteUser(52);
    }

    @Test
    public void testFindOne() {
        User user = dao.findById(48);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
//        List<User> users = dao.findByName("%王%");
        List<User> users = dao.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal() {
        int total = dao.findTotal();
        System.out.println(total);
    }
}
