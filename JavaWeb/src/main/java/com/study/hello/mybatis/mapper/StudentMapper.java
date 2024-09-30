package com.study.hello.mybatis.mapper;

import com.study.hello.mybatis.entity.StudentEntity;
import org.apache.ibatis.annotations.Param;

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
    Integer updateDynamic(@Param("entity") StudentEntity entity);

    /**
     * 删除数据(动态sql)
     */
    Integer deleteDynamic(List<Long> idList);

    /**
     * 新增数据(主键返回)
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

}