package org.soin.core.domain.music.serivce;

import lombok.RequiredArgsConstructor;
import org.soin.core.domain.music.params.MusicCommentParams;
import org.soin.core.domain.music.repository.ICommentRepository;
import org.soin.core.domain.music.vo.MusicCommentVo;
import org.soin.core.infrastructure.base.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-22 15:20
 **/
@Service(value = "CommentService")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private final ICommentRepository iCommentRepository;

    /**
     * 获取歌曲评论列表
     *
     * @param params 查询数据源
     * @return 评论列表
     */
    public Page<MusicCommentVo> page(MusicCommentParams params) {
        return iCommentRepository.page(params);
    }

    /**
     * 提交音乐评论
     *
     * @param userId  人员ID
     * @param musicId 音乐ID
     * @param content 评论内容
     * @return 是否评论成功
     */
    protected boolean submitComment(Long userId, Long musicId, String content) {
        return iCommentRepository.insert(userId, musicId, content);
    }
}
