package com.coconut.controller;

import com.coconut.common.Result;
import org.apache.velocity.shaded.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${file-dir}")
    private String fileDir;

    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            file.transferTo(Paths.get(fileDir + filename));
        } catch (IOException e) {
            return Result.fail("Upload failed");
        }
        return Result.success(filename);
    }

}
