package org.soin.core.infrastructure.mappers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.domain.singer.vo.SingerVo;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:02
 **/
public interface SingerMapper extends BaseMapper<Singer> {

    /**
     * 随机获取歌手
     *
     * @param limit 查询条数
     * @return 歌手列表
     */
    List<SingerVo> singerQuery(@Param("limit") Integer limit);
}
