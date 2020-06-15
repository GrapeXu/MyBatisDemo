package com.learn;

import com.learn.dao.UserDao;
import com.learn.domain.User;
import com.learn.mybatis.io.Resources;
import com.learn.mybatis.sqlsession.SqlSession;
import com.learn.mybatis.sqlsession.SqlSessionFactory;
import com.learn.mybatis.sqlsession.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class MyTest {

    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3.使用工厂生产SqlSession对象
        SqlSession session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        UserDao dao = session.getMapper(UserDao.class);
        //5.使用代理对象执行方法
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6.释放资源
        session.close();
        in.close();
    }

}
