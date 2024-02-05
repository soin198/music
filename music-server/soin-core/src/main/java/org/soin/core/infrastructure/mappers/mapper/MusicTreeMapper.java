package org.soin.core.infrastructure.mappers.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.soin.core.domain.music.entity.MusicTree;
import org.soin.core.domain.music.vo.MusicTreeVo;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:02
 **/
public interface MusicTreeMapper extends BaseMapper<MusicTree> {

    /**
     * 随机获取歌单
     *
     * @return 歌单列表
     */
    List<MusicTreeVo> musicTreeQuery();
}
