import { post } from '../utils/http'

export function queryAllOrderDetailsInfo(params) {
  return post("/api/cusOrderDetails/queryAll", params)
}

export function getCurrentId(params) {
    return post("/api/cusOrderDetails/getCurrentId", params)
  }

export function add(params) {
  return post("/api/cusOrderDetails/add", params)
}

export function update(params) {
  return post("/api/cusOrderDetails/update", params)
}

export function del(params) {
  return post("/api/cusOrderDetails/delete", params)
}