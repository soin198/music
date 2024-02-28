package org.soin.core.domain.music.repository;

import org.soin.core.domain.music.params.MusicParams;
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
     * 根据歌曲ID获取评论页数据
     *
     * @param musicId 音乐ID
     * @return 评论页数据
     */
    MusicComposeVo musicComposeQuery(Long musicId);
}
