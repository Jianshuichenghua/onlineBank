import $axios from './index'

const baseUrl = `fund`;

export function deposit(data) {

  const url = `${baseUrl}/deposit`;
  return $axios.post(url, data)
}

export function withdrawal(data) {

    const url = `${baseUrl}/withdrawal`;
    return $axios.post(url, data)
}

export function transfer(data) {

  const url = `${baseUrl}/transfer`;
  return $axios.post(url, data)
}