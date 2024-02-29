import {getBaseURL, get, post} from "./request";

const HttpManager = {
    attachImageUrl: (url) => url ? `${getBaseURL()}/${url}` : "https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png",
    uploadUrl: (userId) => `${getBaseURL()}/user/avatar/update?id=${userId}`,
    updateUserMsg: (params) => post(`user/update`, params),
    updateUserPassword: (params) => post(`user/updatePassword`, params),

    // =======================> 歌单 API
    // 获取全部歌单
    getSongList: () => get(`/music/tree/musicTreeQuery`),
    // 获取歌单类型
    getSongListOfStyle: (style) => get(`songList/style/detail?style=${style}`),
    // 返回标题包含文字的歌单
    getSongListOfLikeTitle: (keywords) => get(`songList/likeTitle/detail?title=${keywords}`),
    // 返回歌单里指定歌单ID的歌曲
    getListSongOfSongId: (songListId) => get(`listSong/detail?songListId=${songListId}`),

    // =======================> 收藏 API
    // 返回的指定用户ID的收藏列表
    getCollectionOfUser: (userId) => get(`collection/detail?userId=${userId}`),

    // =======================> 评分 API
    // 提交评分
    setRank: (params) => post(`rankList/add`, params),
    // 获取指定歌单的评分
    getRankOfSongListId: (songListId) => get(`rankList?songListId=${songListId}`),
    // 获取指定用户的歌单评分
    getUserRank: (consumerId, songListId) => get(`/rankList/user?consumerId=${consumerId}&songListId=${songListId}`),
    // =======================> 歌曲 API
    // 返回指定歌曲ID的歌曲
    getSongOfId: (id) => get(`song/detail?id=${id}`),
    // 返回指定歌手名的歌曲
    getSongOfSingerName: (keywords) => get(`song/singerName/detail?name=${keywords}`),
    // 下载音乐
    downloadMusic: (url) => get(url, {responseType: "blob"}),
};

export {HttpManager};
