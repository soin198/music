package org.soin.core.infrastructure.repository.singer;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.singer.params.SingerParams;
import org.soin.core.domain.singer.repository.ISingerRepository;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.mappers.SingerMapperService;
import org.soin.core.infrastructure.mappers.mapper.singer.SingerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SingerRepository implements ISingerRepository {

    private final SingerMapper singerMapper;

    private final SingerMapperService singerMapperService;

    /**
     * 获取歌手列表
     *
     * @param singerParams 歌手列表查询数据源
     * @return 歌手列表
     */
    @Override
    public List<SingerVo> singerQuery(SingerParams singerParams) {
        List<SingerVo> list = singerMapperService.getBaseMapper().singerQuery(singerParams);
        return (null != list && !list.isEmpty()) ? list : Lists.newArrayList();
    }

    /**
     * 统计总歌手数量
     *
     * @param singerParams 统计数据源
     * @return 总歌手数量
     */
    @Override
    public int count(SingerParams singerParams) {
        return singerMapper.count(singerParams);
    }
}


