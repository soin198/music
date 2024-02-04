package org.soin.core.domain.music.serivce;

/**
 * 收藏歌曲接口
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 09:52
 **/
public interface ICollectService {

    /**
     * 收藏音乐
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     * @return 是否收藏成功
     */
    boolean insertCollectMusic(Long userId, Long musicId);
}
