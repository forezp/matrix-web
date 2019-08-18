import request from '@/utils/request'

export function addCategory(params) {
  return request({
    url: '/model-category',
    method: 'post',
    params
  })
}

export function updateCategory(params) {
  return request({
    url: '/model-category',
    method: 'put',
    params
  })
}

export function categoryList(params) {
  return request({
    url: '/model-category/pagelist',
    method: 'get',
    params
  })
}
