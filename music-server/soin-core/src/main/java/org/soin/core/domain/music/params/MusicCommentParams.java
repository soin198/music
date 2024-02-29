package org.soin.core.domain.music.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class MusicCommentParams extends BaseParams implements Serializable {

    private static final long serialVersionUID = 6154793098285151686L;

    /**
     * 歌曲ID
     */
    @NotNull(message = "请提供歌曲ID")
    private Long musicId;

}
