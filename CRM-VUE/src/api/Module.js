import { post } from '../utils/http'

export function loadModuleData(params) {
    return post("/api/module/loadData", params)
  }

  export function queryAllModuleInfo(params) {
    return post("/api/module/queryAll", params)
  }

  export function update(params) {
    return post("/api/module/update", params)
  }

  export function add(params) {
    return post("/api/module/add", params)
  }

  export function del(params) {
    return post("/api/module/delete", params)
  }

  export function getCurrentId(params) {
    return post("/api/module/getCurrentId",params)
  }