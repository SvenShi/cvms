import request from '@/utils/request'

// 查询简历列表
export function listCv(query) {
  return request({
    url: '/cv/cv/list',
    method: 'get',
    params: query
  })
}

// 查询简历详细
export function getCv(id) {
  return request({
    url: '/cv/cv/' + id,
    method: 'get'
  })
}

// 新增简历
export function addCv(data) {
  return request({
    url: '/cv/cv',
    method: 'post',
    data: data
  })
}

// 修改简历
export function updateCv(data) {
  return request({
    url: '/cv/cv',
    method: 'put',
    data: data
  })
}

// 删除简历
export function delCv(id) {
  return request({
    url: '/cv/cv/' + id,
    method: 'delete'
  })
}
