import request from 'umi-request';

export async function query(params) {
  return request('/system/role/list', {
    params,
  });
}

export async function queryById(params) {
  return request(`/system/role/${params}`);
}

export async function roleMenuTreeselect(params) {
  return request(`/system/menu/roleMenuTreeselect/${params}`);
}

export async function queryMenuTree() {
  return request(`/system/menu/treeselect`);
}

export async function create(params) {
  return request('/system/role', {
    method: 'POST',
    data: { ...params },
  });
}
export async function update(params) {
  return request('/system/role', {
    method: 'PUT',
    data: { ...params },
  });
}
