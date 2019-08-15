import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/model/pagelist',
    method: 'get',
    params
  })
}

export function save(params) {
  return request({
    url: '/model',
    method: 'post',
    params
  })
}

