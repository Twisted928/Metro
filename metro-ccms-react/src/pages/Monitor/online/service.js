import request from 'umi-request';

export async function queryRule(params) {
  return request('/system/dict/type/list', {
    params,
  });
}

export async function queryDataById(params) {
  return request(`/system/dict/type/${params}`);
}

export async function create(params) {
  return request('/system/dict/type', {
    method: 'POST',
    data: { ...params },
  });
}
export async function update(params) {
  return request('/system/dict/type', {
    method: 'PUT',
    data: { ...params },
  });
}

export async function doExport(params) {
  return request('/system/dict/type', {
    method: 'get',
    responseType: 'blob',
    params,
  });
}

export async function queryDataDById(params) {
  return request(`/system/dict/data/${params}`);
}

export async function createData(params) {
  return request('/system/dict/data', {
    method: 'POST',
    data: { ...params },
  });
}
export async function updateData(params) {
  return request('/system/dict/data', {
    method: 'PUT',
    data: { ...params },
  });
}
