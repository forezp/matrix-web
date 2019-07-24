import request from '@/utils/request'

export function tree() {
  return request({
    url: '/sysOrg/tree',
    method: 'get'
  })
}

export function list() {
  return request({
    url: '/sysOrg/tree',
    method: 'get'
  })
}

// export function save(params) {
//   return request({
//     url: '/sysOrg',
//     method: 'post',
//     params: params
//   })
// }

export function save(params) {
  console.log(params)
  return request({
    headers: {
      'Content-Type': 'application/json'
    },
    transformRequest: [function(data) {
      data = JSON.stringify(data)
      console.log(data)
      return data
    }],
    url: '/sysOrg',
    method: 'post',
    params: {},
    data: {
      'simpleName': params.simpleName,
      'fullName': params.fullName,
      'orgId': params.orgId,
      'pid': params.pid,
      'sortNo': params.sortNo,
      'level': params.level,
      'remarks': params.remarks
    }
  })
}

export function del(id) {
  return request({
    url: '/sysOrg',
    method: 'delete',
    params: {
      id: id
    }
  })
}
