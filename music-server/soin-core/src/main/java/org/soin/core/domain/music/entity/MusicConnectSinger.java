package org.soin.core.domain.music.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseEntity;

import java.io.Serializable;

/**
 * 音乐-歌手
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
@Data
@TableName(value = "t_music_connect_singer")
@EqualsAndHashCode(callSuper = true)
public class MusicConnectSinger extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8084429451355739737L;

    /**
     * 音乐ID
     */
    private Long musicId;

    /**
     * 歌手ID
     */
    private Long singerId;
}
