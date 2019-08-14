import request from '@/utils/request'

export function getTypeList(params) {
  return request({
    url: '/sys-dict-type/pagelist',
    method: 'get',
    params
  })
}

export function getList(params) {
  return request({
    url: '/sys-dict/pagelist',
    method: 'get',
    params
  })
}

export function save(params) {
  return request({
    headers: {
      'Content-Type': 'application/json'
    },
    transformRequest: [function(data) {
      data = JSON.stringify(data)
      console.log(data)
      return data
    }],
    url: '/sys-dict',
    method: 'post',
    params: {},
    data: {
      'codeId': params.codeId,
      'codeName': params.codeName,
      'typeId': params.typeId,
      'typeName': params.typeName,
      'sort': params.sort,
      'remarks': params.remarks
    }
  })
}

export function update(id,params) {
  return request({
    headers: {
      'Content-Type': 'application/json'
    },
    transformRequest: [function(data) {
      data = JSON.stringify(data)
      console.log(data)
      return data
    }],
    url: `/sys-dict/${id}`,
    method: 'put',
    params: {},
    data: {
      'codeId': params.codeId,
      'codeName': params.codeName,
      'typeId': params.typeId,
      'typeName': params.typeName,
      'sort': params.sort,
      'remarks': params.remarks
    }
  })
}

export function remove(id) {
  return request({
    url: `/sys-dict/${id}`,
    method: 'delete'
  })
}
