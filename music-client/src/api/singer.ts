import {get,post} from "./request";

const SingerManager = {

    // 歌曲分页列表
    singerRandomQuery: (params) => post(`/base/singer/singerQuery`, params)
};

export {SingerManager};
