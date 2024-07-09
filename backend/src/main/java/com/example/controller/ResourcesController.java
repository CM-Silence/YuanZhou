package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.FileInfoExtractor;
import com.example.common.FileUploadUtil;
import com.example.common.ImageUploadUtil;
import com.example.common.Result;
import com.example.entity.Resources;
import com.example.service.ResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/res")
@Slf4j
@CrossOrigin
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;

    /**
     * 分页查询
     *
     * @param name       查询名称
     * @param page_size  页面显示数量
     * @param page       当前页面
     * @param type       资源类型
     * @param start_time 资源出版开始时间
     * @param end_time   资源出版结束时间
     * @return Resources类
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> page(String name,
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
        Page<Resources> pageInfo = new Page<>(page, page_size);

        //条件构造器
        LambdaQueryWrapper<Resources> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件，使用like关键字
        queryWrapper.like(name != null, Resources::getTitle, name);
        queryWrapper.gt(startTime != null,Resources::getCreated_at, startTime);
        queryWrapper.lt(endTime != null,Resources::getCreated_at, endTime);
        //添加对type的筛选条件
        if(type != null)
            { switch (type) {
            case 0:
                queryWrapper.eq(true,Resources::getType, type);
            case 1:
                queryWrapper.eq(type == 1,Resources::getType, type);
            case 2:
                queryWrapper.eq(type == 2,Resources::getType, type);
            case 3:
                queryWrapper.eq(type == 3,Resources::getType, type);
            case 4:
                queryWrapper.eq(type == 4,Resources::getType, type);
            default:
                queryWrapper.eq(true,Resources::getType, type);
        }
            }
        //添加排序条件
        queryWrapper.orderByDesc(Resources::getRid);

        //执行分页查询
        resourcesService.page(pageInfo, queryWrapper);

        //更改返回格式，与前端对接
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("rows", pageInfo.getRecords()); // 将records更改为rows
        responseData.put("total", pageInfo.getTotal());
        responseData.put("size", pageInfo.getSize());
        responseData.put("current", pageInfo.getCurrent());
        responseData.put("pages", pageInfo.getPages());

        //返回数据
        return ResponseEntity.ok(responseData);
    }

    /**
     * 资源删除
     * @param rid 资源id
     * @return 结果类
     */
    @DeleteMapping("/delete")
    public Result<String> delete(String rid) {
        if (rid == null){
            return Result.error("delete error");
        } else {

            resourcesService.removeById(rid);
        }
        return Result.success2("delete success");
    }

    /**
     * 新增资源
     * @param resources 资源类
     * @return 成功信息
     */
    @PostMapping("/add")
    public Result<String> add (Resources resources, @RequestParam MultipartFile img, @RequestParam MultipartFile[] files) throws IOException {
        log.info(resources.toString());

        if(img != null) {
            //将img文件转化为url，并将图片存入本地
            String uploadImage = ImageUploadUtil.uploadImage(img);
            //将url存入数据库
            resources.setImg1(uploadImage);
        }

        if(files != null) {
            //将附件files转化为url，并将图片存入本地
            String uploadFile = FileUploadUtil.uploadFiles(files).toString();

            //将files的url转化为json格式
            String uploadFile1 = String.valueOf(FileInfoExtractor.extractFileInfosToJson(uploadFile));

            //将url存入数据库
            resources.setFiles1(uploadFile1);

        }

        //引用IService当中的save方法保存其他数据
        resourcesService.save(resources);
        return Result.success2("新增资源成功");
    }

    /**
     * 编辑资源
     * @param resources 资源类
     * @return 成功信息
     */
    @PutMapping("edit")
    public Result<String> edit (Resources resources, @RequestParam MultipartFile img, @RequestParam MultipartFile[] files) throws IOException {
        log.info(resources.toString());

        if(img != null) {
            //将img文件转化为url，并将图片存入本地
            String uploadImage = ImageUploadUtil.uploadImage(img);
            //将url存入数据库
            resources.setImg1(uploadImage);
        }

        if(files != null) {
            //将附件files转化为url，并将图片存入本地
            String uploadFile = FileUploadUtil.uploadFiles(files).toString();

            //将files的url转化为json格式
            String uploadFile1 = String.valueOf(FileInfoExtractor.extractFileInfosToJson(uploadFile));

            //将url存入数据库
            resources.setFiles1(uploadFile1);

        }
        resourcesService.updateById(resources);
        return Result.success2("修改成功");
    }

}
