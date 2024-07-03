package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.News;

import java.util.List;

public interface NewsService extends IService<News> {

    List<News> selectAll();
}
