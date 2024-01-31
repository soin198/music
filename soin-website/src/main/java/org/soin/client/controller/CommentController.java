package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.core.infrastructure.base.GenericResponse;
import org.soin.core.infrastructure.base.Page;
import org.soin.client.controller.form.CommentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 评论相关控制层
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:30
 **/
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    /**
     * 评论服务
     *//*
    private final ICommentService commentService;

    *//**
     * 提交对应歌曲评论
     *
     * @param submitParams 提交评论参数
     * @return 评论是否提交成功
     *//*
    @PostMapping("/submit")
    public GenericResponse<?> submitComment(@RequestBody @Valid CommentForm submitParams) {
        Assert.notNull(submitParams, "评论内容不可为空!");
        boolean isOpen = commentService.submit(submitParams);
        return GenericResponse.builder().success(isOpen);
    }

    *//**
     * 根据主键删除对应评论
     *
     * @param keyId 删除对应评论
     * @return 是否删除成功
     *//*
    @GetMapping("/delete")
    public GenericResponse<?> deleteByKey(@RequestParam(value = "keyId") Long keyId) {
        boolean isOpen = commentService.deleteByKey(keyId);
        return GenericResponse.builder().success(isOpen);
    }

    *//**
     * 对评论点赞
     *
     * @param keyId 评论主键
     * @return 是否点赞成功
     *//*
    @PostMapping("/like")
    public GenericResponse<?> commentOfLike(@RequestParam(value = "keyId") Long keyId) {
        Assert.notNull(keyId, "请正确选择需要点赞的评论");
        boolean isOpen = commentService.commentOfLike(keyId);
        return GenericResponse.builder().success(isOpen);
    }

    *//**
     * 获取歌曲的评论列表
     *
     * @param musicId 音乐ID
     * @return 对应的评论列表
     *//*
    @GetMapping("/getCommentsByMusicId")
    public GenericResponse<?> getCommentsByMusicId(@RequestParam(value = "musicId") Long musicId) {
        Assert.notNull(musicId, "请提供对应的音乐ID");
        Page<Object> list = commentService.getCommentsByMusicId(musicId);
        return GenericResponse.builder().success(list);
    }*/

}
