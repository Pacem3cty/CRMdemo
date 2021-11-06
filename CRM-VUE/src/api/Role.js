import { post } from '../utils/http'

export function listRoleNameInfo(params) {
  return post("/api/role/listRoleName", params)
}

export function queryAllRoleInfo(params) {
  return post("/api/role/queryAll", params)
}

export function del(params) {
  return post("/api/role/delete", params)
}

export function update(params) {
  return post("/api/role/update", params)
}

export function add(params) {
  return post("/api/role/add", params)
}

export function getCurrentId(params) {
  return post("/api/role/getCurrentId",params)
}