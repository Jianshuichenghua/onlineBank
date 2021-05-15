import $axios from './index'

const preFix = '/account';

export function login(data) {
  const url = `${preFix}/login`
  return $axios.post(url, data)
}
export function getInfo() {
  const url = '/getInfo'
  return $axios.get(url)
}
