package org.soin.core.domain.music.vo;

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
public class MusicComposeVo implements Serializable {

    private static final long serialVersionUID = 2045002506491200923L;

    /**
     * 歌手名称
     */
    private String singerName;

    /**
     * 歌曲名称
     */
    private String musicName;

    /**
     * 歌词
     */
    private String compose;

    /**
     * 歌曲图片
     */
    private String image;


}
