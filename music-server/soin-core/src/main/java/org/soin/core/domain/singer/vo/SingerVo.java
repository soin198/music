package org.soin.core.domain.singer.vo;

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
public class SingerVo implements Serializable {

    private static final long serialVersionUID = -7814431749672707929L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 歌手名称
     */
    private String name;

    /**
     * 歌手性别
     */
    private String sex;

    /**
     * 歌手头像
     */
    private String photo;

    /**
     * 图片渲染
     */
    private String base64;

    /**
     * 歌手生日
     */
    private String birth;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 简介
     */
    private String resume;

}
