import request from '@/utils/request'

export function processList(params) {
  return request({
    url: '/process/pagelist',
    method: 'get',
    params
  })
}

export function updateState(state, procDefId) {
  return request({
    url: `/process/${state}`,
    method: 'put',
    params: {
      procDefId: procDefId
    }
  })
}

export function updateCategory(procDefId, category) {
  return request({
    url: `/process/update-category/${procDefId}`,
    method: 'put',
    params: {
      category: category
    }
  })
}

