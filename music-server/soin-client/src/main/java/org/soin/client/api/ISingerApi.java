package org.soin.client.api;

import org.soin.core.domain.singer.params.SingerParams;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/base/singer")
public interface ISingerApi {

    /**
     * 获取歌手分页列表
     *
     * @param singerParams 歌手列表查询数据源
     * @return 歌手分页列表
     */
    @PostMapping("/singerQuery")
    GenericResponse<Page<SingerVo>> singerQuery(@RequestBody SingerParams singerParams);

    /**
     * 根据歌手ID获取歌手详情
     *
     * @param singerId 歌手ID
     * @return 歌手数据
     */
    @GetMapping("/singerQueryById")
    GenericResponse<SingerVo> singerQueryById(@RequestParam(value = "singerId") Long singerId);

}
