package org.soin.core.domain.music.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * 歌手
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:13
 **/
@Data
public class MusicBO implements Serializable {

    private static final long serialVersionUID = 3728103041447210872L;

    /**
     * 音乐ID
     */
    private Long musicId;

    /**
     * 歌手名称
     */
    private String singerName;

    /**
     * 歌曲名称
     */
    private String musicName;

    /**
     * 歌曲说明
     */
    private String resume;

    /**
     * 歌词
     */
    private String compose;

    /**
     * 歌曲音频
     */
    private String audio;

    /**
     * 歌曲图片
     */
    private String image;

}
