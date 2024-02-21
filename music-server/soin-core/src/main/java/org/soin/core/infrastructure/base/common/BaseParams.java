package org.soin.core.infrastructure.base.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页对象
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 17:26
 **/
@Data
public class BaseParams implements Serializable {

    private static final long serialVersionUID = -9089280992893762566L;

    /**
     * 页码
     */
    private int page;

    /**
     * 每页条数
     */
    private int pageSize;

    /**
     * 计算分页
     *
     * @return 开始分页位置
     */
    public Integer getStart() {
        return (this.page - 1) * this.pageSize;
    }

}
