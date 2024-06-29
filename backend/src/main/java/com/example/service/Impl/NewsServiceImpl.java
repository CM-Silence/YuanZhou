package com.example.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.News;
import com.example.entity.User;
import com.example.mapper.NewsMapper;
import com.example.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    NewsMapper newsMapper;
    @Override
    public List<News> selectAll() {
        return newsMapper.selectAll();
    }
}
