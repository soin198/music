import {get, post} from "./request";

const CollectManager = {
    // 添加我喜欢
    saveLike: (userId, musicId) => post(`/music/saveLike?userId=${userId}&musicId=${musicId}`),
    // 取消我喜欢
    cancelLike: (userId, musicId) => post(`/music/cancelLike?userId=${userId}&musicId=${musicId}`),

};

export {CollectManager};
