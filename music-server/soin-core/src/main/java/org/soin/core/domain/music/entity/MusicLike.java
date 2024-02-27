package org.soin.core.domain.music.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseEntity;

import java.io.Serializable;

/**
 * 我喜欢
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:13
 **/
@Data
@TableName(value = "t_music_like")
@EqualsAndHashCode(callSuper = true)
public class MusicLike extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8023070050925249148L;

    /**
     * 人员ID
     */
    private Long userId;

    /**
     * 音乐ID
     */
    private Long musicId;

}
