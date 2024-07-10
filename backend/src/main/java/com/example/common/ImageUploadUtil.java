package com.example.common;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUploadUtil {

    private static final String UPLOAD_DIR = "yuan_zhou/resources/files"; // 指定上传目录

    /**
     * 上传图片并保存到本地，同时返回假设的访问URL
     *
     * @param file 上传的图片文件
     * @return 图片的假设访问URL
     */
    public static String uploadImage(MultipartFile file) {

        // 构建目标文件路径
        String fileName;
        fileName = file.getOriginalFilename();
        Path targetLocation = null;
        if (fileName != null) {
            targetLocation = Paths.get(UPLOAD_DIR).resolve(fileName).toAbsolutePath().normalize();
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
