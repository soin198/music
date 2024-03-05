package org.soin.core.domain.music.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseEntity;

import java.io.Serializable;

/**
 * 歌手
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:13
 **/
@Data
@TableName(value = "t_music")
@EqualsAndHashCode(callSuper = true)
public class Music extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8826938656344364994L;

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
     * 音频库ID
     */
    private Long audioId;

    /**
     * 图片库ID
     */
    private Long imageId;

    /**
     * 是否可用
     */
    private Boolean enable;

}
