import request from 'umi-request';

export async function query(params) {
  return request('/Customergroup/management/list', {
    params,
  });
}
export async function listInfo(params) {
  return request('/Customergroup/management/listInfo', {
    method: 'POST',
    data: { ...params },
  });
}
