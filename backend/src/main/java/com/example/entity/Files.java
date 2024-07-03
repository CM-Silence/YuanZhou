package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("files")
public class Files implements Serializable {

    @TableField("id")
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("url")
    private String url;

}
