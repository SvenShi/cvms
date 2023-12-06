import request from '@/utils/request'

// 查询简历列表
export function listCv(query) {
  return request({
    url: '/cv/list',
    method: 'get',
    params: query
  })
}

// 查询简历详细
export function getCv(id) {
  return request({
    url: '/cv/' + id,
    method: 'get'
  })
}

// 新增简历
export function upload(data) {
  return request({
    url: '/cv',
    method: 'post',
    data: data
  })
}

// 删除简历
export function delCv(id) {
  return request({
    url: '/cv/' + id,
    method: 'delete'
  })
}
