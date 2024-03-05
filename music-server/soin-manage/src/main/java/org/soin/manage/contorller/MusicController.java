package org.soin.manage.contorller;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.serivce.MusicService;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.base.common.RunTimeTool;
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

    private final MusicService musicService;

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
        RunTimeTool.printMethodMsg("create", "创建音乐", musicCreateDTO);
        Assert.isNull(audio, "请提供音乐音频");
        Assert.isNull(image, "请提供音频封面");
        Long singerId = musicCreateDTO.getSingerId();
        String musicName = musicCreateDTO.getMusicName();
        String resume = musicCreateDTO.getResume();
        String compose = musicCreateDTO.getCompose();
        boolean isOpen = musicService.create(audio, image, singerId, musicName, resume, compose);
        RunTimeTool.printMethodResponseMsg("create", isOpen);
        return GenericResponse.builder().success(isOpen);
    }
}
