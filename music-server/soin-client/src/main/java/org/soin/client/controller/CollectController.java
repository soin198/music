package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 收藏歌曲控制层
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 18:13
 **/
@RestController
public class CollectController {

/*    private final ICollectService collectService;

    *//**
     * 添加收藏歌曲
     *
     * @param musicId 音乐ID
     * @return 是否添加成功
     *//*
    @PostMapping("/insertCollectMusic")
    public GenericResponse<?> insertCollectMusic(@RequestParam(value = "musicId") Long musicId) {
        Assert.notNull(musicId, "请正确选择需要收藏的歌曲");
        boolean isOpen = collectService.insertCollectMusic(null, musicId);
        return GenericResponse.builder().success(isOpen);
    }*/
}
