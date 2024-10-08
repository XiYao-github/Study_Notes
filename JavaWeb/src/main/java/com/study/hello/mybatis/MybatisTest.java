package com.study.hello.mybatis;

import com.study.hello.mybatis.entity.StudentEntity;
import com.study.hello.mybatis.mapper.StudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
        // Properties properties = Resources.getResourceAsProperties("druid.properties");
        // 加载mybatis核心配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        // 选择配置的环境，一般不配置选默认值
        // String environment = "mysql";
        // 获取 SqlSessionFactory 工厂对象
        // SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment, properties); // 手动配置
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); // 自动配置
        // 工厂对象获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 手动提交事务
        // SqlSession sqlSession = sqlSessionFactory.openSession(true); // 自动提交事务
        return sqlSession;
    }

    @Test
    public void queryList() throws IOException {
        // 获取SqlSession对象
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        // 方法调用(不推荐)：参数是一个字符串，该字符串必须是映射配置文件的 namespace.id
        // List<StudentEntity> entityList = sqlSession.selectList("com.study.hello.mybatis.mapper.StudentMapper.queryList");
        // 映射器接口(推荐)
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(mapper.queryList_1());
        System.out.println(mapper.queryList_2());
        System.out.println(mapper.queryList_3());
        System.out.println(mapper.queryList());
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void queryInfo() throws IOException {
        // 构建参数
        long id = 1;
        // 获取SqlSession对象
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        StudentEntity entity = mapper.queryInfo(id);
        System.out.println(entity);
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void queryCondition() throws IOException {
        // 构建参数
        long id = 1;
        String userName = "%" + "张三" + "%";
        String userPhone = "%" + "177" + "%";
        StudentEntity student = new StudentEntity();
        student.setUserName(userName);
        student.setUserPhone(userPhone);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("userPhone", userPhone);
        // 获取SqlSession对象
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(mapper.queryCondition(id, student, map));
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void queryDynamic() throws IOException {
        // 构建参数
        long id = 1;
        String userName = "张三";
        String userPhone = "17720202177";
        StudentEntity student = new StudentEntity();
        student.setId(id);
        student.setUserName(userName);
        student.setUserPhone(userPhone);
        // 获取SqlSession对象
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(mapper.queryDynamic(student));
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void updateDynamic() throws IOException {
        // 构建参数
        long id = 1;
        String userName = "张三";
        String userPhone = "17720202177";
        StudentEntity student = new StudentEntity();
        student.setId(id);
        student.setUserName(userName);
        student.setUserPhone(userPhone);
        // 获取SqlSession对象
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // System.out.println("修改执行成功，受影响行数：" + mapper.update(student));
        System.out.println("修改执行成功，受影响行数：" + mapper.updateDynamic(student));
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void deleteDynamic() throws IOException {
        // 构建参数
        List<Long> list = new ArrayList<>();
        Collections.addAll(list, 1L, 2L, 3L);
        // 获取SqlSession对象
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // System.out.println("删除执行成功，受影响行数：" + mapper.delete(1L));
        System.out.println("删除执行成功，受影响行数：" + mapper.deleteDynamic(list));
        // 测试需要，不提交事务
        // sqlSession.commit();
        // sqlSession.rollback();
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void insert() throws IOException {
        // 构建参数
        String userName = "张三";
        String userPhone = "17720202177";
        Integer userAge = 21;
        StudentEntity student = new StudentEntity();
        student.setUserName(userName);
        student.setUserPhone(userPhone);
        student.setUserAge(userAge);
        // 获取SqlSession对象
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // System.out.println("新增执行成功，受影响行数：" + mapper.insert(student)); // 自增主键
        System.out.println("新增执行成功，受影响行数：" + mapper.insertSelectKey(student)); // 自定义主键
        //返回数据库生成的主键id
        System.out.println("数据库新增返回数据id：" + student.getId());
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void insertBatch() throws IOException {
        // 构建参数
        List<StudentEntity> entityList = new ArrayList<>();
        entityList.add(new StudentEntity().setUserName("张三一").setUserPhone("17720202177").setUserAge(21));
        entityList.add(new StudentEntity().setUserName("张三二").setUserPhone("17720202177").setUserAge(22));
        entityList.add(new StudentEntity().setUserName("张三三").setUserPhone("17720202177").setUserAge(23));
        // 获取SqlSession对象
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println("新增执行成功，受影响行数：" + mapper.insertBatch(entityList));
        //返回数据库生成的主键id
        List<Long> idList = entityList.stream().map(StudentEntity::getId).collect(Collectors.toList());
        System.out.println("数据库新增返回数据id：" + idList);
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void annotations() throws IOException {
        // 构建参数
        long id = 1;
        String userName = "张三三";
        String userPhone = "17720202177";
        Integer userAge = 20;
        StudentEntity student = new StudentEntity();
        student.setId(id);
        student.setUserName(userName);
        student.setUserPhone(userPhone);
        student.setUserAge(userAge);
        // 获取SqlSession对象
        SqlSession sqlSession = getSqlSession();
        // 执行sql
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        // System.out.println("新增执行成功，受影响行数：" + mapper.insertTest(student));
        // System.out.println("删除执行成功，受影响行数：" + mapper.deleteTest(id));
        // System.out.println("修改执行成功，受影响行数：" + mapper.updateTest(student));

        System.out.println("查询执行成功，selectInfo：" + mapper.selectInfo(id));
        System.out.println("查询执行成功，selectList：" + mapper.selectList());
        System.out.println("查询执行成功，selectPage：" + mapper.selectPage(0, 10));
        System.out.println("查询执行成功，selectSize：" + mapper.selectSize());
        System.out.println("查询执行成功，selectResult：" + mapper.selectResult());
        // 提交事务
        sqlSession.commit();
        // 释放资源
        sqlSession.close();
    }
}
