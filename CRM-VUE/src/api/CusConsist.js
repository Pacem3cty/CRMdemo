import { post } from '../utils/http'

export function queryCusConsistInfo(params) {
  return post("/api/stats/listConsistStats", params)
}

export function queryCusCsrInfo(params) {
  return post("/api/stats/listCusCsrStats", params)
}

export function queryCusCcrInfo(params) {
  return post("/api/stats/listCusCcrStats", params)
}