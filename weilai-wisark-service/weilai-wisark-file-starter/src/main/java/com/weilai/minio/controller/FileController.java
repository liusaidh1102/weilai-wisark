package com.weilai.minio.controller;
import com.weilai.common.response.Result;
import com.weilai.minio.entity.FileInfo;
import com.weilai.minio.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Tag(name = "文件上传", description = "文件上传接口")
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping(value = "/upload")
    // 设置文件上传
    @Operation(summary = "上传文件")
    public Result<FileInfo> uploadFile(@RequestParam("file") MultipartFile file) {
        FileInfo fileInfo = fileService.uploadFile(file);
        if (fileInfo == null) {
            return Result.fail("服务异常，文件上传失败");
        }
        return Result.ok(fileInfo);
    }
}