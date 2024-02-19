package org.soin.core.infrastructure.repository.singer;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.singer.repository.ISingerRepository;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.mappers.SingerMapperService;
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

    private final SingerMapperService singerMapperService;

    /**
     * 随机获取歌手
     *
     * @param limit 查询条数
     * @return 歌手列表
     */
    @Override
    public List<SingerVo> singerQuery(Integer limit) {
        List<SingerVo> list = singerMapperService.getBaseMapper().singerQuery(limit);
        return (null != list && !list.isEmpty()) ? list : Lists.newArrayList();
    }
}


