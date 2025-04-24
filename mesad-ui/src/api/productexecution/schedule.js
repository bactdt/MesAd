import request from '@/utils/request'

// 查询订单排产列表
export function listSchedule(query) {
  return request({
    url: '/productexecution/schedule/list',
    method: 'get',
    params: query
  })
}
export function listSchedulechangeall(query) {
  return request({
    url: '/productexecution/schedule/listcahngeall',
    method: 'get',
    params: query
  })
}

// 查询订单排产详细
export function getSchedule(scheduleId) {
  return request({
    url: '/productexecution/schedule/' + scheduleId,
    method: 'get'
  })
}

// 新增订单排产
export function addSchedule(data) {
  return request({
    url: '/productexecution/schedule',
    method: 'post',
    data: data
  })
}

// 修改订单排产
export function updateSchedule(data) {
  return request({
    url: '/productexecution/schedule',
    method: 'put',
    data: data
  })
}

// 删除订单排产
export function delSchedule(scheduleId) {
  return request({
    url: '/productexecution/schedule/' + scheduleId,
    method: 'delete'
  })
}
