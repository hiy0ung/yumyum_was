package org.koreait.yumyum.service.implement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class MenuImageServiceImpl {

    @Value("${user.dir}")
    private String projectPath;

    public String uploadFile(MultipartFile file) {
        if (file == null) {
            return null;
        }

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + fileExtension;

        String rootPath = projectPath + "/upload/menu/profile/";
        String filePath = rootPath + newFileName;

        File fileDir = new File(rootPath);

        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        Path uploadPath = Paths.get(filePath);

        try {
            Files.write(uploadPath, file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
