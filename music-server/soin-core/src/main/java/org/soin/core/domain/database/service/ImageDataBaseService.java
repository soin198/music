package org.soin.core.domain.database.service;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.entity.ImageDataBase;
import org.soin.core.domain.database.repository.IImageDataBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
