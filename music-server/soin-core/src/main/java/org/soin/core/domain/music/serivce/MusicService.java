package org.soin.core.domain.music.serivce;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.params.MusicParams;
import org.soin.core.domain.music.repository.IMusicRepository;
import org.soin.core.domain.music.vo.MusicComposeVo;
import org.soin.core.domain.music.vo.MusicVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.common.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:20
 **/
@Service(value = "MusicService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicService {

    private final IMusicRepository iMusicRepository;

    private final CommentService commentService;

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
}
