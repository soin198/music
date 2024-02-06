package org.soin.core.domain.music.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseEntity;

import java.io.Serializable;

/**
 * 歌单
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 09:52
 **/
@Data
@TableName(value = "t_music_tree")
@EqualsAndHashCode(callSuper = true)
public class MusicTree extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -9185904477226961833L;

    /**
     * 标题
     */
    private String title;

    /**
     * 歌单图
     */
    private String photo;

    /**
     * 歌单简介
     */
    private String resume;

    /**
     * 歌单类型
     */
    private Type type;

    /**
     * 是否开启
     */
    private Boolean enable;

    /**
     * 歌单类型
     */
    public enum Type {

        /**
         * 华语
         */
        CHINESE,

        /**
         * 粤语
         */
        CANTONESE,

        /**
         * 欧美
         */
        EUROPE,

        /**
         * 日本
         */
        JAPAN,

        /**
         * 韩国
         */
        KOREA,

        /**
         * 轻音乐
         */
        LIGHT_MUSIC,

        /**
         * BGM
         */
        BGM,

        /**
         * 乐器
         */
        INSTRUMENT;

    }
}
