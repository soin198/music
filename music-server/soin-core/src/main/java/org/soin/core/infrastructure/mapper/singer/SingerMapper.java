package org.soin.core.infrastructure.mapper.singer;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.domain.singer.params.SingerParams;
import org.soin.core.domain.singer.vo.SingerVo;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:02
 **/
public interface SingerMapper extends BaseMapper<Singer> {

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
}
