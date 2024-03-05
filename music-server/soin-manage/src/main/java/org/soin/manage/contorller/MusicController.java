package org.soin.manage.contorller;

import lombok.RequiredArgsConstructor;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.soin.manage.api.IMusicApi;
import org.soin.manage.api.dto.MusicCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:07
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicController implements IMusicApi {

    /**
     * 创建音乐
     *
     * @param audio          音频
     * @param image          图片
     * @param musicCreateDTO 音乐数据源
     * @return 是否创建成功
     */
    @Override
    public GenericResponse<Boolean> create(MultipartFile audio, MultipartFile image, MusicCreateDTO musicCreateDTO) {
        return null;
    }
}
