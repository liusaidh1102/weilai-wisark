package com.weilai.user.controller;
import com.weilai.common.response.Result;
import com.weilai.minio.entity.FileInfo;
import com.weilai.minio.enums.PolicyType;
import com.weilai.minio.exceptions.MinioServiceException;
import com.weilai.minio.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/sys/user")
@Tag(name = "用户管理", description = "用户管理、管理员管理、在线用户等")
public class SysUserController {

    @Resource
    private FileService fileService;



    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) {
        try {
            fileService.createBucket("user", PolicyType.READ_ONLY);
            FileInfo fileInfo = fileService.uploadFile(file);
            return Result.ok(fileInfo);
        } catch (MinioServiceException e) {
            throw new RuntimeException(e);
        }
    }
}