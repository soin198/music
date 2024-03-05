package org.soin.manage.api;

import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.manage.api.dto.MusicCreateDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:37
 **/
@RequestMapping(BaseConstant.BASE_BACKSTAGE_SERVICE_URL + "/music")
public interface IMusicApi {

    /**
     * 创建音乐
     *
     * @param audio          音频
     * @param image          图片
     * @param musicCreateDTO 音乐数据源
     * @return 是否创建成功
     */
    @PostMapping("/create")
    GenericResponse<Boolean> create(@RequestParam(value = "audio") MultipartFile audio,
                                    @RequestParam(value = "image") MultipartFile image,
                                    @RequestBody MusicCreateDTO musicCreateDTO);

}
