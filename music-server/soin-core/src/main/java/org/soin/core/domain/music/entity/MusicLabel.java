package org.soin.core.domain.music.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseEntity;

import java.io.Serializable;

/**
 * 歌曲收藏
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:13
 **/
@Data
@TableName(value = "t_music_label")
@EqualsAndHashCode(callSuper = true)
public class MusicLabel extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 3501632045521568617L;

    /**
     * 标签
     */
    private String value;

    /**
     * 标签类型
     */
    private Type type;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 标签类型枚举
     */
    public enum Type {
        /**
         * 华语
         */
        CHINESE;
    }
}
