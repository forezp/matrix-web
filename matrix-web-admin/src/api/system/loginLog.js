import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/sys-login-log/pagelist',
    method: 'get',
    params
  })
}

export function clear() {
  return request({
    url: '/loginLog',
    method: 'delete'
  })
}
