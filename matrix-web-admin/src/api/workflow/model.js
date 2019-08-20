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

export function updateCategory(params) {
  return request({
    url: '/model',
    method: 'put',
    params
  })
}

export function remove(params) {
  return request({
    url: '/model',
    method: 'delete',
    params: {
      id: params
    }
  })
}

export function deploy(id) {
  return request({
    url: `/model/deploy/${id}`,
    method: 'put'
  })
}

