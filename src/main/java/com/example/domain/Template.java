package com.example.domain;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "T_TEMPLATE")
// @KeySequence(value = "SEQ_HANDOVER_LOG")
public class Template {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long parentId;
    private String name;
    private String content;
    private Date createTime;
    @TableField(value = "name")
    private String text;

}
