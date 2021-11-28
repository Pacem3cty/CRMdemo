import { post } from '../utils/http'

export function queryAllCusInfo(params) {
  return post("/api/cus/queryAll", params)
}

export function getCurrentId(params) {
  return post("/api/cus/getCurrentId", params)
}

export function getCusNum(params) {
  return post("/api/cus/getCusNum", params)
}

export function add(params) {
  return post("/api/cus/add", params)
}

export function update(params) {
  return post("/api/cus/update", params)
}

export function del(params) {
  return post("/api/cus/delete", params)
}

export function listCusName(params) {
  return post("/api/cus/listCusName", params)
}
