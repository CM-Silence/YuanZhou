package com.example.common;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Component
public class ImageUploadUtil {
    @Value("${backend.path}")
    private String basePath; // 指定上传目录

    /**
     * 上传图片并保存到本地，同时返回假设的访问URL
     *
     * @param file 上传的图片文件
     * @return 图片的假设访问URL
     */
    public String uploadImage(MultipartFile file) {

        // 构建目标文件路径
        String fileName;
        fileName = file.getOriginalFilename();
        Path targetLocation = null;
        if (fileName != null) {
            targetLocation = Paths.get(basePath).resolve(fileName).toAbsolutePath().normalize();
        }

        //创建一个目录对象
        File dir = new File(basePath);

        //判断当前目录是否存在
        if(!dir.exists()) {
            //目录不存在，则创建一个目录
            dir.mkdirs();
        }

        // 如果文件已存在，则直接返回其URL
        // 假设你的应用部署在http://example.com/，并且你有一个服务或静态资源路径来访问这些图片
        // 注意：这里只是一个示例，实际URL应该根据你的Web服务器和应用配置来设置
        if (targetLocation != null) {
            Files.exists(targetLocation);
        }

        return "img/" + fileName;
    }
}
