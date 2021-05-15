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
    meta: { title: '登录页' },
    hidden: true
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login'),
    meta: { title: '登录页' },
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
        meta: { title: '首页', icon: 'el-icon-s-data' }
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
        meta: { title: '个人中心' }
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
        meta: { title: '引导指南', icon: 'el-icon-s-flag' }
      }
    ]
  }
]
/*动态添加routers*/
export const asyncRoutes = [
  {
    path: '/permission',
    name: 'Permission',
    component: Layout,
    redirect: '/permission/page-use',
    meta: {
      title: '权限许可',
      icon: 'el-icon-lock'
    },
    children: [
      {
        path: 'page-user',
        name: 'PageUser',
        component: () => import('@/views/permission/page-user'),
        meta: { title: '用户页面', icon: 'el-icon-user' }
      },
      {
        path: 'page-admin',
        name: 'PageAdmin',
        component: () => import('@/views/permission/page-admin'),
        meta: {
          title: '管理员页面',
          icon: 'el-icon-user-solid'
        }
      },
      {
        path: 'roles',
        name: 'Roles',
        component: () => import('@/views/permission/roles'),
        meta: { title: '权限设置', icon: 'el-icon-s-tools' }
      }
    ]
  },
  {
    path: '/table',
    name: 'Table',
    redirect: '/table/base-table',
    component: Layout,
    meta: {
      title: 'Table',
      icon: 'el-icon-table iconfont'
    },
    children: [
      {
        path: 'base-table',
        name: 'BaseTable',
        component: () => import('@/views/table/common-table'),
        meta: { title: '普通表格' }
      },
      {
        path: 'complex-table',
        name: 'ComplexTable',
        component: () => import('@/views/table/complex-table'),
        meta: { title: '复杂表格' }
      }
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
        meta: { title: 'Icons图标', icon: 'el-icon-picture-outline' }
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
        meta: { icon: 'el-icon-s-claim', title: 'Deposit And Withdrawal' }
      },

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
    path: '/fund/transfer',
    component: Layout,
    name: 'transfer',
    redirect: '/fund/transfer',
    children: [
      {
        path: 'transfer',
        name: 'transfer-index',
        component: () => import('@/views/fund/transfer'),
        meta: { icon: 'el-icon-s-claim', title: 'Transfer' }
      }
    ]
  },
  {
    path: '/echarts',
    component: Layout,
    name: 'Echarts',
    redirect: '/echarts/slide-chart',
    meta: { icon: 'el-icon-s-marketing', title: 'Echarts' },
    children: [
      {
        path: 'slide-chart',
        name: 'Sldie-chart',
        component: () => import('@/views/echarts/slide-chart'),
        meta: { title: '滑动charts' }
      },
      {
        path: 'dynamic-chart',
        name: 'Dynamic-chart',
        component: () => import('@/views/echarts/dynamic-chart'),
        meta: { title: '切换charts' }
      },
      {
        path: 'map-chart',
        name: 'Map-chart',
        component: () => import('@/views/echarts/map-chart'),
        meta: { title: 'map' }
      }
    ]
  },
  {
    path: '/excel',
    component: Layout,
    name: 'Excel',
    redirect: '/excel-operate/excel-out',
    meta: { icon: 'el-icon-excel iconfont', title: 'Excel' },
    children: [
      {
        path: 'excel-out',
        name: 'Excel-out',
        component: () => import('@/views/excel-operate/excel-out'),
        meta: { title: 'Excel导出' }
      },
      {
        path: 'excel-in',
        name: 'Excel-in',
        component: () => import('@/views/excel-operate/excel-in'),
        meta: { title: 'Excel导入' }
      },
      {
        path: 'mutiheader-out',
        name: 'Mutiheader-out',
        component: () => import('@/views/excel-operate/mutiheader-out'),
        meta: { title: '多级表头导出' }
      }
    ]
  },
  {
    path: '/error',
    component: Layout,
    name: 'Error',
    redirect: '/error/404',
    children: [
      {
        path: '404',
        name: 'Page404',
        component: () => import('@/views/error-page/404'),
        meta: { title: '404', icon: 'el-icon-s-release' }
      }
    ]
  },
  {
    path: 'https://github.com/gcddblue/vue-admin-webapp',
    name: 'Github',
    meta: { icon: 'el-icon-link', title: '项目链接' }
  },
  NavTest,
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
