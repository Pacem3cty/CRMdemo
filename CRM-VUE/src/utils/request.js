import axios from 'axios'


axios.defaults.timeout = 10000
//axios异步请求默认超时设置为10000ms
const service = axios.create({
    // baseURL: '/api',
    // 跨域请求时是否需要使用凭证
    withCredentials: true,
    // 跨域请求 30s 超时
    timeout: 30000,
    validateStatus: function () {
        // 使用async-await，处理reject情况较为繁琐，所以全部返回resolve，在业务代码中处理异常
        return true
    },
    // 在向服务器发送请求前，序列化请求数据
    transformRequest: [function (data) {
        data = JSON.stringify(data)//将JSON格式的数据转换为字符串类型
        return data
    }],
    // 在传递给 then/catch 前，修改响应数据
    transformResponse: [function (data) {
        if (typeof data === 'string' && data.startsWith('{')) {
            data = JSON.parse(data)
        }
        return data
    }],
    headers: {//请求头
        get: {//get请求中内容格式为默认格式
          'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
        },
        post: {//post请求中内容格式为JSON
          'Content-Type': 'application/json;charset=utf-8'
        }
      },
})


// 请求拦截器
service.interceptors.request.use((config) => {

    let pathname = location.pathname;
    if(localStorage.getItem('token')){
        if(pathname != '/' &&  pathname != '/login'){
            config.headers.common['token'] = localStorage.getItem('token');
        }
    }
    return config
}, (error) => {
    // 错误抛到业务代码
    error.data = {}
    error.data.msg = '服务器异常，请联系管理员！'
    return Promise.resolve(error)
})

// 根据不同的状态码，生成不同的提示信息
const showStatus = (status) => {
    let message = ''
    // 这一坨代码可以使用策略模式进行优化
    switch (status) {
        case 400:
            message = '请求错误(400)'
            break
        case 401:
            message = '未授权，请重新登录(401)'
            break
        case 403:
            message = '拒绝访问(403)'
            break
        case 404:
            message = '请求出错(404)'
            break
        case 408:
            message = '请求超时(408)'
            break
        case 500:
            message = '服务器错误(500)'
            break
        case 501:
            message = '服务未实现(501)'
            break
        case 502:
            message = '网络错误(502)'
            break
        case 503:
            message = '服务不可用(503)'
            break
        case 504:
            message = '网络超时(504)'
            break
        case 505:
            message = 'HTTP版本不受支持(505)'
            break
        default:
            message = `连接出错(${status})!`
    }
    return `${message}，请检查网络或联系管理员！`
}


// 响应拦截器
service.interceptors.response.use((response) => {
    const status = response.status
    let msg = ''
    if (status < 200 || status >= 300) {
        // 处理http错误，抛到业务代码
        msg = showStatus(status)
        if (typeof response.data === 'string') {
            response.data = { msg }
        } else {
            response.data.msg = msg
        }
    }
    return response
}, (error) => {
    // 错误抛到业务代码
    error.data = {}
    error.data.msg = '请求超时或服务器异常，请检查网络或联系管理员！'
    return Promise.resolve(error)
})

export default service