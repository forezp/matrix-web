import request from '@/utils/request'

export function addCategory(params) {
  return request({
    url: '/task-group',
    method: 'post',
    params
  })
}

export function updateCategory(params) {
  return request({
    url: '/task-group',
    method: 'put',
    params
  })
}

export function categoryList(params) {
  return request({
    url: '/task-group/pagelist',
    method: 'get',
    params
  })
}
