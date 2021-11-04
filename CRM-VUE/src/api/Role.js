import { post } from '../utils/http'

export function listRoleNameInfo(params) {
  return post("/api/role/listRoleName", params)
}

export function queryAllRoleInfo(params) {
  return post("/api/role/queryAll", params)
}

