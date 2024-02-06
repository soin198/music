package org.soin.core.domain.singer.repository;

import org.soin.core.domain.singer.vo.SingerVo;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface ISingerRepository {

    /**
     * 随机获取歌手
     *
     * @param limit 查询条数
     * @return 歌手列表
     */
    List<SingerVo> singerQuery(Integer limit);
}
