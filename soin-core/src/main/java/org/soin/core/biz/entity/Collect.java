package org.soin.core.biz.entity;

import lombok.Data;
import org.soin.core.biz.common.BaseEntity;

import java.io.Serializable;

/**
 * 收藏歌曲
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 09:52
 **/
@Data
public class Collect extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -9185904477226961833L;

    /**
     * 人员ID
     */
    private Long userId;

    /**
     * 音乐ID
     */
    private Long musicId;
}
