package org.soin.core.domain.database.repository;

import org.soin.core.domain.database.entity.ImageDataBase;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
public interface IImageDataBaseRepository {

    /**
     * 添加图片库数据
     *
     * @param path 文件路径
     * @param name 文件名称
     * @param size 文件大小
     * @param type 文件类型
     */
    void insert(String path, String name, long size, ImageDataBase.Type type);
}
