import request from '@/utils/request'

// 查询订单管理列表
export function listOrder(query) {
  return request({
    url: '/mesoder/order/list',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getOrder(orderId) {
  return request({
    url: '/mesoder/order/' + orderId,
    method: 'get'
  })
}

// 新增订单管理
export function addOrder(data) {
  return request({
    url: '/mesoder/order',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateOrder(data) {
  return request({
    url: '/mesoder/order',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delOrder(orderId) {
  return request({
    url: '/mesoder/order/' + orderId,
    method: 'delete'
  })
}
