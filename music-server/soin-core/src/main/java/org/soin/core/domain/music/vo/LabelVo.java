package org.soin.core.domain.music.vo;

import lombok.Data;
import org.soin.core.domain.music.entity.MusicLabel;

import java.io.Serializable;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:13
 **/
@Data
public class LabelVo implements Serializable {

    private static final long serialVersionUID = 4179566877895239429L;

    /**
     * 标签主键
     */
    private Long id;

    /**
     * 标签值
     */
    private String value;

    /**
     * 标签类型
     */
    private MusicLabel.Type type;

    /**
     * 排序
     */
    private Integer order;
}
