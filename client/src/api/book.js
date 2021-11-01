import request from '@/utils/request'

export function bookLogin(data) {
  return request({
    url: '/login',
    method: 'post',
    data
  })
}

//获取用户列表
export function bookList(data) {
  return request({
    url: 'book/list',
    method: 'post',
    data
  })
}

//添加用户
export function bookAdd(data) {
  return request({
    url: 'book/add',
    method: 'post',
    data
  })
}

//修改用户
export function bookUpdate(data) {
  return request({
    url: 'book/update',
    method: 'post',
    data
  })
}

//删除用户
export function bookDelete(id) {
  return request({
    url: `book/delete/${id}`,
    method: 'post',   
      id
  })
}

//批量删除用户
export function userBatchDelete(data) {
  return request({
    url: 'book/delete/batch',
    method: 'post',
    data
  })
}
