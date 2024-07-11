package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Labs;
import com.example.mapper.LabsMapper;
import com.example.service.LabsService;
import org.springframework.stereotype.Service;

@Service
public class LabsServiceImpl extends ServiceImpl<LabsMapper, Labs> implements LabsService {
}
