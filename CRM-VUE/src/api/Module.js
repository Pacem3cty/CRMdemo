import { post } from '../utils/http'

export function loadModuleData(params) {
    return post("/api/module/loadData", params)
  }

  export function queryAllModuleInfo(params) {
    return post("/api/module/queryAll", params)
  }