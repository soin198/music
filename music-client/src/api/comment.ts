import {post} from "./request";

const CommentManager = {
    // 获取评论列表
    page: (params) => post(`/comment/page`, params),
    //提交歌曲评论
    submit: (params) => post(`/comment/submit`, params),

};

export {CommentManager};
