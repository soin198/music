package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.ICommentApi;
import org.soin.client.api.dto.CommentDTO;
import org.soin.core.domain.music.params.MusicCommentParams;
import org.soin.core.domain.music.serivce.CommentService;
import org.soin.core.domain.music.serivce.MusicService;
import org.soin.core.domain.music.vo.MusicCommentVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 评论相关控制层
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:30
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController implements ICommentApi {

    /**
     * 音乐服务
     */
    private final MusicService musicService;

    /**
     * 评论服务
     */
    private final CommentService commentService;

    /**
     * 获取歌曲评论列表
     *
     * @param params 查询数据源
     * @return 评论列表
     */
    @Override
    public GenericResponse<Page<MusicCommentVo>> page(MusicCommentParams params) {
        RunTimeTool.printMethodMsg("page", "获取歌曲评论列表", params);
        Page<MusicCommentVo> page = commentService.page(params);
        RunTimeTool.printMethodResponseMsg("page", page.getTotalRows());
        return GenericResponse.builder().success(page);
    }

    /**
     * 评论歌曲
     *
     * @param params 评论参数
     * @return 是否评论成功
     */
    @Override
    public GenericResponse<Boolean> submitComment(CommentDTO params) {
        RunTimeTool.printMethodMsg("submitComment", "提交歌曲评论", params);
        Long userId = params.getUserId();
        Long musicId = params.getMusicId();
        String content = params.getContent();
        boolean isOpen = musicService.submitComment(userId, musicId, content);
        RunTimeTool.printMethodResponseMsg("submitComment", isOpen);
        return GenericResponse.builder().success(isOpen);
    }

    /**
     * 评论点赞
     *
     * @param commentId 评论ID
     * @return 是否点赞成功
     */
    @Override
    public GenericResponse<Boolean> click(Long commentId) {
        RunTimeTool.printMethodMsg("click", String.format("评论：%s点赞", commentId));
        boolean isOpen = musicService.click(commentId);
        RunTimeTool.printMethodResponseMsg("click", isOpen);
        return GenericResponse.builder().success(isOpen);
    }

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 是否删除成功
     */
    @Override
    public GenericResponse<Boolean> omit(Long commentId) {
        RunTimeTool.printMethodMsg("omit", "删除评论", commentId);
        boolean isOpen = musicService.omit(commentId);
        RunTimeTool.printMethodResponseMsg("omit", isOpen);
        return GenericResponse.builder().success(isOpen);
    }
}
