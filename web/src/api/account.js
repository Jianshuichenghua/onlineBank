import $axios from './index'

const baseUrl = `account`;

export function findByAccountLike(data) {

    const url = `${baseUrl}/findByAccountLike`
    return $axios.get(url, data)
}