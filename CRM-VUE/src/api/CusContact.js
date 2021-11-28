import { post } from '../utils/http'

export function queryAllCusContactInfo(params) {
  return post("/api/cusContact/queryAll", params)
}

export function getCurrentId(params) {
    return post("/api/cusContact/getCurrentId", params)
  }

export function add(params) {
  return post("/api/cusContact/add", params)
}

export function update(params) {
  return post("/api/cusContact/update", params)
}

export function del(params) {
  return post("/api/cusContact/delete", params)
}

