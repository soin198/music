package org.soin.core.domain.music.repository;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:05
 **/
public interface IMusicConnectLabelRepository {

    /**
     * 音乐标签关联
     *
     * @param musicId 音乐ID
     * @param labelId 标签ID
     * @return 是否创建成功
     */
    boolean insert(Long musicId, Long labelId);
}
