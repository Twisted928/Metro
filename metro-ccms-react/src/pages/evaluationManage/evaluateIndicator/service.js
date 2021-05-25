import request from 'umi-request';

export async function query(params) {
  return request('/model/index/list', {
    params,
  });
}

export async function addAndUpdataList(params) {
  return request('/model/index/save', {
    method: 'POST',
    data: { ...params },
  });
}

export async function deleteList(params) {
  return request('/model/index/remove', {
    method: 'POST',
    data: { ...params },
  });
}
