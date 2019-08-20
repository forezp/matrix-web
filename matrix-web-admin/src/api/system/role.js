import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/sysRole/pagelist',
    method: 'get',
    params
  })
}

export function save(params) {
  return request({
    url: '/sysRole',
    method: 'post',
    params
  })
}

export function remove(roleId) {
  return request({
    url: '/role',
    method: 'delete',
    params: {
      roleId: roleId
    }
  })
}

export  function roleTreeListByIdUser(idUser){
  return request({
    url: `/sysRole/${idUser}`,
    method: 'get',
    params: {
      idUser: idUser
    }
  })
}

export function savePermissons(params) {
  return request({
    url: '/role/savePermisson',
    method: 'post',
    params
  })
}
