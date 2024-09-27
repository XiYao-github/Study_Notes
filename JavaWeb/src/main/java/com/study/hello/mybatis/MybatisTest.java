package com.study.hello.mybatis;

import com.study.hello.mybatis.entity.StudentEntity;
import com.study.hello.mybatis.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.*;
import java.util.List;
import java.util.Properties;

/**
 * JavaEE三层架构：表现层、业务层、持久层
 * 持久层：负责操作数据到数据库的那一层代码。
 * <p>
 * MyBatis是一款优秀的持久层框架，用于简化JDBC开发
 * MyBatis使用
 * 编写核心配置文件：获取数据库连接信息，解决硬编码问题
 */
public class MybatisTest {

    /**
     * mybatis连接数据库
     */
    private SqlSession getSqlSession() throws IOException {
        // 加载数据库连接配置文件
        Properties properties = Resources.getResourceAsProperties("druid.properties");
        // 加载mybatis核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 选择配置的环境，一般不配置选默认值
        String environment = "dev";
        // 获取 SqlSessionFactory 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment, properties);
        // 工厂对象获取 SqlSession 对象
        return sqlSessionFactory.openSession();
    }

    @Test
    public void queryList() throws IOException {
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        // 方法调用(不推荐)：参数是一个字符串，该字符串必须是映射配置文件的 namespace.id
        // List<StudentEntity> entityList = sqlSession.selectList("com.study.hello.mybatis.mapper.StudentMapper.queryList");
        // 映射器接口(推荐)
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<StudentEntity> entityList = mapper.queryList();
        System.out.println(entityList);
        // 释放资源
        sqlSession.close();
    }


}
