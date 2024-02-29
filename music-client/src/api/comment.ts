import {post} from "./request";

const CommentManager = {
    // 获取评论列表
    page: (params) => post(`/comment/page`, params),
    //提交歌曲评论
    submit: (params) => post(`/comment/submit`, params),
    //点赞评论
    clickLike: (commentId) => post(`/comment/click?commentId=${commentId}`),
    //删除评论
    omit: (commentId) => post(`/comment/omit?commentId=${commentId}`)
};

export {CommentManager};
