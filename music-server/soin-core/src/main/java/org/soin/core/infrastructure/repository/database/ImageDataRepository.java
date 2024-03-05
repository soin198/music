package org.soin.core.infrastructure.repository.database;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.entity.ImageDataBase;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.mapper.database.ImageDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImageDataRepository implements org.soin.core.domain.database.repository.ImageDataRepository {

    private final ImageDataMapper imageDataMapper;

    /**
     * 添加图片库数据
     *
     * @param path 文件路径
     * @param name 文件名称
     * @param size 文件大小
     * @param type 文件类型
     * @return 图片库ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(String path, String name, long size, ImageDataBase.Type type) {
        Assert.isBlank(path, "请提供文件路径");
        Assert.isBlank(name, "请提供文件名称");
        Assert.isNull(type, "请提供文件类型");
        ImageDataBase data = new ImageDataBase();
        data.setPath(path);
        data.setName(name);
        data.setSize(size);
        data.setType(type);
        imageDataMapper.insert(data);
        return data.getId();
    }

    /**
     * 根据图片库ID获取图片库
     *
     * @param keyId 图片库ID
     * @return 图片库
     */
    @Override
    public ImageDataBase findOne(Long keyId) {
        Assert.isNull(keyId, "请提供图片库ID");
        QueryWrapper<ImageDataBase> wrapper = new QueryWrapper<>();
        wrapper.eq("id", keyId);
        return imageDataMapper.selectOne(wrapper);
    }

    /**
     * 获取图片库列表
     *
     * @return 图片库列表
     */
    @Override
    public List<ImageDataBase> list() {
        QueryWrapper<ImageDataBase> wrapper = new QueryWrapper<>();
        List<ImageDataBase> list = imageDataMapper.selectList(wrapper);
        return (null == list) ? Lists.newArrayList() : list;
    }

    /**
     * 更新图片库
     *
     * @param imageDataBase 更新图片库
     * @return 是否更新成功
     */
    @Override
    public boolean update(ImageDataBase imageDataBase) {
        Assert.isNull(imageDataBase, "请提供图片库");
        imageDataBase.setLastModifiedDate(new Date());
        imageDataMapper.updateById(imageDataBase);
        return Boolean.TRUE;
    }


}


