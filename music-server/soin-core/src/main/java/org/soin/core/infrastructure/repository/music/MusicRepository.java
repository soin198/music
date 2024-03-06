package org.soin.core.infrastructure.repository.music;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.Music;
import org.soin.core.domain.music.params.BackstageMusicParams;
import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.repository.IMusicRepository;
import org.soin.core.domain.music.vo.BackstageMusicVo;
import org.soin.core.domain.music.vo.MusicComposeVo;
import org.soin.core.domain.music.vo.MusicVo;
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
     * @param musicParams 歌曲查询数据源
     * @return 歌单列表
     */
    @Override
    public Page<MusicVo> page(MusicParams musicParams) {
        Assert.isNull(musicParams, "请提供查询数据源");
        int totalRows = musicMapper.count(musicParams);
        List<MusicVo> list = (totalRows > 0) ? musicMapper.page(musicParams) : Lists.newArrayList();
        List<MusicVo> musicVos = list.stream().peek(music -> {
            //图片转换
            String imagePath = music.getImage();
            String base64 = ImageUtil.generate(imagePath);
            music.setImage(base64);
            //音频转换
            String audioPath = music.getAudio();
            String audio64 = AudioUtil.generate(audioPath);
            music.setAudio(audio64);
        }).collect(Collectors.toList());
        return new Page<>(totalRows, musicVos);
    }

    /**
     * 获取歌曲分页列表
     *
     * @param params 分页查询数据源
     * @return 歌曲分页
     */
    @Override
    public Page<BackstageMusicVo> page(BackstageMusicParams params) {
        Assert.isNull(params, "请提供查询数据源");
        int totalRows = musicMapper.backstageCount(params);
        List<BackstageMusicVo> list = (totalRows > 0) ? musicMapper.backstagePage(params) : Lists.newArrayList();
        list = list.stream().peek(item -> item.setImage(ImageUtil.generate(item.getImage()))).collect(Collectors.toList());
        return new Page<>(totalRows, list);
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


