package com.example.common;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInfoExtractor {



    /**
     * 从给定的字符串中提取多个name和url对，并生成JSON数组
     *
     * @param inputList 包含多个name和url对的字符串列表
     * @return 包含所有提取信息的JSON数组，如果未找到匹配项则返回空的JSON数组
     */
    public static String extractFileInfosToJson(List<String> inputList) {
        // 转换后的列表
        List<Map<String, String>> resultList = new ArrayList<>();

        // 正则表达式，匹配 "key: value" 格式的字符串，其中 value 可以包含冒号但不应以冒号开头
        Pattern pattern = Pattern.compile("(\\w+):\\s*([^,\\s]+(?:\\s+[^,\\s:]+)*)");

        // 解析并转换
        for (String input : inputList) {
            Map<String, String> map = new HashMap<>();
            Matcher matcher = pattern.matcher(input.trim());
            while (matcher.find()) {
                String key = matcher.group(1);
                String value = matcher.group(2).trim(); // 去除可能存在的尾随空格
                map.put(key, value);
            }
            resultList.add(map);
        }

        // 转换为JSON
        Gson gson = new Gson();

        return gson.toJson(resultList);
    }
}