import axios from 'axios'
import Qs from 'qs'
import store from '@/store'
import router from '@/router'
import Vue from 'vue'
import { Loading, Message } from 'element-ui' // 引用element-ui的加载和消息提示组件
const codeMsgMap = {
  ACCOUNT_IS_BLANK: 'Account number is empty',
  ACCOUNT_IS_EXISTED: 'Account number already exists',
  ACCOUNT_IS_NOT_EXISTED: 'Account does not exist',
  TARGET_ACCOUNT_IS_NOT_EXISTED: 'The target account does not exist',
  PASSWORD_IS_WRONG: 'Wrong password',
  NOT_SUFFICIENT_FUNDS: 'Insufficient account balance',
}
const $axios = axios.create({
  // 设置超时时间
  timeout: 30000,
  // 基础url，会在请求url中自动添加前置链接
  baseURL: process.env.VUE_APP_BASE_API
})
Vue.prototype.$http = axios // 并发请求
// 在全局请求和响应拦截器中添加请求状态
let loading = null

// 请求拦截器
$axios.interceptors.request.use(
  config => {
    loading = Loading.service({ text: 'loading' })
    const token = store.getters.token
    if (token) {
      config.headers.Authorization = token // 请求头部添加token
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)
// 响应拦截器
$axios.interceptors.response.use(
  res => {
    if (loading) {
      loading.close()
    }
    // debugger
    const response = res.data;
    if (response.successful) {
      return Promise.resolve(response.body)
    } else {
      const msg = codeMsgMap[response.code];
      Message.error(msg)
      return Promise.reject({ ...response, msg })
    }
  },
  error => {
    if (loading) {
      loading.close()
    }
    console.log(error)
    if (error.response) {
      switch (error.response.status) {
        case 401:
          // 返回401 清除token信息并跳转到登陆页面
          store.commit('DEL_TOKEN')
          router.replace({
            path: '/login',
            query: {
              redirect: router.currentRoute.fullPath
            }
          })
          break
        case 404:
          Message.error('网络请求不存在')
          break
        default:
          Message.error(error.response.data.message)
      }
    } else {
      // 请求超时或者网络有问题
      if (error.message.includes('timeout')) {
        Message.error('请求超时！请检查网络是否正常')
      } else {
        Message.error('请求失败，请检查网络是否已连接')
      }
    }
    return Promise.reject(error)
  }
)

// get，post请求方法
export default {
  post(url, data) {
    return $axios({
      method: 'post',
      url,
      data: Qs.stringify(data),
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      }
    })
  },
  get(url, params) {
    return $axios({
      method: 'get',
      url,
      params
    })
  }
}
