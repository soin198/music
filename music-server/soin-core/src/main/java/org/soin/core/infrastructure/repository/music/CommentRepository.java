package org.soin.core.infrastructure.repository.music;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.Comment;
import org.soin.core.domain.music.params.MusicCommentParams;
import org.soin.core.domain.music.repository.ICommentRepository;
import org.soin.core.domain.music.vo.MusicCommentVo;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.mapper.music.CommentMapper;
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
public class CommentRepository implements ICommentRepository {

    private final CommentMapper commentMapper;

    /**
     * 获取歌曲评论列表
     *
     * @param params 查询数据源
     * @return 评论列表
     */
    @Override
    public Page<MusicCommentVo> page(MusicCommentParams params) {
        Assert.isNull(params, "请提供查询数据源");
        Long musicId = params.getMusicId();
        int rows = commentMapper.count(musicId);
        List<MusicCommentVo> list = (rows > 0) ? commentMapper.list(params) : Lists.newArrayList();
        return new Page<>(rows, list);
    }

    /**
     * 添加
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     * @param content 评论内容
     * @return 是否添加成功
     */
    @Override
    public boolean insert(Long userId, Long musicId, String content) {
        Assert.isNull(userId, "请提供人员ID");
        Assert.isNull(musicId, "请提供音乐ID");
        Assert.isBlank(content, "请提供评论内容");
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setMusicId(musicId);
        comment.setContent(content);
        comment.setClick(0);
        return commentMapper.insert(comment) > 0;
    }
}


