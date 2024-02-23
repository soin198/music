package org.soin.client.api;

import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.vo.MusicVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-21 15:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/music")
public interface IMusicApi {

    /**
     * 根据歌手ID获取歌手歌单列表
     *
     * @param musicParams 歌曲查询分页
     * @return 歌单列表
     */
    @PostMapping("/page")
    GenericResponse<Page<MusicVo>> page(@RequestBody MusicParams musicParams);

}