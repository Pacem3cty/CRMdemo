import { post } from '../utils/http'

export function queryCusConsistInfo(params) {
  return post("/api/stats/listConsistStats", params)
}