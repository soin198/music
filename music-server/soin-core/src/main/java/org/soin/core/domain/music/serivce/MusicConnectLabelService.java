package org.soin.core.domain.music.serivce;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.repository.IMusicConnectLabelRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:20
 **/
@Service(value = "MusicConnectLabelService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicConnectLabelService {

    private final IMusicConnectLabelRepository iMusicConnectLabelRepository;

    /**
     * 音乐标签关联
     *
     * @param musicId 音乐ID
     * @param labelId 标签ID
     * @return 是否创建成功
     */
    protected boolean create(Long musicId, Long labelId) {
        Assert.isNull(musicId, "请提供音乐ID");
        Assert.isNull(labelId, "请提供标签ID");
        return iMusicConnectLabelRepository.insert(musicId, labelId);
    }


}
