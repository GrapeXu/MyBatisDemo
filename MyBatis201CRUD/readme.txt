MyBatis XML方式 的CRUD

pom文件依赖：
    1.mybatis
    2.mysql-connector-java
    3.log4j
    4.junit


主配置文件：
    SqlMapConfig.xml

映射配置：
    xml方式-> com/learn/dao/UserDao.xml
         对应 com.learn.dao.UserDao
    注解方式-> com.learn.dao.UserDao 加注解
        主配置文件 配一下即可

使用|测试：
    MyTest：main方法
        1.InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml")
        2.SqlSessionFactoryBuilder
        3.SqlSessionFactory
        4.SqlSession
        5.session.getMapper(UserDao.class)
        使用代理对象即可，这是为我们生成的dao实现类
        session.close() + in.close()

    MyBatisTest：junit
        InputStream SqlSession UserDao
        @Before
            1.InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml")
            2.SqlSessionFactoryBuilder
            3.SqlSessionFactory
            4.SqlSession
            5.session.getMapper(UserDao.class)
        @After
            1.session.commit()
            2.session.close()
            3.in.close()