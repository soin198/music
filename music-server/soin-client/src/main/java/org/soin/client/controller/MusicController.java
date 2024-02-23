package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.IMusicApi;
import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.serivce.MusicService;
import org.soin.core.domain.music.vo.MusicVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.base.common.RunTimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
        Assert.isNull(musicParams, "数据异常");
        Page<MusicVo> page = musicService.page(musicParams);
        RunTimeTool.printMethodResponseMsg("page", page.getTotalRows());
        return GenericResponse.builder().success(page);
    }
}
