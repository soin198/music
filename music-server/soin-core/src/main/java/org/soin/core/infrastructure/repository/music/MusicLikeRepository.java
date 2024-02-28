package org.soin.core.infrastructure.repository.music;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.MusicLike;
import org.soin.core.domain.music.repository.IMusicLikeRepository;
import org.soin.core.infrastructure.base.common.Assert;
import org.soin.core.infrastructure.mapper.music.MusicLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-31 14:06
 **/
@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicLikeRepository implements IMusicLikeRepository {

    private final MusicLikeMapper musicLikeMapper;

    /**
     * 创建我喜欢数据
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     * @return 是否保存成功
     */
    @Override
    public boolean insert(Long userId, Long musicId) {
        Assert.isNull(userId, "userId is null");
        Assert.isNull(musicId, "请选择音乐");
        MusicLike musicLike = new MusicLike();
        musicLike.setUserId(userId);
        musicLike.setMusicId(musicId);
        return musicLikeMapper.insert(musicLike) > 0;
    }

    /**
     * 取消我喜欢
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否取消成功
     */
    @Override
    public boolean cancelLike(Long userId, Long musicId) {
        Assert.isNull(userId, "userId is null");
        Assert.isNull(musicId, "请选择音乐");
        QueryWrapper<MusicLike> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("musicId", musicId);
        return musicLikeMapper.delete(wrapper) > 0;
    }

    /**
     * 获取歌曲收藏数据
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 歌曲喜欢数据
     */
    @Override
    public Optional<MusicLike> getOne(Long userId, Long musicId) {
        Assert.isNull(userId, "userId is null");
        Assert.isNull(musicId, "请选择音乐");
        QueryWrapper<MusicLike> wrapper = new QueryWrapper<>();
        wrapper.eq("userId", userId);
        wrapper.eq("musicId", musicId);
        return Optional.ofNullable(musicLikeMapper.selectOne(wrapper));
    }
}


