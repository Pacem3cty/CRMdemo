import { post } from '../utils/http'

export function add(params) {
    return post("/api/userRole/add", params)
}

export function update(params) {
    return post("/api/userRole/update", params)
}

export function queryRoleId(params) {
    return post("/api/userRole/queryRoleId", params)
  }