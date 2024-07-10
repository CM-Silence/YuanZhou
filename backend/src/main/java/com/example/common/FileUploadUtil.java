package com.example.common;
import com.example.entity.FileInfo;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUploadUtil {
    private static final String UPLOAD_DIR = "D/yuan_zhou/data/files"; // 指定上传目录

    /**
     * 文件传入与转换
     *
     * @param files 上传文件
     * @return 文件合集
     */
    public static List<String> uploadFiles(MultipartFile[] files) {
        List<String> fileInfos = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (file.isEmpty()) {
                throw new IllegalArgumentException("One of the files is empty");
            }

            String originalFileName = file.getOriginalFilename();
            String baseName = "附件" + (i + 1);
            String fileName = (originalFileName != null && !originalFileName.isEmpty() ? "_" + originalFileName : "");
            Path targetLocation = Paths.get(UPLOAD_DIR).resolve(fileName).toAbsolutePath().normalize();

            String url = originalFileName; // 默认URL为原始文件名

            if (Files.exists(targetLocation)) {
                // 如果文件已存在，则修改URL
                url = fileName + " (existing file, not re-uploaded)";
            }

                // 创建FileInfo对象并添加到列表中
                FileInfo fileInfo = new FileInfo(baseName, url);
                fileInfos.add(fileInfo.toString());
            }

            return fileInfos;
        }
    }

