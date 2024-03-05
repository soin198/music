package org.soin.core.domain.database.repository;

import org.soin.core.domain.database.entity.AudioDataBase;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
public interface IAudioDataRepository {

    /**
     * 创建视频库资源
     *
     * @param path 文件路径
     * @param name 文件名称
     * @param size 文件大小
     * @param type 文件类型
     * @return 视频库主键
     */
    Long insert(String path, String name, long size, AudioDataBase.Type type);
}
