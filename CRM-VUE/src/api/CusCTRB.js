import { post } from '../utils/http'

export function queryAllCusCTRBInfo(params) {
  return post("/api/stats/listCusCTRB", params)
}

export function queryOrderStatsInfo(params) {
  return post("/api/stats/listOrderStats", params)
}

export function queryTotalStatsInfo(params) {
  return post("/api/stats/listTotalStats", params)
}

export function queryAovStatsInfo(params) {
  return post("/api/stats/listAovStats", params)
}