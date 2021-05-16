import Vue from 'vue'
import Router from 'vue-router'
import store from '@/store'

Vue.use(Router)

import Layout from '@/layout'
import NavTest from './modules/nav-test'
import getTitle from '@/utils/getTitle'

/**
 * 路由相关属性说明
 * hidden: 当设置hidden为true时，意思不在sideBars侧边栏中显示
 * mete{
 * title: xxx,  设置sideBars侧边栏名称
 * icon: xxx,  设置ideBars侧边栏图标
 * noCache: true  当设置为true时不缓存该路由页面
 * }
 */

/*通用routers*/
export const currencyRoutes = [
  {
    path: '/register',
    name: 'register',
    component: () => import('@/views/register'),
    meta: { title: 'Register' },
    hidden: true
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login'),
    meta: { title: 'Login' },
    hidden: true
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/error-page/404.vue'),
    hidden: true
  },
  {
    path: '/',
    name: 'Home',
    component: Layout,
    redirect: '/dashbord',
    children: [
      {
        path: 'dashbord',
        name: 'Dashbord',
        component: () => import('@/views/dashboard'),
        meta: { title: 'Home' }
      }
    ]
  },
  {
    path: '/funds',
    component: Layout,
    name: 'funds',
    redirect: '/funds/fund-list',
    children: [
      {
        path: 'fund-list',
        name: 'Fund-list',
        component: () => import('@/views/fund/funds'),
        meta: { title: 'view Funds Transactions' }
      }
    ]
  },
  {
    path: '/personal',
    name: 'Personal',
    component: Layout,
    redirect: '/personal/index',
    hidden: true,
    children: [
      {
        path: 'index',
        name: 'Personal-index',
        component: () => import('@/views/personal'),
        meta: { title: 'View Utility Bills Transaction' }
      }
    ]
  },
  {
    path: '/driver',
    name: 'Driver',
    component: Layout,
    redirect: '/driver/index',
    children: [
      {
        path: 'index',
        name: 'Driver-index',
        component: () => import('@/views/driver-page'),
        meta: { title: 'View Current Balance' }
      }
    ]
  }
]
/*动态添加routers*/
export const asyncRoutes = [
  {
    path: '/fund/transfer',
    component: Layout,
    name: 'transfer',
    redirect: '/fund/transfer',
    children: [
      {
        path: 'transfer',
        name: 'transfer-index',
        component: () => import('@/views/fund/transfer'),
        meta: { title: 'Funds Transfer' }
      }
    ]
  },
  {
    path: '/fund/deposit',
    component: Layout,
    name: 'deposit',
    redirect: '/fund/deposit',
    children: [
      {
        path: 'deposit',
        name: 'deposit-index',
        component: () => import('@/views/fund/deposit'),
        meta: { title: 'Deposit And Withdrawal' }
      },

    ]
  },
  {
    path: '/icons',
    component: Layout,
    name: 'Icons',
    redirect: '/icons/index',
    children: [
      {
        path: 'index',
        name: 'Icons-index',
        component: () => import('@/views/icons'),
        meta: { title: 'View Access Logs' }
      }
    ]
  },
  {
    path: '*',
    name: '*404',
    redirect: '/404',
    hidden: true
  }
]
const creatRouter = () => {
  return new Router({
    routes: currencyRoutes,
    scrollBehavior() {
      return { x: 0, y: 0 }
    }
  })
}

const router = creatRouter()

// 解决addRoute不能删除动态路由问题
export function resetRouter() {
  const reset = creatRouter()
  router.matcher = reset.matcher
}
const routerRole = ["transfer", "transfer-index", "funds", "Fund-list", "deposit", "deposit-index", "Home", "Dashbord", "Driver", "Driver-index", "Permission", "PageUser", "PageAdmin", "Roles", "Table", "BaseTable", "ComplexTable", "Icons", "Icons-index", "Components", "Sldie-yz", "Upload", "Carousel", "Echarts", "Sldie-chart", "Dynamic-chart", "Map-chart", "Excel", "Excel-out", "Excel-in", "Mutiheader-out", "Error", "Page404", "Github", "NavTest", "Nav1", "Nav2", "Nav2-1", "Nav2-2", "Nav2-2-1", "Nav2-2-2", "*404"]

// 导航守卫
router.beforeEach(async (to, from, next) => {
  document.title = getTitle(to.meta.title)
  if (to.path === '/login' || to.path === '/register') {
    next()
  } else {
    if (store.getters.token) {
      const hasRoles = store.getters.roles.length > 0
      next()
      if (hasRoles) {
        next()
      } else {

        const roles = routerRole;

        await store.dispatch('user/setRoles', roles)
        await store.dispatch(
          'permission/getAsyncRoutes',
          roles
        )
        const addRoutes = await store.dispatch(
          'permission/getAsyncRoutes',
          roles
        )
        console.log(roles);
        router.addRoutes(addRoutes)
        // this.$router.push({
        //   path: '/dashbord'
        // })
        // next({
        //   path: '/dashbord',
        //   query: {
        //   }
        // })
      }
    } else {
      next({
        path: '/login',
        query: {
          // redirect: to.fullPath
        }
      })
    }
  }
})
export default router
