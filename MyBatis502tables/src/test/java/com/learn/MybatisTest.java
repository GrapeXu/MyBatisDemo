package com.learn;

import com.learn.dao.RoleDao;
import com.learn.dao.UserDao;
import com.learn.domain.Role;
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
    private UserDao userDao;
    private RoleDao roleDao;

    @Before //用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输出流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        session = factory.openSession();
        //4.获取dao的代理对象
        userDao = session.getMapper(UserDao.class);
        roleDao = session.getMapper(RoleDao.class);
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
    public void testRoleFindAll() {
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            System.out.println("--每个角色信息--");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

    @Test
    public void testUserFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("用户信息");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }



}
