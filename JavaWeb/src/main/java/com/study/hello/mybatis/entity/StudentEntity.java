package com.study.hello.mybatis.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class StudentEntity implements Serializable {
    private Long id;
    private String userPhone;
    private String userName;
    private Integer userAge;
    // private Integer state;
    // private Long createdBy;
    // private LocalDateTime createdTime;
    // private Long updatedBy;
    // private LocalDateTime updatedTime;
    // private Boolean delFlag;
    // private StudentEntity studentA;
    // private StudentEntity studentB;
}