package org.soin.core.domain.music.serivce;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.entity.AudioDataBase;
import org.soin.core.domain.database.entity.ImageDataBase;
import org.soin.core.domain.database.service.AudioDataService;
import org.soin.core.domain.database.service.ImageDataService;
import org.soin.core.domain.music.params.BackstageMusicParams;
import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.repository.IMusicRepository;
import org.soin.core.domain.music.vo.BackstageMusicVo;
import org.soin.core.domain.music.vo.MusicComposeVo;
import org.soin.core.domain.music.vo.MusicVo;
import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.domain.singer.serivce.SingerService;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:20
 **/
@Service(value = "MusicService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicService {

    private final IMusicRepository iMusicRepository;

    /**
     * 评论服务
     */
    private final CommentService commentService;

    /**
     * 图片库服务
     */
    private final ImageDataService imageDataService;

    /**
     * 音频服务
     */
    private final AudioDataService audioDataService;

    /**
     * 歌手服务
     */
    private final SingerService singerService;

    /**
     * 音乐关联歌手服务
     */
    private final MusicConnectSingerService musicConnectSingerService;

    /**
     * 获取歌曲分页
     *
     * @param musicParams 歌曲查询数据源
     * @return 歌单列表
     */
    public Page<MusicVo> page(MusicParams musicParams) {
        Assert.isNull(musicParams, "数据异常");
        return iMusicRepository.page(musicParams);
    }

    /**
     * 获取歌曲分页列表
     *
     * @param params 分页查询数据源
     * @return 歌曲分页
     */
    public Page<BackstageMusicVo> page(BackstageMusicParams params) {
        Assert.isNull(params, "数据异常");
        return iMusicRepository.page(params);
    }

    /**
     * 根据歌曲ID获取评论页数据
     *
     * @param musicId 音乐ID
     * @return 评论页数据
     */
    public MusicComposeVo musicComposeQuery(Long musicId) {
        return iMusicRepository.musicComposeQuery(musicId);
    }

    /**
     * 提交音乐评论
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     * @param content 评论内容
     * @return 是否评论成功
     */
    public boolean submitComment(Long userId, Long musicId, String content) {
        return commentService.submitComment(userId, musicId, content);
    }

    /**
     * 评论点赞
     *
     * @param commentId 评论ID
     * @return 是否点赞成功
     */
    public boolean click(Long commentId) {
        return commentService.click(commentId);
    }

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 是否删除成功
     */
    public boolean omit(Long commentId) {
        return commentService.delete(commentId);
    }

    /**
     * 创建音乐
     *
     * @param audio     音频
     * @param image     图片
     * @param singerId  歌手ID
     * @param musicName 歌曲名称
     * @param resume    歌曲简介
     * @param compose   歌词
     * @return 是否创建成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean create(MultipartFile audio, MultipartFile image, Long singerId, String musicName, String resume, String compose) {
        //验证歌手
        Singer singer = singerService.findOne(singerId).orElseThrow(() -> new IllegalArgumentException("singerId is invalid"));
        //音频处理
        String audioName = audio.getName();
        String audioSuffix = StringUtil.getSuffix(audioName);
        AudioDataBase.Type type = AudioDataBase.Type.match(audioSuffix);
        Long audioId = audioDataService.create(audioName, audioName, audio.getSize(), type);
        //图片处理
        String imageName = image.getName();
        String imageSuffix = StringUtil.getSuffix(imageName);
        ImageDataBase.Type match = ImageDataBase.Type.match(imageSuffix);
        Long imageId = imageDataService.create(imageName, imageName, image.getSize(), match);
        //创建音乐
        Long musicId = iMusicRepository.create(musicName, resume, compose, audioId, imageId);
        //关联歌手
        return musicConnectSingerService.create(musicId, singer.getId());
    }
}
