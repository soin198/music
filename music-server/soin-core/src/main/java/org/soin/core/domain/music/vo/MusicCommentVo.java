package org.soin.core.domain.music.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 歌曲评论
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:13
 **/
@Data
public class MusicCommentVo implements Serializable {

    private static final long serialVersionUID = -5896299198814349602L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 评论ID
     */
    private Long commentId;

    /**
     * 内容
     */
    private String content;

    /**
     * 点击数
     */
    private Integer click;

}
