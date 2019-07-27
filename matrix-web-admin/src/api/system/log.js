import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/syslog/pagelist',
    method: 'get',
    params
  })
}

export function clear(ids) {
  return request({
    url: '/syslog/currentpage',
    method: 'delete',
    params: {
      ids: ids
    }
  })
}
