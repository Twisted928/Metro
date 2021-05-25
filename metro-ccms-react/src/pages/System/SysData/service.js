import request from 'umi-request';

export async function queryDept(params) {
  return request('/system/data/list', {
    params,
  });
}

export async function create(params) {
  return request('/system/data', {
    method: 'POST',
    data: { ...params },
  });
}
export async function update(params) {
  return request('/system/data', {
    method: 'POST',
    data: { ...params },
  });
}

export async function getDeptAll() {
  return request(`/system/data/treeselect`);
}

export async function enableOrList(params) {
  return request('/system/data/enableOrDisable', {
    params,
  });
}
