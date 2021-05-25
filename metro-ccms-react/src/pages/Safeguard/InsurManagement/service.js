import request from 'umi-request';

export async function query(params) {
  return request('/insurance/manage/list', {
    params,
  });
}

export async function tbList(params) {
  return request('/insurance/manage/save', {
    method: 'POST',
    data: { ...params },
  });
}

export async function deleteList(params) {
  return request('/insurance/manage/delete', {
    method: 'POST',
    data: { ...params },
  });
}
