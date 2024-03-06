package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.IMusicApi;
import org.soin.client.api.vo.MusicVo;
import org.soin.core.domain.music.bo.MusicBO;
import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.serivce.MusicService;
import org.soin.core.domain.music.vo.MusicComposeVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.soin.core.infrastructure.utils.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 歌曲控制层
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:20
 **/
@ResponseBody
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicController implements IMusicApi {

    private final MusicService musicService;

    /**
     * 获取歌曲分页
     *
     * @param musicParams 歌曲查询数据源
     * @return 歌单列表
     */
    @Override
    public GenericResponse<Page<MusicVo>> page(MusicParams musicParams) {
        RunTimeTool.printMethodMsg("page", "获取歌曲分页列表", musicParams);
        Page<MusicBO> page = musicService.page(musicParams);
        int rows = page.getTotalRows();
        List<MusicBO> list = page.getData();
        RunTimeTool.printMethodResponseMsg("page", rows);
        return GenericResponse.builder().success(new Page<>(rows, ConvertUtil.converts(list, MusicVo.class)));
    }

    /**
     * 根据歌曲ID获取评论页数据
     *
     * @param musicId 音乐ID
     * @return 评论页数据
     */
    @Override
    public GenericResponse<MusicComposeVo> musicComposeQuery(Long musicId) {
        RunTimeTool.printMethodMsg("musicComposeQuery", "获取歌曲评论页数据", musicId);
        MusicComposeVo musicComposeVo = musicService.musicComposeQuery(musicId);
        RunTimeTool.printMethodResponseMsg("musicComposeQuery", musicComposeVo.getMusicName());
        return GenericResponse.builder().success(musicComposeVo);
    }
}
