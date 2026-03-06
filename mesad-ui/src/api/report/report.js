import request from '@/utils/request'

// 查询生产报表列表
export function listReport(query) {
  return request({
    url: '/report/report/list',
    method: 'get',
    params: query
  })
}

// 查询生产报表详细
export function getReport(reportId) {
  return request({
    url: '/report/report/' + reportId,
    method: 'get'
  })
}

// 新增生产报表
export function addReport(data) {
  return request({
    url: '/report/report',
    method: 'post',
    data: data
  })
}

// 修改生产报表
export function updateReport(data) {
  return request({
    url: '/report/report',
    method: 'put',
    data: data
  })
}

// 删除生产报表
export function delReport(reportId) {
  return request({
    url: '/report/report/' + reportId,
    method: 'delete'
  })
}
