自定义MyBatis  xml方式
    依赖 + dom4j

pom文件依赖：
    1.mysql-connector-java
    2.dom4j
    3.jaxen
    4.log4j
    5.junit

主配置文件：
    SqlMapConfig.xml

映射配置：
    注解方式-> com.learn.dao.UserDao 加注解

使用|测试：
    1.InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml")
    2.SqlSessionFactoryBuilder
    3.SqlSessionFactory
    4.SqlSession
    5.session.getMapper(UserDao.class)
    使用代理对象即可，这是为我们生成的dao实现类

实现：在mybatis包里
    annotations：
        ·Select：;;查询的注解
    cfg：
        ·Configuration：连接信息×4 + Mapper<String, Mapper>;;mybatis的配置信息
        ·Mapper：queryString:SQL + resultType:实体类的全限定类名;;用于封装执行的SQL语句和结果类型的全限定类名
    io：
        ·Resources: ;;通过 类加载器 获取 配置文件的 输入流
    sqlsession：
        defaults：
            ·DefaultSqlSessionFactory：Configuration;;SqlSessionFactory接口的实现类，创建操作数据库对象
            ·DefaultSqlSession：Configuration + Connection;;SqlSession接口的实现类，创建代理对象+释放资源
        proxy：
            ·MapperProxy：Connection + Mapper<String, Mapper>;;代理中的增强
        ·SqlSession：;;根据参数创建代理对象
        ·SqlSessionFactory：;;打开SqlSession
        ·SqlSessionFactoryBuilder：;;用于创建SqlSessionFactory对象
    utils：
        ·DataSourceUtil：;;根据配置信息 获取连接；JDBC方式
        ·Executor：;;负责执行SQL语句，并且封装结果集
        ·XMLConfigBuilder：;;解析配置文件
