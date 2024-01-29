package org.soin.core.biz.mapper;

import org.soin.core.biz.entity.Collect;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectMapper {

    int insert(Collect record);

    Collect byUserIdAndMusicIdGet(Long userId, Long musicId);
}
