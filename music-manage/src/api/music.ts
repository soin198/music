import {post} from './request'

const MusicManager = {
    // 歌曲分页列表
    page: (params) => post(`/music/page`, params)
}
export {MusicManager}
