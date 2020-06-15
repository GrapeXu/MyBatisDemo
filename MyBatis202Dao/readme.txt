MyBatis XML方式/Dao方式 的CRUD

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
    MyBatisTest：junit
        InputStream UserDao
        @Before
            1.in = Resources.getResourceAsStream("SqlMapConfig.xml")
            2.SqlSessionFactoryBuilder
            3.dao = new UserDaoImpl(factory)
        @After
            1.in.close()