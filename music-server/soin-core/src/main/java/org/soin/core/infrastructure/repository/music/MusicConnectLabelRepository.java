package org.soin.core.infrastructure.repository.music;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.MusicConnectLabel;
import org.soin.core.domain.music.repository.IMusicConnectLabelRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.mapper.music.MusicConnectLabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicConnectLabelRepository implements IMusicConnectLabelRepository {

    private final MusicConnectLabelMapper musicConnectLabelMapper;

    /**
     * 音乐标签关联
     *
     * @param musicId 音乐ID
     * @param labelId 标签ID
     * @return 是否创建成功
     */
    @Override
    public boolean insert(Long musicId, Long labelId) {
        Assert.isNull(musicId, "请提供音乐ID");
        Assert.isNull(labelId, "请提供标签ID");
        MusicConnectLabel label = new MusicConnectLabel();
        label.setMusicId(musicId);
        label.setLabelId(labelId);
        return (musicConnectLabelMapper.insert(label) > 0);
    }
}


