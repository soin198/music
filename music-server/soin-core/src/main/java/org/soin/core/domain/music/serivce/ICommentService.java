package org.soin.core.domain.music.serivce;

import org.soin.core.infrastructure.base.Page;

/**
 * 评论相关实现类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:27
 **/
public interface ICommentService {

    /**
     * {@link Boolean}
     *
     * @param params 提交评论参数
     * @return 是否提交成功
     */
    //boolean submit(CommentForm params);

    /**
     * 根据主键删除对应评论
     * {@link Boolean}
     *
     * @param keyId 评论主键
     * @return 是否删除成功
     */
    boolean deleteByKey(Long keyId);

    /**
     * 对某一条评论点赞
     *
     * @param keyId 评论主键
     * @return 是否点赞成功
     */
    boolean commentOfLike(Long keyId);

    Page<Object> getCommentsByMusicId(Long musicId);
}
