package org.soin.core.domain.music.repository;

import org.soin.core.domain.music.params.BackstageMusicParams;
import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.vo.BackstageMusicVo;
import org.soin.core.domain.music.vo.MusicComposeVo;
import org.soin.core.domain.music.vo.MusicVo;
import org.soin.core.infrastructure.base.common.Page;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface IMusicRepository {

    /**
     * 获取歌曲分页
     *
     * @param musicParams 歌曲查询数据源
     * @return 歌单列表
     */
    Page<MusicVo> page(MusicParams musicParams);

    /**
     * 获取歌曲分页列表
     *
     * @param params 分页查询数据源
     * @return 歌曲分页
     */
    Page<BackstageMusicVo> page(BackstageMusicParams params);

    /**
     * 根据歌曲ID获取评论页数据
     *
     * @param musicId 音乐ID
     * @return 评论页数据
     */
    MusicComposeVo musicComposeQuery(Long musicId);

    /**
     * 创建音乐
     *
     * @param musicName 音乐名称
     * @param resume    音乐简介
     * @param compose   歌词
     * @param audioId   音频库ID
     * @param imageId   图片库ID
     * @return 音乐主键
     */
    Long create(String musicName, String resume, String compose, Long audioId, Long imageId);
}
