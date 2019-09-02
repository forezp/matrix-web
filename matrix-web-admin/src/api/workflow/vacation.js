import request from '@/utils/request'

export function postVacation(params) {
  return request({
    headers: {
      'Content-Type': 'application/json'
    },
    transformRequest: [function(data) {
      data = JSON.stringify(data)
      console.log(data)
      return data
    }],
    url: '/vacation',
    method: 'post',
    params: {},
    data: {
      'userId': params.userId,
      'name': params.name,
      'remarks': params.remarks,
      'nextUserId': params.nextUserId,
      'reason': params.reason,
      'approveComments': params.approveComments,
      'procDefKey': params.procDefKey,
      'applyDate': params.applyDate,
      'realname': params.realname,
      'processId': params.processId,
      'type': params.type,
      'startTime': params.startTime,
      'endTime': params.endTime,
      'step': params.step
    }
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

