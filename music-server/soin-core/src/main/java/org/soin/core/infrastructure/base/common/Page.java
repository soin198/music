package org.soin.core.infrastructure.base.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 17:26
 **/
@Data
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 6336007095907059675L;

    /**
     * 总条数
     */
    private Integer totalRows;

    /**
     * 响应数据
     */
    private List<T> data;
}
