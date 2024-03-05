import {post} from './request'

const UserManager = {
    // 获取平台用户分页列表
    page: (params) => post(`/custom/page`, params)
}

export {UserManager}
