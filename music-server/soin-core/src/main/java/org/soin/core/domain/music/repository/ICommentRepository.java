package org.soin.core.domain.music.repository;

import org.soin.core.domain.music.params.MusicCommentParams;
import org.soin.core.domain.music.vo.MusicCommentVo;
import org.soin.core.infrastructure.base.common.Page;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface ICommentRepository {

    /**
     * 获取歌曲评论列表
     *
     * @param params 查询数据源
     * @return 评论列表
     */
    Page<MusicCommentVo> page(MusicCommentParams params);

    /**
     * 添加
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     * @param content 评论内容
     * @return 是否添加成功
     */
    boolean insert(Long userId, Long musicId, String content);

    /**
     * 评论点赞
     *
     * @param commentId 评论ID
     * @return 是否点赞成功
     */
    boolean click(Long commentId);

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 是否删除成功
     */
    boolean delete(Long commentId);
}
