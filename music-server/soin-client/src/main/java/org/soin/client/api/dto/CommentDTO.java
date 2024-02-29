package org.soin.client.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 提交评论
 *
 * @author J.FLa.Soin
 * @version 1.0.0
 * @date 2024-01-08 15:53
 **/
@Data
public class CommentDTO implements Serializable {

    private static final long serialVersionUID = 7881075963677007809L;

    /**
     * 人员ID
     */
    @NotNull(message = "请提供评论人ID")
    private Long userId;

    /**
     * 音乐ID
     */
    @NotNull(message = "请提供音乐ID")
    private Long musicId;

    /**
     * 评论内容
     */
    @NotBlank(message = "请提供评论内容")
    private String content;

}
