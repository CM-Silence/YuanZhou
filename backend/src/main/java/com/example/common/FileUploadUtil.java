package com.example.common;
import com.example.entity.FileInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUploadUtil {
    @Value("${backend.path}")
    private String basePath; // 指定上传目录
    private String filePath = "files\\";

    /**
     * 文件传入与转换
     *
     * @param files 上传文件
     * @return 文件合集
     */
    public List<String> uploadFiles(MultipartFile[] files) {
        List<String> fileInfos = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            if (file.isEmpty()) {
                throw new IllegalArgumentException("One of the files is empty");
            }

            String originalFileName = file.getOriginalFilename();
            String baseName = "附件" + (i + 1);
            String fileName = (originalFileName != null && !originalFileName.isEmpty() ? originalFileName : "");

            StringBuilder url = new StringBuilder(basePath + filePath + originalFileName); // 默认URL为原始文件名

            //创建一个目录对象
            File dir = new File(basePath + filePath);

            //判断当前目录是否存在
            if (!dir.exists()) {
                //目录不存在，则创建一个目录
                dir.mkdirs();
            }

            try {
                //将临时文件转存到指定位置
                file.transferTo(new File(basePath + filePath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 创建FileInfo对象并添加到列表中
            FileInfo fileInfo = new FileInfo(baseName, url.toString());
            fileInfos.add(fileInfo.toString());
        }

        return fileInfos;
    }
}


