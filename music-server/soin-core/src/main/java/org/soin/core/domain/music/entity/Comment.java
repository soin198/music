package org.soin.core.domain.music.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.BaseEntity;

import java.io.Serializable;

/**
 * 评论
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:23
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2298031509856817249L;

    /**
     * 人员ID
     */
    private Long userId;

    /**
     * 音乐ID
     */
    private Long musicId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数量
     */
    private Integer upClick;
}
