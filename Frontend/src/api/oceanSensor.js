import request from '@/utils/request'

const baseUrl = 'http://linkworld.natapp1.cc/oceanSensor'

export function showSensor(page, size) {
  return request({
    url: baseUrl + '?page=' + page + '&size=' + size,
    method: 'get'
  })
}

export function addSensor(body) {
  return request({
    url: baseUrl,
    method: 'post',
    data: body,
    headers: { 'Content-Type': 'application/json;charset=UTF-8' }
  })
}

export function deleteSensor(body) {
  return request({
    url: baseUrl,
    method: 'delete',
    data: body
  })
}

export function updateSensor(body) {
  return request({
    url: baseUrl,
    method: 'put',
    data: body,
    headers: { 'Content-Type': 'application/json;charset=UTF-8' }
  })
}

