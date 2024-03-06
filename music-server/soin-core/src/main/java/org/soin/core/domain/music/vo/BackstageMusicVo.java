package org.soin.core.domain.music.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-03-05 23:50
 **/
@Data
public class BackstageMusicVo implements Serializable {

    private static final long serialVersionUID = -4905885607322449928L;

    /**
     * 音乐ID
     */
    private Long musicId;

    /**
     * 图片渲染
     */
    private String image;

    /**
     * 歌手名称
     */
    private String singerName;

    /**
     * 音乐名称
     */
    private String musicName;

    /**
     * 简介
     */
    private String resume;
}
