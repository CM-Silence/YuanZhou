package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.News;
import com.example.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/news")
@Slf4j
public class NewsController {
    @Autowired
    private NewsService newsService;

    /**
     * 分页查询
     * @param name 查询名称
     * @param page_size 页面显示数量
     * @param page 当前页面
     * @param type 资源类型
     * @param start_time 资源出版开始时间
     * @param end_time 资源出版结束时间
     * @return Resources类
     */
    @GetMapping("/list")
    public Result<Page<News>> page(String name,
                                   Integer page_size,
                                   Integer page,
                                   Integer type,
                                   @RequestParam(required = false) String start_time,
                                   @RequestParam(required = false) String end_time){

        //将string类型的时间转化为LocalDateTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime startTime = null;
        LocalDateTime endTime = null;

        if (start_time != null && !start_time.isEmpty()){
            try{
                startTime = LocalDateTime.parse(start_time,formatter);
            } catch (DateTimeParseException e){
                //假如转化失败，则报错
                System.out.println("Failed to parse startTime:"+ start_time);
            }
        }

        if (end_time != null && !end_time.isEmpty()){
            try{
                endTime = LocalDateTime.parse(end_time,formatter);
            } catch (DateTimeParseException e){
                //假如转化失败，则报错
                System.out.println("Failed to parse end_time:"+ end_time);
            }
        }


        //构造分页构造器
        Page<News> pageInfo = new Page<>(page, page_size);

        //条件构造器
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件，使用like关键字
        queryWrapper.like(name != null, News::getTitle, name);
        queryWrapper.gt(startTime != null,News::getCreatedAt, startTime);
        queryWrapper.lt(endTime != null,News::getCreatedAt, endTime);
        //添加对type的筛选条件
        if(type != null)
        { switch (type) {
            case 0:
                queryWrapper.eq(true,News::getType, type);
            case 1:
                queryWrapper.eq(type == 1,News::getType, type);
            default:
                queryWrapper.eq(true,News::getType, type);
        }
        }
        //添加排序条件
        queryWrapper.orderByDesc(News::getId);

        //执行分页查询
        newsService.page(pageInfo, queryWrapper);

        return Result.success1(pageInfo, "get message success");
    }

    /**
     * 新增资源
     * @param news 新闻类
     * @return 成功信息
     */
    @PostMapping("/add")
    public Result<String> add (News news){
        log.info(news.toString());

        newsService.save(news);
        return Result.success2("新增资源成功");
    }

    /**
     * 资源删除
     * @param id 新闻id
     * @return 结果类
     */
    @DeleteMapping("/delete")
    public Result<String> delete(String id) {
        newsService.removeById(id);
        return Result.success2("delete success");
    }

    /**
     * 编辑资源
     * @param news 新闻类
     * @return 成功信息
     */
    @PutMapping("edit")
    public Result<String> edit(News news){
        log.info(news.toString());

        newsService.updateById(news);
        return Result.success2("修改成功");
    }
}
