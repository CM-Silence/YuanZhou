package com.example.common;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class ImageUploadUtil {
    private final String BASE_URL = System.getProperty("user.dir"); // 指定上传目录
    private final String FILE_URL = "/static/img/";

    /**
     * 上传图片并保存到本地，同时返回假设的访问URL
     *
     * @param file 上传的图片文件
     * @return 图片的假设访问URL
     */
    public String uploadImage(MultipartFile file) {

        // 构建目标文件路径
        String fileName = file.getOriginalFilename();

        String url = FILE_URL + fileName; // 默认URL为原始文件名
        //创建一个目录对象
        File dir = new File(BASE_URL + FILE_URL);

        //判断当前目录是否存在
        if(!dir.exists()) {
            //目录不存在，则创建一个目录
            dir.mkdirs();
        }

        try {
            //将临时文件转存到指定位置
            file.transferTo(new File(BASE_URL + FILE_URL + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return url;
    }
}
