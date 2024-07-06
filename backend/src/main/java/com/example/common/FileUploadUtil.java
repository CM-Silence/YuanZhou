package com.example.common;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {

    private static final String UPLOAD_DIR = "D:\\code\\YuanZhou\\backend\\src\\main\\resources\\static\\files"; // 指定上传目录

    /**
     * 上传图片并保存到本地，同时返回假设的访问URL
     *
     * @param file 上传的文件
     * @return 文件的假设访问URL
     * @throws IOException 如果保存文件时发生错误
     */
    public static String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // 构建目标文件路径
        String fileName = file.getOriginalFilename();
        Path targetLocation = Paths.get(UPLOAD_DIR).resolve(fileName).toAbsolutePath().normalize();

        // 如果文件已存在，则直接返回其URL
        if (Files.exists(targetLocation)) {
            // 假设你的应用部署在http://example.com/，并且你有一个服务或静态资源路径来访问这些图片
            // 注意：这里只是一个示例，实际URL应该根据你的Web服务器和应用配置来设置
            return "files/" + fileName;
        }
        // 确保目标目录存在
        Files.createDirectories(targetLocation.getParent());

        // 保存文件
        Files.copy(file.getInputStream(), targetLocation);
        return "files/" + fileName;
    }
}
