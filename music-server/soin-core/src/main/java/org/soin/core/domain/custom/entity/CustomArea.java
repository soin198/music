package org.soin.core.domain.custom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.soin.core.infrastructure.base.BaseEntity;

import java.io.Serializable;

/**
 * 用户省份
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-04 15:00
 **/
@Data
@TableName(value = "t_custom")
@EqualsAndHashCode(callSuper = true)
public class CustomArea extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 4982582688238183436L;

    /**
     * 人员ID
     */
    private Long userId;

    /**
     * 省份
     */
    private Integer province;

    /**
     * 市区
     */
    private Integer city;

    /**
     * 区县
     */
    private Integer region;
}
