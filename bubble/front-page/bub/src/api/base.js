import request from '@/utils/request'

/**
 * 提供一种针对tableName统一的URL命名方式
 * @type {string}
 */
const baseUrl = '{tableName}'

export function searchData(tableName, page, search) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/query',
    method: 'post',
    data: search,
    params: page
  })
}

export function queryData(tableName, id) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/' + id,
    method: 'get'
  })
}

export function createData(tableName, model) {
  return request({
    url: baseUrl.replace('{tableName}', tableName),
    method: 'post',
    data: model
  })
}

export function updateData(tableName, model) {
  return request({
    url: baseUrl.replace('{tableName}', tableName),
    method: 'put',
    data: model
  })
}

export function deletData(tableName, id) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/' + id,
    method: 'delete'
  })
}

export function getColumns(tableName) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/GetColumns',
    method: 'get'
  })
}
