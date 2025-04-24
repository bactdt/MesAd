import request from '@/utils/request'

// 查询生产执行列表
export function listExecution(query) {
  return request({
    url: '/productexecution/execution/list',
    method: 'get',
    params: query
  })
}

// 查询生产执行详细
export function getExecution(executionId) {
  return request({
    url: '/productexecution/execution/' + executionId,
    method: 'get'
  })
}

// 新增生产执行
export function addExecution(data) {
  return request({
    url: '/productexecution/execution',
    method: 'post',
    data: data
  })
}

// 修改生产执行
export function updateExecution(data) {
  return request({
    url: '/productexecution/execution',
    method: 'put',
    data: data
  })
}

// 删除生产执行
export function delExecution(executionId) {
  return request({
    url: '/productexecution/execution/' + executionId,
    method: 'delete'
  })
}
