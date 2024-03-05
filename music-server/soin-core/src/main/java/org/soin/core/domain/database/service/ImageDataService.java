package org.soin.core.domain.database.service;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.entity.ImageDataBase;
import org.soin.core.domain.database.repository.ImageDataRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
@Service(value = "ImageDataService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageDataService {

    private final ImageDataRepository imageDataRepository;

    /**
     * 添加图片库数据
     *
     * @param path 文件路径
     * @param name 文件名称
     * @param size 文件大小
     * @param type 文件类型
     * @return 图片库ID
     */
    public Long create(String path, String name, long size, ImageDataBase.Type type) {
        return imageDataRepository.create(path, name, size, type);
    }

    /**
     * 根据图片库ID获取图片路径
     *
     * @param keyId 图片库ID
     * @return 图片库路径
     */
    public String pathQuery(Long keyId) {
        Assert.isNull(keyId, "请提供图片库ID");
        ImageDataBase imageDataBase = Optional.ofNullable(imageDataRepository
                .findOne(keyId))
                .orElseThrow(() -> new IllegalArgumentException("图片库ID无效"));
        return imageDataBase.getPath() + "/" + imageDataBase.getName();
    }

    /**
     * 获取图片库列表
     *
     * @return 图片库列表
     */
    public List<ImageDataBase> list() {
        return imageDataRepository.list();
    }

    /**
     * 更新图片库
     *
     * @param imageDataBase 更新图片库
     * @return 是否更新成功
     */
    public boolean update(ImageDataBase imageDataBase) {
        return imageDataRepository.update(imageDataBase);
    }
}
