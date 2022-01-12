import { post } from '../utils/http'


export function update(params) {
  return post("/api/service/update", params)
}

export function queryAll(params) {
  return post("/api/service/queryAll", params)
}

export function del(params) {
  return post("/api/service/delete", params)
}

export function getCurrentId(params) {
  return post("/api/service/getCurrentId", params)
}

export function add(params) {
  return post("/api/service/add", params)
}

export function updatePart(params) {
  return post("/api/service/updatePart", params)
}

export function listCustomerName(params) {
  return post("/api/service/listCustomerName", params)
}

export function queryServiceTypeInfo(params) {
  return post("/api/stats/listServiceTypeStats", params)
}

export function queryCsrInfo(params) {
  return post("/api/stats/listCsrStats", params)
}

