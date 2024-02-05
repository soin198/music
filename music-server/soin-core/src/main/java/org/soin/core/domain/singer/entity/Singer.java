package org.soin.core.domain.singer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.BaseEntity;

import java.io.Serializable;

/**
 * 歌手
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 09:52
 **/
@Data
@TableName(value = "t_singer")
@EqualsAndHashCode(callSuper = true)
public class Singer extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -7060543150128927908L;

    /**
     * 歌手名称
     */
    private String name;

    /**
     * 歌手性别
     */
    private GenderEnum sex;

    /**
     * 歌手头像
     */
    private String photo;

    /**
     * 歌手生日
     */
    private String birth;

    /**
     * 国籍
     */
    private Integer nationality;

    /**
     * 简介
     */
    private String resume;

    /**
     * 性别枚举
     */
    public enum GenderEnum {

        /**
         * 男性
         */
        MALE,

        /**
         * 女性
         */
        WOMEN,

        /**
         * 其他
         */
        OTHER;
    }

}
