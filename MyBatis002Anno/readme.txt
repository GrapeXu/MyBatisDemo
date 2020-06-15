mybatis基本环境搭建 注解方式

pom文件依赖：
    1.mybatis
    2.mysql-connector-java
    3.log4j
    4.junit


主配置文件：
    SqlMapConfig.xml

映射配置
    注解方式-> com.learn.dao.UserDao 接口上加注解即可

使用|测试：
    1.InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml")
    2.SqlSessionFactoryBuilder
    3.SqlSessionFactory
    4.SqlSession
    5.session.getMapper(UserDao.class)
    使用代理对象即可，这是为我们生成的dao实现类

