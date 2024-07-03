package com.example.controller;

import com.example.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //默认返回时会经过视图解析器
@RequestMapping("/api/news")
@Slf4j
@CrossOrigin
public class NewsController {

    @Autowired
    private NewsService newsService;

}
