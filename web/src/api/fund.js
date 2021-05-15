import $axios from './index'

const baseUrl = `fund`;

export function deposit(data) {

  const url = '/deposit'
  return $axios.post(url, data)
}

export function withdrawal(data) {

    const url = '/withdrawal'
    return $axios.post(url, data)
}