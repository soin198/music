package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.IMusicLikeApi;
import org.soin.core.domain.music.serivce.MusicLikeService;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 我喜欢
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 18:13
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicLikeController implements IMusicLikeApi {

    private final MusicLikeService musicLikeService;

    /**
     * 添加我喜欢
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否添加成功
     */
    @Override
    public GenericResponse<Boolean> saveLike(Long userId, Long musicId) {
        RunTimeTool.printMethodMsg("saveLike", String.format("用户ID：%s，添加我喜欢，歌曲ID：%s", userId, musicId));
        boolean isOpen = musicLikeService.saveLike(userId, musicId);
        RunTimeTool.printMethodResponseMsg("saveLike", isOpen);
        return GenericResponse.builder().success(isOpen);
    }

    /**
     * 取消我喜欢
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否取消成功
     */
    @Override
    public GenericResponse<Boolean> cancelLike(Long userId, Long musicId) {
        RunTimeTool.printMethodMsg("cancelLike", String.format("用户ID：%s，取消我喜欢，歌曲ID：%s", userId, musicId));
        boolean isOpen = musicLikeService.cancelLike(userId, musicId);
        RunTimeTool.printMethodResponseMsg("cancelLike", isOpen);
        return GenericResponse.builder().success(isOpen);
    }

    /**
     * 验证是否喜欢此音乐
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否喜欢
     */
    @Override
    public GenericResponse<Boolean> isLike(Long userId, Long musicId) {
        RunTimeTool.printMethodMsg("isLike", String.format("用户ID：%s，验证歌曲%s是否添加到我喜欢", userId, musicId));
        boolean isOpen = musicLikeService.getOne(userId, musicId);
        RunTimeTool.printMethodResponseMsg("isLike", isOpen);
        return GenericResponse.builder().success(isOpen);
    }

}
