import request from '@/utils/request'

// 查询人才库列表
export function listInterviewee(query) {
  return request({
    url: '/interviewee/interviewee/list',
    method: 'get',
    params: query
  })
}

// 查询人才库详细
export function getInterviewee(id) {
  return request({
    url: '/interviewee/interviewee/' + id,
    method: 'get'
  })
}

// 新增人才库
export function addInterviewee(data) {
  return request({
    url: '/interviewee/interviewee',
    method: 'post',
    data: data
  })
}

// 修改人才库
export function updateInterviewee(data) {
  return request({
    url: '/interviewee/interviewee',
    method: 'put',
    data: data
  })
}

// 删除人才库
export function delInterviewee(id) {
  return request({
    url: '/interviewee/interviewee/' + id,
    method: 'delete'
  })
}
