import { post } from '../utils/http'

/*登录请求 */

export function login(params) {
    return post("/api/user/login",params)
  }