package org.soin.website.biz.serivce.impl;

import lombok.RequiredArgsConstructor;
import org.soin.core.biz.common.Page;
import org.soin.core.biz.entity.Comment;
import org.soin.core.biz.mapper.CommentMapper;
import org.soin.website.biz.serivce.ICommentService;
import org.soin.website.controller.form.CommentForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 评论相关实现类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:30
 **/
@Service("iCommentService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements ICommentService {

    /**
     * 默认点赞数
     */
    private static final int DEFAULT_VALUE = 0;

    /**
     * comment mapper
     */
    private final CommentMapper commentMapper;

    /**
     * 根据主键获取对应评论
     * {@link Comment}
     *
     * @param keyId 主键
     * @return 对应评论
     */
    private Comment getCommentByKeyId(Long keyId) {
        return commentMapper.getCommentByKeyId(keyId);
    }

    /**
     * 创建一条评论
     * {@link Comment}
     *
     * @param userId  人员
     * @param musicId 音乐ID
     * @param content 评论内容
     * @return 创建的评论
     */
    @Valid
    private Comment insert(@NotNull Long userId, @NotNull Long musicId, @NotBlank String content) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setMusicId(musicId);
        comment.setContent(content);
        comment.setCreateDate(new Date());
        comment.setUpClick(DEFAULT_VALUE);
        return commentMapper.insert(comment);
    }

    /**
     * 更新当前评论
     * {@link Boolean}
     *
     * @param comment 当前评论
     * @return 是否更新成功
     */
    private Boolean renew(Comment comment) {
        return commentMapper.renew(comment);
    }

    /**
     * {@link Boolean}
     *
     * @param params 提交评论参数
     * @return 是否提交成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submit(CommentForm params) {
        Assert.notNull(params, "submit error params is null!");
        Comment comment = this.insert(params.getUserId(), params.getMusicId(), params.getContent());
        return (null != comment);
    }

    /**
     * 根据主键删除对应评论
     * {@link Boolean}
     *
     * @param keyId 评论主键
     * @return 是否删除成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByKey(Long keyId) {
        Assert.notNull(keyId, "请提供keyId");
        Comment commentVo = this.getCommentByKeyId(keyId);
        return this.renew(commentVo);
    }

    /**
     * 对某一条评论点赞
     *
     * @param keyId 评论主键
     * @return 是否点赞成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean commentOfLike(Long keyId) {
        Assert.notNull(keyId, "请提供评论主键");
        Comment comment = this.getCommentByKeyId(keyId);
        Assert.notNull(comment, "keyId is invalid");
        Integer click = comment.getUpClick();
        comment.setUpClick(click + 1);
        return this.renew(comment);
    }

    @Override
    public Page<Object> getCommentsByMusicId(Long musicId) {
        return null;
    }

}
