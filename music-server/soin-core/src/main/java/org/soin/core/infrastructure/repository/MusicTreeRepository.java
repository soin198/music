package org.soin.core.infrastructure.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.MusicTree;
import org.soin.core.domain.music.repository.IMusicTreeRepository;
import org.soin.core.domain.music.vo.MusicTreeVo;
import org.soin.core.infrastructure.mappers.mapper.IMusicTreeMapper;
import org.soin.core.infrastructure.utils.ConvertUtil;
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
public class MusicTreeRepository implements IMusicTreeRepository {

    private final IMusicTreeMapper iMusicTreeMapper;

    /**
     * 获取所有启用歌单
     *
     * @param musicType 歌单类型
     * @return 歌单列表
     */
    @Override
    public List<MusicTreeVo> musicTreeQueryByType(MusicTree.Type musicType) {
        QueryWrapper<MusicTree> wrapper = new QueryWrapper<>();
        wrapper.eq("enable", Boolean.TRUE);
        wrapper.eq("type", musicType);
        List<MusicTree> list = iMusicTreeMapper.selectList(wrapper);
        if (null == list) {
            return Lists.newArrayList();
        }
        return ConvertUtil.converts(list, MusicTreeVo.class);
    }

    /**
     * 随机获取20个歌单
     *
     * @return 歌单列表
     */
    @Override
    public List<MusicTreeVo> musicTreeQuery() {
        List<MusicTreeVo> list = iMusicTreeMapper.musicTreeQuery();
        return (null != list && list.size() > 0) ? list : Lists.newArrayList();
    }

}


