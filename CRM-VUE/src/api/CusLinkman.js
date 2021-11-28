import { post } from '../utils/http'

export function queryAllCusLinkmanInfo(params) {
  return post("/api/cusLinkman/queryAll", params)
}

export function getCurrentId(params) {
    return post("/api/cusLinkman/getCurrentId", params)
  }

export function add(params) {
  return post("/api/cusLinkman/add", params)
}

export function update(params) {
  return post("/api/cusLinkman/update", params)
}

export function del(params) {
  return post("/api/cusLinkman/delete", params)
}