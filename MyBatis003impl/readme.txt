mybatis基本环境搭建 实现类方式

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

实现类：
    直接使用SqlSession的selectList等方法(全路径类名.方法名)进行调用
    跳过代理对象

测试|使用：
     1.InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml")
     2.SqlSessionFactoryBuilder
     3.SqlSessionFactory
     就是这里不一样，搬出到Impl里了
     session + getMapper -> session + selectList等方法
     4.new UserDaoImpl(factory)
