MyBatis 采用的是自己的连接池技术
通过 主配置文件 配置
MyBatis 通过工厂模式来创建数据源 DataSource 对象

    ·UNPOOLED:不使用连接池的数据源
    ·POOLED:使用连接池的数据源
    ·JNDI:使用 JNDI 实现的数据源

该项目只是简单的 XML CRUD POOLED