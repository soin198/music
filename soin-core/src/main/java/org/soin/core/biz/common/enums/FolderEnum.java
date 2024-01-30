package org.soin.core.biz.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * redis资源索引
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2023-06-26 17:14
 **/
@Getter
@AllArgsConstructor
public enum FolderEnum {

    /**
     * 用户端
     */
    CLIENT("CLIENT:", 0),

    /**
     * 后台
     */
    MANAGE("MANAGE:", 1);

    /**
     * 命名空间
     */
    private final String nameSpace;

    /**
     * 缓存空间
     */
    private final int index;

}
