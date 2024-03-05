package org.soin.manage.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 添加音乐
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-30 17:21
 **/
@Data
public class MusicCreateDTO implements Serializable {

    private static final long serialVersionUID = -1146546256362760714L;

    /**
     * 歌手ID
     */
    @NotNull(message = "请选择所属歌手")
    private Long singerId;

    /**
     * 音乐名称
     */
    @NotBlank(message = "请输入音乐名称")
    private String musicName;

    /**
     * 音乐说明
     */
    @NotBlank(message = "请输入音乐说明")
    private String resume;

    /**
     * 歌词
     */
    @NotBlank(message = "请输入歌词")
    private String compose;

}
