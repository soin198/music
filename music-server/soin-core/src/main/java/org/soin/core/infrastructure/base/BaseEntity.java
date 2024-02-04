package org.soin.core.infrastructure.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用实体
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 14:55
 **/
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 3736183870812543644L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 创建时间
     */
    private Date createDate = new Date();

    /**
     * 最新修改时间
     */
    private Date lastModifiedDate;

}
