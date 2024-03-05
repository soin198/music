package org.soin.core.infrastructure.repository.music;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.MusicLabel;
import org.soin.core.domain.music.repository.IMusicLabelRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.mapper.music.MusicLabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicLabelRepository implements IMusicLabelRepository {

    private final MusicLabelMapper musicLabelMapper;

    /**
     * 统计标签总数
     *
     * @return 标签总数
     */
    @Override
    public int count() {
        QueryWrapper<MusicLabel> wrapper = new QueryWrapper<>();
        return musicLabelMapper.selectCount(wrapper).intValue();
    }

    /**
     * 添加音乐标签
     *
     * @param value 标签值
     * @param type  标签类型
     * @param order 标签排序
     * @return 标签主键
     */
    @Override
    public Long insert(String value, MusicLabel.Type type, int order) {
        Assert.isBlank(value, "标签为空");
        Assert.isNull(type, "标签类型为空");
        MusicLabel musicLabel = new MusicLabel();
        musicLabel.setValue(value);
        musicLabel.setType(type);
        musicLabel.setOrder(order);
        musicLabelMapper.insert(musicLabel);
        return musicLabel.getId();
    }
}


