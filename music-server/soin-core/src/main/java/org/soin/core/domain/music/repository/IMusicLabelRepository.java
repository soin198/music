package org.soin.core.domain.music.repository;

import org.soin.core.domain.music.entity.MusicLabel;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface IMusicLabelRepository {

    /**
     * 统计标签总数
     *
     * @return 标签总数
     */
    int count();

    /**
     * 添加音乐标签
     *
     * @param value 标签值
     * @param type  标签类型
     * @param order 标签排序
     * @return 标签主键
     */
    Long insert(String value, MusicLabel.Type type, int order);
}
