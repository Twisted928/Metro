import request from 'umi-request';

export async function query(params) {
  return request('/customer/blacklist/list', {
    params,
  });
}

export async function queryHistory(params) {
  return request('/customer/blacklist/listlishi', {
    params,
  });
}
