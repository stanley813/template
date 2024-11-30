package com.example.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.ResultMapper;
import com.example.domain.Result;
import com.example.service.ResultService;

@Service
public class ResultServiceImpl extends ServiceImpl<ResultMapper, Result> implements ResultService {

}
