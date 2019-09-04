import request from '@/utils/request'

export function todoTaskList(params) {
  return request({
    url: '/task/todo',
    method: 'get',
    params
  })
}

export function myTaskList(params) {
  return request({
    url: '/task/starter',
    method: 'get',
    params
  })
}

export function finishTaskList(params) {
  return request({
    url: '/task/finish',
    method: 'get',
    params
  })
}
