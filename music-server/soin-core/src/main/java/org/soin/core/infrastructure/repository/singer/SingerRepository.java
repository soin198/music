package org.soin.core.infrastructure.repository.singer;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.domain.singer.params.SingerParams;
import org.soin.core.domain.singer.repository.ISingerRepository;
import org.soin.core.domain.singer.vo.SingerVo;
import org.soin.core.infrastructure.mapper.singer.SingerMapper;
import org.soin.core.infrastructure.utils.Assert;
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
     * 获取歌手列表
     *
     * @param singerParams 歌手列表查询数据源
     * @return 歌手列表
     */
    @Override
    public List<SingerVo> singerQuery(SingerParams singerParams) {
        List<SingerVo> list = singerMapper.singerQuery(singerParams);
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

    /**
     * 根据歌手ID获取歌手详情
     *
     * @param singerId 歌手ID
     * @return 歌手数据
     */
    @Override
    public Singer getOneByKeyId(Long singerId) {
        Assert.isNull(singerId, "请提供singerId");
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();
        wrapper.eq("id", singerId);
        return singerMapper.selectOne(wrapper);
    }
}


