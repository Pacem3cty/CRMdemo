import { post } from '../utils/http'

export function loadCheckedData(params) {
  return post("/api/permission/loadData", params)
}

export function authModules(params) {
  return post("/api/permission/authModules", params)
}