package com.example.common;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileInfoExtractor {

    // 正则表达式匹配 name=... 和紧跟着的 url=...
    private static final Pattern pattern = Pattern.compile("name=(\\S+).*?url=(\\S+)");

    /**
     * 从给定的字符串中提取多个name和url对，并生成JSON数组
     *
     * @param input 包含多个name和url对的字符串
     * @return 包含所有提取信息的JSON数组，如果未找到匹配项则返回空的JSON数组
     */
    public static JSONArray extractFileInfosToJson(String input) {
        JSONArray jsonArray = new JSONArray();
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String name = matcher.group(1).replaceAll(",$", "");
            String url = matcher.group(2);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("url", url);

            jsonArray.put(jsonObject);
        }

        return jsonArray;
    }
}