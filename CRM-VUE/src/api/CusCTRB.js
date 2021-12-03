import { post } from '../utils/http'

export function queryAllCusCTRBInfo(params) {
  return post("/api/stats/listCusCTRB", params)
}