package com.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "T_TEMPLATE_RESULT")
@KeySequence(value = "SEQ_TEMPLATE_RESULT")
public class Result {
    // @TableId(type = IdType.AUTO)
    @TableId(type = IdType.INPUT)
    private Long id;
    private String examinationFinding;
    private String conclusion;
    private String otherId;

}
