package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.Template;


@Mapper
@Repository
public interface TemplateMapper extends BaseMapper<Template>{
}
