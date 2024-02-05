package org.soin.client.api;

import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/base/singer")
public interface ISingerApi {

    /**
     * 随机获取20个歌手
     *
     * @return 歌手列表
     */
    @PostMapping("/singerQuery")
    GenericResponse<List<SingerVo>> singerQuery();

}
