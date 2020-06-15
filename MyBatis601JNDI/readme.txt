MyBatis连接池的一种
    JNDI：Java命名 和 目录接口
    ·采用服务器提供的JNDI技术实现，来获取DataSource对象
    ·不同的服务器 所能拿到的DataSource是不一样的
    ·MyBatis会从JNDI服务上查找DataSource实例
    注：如果不是web 或 maven的war工程，是不能使用的
        ·这里用的是 maven 的 webapp
    tomcat 采用 dbcp连接池

pom文件依赖：
    1.mybatis
    2.mysql-connector-java
    3.log4j
    4.junit

    5.servlet-api
    6.jsp-api

主配置文件
    JDNI

webapp/META-INF/context.xml：配置tomcat相关信息

index.jsp：使用|测试
