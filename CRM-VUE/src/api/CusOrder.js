import { post } from '../utils/http'

export function queryAllCusOrderInfo(params) {
  return post("/api/cusOrder/queryAll", params)
}

export function getCurrentId(params) {
    return post("/api/cusOrder/getCurrentId", params)
  }

export function add(params) {
  return post("/api/cusOrder/add", params)
}

export function update(params) {
  return post("/api/cusOrder/update", params)
}

export function del(params) {
  return post("/api/cusOrder/delete", params)
}