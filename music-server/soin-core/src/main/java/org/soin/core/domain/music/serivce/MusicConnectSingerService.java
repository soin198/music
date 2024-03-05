package org.soin.core.domain.music.serivce;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.repository.IMusicConnectSingerRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:20
 **/
@Service(value = "MusicConnectSingerService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicConnectSingerService {

    private final IMusicConnectSingerRepository iMusicConnectSingerRepository;

    /**
     * 音乐歌手关联
     *
     * @param musicId  音乐ID
     * @param singerId 歌手ID
     * @return 是否创建成功
     */
    protected boolean create(Long musicId, Long singerId) {
        Assert.isNull(musicId, "请提供音乐ID");
        Assert.isNull(singerId, "请提供歌手ID");
        return iMusicConnectSingerRepository.create(musicId, singerId);
    }


}
