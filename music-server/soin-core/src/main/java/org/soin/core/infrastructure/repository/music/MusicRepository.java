package org.soin.core.infrastructure.repository.music;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.bo.MusicBO;
import org.soin.core.domain.music.entity.Music;
import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.repository.IMusicRepository;
import org.soin.core.domain.music.vo.MusicComposeVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.mapper.music.MusicMapper;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.utils.AudioUtil;
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
     * @param params 歌曲查询数据源
     * @return 歌单列表
     */
    @Override
    public Page<MusicBO> page(MusicParams params) {
        Assert.isNull(params, "请提供查询数据源");
        Page<MusicBO> page = new Page<>(0, Lists.newArrayList());
        int totalRows = musicMapper.count(params);
        List<MusicBO> list = (totalRows > 0) ? musicMapper.page(params) : Lists.newArrayList();
        if (totalRows == 0) {
            return page;
        }
        List<MusicBO> musicVos = list.stream().peek(music -> {
            music.setImage(ImageUtil.generate(music.getImage()));
            music.setAudio(AudioUtil.generate(music.getAudio()));
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
        composeVo.setImage(ImageUtil.generate(composeVo.getImage()));
        return composeVo;
    }

    /**
     * 创建音乐
     *
     * @param musicName 音乐名称
     * @param resume    音乐简介
     * @param compose   歌词
     * @param audioId   音频库ID
     * @param imageId   图片库ID
     * @return 音乐主键
     */
    @Override
    public Long create(String musicName, String resume, String compose, Long audioId, Long imageId) {
        Assert.isBlank(musicName, "请提供音乐名称");
        Assert.isBlank(resume, "请提供音乐简介");
        Assert.isBlank(compose, "请提供歌词");
        Assert.isNull(audioId, "请提供视频库ID");
        Assert.isNull(imageId, "请提供图片库ID");
        Music music = new Music();
        music.setMusicName(musicName);
        music.setResume(resume);
        music.setCompose(compose);
        music.setAudioId(audioId);
        music.setImageId(imageId);
        music.setEnable(Boolean.TRUE);
        return music.getId();
    }
}


