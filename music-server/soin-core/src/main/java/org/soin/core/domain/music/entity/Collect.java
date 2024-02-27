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
@TableName(value = "t_music_collect")
@EqualsAndHashCode(callSuper = true)
public class Collect extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4301094646657887681L;

    /**
     * 人员ID
     */
    private Long userId;

    /**
     * 音乐ID
     */
    private Long musicId;

}
