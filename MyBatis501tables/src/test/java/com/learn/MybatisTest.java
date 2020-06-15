package com.learn;

import com.learn.dao.AccountDao;
import com.learn.dao.UserDao;
import com.learn.domain.Account;
import com.learn.domain.AccountUser;
import com.learn.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisTest {

    private InputStream in;
    private SqlSession session;
    private UserDao userDao;
    private AccountDao accountDao;

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
        accountDao = session.getMapper(AccountDao.class);
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
    public void testAccountFindAll() {
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    @Test
    public void testAccountFindAllAccount() {
        List<AccountUser> aus = accountDao.findAllAccount();
        for (AccountUser au : aus) {
            System.out.println(au);
        }
    }

    @Test
    public void testUserFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("每个用户的信息");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }
}
