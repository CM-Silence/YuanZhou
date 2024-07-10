package com.example.common;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // 解析并转换
        for (String input : inputList) {
            Map<String, String> map = new HashMap<>();
            // 使用trim()来去除多余的空格
            String[] parts = input.trim().split(",\\s*"); // 分割逗号，并去除逗号后的空格
            for (String part : parts) {
                String[] keyValue = part.trim().split(":\\s*"); // 分割键和值，并去除冒号后的空格
                map.put(keyValue[0], keyValue[1]);
            }
            resultList.add(map);
        }

        // 转换为JSON
        Gson gson = new Gson();

        return gson.toJson(resultList);
    }
}