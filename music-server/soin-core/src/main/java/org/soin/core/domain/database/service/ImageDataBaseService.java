package org.soin.core.domain.database.service;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.entity.ImageDataBase;
import org.soin.core.domain.database.repository.IImageDataBaseRepository;
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
@Service(value = "ImageDataBaseService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageDataBaseService {

    private final IImageDataBaseRepository imageDataBaseRepository;

    /**
     * 添加图片库数据
     *
     * @param path 文件路径
     * @param name 文件名称
     * @param size 文件大小
     * @param type 文件类型
     */
    public void insert(String path, String name, long size, ImageDataBase.Type type) {
        imageDataBaseRepository.insert(path, name, size, type);
    }

    /**
     * 根据图片库ID获取图片库
     *
     * @param keyId 图片库ID
     * @return 图片库
     */
    public Optional<ImageDataBase> findOne(Long keyId) {
        Assert.isNull(keyId, "请提供图片库ID");
        ImageDataBase data = imageDataBaseRepository.findOne(keyId);
        return Optional.ofNullable(data);
    }

    /**
     * 根据图片库ID获取图片路径
     *
     * @param keyId 图片库ID
     * @return 图片库路径
     */
    public String pathQuery(Long keyId) {
        return this.findOne(keyId)
                .orElseThrow(() -> new IllegalArgumentException("图片库ID无效"))
                .getPath();
    }

    /**
     * 获取图片库列表
     *
     * @return 图片库列表
     */
    public List<ImageDataBase> list() {
        return imageDataBaseRepository.list();
    }

    /**
     * 更新图片库
     *
     * @param imageDataBase 更新图片库
     * @return 是否更新成功
     */
    public boolean update(ImageDataBase imageDataBase) {
        return imageDataBaseRepository.update(imageDataBase);
    }
}
