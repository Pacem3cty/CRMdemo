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

export function queryAllUserInfo(params) {
  return post("/api/user/queryAll",params)
}

export function listUserNameInfo(params) {
  return post("/api/user/listUserName",params)
}

export function listTrueNameInfo(params) {
  return post("/api/user/listTrueName",params)
}

export function add(params) {
  return post("/api/user/add",params)
}

export function update(params) {
  return post("/api/user/update",params)
}

export function getCurrentId(params) {
  return post("/api/user/getCurrentId",params)
}

export function del(params) {
  return post("/api/user/delete",params)
}


