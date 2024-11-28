package com.example.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "T_TEMPLATE_RESULT")
public class Result {
    private Long id;
    private String check;
    private String conclusion;
    private Long someIds;

}
