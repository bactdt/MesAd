import request from '@/utils/request'

// 查询生产参数记录列表
export function listParameter(query) {
  return request({
    url: '/production_parameter/parameter/list',
    method: 'get',
    params: query
  })
}

// 查询生产参数记录详细
export function getParameter(parameterId) {
  return request({
    url: '/production_parameter/parameter/' + parameterId,
    method: 'get'
  })
}

// 新增生产参数记录
export function addParameter(data) {
  return request({
    url: '/production_parameter/parameter',
    method: 'post',
    data: data
  })
}

// 修改生产参数记录
export function updateParameter(data) {
  return request({
    url: '/production_parameter/parameter',
    method: 'put',
    data: data
  })
}

// 删除生产参数记录
export function delParameter(parameterId) {
  return request({
    url: '/production_parameter/parameter/' + parameterId,
    method: 'delete'
  })
}
