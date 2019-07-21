import request from '@/utils/request'

export function getList() {
  return request({
    url: '/sysMenu/list',
    method: 'get'
  })
}

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
    url: '/sysMenu',
    method: 'post',
    params: {},
    data: {
      'code': params.code,
      'pcode': params.pcode,
      'url': params.url,
      'icon': params.icon,
      'ismenu': params.ismenu,
      'isopen': params.isopen,
      'levels': params.levels,
      'name': params.name,
      'num': params.num,
      'status': params.status
    }
  })
}

export function delMenu(id) {
  return request({
    url: '/sysMenu',
    method: 'delete',
    params: {
      id: id
    }
  })
}
export function menuTreeListByRoleId(roleId) {
  return request({
    url: '/menu/menuTreeListByRoleId',
    method: 'get',
    params: {
      roleId: roleId
    }
  })
}
