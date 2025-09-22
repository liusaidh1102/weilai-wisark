package com.weilai.user.controller;
import com.kangaroohy.minio.enums.PolicyType;
import com.kangaroohy.minio.exceptions.MinioServiceException;
import com.kangaroohy.minio.service.MinioService;
import com.weilai.common.response.Result;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/sys/user")
@Tag(name = "用户管理", description = "用户管理、管理员管理、在线用户等")
public class SysUserController {

    @Resource
    private MinioService minioService;



    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) {
        try {
            minioService.createBucket("user", PolicyType.READ_ONLY);
            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
            minioService.putObject(file.getOriginalFilename(), file.getContentType(),file.getInputStream());
            String url = minioService.getObjectUrl(file.getName());
            return Result.ok(url);
        } catch (MinioServiceException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}