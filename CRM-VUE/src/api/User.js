import { post } from '../utils/http'

/*登录请求 */

export function updatePwd(params) {
    return post("/api/user/updatePwd",params)
  }

export function listUserInfo(params) {
  return post("/api/user/listInfo",params)
}

export function updateUserInfo(params) {
  return post("/api/user/updateInfo",params)
}

