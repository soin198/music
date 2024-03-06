package org.soin.core.domain.music.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseParams;

import java.io.Serializable;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-30 17:21
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class BackstageMusicParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = -6530724358042794136L;

    /**
     * 音乐名称
     */
    private String musicName;

    /**
     * 歌手名称
     */
    private String singerName;
}
