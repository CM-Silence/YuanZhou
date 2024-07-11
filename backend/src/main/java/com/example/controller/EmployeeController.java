package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/staff")
@Slf4j
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工分页查询
     * @param key_word 用户名
     * @param page_size 分页大小
     * @param page 当前页面
     * @return 员工列表及分页信息
     */
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> page(String key_word,
                                                    @RequestParam(value = "page_size", defaultValue = "65535") Integer page_size,
                                                    @RequestParam(value = "page", defaultValue = "1") Integer page){
        //构造分页构造器
        Page<Employee> pageInfo = new Page<>(page, page_size);

        //条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();

        //添加过滤条件，使用like关键字
        queryWrapper.like(key_word != null, Employee::getName , key_word);

        //添加排序条件
        queryWrapper.orderByDesc(Employee::getSid);

        //执行分页查询
        employeeService.page(pageInfo, queryWrapper);

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
     * 员工增加
     * @param employee 传入员工类
     * @return 保存成功信息
     */
    @PostMapping("/add")
    public Result<String> add (Employee employee)  {
        log.info(employee.toString());
        //引用IService当中的save方法保存其他数据
        employeeService.save(employee);

        return Result.success2("save success");
    }

    /**
     * 员工删除
     * @param sid 传入员工sid
     * @return 删除成功信息
     */
    @DeleteMapping("/delete")
    public Result<String> delete(String sid) {
        if (sid == null){
            return Result.error("delete error");
        } else {
            employeeService.removeById(sid);
        }
        return Result.success2("delete success");
    }

    /**
     * 员工编辑
     * @param employee 传入员工类
     * @return 编辑成功信息
     */
    @PutMapping("/edit")
    public Result<String> edit (Employee employee)  {
        log.info(employee.toString());

        employeeService.updateById(employee);
        return Result.success2("edit success");
    }
}
