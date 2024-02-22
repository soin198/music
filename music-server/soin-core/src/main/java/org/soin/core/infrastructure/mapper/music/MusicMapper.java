package org.soin.core.infrastructure.mapper.music;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.soin.core.domain.music.entity.Music;
import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.vo.MusicVo;

import java.util.List;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:02
 **/
public interface MusicMapper extends BaseMapper<Music> {

    /**
     * 统计歌曲总数
     *
     * @param musicParams 歌曲统计数据源
     * @return 歌曲总数
     */
    int count(MusicParams musicParams);

    /**
     * 获取歌曲分页
     *
     * @param musicParams 歌曲查询数据源
     * @return 歌单列表
     */
    List<MusicVo> page(MusicParams musicParams);

}
