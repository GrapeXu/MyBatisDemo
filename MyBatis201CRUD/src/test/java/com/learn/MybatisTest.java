package com.learn;

import com.learn.dao.UserDao;
import com.learn.domain.QueryVo;
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
    private SqlSession session;
    private UserDao dao;

    @Before //用于在测试方法执行之前执行
    public void init() throws Exception {
        //1.读取配置文件，生成字节输出流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        session = factory.openSession();
        //4.获取dao的代理对象
        dao = session.getMapper(UserDao.class);
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
        user.setId(51);
        user.setUsername("update1 username");
        user.setAddress("update1 address");
        user.setSex("女");
        user.setBirthday(new Date());

        //5.执行查询所有方法
        dao.updateUser(user);
    }

    @Test
    public void testDelete() {
        dao.deleteUser(51);
    }

    @Test
    public void testFindOne() {
        User user = dao.findById(48);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
//        List<User> users = dao.findByName("%王%");
        List<User> users = dao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);

        List<User> users = dao.findUserByVo(vo);

        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindTotal() {
        int total = dao.findTotal();
        System.out.println(total);
    }
}
