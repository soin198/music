package org.soin.core.domain.singer.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseEntity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;

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
    private Gender sex;

    /**
     * 图片库ID
     */
    private Long imageId;

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

    /**
     * 性别枚举
     */
    public enum Gender {

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

        /**
         * 验证歌手类型是否包含
         *
         * @param gender 歌手类型
         * @return 是否包含
         */
        public static boolean contain(Gender gender) {
            if (null == gender) {
                return Boolean.FALSE;
            }
            return !Arrays.stream(Gender.values()).collect(Collectors.toList()).contains(gender);
        }
    }

}
