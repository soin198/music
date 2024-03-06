package org.soin.client.api.vo;

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
public class MusicVo implements Serializable {

    private static final long serialVersionUID = 8826938656344364994L;

    /**
     * 歌手名称
     */
    private String singerName;

    /**
     * 歌曲主键
     */
    private Long musicId;

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
     * 歌曲图片
     */
    private String image;

    /**
     * 歌曲音频
     */
    private String audio;

}
