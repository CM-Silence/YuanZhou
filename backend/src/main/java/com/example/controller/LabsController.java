package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.ImageUploadUtil;
import com.example.common.Result;
import com.example.entity.Labs;
import com.example.service.LabsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/labs")
@Slf4j
@CrossOrigin
public class LabsController {


    @Autowired
    private LabsService labsService;

    @Autowired
    private ImageUploadUtil imageUploadUtil;

    /**
     * 分页查询
     *
     * @param key_word   查询名称
     * @param page_size  页面显示数量
     * @param page       当前页面
     * @return Labs类
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> page(String key_word,
                                                    @RequestParam(value = "page_size", defaultValue = "65535") Integer page_size,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page){

        //构造分页构造器
        Page<Labs> pageInfo = new Page<>(page, page_size);

        //条件构造器
        LambdaQueryWrapper<Labs> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件，使用like关键字
        queryWrapper.like(key_word != null, Labs::getName, key_word);

        //添加排序条件
        queryWrapper.orderByDesc(Labs::getLid);

        //执行分页查询
        labsService.page(pageInfo, queryWrapper);

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
     * @param labs 实验室类
     * @return 成功信息
     */
    @PostMapping("/add")
    public Result<String> add (Labs labs)  {
        log.info(labs.toString());

        //引用IService当中的save方法保存其他数据
        labsService.save(labs);
        return Result.success2("save success");
    }

    /**
     * 资源删除
     * @param lid 实验室id
     * @return 结果类
     */
    @DeleteMapping("/delete")
    public Result<String> delete(String lid) {
        if (lid == null){
            return Result.error("delete error");
        } else {
            labsService.removeById(lid);
        }
        return Result.success2("delete success");
    }

    /**
     * 编辑资源
     * @param labs 实验室类
     * @return 成功信息
     */
    @PutMapping("/edit")
    public Result<String> edit (Labs labs,
                                @RequestParam(value = "img", required = false) MultipartFile img) {
        log.info(labs.toString());
        if(img != null) {
            //将img文件转化为url，并将图片存入本地
            String uploadImage = imageUploadUtil.uploadImage(img);
            //将url存入数据库
            labs.setImg1(uploadImage);
        }

        //引用IService当中的updateById方法保存其他数据
        labsService.updateById(labs);
        return Result.success2("edit success");
    }
}

