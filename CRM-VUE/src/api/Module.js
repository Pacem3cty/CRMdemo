import { post } from '../utils/http'

export function loadModuleData(params) {
    return post("/api/module/loadData", params)
  }