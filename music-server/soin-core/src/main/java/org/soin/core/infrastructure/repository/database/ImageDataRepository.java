package org.soin.core.infrastructure.repository.database;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.entity.ImageDataBase;
import org.soin.core.domain.database.repository.IImageDataBaseRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.mapper.database.ImageDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageDataRepository implements IImageDataBaseRepository {

    private final ImageDataMapper imageDataMapper;

    /**
     * 添加图片库数据
     *
     * @param path 文件路径
     * @param name 文件名称
     * @param size 文件大小
     * @param type 文件类型
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(String path, String name, long size, ImageDataBase.Type type) {
        Assert.isBlank(path, "请提供文件路径");
        Assert.isBlank(name, "请提供文件名称");
        Assert.isNull(type, "请提供文件类型");
        ImageDataBase data = new ImageDataBase();
        data.setPath(path);
        data.setName(name);
        data.setSize(size);
        data.setType(type);
        imageDataMapper.insert(data);
    }


}


