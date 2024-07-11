package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Devices;
import com.example.mapper.DevicesMapper;
import com.example.service.DevicesService;
import org.springframework.stereotype.Service;

@Service
public class DevicesServiceImpl extends ServiceImpl<DevicesMapper, Devices> implements DevicesService {
}
