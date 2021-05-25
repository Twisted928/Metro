import request from 'umi-request';

export async function queryMenu(params) {
  return request('/system/menu/list', {
    params,
  });
}

export async function queryById(params) {
  return request(`/system/menu/${params}`);
}

export async function create(params) {
  return request('/system/menu', {
    method: 'POST',
    data: { ...params },
  });
}
export async function update(params) {
  return request('/system/menu', {
    method: 'PUT',
    data: { ...params },
  });
}

export async function reloadResCache() {
  return request('/system/menu/reloadResCache');
}


export async function queryMenuTree() {
  return request(`/system/menu/treeselect`);
}