import {get, post} from "./request";

const MusicManager = {
    // 获取歌手下歌曲列表
    page: (params) => post(`/music/page`, params),
    //歌单分页列表
    singerRandomQuery: (params) => post(`/base/singer/singerQuery`, params),
    //获取歌曲评论页数据
    musicComposeQuery: (musicId) => get(`/music/musicComposeQuery?musicId=${musicId}`),
};

export {MusicManager};
