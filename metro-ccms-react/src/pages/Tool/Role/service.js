import request from 'umi-request';

export async function query(params) {
  return request('/tool/gen/list', {
    params,
  });
}

export async function queryById(params) {
  return request(`/tool/gen/${params}`);
}

export async function preview(params) {
  return request(`/tool/gen/preview/${params}`);
}

export async function roleMenuTreeselect(params) {
  return request(`/system/menu/roleMenuTreeselect/${params}`);
}

export async function queryMenuTree() {
  return request(`/system/menu/treeselect`);
}

export async function remove(params) {
  return request('/tool/gen', {
    method: 'DELETE',
    data: { ...params },
  });
}
export async function update(params) {
  return request('/tool/gen', {
    method: 'PUT',
    data: { ...params },
  });
}

export async function importTable(params) {
  return request(`/tool/gen/importTable?tables=${params}`, {
    method: 'POST',
  });
}
