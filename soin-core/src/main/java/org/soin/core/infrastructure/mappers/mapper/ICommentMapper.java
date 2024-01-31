package org.soin.core.infrastructure.mappers.mapper;

import io.lettuce.core.dynamic.annotation.Param;
import org.soin.core.domain.music.entity.Comment;

/**
 * 音乐评论Mapper
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:33
 **/
public interface ICommentMapper {

    /**
     * 创建一条评论
     * {@link Comment}
     *
     * @param comment 待创建的评论
     * @return 创建的评论
     */
    Comment insert(Comment comment);

    /**
     * 根据主键获取对应评论
     * {@link Comment}
     *
     * @param keyId 主键
     * @return 对应评论
     */
    Comment getCommentByKeyId(@Param("keyId") Long keyId);

    /**
     * 更新当前评论
     * {@link Boolean}
     *
     * @param comment 当前评论
     * @return 是否更新成功
     */
    Boolean renew(Comment comment);
}
