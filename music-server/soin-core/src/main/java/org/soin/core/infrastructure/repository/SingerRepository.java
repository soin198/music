package org.soin.core.infrastructure.repository;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.singer.repository.ISingerRepository;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.mappers.mapper.SingerMapper;
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

    /**
     * 随机获取20个歌手
     *
     * @return 歌手列表
     */
    @Override
    public List<SingerVo> singerQuery() {
        List<SingerVo> list = singerMapper.singerQuery();
        return (null != list && !list.isEmpty()) ? list : Lists.newArrayList();
    }
}


