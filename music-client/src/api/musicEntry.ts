import {get, post} from "./request";

const MusicEntryManager = {

    //歌单分页列表
    singerRandomQuery: (params) => post(`/base/singer/singerQuery`, params)
};

export {MusicEntryManager};
