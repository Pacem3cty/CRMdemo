import { post } from '../utils/http'

export function queryAllCusReprieveInfo(params) {
  return post("/api/cusReprieve/queryAll", params)
}

export function getCurrentId(params) {
  return post("/api/cusReprieve/getCurrentId", params)
}

export function add(params) {
  return post("/api/cusReprieve/add", params)
}

export function update(params) {
  return post("/api/cusReprieve/update", params)
}

export function del(params) {
  return post("/api/cusReprieve/delete", params)
}
