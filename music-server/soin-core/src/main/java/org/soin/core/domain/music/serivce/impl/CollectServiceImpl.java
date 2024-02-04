package org.soin.core.domain.music.serivce.impl;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.Collect;
import org.soin.core.domain.music.serivce.ICollectService;
import org.soin.core.infrastructure.mappers.mapper.ICollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * 收藏歌曲接口实现类
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 09:52
 **/
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CollectServiceImpl implements ICollectService {
    @Override
    public boolean insertCollectMusic(Long userId, Long musicId) {
        return false;
    }

    /**
     * 收藏音乐接口实现类
     */
    //private final ICollectMapper ICollectMapper;

    /**
     * 收藏音乐
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     * @return 是否收藏成功
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertCollectMusic(Long userId, Long musicId) {
        this.checkExist(userId, musicId);
        this.insert(userId, musicId);
        return Boolean.TRUE;
    }

    *//**
     * 添加收藏音乐
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     * @return 主键
     *//*
    private int insert(Long userId, Long musicId) {
        Assert.notNull(userId, "请提供人员ID");
        Assert.notNull(musicId, "请提供音乐ID");
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setMusicId(musicId);
        collect.setCreateDate(new Date());
        return ICollectMapper.insert(collect);
    }

    *//**
     * 验证歌曲是否已被收藏
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     *//*
    private void checkExist(Long userId, Long musicId) {
        Assert.notNull(userId, "请提供人员ID");
        Assert.notNull(musicId, "请提供音乐ID");
        Collect collect = ICollectMapper.byUserIdAndMusicIdGet(userId, musicId);
        Assert.notNull(collect, "此歌曲已被收藏，请稍后再试");
    }*/
}