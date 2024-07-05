package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Resources;
import com.example.mapper.ResourcesMapper;
import com.example.service.ResourcesService;
import org.springframework.stereotype.Service;

@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

}
