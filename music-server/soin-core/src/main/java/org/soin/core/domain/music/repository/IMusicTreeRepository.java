package org.soin.core.domain.music.repository;

import org.soin.core.domain.music.entity.MusicTree;
import org.soin.core.domain.music.vo.MusicTreeVo;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface IMusicTreeRepository {

    /**
     * 获取所有启用歌单
     *
     * @param musicType 歌单类型
     * @return 歌单列表
     */
    List<MusicTreeVo> musicTreeQueryByType(MusicTree.Type musicType);

    /**
     * 随机获取歌单
     *
     * @param limit 查询条数
     * @return 歌单列表
     */
    List<MusicTreeVo> musicTreeQuery(Integer limit);
}
