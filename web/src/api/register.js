import $axios from './index'

const preUrl = `/account`
export function register(data) {
  const url = `/${preUrl}/register`;
  return $axios.post(url, data)
}
