import {post} from './request'

const CoreManager = {
    // 登录
    login: (username, password) => post(`/employee/login?username=${username}&password=${password}`)
}

export {CoreManager}
