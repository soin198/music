package org.soin.core.infrastructure.repository.database;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.database.entity.AudioDataBase;
import org.soin.core.domain.database.repository.IAudioDataRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.mapper.database.AudioDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-23 16:39
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AudioDataRepository implements IAudioDataRepository {

    private final AudioDataMapper audioDataMapper;

    /**
     * 创建视频库资源
     *
     * @param path 文件路径
     * @param name 文件名称
     * @param size 文件大小
     * @param type 文件类型
     * @return 视频库主键
     */
    @Override
    public Long insert(String path, String name, long size, AudioDataBase.Type type) {
        Assert.isBlank(path, "请提供音频库路径");
        Assert.isBlank(name, "请提供音频名称");
        Assert.isNull(type, "请提供音频类型");
        AudioDataBase audio = new AudioDataBase();
        audio.setPath(path);
        audio.setName(name);
        audio.setSize(size);
        audio.setType(type);
        audioDataMapper.insert(audio);
        return audio.getId();
    }
}


