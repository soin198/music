package org.soin.core.domain.music.repository;


import org.soin.core.domain.music.entity.MusicLike;

import java.util.Optional;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface IMusicLikeRepository {

    /**
     * 创建我喜欢数据
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     * @return 是否保存成功
     */
    boolean insert(Long userId, Long musicId);

    /**
     * 取消我喜欢
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否取消成功
     */
    boolean cancelLike(Long userId, Long musicId);

    /**
     * 获取歌曲收藏数据
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 歌曲喜欢数据
     */
    Optional<MusicLike> getOne(Long userId, Long musicId);
}
