import request from '@/utils/request'

/**
 * 提供一种针对tableName统一的URL命名方式
 * @type {string}
 *
 * 未按照RESTFUL方式
 */
const baseUrl = '{tableName}'

export function searchData(tableName, search) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/query',
    method: 'post',
    data: search
  })
}

export function queryData(tableName, id) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/qrybykey/' + id,
    method: 'get'
  })
}

export function pageSearchData(tableName, page, search) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/pqry',
    method: 'post',
    data: search,
    params: page
  })
}

export function createData(tableName, model) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/create',
    method: 'post',
    data: model
  })
}

export function updateData(tableName, model) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/update',
    method: 'post',
    data: model
  })
}

export function deletData(tableName, id) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/rmbykey/' + id,
    method: 'get'
  })
}

export function getColumns(tableName) {
  return request({
    url: baseUrl.replace('{tableName}', tableName) + '/GetColumns',
    method: 'get'
  })
}
