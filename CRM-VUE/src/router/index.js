import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)


const originalPush = VueRouter.prototype.push
   VueRouter.prototype.push = function push(location) {
   return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/',
    name: 'Login',
    component: () => import(/* webpackChunkName: "about" */ '../views/Login.vue')
  },
  {
    path: '/home',
    name: 'Home',
    meta:{
      requiresAuth:true
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue'),
    // component: Home,
    //requiresAuth 表示进入该字段需要登录
    children: [
      { path: '/Salechance' , component: () => import( '../views/sales/basicData/SaleChance.vue') ,name: '营销机会', meta: { requiresAuth: true }},
      // { path: '/quotation' , component: () => import( '../views/management/Quotation.vue') ,name: '报价单', meta: { requiresAuth: true }},
      // { path: '/customerCenter' , component: () => import( '../views/management/CustomerCenter.vue') ,name: '客户中心', meta: { requiresAuth: true }},
      // { path: '/equipment' , component: () => import( '../views/basicData/Equipment.vue') ,name: '设备', meta: { requiresAuth: true }},
      // { path: '/dict' , component: () => import( '../views/basicData/Dict.vue') ,name: '字典', meta: { requiresAuth: true }},
      { path: '/account' , component: () => import( '../views/account/management/AccountUpdate.vue') ,name: '账号管理', meta: { requiresAuth: true }},
    ]  
  
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  
  {
    path: "/*",
    component: () => import(/* webpackChunkName: "about" */ '../views/404.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
