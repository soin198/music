package org.soin.core.domain.singer.repository;

import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.domain.singer.params.SingerParams;
import org.soin.core.domain.singer.vo.SingerVo;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface ISingerRepository {

    /**
     * 获取歌手列表
     *
     * @param singerParams 歌手列表查询数据源
     * @return 歌手列表
     */
    List<SingerVo> singerQuery(SingerParams singerParams);

    /**
     * 统计总歌手数量
     *
     * @param singerParams 统计数据源
     * @return 总歌手数量
     */
    int count(SingerParams singerParams);

    /**
     * 根据歌手ID获取歌手详情
     *
     * @param singerId 歌手ID
     * @return 歌手数据
     */
    Singer getOneByKeyId(Long singerId);
}
