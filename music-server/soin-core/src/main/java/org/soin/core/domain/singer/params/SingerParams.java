package org.soin.core.domain.singer.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.domain.singer.entity.Singer;
import org.soin.core.infrastructure.base.common.BaseParams;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-30 17:21
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class SingerParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = 6503183431321237537L;

    /**
     * 歌手名称
     */
    private String singerName;

    /**
     * 歌手性别
     */
    @NotNull(message = "请提供歌手性别")
    private Singer.Gender singerType;

}
