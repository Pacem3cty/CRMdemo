import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/css/main.css'
import locale from 'element-ui/lib/locale/lang/zh-CN'
import axios from 'axios'
import md5 from 'js-md5';
import * as echarts from 'echarts';

import VueCookies from 'vue-cookies'
Vue.use(VueCookies)

// var VueCookie = require('vue-cookie');
// Vue.use(VueCookie);

Vue.prototype.VueCookies = VueCookies
Vue.prototype.$echarts = echarts 
Vue.prototype.$md5 = md5
Vue.prototype.$axios = axios    //全局注册，使用方法为:this.$axios

axios.interceptors.request.use(function (config) {// 为请求头添加Authorization字段为服务端返回的token
  config.headers.token = localStorage.getItem('token')//请求头中cookies携带token
  return config// return config是固定用法 必须有返回值
  })

Vue.use(ElementUI,{locale})

Vue.config.productionTip = false
Vue.config.devtools = false

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)){ // 判断该路由是否需要登录权限
  //  console.log('需要登录');
  if (VueCookies.get('token') != null || VueCookies.get('token') != '') {// 判断cookies当前的token是否存在
  //  if (localStorage.token) { // 判断当前的token是否存在  登录存入的token
    next();
   }
   else {
     alert("没有访问权限！请登录!");
    next(
      {
     path: '/',
     query: {redirect: to.fullPath} // 将跳转的路由path作为参数，登录成功后跳转到该路由
    })
   }
  }
  else {
   next();
  }
 });


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
