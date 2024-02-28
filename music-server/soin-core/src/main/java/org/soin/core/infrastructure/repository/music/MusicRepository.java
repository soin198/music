package org.soin.core.infrastructure.repository.music;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.repository.IMusicRepository;
import org.soin.core.domain.music.vo.MusicComposeVo;
import org.soin.core.domain.music.vo.MusicVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.mapper.music.MusicMapper;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicRepository implements IMusicRepository {

    private final MusicMapper musicMapper;

    /**
     * 获取歌曲分页
     *
     * @param musicParams 歌曲查询数据源
     * @return 歌单列表
     */
    @Override
    public Page<MusicVo> page(MusicParams musicParams) {
        Assert.isNull(musicParams, "请提供查询数据源");
        int totalRows = musicMapper.count(musicParams);
        List<MusicVo> list = (totalRows > 0) ? musicMapper.page(musicParams) : Lists.newArrayList();
        //图片转换
        List<MusicVo> musicVos = list.stream().peek(music -> {
            String imagePath = music.getImagePath();
            String base64 = ImageUtil.generate(imagePath);
            music.setImagePath(base64);
            String audioPath = music.getAudioPath();
        }).collect(Collectors.toList());
        return new Page<>(totalRows, musicVos);
    }

    /**
     * 根据歌曲ID获取评论页数据
     *
     * @param musicId 音乐ID
     * @return 评论页数据
     */
    @Override
    public MusicComposeVo musicComposeQuery(Long musicId) {
        Assert.isNull(musicId, "musicId is null");
        MusicComposeVo composeVo = musicMapper.customQuery(musicId);
        Assert.isNull(composeVo, "数据异常");
        String base64 = ImageUtil.generate(composeVo.getImagePath());
        composeVo.setImagePath(base64);
        return composeVo;
    }
}


