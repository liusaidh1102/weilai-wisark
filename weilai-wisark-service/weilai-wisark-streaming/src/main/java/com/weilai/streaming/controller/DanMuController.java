package com.weilai.streaming.controller;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.DanMu;
import com.weilai.streaming.service.DanMuService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/danmu")
@Tag(name = "弹幕模块", description = "弹幕相关接口")
public class DanMuController {
    @Resource
    private DanMuService danMuService;
    @GetMapping("/list/{roomId}")
    public Result<List<DanMu>> getDanMuList(@PathVariable String roomId) {
        return danMuService.getDanMuList(roomId);
    }
    @PostMapping("/add")
    public Result<String> addDanMu(@RequestBody DanMu danMu) {
       return danMuService.addDanMu(danMu);
    }
}
