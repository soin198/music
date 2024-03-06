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
public class MusicParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = 6154793098285151686L;

    /**
     * 歌手Id
     */
    private Long singerId;

    /**
     * 歌手名称
     */
    private String singerName;

    /**
     * 歌曲名称
     */
    private String musicName;

}
