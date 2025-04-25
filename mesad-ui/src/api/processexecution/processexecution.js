import request from '@/utils/request'

// 查询生产工序执行列表
export function listProcessexecution(query) {
  return request({
    url: '/processexecution/processexecution/list',
    method: 'get',
    params: query
  })
}

// 查询生产工序执行详细
export function getProcessexecution(processExecutionId) {
  return request({
    url: '/processexecution/processexecution/' + processExecutionId,
    method: 'get'
  })
}

// 新增生产工序执行
export function addProcessexecution(data) {
  return request({
    url: '/processexecution/processexecution',
    method: 'post',
    data: data
  })
}

// 修改生产工序执行
export function updateProcessexecution(data) {
  return request({
    url: '/processexecution/processexecution',
    method: 'put',
    data: data
  })
}

// 删除生产工序执行
export function delProcessexecution(processExecutionId) {
  return request({
    url: '/processexecution/processexecution/' + processExecutionId,
    method: 'delete'
  })
}
