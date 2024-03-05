package org.soin.core.domain.music.repository;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface IMusicConnectSingerRepository {

    /**
     * 音乐歌手关联
     *
     * @param musicId  音乐ID
     * @param singerId 歌手ID
     * @return 是否创建成功
     */
    boolean create(Long musicId, Long singerId);
}
