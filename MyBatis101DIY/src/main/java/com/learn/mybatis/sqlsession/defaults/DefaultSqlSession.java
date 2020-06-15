package com.learn.mybatis.sqlsession.defaults;

import com.learn.mybatis.cfg.Configuration;
import com.learn.mybatis.sqlsession.proxy.MapperProxy;
import com.learn.mybatis.sqlsession.SqlSession;
import com.learn.mybatis.utils.DataSourceUtil;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession接口的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;

    private Connection conn;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        this.conn = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        /*
         * 代理谁 就用谁的类加载器
         *      daoInterfaceClass.getClassLoader()
         * 代理谁 就要和谁实现相同的接口 这里本身就是接口
         *      new Class[]{daoInterfaceClass}
         * 如何代理？
         *      自己的代理方式 MapperProxy implements InvocationHandler 传参处理
         */
        return (T) Proxy.newProxyInstance (daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                new MapperProxy(cfg.getMappers(), conn)
        );

    }

    /**
     * 用于释放资源
     */
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
