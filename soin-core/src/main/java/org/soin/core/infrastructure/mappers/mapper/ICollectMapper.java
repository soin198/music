package org.soin.core.infrastructure.mappers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.soin.core.domain.music.entity.Collect;

public interface ICollectMapper extends BaseMapper<Collect> {

    Collect byUserIdAndMusicIdGet(Long userId, Long musicId);
}
