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
    meta: {
      requiresAuth: true
    },
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/Home.vue'),
    // component: Home,
    //requiresAuth 表示进入该字段需要登录
    children: [
      { path: '/Salechance', component: () => import('../views/sales/basicData/SaleChance.vue'), name: '营销机会', meta: { requiresAuth: true } },
      { path: '/CusDevPlan', component: () => import('../views/sales/basicData/CusDevPlan.vue'), name: '客户开发计划', meta: { requiresAuth: true } },
      { path: '/Cus', component: () => import('../views/customer/basicData/Cus.vue'), name: '客户信息', meta: { requiresAuth: true } },
      { path: '/CusLoss', component: () => import('../views/customer/basicData/CusLoss.vue'), name: '客户流失', meta: { requiresAuth: true } },
      { path: '/ServiceCreate' , component: () => import( '../views/service/basicData/ServiceCreate.vue') ,name: '服务创建', meta: { requiresAuth: true }},
      { path: '/ServiceDisp' , component: () => import( '../views/service/basicData/ServiceDisp.vue') ,name: '服务分配', meta: { requiresAuth: true }},
      { path: '/ServiceHandle' , component: () => import( '../views/service/basicData/ServiceHandle.vue') ,name: '服务处理', meta: { requiresAuth: true }},
      { path: '/ServiceFeedback' , component: () => import( '../views/service/basicData/ServiceFeedback.vue') ,name: '服务反馈', meta: { requiresAuth: true }},
      { path: '/ServiceFile' , component: () => import( '../views/service/basicData/ServiceFile.vue') ,name: '服务归档', meta: { requiresAuth: true }},
      { path: '/Dict', component: () => import('../views/system/basicData/Dict.vue'), name: '字典管理', meta: { requiresAuth: true } },
      { path: '/Role', component: () => import('../views/system/basicData/Role.vue'), name: '角色管理', meta: { requiresAuth: true } },
      { path: '/Module', component: () => import('../views/system/basicData/Module.vue'), name: '资源管理', meta: { requiresAuth: true } },
      { path: '/Account', component: () => import('../views/system/management/AccountUpdate.vue'), name: '账号管理', meta: { requiresAuth: true } },
      { path: '/User', component: () => import('../views/system/basicData/User.vue'), name: '用户管理', meta: { requiresAuth: true } },
      { path: '/CusCTRB', component: () => import('../views/stats/basicData/CusCTRB.vue'), name: '客户贡献分析', meta: { requiresAuth: true } },
      { path: '/CusConsist', component: () => import('../views/stats/basicData/CusConsist.vue'), name: '客户构成分析', meta: { requiresAuth: true } },
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
