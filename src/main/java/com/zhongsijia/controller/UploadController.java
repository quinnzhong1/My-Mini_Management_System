package com.zhongsijia.controller;

import com.zhongsijia.pojo.Result;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class UploadController {
  @PostMapping("/upload")
  public Result upload(MultipartFile image) throws IOException {
    log.info("Upload file: {}" ,image.getOriginalFilename());
    String originalFilename = image.getOriginalFilename();
    int index = originalFilename.lastIndexOf(".");
    String extname = originalFilename.substring(index);
    String newFileName = UUID.randomUUID().toString() + extname;
    image.transferTo(new File("/Users/sijiazhong/Desktop/Java_code/images/" + newFileName));
    log.info("New file name: {}", newFileName);

    return Result.success(newFileName);
  }

}
