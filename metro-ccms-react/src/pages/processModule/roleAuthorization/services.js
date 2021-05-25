import { request } from 'umi';

export async function query(params) {
  return request('/activiti/authorization/rolelistPage', {
    params,
  });
}

export async function todoList(params) {
  return request('/common/utils/getTodoList', {
    params,
  });
}
export async function queryById(params) {
  return request('/activiti/authorization/getUserRoleList', {
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
  return request('/activiti/authorization/autherRole', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getAutherDoList(params) {
  return request('/activiti/authorization/getAutherRoleListByMainId', {
    method: 'POST',
    data: { ...params },
  });
}

export async function updateAutherList(params) {
  return request('/activiti/authorization/updateAutherRole', {
    method: 'POST',
    data: { ...params },
  });
}

export async function deleteAutherDoMain(params) {
  return request('/activiti/authorization/deleteAutherRoleMain', {
    method: 'POST',
    data: { ...params },
  });
}
export async function deleteAutherRole(params) {
  return request('/activiti/authorization/deleteAutherRole', {
    method: 'POST',
    data: { ...params },
  });
}
