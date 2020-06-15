<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.ibatis.io.Resources" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactoryBuilder" %>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory" %>
<%@ page import="org.apache.ibatis.session.SqlSession" %>
<%@ page import="com.learn.dao.UserDao" %>
<%@ page import="com.learn.domain.User" %>
<%@ page import="java.util.List" %>
<html>
<body>
<h2>Hello World!</h2>
<%
    InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    SqlSessionFactory factory = builder.build(in);
    SqlSession session1 = factory.openSession();
    UserDao userDao = session1.getMapper(UserDao.class);
    List<User> users = userDao.findAll();
    for (User user : users) {
        System.out.println(user);
    }
    session1.close();
    in.close();
%>
</body>
</html>
