package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.LabApplication;
import com.example.mapper.LabApplicationMapper;
import com.example.service.LabApplicationService;
import org.springframework.stereotype.Service;

@Service
public class LabApplicationServiceImpl extends ServiceImpl<LabApplicationMapper, LabApplication> implements LabApplicationService {
}
