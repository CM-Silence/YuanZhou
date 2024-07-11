package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.FileInfoExtractor;
import com.example.common.FileUploadUtil;
import com.example.common.ImageUploadUtil;
import com.example.common.Result;
import com.example.entity.News;
import com.example.mapper.NewsMapper;
import com.example.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
@Slf4j
@CrossOrigin
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private ImageUploadUtil imageUploadUtil;

    @Autowired
    private FileUploadUtil fileUploadUtil;
    /**
     * 分页查询
     *
     * @param key_word       查询名称
     * @param page_size  页面显示数量
     * @param page       当前页面
     * @param type       资源类型
     * @param start_time 资源出版开始时间
     * @param end_time   资源出版结束时间
     * @return Resources类
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> page(String key_word,
                                                    @RequestParam(value = "page_size", defaultValue = "65535") Integer page_size,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page,
                                                    @RequestParam(required = false)Integer type,
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
        queryWrapper.like(key_word != null, News::getTitle, key_word);
        queryWrapper.gt(startTime != null,News::getCreated_at, startTime);
        queryWrapper.lt(endTime != null,News::getCreated_at, endTime);
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
        queryWrapper.orderByDesc(News::getMid);

        //执行分页查询
        newsService.page(pageInfo, queryWrapper);

        //更改返回格式，与前端对接
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("rows", pageInfo.getRecords()); // 将records更改为rows
        responseData.put("total", pageInfo.getTotal());
        responseData.put("total_pages", (int) Math.ceil((double) pageInfo.getTotal() / pageInfo.getSize()));
        responseData.put("current", pageInfo.getCurrent());
        responseData.put("page", pageInfo.getPages());

        //返回数据
        return ResponseEntity.ok(responseData);
    }

    /**
     * 新增资源
     * @param news 新闻类
     * @return 成功信息
     */
    @PostMapping("/add")
    public Result<String> add (News news)  {
        log.info(news.toString());

        //引用IService当中的save方法保存其他数据
        newsService.save(news);
        return Result.success2("save success");
    }

    /**
     * 资源删除
     * @param mid 新闻id
     * @return 结果类
     */
    @DeleteMapping("/delete")
    public Result<String> delete(String mid) {
        if (mid == null){
            return Result.error("delete error");
        } else {
            newsService.removeById(mid);
        }
        return Result.success2("delete success");
    }

    /**
     * 编辑资源
     * @param news 新闻类
     * @return 成功信息
     */
    @PutMapping("/edit")
    public Result<String> edit (News news,
                                @RequestParam(value = "img", required = false) MultipartFile img,
                                @RequestParam(value = "files", required = false) MultipartFile[] files) {
        log.info(news.toString());
        if(img != null) {
            //将img文件转化为url，并将图片存入本地
            String uploadImage = imageUploadUtil.uploadImage(img);
            //将url存入数据库
            news.setImg1(uploadImage);
        }

        if(files != null) {
            //将附件files转化为url，并将图片存入本地
            List<String> uploadFile = fileUploadUtil.uploadFiles(files);

            //将files的url转化为json格式
            String uploadFile1 = FileInfoExtractor.extractFileInfosToJson(uploadFile);

            //将url存入数据库
            news.setFiles1(uploadFile1);

        }

        //引用IService当中的updateById方法保存其他数据
        newsService.updateById(news);
        return Result.success2("edit success");
    }
}
