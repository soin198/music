package org.soin.client.controller;

import lombok.RequiredArgsConstructor;
import org.soin.client.api.IMusicTreeApi;
import org.soin.core.domain.music.entity.MusicTree;
import org.soin.core.domain.music.serivce.MusicTreeService;
import org.soin.core.domain.music.vo.MusicTreeVo;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.core.infrastructure.utils.RunTimeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 18:13
 **/
@ResponseBody
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicTreeController implements IMusicTreeApi {

    private final MusicTreeService musicTreeService;

    /**
     * 获取所有启用歌单
     *
     * @param musicType 歌单类型
     * @return 歌单列表
     */
    @Override
    public GenericResponse<List<MusicTreeVo>> musicTreeQueryByType(MusicTree.Type musicType) {
        RunTimeTool.printMethodMsg("musicTreeQueryByType", "根据歌单类型获取歌单", musicType);
        List<MusicTreeVo> list = musicTreeService.musicTreeQueryByType(musicType);
        RunTimeTool.printMethodResponseMsg("musicTreeQueryByType", list.size());
        return GenericResponse.builder().success(list);
    }

    /**
     * 随机获取歌单
     *
     * @return 随机获取歌单
     */
    @Override
    public GenericResponse<List<MusicTreeVo>> musicTreeQuery() {
        RunTimeTool.printMethodMsg("musicTreeQuery", "随机获取歌单");
        List<MusicTreeVo> list = musicTreeService.musicTreeQuery();
        RunTimeTool.printMethodResponseMsg("musicTreeQuery", list);
        return GenericResponse.builder().success(list);
    }

}
