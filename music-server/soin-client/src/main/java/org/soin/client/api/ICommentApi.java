package org.soin.client.api;

import org.soin.client.api.dto.CommentDTO;
import org.soin.core.domain.music.params.MusicCommentParams;
import org.soin.core.domain.music.vo.MusicCommentVo;
import org.soin.core.infrastructure.base.common.Page;
import org.soin.core.infrastructure.base.constant.BaseConstant;
import org.soin.core.infrastructure.base.response.GenericResponse;
import org.springframework.web.bind.annotation.*;


/**
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-02-21 15:37
 **/
@RequestMapping(BaseConstant.BASE_SERVICE_URL + "/comment")
public interface ICommentApi {

    /**
     * 获取歌曲评论列表
     *
     * @param params 查询数据源
     * @return 评论列表
     */
    @PostMapping("/page")
    GenericResponse<Page<MusicCommentVo>> page(@RequestBody MusicCommentParams params);

    /**
     * 评论歌曲
     *
     * @param params 评论参数
     * @return 是否评论成功
     */
    @PostMapping("/submit")
    GenericResponse<Boolean> submitComment(@RequestBody CommentDTO params);

    /**
     * 评论点赞
     *
     * @param commentId 评论ID
     * @return 是否点赞成功
     */
    @PostMapping("/click")
    GenericResponse<Boolean> click(@RequestParam(value = "commentId") Long commentId);

    /**
     * 删除评论
     *
     * @param commentId 评论ID
     * @return 是否删除成功
     */
    @PostMapping("/omit")
    GenericResponse<Boolean> omit(@RequestParam(value = "commentId") Long commentId);

}
