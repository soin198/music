package org.soin.client.api;

import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-27 15:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/music")
public interface IMusicLikeApi {

    /**
     * 收藏歌曲
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否收藏成功
     */
    @PostMapping("/saveLike")
    GenericResponse<Boolean> saveLike(@RequestParam(value = "userId") Long userId,
                                      @RequestParam(value = "musicId") Long musicId);

    /**
     * 取消我喜欢
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否取消成功
     */
    @PostMapping("/cancelLike")
    GenericResponse<Boolean> cancelLike(@RequestParam(value = "userId") Long userId,
                                        @RequestParam(value = "musicId") Long musicId);


}
