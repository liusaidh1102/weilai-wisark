package com.weilai.minio.controller;
import com.weilai.common.response.Result;
import com.weilai.minio.entity.File;
import com.weilai.minio.entity.FileDTO;
import com.weilai.minio.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/file")
@Tag(name = "文件上传", description = "文件上传接口")
public class FileController {

    @Resource
    private FileService fileService;


    @PostMapping(value = "/upload")
    // 设置文件上传
    @Operation(summary = "上传文件")
    public Result<File> uploadFile(@RequestParam("file") MultipartFile file) {
        File fileInfo = fileService.uploadFile(file);
        if (fileInfo == null) {
            return Result.fail("服务异常，文件上传失败");
        }
        return Result.ok(fileInfo);
    }


    /**
     * 校验文件是否存在
     * @param md5 String
     */
    @GetMapping("/check/{md5}")
    @Operation(summary = "判断文件是不是存在")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "700", description = "文件不存在"),
            @ApiResponse(responseCode = "704", description = "上传成功"),
            @ApiResponse(responseCode = "701", description = "断点续传")
    })
    public Result<File> checkFile(@PathVariable("md5") String md5) {
        return fileService.checkFile(md5);
    }

    /**
     * 分片初始化
     * @param fileDTO 文件信息
     * @return Result<Object>
     */
    @PostMapping("/init")
    @Operation(summary = "初始化文件上传")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "703", description = "初始化失败"),
            @ApiResponse(responseCode = "705", description = "初始化成功")
    })
    public Result<?> initMultiPartUpload(@RequestBody @Validated FileDTO fileDTO) {
        return fileService.initMultiPartUpload(fileDTO);
    }


    /**
     * 完成上传
     * @return Result<Object>
     */
    @PostMapping("/merge/{md5}")
    @Operation(summary = "合并上传任务")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "703", description = "上传失败"),
            @ApiResponse(responseCode = "704", description = "上传成功")
    })
    public Result<File> completeMultiPartUpload(@PathVariable String md5) {
        //合并文件
        return fileService.mergeMultipartUpload(md5);
    }







}