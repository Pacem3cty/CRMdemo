import { post } from '../utils/http'

export function queryAll(params) {
    return post("/api/sales/queryAll",params)
  }

  export function del(params) {
    return post("/api/sales/delete",params)
  }
