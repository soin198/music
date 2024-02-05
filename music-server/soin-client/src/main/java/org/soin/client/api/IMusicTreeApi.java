package org.soin.client.api;

import org.soin.core.domain.music.entity.MusicTree;
import org.soin.core.domain.music.vo.MusicTreeVo;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/music/tree")
public interface IMusicTreeApi {

    /**
     * 获取歌单列表
     *
     * @param musicType 歌单类型
     * @return 歌单列表
     */
    @GetMapping("/musicTreeQueryByType")
    GenericResponse<List<MusicTreeVo>> musicTreeQueryByType(@RequestParam(value = "musicType") MusicTree.Type musicType);

    /**
     * 随机获取歌单
     *
     * @return 随机获取歌单
     */
    @GetMapping("/musicTreeQuery")
    GenericResponse<List<MusicTreeVo>> musicTreeQuery();

}
