package org.soin.core.domain.database.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.common.BaseEntity;

import java.io.Serializable;

/**
 * 音频库
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
@Data
@TableName(value = "t_audio_database")
@EqualsAndHashCode(callSuper = true)
public class AudioDataBase extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8411929559800876415L;

    /**
     * 音频存储路径
     */
    private String path;

    /**
     * 音频名称
     */
    private String name;

    /**
     * 音频大小
     */
    private Long size;

    /**
     * 音频类型
     */
    private ImageDataBase.Type type;

    public enum Type {

    }

}
