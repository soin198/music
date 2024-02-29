package org.soin.core.infrastructure.mapper.music;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.soin.core.domain.music.entity.Comment;
import org.soin.core.domain.music.params.MusicCommentParams;
import org.soin.core.domain.music.vo.MusicCommentVo;

import java.util.List;

/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-09 14:02
 **/
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 统计当前歌曲评论总数
     *
     * @param musicId 音乐ID
     * @return 音乐评论总数
     */
    int count(@Param("musicId") Long musicId);

    /**
     * 获取当前歌曲评论列表
     *
     * @param params 查询数据源
     * @return 评论列表
     */
    List<MusicCommentVo> list(MusicCommentParams params);
}
