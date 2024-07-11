package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.LabApplication;
import com.example.service.LabApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/application")
@Slf4j
@CrossOrigin
public class LabApplicationController {

    @Autowired
    private LabApplicationService labApplicationService;

    /**
     * 分页查询
     * @param key_word 关键字查询
     * @param page_size 分页大小
     * @param page 当前页面
     * @return 分页信息
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> page(String key_word,
                                                    @RequestParam(value = "page_size", defaultValue = "65535") Integer page_size,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page){
        //构造分页构造器
        Page<LabApplication> pageInfo = new Page<>(page, page_size);

        //条件构造器
        LambdaQueryWrapper<LabApplication> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件，使用like关键字
        queryWrapper.like(key_word != null, LabApplication::getLab , key_word);

        //添加排序条件
        queryWrapper.orderByDesc(LabApplication::getAid);

        //执行分页查询
        labApplicationService.page(pageInfo, queryWrapper);

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
     * 申请增加
     * @param labApplication labApplication类
     * @return 保存成功信息
     */
    @PostMapping("/add")
    public Result<String> add (LabApplication labApplication)  {
        log.info(labApplication.toString());
        //引用IService当中的save方法保存其他数据
        labApplicationService.save(labApplication);

        return Result.success2("save success");
    }

    /**
     * 申请删除
     * @param aid 传入aid
     * @return 删除成功信息
     */
    @DeleteMapping("/delete")
    public Result<String> delete(String aid) {
        if (aid == null){
            return Result.error("delete error");
        } else {
            labApplicationService.removeById(aid);
        }
        return Result.success2("delete success");
    }

    /**
     * 申请编辑
     * @param labApplication 传入申请表类
     * @return 编辑成功信息
     */
    @PutMapping("/edit")
    public Result<String> edit (LabApplication labApplication)  {
        log.info(labApplication.toString());

        labApplicationService.updateById(labApplication);
        return Result.success2("edit success");
    }

}
