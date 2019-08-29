import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/user/pagelist',
    method: 'get',
    params
  })
}

// export function saveUser(params) {
//   return request({
//     url: '/user',
//     method: 'post',
//     params
//   })
// }

export function saveUser(params) {
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
    url: '/user',
    method: 'post',
    params: {},
    data: {
      'userId': params.userId,
      'simpleName': params.simpleName,
      'orgId': params.orgId,
      'password': params.password,
      'mobile': params.mobile,
      'email': params.email,
      'birthday': params.birthday,
      'status': params.status,
      'realname': params.realname,
      'sex': params.sex
    }
  })
}

export function remove(userId) {
  return request({
    url: '/user',
    method: 'delete',
    params: {
      userId
    }
  })
}

export function setRole(params) {
  return request({
    url: '/user/roles',
    method: 'post',
    params
  })
}

export function getCurrentUser() {
  return request({
    url: '/user/currentUser',
    method: 'get'
  })
}
