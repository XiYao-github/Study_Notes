package com.study.hello.mybatis.mapper;

import com.study.hello.mybatis.entity.StudentEntity;

import java.util.List;

public interface StudentMapper {

    List<StudentEntity> queryList_1();

    List<StudentEntity> queryList_2();

    List<StudentEntity> queryList_3();

    List<StudentEntity> queryList();

    StudentEntity queryInfo(Long id);

}