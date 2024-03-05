package org.soin.core.infrastructure.repository.music;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.MusicConnectSinger;
import org.soin.core.domain.music.repository.IMusicConnectSingerRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.mapper.music.MusicConnectSingerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicConnectSingerRepository implements IMusicConnectSingerRepository {

    private final MusicConnectSingerMapper musicConnectSingerMapper;

    /**
     * 音乐歌手关联
     *
     * @param musicId  音乐ID
     * @param singerId 歌手ID
     * @return 是否创建成功
     */
    @Override
    public boolean create(Long musicId, Long singerId) {
        Assert.isNull(musicId, "请提供音乐ID");
        Assert.isNull(singerId, "请提供歌手ID");
        MusicConnectSinger singer = new MusicConnectSinger();
        singer.setMusicId(musicId);
        singer.setSingerId(singerId);
        return (musicConnectSingerMapper.insert(singer) > 0);
    }
}


