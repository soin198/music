package org.soin.core.domain.database.service;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.entity.AudioDataBase;
import org.soin.core.domain.database.repository.IAudioDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
@Service(value = "AudioDataService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AudioDataService {

    private final IAudioDataRepository iAudioDataRepository;

    /**
     * 创建视频库资源
     *
     * @param path 文件路径
     * @param name 文件名称
     * @param size 文件大小
     * @param type 文件类型
     * @return 视频库主键
     */
    public Long create(String path, String name, long size, AudioDataBase.Type type) {
        return iAudioDataRepository.insert(path, name, size, type);
    }
}
