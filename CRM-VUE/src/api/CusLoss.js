import { post } from '../utils/http'

export function queryAllCusLossInfo(params) {
    return post("/api/cusLoss/queryAll", params)
  }

  export function listCusName(params) {
    return post("/api/cusLoss/listCusName", params)
  }

  export function listCusManager(params) {
    return post("/api/cusLoss/listCusManager", params)
  }

  export function update(params) {
    return post("/api/cusLoss/update", params)
  }

