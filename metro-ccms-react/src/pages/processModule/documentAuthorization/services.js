import { request } from 'umi';

export async function query(params) {
  return request('/activiti/authorization/documentlistPage', {
    params,
  });
}

export async function todoList(params) {
  return request('/common/utils/getTodoList', {
    params,
  });
}

export async function getRoleUser(params) {
  return request('/activiti/authorization/getRoleUserInfoList', {
    method: 'POST',
    data: { ...params },
  });
}

export async function addauther(params) {
  return request('/activiti/authorization/auther', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getAutherDoList(params) {
  return request('/activiti/authorization/getAutherDoListByMainId', {
    method: 'POST',
    data: { ...params },
  });
}

export async function updateAutherList(params) {
  return request('/activiti/authorization/updateAuther', {
    method: 'POST',
    data: { ...params },
  });
}

export async function deleteAutherDoMain(params) {
  return request('/activiti/authorization/deleteAutherDoMain', {
    method: 'POST',
    data: { ...params },
  });
}
export async function deleteAutherDo(params) {
  return request('/activiti/authorization/deleteAutherDo', {
    method: 'POST',
    data: { ...params },
  });
}
