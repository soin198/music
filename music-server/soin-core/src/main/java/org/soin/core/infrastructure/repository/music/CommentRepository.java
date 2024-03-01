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
import org.soin.core.infrastructure.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        return new Page<>(rows, list.stream().peek(item -> item.setUserPic(ImageUtil.generate(item.getUserPic()))).collect(Collectors.toList()));
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
    @Transactional(rollbackFor = Exception.class)
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

    /**
     * 评论点赞
     *
     * @param commentId 评论ID
     * @return 是否点赞成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean click(Long commentId) {
        Assert.isNull(commentId, "请正确点击点赞的评论");
        Comment comment = commentMapper.selectById(commentId);
        Assert.isNull(comment, "commentId is invalid");
        Integer click = comment.getClick();
        comment.setClick(click + 1);
        return commentMapper.updateById(comment) > 0;
    }

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 是否删除成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Long commentId) {
        Assert.isNull(commentId, "请选择想要删除的评论");
        return commentMapper.deleteById(commentId) > 0;
    }


}


