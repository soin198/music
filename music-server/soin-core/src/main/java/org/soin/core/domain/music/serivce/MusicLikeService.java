package org.soin.core.domain.music.serivce;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.entity.MusicLike;
import org.soin.core.domain.music.repository.IMusicLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:20
 **/
@Service(value = "MusicLikeService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MusicLikeService {

    private final IMusicLikeRepository iMusicLikeRepository;

    /**
     * 添加我喜欢
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否添加成功
     */
    public boolean saveLike(Long userId, Long musicId) {
        return iMusicLikeRepository.insert(userId, musicId);
    }

    /**
     * 取消我喜欢
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否取消成功
     */
    public boolean cancelLike(Long userId, Long musicId) {
        return iMusicLikeRepository.cancelLike(userId, musicId);
    }

    /**
     * 验证是否喜欢此音乐
     *
     * @param userId  收藏人ID
     * @param musicId 歌曲ID
     * @return 是否喜欢
     */
    public boolean getOne(Long userId, Long musicId) {
        Optional<MusicLike> op = iMusicLikeRepository.getOne(userId, musicId);
        return op.isPresent();
    }
}
