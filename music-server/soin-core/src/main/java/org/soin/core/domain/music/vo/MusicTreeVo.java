package org.soin.core.domain.music.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 歌单返回体
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:53
 **/
@Data
public class MusicTreeVo implements Serializable {

    private static final long serialVersionUID = -7814431749672707929L;

    /**
     * 歌单主键
     */
    private Long id;

    /**
     * 歌单标题
     */
    private String title;

    /**
     * 歌单图片
     */
    private String photo;

    /**
     * 图片渲染
     */
    private String base64;

    /**
     * 歌单简介
     */
    private String resume;

    /**
     * 歌单类型
     */
    private String type;


}
