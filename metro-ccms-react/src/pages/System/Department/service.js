import request from 'umi-request';

export async function queryDept(params) {
  return request('/system/dept/list', {
    params,
  });
}

export async function queryById(params) {
  return request(`/system/dept/${params}`);
}

export async function create(params) {
  return request('/system/dept', {
    method: 'POST',
    data: { ...params },
  });
}
export async function update(params) {
  return request('/system/dept', {
    method: 'PUT',
    data: { ...params },
  });
}

export async function getDeptAll() {
  return request(`/system/dept/treeselect`);
}
