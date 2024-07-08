package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.TrainingExperiments;
import com.example.mapper.TrainingExperimentsMapper;
import com.example.service.TrainingExperimentsService;
import org.springframework.stereotype.Service;

@Service
public class TrainingExperimentsServiceImpl extends ServiceImpl<TrainingExperimentsMapper, TrainingExperiments> implements TrainingExperimentsService {
}
