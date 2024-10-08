package com.study.hello.mybatis.mapper;

import com.study.hello.mybatis.entity.StudentEntity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.HashMap;
import java.util.List;

/*
    MyBatis 参数封装：
    单个参数：
        1. POJO类型：直接使用，属性名 和 参数占位符名称 一致
        2. Map集合：直接使用，键名 和 参数占位符名称 一致
        3. Collection集合：封装为Map集合，可以使用@Param注解，替换Map集合中默认的arg键名
            map.put("arg0",collection集合);
            map.put("collection",collection集合);
        4. List：封装为Map集合，可以使用@Param注解，替换Map集合中默认的arg键名
            map.put("arg0",list集合);
            map.put("collection",list集合);
            map.put("list",list集合);
        5. Array：封装为Map集合，可以使用@Param注解，替换Map集合中默认的arg键名
            map.put("arg0",数组);
            map.put("array",数组);
        6. 其他类型：直接使用
    多个参数：封装为Map集合，一个参数put两次，键名分别为(arg，param)，(arg)键名下标从0开始，(param)键名下标从1开始
            map.put("arg0",参数值1)
            map.put("agr1",参数值2)
            map.put("param1",参数值1)
            map.put("param2",参数值2)
        使用@Param注解的参数名称，替换的就是arg键名的参数名，例：@Param("userName"),@Param("userPhone")
            map.put("userName",参数值1)
            map.put("userPhone",参数值2)
            map.put("param1",参数值1)
            map.put("param2",参数值2)
 */
public interface StudentMapper {
    /**
     * 查询列表(as别名)
     */
    List<StudentEntity> queryList_1();

    /**
     * 查询列表(sql片段)
     */
    List<StudentEntity> queryList_2();

    /**
     * 查询列表(resultMap结果集映射)
     */
    List<StudentEntity> queryList_3();

    /**
     * 查询列表
     */
    List<StudentEntity> queryList();

    /**
     * 查询详情
     */
    StudentEntity queryInfo(Long id);

    /**
     * 多条件查询
     */
    // 使用 @Param("参数名称") 标记参数，映射配置文件需要使用 #{参数名称} 进行占位
    // 使用实体类接收多个参数，将该实体类对象作为接口的方法参数。该方式要求在映射配置文件使用 #{内容} 时，内容必须和实体类属性名保持一致。
    // 单个对象参数可以直接使用(对象属性名)#{id}映射，多个对象参数需要使用(对象名.属性名) #{entity.id} 映射
    // 使用Map集合接收多个参数，将Map集合作为接口的方法参数。该方式要求在映射配置文件使用 #{内容} 时，内容必须和Map集合中键的名称一致。
    List<StudentEntity> queryCondition(@Param("id") Long id, @Param("entity") StudentEntity entity, @Param("map") HashMap<String, Object> map);

    /**
     * 多条件查询(动态sql)
     */
    List<StudentEntity> queryDynamic(StudentEntity entity);

    /**
     * 更新数据(动态sql)
     */
    Integer update(@Param("entity") StudentEntity entity);

    /**
     * 更新数据(动态sql)
     */
    Integer updateDynamic(@Param("entity") StudentEntity entity);

    /**
     * 删除数据(动态sql)
     */
    Integer delete(Long id);

    /**
     * 删除数据(动态sql)
     */
    Integer deleteDynamic(List<Long> idList);

    /**
     * 新增数据(自增主键)
     */
    Integer insert(StudentEntity entity);

    /**
     * 新增数据(主键生成：selectKey)
     */
    Integer insertSelectKey(StudentEntity entity);

    /**
     * 批量新增数据(动态sql)
     */
    Integer insertBatch(List<StudentEntity> entityList);

    /**
     * 新增数据(注解)
     */
    @Insert("insert into student (user_name, user_phone, user_age) values (#{userName}, #{userPhone}, #{userAge})")
    Integer insertTest(StudentEntity entity);

    /**
     * 删除数据(注解)
     */
    @Delete("delete from student where id = #{id}")
    Integer deleteTest(Long id);

    /**
     * 更新数据(注解)
     */
    @Update("update student set user_name = #{userName}, user_phone = #{userPhone}, user_age = #{userAge} where id = #{id}")
    Integer updateTest(StudentEntity entity);

    /**
     * 查询详情(注解)
     */
    @Select("select * from student where id = #{id}")
    @ResultMap(value = "resultMap")
    StudentEntity selectInfo(Long id);

    /**
     * 查询列表(注解)
     */
    @Select("select * from student")
    @ResultMap(value = "resultMap")
    List<StudentEntity> selectList();

    /**
     * 分页查询(注解)
     */
    @Select("select * from student limit #{num}, #{size}")
    @ResultMap(value = "resultMap")
    List<StudentEntity> selectPage(Integer num, Integer size);

    /**
     * 查询数据量(注解)
     */
    @Select("select count(*) from student")
    Integer selectSize();


    /**
     * @Results 注解代替的是标签<resultMap>
     * 该注解中可以使用单个@Result注解，也可以使用@Result集合
     * 属性介绍：
     * id：结果映射唯一标识
     * value：Result集合
     * @Resutl 注解代替了<id>标签和<result>标签
     * 属性介绍：
     * id：是否是主键字段
     * column：数据库的列名
     * property：需要装配的属性名
     * javaType：java类型(default void.class)，通常不会配置
     * jdbcType：jdbc类型(default JdbcType.UNDEFINED)，通常不会配置
     * typeHandler：类型转换器(default UnknownTypeHandler.class)，通常不会配置
     */
    @Results(id = "resultMap",
            value = {
                    @Result(id = true, column = "id", property = "id", javaType = Long.class, jdbcType = JdbcType.BIGINT),
                    @Result(column = "user_name", property = "userName", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "user_phone", property = "userPhone", javaType = String.class, jdbcType = JdbcType.VARCHAR),
                    @Result(column = "user_age", property = "userAge", javaType = Integer.class, jdbcType = JdbcType.INTEGER),
            })
    @Select("select * from student")
    List<StudentEntity> selectResult();

}