package org.soin.core.infrastructure.mappers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.domain.singer.vo.SingerVo;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:02
 **/
public interface ISingerMapper extends BaseMapper<Singer> {

    /**
     * 随机获取20个歌手
     *
     * @return 歌手列表
     */
    List<SingerVo> singerQuery();
}
