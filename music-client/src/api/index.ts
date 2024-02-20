import {getBaseURL, get, post, deletes} from "./request";

const HttpManager = {
    attachImageUrl: (url) => url ? `${getBaseURL()}/${url}` : "https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png",
    login: (params) => post(`/custom/authority/login`, params),
    register: (params) => post(`/custom/authority/register`, params),
    cancelAccount: (userId) => post(`/custom/authority/cancel?userId=${userId}`),
    updateUserMsg: (params) => post(`user/update`, params),
    updateUserPassword: (params) => post(`user/updatePassword`, params),
    getOne: (userId) => get(`/custom/authority/detailsQuery?userId=${userId}`),
    uploadUrl: (userId) => `${getBaseURL()}/user/avatar/update?id=${userId}`,
    generateCode: (phone, smsType) => post(`/sms/generateCode?phone=${phone}&type=${smsType}`),

    // =======================> 歌单 API
    // 获取全部歌单
    getSongList: () => get(`/music/tree/musicTreeQuery`),
    // 获取歌单类型
    getSongListOfStyle: (style) => get(`songList/style/detail?style=${style}`),
    // 返回标题包含文字的歌单
    getSongListOfLikeTitle: (keywords) => get(`songList/likeTitle/detail?title=${keywords}`),
    // 返回歌单里指定歌单ID的歌曲
    getListSongOfSongId: (songListId) => get(`listSong/detail?songListId=${songListId}`),

    // =======================> 歌手 API
    // 返回所有歌手
    getAllSinger: () => get("/base/singer/singerQuery"),
    // 通过性别对歌手分类
    getSingerOfSex: (sex) => get(`singer/sex/detail?sex=${sex}`),

    // =======================> 收藏 API
    // 返回的指定用户ID的收藏列表
    getCollectionOfUser: (userId) => get(`collection/detail?userId=${userId}`),
    // 添加收藏的歌曲 type: 0 代表歌曲， 1 代表歌单
    setCollection: (params) => post(`collection/add`, params),

    deleteCollection: (userId, songId) => deletes(`collection/delete?userId=${userId}&&songId=${songId}`),

    isCollection: (params) => post(`collection/status`, params),

    // =======================> 评分 API
    // 提交评分
    setRank: (params) => post(`rankList/add`, params),
    // 获取指定歌单的评分
    getRankOfSongListId: (songListId) => get(`rankList?songListId=${songListId}`),
    // 获取指定用户的歌单评分
    getUserRank: (consumerId, songListId) => get(`/rankList/user?consumerId=${consumerId}&songListId=${songListId}`),

    // =======================> 评论 API
    // 添加评论
    setComment: (params) => post(`comment/add`, params),
    // 删除评论
    deleteComment: (id) => get(`comment/delete?id=${id}`),
    // 点赞
    setSupport: (params) => post(`comment/like`, params),
    // 返回所有评论
    getAllComment: (type, id) => {
        let url = "";
        if (type === 1) {
            url = `comment/songList/detail?songListId=${id}`;
        } else if (type === 0) {
            url = `comment/song/detail?songId=${id}`;
        }
        return get(url);
    },

    // =======================> 歌曲 API
    // 返回指定歌曲ID的歌曲
    getSongOfId: (id) => get(`song/detail?id=${id}`),
    // 返回指定歌手ID的歌曲
    getSongOfSingerId: (id) => get(`song/singer/detail?singerId=${id}`),
    // 返回指定歌手名的歌曲
    getSongOfSingerName: (keywords) => get(`song/singerName/detail?name=${keywords}`),
    // 下载音乐
    downloadMusic: (url) => get(url, {responseType: "blob"}),
};

export {HttpManager};
