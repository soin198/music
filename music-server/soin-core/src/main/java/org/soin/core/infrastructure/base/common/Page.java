package org.soin.core.infrastructure.base.common;

import com.google.common.collect.Lists;
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
    private int totalRows = 0;

    /**
     * 响应数据
     */
    private List<T> data = Lists.newArrayList();

    /**
     * 无参构造
     */
    public Page() {}

    /**
     * 有参构造
     *
     * @param totalRows 总条数
     * @param data      分页数据
     */
    public Page(int totalRows, List<T> data) {
        this.totalRows = totalRows;
        this.data = data;
    }
}
