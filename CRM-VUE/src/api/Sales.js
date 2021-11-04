import { post } from '../utils/http'


export function update(params) {
  return post("/api/sales/update", params)
}

export function queryAll(params) {
  return post("/api/sales/queryAll", params)
}

export function del(params) {
  return post("/api/sales/delete", params)
}

export function getCurrentId(params) {
  return post("/api/sales/getCurrentId", params)
}

export function add(params) {
  return post("/api/sales/add", params)
}

export function listCreatePerson(params) {
  return post("/api/sales/listCreatePerson", params)
}

export function listCustomerName(params) {
  return post("/api/sales/listCustomerName", params)
}

export function queryIsAssigned(params) {
  return post("/api/sales/queryIsAssigned", params)
}

export function queryCusDevPlan(params) {
  return post("/api/sales/tCusDevPlan/queryAll", params)
}

export function cusDevPlanDel(params) {
  return post("/api/sales/tCusDevPlan/delete", params)
}

export function getCusDevPlanCurrentId(params) {
  return post("/api/sales/tCusDevPlan/getCurrentId", params)
}

export function addCusDevPlan(params) {
  return post("/api/sales/tCusDevPlan/add", params)
}

export function updateCusDevPlan(params) {
  return post("/api/sales/tCusDevPlan/update", params)
}
