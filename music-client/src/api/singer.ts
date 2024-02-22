import {get, post} from "./request";

const SingerManager = {

    // 歌手分页列表
    singerRandomQuery: (params) => post(`/base/singer/singerQuery`, params),

    // 根据歌手ID获取歌手详情
    singerQueryById: (singerId) => get(`/base/singer/singerQueryById?singerId=${singerId}`)

};

export {SingerManager};
