import { post } from '../utils/http'

export function main(params) {
    return post("/api/main", params)
  }