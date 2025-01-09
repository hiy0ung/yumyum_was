package org.koreait.yumyum.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {

    @Value("${user.dir}")
    private String projectPath;

    public String uploadFile(MultipartFile file) {
        if(file == null || file.isEmpty()) { return null; }

        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String filePath = "profile/" + newFileName;
        String rootPath = projectPath + ("/upload/");

        File f = new File(rootPath, "profile");

        if(!f.exists()) { f.mkdirs(); }

        Path uploadPath = Paths.get(rootPath + filePath);

        try {
            Files.write(uploadPath, file.getBytes());
        }catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

}
