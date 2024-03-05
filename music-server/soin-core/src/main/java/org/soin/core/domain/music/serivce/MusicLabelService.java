package org.soin.core.domain.music.serivce;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.MusicLabel;
import org.soin.core.domain.music.repository.IMusicLabelRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:20
 **/
@Service(value = "MusicLabelService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicLabelService {

    private final IMusicLabelRepository iMusicLabelRepository;

    /**
     * 创建音乐标签
     *
     * @param value 标签
     * @param type  标签类型
     * @return 标签主键
     */
    protected Long create(String value, MusicLabel.Type type) {
        Assert.isBlank(value, "标签为空");
        Assert.isNull(type, "标签类型为空");
        int rows = iMusicLabelRepository.count();
        return iMusicLabelRepository.insert(value, type, (rows + 1));
    }

}
