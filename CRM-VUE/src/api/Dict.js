import { post } from '../utils/http'

export function listDataNameInfo(params) {
  return post("/api/dict/listDataName", params)
}

export function queryAllDictInfo(params) {
  return post("/api/dict/queryAll", params)
}

export function del(params) {
  return post("/api/dict/delete", params)
}

export function update(params) {
  return post("/api/dict/update", params)
}

export function add(params) {
  return post("/api/dict/add", params)
}

export function getCurrentId(params) {
  return post("/api/dict/getCurrentId",params)
} 