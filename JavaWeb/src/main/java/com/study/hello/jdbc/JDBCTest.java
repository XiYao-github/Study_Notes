package com.study.hello.jdbc;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * JDBC(Java DataBase Connectivity)数据库连接，就是使用Java语言操作关系型数据库的一套API
 */
public class JDBCTest {

    @Test
    public void statementTest() throws Exception {
        /**
         * DriverManager(驱动管理类)作用：
         * - 注册驱动：Class.forName("com.mysql.cj.jdbc.Driver");
         * - MySQL5之后的驱动包，可以省略注册驱动的步骤，自动加载jar包中META-INF/services/java.sql.Driver文件中的驱动类
         * - 连接路径语法：jdbc:mysql://ip地址(域名):端口号/数据库名称?参数键值对1&参数键值对2…
         * - 配置 useSSL=false 参数，禁用安全连接方式，解决警告提示
         * - 如果连接的是本机mysql服务器，并且mysql服务默认端口是3306，可以简化书写："jdbc:mysql:///test?useSSL=false"
         */
        String url = "jdbc:mysql://127.0.0.1:3308/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        /**
         * Connection(数据库连接对象)
         * 获取执行对象
         * - Statement createStatement() 普通执行SQL对象
         * - PreparedStatement prepareStatement(sql) 预编译SQL的执行SQL对象：防止SQL注入
         * - CallableStatement prepareCall(sql) 执行存储过程的对象，存储过程MySQL不常用
         * 事务管理(MySQL默认是自动提交事务)
         * - 参数autoCommit表示是否自动提交事务，true表示自动提交事务，false表示手动提交事务。
         * - void setAutoCommit(boolean autoCommit) 开启事务：BEGIN; 或者 START TRANSACTION;
         * - void commit() 提交事务：COMMIT;
         * - void rollback() 回滚事务：ROLLBACK;
         */
        Connection connection = DriverManager.getConnection(url, username, password);
        // 编写sql
        String sql = "select * from student";
        /**
         * Statement作用：执行SQL语句，而针对不同类型的SQL语句使用的方法也不一样。
         * - int executeUpdate(String sql) 执行DDL、DML语句，返回受影响的行数
         * - ResultSet executeQuery(String sql) 执行DQL语句，返回查询结果集
         */
        Statement statement = connection.createStatement();
        /**
         * ResultSet(结果集对象)作用：封装了SQL查询语句的结果。
         * - boolean next() 将光标从当前位置向前移动一行，判断当前行是否为有效行，true表示当前行有数据，false表示当前行没有数据。
         * - xxx getXxx(参数) 获取数据：int类型的参数(列的编号，从1开始)，String类型的参数(列的名称)
         */
        ResultSet resultSet = statement.executeQuery(sql);
        // 创建集合
        List<Student> list = new ArrayList<>();
        // 处理结果，遍历resultSet中的所有数据
        // 判断当前行是否有数据，并且将光标向下移动一行
        while (resultSet.next()) {
            // 获取数据 getXxx()
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            // 创建对象
            Student student = new Student();
            // 设置对象参数
            student.setId(id);
            student.setName(name);
            student.setAge(age);
            // 存入集合
            list.add(student);
        }
        System.out.println(list);
        // 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void preparedStatementTest() throws Exception {
        /**
         * DriverManager(驱动管理类)作用：
         * - 注册驱动：Class.forName("com.mysql.cj.jdbc.Driver");
         * - MySQL5之后的驱动包，可以省略注册驱动的步骤，自动加载jar包中META-INF/services/java.sql.Driver文件中的驱动类
         * - 连接路径语法：jdbc:mysql://ip地址(域名):端口号/数据库名称?参数键值对1&参数键值对2…
         * - 配置 useSSL=false 参数，禁用安全连接方式，解决警告提示
         * - 如果连接的是本机mysql服务器，并且mysql服务默认端口是3306，可以简化书写："jdbc:mysql:///test?useSSL=false"
         */
        String url = "jdbc:mysql://127.0.0.1:3308/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "root";
        /**
         * Connection(数据库连接对象)
         * 获取执行对象
         * - Statement createStatement() 普通执行SQL对象
         * - PreparedStatement prepareStatement(sql) 预编译SQL的执行SQL对象：防止SQL注入
         * - CallableStatement prepareCall(sql) 执行存储过程的对象，存储过程MySQL不常用
         * 事务管理(MySQL默认是自动提交事务)
         * - 参数autoCommit表示是否自动提交事务，true表示自动提交事务，false表示手动提交事务。
         * - void setAutoCommit(boolean autoCommit) 开启事务：BEGIN; 或者 START TRANSACTION;
         * - void commit() 提交事务：COMMIT;
         * - void rollback() 回滚事务：ROLLBACK;
         */
        Connection connection = DriverManager.getConnection(url, username, password);
        /**
         * Java代码操作数据库流程
         * - 将编写的sql语句发送到MySQL服务器端，MySQL服务端会对sql语句进行如下操作
         * - 检查SQL语句的语法是否正确。
         * - 编译SQL语句，将SQL语句编译成可执行的函数。
         * - 执行SQL语句。
         * 开启预编译功能
         * - 检查SQL和编译SQL花费的时间比执行SQL的时间还要长。
         * - 如果我们只是重新设置参数，那么检查SQL语句和编译SQL语句将不需要重复执行。
         * - 编写url时添加参数预编译功能：useServerPrepStmts=true
         */
        String sql = "select * from student where user_name in (?, ?)";
        /**
         * PreparedStatement作用：预编译SQL语句并执行，预防SQL注入问题
         * - 预编译SQL，性能更高，防止SQL注入：将敏感字符进行转义
         * - 调用这两个方法时不需要传递SQL语句，因为获取SQL语句执行对象时已经对SQL语句进行预编译了。
         * - int executeUpdate() 执行DDL、DML语句，返回受影响的行数
         * - ResultSet executeQuery() 执行DQL语句，返回查询结果集
         */
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        /**
         * PreparedStatement对象：setXxx(参数1，参数2)：设置？的值
         * 参数1：？的位置编号，从1开始
         * 参数2：？的值
         */
        preparedStatement.setString(1, "张三");
        preparedStatement.setString(2, "李四");
        /**
         * ResultSet(结果集对象)作用：封装了SQL查询语句的结果。
         * - boolean next() 将光标从当前位置向前移动一行，判断当前行是否为有效行，true表示当前行有数据，false表示当前行没有数据。
         * - xxx getXxx(参数) 获取数据：int类型的参数(列的编号，从1开始)，String类型的参数(列的名称)
         */
        ResultSet resultSet = preparedStatement.executeQuery();
        // 创建集合
        List<Student> list = new ArrayList<>();
        // 处理结果，遍历resultSet中的所有数据
        // 判断当前行是否有数据，并且将光标向下移动一行
        while (resultSet.next()) {
            // 获取数据 getXxx()
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            // 创建对象
            Student student = new Student();
            // 设置对象参数
            student.setId(id);
            student.setName(name);
            student.setAge(age);
            // 存入集合
            list.add(student);
        }
        System.out.println(list);
        // 释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void druidTest() throws Exception {
        /**
         * 数据库连接池
         * - 数据库连接池是个容器，负责分配，管理数据库连接(Connection)，通过线程池技术，资源重用，提升系统响应速度，避免数据库连接遗漏。
         * - 用户需要连接数据库时，不需要自己创建连接，而只需要从连接池中获取一个连接进行使用，使用完毕后再将连接对象归还给连接池。
         * - 这样就可以起到资源重用，也节省了频繁创建连接销毁连接所花费的时间，从而提升了系统响应的速度。
         *
         * DataSource(数据库连接池)
         * - 官方(SUN)提供的数据库连接池标准接口，由第三方组织实现此接口。该接口提供了获取连接的功能。
         * - 这样就可以不需要通过DriverManager对象获取Connection对象，而是通过连接池DataSource获取Connection对象
         * - Connection getConnection() 获取数据库连接
         *
         * Druid(德鲁伊)
         * - Druid连接池是阿里巴巴开源的数据库连接池项目。
         * - 功能强大，性能优秀，是Java语言最好的数据库连接池之一。
         * - Druid通过读取配置文件获取连接数据库的配置信息。
         */
        // 定义配置文件：一般放置在 resources 文件目录下
        String path = "./src/main/resources/druid.properties";
        // 加载数据库连接配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream(path));
        // 获取数据库连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        // 获取连接
        Connection connection = dataSource.getConnection();
        // 编写sql
        String sql = "select * from student";
        // 获取statement对象
        Statement statement = connection.createStatement();
        // 执行SQL语句，获得封装的查询结果ResultSet
        ResultSet resultSet = statement.executeQuery(sql);
        // 创建集合
        List<Student> list = new ArrayList<>();
        // 处理结果，遍历resultSet中的所有数据
        // 判断当前行是否有数据，并且将光标向下移动一行
        while (resultSet.next()) {
            // 获取数据 getXxx()
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            // 创建对象
            Student student = new Student();
            // 设置对象参数
            student.setId(id);
            student.setName(name);
            student.setAge(age);
            // 存入集合
            list.add(student);
        }
        System.out.println(list);
        // 释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }
}